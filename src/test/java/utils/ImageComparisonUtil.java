package utils;

import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This is a basic pixel-by-pixel approach. It can be sensitive to small differences in fonts
 * , antialiasing, etc. If you have frequent false positives
 * , consider OpenCV or a commercial tool that supports fuzzy matching.
 */
public class ImageComparisonUtil {

    // Simple pixel-by-pixel approach
    public static boolean compareImages(Screenshot newScreenshot, File baselineImageFile, double tolerance) throws IOException, IOException {
        BufferedImage actualImage = newScreenshot.getImage();
        BufferedImage expectedImage = ImageIO.read(baselineImageFile);

        // Check if dimensions match
        if (actualImage.getWidth() != expectedImage.getWidth()
                || actualImage.getHeight() != expectedImage.getHeight()) {
            return false;
        }

        // Compare each pixel (may be slow for large images)
        int width = actualImage.getWidth();
        int height = actualImage.getHeight();
        int differingPixels = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int actualRGB = actualImage.getRGB(x, y);
                int expectedRGB = expectedImage.getRGB(x, y);
                if (actualRGB != expectedRGB) {
                    differingPixels++;
                }
            }
        }

        double totalPixels = width * height;
        double diffPercentage = (differingPixels / totalPixels) * 100;

        return diffPercentage <= tolerance; // e.g. tolerance in % (like 0.1%)
    }
}

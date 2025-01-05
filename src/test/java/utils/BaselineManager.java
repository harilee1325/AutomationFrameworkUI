package utils;

import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BaselineManager {

    // Where you store baseline images
    private static final String BASELINE_FOLDER = "baselineimages";

    public static void storeBaseline(Screenshot screenshot, String baselineName) throws IOException {
        // Extract the BufferedImage from AShot's Screenshot object
        BufferedImage image = screenshot.getImage();

        // Ensure the baseline directory exists
        File baselineDir = new File(BASELINE_FOLDER);
        if (!baselineDir.exists()) {
            baselineDir.mkdirs();
        }

        // Construct the file path for the baseline
        File baselineFile = new File(baselineDir, baselineName + ".png");

        // (Optional) Check if file already exists
        if (baselineFile.exists()) {
            System.out.println("Baseline image '" + baselineName + "' already exists. Overwriting...");
        } else {
            System.out.println("Creating new baseline image: " + baselineFile.getAbsolutePath());
        }

        // Write the image to disk
        ImageIO.write(image, "png", baselineFile);
    }
}


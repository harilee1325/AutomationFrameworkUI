package reporting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogReporter {
    private static final Logger logger = LogManager.getLogger(LogReporter.class);

    public static void logInfo(String message) {
        // Log to Log4j
        logger.info(message);

        // Log to ExtentReports (if a test is active)
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().info(message);
        }
    }

    public static void logInfoOnly(String message) {
        // Log to Log4j
        logger.info(message);
    }

    public static void logError(String message, Throwable throwable) {
        // Log error to Log4j
        logger.error(message, throwable);

        // Log error to ExtentReports (if a test is active)
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().fail(message + "\nException: " + throwable.getMessage());
        }
    }

    public static void logWarn(String message) {
        logger.warn(message);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().warning(message);
        }
    }
}

package utils;

import org.openqa.selenium.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: ByJavaScript
 * ByJavaScript constructor takes a JavaScript snippet as a string.
 *
 */
public class ByJavaScript extends By {
    private final String jsCode;
    private final WebDriver driver;


    // Constructor takes a JS snippet that returns a single element or a collection of elements
    public ByJavaScript(String jsCode, WebDriver driver) {
        this.jsCode = jsCode;
        this.driver = driver;
    }

    @Override
    public WebElement findElement(SearchContext context) {
        Object scriptResult = ((JavascriptExecutor) driver).executeScript("return " + jsCode);

        if (scriptResult instanceof WebElement) {
            return (WebElement) scriptResult;
        }
        else if (scriptResult instanceof List) {
            // If your JS returns an array or NodeList of 1 element
            List<?> results = (List<?>) scriptResult;
            if (!results.isEmpty() && results.get(0) instanceof WebElement) {
                return (WebElement) results.get(0);
            }
        }

        throw new NoSuchElementException("No element found using JS: " + jsCode);
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        Object scriptResult = ((JavascriptExecutor) driver).executeScript("return " + jsCode);

        if (scriptResult instanceof List) {
            // Convert each item in the list to a WebElement
            List<?> results = (List<?>) scriptResult;
            return results.stream()
                    .filter(obj -> obj instanceof WebElement)
                    .map(obj -> (WebElement) obj)
                    .collect(Collectors.toList());
        }
        else if (scriptResult instanceof WebElement) {
            // Single element can be wrapped in a list
            return Stream.of((WebElement) scriptResult).collect(Collectors.toList());
        }

        return List.of();
    }
}

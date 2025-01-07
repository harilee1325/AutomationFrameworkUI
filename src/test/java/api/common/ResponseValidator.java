package api.common;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Unexpected status code: " + response.getStatusCode());
    }

    public static void validateResponseTime(Response response, long maxResponseTime) {
        Assert.assertTrue(response.getTime() <= maxResponseTime,
                "Response time exceeded: " + response.getTime() + "ms");
    }

    public static void validateResponseBodyContains(Response response, String expectedContent) {
        Assert.assertTrue(response.getBody().asString().contains(expectedContent),
                "Response body does not contain: " + expectedContent);
    }
}

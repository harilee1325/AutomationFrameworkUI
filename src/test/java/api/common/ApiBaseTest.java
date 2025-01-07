package api.common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {
    protected RequestSpecification request;

    @BeforeClass
    public void setUp() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Replace with your API URL
    }
}

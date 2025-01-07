package api.common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
    public static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(Endpoints.BASE_URL)
                .contentType("application/json")
                .accept("application/json");
    }

    public static String buildUserRequestBody(String name, String email, String username) {
        return """
        {
            "name": "%s",
            "email": "%s",
            "username": "%s"
        }
        """.formatted(name, email, username);
    }
}

package api;

import api.common.ApiBaseTest;
import api.common.Endpoints;
import api.common.RequestBuilder;
import api.common.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserApiTest extends ApiBaseTest {
    @Test
    public void testGetAllUsers() {
        Response response = RequestBuilder.getRequestSpec()
                .get(Endpoints.USERS);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateResponseTime(response, 2000); // 2 seconds
    }

    @Test
    public void testCreateUser() {
        String requestBody = RequestBuilder.buildUserRequestBody(
                "John Doe", "john.doe@example.com", "johndoe");

        Response response = RequestBuilder.getRequestSpec()
                .body(requestBody)
                .post(Endpoints.USERS);

        ResponseValidator.validateStatusCode(response, 201);
        ResponseValidator.validateResponseBodyContains(response, "John Doe");
    }

    @Test
    public void testGetUserById() {
        Response response = RequestBuilder.getRequestSpec()
                .pathParam("id", 1)
                .get(Endpoints.USER_BY_ID);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateResponseBodyContains(response, "Leanne Graham");
    }
}

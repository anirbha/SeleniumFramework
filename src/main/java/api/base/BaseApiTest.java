package api.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {
    protected static RequestSpecification request;

    public static RequestSpecification getRequest(String BaseUrl, String endpoint)
    {
        request=RestAssured.given().baseUri(BaseUrl).basePath(endpoint).header("Content-Type","application/json");
        return request;
    }
}

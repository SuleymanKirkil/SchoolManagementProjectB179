package school_management.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken(){
        String url = "https://managementonschools.com/app/auth/login";
        String body = "{\n" +
                "  \"password\": \"Gurkay123+\",\n" +
                "  \"username\": \"AdminGurkay\"\n" +
                "}";

        Response response = given().body(body).contentType(ContentType.JSON).when().post(url);
        return response.jsonPath().getString("token");
    }
}

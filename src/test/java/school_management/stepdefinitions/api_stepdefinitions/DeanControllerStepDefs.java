package school_management.stepdefinitions.api_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import school_management.pojos.DeanControllerPojo.DeanPojo;
import school_management.pojos.DeanControllerPojo.DeanPostPojo;
import school_management.pojos.DeanControllerPojo.ObjectPojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static school_management.base_url.BaseUrl.setUp;
import static school_management.base_url.BaseUrl.spec;

public class DeanControllerStepDefs {
    DeanPostPojo payLoad;
    Response response;
    DeanPojo actualData;
    public static Integer userId;
    public static ObjectPojo objectPojo;
    DeanPojo expectedData;

    @Given("User is authorized as {string}")
    public void userIsAuthorizedAs(String role) {
        role = role.trim().toLowerCase();

        switch (role) {
            case "admin":
                setUp("AdminGurkay", "Gurkay123+");
                break;
            case "dean":
                setUp("", "");
                break;
            default:
                System.out.println("Please Enter with valid role");
        }
    }

    @And("User sets the Url for Dean Save")
    public void userSetsTheUrlForDeanSave() {
        spec.pathParams("first", "dean"
                , "second", "save");
    }

    @And("sets the payload for Dean Save")
    public void setsThePayloadForDeanSave() {
        payLoad = new DeanPostPojo("1975-05-05", "Istanbul", "FEMALE", "Tom", "12345678Aa", "524-456-7395", "523-65-6879", "Sawyer", "TomSawyer");

    }

    @When("sends post request and get response")
    public void sendsPostRequestAndGetResponse() {
        response = given(spec).body(payLoad).post("{first}/{second}");
        //response.prettyPrint();
    }

    @Then("verifies status code is {int}")
    public void verifiesStatusCodeIs(int code) {
        assertEquals(code, response.statusCode());
    }

    @And("verifies response body")
    public void verifiesResponseBody() {
        actualData = response.as(DeanPojo.class);
/*{



  "surname": "Sawyer",
  "username": "TomSawyer"
}

 */
        assertEquals(payLoad.getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(payLoad.getBirthPlace(), actualData.getObject().getBirthPlace());
        assertEquals(payLoad.getGender(), actualData.getObject().getGender());
        assertEquals(payLoad.getName(), actualData.getObject().getName());
        assertEquals(payLoad.getPhoneNumber(), actualData.getObject().getPhoneNumber());
        assertEquals(payLoad.getSsn(), actualData.getObject().getSsn());
        assertEquals(payLoad.getSurname(), actualData.getObject().getSurname());
        assertEquals(payLoad.getUsername(), actualData.getObject().getUsername());
    }

    @And("User gets id of the Dean with username {string}")
    public void userGetsIdOfTheDeanWithUsername(String userName) {
        spec.pathParams("first", "dean"
                , "second", "getAll");
        response = given(spec).when().get("{first}/{second}");
        List<Integer> userIdList = response.jsonPath().getList("findAll{it.username=='" + userName + "'}.userId");
        System.out.println(userIdList + "list ");
        userId = userIdList.get(0);
    }

    @And("User sets the Url for Get Dean By id")
    public void userSetsTheUrlForGetDeanById() {
        spec.pathParams("first", "dean", "second", "getManagerById", "third", userId);
    }

    @And("sets the expected data for Get Dean By id")
    public void setsTheExpectedDataForGetDeanById() {
        objectPojo = new ObjectPojo("1975-05-05", "Istanbul", "FEMALE", "Tom", "524-456-7395",
                "523-65-6879", "Sawyer", userId, "TomSawyer");
        expectedData = new DeanPojo("OK", "Dean successfully found", objectPojo);

    }

    @When("sends get request and get response")
    public void sendsGetRequestAndGetResponse() {
        response = given(spec).when().get("{first}/{second}/{third}");
    }

    @And("verifies response body for Get Dean By id")
    public void verifiesResponseBodyForGetDeanById() {
        DeanPojo actualData = response.as(DeanPojo.class);
/*
{
    "object": {
        "userId": 3201,
        "username": "TomSawyer",
        "name": "Tom",
        "surname": "Sawyer",
        "birthDay": "1975-05-05",
        "ssn": "523-65-6879",
        "birthPlace": "Turkey",
        "phoneNumber": "524-456-7395",
        "gender": "FEMALE"
    },
    "message": "Dean Saved",
    "httpStatus": "CREATED"
}
 */
        assertEquals(objectPojo.getUserId(), actualData.getObject().getUserId());
        assertEquals(objectPojo.getUsername(), actualData.getObject().getUsername());
        assertEquals(objectPojo.getName(), actualData.getObject().getName());
        assertEquals(objectPojo.getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(objectPojo.getSsn(), actualData.getObject().getSsn());
        assertEquals(objectPojo.getBirthPlace(), actualData.getObject().getBirthPlace());
        assertEquals(objectPojo.getPhoneNumber(), actualData.getObject().getPhoneNumber());
        assertEquals(objectPojo.getGender(), actualData.getObject().getGender());
        assertEquals(expectedData.getHttpStatus(), actualData.getHttpStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
    }

    @And("User deletes the created dean")
    public void userDeletesTheCreatedDean() {
        spec.pathParams("first", "dean", "second", "delete", "third", userId);
        given(spec).when().delete("{first}/{second}/{third}").then().statusCode(200);

    }
}

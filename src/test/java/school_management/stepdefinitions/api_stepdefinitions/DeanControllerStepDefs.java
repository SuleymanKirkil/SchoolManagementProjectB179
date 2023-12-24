package school_management.stepdefinitions.api_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import school_management.pojos.DeanControllerPojo.DeanPojo;
import school_management.pojos.DeanControllerPojo.DeanPostPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static school_management.base_url.BaseUrl.setUp;
import static school_management.base_url.BaseUrl.spec;

public class DeanControllerStepDefs {
    DeanPostPojo payLoad;
    Response response;
    DeanPojo actualData;
    @Given("User is authorized as {string}")
    public void userIsAuthorizedAs(String role) {
        role = role.trim().toLowerCase();

        switch (role){
            case "admin":
                setUp();
                break;
            case "dean" :
                setUp();
                break;
        }
    }

    @And("User sets the Url for Dean Save")
    public void userSetsTheUrlForDeanSave() {
        spec.pathParams("first","dean"
        ,"second","save");
    }

    @And("sets the payload for Dean Save")
    public void setsThePayloadForDeanSave() {
        payLoad = new DeanPostPojo("1975-05-05","Turkey","FEMALE","Tom","12345678Aa","524-456-7395","523-65-6879","Sawyer","TomSawyer");

    }

    @When("sends post request and get response")
    public void sendsPostRequestAndGetResponse() {
        response = given(spec).body(payLoad).post("{first}/{second}");
        //response.prettyPrint();
    }

    @Then("verifies status code is {int}")
    public void verifiesStatusCodeIs(int code) {
        assertEquals(code,response.statusCode());
    }

    @And("verifies response body")
    public void verifiesResponseBody() {
       actualData =response.as(DeanPojo.class);
/*{



  "surname": "Sawyer",
  "username": "TomSawyer"
}

 */
       assertEquals(payLoad.getBirthDay(),actualData.getObject().getBirthDay());
       assertEquals(payLoad.getBirthPlace(),actualData.getObject().getBirthPlace());
       assertEquals(payLoad.getGender(),actualData.getObject().getGender());
       assertEquals(payLoad.getName(),actualData.getObject().getName());
       assertEquals(payLoad.getPhoneNumber(),actualData.getObject().getPhoneNumber());
       assertEquals(payLoad.getSsn(),actualData.getObject().getSsn());
       assertEquals(payLoad.getSurname(),actualData.getObject().getSurname());
       assertEquals(payLoad.getUsername(),actualData.getObject().getUsername());
    }
}

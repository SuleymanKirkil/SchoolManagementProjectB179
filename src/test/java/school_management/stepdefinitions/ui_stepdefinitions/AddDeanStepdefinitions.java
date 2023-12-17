package school_management.stepdefinitions.ui_stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import school_management.pages.AddDeanPage;
import school_management.utilities.ConfigReader;
import school_management.utilities.Driver;
import school_management.utilities.ReusableMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddDeanStepdefinitions {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    AddDeanPage addDean = new AddDeanPage();
    Faker faker = new Faker();
    Response response;

    String name;
    static int userId;
    @Given("User visit the url")
    public void user_visit_the_url() {

        Driver.getDriver().get(ConfigReader.getProperty("manage_Url"));

    }
    @When("User clicks on log in button")
    public void user_clicks_on_log_in_button() {
        addDean = new AddDeanPage();
        if (addDean.loginLink.isDisplayed()) {
            addDean.loginLink.click();
        } else {
            addDean.menuButton.click();
        }
    }
    @When("User enters {string} in username field")
    public void user_enters_in_username_field(String string) {
        addDean.usernameBox.sendKeys(string);

    }
    @When("Enter {string} in password field")
    public void enter_in_password_field(String string) {
        addDean.passwordBox.sendKeys(string);

    }
    @When("Clicks on the Login button")
    public void clicks_on_the_login_button() {
        ReusableMethods.click(addDean.loginButton);

    }
    @When("Clicks on the Menu button")
    public void clicks_on_the_menu_button() {
        addDean.menuButton.click();

    }
    @When("Clicks on the Dean Management")
    public void clicks_on_the_dean_management() {
        addDean.deanManagementButton.click();

    }
    @When("Enters {string} in Name field")
    public void enters_in_name_field(String str) {
        name = faker.name().firstName();
        if (str.equals("a name")) {
            addDean.name.sendKeys(name);
        } else {
            addDean.name.sendKeys(str);
        }
    }
    @When("Enters {string} in SurName field")
    public void enters_in_sur_name_field(String str) {
        String surname = faker.name().lastName();
        if (str.equals("a surname")) {
            addDean.surname.sendKeys(surname);
        } else {
            addDean.surname.sendKeys(str);
        }
    }
    @When("Enters {string} in BirthPlace field")
    public void enters_in_birth_place_field(String str) {
        String birthPlace = faker.lorem().word();
        if (str.equals("bir yer ismi")) {
            addDean.birthPlace.sendKeys(birthPlace);
        } else  {
            addDean.birthPlace.sendKeys(str);
            ReusableMethods.wait(2);
        }
    }
    @When("Enters {string} in Gender field")
    public void enters_in_gender_field(String str) {
        String gender = "";
        if (str.equalsIgnoreCase("FEMALE")) {
            addDean.genderFemale.click();
            gender = "FEMALE";
        } else if (str.equalsIgnoreCase("MALE")) {
            addDean.genderMale.click();
            gender = "MALE";
        } else {
            System.out.println("Hatalı veri girdiniz.");
        }
        ReusableMethods.wait(2);
    }
    @When("Enters {string} in DateOfBirth field")
    public void enters_in_date_of_birth_field(String date) {
        addDean.birthDay.sendKeys(date);
        ReusableMethods.wait(2);
    }

    @And("Enters {string} in Phone Number field")
    public void entersInPhoneNumberField(String str) {

        if (str.equals("a phone number")) {
            String ListPhoneNo = faker.regexify("[0-9]{3}-[0-9]{3}-[0-9]{4}");
            addDean.phoneNumber.sendKeys(ListPhoneNo);
        } else {
            addDean.phoneNumber.sendKeys(str);
        }
    }
    public static String ListSSNNo;
    @When("Enters SSN in SSNno field")
    public void enters_ssn_in_ss_nno_field() {
        ListSSNNo = "523-65-6879";
        addDean.ssn.sendKeys(ListSSNNo);
        ReusableMethods.wait(2);
    }

    @When("Enters {string} in UserName field")
    public void enters_in_user_name_field(String str) {
        String username = faker.random().nextInt(1000,9999)+ "tom";
        if (str.equals("a user name")) {
            addDean.username.sendKeys(username);
        } else {
            addDean.username.sendKeys(str);
        }
    }
    @When("Enters password in Password field")
    public void enters_password_in_password_field() {
        addDean.deanPassword.sendKeys("12345678Aa");

    }
    @When("Clicks on DeanSubmit buton")
    public void clicks_on_dean_submit_buton() {
        addDean.deanSubmit.click();
        ReusableMethods.wait(2);
    }
    @Then("Verifies  Dean is created")
    public void verifies_dean_is_created() {
        Assert.assertTrue(addDean.deanSavedPopUp.isDisplayed());

    }


}

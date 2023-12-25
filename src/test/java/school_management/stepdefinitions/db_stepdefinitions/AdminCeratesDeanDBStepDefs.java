package school_management.stepdefinitions.db_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import school_management.stepdefinitions.api_stepdefinitions.DeanControllerStepDefs;

import java.sql.*;

import static junit.framework.TestCase.assertEquals;
import static school_management.stepdefinitions.api_stepdefinitions.DeanControllerStepDefs.objectPojo;

public class AdminCeratesDeanDBStepDefs {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @Given("User Set connection")
    public void userSetConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://managementonschools.com:5432/school_management","select_user","43w5ijfso");
    }

    @And("User creates statememnt")
    public void userCreatesStatememnt() throws SQLException {
        statement = connection.createStatement();
    }

    @When("User execute query for created dean")
    public void userExecuteQueryForCreatedDean() throws SQLException {
        resultSet = statement.executeQuery("select * from dean where username ='TomSawyer'");
    }

    @Then("validates result set")
    public void validatesResultSet() throws SQLException {
        resultSet.next();
        assertEquals(objectPojo.getBirthDay(),resultSet.getString("birth_day"));
        assertEquals(objectPojo.getBirthPlace(),resultSet.getString("birth_place"));
        assertEquals(1,resultSet.getInt("gender"));
        assertEquals(objectPojo.getName(),resultSet.getString("name"));
    }

    @And("User terminate connection")
    public void userTerminateConnection() throws SQLException {
        statement.close();
        connection.close();
    }
}

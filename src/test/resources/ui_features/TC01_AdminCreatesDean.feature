@UI
Feature: Admin creates Dean

  Scenario: Admin should be able to create Dean
    Given User visit the url
    When User clicks on log in button
    And  User enters "AdminGurkay" in username field
    And Enter "Gurkay123+" in password field
    And Clicks on the Login button
    And Clicks on the Menu button
    And Clicks on the Dean Management
    And Enters "Tom" in Name field
    And Enters "Sawyer" in SurName field
    And Enters "Istanbul" in BirthPlace field
    And Enters "Female" in Gender field
    And Enters "05051975" in DateOfBirth field
    And Enters "524-456-7395" in Phone Number field
    And Enters SSN in SSNno field
    And Enters "TomSawyer" in UserName field
    And Enters password in Password field
    And Clicks on DeanSubmit buton
    Then Verifies  Dean is created
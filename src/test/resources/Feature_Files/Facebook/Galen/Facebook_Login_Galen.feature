Feature: Login to Facebook with Galen validation

  Scenario: Successful Login to Facebook with default credentials
    Given I click on accept cookies
    And I validate page using Galen Framework "Facebook_Login_without_inserted_credentails" file
    And I populate values: email "jasecuframework@gmail.com" , password "CORRECTPASSWORD"
    And I validate page using Galen Framework "Facebook_Login_with_inserted_credentails" file
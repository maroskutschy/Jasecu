Feature: Login to Facebook with Galen validation

  Scenario: Successful Login to Facebook with default credentials
    And I validate page using Galen Framework "Facebook_Login_without_inserted_credentails" file
    And I populate values: email "jasecuframework@gmail.com" , password "Jasecu12"
    And I validate page using Galen Framework "Facebook_Login_with_inserted_credentails" file
    When I login to facebook with default credentials
    Then Facebook Welcome Page is successfully displayed
Feature: Login to Facebook

  #@Login
  #Scenario: Successful Login to Facebook with specified credentials
  #  When I successfully login to facebook with email "jasecuframework@gmail.com" and password "Jasecu12"
    #When I successfully login to facebook with email "XXX" and password "Jasecu12"
   # Then Facebook Welcome Page is successfully displayed

  Scenario: Successful Login to Facebook with default credentials
    #Given I open browser for Galen Framework ""
    And I validate page using Galen Framework "login2" file
    And I populate values: email "jasecuframework@gmail.com" , password "Jasecu12"
    And I validate page using Galen Framework "login2_with_inserted_text" file
    When I successfully login to facebook with default credentials
    Then Facebook Welcome Page is successfully displayed
    #And I validate page using Galen Framework "login2.gspec" file
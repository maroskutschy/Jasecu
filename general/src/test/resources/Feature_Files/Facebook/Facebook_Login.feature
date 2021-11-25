Feature: Login to Facebook

  Scenario: Successful Login to Facebook with specified credentials

    Given I successfully login to facebook with email "jasecuframework@gmail.com" and password "CORRECTPASSWORD"
    Then Facebook Welcome Page is successfully displayed

  @LoginTest
  Scenario Outline: Unsuccessful Login to Facebook
    When I unsuccessfully login to facebook with email "<email>" and password "<password>"
    Then Because of "<reason of problem>" Incorrect Login Page is displayed with following message "<message>"

    Examples:
    |reason of problem               |email                      |password   |message                                                                                     |
    |Incorrect password              |jasecuframework@gmail.com  |INCORRECTPASSWORD|The password you’ve entered is incorrect. Forgot Password?                                  |
    |Incorrect email or phone number |jasecuxxframeworkx         |CORRECTPASSWORD   |The email or phone number you’ve entered doesn’t match any account. Sign up for an account. |
    |Incorrect email                 |xyzadsfasd@gmail.com       |CORRECTPASSWORD   |The email you’ve entered doesn’t match any account. Sign up for an account.                 |
    |Incorrect email and password    |xyzadsfasd@gmail.com       |INCORRECTPASSWORD|The email you’ve entered doesn’t match any account. Sign up for an account.                 |
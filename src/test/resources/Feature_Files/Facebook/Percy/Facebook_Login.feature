Feature: Login to Facebook with Percy Validation

  Scenario: Successful Login to Facebook with specified credentials and Percy validation

    Given I successfully login to facebook with email "jasecuframework@gmail.com" and password "CORRECTPASSWORD"
    Then Facebook Welcome Page is successfully displayed
    And I make Percy Visual Test validation with screenshot name: "Welcome Page"

    # ONE TIME SETUP:
    # Install NodeJS
    # in cmd/terminal > switch to root of jasecu framework project
    # perform command: npm init
    # perform command: npm install --save-dev @percy/agent
    # BEFORE EACH RUN:
    # in cmd/terminal > switch to root of jasecu framework project
    # mac/linux: perform command: export PERCY_TOKEN=7eb638ab17aa93514e1f97bfaf538590fd3a643c4993a09e73f49699e7eeb1ca
    # windows: perform command: set PERCY_TOKEN=7eb638ab17aa93514e1f97bfaf538590fd3a643c4993a09e73f49699e7eeb1ca
    # perform command: npx percy exec -- mvn install -PTestNG -DTestSuite=FacebookPercy.xml


  #@LoginTest
  #Scenario Outline: Unsuccessful Login to Facebook
  #  When I unsuccessfully login to facebook with email "<email>" and password "<password>"
  #  Then Because of "<reason of problem>" Incorrect Login Page is displayed with following message "<message>"

   # Examples:
   # |reason of problem               |email                      |password   |message                                                                                     |
   # |Incorrect password              |jasecuframework@gmail.com  |INCORRECTPASSWORD|The password you’ve entered is incorrect. Forgot Password?                                  |
   # |Incorrect email or phone number |jasecuxxframeworkx         |CORRECTPASSWORD   |The email or phone number you’ve entered doesn’t match any account. Sign up for an account. |
   # |Incorrect email                 |xyzadsfasd@gmail.com       |CORRECTPASSWORD   |The email you’ve entered doesn’t match any account. Sign up for an account.                 |
   # |Incorrect email and password    |xyzadsfasd@gmail.com       |INCORRECTPASSWORD|The email you’ve entered doesn’t match any account. Sign up for an account.                 |
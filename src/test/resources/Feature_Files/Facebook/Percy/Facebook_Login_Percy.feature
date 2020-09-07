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
    # mac/linux: perform command: export PERCY_TOKEN=YOUR_PERCY_TOKEN
    # windows: perform command: set PERCY_TOKEN=YOUR_PERCY_TOKEN
    # perform command: npx percy exec -- mvn install -PTestNG -DTestSuite=FacebookPercy.xml
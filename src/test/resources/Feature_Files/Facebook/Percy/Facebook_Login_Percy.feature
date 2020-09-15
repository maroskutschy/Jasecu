Feature: Login to Facebook with Percy Validation

  Scenario: Successful Login to Facebook with specified credentials and Percy validation

    When I login to facebook with default credentials
    Then Facebook Welcome Page is successfully displayed
    And And pause for "6" seconds
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
    # settings of Jenkins job (example of running on Mac slave):
    # Build: Execute shell:
    #export PERCY_TOKEN=YOUR_PERCY_TOKEN
    #export PATH=/usr/local/bin
    #export M2_HOME=/usr/local/Cellar/maven/3.2.3/libexec
    #export M2=$M2_HOME/bin
    #export PATH=$M2:$PATH
    #npx percy exec -- mvn install -PTestNG -DTestSuite=FacebookPercy.xml

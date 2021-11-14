Feature: Login to Facebook with Applitools validation

  Scenario: Successful Login to Facebook with default credentials - Applitools

    When I login to facebook with default credentials
    Then Facebook Welcome Page is successfully displayed
    Then I make Applitools Visual Test validation running via UltraFast Grid with test name: "Test 1" and step name: "Login"
    Then I make Applitools Visual Test validation by xpath of region: "//span[contains(., 'on your mind')]" running via UltraFast Grid with test name: "Task 2" and step name: "Section with the content"
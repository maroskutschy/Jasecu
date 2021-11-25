Feature: Login to Facebook Sample

  Scenario: Successful Login to Facebook with default credentials

    When I login to facebook with default credentials
    Then Facebook Welcome Page is successfully displayed

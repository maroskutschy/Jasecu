Feature: API example

  Scenario: users

    * url 'https://jsonplaceholder.typicode.com'
    Given path 'users'
    When method get
    Then status 200

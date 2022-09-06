Feature: sample karate test script
  for help, see: https://github.com/intuit/karate/wiki/IDE-Support

  Background:
    * url 'https://jsonplaceholder.typicode.com'

  Scenario: get all users and then validate 1st user
    Given path 'users'
    When method get
    Then status 200
    And match response[0].username == "BretX"
    And match response[0].address.city == "Gwenborough"

  Scenario: get all users and then validate 2nd user
    Given path 'users'
    When method get
    Then status 200
    And match response[1].username == "Antonette"
    And match response[1].address.city == "WisokyburghY"

  Scenario: get all users and then validate 3rd user
    Given path 'users'
    When method get
    Then status 200
    And match response[2].username == "SamanthaZ"
    And match response[2].address.city == "McKenziehaven"

  Scenario: get all users and then validate 4th user
    Given path 'users'
    When method get
    Then status 200
    And match response[3].username == "KarianneABCD"
    And match response[3].address.city == "South Elvis"

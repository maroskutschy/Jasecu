Feature: Make and delete the post

  Background: Login

    Given I login to facebook with default credentials

  @PostTest
  Scenario: Make the post

    Given Facebook Welcome Page is successfully displayed
    When I make the post with text:
    | Text of the post|
    | post number 1   |
    | post number 2   |
    Then The post with following text is successfully saved:
    | Text of the post|
    | post number 1   |
    | post number 2   |

  @PostTest
  Scenario: Delete the post

    Given Facebook Welcome Page is successfully displayed
    #And switch to News Feed Most Recent option
    When I delete following post from the top:
     | Order of post |
     | 2             |
     | 1             |
    Then The post with following text is successfully deleted:
     |Text of the post |
     | post number 1   |
     | post number 2   |



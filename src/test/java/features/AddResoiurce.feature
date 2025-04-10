Feature: Validating addPlace API's

  Scenario Outline: Verify if addPlace is being Succesfully added using AddPlaceAPI
    Given Add Place Payload "<name>"  "<language>" "<address>"
    When calls "AddPlaceAPI" with POST http request
    Then the API call got success with status code

    Examples:
      | name | language | address            |
      | Mani | Java     | World cross center |

    Scenario: hgfd
      Given Delete API "deletePlaceAPI"
      When the API call got success with status code
      Then "status" in response body is "OK"
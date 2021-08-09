Feature: Gmail tests

  Scenario Outline: Share status in gmail
    Given The user opens gmail Accounts Page
    When User login gmail account
    Then User share "<status>" status
    Examples:
      | status  |
      | GO AHEAD |
      | DON'T GIVE UP |
      | Be yourself |


  Scenario: User signed out from hangouts
    Given The user opens gmail Accounts Page
    When User login gmail account
    Then User signed out from hangouts
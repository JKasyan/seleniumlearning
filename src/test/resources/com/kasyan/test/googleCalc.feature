Feature: Check addition in Google calculator

  Scenario: Addition
    Given User open google
    When User enter "7*8" in search bar
    Then User should get "56"
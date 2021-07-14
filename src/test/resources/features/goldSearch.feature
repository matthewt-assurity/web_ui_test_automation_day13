Feature: Search Count
  Trade Me shows the number of results for a search

  Scenario: Search for Gold items shows count
    Given I am conducting a TradeMe search
    When I search for "Gold"
    Then I see "27" results

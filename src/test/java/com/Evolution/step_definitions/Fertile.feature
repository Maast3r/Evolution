Feature: Fertile
  During game play, when Phase3 ends and a player has a species which has fertile,
  the species population goes up by 1

  Scenario Outline: Population up by 1
    Given I have a new Game with <Num Players> real players
    And it is currently real game Phase3
    And there is a species that has Fertile
    And the species has a population of 2
    When Phase3 ends in real game
    Then the species population should be 3
    Examples:
      | Num Players |
      | 3           |
      | 4           |
      | 5            |
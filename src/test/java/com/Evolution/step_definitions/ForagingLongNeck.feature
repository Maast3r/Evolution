Feature: Foraging LongNeck
  During game play, when phase three ends and the player species has foragin and longneck,
  the species should eat twice

  Scenario Outline: Eat twice at phase 3
    Given I have a new Game with <Num Players> real players
    And it is currently real game Phase3
    And there is a species that has LongNeck
    And there is a species that has Foraging
    And the species has a population of 2
    When Phase3 ends in real game
    Then the species eaten food should increase by 2
    Examples:
      | Num Players |
      | 3           |
      | 4           |
      | 5           |
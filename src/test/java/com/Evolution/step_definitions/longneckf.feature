Feature: LongNeck
  During game play, when a given player feeds his species and his species owns the Cooperation trait,
  the species should eat at the end of phase 3. (Only happens in Phase 4)

  Scenario Outline: Eat twice
    Given I have a new Game with <Num Players> real players
    And it is currently real game Phase3
    And there is a species that has LongNeck
    And the species has a population of 2
    When Phase3 ends in real game
    Then the species eaten food should increase by 1
    Examples:
      | Num Players |
      | 3           |
      | 4           |
      | 5           |
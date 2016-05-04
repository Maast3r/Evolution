Feature: Foraging
  During game play, when a given player feeds his species and his species owns the Foraging trait,
  the species should eat twice. (Only happens in Phase 4)

  Scenario Outline: Eat twice
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Foraging
    And the species has a population of 2
    And the wateringhole has 1 food
    When the species eats
    Then the species eaten food should increase by 2
    Examples:
      | Num Players |
      | 3           |
      | 4           |
      | 5           |
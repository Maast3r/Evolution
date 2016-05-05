Feature: Cooperation
  During game play, when a given player feeds his species and his species owns the Cooperation trait,
  the species on the right should also feed. (Only happens in Phase 4)

  Scenario Outline: Feed right species
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Cooperation
    And the wateringhole has 1 food
    When the species eats
    Then the species on the right eaten food should increase by 1
    Examples:
      | Num Players |
      | 3           |
      | 4           |
      | 5           |
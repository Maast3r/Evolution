Feature: Climbing
  During game play, when a given player has the carnivore (and not climbing) trait on a species and another player
  has a species with climbing, the carnivore cannot see the climbing species.

  Scenario Outline: Climbing
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Carnivore
    And the carnivore has a body size of 6
    And there is another species that has Climbing
    Then the carnivore sees no available species to attack
    Examples:
      | Num Players | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |
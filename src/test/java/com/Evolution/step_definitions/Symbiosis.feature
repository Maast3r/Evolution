Feature: Symbiosis
  During game play, when a given player has the carnivore trait on a species and another player
  has a species with symbiosis, the carnivore cannot see the left species.

  Scenario Outline: Symbiosis
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Carnivore
    And all other species except the symbiosis left have a body size 6
    And there is another species that has Symbiosis
    Then the carnivore sees no available species to attack
    Examples:
      | Num Players | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |
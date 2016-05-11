Feature: Pack Hunting
  During game play, when a given player has the carnivore and Pack Hunting traits, it can attack all other species
  who don't have a body size > the carnivores pop + body size.

  Scenario Outline: Pack Hunting
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Carnivore
    And one species has a body size of 5
    And the next has a body size of 6
    And the carnivore has a body size of 3 and a population of 3
    Then the carnivore sees four available species to attack
    Examples:
      | Num Players | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |

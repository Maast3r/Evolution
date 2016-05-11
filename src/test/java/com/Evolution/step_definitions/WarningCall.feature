Feature: Warning Call
  During game play, when a given player has the carnivore (and not Warning Call) trait on a species and another player
  has a species with climbing, the carnivore cannot see the climbing species.

  Scenario Outline: Warning Call
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Carnivore
    And there is another species that has Warning Call
    And all other species except the one with Warning Call have a body size 6
    Then the carnivore sees no available species to attack
    Examples:
      | Num Players | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |


  Scenario Outline: Warning Call
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Carnivore
    And all other species except the second player have a body size 6
    And there is another species that has Warning Call
    Then the carnivore sees no available species to attack
    Examples:
      | Num Players | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |

  Scenario Outline: Ambush negates warning call
    Given I have a new Game with <Num Players> real players
    And it is currently Phase4 in real game
    And there is a species that has Carnivore and Ambush
    And all other species except the second player have a body size 6
    And there is another species that has Warning Call
    Then the carnivore sees 2 available species to attack
    Examples:
      | Num Players | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |

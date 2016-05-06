Feature: Attackable Species
  As a player, when I choose a species with which to attack others, I want to be provided a list of all species that
  my species can successfully attack.

  Scenario Outline: Attackable Species No Other Traits
    Given I have a new Game with <number> real players with real Species
    And Player <attacking_player_index> Species <attacking_species_index> is initially holding a Carnivore Card
    When Player <attacking_player_index> Species <attacking_species_index> views the attackable Species
    Then every other valid player_index, species_index will now be shown
    Examples:
      | number | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |


  Scenario Outline: Attackable Species No Other Traits Some Smaller
    Given I have a new Game with <number> real players with real Species
    And Player <attacking_player_index> Species <attacking_species_index> is initially holding a Carnivore Card
    And half of the species other than player <attacking_player_index>'s species <attacking_species_index> have a smaller body size
    When Player <attacking_player_index> Species <attacking_species_index> views the attackable Species
    Then every other valid player_index, species_index will now be shown if they have a smaller body size
    Examples:
      | number | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |
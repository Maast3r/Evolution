Feature: Attackable Species
  As a player, when I choose a species with which to attack others, I want to be provided a list of all species that
  my species can successfully attack. I also need to make sure my species is a carnivore

  Scenario Outline: Attackable Species No Other Traits
    Given I have a new Game with <number> real players with real Species
    When Player <attacking_player_index> Species <attacking_species_index> views the attackable Species
    Then you see no attackable species
    Examples:
      | number | attacking_player_index | attacking_species_index |
      | 3      | 0                      | 0                       |
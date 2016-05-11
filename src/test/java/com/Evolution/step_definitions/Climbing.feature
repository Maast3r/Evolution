Feature: Climbing
  As a player, when I choose a species with which to attack others, I want to be able to attack
  another species if we both have the trait 'climbing'. I also need to make sure
  my species is a carnivore.

  Scenario Outline: Climbing Both Species
    Given I have a new Game with <number> real players
    And player <attacking_player_index>'s species <attacking_species_index> is a carnivore
    And Player <attacking_player_index> Species <attacking_species_index> can climb
    And player <attacking_player_index>'s species <attacking_species_index> initially has a body size of <player_attacking_body_size>
    And player <player_under_attack>'s species <species_under_attack> can climb
    When Player <attacking_player_index>'s Species <attacking_species_index> views the attackable Species
    Then you see player <player_under_attack>'s species <species_under_attack>
    Examples:
      | number | attacking_player_index | attacking_species_index | player_under_attack | species_under_attack   | player_attacking_body_size |
      | 3      | 0                      | 0                       | 1                   | 0                      | 2                          |
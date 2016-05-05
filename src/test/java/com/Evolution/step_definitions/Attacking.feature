# Created by burchtm at 5/4/2016
Feature: Attacking
  During gameplay, a player's species can attack another species.

  Scenario Outline: AttackSuccessful
    Given I have a new Game with <Num Players> real players
    And player <player_attacking> has <num_player_1_species> species
    And player <player_under_attack> has <num_player_2_species> species
    And player <player_under_attack>'s species <species_under_attack> initially has a body size of <player_under_attack_body_size>
    And player <player_attacking>'s species <species_attacking> initially has a body size of <player_attacking_body_size>
    And player <player_attacking>'s species <species_attacking> initially has a population of <player_under_attack_body_size>
    And player <player_under_attack>'s species <species_under_attack> initially has a population of <initial_player_2_population>
    And player <player_attacking>'s species <species_attacking> is a carnivore
    When player <player_attacking>'s species <species_attacking> attacks player <player_under_attack>'s species <species_under_attack>
    Then player <player_attacking>'s species <species_attacking> now has a food count of <player_under_attack_body_size>
    And player <player_under_attack>'s species <species_under_attack> now has a population of <final_player_2_population>
    Examples:
      | Num Players | num_player_1_species | num_player_2_species | player_attacking | species_attacking | player_under_attack | species_under_attack | initial_player_2_population | final_player_2_population | player_under_attack_body_size | player_attacking_body_size |
      | 3           | 1                    | 1                    | 1                | 0                 | 2                   | 0                    | 3                           | 2                         | 4                             | 5                          |
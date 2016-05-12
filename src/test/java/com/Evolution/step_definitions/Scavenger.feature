# Created by brownba1 at 5/11/2016
Feature: Scavenger
  During gameplay, when a Carnivore Species attacks another species, all species currently holding Scavenger also eat a
  piece of food from the food bank

  Scenario Outline: Scavenger eat with Carnivore
    Given I have a new Game with <number> real players
    And player <a> has species <b> which is a carnivore
    And player <a>'s species <b> has an initial body size of <body_size>
    And player <c> species <d> is a scavenger
    When player <a> species <b> attacks player <e> species <f>
    Then player <c> species <d> eats from the food bank
    Examples:
      | number | a | b | c | d | e | f |body_size|
      | 3      | 0 | 0 | 1 | 0 | 2 | 0 |2        |
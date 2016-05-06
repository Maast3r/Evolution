# Created by goistjt at 5/5/2016
Feature: Phase4EndFix
  During gameplay, when phase 4 ends, the current turn needs to increment to the next player.

  Scenario: Phase4 Turn Increment
    Given I have a new Game with 3 players
    And it is currently Phase4
    And it is player three's turn
    When Phase4 ends
    Then it is the first player's turn

  Scenario: Phase4 Species Food to Food Bag
    Given I have a new Game with 3 real players with real Species
    And it is initially Phase4 with real players and species
    And it is initially player three's turn
    And each species initially has 1 population
    And each species initially has 1 food
    When phase4 finishes
    Then the food on each species moves to its player's food bag
    And each player now has 1 food in their food bag
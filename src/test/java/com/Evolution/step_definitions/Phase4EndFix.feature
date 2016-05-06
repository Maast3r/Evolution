# Created by goistjt at 5/5/2016
Feature: Phase4EndFix
  During gameplay, when phase 4 ends, the current turn needs to increment to the next player.

  Scenario: Phase4 Turn Increment
    Given I have a new Game with 3 players
    And it is currently Phase4
    And it is player three's turn
    When Phase4 ends
    Then it is the first player's turn
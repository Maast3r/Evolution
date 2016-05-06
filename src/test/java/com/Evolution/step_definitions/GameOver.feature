# Created by goistjt at 5/4/2016
Feature: Game Over
  As a player, when I draw the last card during phase 3, I want the Game to be signaled to end.
  As a player, when one of my species eats the last food, I want the Game to be signaled to end.


  Scenario: Last Card
    Given I have a new Game with 3 players with a real Draw Deck
    And the Draw Deck initally has one card
    When I draw the last card
    Then the Game is now signaled to end

  Scenario: Last Food
    Given I have a new Game with 3 players
    And the Watering Hole initially has one food
    And the Food Bank initially has zero food
    When one of my species eats the last food from the Watering Hole
    Then the Watering Hole now has zero food
    And the Game is now signaled to end
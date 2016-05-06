# Created by goistjt at 5/2/2016
Feature: Phase3CardFoodToWH
  As a player, when Phase 3 of the Game ends, I want all cards currently on the watering hole to have their food
  value added to the watering hole and the cards to be discarded to the discard pile.


  Scenario Outline: CardFoodToWH
    Given I have a new Game with <Num Players> real players
    And it is currently Phase3 in real game
    And I have an empty Watering Hole in real game
    And there are Cards on the Watering Hole in real game
    When Phase3 ends in real game
    Then the food on the Cards shall be added to the Watering Hole in real game
    And the Cards shall be discarded in real game
    Examples:
      | Num Players |
      | 3           |
      | 4           |
      | 5           |
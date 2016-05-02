# Created by goistjt at 5/2/2016
Feature: Phase3CardFoodToWH


  Scenario: CardFoodToWH
    Given I have a new Game with 4 players
    And it is currently Phase3
    And I have an empty Watering Hole
    And there are Cards on the Watering Hole
    When Phase3 ends
    Then the food on the Cards shall be added to the Watering Hole
    And the Cards shall be discarded
# Created by goistjt at 5/2/2016
Feature: Phase3CardFoodToWH


  Scenario Outline: CardFoodToWH
    Given I have a new Game with <Num Players> real players
    And it is currently Phase3 in real game
    And I have an empty Watering Hole in real game
    And there are Cards on the Watering Hole in real game
    When Phase3 ends in real game
    Then the food on the Cards shall be added to the Watering Hole in real game
    And the Cards shall be discarded in real game
    Examples:
      |Num Players  |
      |3            |
      |4            |
      |5            |
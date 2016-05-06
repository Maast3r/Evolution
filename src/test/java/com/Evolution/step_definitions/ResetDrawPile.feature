Feature: Foraging
  As a player, when I draw the last card during phase 3, I want the Game to be signaled to end
  and the discard pile shuffled into the draw pile.

  Scenario Outline: Eat twice
    Given I have a new Game with <Num Players> real players
    And it is currently real Phase2
    And the DrawPile has 1 card
    When I draw last card in real game
    Then the discard pile gets reshuffled into the draw pile
    Examples:
      | Num Players |
      | 3           |
      | 4           |
      | 5           |
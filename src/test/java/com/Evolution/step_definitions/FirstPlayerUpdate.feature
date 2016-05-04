# Created by goistjt at 5/3/2016
Feature: FirstPlayerUpdate
  During gameplay, when a new round begins, the first player should be equivalent to the round number modulo the number
  of players unless the modulo is zero, in which case it is the last player's turn.

  Scenario Outline: DetermineFirstPlayer
    Given I have a new Game with <Num Players> players
    And it is currently Round <round>
    And the First Player is currently Player <first_player_init>
    And it is currently Phase4
    When Phase4 ends
    Then the Round shall increment
    And the First Player shall be Player <first_player_result>
    Examples:
      | Num Players | round | first_player_init | first_player_result |
      | 3           | 1     | 1                 | 2                   |
      | 3           | 2     | 2                 | 3                   |
      | 4           | 1     | 1                 | 2                   |
      | 4           | 3     | 3                 | 4                   |
      | 5           | 1     | 1                 | 2                   |
      | 5           | 4     | 4                 | 5                   |
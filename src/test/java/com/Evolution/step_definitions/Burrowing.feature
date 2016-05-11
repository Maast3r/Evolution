# Created by brownba1 at 5/11/2016
Feature: Burrowing

  Scenario: Burrowing Body Size Increase
    Given I have a new Species
    And the Species is initially holding Burrowing
    When I get the Species body size
    Then the Species body size is at least 13

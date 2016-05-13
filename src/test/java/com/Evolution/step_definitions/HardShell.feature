# Created by brownba1 at 5/11/2016
Feature: HardShell
  During gameplay, when a species is holding Hard Shell, then its Body Size is increased by 4

  Scenario: Holding Hard Shell
    Given I have a new Species
    And the Species is initially holding Hard Shell
    When I get the Species body size
    Then the Species body size is at least 5
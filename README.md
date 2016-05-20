# Evolution
Brooke Brown, Trevor Burch, Jeremiah Goist, and Andrew Ma

This board game was developed for CSSE 376 (Software Quality Assurance) at Rose-Hulman Institute of Technology.

Each milestone, roles (2x Software Engineer, Software Engineer in Test, Test Engineer) were swapped between team
members.

The following resources were used to setup the project:
* [Gradle JaCoco Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
* [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html), especially the following chapters:
	* [Chapter 7 - Java Quick Start](https://docs.gradle.org/current/userguide/tutorial_java_projects.html)
	* [Chapter 8 - Dependency Management](https://docs.gradle.org/current/userguide/artifact_dependencies_tutorial.html)
	* [Chapter 14 - More about Tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html)

Please see the **build.gradle** and **.gitlab-ci.yml** for more detials.

## Implemented Features
Components
-	First player marker
-	Food bank
-	Watering Hole
-	Food token bags
-	Species boards
-	Trait card deck

Phase 0 Features - Setup
-	Table setup is done automatically with start screen

Phase 1 Features - Deal Cards
-	Deal cards to players

Phase 2 Features - Select Food
-	Choose Trait Card to become Food Card
-	Place card face down

Phase 3 Features - Play Cards
-	Rotate turns
-	Play trait cards face down
-	Discard trait cards from hand
-	Create new species
-	Increase body size
-	Increase population
-	Flip trait cards face-up

Phase 4 Features - Feeding
-	Flip cards to reveal food
-	Remove cards (discard)
-	Add plant food from food bank/watering hole
-	Remove plant food from food bank/watering hole
-	Rotate turns clockwise
-	Pass first player marker
-	Feed hungry species
-	Attack other species
-	Defend against attack
-	Go extinct
-	Remove species board
-	Add food to Food Bag
-	Decrease population

## Unimplemented Features
- Intelligence trait card
- Play with 6-8 players


## Known Issues
- Game window does not scale well

# Sprint 2
* Jeremiah Goist
    * IPlayer + Player/TestPlayer + PlayerTests
    * ISpecies + Species/TestSpecies + SpeciesTests
    * IWateringHole + WateringHole/TestWateringHole + WateringHoleTests
    * JavaDocs contained in relevant Interfaces/Classes
    * Testing above classes based on correct values/inputs
    * 100+ LOC
    * No GUI calls
* Brooke Brown
	* Everything in resources folder (images, fxml screen layouts)
	* StartScreenController + event listener
	* GameScreenController
	* JavaDocs for controllers
	* Testing correct screen setup based on selected number of players
	* 100+ LOC
	* No control logic
* Trevor Burch
    * ICard + Card/TestCard + CardTests
    * IDeck + Deck/TestDeck + DeckTests
    * JavaDocs contained in relevant Interfaces/Classes
    * Testing above classes based on correct values/inputs
    * 100+ LOC
    * No GUI calls
* Andrew Ma
    * IllegalNumberOfPlayers + Game + GameTests
    * InvalidPlayerSpeciesRemovalException + Player + PlayerTests
    * SpeciesBodySizeException + SpeciesPopulationException + Species + SpeciesTest
    * PlayerSpeciesTest
    * CardTests + Card
    * 100+ LOC
    * No GUI calls

# Sprint 3
* Jeremiah Goist
    * Game constructor refactoring
    * Game.foodBank and WateringHole interaction
    * DeckFactory w/ Andrew
    * Players discarding to discardPile, drawing from drawPile w/ Trevor
    * Performed code reviews and had others review my commits.
    * Began writing text file containing card information
    * 75+ LOC
* Brooke Brown
	* CardPopupController.java
	* Connecting GUI to Logic
	* Reformatted card images to be uniform
	* Exploratory Testing
	* Code inspections
	* Refactored GUI setup
	* Designed GUI to simplify actions
	    ~ Simplified for adding additional actions later
	* Helped refactor logic to fit GUI
	* 50+ LOC
* Trevor Burch
    * Cleaned up imports and comments
    * Dealing a card to a player
    * Retrieving all cards in a player's hand
    * Turn tracking
    * Discarding a card from a player's hand
    * Scanned in image files for cards
    * Performed code reviews and had others review my commits.
    * 75+ LOC
* Andrew Ma
    * Coding Practices.txt
    * Metrics.txt
    * Phase One and Phase One Tests
    * DeckFactory
    * DeckEmptyException
    * FoodBankEmptyException
    * InvalidPlayerSelectException
    * WateringHoleEmptyException
    * WrongFileException
    * CardTests/DeckFactoryTests/DeckTests/GameTests/WaterHoleTests
    * 100+ LOC
    * No GUI calls

# Sprint 4
* Jeremiah Goist
    * Discard from hand to Watering Hole
    * De/Activate dropdown for not/current player
    * Phase Label updates on phase switch
    * Dropdown shows only relevant options
    * Fixed GUI bug allowing free add of species
    * 25+ LOC
* Brooke Brown
	* Adding & removing cards from Watering Hole
	* Used mocking & BVA for tests
	* Performed code reviews
	* Added name & number to phases & unit tested them with mocking
	* 50+ LOC
	* No GUI calls
* Trevor Burch
    * Updated metric standards and added a new metric
    * Implemented the execution of PhaseTwo
    * Discarding a card to the wateringHole
    * Exception handling for discarding to the wateringHole
    * Verified coding practices were still being followed
    * 25+ LOC
* Andrew Ma
    * WateringHole and WateringHoleTests
    * BVA Testing
    * 50+ LOC
    * NO GUI calls

# Sprint 5
* Jeremiah Goist
    * Player discard for a Left or Right species
    * Game level add trait to species & remove from player
    * Parameterization of GameTests
    * Input validation testing
    * 50+ LOC
* Brooke Brown
    * Finished card input file
    * Increase body size & population
    * Added new exceptions
    * Refactored game/tests with missing input validation
    * Used paramaterized tests
    * Performed code reviews
    * 50+ LOC
    * No GUI calls
* Trevor Burch
    * Fixed issues found with unneeded methods in Species
    * Wrote Phase 3 Logic
    * Updated Phase 2 to automatically call nextTurn
    * Wrote logic for adding and removing traits from a species at the species level
    * Wrote logic for removing traits from a species at the game level
    * Fixed issue with branch coverage - Added IllegalCardRemovalException w/ Jeremiah
    * 50+ LOC
* Andrew Ma
    * Add/Remove trait card
    * End turn
    * Increase population/body size
    * Add species left/right
    * 50+ LOC

# Sprint 6
* Jeremiah Goist
    * Added Mutation Testing as part of gradle
    * Determined how to generate JaCoCo coverage reports
    * Began implementation of Trait actions
    * Refactored for input validation
    * Performed code reviews
    * Wrote Basic Phase 4 Logic
    * 50+ LOC
* Brooke Brown
    * Added eat to species
    * Added moving food from all species boards to player's food bag
    * Added trait card checks to feedPlayerSpecies in game
    * Added getting the amount of eaten food for a specific species to game (for GUI)
    * Wrote tests for branch coverage
    * Performed code reviews
    * 50+ LOC
* Trevor Burch
    * Added logic for handling phase 4 actions on the GUI Side
    * Debugged issue with cards not being removed from a player after adding a trait from the GUI
    * Aided backend developers in designing calls to game which are used by the GUI in Phase 4
    * Renamed methods in the GUI code to more accurately represent what they do
    * Wrote logic for Phase 4 to show the new traits on a species after they are set in Phase 3
    * 25+ LOC
* Andrew Ma
    * Fixed remove cards backend
    * Designed trait handling with Jeremiah
    * All traits and their interactions with game and species
    * Wrote tests for branch coverage
    * 50+ LOC

#Sprint 7
* Jeremiah Goist
    * Partially added logic to signal that the game should end at the end of the current round.
    * Added "first player" method/tracking so that GUI can move the token around and phase 3 will end on the correct
    player
    * Added basic logic to show attackable species given a player & species
    * Fixed Phase 3 to add food to WH and remove from bank
    * Worked with Trevor to configure cucumber for intelliJ
    * Fixed Phase 4 end logic and added auto moving species food to players at P4 end.
* Brooke Brown
    * Fixed bug with adding trait to species (index exception)
    * Added logic for skipping a player if full (update boxes & call phase.execute)
    * Add phase 4 choice box based on traits
    * Added attack popup screen
    * Fixed screen update issues (fpm not moving, boards not updating when attacked, etc.)
    * 25+ LOC
* Trevor Burch
    * Modified base logic of showing attackable species to look at body size
    * Added logic for attacking another person
    * Worked with Jeremiah to configure cucumber for intelliJ
    * Used BDD for attacking and attackable list changes
    * 50+ LOC
* Andrew Ma
    * Added integration tests for traits
    * Added Polymorphism Factor metric
    * Fixed java docs exceptions in game/phases
    * Experimented with evosuite, not a great tool
    * Used BDD everywhere
    * 50+ LOC

#Sprint 8
* Jeremiah Goist
    * Implemented screen to pop up when the game is determined to be over at the beginning of Phase 1
        * Game Over Screen can route to start game screen to play again
    * Created screen to show any user friendly messages from exceptions that may be thrown during gameplay
    * Added calls to open the exception screen from performAction (player initiated exceptions)
    * 50+ LOC
* Brooke Brown
    * Implemented climbing, burrowing, hard shell, and scavenger using bdd
    * Added new exception and throws documentation
    * Performed code reviews
    * Worked with Jeremiah to solve GUI bug
    * 50+ LOC
* Trevor Burch
    * Implemented interactions between Long Neck, Foraging, and Cooperation traits
    * Added functionality for Fat Tissue to move food from fat tissue to regular food
    * Implemented bottom-up testing for Long Neck
    * Implemented bottom-up testing for Fat Tissue
    * Went through other traits and made sure they were integration tested
    * Added new metric
    * 25+ LOC
* Andrew Ma
    * Implemented Warning Call, Ambush, Symbiosis, Pack Hunting using BDD
    * game getWinner
    * JavaFuzz
    * Attemped own fuzz testing
    * 50+ LOC

# Sprint 9
* Jeremiah Goist
    * Worked with Brooke on i18n
        * Translated existing strings and files to German
        * Changed usages of strings to instead use ResourceBundle
    * Located bugs in GUI/Logic
* Brooke Brown
    * Worked with Jeremiah on i18n
        * Translated existing strings and files to German
        * Initial setup of the ResourceBundle

# Rose Build Library 

At present the project is seeded with sample code, test cases for the sample code, gradle build file, gitlab-ci config file, and JaCoco coverage tool setup. The build process also retrieves the code coverage metrics and shows it in the build window of GitLab.

The following resources were used to setup the project:
* [Gradle JaCoco Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
* [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html), especially the following chapters:
	* [Chapter 7 - Java Quick Start](https://docs.gradle.org/current/userguide/tutorial_java_projects.html)
	* [Chapter 8 - Dependency Management](https://docs.gradle.org/current/userguide/artifact_dependencies_tutorial.html)
	* [Chapter 14 - More about Tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html)

Please see the **build.gradle** and **.gitlab-ci.yml** for more detials.

## Eclipse Setup Instruction

The project was setup using Eclipse (Mars 1 - Java Developer). It can be downloaded from the [On-Campus AFS Server](http://www.rose-hulman.edu/class/csse/binaries/Eclipse/mars/). You will also need to install Gradle plugin from [Eclipse Marketplace](https://marketplace.eclipse.org/content/gradle-integration-eclipse-0). After that, the project can be built within Eclipse IDE.

To build the project in Eclipse, right-click on the project -> Run As -> Gradle Build ... -> Under Gradle task box, enter **build** -> Apply -> Run. Your build should start. Note that you must **install JDK 8 (not JRE)** for all of these to work.

# Sprint 1
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

# Sprint 2
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

# Sprint 3
* Jeremiah Goist
    * 75+ LOC
* Brooke Brown
	* 50+ LOC
* Trevor Burch
    * 75+ LOC
* Andrew Ma
    * WateringHole and WateringHoleTests
    * 50+ LOC
    * No GUI calls
package com.Evolution;

import com.Evolution.exceptions.InvalidPlayerSpeciesRemovalException;
import com.Evolution.exceptions.NullGameObjectException;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Player and Species classes
 * Created by maas on 3/22/2016.
 */

public class PlayerSpeciesTest {
	
	@Test
	public void testAddOneSpeciesLeft() throws NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		test.addSpeciesLeft(s);
		assertEquals(2, test.getSpecies().size());
	}

	@Test
	public void testAddOneSpeciesRight() throws NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		test.addSpeciesLeft(s);
		assertEquals(2, test.getSpecies().size());
	}
	
	@Test
	public void testAddTwoSpeciesLeft() throws NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		test.addSpeciesLeft(s);
		test.addSpeciesLeft(s);
		assertEquals(3, test.getSpecies().size());
	}

	@Test
	public void testAddTwoSpeciesRight() throws NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		test.addSpeciesRight(s);
		test.addSpeciesRight(s);
		assertEquals(3, test.getSpecies().size());
	}
	
	@Test
	public void testAddManySpeciesLeft() throws NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		int testAmount = 1023;
		for(int i=0; i<testAmount; i++){
			test.addSpeciesLeft(s);
		}
		assertEquals(testAmount+1, test.getSpecies().size());
	}

	@Test
	public void testAddManySpeciesRight() throws NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		int testAmount = 1023;
		for(int i=0; i<testAmount; i++){
			test.addSpeciesRight(s);
		}
		assertEquals(testAmount+1, test.getSpecies().size());
	}
	
	@Test
	public void testRemoveOneSpecies() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		test.removeSpecies(0);
		assertEquals(0, test.getSpecies().size());
	}
	
	@Test
	public void testRemoveTwoSpecies() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		test.addSpeciesLeft(s);
		test.addSpeciesLeft(s);
		test.removeSpecies(0);
		test.removeSpecies(0);
		assertEquals(1, test.getSpecies().size());
	}
	
	@Test
	public void testRemoveManySpecies() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
		Player test = new Player(new Species());
		Species s = new Species();
		int testAmount = 1023;
		int removeAmount = 59;
		for(int i=0; i<testAmount; i++){
			test.addSpeciesLeft(s);
		}
		for(int j=0;j<removeAmount; j++){
			test.removeSpecies(0);
		}
		assertEquals(testAmount-removeAmount+1, test.getSpecies().size());
	}
}

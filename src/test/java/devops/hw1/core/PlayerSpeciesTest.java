package devops.hw1.core;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the Player and Species classes
 * Created by maas on 3/22/2016.
 */

public class PlayerSpeciesTest {
	
	@Test
	public void testNoSpecies(){
		Player test = new Player();
		assertEquals(0, test.getSpecies().size());
	}
	
	@Test
	public void testAddOneSpecies(){
		Player test = new Player();
		Species s = new Species();
		test.addSpecies(s);
		assertEquals(1, test.getSpecies().size());
	}
	
	@Test
	public void testAddTwoSpecies(){
		Player test = new Player();
		Species s = new Species();
		test.addSpecies(s);
		test.addSpecies(s);
		assertEquals(2, test.getSpecies().size());
	}
	
	@Test
	public void testAddManySpecies(){
		Player test = new Player();
		Species s = new Species();
		int testAmount = 1023;
		for(int i=0; i<testAmount; i++){
			test.addSpecies(s);
		}
		assertEquals(testAmount, test.getSpecies().size());
	}
	
	@Test
	public void testRemoveOneSpecies(){
		Player test = new Player();
		Species s = new Species();
		test.addSpecies(s);
		test.removeSpecies(0);
		assertEquals(0, test.getSpecies().size());
	}
	
	@Test
	public void testRemoveTwoSpecies(){
		Player test = new Player();
		Species s = new Species();
		test.addSpecies(s);
		test.addSpecies(s);
		test.addSpecies(s);
		test.removeSpecies(0);
		test.removeSpecies(0);
		assertEquals(1, test.getSpecies().size());
	}
	
	@Test
	public void testRemoveManySpecies(){
		Player test = new Player();
		Species s = new Species();
		int testAmount = 1023;
		int removeAmount = 59;
		for(int i=0; i<testAmount; i++){
			test.addSpecies(s);
		}
		for(int j=0;j<removeAmount; j++){
			test.removeSpecies(0);
		}
		assertEquals(testAmount-removeAmount, test.getSpecies().size());
	}
}

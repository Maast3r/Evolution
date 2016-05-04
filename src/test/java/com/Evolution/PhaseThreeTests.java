package com.Evolution;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Logic for the third phase of the game
 */
public class PhaseThreeTests {

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            try {
                players.add(new Player(new Species()));
            } catch (NullGameObjectException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    @Test
    public void testNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException, InvalidWateringHoleCardCountException, FoodBankEmptyException {
        IDeck<ICard> drawPile = new Deck<>();
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        for (int i = 0; i < 3; i++) {
            try{
                wh.addCard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Game g = new Game(generateNumPlayers(3), wh, drawPile, discardPile);
        PhaseThree p = new PhaseThree(g);
        g.setPhase(p);
        for(int i = 0; i < 3; i++){
            g.getPhase().execute();
        }
        assertEquals(PhaseFour.class, g.getPhase().getClass());
    }

    @Test
    public void testNoNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException, InvalidWateringHoleCardCountException, FoodBankEmptyException {
        IDeck<ICard> drawPile = new Deck<>();
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        for (int i = 0; i < 3; i++) {
            try{
                wh.addCard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Game g = new Game(generateNumPlayers(3), wh, drawPile, discardPile);
        PhaseThree p = new PhaseThree(g);
        g.setPhase(p);
        for(int i = 0; i < 2; i++){
            g.getPhase().execute();
        }
        assertEquals(PhaseThree.class, g.getPhase().getClass());
    }

    @Test
    public void testGetPhaseName() {
        Game g = EasyMock.niceMock(Game.class);
        PhaseThree p = new PhaseThree(g);
        assertEquals(p.getName(), "Play Cards");
    }

    @Test
    public void testGetPhaseNumber() {
        Game g = EasyMock.niceMock(Game.class);
        PhaseThree p = new PhaseThree(g);
        assertEquals(p.getNumber(), 3);
    }
}

package com.Evolution;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class PhaseTwoTests {

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(new Player(new Species()));
        }
        return players;
    }


    @Test
    public void testNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException {
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
        PhaseTwo p = new PhaseTwo(g);
        g.setPhase(p);
        p.execute();
        assertEquals(PhaseThree.class, g.getPhase().getClass());
    }

    @Test
    public void textNotNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException {
        IDeck<ICard> drawPile = new Deck<>();
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        for (int i = 0; i < 2; i++) {
            try{
                wh.addCard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Game g = new Game(generateNumPlayers(3), wh, drawPile, discardPile);
        PhaseTwo p = new PhaseTwo(g);
        g.setPhase(p);
        p.execute();
        assertEquals(PhaseTwo.class, g.getPhase().getClass());
    }

    @Test
    public void testGetPhaseName() {
        Game g = EasyMock.niceMock(Game.class);
        PhaseTwo p = new PhaseTwo(g);
        assertEquals(p.getName(), "Discard to Watering Hole");
    }
}

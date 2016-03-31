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
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class PhaseOneTests {

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(new Player(new Species()));
        }
        return players;
    }

    @Test
    public void testInit() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        IDeck<ICard> drawPile = new Deck<>();
        IDeck<ICard> discardPile = new Deck<>();

        IWateringHole wh = new WateringHole();
        Game g = new Game(generateNumPlayers(3), wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        assertEquals(3, g.getPlayerObjects().size());
        assertEquals(0, g.getDrawPile().getSize());
        assertEquals(0, g.getDiscardPile().getSize());
    }

    @Test
    public void testNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException {
        IDeck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 12; i++) {
            drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        }
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        Game g = new Game(generateNumPlayers(3), wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        p.execute();
        assertEquals(PhaseTwo.class, g.getPhase().getClass());
    }

    @Test
    public void testExecute1() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException {
        IDeck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 12; i++) {
            drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        }
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        Game g = new Game(generateNumPlayers(3), wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        p.execute();
        assertEquals(4, g.getPlayerObjects().get(0).getCards().size());
        assertEquals(0, g.getDrawPile().getSize());
        assertEquals(1, g.getPlayerObjects().get(0).getSpecies().size());
    }

    @Test
    public void testExecute2() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException {
        ArrayList<IPlayer> players = generateNumPlayers(3);
        players.get(0).addSpeciesLeft(new Species());
        IDeck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 13; i++) {
            drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        }
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        Game g = new Game(players, wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        p.execute();
        assertEquals(5, g.getPlayerObjects().get(0).getCards().size());
        assertEquals(0, g.getDrawPile().getSize());
        assertEquals(2, g.getPlayerObjects().get(0).getSpecies().size());
    }
}

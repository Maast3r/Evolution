package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.interfaces.*;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;


public class PhaseOneTests {
    @Test
    public void testInit() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<IPlayer>();
        players.add(new Player(new Species()));
        IDeck<ICard> drawPile = new Deck<>();
        IDeck<ICard> discardPile = new Deck<>();

        IWateringHole wh = new WateringHole();
        Game g = new Game(players, wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        assertEquals(1, g.getPlayerObjects().size());
        assertEquals(0, g.getDrawPile().getSize());
        assertEquals(0, g.getDiscardPile().getSize());
    }

    @Test
    public void testInitRandom() throws IllegalCardDirectionException, IllegalNumberOfPlayers {
        ArrayList<IPlayer> players = new ArrayList<IPlayer>();
        players.add(new Player(new Species()));
        IDeck<ICard> drawPile = new Deck<>();
        Random rn = new Random();
        int random = rn.nextInt(1000 - 0 + 1) + 0;
        IDeck<ICard> discardPile = new Deck<>();
        for(int i=0; i<random; i++){
            players.add(new Player(new Species()));
            drawPile.discard(new Card("Carnivore", "Makes a species a carnivore",
                    "./carnivore.jpg", 3, 0));
            discardPile.discard(new Card("Carnivore", "Makes a species a carnivore",
                    "./carnivore.jpg", 3, 0));
        }


        IWateringHole wh = new WateringHole();
        Game g = new Game(players, wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        assertEquals(random, g.getPlayerObjects().size());
        assertEquals(random, g.getDrawPile().getSize());
        assertEquals(random, g.getDiscardPile().getSize());
    }

    @Test
    public void testNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers {
        IPhases fakePhaseTwo = EasyMock.niceMock(PhaseTwo.class);
        fakePhaseTwo.execute();
        EasyMock.replay(fakePhaseTwo);

        ArrayList<IPlayer> players = new ArrayList<IPlayer>();
        players.add(new Player(new Species()));
        IDeck<ICard> drawPile = new Deck<>();
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        Game g = new Game(players, wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        p.execute();

        EasyMock.verify(fakePhaseTwo);
    }

    @Test
    public void testExecute1() throws IllegalCardDirectionException, IllegalNumberOfPlayers {
        ArrayList<IPlayer> players = new ArrayList<IPlayer>();
        players.add(new Player(new Species()));
        IDeck<ICard> drawPile = new Deck<>();
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        Game g = new Game(players, wh, drawPile, discardPile);
        PhaseOne p = new PhaseOne(g);
        p.execute();
        assertEquals(4, g.getPlayerObjects().get(0).getCards().size());
        assertEquals(0, g.getDrawPile().getSize());
        assertEquals(1, g.getPlayerObjects().get(0).getSpecies().size());
    }

    @Test
    public void testExecute2() throws IllegalCardDirectionException, IllegalNumberOfPlayers {
        ArrayList<IPlayer> players = new ArrayList<IPlayer>();
        players.add(new Player(new Species()));
        players.get(0).addSpeciesLeft(new Species());
        IDeck<ICard> drawPile = new Deck<>();
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        drawPile.discard(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
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

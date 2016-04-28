package com.Evolution;

import com.Evolution.interfaces.ITrait;
import com.Evolution.logic.Game;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 4/27/2016.
 */
public class LongNeckTests {

    @Test
    public void getGame() {
        Game g = EasyMock.niceMock(Game.class);
        ITrait t = new LongNeck(g);
        assertEquals(g, t.getGame());
    }
}

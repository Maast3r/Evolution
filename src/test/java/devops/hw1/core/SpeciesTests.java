package devops.hw1.core;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by goistjt on 3/21/2016.
 */
public class SpeciesTests {

    @Test
    public void testBodySize() {
        Species s = new Species();
        assertEquals(1, s.getSize());
    }
}

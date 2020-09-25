/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import othello.bots.OthelloHuman;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class OthelloHumanTest {

    private final OthelloHuman othelloHuman;

    public OthelloHumanTest() {
        this.othelloHuman = new OthelloHuman();
    }

    @Test
    public void inputStringChecker() {
        assert (!othelloHuman.isInputFormatValid(":D"));
        assert (!othelloHuman.isInputFormatValid("dd22"));
        assert (!othelloHuman.isInputFormatValid("p9"));
        assert (othelloHuman.isInputFormatValid("h1"));
    }

    @Test
    public void stringToCoordinateConversion() {
        assertEquals(2, othelloHuman.parseInputToCoordinates("c1")[0]);
        assertEquals(6, othelloHuman.parseInputToCoordinates("a7")[1]);
    }
}

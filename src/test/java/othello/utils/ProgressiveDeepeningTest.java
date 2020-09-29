/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
public class ProgressiveDeepeningTest {
    
    public ProgressiveDeepeningTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /*
    TESTAA:
    - palauttaa ekan löytyvän avauksist; avaukset equal
    - tokassa siirrossa palauttaa ny todetun parhaan : 22
    */
    
    @Test
    public void returnsTopLeftMostOpeningMove() {
        // opening moves are equal due to symmetry
    }
    
    @Test
    public void decidesRightOpeningMoveAsSecondPlayer() {
        // this move was found to be best with the tested algorithm;
        // test confirms decisions will not change.
    }
}

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
public class EvaluatorsTest {

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void countOfStableEdgeDiscsIsCorrect() {
        int[][] board = new int[][]{
            {2, 2, 2, 0, 0, 0, 2, 2},
            {2, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 2},
            {1, 0, 0, 0, 0, 0, 0, 2},
            {1, 1, 1, 1, 0, 0, 2, 2}};
        assertEquals(18, Evaluators.countEdgeStability(board, new boolean[8][8], 2));
        assertEquals(5, Evaluators.countEdgeStability(board, new boolean[8][8], 1));

    }

}

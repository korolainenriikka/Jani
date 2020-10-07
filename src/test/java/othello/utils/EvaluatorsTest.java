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
import static othello.api.Tile.WHITE;

/**
 *
 * @author riikoro
 */
public class EvaluatorsTest {
    
    @Test
    public void weighedScorerCalculatesRight() {
        
    }

    @Test
    public void midgameEvaluatorCalculatesRight() {
        
    }
    
    @Test
    public void endgameEvaluatorsCalculatesRight() {
        int[][] board = new int[][]{
            {2, 2, 2, 0, 0, 0, 2, 2},
            {2, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 1, 2, 1, 2, 1, 2},
            {2, 0, 2, 1, 2, 1, 2, 2},
            {2, 0, 0, 1, 2, 2, 2, 2},
            {2, 0, 1, 1, 2, 2, 2, 2},
            {1, 1, 0, 1, 2, 0, 0, 2},
            {1, 1, 1, 1, 2, 0, 2, 2}};
        assertEquals(-193, Evaluators.endgameEvaluator(board, WHITE));
    }

}

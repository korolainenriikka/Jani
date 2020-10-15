/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import static othello.api.Tile.WHITE;

/**
 *
 * @author riikoro
 */
public class EvaluatorsTest {

    @Test
    public void weighedScorerComputedRight() {
        int[][] board = new int[][]{
            {1, 0, 0, 0, 0, 0, 0, 1},
            {0, 2, 0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 2, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 2, 0},
            {1, 0, 0, 0, 0, 0, 0, 1}
        };

        assertEquals(492, Evaluators.stateEvaluator(board));
    }

    @Test
    public void mobilityScoresComputedRight() {
        // mobility: 5
        // frontier: 12
        // (5-12)*10 *-1 = 90
        int[][] board = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 2, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 2, 1, 1, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},};
        assertEquals(70, Evaluators.openingEvaluator(board, WHITE));
    }

    @Test
    public void endgameEvaluatorsComputedRight() {
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

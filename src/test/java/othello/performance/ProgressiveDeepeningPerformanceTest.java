/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.performance;

import org.junit.Test;
import static othello.api.Tile.BLACK;
import static othello.utils.GamePhase.*;
import othello.utils.ProgressiveDeepening;

/**
 * Tests for measuring depths reached with progressive deepening with various
 * evaluators; for testing costs of evaluation computation. No assertions.
 * 
 * @author riikoro
 */
public class ProgressiveDeepeningPerformanceTest {
    
    /*@Test
    public void tileScoringOpeningDepth() {
        int[][] openingBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 0},
            {0, 0, 0, 1, 2, 0, 0, 0},
            {0, 0, 1, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        ProgressiveDeepening.findBestMove(openingBoard, BLACK, true, OPENING);
    }

    @Test
    public void tileScoringMidgameDepth() {
        int[][] midgameBoard = {
            {0, 2, 1, 1, 1, 1, 1, 0},
            {2, 2, 1, 2, 2, 2, 2, 0},
            {0, 2, 1, 1, 1, 2, 2, 0},
            {0, 0, 2, 1, 2, 2, 2, 0},
            {0, 0, 1, 2, 2, 0, 2, 0},
            {0, 0, 0, 2, 2, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        ProgressiveDeepening.findBestMove(midgameBoard, BLACK, true, MIDGAME);
    }

    @Test
    public void tileScoringEndgameDepth() {
        int[][] endgameBoard = {
            {0, 0, 1, 1, 1, 1, 0, 0},
            {1, 2, 2, 2, 2, 2, 2, 0},
            {1, 1, 2, 1, 1, 2, 2, 1},
            {1, 2, 1, 2, 2, 1, 2, 1},
            {1, 2, 1, 2, 2, 1, 2, 1},
            {1, 1, 2, 1, 2, 2, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 0},
            {0, 1, 0, 2, 2, 2, 2, 0}
        };
        ProgressiveDeepening.findBestMove(endgameBoard, BLACK, true, ENDGAME);
    }*/
}

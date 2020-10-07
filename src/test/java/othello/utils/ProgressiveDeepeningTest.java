/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import org.junit.*;
import static othello.api.Tile.*;
import static othello.utils.GamePhase.*;

/**
 *
 * @author riikoro
 */
public class ProgressiveDeepeningTest {

    private int[][] board;

    @Before
    public void setUp() {
        //init default othello start state
        this.board = new int[8][8];
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }

    @Test
    public void returnsValidOpeningMove() {
        // opening moves are equal due to symmetry
        int[] move = ProgressiveDeepening.findBestMove(board, BLACK, false, OPENING);
        assert ((move[0] == 2 && move[1] == 3)
                || (move[0] == 3 && move[1] == 2)
                || (move[0] == 4 && move[1] == 5)
                || (move[0] == 5 && move[1] == 4));

    }

    @Test
    public void playsWinningMove() {
        int[][] gameState = new int[][]{
            {1, 1, 2, 2, 2, 2, 0, 1},
            {0, 1, 2, 2, 2, 1, 1, 1},
            {2, 2, 1, 2, 1, 1, 1, 1},
            {2, 1, 2, 1, 2, 1, 2, 1},
            {2, 1, 2, 2, 1, 2, 1, 1},
            {2, 2, 2, 2, 2, 1, 2, 1},
            {2, 2, 2, 2, 2, 2, 1, 1},
            {2, 1, 1, 1, 1, 1, 1, 1}
        };
        int[] move = ProgressiveDeepening.findBestMove(gameState, WHITE, false, ENDGAME);
        assert (move[0] == 0 && move[1] == 6);
    }

    @Test
    public void usesAllAvailableTimeForAllMoveDecisions() {
        long start = System.nanoTime();
        ProgressiveDeepening.findBestMove(board, BLACK, false, OPENING);
        assert (((System.nanoTime() - start) / 1e9) > 0.9);
    }
}

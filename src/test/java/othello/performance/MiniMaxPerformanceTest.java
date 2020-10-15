/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.performance;

import org.junit.Before;
import othello.bots.*;
import static othello.api.Tile.BLACK;

import org.junit.Test;

/**
 * For testing minimax run times with various depths / bots. Will try with
 * incrementing depth until time limit of 1.0 sec is exceeded. No assertions.
 *
 * @author riikoro
 */
public class MiniMaxPerformanceTest {

    private JaniTileScorer bot;
    private JaniAlphaTileScorer abBot;

    @Before
    public void setUp() {
        this.bot = new JaniTileScorer();
        this.abBot = new JaniAlphaTileScorer();

        bot.startGame(BLACK);
        abBot.startGame(BLACK);
    }

    public void testPerformance(String gamePhase, int[][] board) {
        int depth = 1;

        while (true) {
            long start = System.nanoTime();
            bot.makeMoveCustomDepth(board, depth);
            long end = System.nanoTime();
            if (((end - start) / 1e9) > 1.0 || depth == 31) {
                break;
            }
            depth++;
        }

        depth--;
        if (depth == 30) {
            System.out.println(gamePhase + ": minimax computed the full game tree");
        } else {
            System.out.println(gamePhase + ": minimax computed to depth " + depth);
        }

        depth = 1;
        while (true) {
            long start = System.nanoTime();
            abBot.makeMoveCustomDepth(board, depth);
            long end = System.nanoTime();

            if (((end - start) / 1e9) > 1.0 || depth == 31) {
                break;
            }
            depth++;
        }

        depth--;
        if (depth == 30) {
            System.out.println(gamePhase + ": ab-pruning minimax computed the full game tree");
        } else {
            System.out.println(gamePhase + ": ab-pruning minimax computed to depth " + depth);
        }
    }

    @Test
    public void openingDepth() {
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
        testPerformance("Opening", openingBoard);
    }

    @Test
    public void midgameDepth() {
        int[][] midGameBoard = {
            {0, 2, 1, 1, 1, 1, 1, 0},
            {2, 2, 1, 2, 2, 2, 2, 0},
            {0, 2, 1, 1, 1, 2, 2, 0},
            {0, 0, 2, 1, 2, 2, 2, 0},
            {0, 0, 1, 2, 2, 0, 2, 0},
            {0, 0, 0, 2, 2, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        testPerformance("Midgame", midGameBoard);
    }

    @Test
    public void endgameDepth() {
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
        testPerformance("Endgame", endgameBoard);
    }
}

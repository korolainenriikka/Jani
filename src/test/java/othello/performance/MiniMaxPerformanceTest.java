/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.performance;

import othello.bots.JaniTileScorer;
import static othello.api.Tile.BLACK;

import org.junit.Test;

/**
 * For testing minimax run times with various depths. Will try with incrementing
 * depth until time limit of 1.0 sec is exceeded. No assertions.
 *
 * @author riikoro
 */
public class MiniMaxPerformanceTest {

    private JaniTileScorer bot;

    public MiniMaxPerformanceTest() {
        this.bot = new JaniTileScorer();
        bot.startGame(BLACK);
    }

    //tests for measuring performance times of minimax
    //time limit 1.0 seconds for all tests
    @Test
    public void decidesOpeningMoveInTime() {
        int i = 1;
        while (true) {
            long start = System.nanoTime();

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

            bot.makeMoveCustomDepth(openingBoard, i);
            long end = System.nanoTime();
            System.out.println("Opening move computation time with minimax depth " + i + ": " + ((end - start) / 1e9) + " seconds");

            if (((end - start) / 1e9) > 1.0) {
                break;
            }
            i++;
        }
    }

    @Test
    public void decidesMoveMidGameInTime() {
        int i = 1;
        while (true) {
            long start = System.nanoTime();

            int[][] board = {
                {0, 2, 1, 1, 1, 1, 1, 0},
                {2, 2, 1, 2, 2, 2, 2, 0},
                {0, 2, 1, 1, 1, 2, 2, 0},
                {0, 0, 2, 1, 2, 2, 2, 0},
                {0, 0, 1, 2, 2, 0, 2, 0},
                {0, 0, 0, 2, 2, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
            };

            bot.makeMoveCustomDepth(board, i);
            long end = System.nanoTime();
            System.out.println("Mid-game move computation time with minimax depth " + i + ": " + ((end - start) / 1e9) + " seconds");

            if (((end - start) / 1e9) > 1.0) {
                break;
            }
            i++;
        }
    }

    @Test
    public void decidesMoveInTimeCloseToEndOfGame() {
        int i = 1;
        while (true) {
            long start = System.nanoTime();

            int[][] board = {
                {0, 0, 1, 1, 1, 1, 0, 0},
                {1, 2, 2, 2, 2, 2, 2, 0},
                {1, 1, 2, 1, 1, 2, 2, 1},
                {1, 2, 1, 2, 2, 1, 2, 1},
                {1, 2, 1, 2, 2, 1, 2, 1},
                {1, 1, 2, 1, 2, 2, 2, 1},
                {1, 2, 2, 2, 2, 2, 2, 0},
                {0, 1, 0, 2, 2, 2, 2, 0}
            };

            bot.makeMoveCustomDepth(board, i);
            long end = System.nanoTime();
            System.out.println("End of game move computation time with minimax depth " + i + ": " + ((end - start) / 1e9) + " seconds");

            if (((end - start) / 1e9) > 1.0 || i == 12) {
                break;
            }
            i++;
        }
    }
}

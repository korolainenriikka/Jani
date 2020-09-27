package othello.utils;

import static othello.api.Tile.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Evaluation functions and board processing methods for evaluation.
 *
 * @author riikoro
 */
public class Evaluators {

    /**
     * Scoring factors for various tiles on board, from
     * http://samsoft.org.uk/reversi/strategy.htm#position
     */
    static final int[][] BOARD_SCORING = {
        {99, -8, 8, 6, 6, 8, -8, 99},
        {-8, -24, -4, -3, -3, -4, -24, -8},
        {8, -4, 7, 4, 4, 7, -4, 8},
        {6, -3, 4, 0, 0, 4, -3, 6},
        {6, -3, 4, 0, 0, 4, -3, 6},
        {8, -4, 7, 4, 4, 7, -4, 8},
        {-8, -24, -4, -3, -3, -4, -24, -8},
        {99, -8, 8, 6, 6, 8, -8, 99}};

    static final int[][] CORNERS = {{0, 0}, {0, 7}, {7, 0}, {7, 7}};
    static final int[][] X_CORNERS = {{1, 1}, {1, 6}, {6, 1}, {6, 6}};

    static final int SIZE = 8;

    /**
     * Win-determining evaluating function returns 1, 0 or -1 and only evaluates
     * if game is over. 1 = black won, 0 = even, -1 = white won
     *
     * @param board state of the game
     * @return integer representing the winner of the game
     */
    public static int winEvaluator(final int[][] board) {
        return BoardUtils.winner(board);
    }

    /**
     * Weighed tile count maximizer.
     *
     * @param board state of the game
     * @return int value between -1000 and 1000 evaluating the desirability.
     */
    public static int stateEvaluator(int[][] board) {
        int score = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == BLACK) {
                    score += BOARD_SCORING[i][j];
                } else if (board[i][j] == WHITE) {
                    score -= BOARD_SCORING[i][j];
                }
            }
        }

        return score;
    }

    /**
     * End-game evaluation: edge stability + count of discs
     *
     * @param board current state of game
     * @return
     */
    public static int endGameEvaluator(int[][] board, int player) {
        boolean[][] isTileStable = new boolean[8][8];
        int edgeStability = countEdgeStability(board, isTileStable, player);
        int countOfNonStable = countNonStableDiscs(board, isTileStable, player);
        return 10 * edgeStability + countOfNonStable;
    }

    /**
     * Counts the amount of stable edge discs a player has in a given game
     * state.
     *
     * @param board state of the game
     * @param tileIsStable array for marking stable discs
     * @param player player whose count is computed
     * @return count of stable edge discs
     */
    public static int countEdgeStability(int[][] board, boolean[][] tileIsStable, int player) {
        int edgeStability = 0;

        for (int[] corner : CORNERS) {
            if (board[corner[0]][corner[1]] == player) {
                tileIsStable[corner[0]][corner[1]] = true;
                edgeStability++;

                for (int i = 1; i < SIZE - 1; i++) {
                    int increment = corner[1] == 0 ? 1 : -1;
                    if (board[corner[0]][corner[1] + (i * increment)] == player
                            && !tileIsStable[corner[0]][corner[1] + (i * increment)]) {
                        tileIsStable[corner[0]][corner[1] + (i * increment)] = true;
                        edgeStability++;
                    } else {
                        break;
                    }
                }

                for (int i = 1; i < SIZE - 1; i++) {
                    int increment = corner[0] == 0 ? 1 : -1;
                    if (board[corner[0] + (i * increment)][corner[1]] == player
                            && !tileIsStable[corner[0] + (i * increment)][corner[1]]) {
                        tileIsStable[corner[0] + (i * increment)][corner[1]] = true;
                        edgeStability++;
                    } else {
                        break;
                    }
                }
            }
        }
        return edgeStability;
    }

    /**
     * Counts the amount of non-stable discs on board.
     *
     * @param board state of the game
     * @param tileIsStable array to check which discs are counted as stable
     * @param player player whose discs are counted
     * @return count of players discs
     */
    public static int countNonStableDiscs(int[][] board, boolean[][] tileIsStable, int player) {
        int discCount = 0;
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tileIsStable[i][j] && board[i][j] == player) {
                    discCount++;
                }
            }
        }
        return discCount;
    }
}

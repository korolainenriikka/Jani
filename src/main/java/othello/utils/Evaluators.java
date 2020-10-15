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
    static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
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
     * Opening evaluation: own mobility maximizing and frontier minimizing.
     * 
     * @param board
     * @param player
     * @return 
     */
    public static int openingEvaluator(int[][] board, int player) {
        int score = mobilityScore(board, player);
        return player == BLACK ? score : -1 * score;
    }

    /**
     * Evaluation function for midgame: weighed linear combination of mobility
     * and position scoring.
     *
     * @param board current state of the game
     * @param player player playing currently
     * @return int value representing desirability of game state
     */
    public static int midgameEvaluator(int[][] board, int player) {
        //mid-game evaluation: mobility-maximizing (count of moves available)
        //minimize own frontier maximize moves one has
        int score = mobilityScore(board, player) + (stateEvaluator(board) / 10);
        return player == BLACK ? score : -1 * score;
    }

    private static int mobilityScore(int[][] board, int player) {
        int ownFrontier = 0;
        int mobility = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    mobility++;
                }
                for (int[] d : DIRECTIONS) {
                    int adjacentRow = i + d[0];
                    int adjacentCol = j + d[1];

                    if (BoardUtils.withinBoard(adjacentRow, adjacentCol)
                            && board[i][j] == 0
                            && board[adjacentRow][adjacentCol] == player) {
                        ownFrontier++;
                        break;
                    }
                }

            }
        }

        return (mobility - ownFrontier) * 10;
    }

    /**
     * End-game evaluation: edge stability + count of discs.
     *
     * @param board current state of game
     * @param player player whose turn it is
     * @return evaluation score
     */
    public static int endgameEvaluator(int[][] board, int player) {
        boolean[][] isTileStable = new boolean[8][8];
        int edgeStability = countEdgeStability(board, isTileStable, player);
        int countOfNonStable = countNonStableDiscs(board, isTileStable, player);
        int score = 10 * edgeStability + countOfNonStable;
        if (player == BLACK) {
            return score;
        } else {
            return -1 * score;
        }
    }

    private static int countEdgeStability(int[][] board, boolean[][] tileIsStable, int player) {
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

    private static int countNonStableDiscs(int[][] board, boolean[][] tileIsStable, int player) {
        int discCount = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!tileIsStable[i][j] && board[i][j] == player) {
                    discCount++;
                }
            }
        }

        return discCount;
    }

}

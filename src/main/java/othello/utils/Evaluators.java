package othello.utils;

import static othello.api.Tile.BLACK;
import static othello.api.Tile.WHITE;
import static othello.utils.Evaluators.CORNERS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Evaluation functions and board processing methods for evaluation
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
     * Evaluates the desirability of current board state. High value = desirable
     *
     * First edition:
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
     * Evaluation function based on othello-bot Iago's evaluation
     *
     *
     * @param board current state of game
     * @return
     */
    public static int iagoEvaluator(int[][] board, int player) {
        // param player for counting whose score counted, if minimizer multiply by -1!!!!
        int[] stability = countStability(board, player);
        //int[] mobility = countMobility(board, player);
        return 1;
    }

    /*
    TARVITAAN
    -ESAC
    -CMAC
    -edgestability -> stable jos jompaa kumpaa suuntaa omii kulmaan asti
    -internalstability
    conditions: on the line one side others disc one side empty
    a disc is provably stable if there are no lines through the square along which both of these conditions can ever hold
    
    
    -currentmobility
    -potentialmobility
     */
    public static int[] countStability(int[][] board, int player) {
        boolean[][] isTileStable = new boolean[8][8];
        int edgeStability = countEdgeStability(board, isTileStable, player);

        //no disc can be internally stable if edge stability less than 4
        if (edgeStability < 4) {
            return new int[]{edgeStability, 0};
        }

        int internalStability = countInternalStability(board, isTileStable, player);
        return new int[]{edgeStability, internalStability};
    }

    /**
     * Counts the amount of stable edge discs a player has in a given game
     * state.
     *
     * @param board state of the game
     * @param isTileStable array for marking stable discs
     * @param player player whose count is computed
     * @return count of stable edge discs
     */
    public static int countEdgeStability(int[][] board, boolean[][] isTileStable, int player) {
        int edgeStability = 0;
        for (int[] corner : CORNERS) {
            if (board[corner[0]][corner[1]] == player) {
                isTileStable[corner[0]][corner[1]] = true;
                edgeStability++;

                for (int i = 1; i < 7; i++) {
                    int increment = corner[1] == 0 ? 1 : -1;
                    if (board[corner[0]][corner[1] + (i * increment)] == player
                            && !isTileStable[corner[0]][corner[1] + (i * increment)]) {
                        isTileStable[corner[0]][corner[1] + (i * increment)] = true;
                        edgeStability++;
                    } else {
                        break;
                    }
                }

                for (int i = 1; i < 7; i++) {
                    int increment = corner[0] == 0 ? 1 : -1;
                    if (board[corner[0] + (i * increment)][corner[1]] == player
                            && !isTileStable[corner[0] + (i * increment)][corner[1]]) {
                        isTileStable[corner[0] + (i * increment)][corner[1]] = true;
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
     * Counts the amount of internally stable discs in board.
     *
     * @param board
     * @param tileStable
     * @param player
     * @return
     */
    public static int countInternalStability(int[][] board, boolean[][] tileStable, int player) {
        return 1;
    }
}

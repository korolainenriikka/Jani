/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;
import othello.utils.BoardUtils;

/**
 * Algorithm utilities: minimax, evaluating function (multiple if needed).
 *
 * @author riikoro
 */
public class AIUtils {

    /**
     * Scoring factors for various tiles on board, from
     * http://samsoft.org.uk/reversi/strategy.htm#position
     */
    static final int[][] boardScoring = {
        {99, -8, 8, 6, 6, 8, -8, 99},
        {-8, -24, -4, -3, -3, -4, -24, -8},
        {8, -4, 7, 4, 4, 7, -4, 8},
        {6, -3, 4, 0, 0, 4, -3, 6},
        {6, -3, 4, 0, 0, 4, -3, 6},
        {8, -4, 7, 4, 4, 7, -4, 8},
        {-8, -24, -4, -3, -3, -4, -24, -8},
        {99, -8, 8, 6, 6, 8, -8, 99},};

    /**
     * Algorithm for trying various game states and predicting their outcomes.
     * Black maximizes white minimizes.
     *
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static int minimax(int[][] board, int player, int depth) {
        if (BoardUtils.gameOver(board)) {
            //convert winner return value to a weighed score
            return winEvaluator(board) * 1000;
        }

        if (depth == 0) {
            return stateEvaluator(board);
        }
        
        int opponent = player == BLACK ? WHITE : BLACK;

        //maximize
        if (player == BLACK) {
            
            int bestScore = -9999;
            
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimax(BoardUtils.boardAfterMove(new int[]{i, j}, board, player), opponent, depth - 1);
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }

        //minimize
        if (player == WHITE) {
            
            int bestScore = 9999;
            
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    
                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimax(BoardUtils.boardAfterMove(new int[]{i, j}, board, player), opponent, depth - 1);
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
        
        return 0;
    }

    /**
     * First evaluating function returns 1, 0 or -1 and only evaluates if game
     * is over. 1 = black won, 0 = even, -1 = white won
     *
     * @param board state of the game
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
                    score += boardScoring[i][j];
                } else if (board[i][j] == WHITE) {
                    score -= boardScoring[i][j];
                }
            }
        }

        return score;
    }
}

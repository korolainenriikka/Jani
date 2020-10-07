/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;
import static othello.utils.GamePhase.*;

/**
 * Variations of minimax: standard algorithm, standard + ab-pruning, standard +
 * ab + 0.99 sec timeout for progressive deepening.
 *
 * @author riikoro
 */
public class Minimax {

    /**
     * Apha-beta-pruning minimax with timeout. Used by progressive deepening.
     * Uses improved mobility evaluation during midgame
     *
     * @param board current state of game
     * @param player the color whose turn it is
     * @param depth current depth of recursion
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static int minimaxAlphaBetaWithTimeout(int[][] board, int player, int depth, int a, int b, long startTime, GamePhase phase) {
        if ((System.nanoTime() - startTime) / 1e9 > 0.99) {
            return 0;
        }

        if (BoardUtils.gameOver(board)) {
            //convert winner return value to a weighed score
            return Evaluators.winEvaluator(board) * 1000;
        }

        if (depth == 0 && phase != ENDGAME) {
            return Evaluators.midgameEvaluator(board, player);
        }
        
        if (depth == 0 && phase == ENDGAME) {
            return Evaluators.endgameEvaluator(board, player);
        }

        int opponent = player == BLACK ? WHITE : BLACK;

        //maximize
        if (player == BLACK) {
            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimaxAlphaBetaWithTimeout(BoardUtils.boardAfterMove(
                                new int[]{i, j}, board, player), opponent, depth - 1, a, b, startTime, phase);
                        if (score > bestScore) {
                            bestScore = score;
                        }

                        if (score > a) {
                            a = score;
                        }

                        if (a >= b) {
                            return a;
                        }
                    }
                }
            }
            return a;
        }

        //minimize
        if (player == WHITE) {
            double bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimaxAlphaBetaWithTimeout(BoardUtils.boardAfterMove(
                                new int[]{i, j}, board, player), opponent, depth - 1, a, b, startTime, phase);
                        if (score < bestScore) {
                            bestScore = score;
                        }

                        if (score < b) {
                            b = score;
                        }

                        if (b <= a) {
                            return b;
                        }
                    }
                }
            }
            return b;
        }
        return 0;
    }

    /**
     * Apha-beta-pruning minimax.
     *
     * @param board current state of game
     * @param player the color whose turn it is
     * @param depth current depth of recursion
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static int minimaxAlphaBeta(int[][] board, int player, int depth, int a, int b) {
        if (BoardUtils.gameOver(board)) {
            //convert winner return value to a weighed score
            return Evaluators.winEvaluator(board) * 1000;
        }

        if (depth == 0) {
            return Evaluators.stateEvaluator(board);
        }

        int opponent = player == BLACK ? WHITE : BLACK;

        //maximize
        if (player == BLACK) {

            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimaxAlphaBeta(BoardUtils.boardAfterMove(
                                new int[]{i, j}, board, player), opponent, depth - 1, a, b);
                        if (score > bestScore) {
                            bestScore = score;
                        }

                        if (score > a) {
                            a = score;
                        }

                        if (a >= b) {
                            return a;
                        }
                    }
                }
            }
            return a;
        }

        //minimize
        if (player == WHITE) {

            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimaxAlphaBeta(BoardUtils.boardAfterMove(
                                new int[]{i, j}, board, player), opponent, depth - 1, a, b);
                        if (score < bestScore) {
                            bestScore = score;
                        }

                        if (score < b) {
                            b = score;
                        }

                        if (b <= a) {
                            return b;
                        }
                    }
                }
            }
            return b;
        }
        return 0;
    }

    /**
     * Standard minimax.
     *
     * @param board current state of game
     * @param player the color whose turn it is
     * @param depth current depth of recursion
     * @return desirability of bsearced board
     */
    public static int minimax(int[][] board, int player, int depth) {
        if (BoardUtils.gameOver(board)) {
            //convert winner return value to a weighed score
            return Evaluators.winEvaluator(board) * 1000;
        }

        if (depth == 0) {
            return Evaluators.stateEvaluator(board);
        }

        int opponent = player == BLACK ? WHITE : BLACK;

        //maximize
        if (player == BLACK) {

            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimax(BoardUtils.boardAfterMove(
                                new int[]{i, j}, board, player), opponent, depth - 1);
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

            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        int score = minimax(BoardUtils.boardAfterMove(
                                new int[]{i, j}, board, player), opponent, depth - 1);
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
}

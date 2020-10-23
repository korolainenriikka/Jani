/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;
import static othello.utils.EntryType.*;
import static othello.utils.GamePhase.*;

/**
 * Variations of minimax: standard algorithm, standard + ab-pruning, standard +
 * ab + 0.99 sec timeout for progressive deepening, standard + ab + timeout +
 * transpositions storage.
 *
 * @author riikoro
 */
public class Minimax {

    /**
     * Minimax with transposition table implementation.
     *
     * @param board current state of game
     * @param player the color whose turn it is
     * @param depth current depth of recursion
     * @param a current alpha value
     * @param b current beta value
     * @param startTime timestamp of start of computation
     * @param phase current gamephase
     * @param table transposition table for value storage
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static int minimaxWithMemory(int[][] board, int player, int depth, int a, int b, long startTime, GamePhase phase, TranspositionTable table) {
        if ((System.nanoTime() - startTime) / 1e9 > 0.99) {
            return 0;
        }

        if (table.hasAssociatedData(board)) {
            TableEntry data = table.get(board);

            if (data.getDepth() >= depth) {
                if (data.getType() == EXACT) {
                    return data.getMinimaxScore();
                }

                if (data.getType() == ALPHA && data.getMinimaxScore() > a) {
                    a = data.getMinimaxScore();
                }

                if (data.getType() == BETA && data.getMinimaxScore() < b) {
                    b = data.getMinimaxScore();
                }

                if (a >= b) {
                    return data.getMinimaxScore();
                }
            }
        }

        if (BoardUtils.gameOver(board)) {
            return Evaluators.winEvaluator(board) * 1000;
        }

        if (depth == 0 && phase == OPENING) {
            return Evaluators.openingEvaluator(board, player);
        }

        if (depth == 0 && phase == MIDGAME) {
            return Evaluators.midgameEvaluator(board, player);
        }

        if (depth == 0 && phase == ENDGAME) {
            return Evaluators.endgameEvaluator(board, player);
        }

        int opponent = player == BLACK ? WHITE : BLACK;
        int[] bestMove = new int[]{0, 0};
        int entryDepth = depth < 20 ? depth : 100;

        //maximize
        if (player == BLACK) {
            int bestScore = -2147483648;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (BoardUtils.isAllowed(i, j, player, board) 
                            && !(i == bestMove[0] && j == bestMove[1])) {
                        int[] thisMove = new int[]{i, j};
                        int score = minimaxWithMemory(BoardUtils.boardAfterMove(
                                thisMove, board, player), opponent, depth - 1, a, b, startTime, phase, table);
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = thisMove;
                        }

                        if (score > a) {
                            a = score;
                        }

                        if (a >= b) {
                            table.add(new TableEntry(board, entryDepth, ALPHA, score, bestMove));
                            return a;
                        }

                    }
                }
            }
            table.add(new TableEntry(board, depth, EXACT, bestScore, bestMove));
            return a;
        }

        //minimize
        if (player == WHITE) {
            int bestScore = 2147483647;
            
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (BoardUtils.isAllowed(i, j, player, board) && !(i == bestMove[0] && j == bestMove[1])) {
                        int score = minimaxWithMemory(BoardUtils.boardAfterMove(
                                new int[]{i, j}, board, player), opponent, depth - 1, a, b, startTime, phase, table);
                        if (score < bestScore) {
                            bestScore = score;
                        }

                        if (score < b) {
                            b = score;
                        }

                        if (b <= a) {
                            table.add(new TableEntry(board, entryDepth, BETA, score, bestMove));
                            return b;
                        }
                    }
                }
            }
            table.add(new TableEntry(board, entryDepth, EXACT, bestScore, bestMove));
            return b;
        }
        return 0;
    }

    /**
     * Apha-beta-pruning minimax with timeout. Used by progressive deepening.
     * Uses improved mobility evaluation during midgame.
     *
     * @param board current state of game
     * @param player the color whose turn it is
     * @param depth current depth of recursion
     * @param a current alpha value
     * @param b current beta value
     * @param startTime timestamp of start of computation
     * @param phase current gamephase
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static int minimaxAlphaBetaWithTimeout(int[][] board, int player, int depth, int a, int b, long startTime, GamePhase phase) {
        if ((System.nanoTime() - startTime) / 1e9 > 0.99) {
            return 0;
        }

        if (BoardUtils.gameOver(board)) {
            return Evaluators.winEvaluator(board) * 1000;
        }

        if (depth == 0 && phase == OPENING) {
            return Evaluators.openingEvaluator(board, player);
        }

        if (depth == 0 && phase == MIDGAME) {
            return Evaluators.midgameEvaluator(board, player);
        }

        if (depth == 0 && phase == ENDGAME) {
            return Evaluators.endgameEvaluator(board, player);
        }

        int opponent = player == BLACK ? WHITE : BLACK;

        //maximize
        if (player == BLACK) {
            int bestScore = -2147483648;

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
            int bestScore = 2147483647;

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
     * @param a current alpha value
     * @param b current beta value
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static int minimaxAlphaBeta(int[][] board, int player, int depth, int a, int b) {
        if (BoardUtils.gameOver(board)) {
            return Evaluators.winEvaluator(board) * 1000;
        }

        if (depth == 0) {
            return Evaluators.stateEvaluator(board);
        }

        int opponent = player == BLACK ? WHITE : BLACK;

        //maximize
        if (player == BLACK) {

            int bestScore = -2147483648;

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

            int bestScore = 2147483647;

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
     * @return desirability of searched board
     */
    public static int minimax(int[][] board, int player, int depth) {
        if (BoardUtils.gameOver(board)) {
            return Evaluators.winEvaluator(board) * 1000;
        }

        if (depth == 0) {
            return Evaluators.stateEvaluator(board);
        }

        int opponent = player == BLACK ? WHITE : BLACK;

        //maximize
        if (player == BLACK) {

            int bestScore = -2147483648;

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

            int bestScore = 2147483647;

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

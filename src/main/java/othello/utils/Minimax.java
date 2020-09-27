/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import java.util.Comparator;
import java.util.PriorityQueue;
import static othello.api.Tile.*;
import othello.utils.BoardUtils;
import othello.utils.Evaluators;

/**
 * Variations of minimax: standard algorithm, standard + ab-pruning, standard +
 * ab + progressive deepening.
 *
 * @author riikoro
 */
public class Minimax {

    /**
     * Method implementing progressively deepening minimax; calls minimax with
     * incrementing depths and orders searched moves according to previous
     * search scores.
     *
     * TODO: implement self-made min/max priorityqueue.
     *
     * @param board state of the game
     * @param player player to make the next move
     * @return move in a {row, col} array
     */
    public static int[] progressiveDeepening(int[][] board, int player) {
        long start = System.nanoTime();

        // node format: {{move_row, move_col}, {minimaxscore, 0}}
        // comparator minimaxscore
        // two heaps, one in iteration, one for inserting new nodes
        PriorityQueue<int[][]> oddHeap;
        PriorityQueue<int[][]> evenHeap;

        // initialize heaps
        if (player == BLACK) {
            //max heap
            Comparator c = new Comparator<int[][]>() {
                @Override
                public int compare(int[][] o1, int[][] o2) {
                    if ((o1[1][1] - o2[1][1]) == 0) {
                        return -(o1[1][0] - o2[1][0]);
                    }
                    return o1[1][1] - o2[1][1];
                }
            };
            oddHeap = new PriorityQueue<int[][]>(c);
            evenHeap = new PriorityQueue<int[][]>(c);
        } else {
            //min heap
            Comparator c = new Comparator<int[][]>() {
                @Override
                public int compare(int[][] o1, int[][] o2) {
                    if ((o1[1][1] - o2[1][1]) == 0) {
                        return o1[1][0] - o2[1][0];
                    }
                    return o1[1][1] - o2[1][1];
                }
            };

            oddHeap = new PriorityQueue<int[][]>(c);
            evenHeap = new PriorityQueue<int[][]>(c);
        }

        // find moves and insert to heap
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    oddHeap.add(new int[][]{{i, j}, {0, 0}});
                }
            }
        }

        // call minimax iteratively
        int depth = 0;
        double infty = Double.POSITIVE_INFINITY;
        PriorityQueue<int[][]> heapToIterate;
        PriorityQueue<int[][]> heapToInsert = null;

        while ((System.nanoTime() - start) / 1e9 < 0.5) {
            depth++;

            if (depth / 2 == 0) {
                heapToIterate = evenHeap;
                heapToInsert = oddHeap;
            } else {
                heapToIterate = oddHeap;
                heapToInsert = evenHeap;
            }

            for (int[][] moveRecord : heapToIterate) {
                if ((System.nanoTime() - start) / 1e9 > 0.5) {
                    break;
                }
                int[][] boardAfterMove = BoardUtils
                        .boardAfterMove(moveRecord[0], board, player);
                int score = (int) minimaxAlphaBeta(
                        boardAfterMove, player, depth, -1 * infty, infty);
                heapToInsert.add(new int[][]{moveRecord[0], {score, depth}});
            }
        }
        // just before timeout return highest evaluated next move
        return heapToInsert.poll()[0];
    }

    /**
     * Algorithm for trying various game states and predicting their outcomes.
     * Black maximizes white minimizes.
     *
     * @param board current state of game
     * @param player the color whose turn it is
     * @param depth current depth of recursion
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static double minimaxAlphaBeta(int[][] board, int player, int depth, double a, double b) {
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

            double bestScore = Double.NEGATIVE_INFINITY;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        double score = minimaxAlphaBeta(BoardUtils.boardAfterMove(
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

            double bestScore = Double.POSITIVE_INFINITY;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        double score = minimaxAlphaBeta(BoardUtils.boardAfterMove(
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
     * Algorithm for trying various game states and predicting their outcomes.
     * Black maximizes white minimizes.
     *
     * @param board current state of game
     * @param player the color whose turn it is
     * @param depth current depth of recursion
     * @return desirability of board, +-1000=game over, between -1000 and 1000
     * state desirability
     */
    public static double minimax(int[][] board, int player, int depth) {
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

            double bestScore = Double.NEGATIVE_INFINITY;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        double score = minimax(BoardUtils.boardAfterMove(
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

            double bestScore = Double.POSITIVE_INFINITY;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (BoardUtils.isAllowed(i, j, player, board)) {
                        double score = minimax(BoardUtils.boardAfterMove(
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

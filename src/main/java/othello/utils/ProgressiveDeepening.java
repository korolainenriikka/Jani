/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import othello.utils.Heap;
import static othello.api.Tile.*;

/**
 * Progressive Deepening implementation with 1.0 second timeout.
 *
 * @author riikoro
 */
public class ProgressiveDeepening {

    // heap iterated with odd depths and used for insertions with even depths
    private static Heap oddHeap;
    // ... vice versa
    private static Heap evenHeap;

    /**
     * Method implementing progressively deepening minimax; calls minimax with
     * incrementing depths and orders searched moves according to previous
     * search scores. Interrupts current iteration immediately at timeout.
     *
     * @param board state of the game
     * @param player player to make the next move
     * @param printsOn enable prints for performance tests
     * @param phase current phase of game
     * @param usesMemory true if bot implements a transposition table
     * @param table transposition table, null if no memory usage
     * @return move in a {row, col} array
     */
    public static int[] findBestMove(int[][] board, int player, boolean printsOn, GamePhase phase, boolean usesMemory, TranspositionTable table) {
        long start = System.nanoTime();

        // initialization
        initializeHeaps(player);
        addAvailableMovesToOddHeap(board, player);

        // minimax iteration
        int depth = 0;
        int infty = 2147483647;
        Heap heapToIterate = oddHeap;
        Heap heapToInsert = evenHeap;
        int[] currentBest = heapToIterate.peek()[0];

        while ((System.nanoTime() - start) / 1e9 < 0.9) {
            depth++;
            if (depth % 2 == 0) {
                heapToIterate = evenHeap;
                heapToInsert = oddHeap;
            } else {
                heapToIterate = oddHeap;
                heapToInsert = evenHeap;
            }
            currentBest = heapToIterate.peek()[0];

            while (!heapToIterate.isEmpty()) {
                int[][] moveRecord = heapToIterate.poll();
                if ((System.nanoTime() - start) / 1e9 > 0.99) {
                    break;
                }
                int[][] boardAfterMove = BoardUtils
                        .boardAfterMove(moveRecord[0], board, player);
                int opponent = player == BLACK ? WHITE : BLACK;
                
                int score;
                if (usesMemory) {
                    score = Minimax.minimaxWithMemory(
                        boardAfterMove, opponent, depth, -1 * infty, infty, start, phase, table);
                } else {
                    score = Minimax.minimaxAlphaBetaWithTimeout(
                        boardAfterMove, opponent, depth, -1 * infty, infty, start, phase);
                }
                heapToInsert.add(new int[][]{moveRecord[0], {score, 0}});
            }
        }

        print("Depth " + depth + " reached", printsOn);
        // just before timeout return currently highest evaluated move
        return currentBest;
    }

    private static void initializeHeaps(int player) {
        if (player == BLACK) {
            //max heaps
            oddHeap = new Heap(true);
            evenHeap = new Heap(true);
        } else {
            //min heaps
            oddHeap = new Heap(false);
            evenHeap = new Heap(false);
        }
    }

    private static void addAvailableMovesToOddHeap(int[][] board, int player) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    oddHeap.add(new int[][]{{i, j}, {0, 0}});
                }
            }
        }
    }

    private static void print(String string, boolean printsOn) {
        // for printing depth reached in performance tests.
        if (printsOn) {
            System.out.println(string);
        }
    }
}

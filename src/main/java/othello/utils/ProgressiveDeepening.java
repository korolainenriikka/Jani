/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import java.util.Comparator;
import java.util.PriorityQueue;
import static othello.api.Tile.*;

/**
 * Progressive Deepening implementation with 1.0 second timeout.
 *
 * @author riikoro
 */
public class ProgressiveDeepening {

    // heap iterated with odd depths and used for insertions with even depths
    private static PriorityQueue<int[][]> oddHeap;
    // ... vice versa
    private static PriorityQueue<int[][]> evenHeap;

    /**
     * Method implementing progressively deepening minimax; calls minimax with
     * incrementing depths and orders searched moves according to previous
     * search scores. Interrupts current iteration immediately at timeout.
     *
     * TODO: implement self-made min/max priorityqueue.
     *
     * @param board state of the game
     * @param player player to make the next move
     * @return move in a {row, col} array
     */
    public static int[] findBestMove(int[][] board, int player) {
        long start = System.nanoTime();

        // initialization
        initializeHeaps(player);
        addAvailableMovesToOddHeap(board, player);

        // minimax iteration
        int depth = 0;
        double infty = Double.POSITIVE_INFINITY;
        PriorityQueue<int[][]> heapToIterate = oddHeap;
        PriorityQueue<int[][]> heapToInsert = evenHeap;
        int[] currentBest = heapToIterate.peek()[0];

        while ((System.nanoTime() - start) / 1e9 < 0.99) {
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
                int score = (int) Minimax.minimaxAlphaBetaWithTimeout(
                        boardAfterMove, opponent, depth, -1 * infty, infty, start);
                heapToInsert.add(new int[][]{moveRecord[0], {score, 0}});
            }
        }
        // just before timeout return currently highest evaluated move
        System.out.println("returning: " + currentBest[0] + currentBest[1]);
        return currentBest;
    }

    private static void initializeHeaps(int player) {
        // node format: {{move_row, move_col}, {minimaxscore, 0}}
        // comparator minimaxscore
        // two heaps, one in iteration, one for inserting new nodes
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
    }

    private static void addAvailableMovesToOddHeap(int[][] board, int player) {
        // find moves and insert to first heap
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    oddHeap.add(new int[][]{{i, j}, {0, 0}});
                }
            }
        }
    }

}

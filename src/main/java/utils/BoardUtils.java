/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Arrays;

/**
 *
 * @author riikoro
 */
public class BoardUtils {

    static final int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static final int SIZE = 8;

    /**
     * Util for checking if a given move is allowed according to game rules
     *
     * @param row row number of the move to be made
     * @param col col number of the move to be made
     * @param player the player making the move
     * @param board current state of the board
     * @return if the move is allowed or not
     */
    public static boolean isAllowed(int row, int col, int player, int[][] board) {
        int opponent = player == 1 ? 2 : 1;

        //clockwise from top-left:
        for (int i = 0; i < directions.length; i++) {
            int nextrow = row + directions[i][0];
            int nextcol = col + directions[i][1];

            if (withinBoard(nextrow, nextcol) && board[nextrow][nextcol] == opponent) {
                while (withinBoard(nextrow, nextcol) && board[nextrow][nextcol] == opponent) {
                    nextrow = nextrow + directions[i][0];
                    nextcol = nextcol + directions[i][1];
                }

                if (!withinBoard(nextrow, nextcol)) {
                    continue;
                }

                if (board[nextrow][nextcol] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Util for checking if indices fit in the 8x8 board
     *
     * @param row row index
     * @param col column index
     * @return if indices are valid
     */
    public static boolean withinBoard(int row, int col) {
        if (row >= 0 && col >= 0 && row < SIZE && col < SIZE) {
            return true;
        }
        return false;
    }

    /**
     * Util for predicting state of board after a given move
     *
     * @param move the indices of the move in {row, col} array
     * @param board board state before move
     * @param player the player whose turn it is
     * @return a copy of the board with the new state
     */
    public static int[][] boardAfterMove(int[] move, int[][] board, int player) {
        //util for predicting game state after move
        int[][] afterMove = copy2dArray(board);
        int moveRow = move[0];
        int moveCol = move[1];
        afterMove[moveRow][moveCol] = player;
        int opponent = player == 1 ? 2 : 1;

        //flip pieces surrounded by opponent
        for (int i = 0; i < directions.length; i++) {
            int nextrow = moveRow + directions[i][0];
            int nextcol = moveCol + directions[i][1];

            if (withinBoard(nextrow, nextcol) && board[nextrow][nextcol] == opponent) {
                while (withinBoard(nextrow, nextcol) && board[nextrow][nextcol] == opponent) {
                    nextrow = nextrow + directions[i][0];
                    nextcol = nextcol + directions[i][1];
                }

                if (!withinBoard(nextrow, nextcol)) {
                    continue;
                }

                if (board[nextrow][nextcol] == player) {
                    while (nextrow != moveRow || nextcol != moveCol) {
                        nextrow = nextrow - directions[i][0];
                        nextcol = nextcol - directions[i][1];
                        afterMove[nextrow][nextcol] = player;
                    }
                }
            }
        }
        return afterMove;
    }

    /**
     * Square matrix copying tool for other utils
     *
     * @param array array to be copied
     * @return a copy of the array
     */
    public static int[][] copy2dArray(int[][] array) {
        int[][] copy = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                copy[i][j] = array[i][j];
            }
        }

        return copy;
    }

}

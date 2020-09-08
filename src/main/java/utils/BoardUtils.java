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

    public static boolean isAllowed(int x, int y, int player, int[][] board) {
        int opponent = player == 1 ? 2 : 1;

        //clockwise from top-left:
        for (int i = 0; i < directions.length; i++) {
            int nextrow = x + directions[i][0];
            int nextcol = y + directions[i][1];

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

    public static boolean withinBoard(int row, int col) {
        if (row >= 0 && col >= 0 && row < SIZE && col < SIZE) {
            return true;
        }
        return false;
    }

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

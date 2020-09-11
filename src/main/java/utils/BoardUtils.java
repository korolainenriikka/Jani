/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author riikoro
 */
public class BoardUtils {

    /**
     * Index changes for iterating all tiles in board surrounding the one
     * inspected.
     */
    static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
    {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    /**
     * Size of the othello board.
     */
    static final int SIZE = 8;

    /**
     * Util for checking if a given move is allowed according to game rules.
     *
     * @param row row number of the move to be made
     * @param col col number of the move to be made
     * @param player the player making the move
     * @param board current state of the board
     * @return if the move is allowed or not
     */
    public static boolean isAllowed(final int row, final int col,
            final int player, final int[][] board) {
        if (!withinBoard(row, col)
                || board[row][col] != 0) {
            return false;
        }

        int opponent = player == 1 ? 2 : 1;

        //clockwise from top-left:
        for (int[] direction : DIRECTIONS) {
            int nextrow = row + direction[0];
            int nextcol = col + direction[1];

            if (!withinBoard(nextrow, nextcol)
                    || board[nextrow][nextcol] != opponent) {
                continue;
            }

            while (withinBoard(nextrow, nextcol)
                    && board[nextrow][nextcol] == opponent) {
                nextrow = nextrow + direction[0];
                nextcol = nextcol + direction[1];
            }

            if (!withinBoard(nextrow, nextcol)) {
                continue;
            }

            if (board[nextrow][nextcol] == player) {
                return true;
            }
        }
        return false;
    }

    /**
     * Util for checking if indices fit in the 8x8 board.
     *
     * @param row row index
     * @param col column index
     * @return if indices are valid
     */
    public static boolean withinBoard(final int row, final int col) {
        if (row >= 0 && col >= 0 && row < SIZE && col < SIZE) {
            return true;
        }
        return false;
    }

    /**
     * Util for predicting state of board after a given move.
     *
     * @param move the indices of the move in {row, col} array
     * @param board board state before move
     * @param player the player whose turn it is
     * @return a copy of the board with the new state
     */
    public static int[][] boardAfterMove(final int[] move,
            final int[][] board, final int player) {
        //util for predicting game state after move
        int[][] afterMove = copy2dArray(board);
        int moveRow = move[0];
        int moveCol = move[1];
        afterMove[moveRow][moveCol] = player;
        int opponent = player == 1 ? 2 : 1;

        //flip pieces surrounded by opponent
        for (int i = 0; i < DIRECTIONS.length; i++) {
            int nextrow = moveRow + DIRECTIONS[i][0];
            int nextcol = moveCol + DIRECTIONS[i][1];

            if (withinBoard(nextrow, nextcol)
                    && board[nextrow][nextcol] == opponent) {
                while (withinBoard(nextrow, nextcol)
                        && board[nextrow][nextcol] == opponent) {
                    nextrow = nextrow + DIRECTIONS[i][0];
                    nextcol = nextcol + DIRECTIONS[i][1];
                }

                if (!withinBoard(nextrow, nextcol)) {
                    continue;
                }

                if (board[nextrow][nextcol] == player) {
                    while (nextrow != moveRow || nextcol != moveCol) {
                        nextrow = nextrow - DIRECTIONS[i][0];
                        nextcol = nextcol - DIRECTIONS[i][1];
                        afterMove[nextrow][nextcol] = player;
                    }
                }
            }
        }
        return afterMove;
    }

    /**
     * Square matrix copying tool for other utils.
     *
     * @param array array to be copied
     * @return a copy of the array
     */
    public static int[][] copy2dArray(final int[][] array) {
        int[][] copy = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                copy[i][j] = array[i][j];
            }
        }

        return copy;
    }

    /**
     * Checks if current player has won the game.
     *
     * @param boardState state of the game
     * @return if the player won the game
     */
    public static boolean winsAt(final int[][] boardState) {
        return false;
    }

}

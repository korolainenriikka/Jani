package othello.domain;

import static othello.api.Tile.*;
import java.util.Arrays;

/**
 * Othello board modification and checking methods for running the game.
 *
 * @author riikoro
 */
public class Board {

    /**
     * Size of the board.
     */
    public static final int SIZE = 8;
    /**
     * For iterating the tiles surrounding another in specified indices.
     */
    public static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private int[][] board;

    /**
     * Initializes the standard start of an Othello game.
     */
    public Board() {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.setTile(i, j, EMPTY);
            }
        }
        this.setTile(3, 3, WHITE);
        this.setTile(4, 3, BLACK);
        this.setTile(3, 4, BLACK);
        this.setTile(4, 4, WHITE);
    }

    /**
     * Constructor for testing game functions in various game states.
     *
     * @param initialBoard state of game needed in the test
     */
    public Board(int[][] initialBoard) {
        this.board = initialBoard;
    }

    /**
     * Insert new move to the board if move is valid.
     *
     * @param x row of the move to be added
     * @param y column of the move to be added
     * @param color color of the piece to be added
     * @return true/false according to whether move was valid or not
     */
    public boolean addMove(int x, int y, int color) {
        if (isMoveValid(x, y, color)) {
            this.setTile(x, y, color);
            int opponent = color == WHITE ? BLACK : WHITE;

            //flip pieces surrounded by opponent
            for (int[] direction : DIRECTIONS) {
                int nextx = x + direction[0];
                int nexty = y + direction[1];

                if (isMoveInBounds(nextx, nexty) && this.getTile(nextx, nexty) == opponent) {
                    while (isMoveInBounds(nextx, nexty) && this.getTile(nextx, nexty) == opponent) {
                        nextx = nextx + direction[0];
                        nexty = nexty + direction[1];
                    }

                    if (!isMoveInBounds(nextx, nexty)) {
                        continue;
                    }

                    if (this.getTile(nextx, nexty) == color) {
                        while (nextx != x || nexty != y) {
                            nextx = nextx - direction[0];
                            nexty = nexty - direction[1];
                            this.setTile(nextx, nexty, color);
                        }
                    }
                }
            }
            return true;
        }

        return false;
    }

    private boolean isMoveValid(int x, int y, int color) {
        if (!isMoveInBounds(x, y)
                || this.getTile(x, y) != EMPTY) {
            return false;
        }

        int opponent = color == WHITE ? BLACK : WHITE;

        //clockwise from top-left:
        for (int[] direction : DIRECTIONS) {
            int nextx = x + direction[0];
            int nexty = y + direction[1];

            if (!isMoveInBounds(nextx, nexty)
                    || this.getTile(nextx, nexty) != opponent) {
                continue;
            }

            while (isMoveInBounds(nextx, nexty)
                    && this.getTile(nextx, nexty) == opponent) {
                nextx += direction[0];
                nexty += direction[1];
            }

            if (!isMoveInBounds(nextx, nexty)) {
                continue;
            }

            if (this.getTile(nextx, nexty) == color) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the piece in specified tile.
     *
     * @param x row
     * @param y column
     * @return int value representing color of the piece or empty tile
     */
    public int getTile(int x, int y) {
        return this.board[x][y];
    }

    /**
     * Edit board in specified indices.
     *
     * @param x row to be edited
     * @param y column to be edited
     * @param value int value representing color of the piece or empty tile
     */
    private void setTile(int x, int y, int value) {
        this.board[x][y] = value;
    }

    /**
     * Checks if game is over or not.
     *
     * @return true = game over
     */
    public boolean isGameOver() {
        if (!hasValidMovesLeft(BLACK) && !hasValidMovesLeft(WHITE)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the color of the winner of the game or empty in case of draw.
     *
     * @return winner color or empty
     */
    public int winner() {
        int blackScore = 0, whiteScore = 0;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (this.getTile(x, y) == BLACK) {
                    blackScore++;
                } else {
                    whiteScore++;
                }
            }
        }
        if (blackScore > whiteScore) {
            return BLACK;
        } else if (whiteScore > blackScore) {
            return WHITE;
        } else {
            return EMPTY;
        }
    }

    /**
     * Checks if player has valid moves left in game.
     *
     * @param player the color being checked
     * @return true if there are moves
     */
    public boolean hasValidMovesLeft(int player) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (this.isMoveValid(x, y, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if indices fit in 8x8 board.
     *
     * @param row row to be checked
     * @param col column to be checked
     * @return if indices fit or not.
     */
    private boolean isMoveInBounds(int row, int col) {
        if (row >= 0 && col >= 0 && row < board.length && col < board.length) {
            return true;
        }
        return false;
    }

    /**
     * Equality checker for tests, equal = all array values of board parameter
     * equal.
     *
     * @param obj object compared to this one
     * @return true for equality
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }

    /**
     * return the size of the board
     *
     * @return size (= 8)
     */
    public int size() {
        return SIZE;
    }

    /**
     * Returns a copy of the board array.
     *
     * @return an array copy
     */
    public int[][] getAsArray() {
        int[][] copyOfBoard = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                copyOfBoard[i][j] = this.board[i][j];
            }
        }

        return copyOfBoard;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;
import java.util.Arrays;
import othello.utils.BoardUtils;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
public class BoardUtilsTest {

    private int[][] startOfGameBoard;
    private int[][] oneMoveLeftBoard;

    @Before
    public void setUp() {
        startOfGameBoard = new int[8][8];
        startOfGameBoard[3][3] = WHITE;
        startOfGameBoard[3][4] = BLACK;
        startOfGameBoard[4][3] = BLACK;
        startOfGameBoard[4][4] = WHITE;

        oneMoveLeftBoard = new int[8][8];
        for (int[] row : oneMoveLeftBoard) {
            Arrays.fill(row, BLACK);
        }
        oneMoveLeftBoard[0][7] = WHITE;
        oneMoveLeftBoard[7][7] = EMPTY;
    }

    public boolean boardsEqual(int[][] first, int[][] second) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first.length; j++) {
                if (first[i][j] != second[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void tileInBoardReturnsTrue() {
        assert (BoardUtils.withinBoard(0, 7));
        assert (BoardUtils.withinBoard(1, 4));
    }

    @Test
    public void tileOutOfBoardReturnsFalse() {
        assert (!BoardUtils.withinBoard(10, 9));
    }

    @Test
    public void openingMoveOnEdgeNotAllowed() {
        assert (!BoardUtils.isAllowed(0, 0, 1, startOfGameBoard));
    }

    /*
        isAllowed test moves
        0 1 2 3 4 5 6 7 
      0| | | | | | | | |
      1| | | | | | | | |
      2| | | | | | | | |
      3| | | |2|1|t1| | |
      4| | | |1|2|t2| | |
      5| | | | | | | | |
      6| | | | | | | | |
      7| | | | | | | | |
     */
    @Test
    public void openingMoveNextToOwnPlayerNotAllowed() {
        //spot t1
        assert (!BoardUtils.isAllowed(3, 5, 1, startOfGameBoard));
    }

    @Test
    public void openingMoveNextToOthersAllowed() {
        //spot t2
        assert (BoardUtils.isAllowed(4, 5, 1, startOfGameBoard));
    }

    /*
    boardAfterMove initial board & test move
    (all 2's except bottom one should flip)
        0 1 2 3 4 5 6 7 
      0| | | | | | | | |
      1| | | | | | | | |
      2| | | |1| | | | |
      3| | | |2|1| | | |
      4| | | | |2|2|2|1|
      5| | | |2| | | | |
      6| | | | | | | | |
      7| | | | | | | | |
     */
    @Test
    public void boardAfterMoveReturnsCorrectPrediction() {
        int[][] initial = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 0},
            {0, 0, 0, 0, 2, 2, 2, 1},
            {0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[] move = {4, 3};
        int[][] afterMove = BoardUtils.boardAfterMove(move, initial, 1);

        int[][] expected = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        assert (boardsEqual(expected, afterMove));
    }

    @Test
    public void initialStateGameNotOver() {
        assert (!BoardUtils.gameOver(startOfGameBoard));
    }

    @Test
    public void oneMoveLeftGameNotOver() {
        assert (!BoardUtils.gameOver(oneMoveLeftBoard));
    }

    @Test
    public void noMovesLeftGameOver() {
        oneMoveLeftBoard[7][7] = WHITE;
        assert (BoardUtils.gameOver(oneMoveLeftBoard));
    }

    @Test
    public void playerWithMoreDiscsWins() {
        assertEquals(BLACK, BoardUtils.winner(oneMoveLeftBoard));
    }
}

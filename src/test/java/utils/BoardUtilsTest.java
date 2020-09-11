/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import utils.BoardUtils;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
public class BoardUtilsTest {

    private int[][] board;

    @Before
    public void setUp() {
        board = new int[8][8];
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
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

    //for debug
    public String boardToString(int[][] boardToPrint) {
        String b = "  a b c d e f g h\n";
        for (int i = 0; i < boardToPrint.length; i++) {
            b += i + 1;
            for (int j = 0; j < boardToPrint[0].length; j++) {
                b += "|";
                if (boardToPrint[i][j] == 0) {
                    b += " ";
                } else if (boardToPrint[i][j] == 1) {
                    b += "○";
                } else {
                    b += "●";
                }
            }
            b += "|\n";
        }
        return b;
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
        assert (!BoardUtils.isAllowed(0, 0, 1, board));
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
        assert (!BoardUtils.isAllowed(3, 5, 1, board));
    }

    @Test
    public void openingMoveNextToOthersAllowed() {
        //spot t2
        System.out.println("täs testis");
        assert (BoardUtils.isAllowed(4, 5, 1, board));
        System.out.println("tää testi loppuu täs ");
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
}

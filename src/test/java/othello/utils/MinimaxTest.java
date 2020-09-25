/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;
import othello.utils.BoardUtils;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
public class MinimaxTest {

    private int[][] board;
    private final double INFTY;

    public MinimaxTest() {
        INFTY = Double.POSITIVE_INFINITY;
    }

    @Before
    public void setUp() {
        board = new int[8][8];
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }

    @Test
    public void minimaxOpeningScoresAlwaysEqual() {
        // every opening score has value 4  
        assertEquals(4, (int) Minimax.minimaxAlphaBeta(board, BLACK, 1, -1 * INFTY, INFTY));
        assertEquals(-4, (int) Minimax.minimaxAlphaBeta(board, WHITE, 1, -1 * INFTY, INFTY));
    }

    @Test
    public void minimaxReturnsWinningScoreIfEliminationPossibleInOneMove() {
        // white can eliminate black with one move in this situation
        int[][] eliminationBoard = new int[8][8];
        eliminationBoard[3][2] = BLACK;
        eliminationBoard[3][3] = BLACK;
        eliminationBoard[4][3] = BLACK;
        eliminationBoard[2][2] = WHITE;
        eliminationBoard[2][4] = WHITE;
        eliminationBoard[4][4] = WHITE;

        assertEquals(-1000, (int) Minimax.minimaxAlphaBeta(eliminationBoard, WHITE, 1, -1 * INFTY, INFTY));
    }

    @Test
    public void winEvaluatorReturnVaueEqualsBoardUtilWinningValue() {
        assertEquals(BoardUtils.winner(board), Evaluators.winEvaluator(board));
    }

    @Test
    public void stateEvaluatorReturnsSumOfPieceScoresOfPlayer() {
        board[0][0] = BLACK;
        board[1][1] = BLACK;
        assertEquals(75, Evaluators.stateEvaluator(board));
    }
}

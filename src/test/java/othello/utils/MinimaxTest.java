/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;

import org.junit.*;
import static org.junit.Assert.*;
import static othello.utils.GamePhase.OPENING;

/**
 *
 * @author riikoro
 */
public class MinimaxTest {

    private int[][] board;
    private final int INFTY;

    public MinimaxTest() {
        INFTY = Integer.MAX_VALUE;
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
    }
    
    @Test
    public void minimaxWithMemoryOpeningScoresEqual() {
        // every opening score has value 20 w opening evaluator
        TranspositionTable table = new TranspositionTable();
        TranspositionTable.generateZobristIdentifiers();
        assertEquals(20, (int) Minimax.minimaxWithMemory(board, BLACK, 1, -1 * INFTY, INFTY, System.nanoTime(), OPENING, table));
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
}

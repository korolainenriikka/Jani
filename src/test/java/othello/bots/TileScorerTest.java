/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import java.util.Arrays;
import static othello.api.Tile.*;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Tests both tile-scoring bots
 *
 * @author riikoro
 */
public class TileScorerTest {

    private JaniTileScorer bot;
    private JaniAlphaTileScorer botA;
    private int[][] board;

    @Before
    public void setUp() {
        this.bot = new JaniTileScorer();
        this.botA = new JaniAlphaTileScorer();
        //init default othello start state
        this.board = new int[8][8];
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }

    @Test
    public void playsOpeningTopLeft() {
        bot.startGame(BLACK);
        int[] move = bot.makeMove(board);

        // plays first encountered allowed move, opening scores equal
        assert (move[0] == 2 && move[1] == 3);
    }

    @Test
    public void choosesMoveThatResultsInBotWinning() {
        int[][] gameState = new int[][]{
            {1, 1, 2, 2, 2, 2, 0, 1},
            {0, 1, 2, 2, 2, 1, 1, 1},
            {2, 2, 1, 2, 1, 1, 1, 1},   
            {2, 1, 2, 1, 2, 1, 2, 1},
            {2, 1, 2, 2, 1, 2, 1, 1},
            {2, 2, 2, 2, 2, 1, 2, 1},
            {2, 2, 2, 2, 2, 2, 1, 1},
            {2, 1, 1, 1, 1, 1, 1, 1}
        };

        bot.startGame(WHITE);
        botA.startGame(WHITE);
        int[] move = bot.makeMove(gameState.clone());
        int[] moveA = botA.makeMove(gameState.clone());

        // this move wins the other one loses for sure
        assert ((move[0] == 0 && move[1] == 6) && (moveA[0] == 0 && moveA[1] == 6));
    }

    @Test
    public void isNotHuman() {
        assertFalse(bot.isHuman() || botA.isHuman());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import static othello.api.Tile.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author riikoro
 */
public class JaniTileScorerTest {

    private JaniTileScorer bot;
    private int[][] board;

    @Before
    public void setUp() {
        this.bot = new JaniTileScorer();
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
        bot.startGame(WHITE);
    }

    @Test
    public void isNotHuman() {
        assertFalse(bot.isHuman());
    }
}

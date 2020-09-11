/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bots;

import static coreinterfaces.Tile.BLACK;
import static coreinterfaces.Tile.WHITE;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
public class JaniDummyTest {

    private JaniDummy bot;
    private int[][] board;

    @Before
    public void setUp() {
        this.bot = new JaniDummy();
        bot.startGame(BLACK);
        //init default othello start state
        this.board = new int[8][8];
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }

    @Test
    public void playsAlwaysTopLeftMostAllowed() {
        assertEquals(2, bot.makeMove(board)[0]);
        assertEquals(3, bot.makeMove(board)[1]);
    }

    @Test
    public void returnsNullIfAskedToPlayWhenNoMoveAllowed() {
        board[3][3] = BLACK;
        board[4][4] = BLACK;
        assertEquals(null, bot.makeMove(board));
    }

    @Test
    public void isNotHuman() {
        assertFalse(bot.isHuman());
    }
}

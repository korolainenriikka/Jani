/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bots;

import static coreinterfaces.Tile.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author riikoro
 */
public class JaniRandomTest {

    private JaniRandom bot;
    private int[][] board;

    @Before
    public void setUp() {
        this.bot = new JaniRandom();
        bot.startGame(BLACK);
        //init default othello start state
        this.board = new int[8][8];
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }

    @Test
    public void playsOpeningAccordingToRules() {
        int[] move = bot.makeMove(board);

        // check if opening move is one of the 4 allowed ones
        assert ((move[0] == 2 && move[1] == 3)
                || (move[0] == 3 && move[1] == 2)
                || (move[0] == 4 && move[1] == 5)
                || (move[0] == 5 && move[1] == 4));
    }

    @Test
    public void isNotHuman() {
        assertFalse(bot.isHuman());
    }
}

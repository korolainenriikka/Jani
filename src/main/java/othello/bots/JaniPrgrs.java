/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import othello.api.OthelloBot;
import othello.utils.*;

/**
 * A bot using a progressively deepening a-b minimax.
 *
 * @author riikoro
 */
public class JaniPrgrs implements OthelloBot {

    /**
     * The color this bot plays in the game.
     */
    private int player;

    /**
     * Initialize bot parameters.
     *
     * @param color the color this bot is playing in the game
     */
    @Override
    public void startGame(int color) {
        this.player = color;
    }

    /**
     * Computes optimal move with an iteratively deepening minimax.
     *
     * @param board state of game
     * @return array of move indices in form {row, col}
     */
    @Override
    public int[] makeMove(int[][] board) {
        int[] move = Minimax.progressiveDeepening(board, player);
        return move;
    }

    /**
     * Tells game client it is a bot playing.
     *
     * @return false
     */
    @Override
    public boolean isHuman() {
        return false;
    }

}

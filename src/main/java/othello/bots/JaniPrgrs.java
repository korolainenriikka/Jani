/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import othello.api.OthelloBot;
import othello.utils.*;
import static othello.utils.GamePhase.*;

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
     * Moves made in the game
     */
    private int movesMade;
    /**
     * Current phase of the game
     */
    private GamePhase phase;

    /**
     * Initialize bot parameters.
     *
     * @param color the color this bot is playing in the game
     */
    @Override
    public void startGame(int color) {
        this.player = color;
        this.movesMade = 0;
        this.phase = OPENING;
    }

    /**
     * Computes optimal move with an iteratively deepening minimax.
     *
     * @param board state of game
     * @return array of move indices in form {row, col}
     */
    @Override
    public int[] makeMove(int[][] board) {
        movesMade++;
        if (movesMade == 10) {
            phase = MIDGAME;
        }
        
        if (movesMade == 20) {
            phase = ENDGAME;
        }
        
        int[] move = ProgressiveDeepening.findBestMove(board, player, false, phase);
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

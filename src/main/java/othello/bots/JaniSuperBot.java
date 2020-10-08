/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import othello.api.OthelloBot;
import othello.utils.TranspositionTable;

/**
 * The final bot using ab-pruning minimax + game phase dependent evaluation +
 * iterative deepening + a transposition table. Will hopefully be the most epic
 * superbot of 'em all.
 *
 * @author riikoro
 */
public class JaniSuperBot implements OthelloBot {

    /**
     * Color the bot plays in this game.
     */
    private int player;

    private TranspositionTable table;

    /**
     * Initialize transposition & player.
     *
     * @param player the color the bot plays in this game.
     */
    @Override
    public void startGame(int player) {
        this.player = player;
        table.generateZobristIdentifiers();
    }

    /**
     * Choose which move to make next.
     *
     * @param board current state of the game
     * @return move coordinates as {row, col} array
     */
    @Override
    public int[] makeMove(int[][] board) {
        table.clear();
        return new int[]{0, 0};
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

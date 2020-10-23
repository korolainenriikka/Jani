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

    private int movesMade;

    private GamePhase phase;

    /**
     * Initialize transposition & player.
     *
     * @param player the color the bot plays in this game.
     */
    @Override
    public void startGame(int player) {
        this.player = player;
        this.table = new TranspositionTable();
        TranspositionTable.generateZobristIdentifiers();
        this.movesMade = 0;
        this.phase = OPENING;
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
        movesMade++;
        if (movesMade == 10) {
            phase = MIDGAME;
        }

        if (movesMade == 20) {
            phase = ENDGAME;
        }

        int[] move = ProgressiveDeepening.findBestMove(board, player, false, phase, true, table);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import othello.api.OthelloBot;
import othello.utils.BoardUtils;

/**
 * A bot playing randomly.
 *
 * @author riikoro
 */
public class JaniRandom implements OthelloBot {
    /**
     * The color the bot plays in current game.
     */
    private int player;

    /**
     * Save the color this bot is playing in the game.
     *
     * @param color
     */
    @Override
    public void startGame(final int color) {
        this.player = color;
    }

    /**
     * Finds all possible moves and picks one randomly.
     * Tried to implement without arraylist but bot broke :(
     *
     * @param boardState current state of the game
     * @return move coordinates in form {row, col}
     */
    @Override
    public int[] makeMove(final int[][] boardState) {
        int[][] availableMoves = new int[25][2];
        int arrayIndex = 0;

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, boardState)) {
                    availableMoves[arrayIndex][0] = i;
                    availableMoves[arrayIndex][1] = j;
                    arrayIndex++;
                }
            }
        }

        int random = (int) (System.nanoTime() % arrayIndex);
        return availableMoves[random];
    }

    /**
     * Tells the game it is a bot playing; timeout etc implemented in core.
     *
     * @return
     */
    @Override
    public boolean isHuman() {
        return false;
    }
    
}

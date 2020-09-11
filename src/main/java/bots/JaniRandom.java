/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bots;

import coreinterfaces.OthelloBot;
import java.util.ArrayList;
import java.util.Random;
import utils.BoardUtils;

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
     *
     * @param boardState current state of the game
     * @return move coordinates in form {row, col}
     */
    @Override
    public int[] makeMove(final int[][] boardState) {
        ArrayList<int[]> availableMoves = new ArrayList<>();

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, boardState)) {
                    int[] move = {i, j};
                    availableMoves.add(move);
                }
            }
        }

        Random r = new Random();
        return availableMoves.get(r.nextInt(availableMoves.size()));
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

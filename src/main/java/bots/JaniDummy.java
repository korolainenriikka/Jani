package bots;

import utils.BoardUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * A dumb initial bot playing according to rules, mainly for utils testing.
 *
 * @author riikoro
 */
public class JaniDummy {

    private int player;

    /**
     * Initializes game, gets player number from cli
     *
     * @param player which player the bot plays
     */
    public void startGame(int player) {
        this.player = player;
    }

    /**
     * Dummy makemove method returns first allowed tile it wins, plays
     * top/left-most allowed
     *
     * @param board Game board from client
     */
    public int[] makeMove(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    int[] move = {i, j};
                    return move;
                }
            }
        }
        return null;
    }

    /**
     * Tells the game it is a bot playing; timeout etc implemented in core
     *
     * @return
     */
    public boolean isHuman() {
        return false;
    }
}

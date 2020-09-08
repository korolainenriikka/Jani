package bots;

import utils.BoardUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author riikoro
 */
public class JaniDummy {

    private int player;

    public void startGame(int player) {
        this.player = player;
    }

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
}

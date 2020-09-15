/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bots;

import coreinterfaces.OthelloBot;
import static coreinterfaces.Tile.BLACK;
import utils.AIUtils;
import utils.BoardUtils;

/**
 * This bot uses minimax and tries to get its pieces to as many as high-scored
 * tiles as possible
 *
 * @author riikoro
 */
public class JaniTileScorer implements OthelloBot {

    /**
     * The color this bot plays in the game.
     */
    private int player;
    /**
     * Whether the bot maximizes or minimizes scoring in the game (black = max)
     */
    private boolean maximize;

    @Override
    public void startGame(int color) {
        this.player = color;
        if (color == BLACK) {
            maximize = true;
        } else {
            maximize = false;
        }
    }

    @Override
    public int[] makeMove(int[][] board) {
        int[] move = new int[]{-1,-1};
        int bestScore = maximize ? -9999 : 9999;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    int score = AIUtils.minimax(board, player, 4);

                    if (maximize) {
                        if (score >= bestScore) {
                            int[] newBestMove = {i, j};
                            move = newBestMove;
                        }
                    } else {
                        if (score <= bestScore) {
                            int[] newBestMove = {i, j};
                            move = newBestMove;
                        }
                    }
                }
            }
        }
        return move;
    }
    
    // for empirical performance tests
    public int[] makeMoveCustomDepth(int[][] board, int depth) {
        int[] move = new int[2];
        int bestScore = maximize ? -9999 : 9999;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    int score = AIUtils.minimax(board, player, depth);

                    if (maximize) {
                        if (score > bestScore) {
                            int[] newBestMove = {i, j};
                            move = newBestMove;
                        }
                    } else {
                        if (score < bestScore) {
                            int[] newBestMove = {i, j};
                            move = newBestMove;
                        }
                    }
                }
            }
        }

        return move;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

}

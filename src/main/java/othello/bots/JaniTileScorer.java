/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import othello.api.OthelloBot;
import static othello.api.Tile.*;
import othello.utils.*;
import static othello.utils.GamePhase.*;

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
    /**
     * Enum value for changing minimax depth during the game
     */
    private GamePhase phase;
    /**
     * Count of moves made in the game for keeping track of phase
     */
    private int movesMade;

    /**
     * Initialize bot parameters.
     *
     * @param color the color this bot is playing in the game
     */
    @Override
    public void startGame(int color) {
        this.player = color;
        phase = OPENING;
        movesMade = 0;

        if (color == BLACK) {
            maximize = true;
        } else {
            maximize = false;
        }
    }

    /**
     * Decide the next move; depths empirically tested to approximate computation
     * of 1.0 sec, end computes until end of game.
     *
     * @param board state of game
     * @return array of move indices in form {row, col}
     */
    @Override
    public int[] makeMove(int[][] board) {
        long start = System.nanoTime();
        movesMade++;
        if (movesMade == 3) {
            phase = MIDGAME;
        } else if (movesMade == 27) {
            phase = ENDGAME;
        }
        
        
        int depth;
        int[] move = new int[]{-1, -1};
        int bestScore = maximize ? -9999 : 9999;
        
        switch (phase) {
            case OPENING:
                depth = 4;
                break;
            case MIDGAME:
                depth = 5;
                break;
            default:
                depth = 20;
                break;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    int score = AIUtils.minimax(board, player, depth);
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
                    
                    if ((System.nanoTime()-start) > 0.99) {
                        return move;
                    }
                }
            }
        }
        
        long end = System.nanoTime();
        if ((end-start)/1e9 > 1.0){
            System.out.println("Time limit exceeded");
        }
        return move;
    }

    /**
     * Try makeMove method with varying depths to time execution.
     *
     * @param board state of game
     * @param depth depth of minimax search
     * @return array of move indices in form {row, col}
     */
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

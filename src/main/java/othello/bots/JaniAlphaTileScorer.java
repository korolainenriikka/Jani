/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.bots;

import othello.api.OthelloBot;
import static othello.api.Tile.*;
import othello.utils.*;

/**
 * Same as tileScorer but implements alpha-beta pruning. Depths 2-3 smaller than
 * performance tests computed in 1 second, added buffer in case more branching
 * happens in the actual game.
 *
 * @author riikoro
 */
public class JaniAlphaTileScorer implements OthelloBot {

    /**
     * The color this bot plays in the game.
     */
    private int player;
    /**
     * Whether the bot maximizes or minimizes scoring in the game (black = max)
     */
    private boolean maximize;
    /**
     * Depth of minimax, altered throughout the game
     */
    private int depth;
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
        this.depth = 6;
        movesMade = 0;

        if (color == BLACK) {
            maximize = true;
        } else {
            maximize = false;
        }
    }

    /**
     * Decide the next move; depths empirically tested to approximate
     * computation of 1.0 sec, end computes until end of game.
     *
     * PROBLEM: checks time too rarely -> is timeouted too often PROB #2: no
     * top-level pruning
     *
     * @param board state of game
     * @return array of move indices in form {row, col}
     */
    @Override
    public int[] makeMove(int[][] board) {
        long start = System.nanoTime();
        updateGamePhase();
        int[] move = new int[2];

        double infty = Double.POSITIVE_INFINITY;
        double bestScore = maximize ? -1 * infty : infty;
        double a = -1 * infty;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    double score = Minimax.minimaxAlphaBeta(
                            board, player, depth, -1 * infty, infty);

                    if (maximize) {
                        if (score >= bestScore) {
                            move = new int[]{i, j};
                        }
                    } else {
                        if (score <= bestScore) {
                            move = new int[]{i, j};
                        }
                    }

                    if ((System.nanoTime() - start) > 0.95) {
                        return move;
                    }
                }
            }
        }
        return move;
    }

    private void updateGamePhase() {
        movesMade++;
        if (movesMade == 3) {
            depth = 7;
        } else if (movesMade == 27) {
            depth = 25;
        }
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
        double infty = Double.POSITIVE_INFINITY;
        double bestScore = maximize ? -1 * infty : infty;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (BoardUtils.isAllowed(i, j, player, board)) {
                    double score = Minimax.minimaxAlphaBeta(board, player, depth, -1 * infty, infty);

                    if (maximize) {
                        if (score > bestScore) {
                            move = new int[]{i, j};
                        }
                    } else {
                        if (score < bestScore) {
                            move = new int[]{i, j};
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.ui;

import othello.api.OthelloBot;
import static othello.api.Tile.*;

import othello.domain.Board;
import static othello.domain.Board.SIZE;

/**
 * Command-line UI for othello
 *
 * @author riikoro
 */
public class UI {

    /**
     * For running multiple bot v bot games in a row, no prints of individual
     * game situations
     *
     * @param bot1 player 1
     * @param bot2 player 2
     * @param numberOfGames number of games to be played
     */
    public static void tournament(OthelloBot bot1, OthelloBot bot2, int numberOfGames) {
        System.out.println("Executing tournament of " + numberOfGames + " games...");

        int winsBot1 = 0;
        int winsBot2 = 0;

        int i = 0;
        while (i < numberOfGames) {
            int winner = battle(bot1, bot2, false);
            if (winner == 1) {
                winsBot1++;
            } else if (winner == 2) {
                winsBot2++;
            }
            i++;
        }

        System.out.println("Tournament over, results:");
        System.out.println("Player 1: " + winsBot1 + " wins");
        System.out.println("Player 2: " + winsBot2 + " wins");
    }

    /**
     * Play an othello game.
     *
     * @param bot1 player 1
     * @param bot2 player 2
     * @param printsOn enable/disable prints of game situations / results
     * @return int representing winner of game
     */
    public static int battle(OthelloBot bot1, OthelloBot bot2, boolean printsOn) {
        Board board = new Board();
        int turn = 1;
        int winner = 0;

        OthelloBot[] contestants = new OthelloBot[]{bot1, bot2};
        int[] colors = new int[]{BLACK, WHITE};
        bot1.startGame(colors[0]);
        bot2.startGame(colors[1]);

        print("GAME STARTED", printsOn);
        while (!board.isGameOver()) {
            print("-------------------------------", printsOn);
            print(boardToString(board), printsOn);
            print("turn: " + colorToMark(colors[turn - 1]), printsOn);

            int opponent = turn == 1 ? 2 : 1;

            if (board.hasValidMovesLeft(turn)) {
                int[] move = contestants[turn - 1].makeMove(board.getAsArray());

                boolean moveValid = board.addMove(move[0], move[1], colors[turn - 1]);
                if (!moveValid && !contestants[turn - 1].isHuman()) {
                    print("INVALID MOVE - DISQUALIFIED", printsOn);
                    winner = opponent;
                    break;
                } else if (!moveValid && contestants[turn - 1].isHuman()) {
                    print("Invalid move, please try again", printsOn);
                    continue;
                }
            } else {
                print("No possible moves", printsOn);
            }

            turn = opponent;
        }

        print("GAME OVER", printsOn);
        print("WINNER: ", printsOn);

        if (winner == 0) {
            winner = board.winner();
            print(colorToMark(winner), printsOn);
            return winner;
        } else {
            print("" + winner, printsOn);
            return winner;
        }
    }

    /**
     * Print specified string if prints are enabled.
     *
     * @param stringToPrint String to be printed
     * @param printsEnabled prints enabled/disabled
     */
    public static void print(String stringToPrint, boolean printsEnabled) {
        if (printsEnabled) {
            System.out.println(stringToPrint);
        }
    }

    /**
     * String representing the board for printing, form: 
     * a b c d e f g h 
     * 1| | | | | | | | | 
     * 2| | | | | | | | | 
     * 3| | | | | | | | | 
     * 4| | | |○|#| | | | 
     * 5| | | |#|○| | | | 
     * 6| | | | | | | | | 
     * 7| | | | | | | | | 
     * 8| | | | | | | | |
     *
     * @param board state of the game
     * @return string representation
     */
    public static String boardToString(Board board) {
        String b = "  a b c d e f g h\n";
        for (int y = 0; y < SIZE; y++) {
            b += y + 1;
            for (int x = 0; x < SIZE; x++) {
                b += "|";
                if (board.getTile(x, y) == EMPTY) {
                    b += " ";
                } else {
                    b += colorToMark(board.getTile(x, y));
                }
            }
            b += "|\n";
        }
        return b;
    }

    /**
     * Converts integers in board to chars in print.
     *
     * @param color int representing piece color
     * @return Char, # = black, ○ = white
     */
    private static String colorToMark(int color) {
        if (color == BLACK) {
            return "#";
        } else {
            return "○";
        }
    }
}

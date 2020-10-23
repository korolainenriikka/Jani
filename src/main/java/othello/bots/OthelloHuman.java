package othello.bots;

import othello.api.OthelloBot;
import java.util.*;
import java.util.regex.Pattern;

/**
 * OthelloBot implementation for humans playing.
 *
 * @author riikoro
 */
public class OthelloHuman implements OthelloBot {

    public final boolean isHuman = true;
    Scanner scanner;

    /**
     * Initialize scanner for asking moves.
     */
    public OthelloHuman() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void startGame(int color) {
    }

    @Override
    public int[] makeMove(int[][] board) {
        while (true) {
            System.out.println("Insert move (format: a1): ");
            String move = scanner.nextLine();
            if (isInputFormatValid(move)) {
                int[] coordinates = parseInputToCoordinates(move);
                return coordinates;
            } else {
                System.out.println("Invalid move format");
            }
        }
    }

    /**
     * Checks that human inputs are valid tile indices.
     *
     * @param input input string
     * @return true = valid input
     */
    public boolean isInputFormatValid(String input) {
        return Pattern.matches("[a-h]{1}[1-8]{1}", input);
    }

    /**
     * Parses rightly formatted input to board array indices.
     *
     * @param input input String
     * @return indices as {row, col array}
     */
    public int[] parseInputToCoordinates(String input) {
        //a=10
        int[] coordinates = new int[2];
        coordinates[0] = Character.getNumericValue(input.charAt(0)) - 10;
        coordinates[1] = Character.getNumericValue(input.charAt(1)) - 1;
        return coordinates;
    }

    public boolean isHuman() {
        return isHuman;
    }
}

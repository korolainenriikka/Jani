package coreinterfaces;

public interface OthelloBot {

    /**
     * An game initialization method for the player.
     *
     * @param color the color the bot plays in game
     */
    void startGame(int color);

    /**
     * Method used to ask the bot for its next move.
     *
     * @param board current sate of the game
     * @return move indices as {row, col} array
     */
    int[] makeMove(int[][] board);

    /**
     * Whether the OthelloBot is human.
     *
     * @return if the bot implementation is human
     */
    boolean isHuman();
}

package othello.api;

public interface OthelloBot {

    /**
     * A method for bot initializing necessary parameters.
     *
     * @param color color the bot plays in the game
     */
    void startGame(int color);

    /**
     * For asking the bot for its next move in game.
     *
     * @param board current state of game
     * @return move as {row, col} array
     */
    int[] makeMove(int[][] board);

    /**
     * For human/bot-only features in UI.
     *
     * @return true = human
     */
    boolean isHuman();
}

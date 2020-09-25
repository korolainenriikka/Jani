package othello;

import org.junit.*;
import static org.junit.Assert.*;
import othello.domain.Board;
import static othello.api.Tile.*;

/**
 *
 * @author riikoro
 */
public class BoardTest {

    private Board openingBoard;
    private Board endOfGameBoard;

    @Before
    public void setUp() {
        int[][] boardWithOneMoveLeft = {
            {2, 2, 2, 2, 1, 2, 1, 1},
            {2, 2, 1, 2, 1, 2, 2, 2},
            {2, 1, 1, 1, 2, 2, 2, 2},
            {2, 1, 1, 1, 1, 1, 2, 2},
            {2, 2, 2, 1, 1, 1, 2, 2},
            {1, 1, 1, 1, 1, 1, 1, 2},
            {2, 2, 2, 2, 1, 1, 1, 2},
            {1, 1, 1, 1, 2, 2, 2, 0}
        };

        this.openingBoard = new Board();
        this.endOfGameBoard = new Board(boardWithOneMoveLeft);
    }

    @Test
    public void initialStateCorrect() {
        assertEquals(openingBoard.getTile(2, 3), EMPTY);
        assertEquals(openingBoard.getTile(3, 3), WHITE);
        assertEquals(openingBoard.getTile(4, 3), BLACK);
    }

    private boolean isBoardChanged(Board board1, Board board2) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board1.getTile(row, col) != board2.getTile(row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void openingMoveOnEdgeWontChangeBoard() {
        openingBoard.addMove(0, 0, WHITE);
        assertFalse(isBoardChanged(openingBoard, new Board()));
    }

    @Test
    public void openingMoveOnEdgeReturnsFalse() {
        assertFalse(openingBoard.addMove(0, 0, WHITE));
    }

    @Test
    public void noNeighboringOpponentWontChangeBoard() {
        openingBoard.addMove(2, 5, WHITE);
        assertFalse(isBoardChanged(openingBoard, new Board()));
    }

    @Test
    public void noNeighboringOpponentReturnsFalse() {
        assertFalse(openingBoard.addMove(2, 5, WHITE));
    }

    @Test
    public void rightOpeningMoveReturnsTrue() {
        assertTrue(openingBoard.addMove(3, 2, BLACK));
    }

    @Test
    public void takenTileMoveReturnsFalse() {
        openingBoard.addMove(3, 2, BLACK);
        assertFalse(openingBoard.addMove(3, 2, BLACK));
    }

    @Test
    public void outOfBoundsMoveReturnsFalse() {
        assertFalse(openingBoard.addMove(-1, -1, BLACK));
    }

    @Test
    public void addingMoveFlipsCorrectPieces() {
        int[][] initial = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 2, 0, 2, 0, 0},
            {0, 0, 0, 2, 2, 0, 0, 0},
            {0, 0, 1, 0, 2, 2, 1, 0},
            {0, 0, 2, 2, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        this.openingBoard = new Board(initial);
        openingBoard.addMove(4, 3, BLACK);

        int[][] expected = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 2, 2, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Board expectedBoard = new Board(expected);
        assert (openingBoard.equals(expectedBoard));
    }

    @Test
    public void simpleMoveIsGameOverDoesNotReturnError() {
        // This produced an ArrayIndexOutOfBounds error
        openingBoard.addMove(3, 2, BLACK);
        openingBoard.addMove(3, 1, WHITE);
        openingBoard.addMove(3, 0, BLACK);
        assertFalse(openingBoard.isGameOver());
    }

    @Test
    public void onePossibleMoveLeftGameNotOver() {
        assert (!endOfGameBoard.isGameOver());
    }

    @Test
    public void noPossibleMovesGameOver() {
        endOfGameBoard.addMove(7, 7, BLACK);
        assert (endOfGameBoard.isGameOver());
    }

    @Test
    public void onlyOnePlayerHasMovesLeftHasMoveLeftRecognizes() {
        //bug case from before, problem in ui
        int[][] initial = {
            {2, 2, 2, 0, 0, 0, 0, 0},
            {2, 2, 1, 0, 0, 0, 0, 0},
            {2, 1, 1, 1, 0, 0, 0, 0},
            {2, 1, 1, 1, 1, 0, 0, 0},
            {2, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Board initialBoard = new Board(initial);
        assert (initialBoard.hasValidMovesLeft(WHITE));
        assert (!initialBoard.hasValidMovesLeft(BLACK));
    }

    @Test
    public void theOneWithMorePiecesAtEndOfGameWins() {
        endOfGameBoard.addMove(7, 7, BLACK);
        assertEquals(BLACK, endOfGameBoard.winner());
    }

}

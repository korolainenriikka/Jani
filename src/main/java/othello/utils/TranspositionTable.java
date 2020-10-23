/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;
import static othello.utils.EntryType.*;

/**
 * Data structure for storing data on the game thus far; table containing
 * entries with the data received from previous searched.
 *
 * @author riikoro
 */
public class TranspositionTable {

    /**
     * Unique numbers representing a piece in a tile. index 0: black in [0][0],
     * 1: white in [0][0], 2: black in [0][1]...
     */
    private static long[] pieceIdentifiers;
    /**
     * The actual table, not implemented yet
     */
    private TableEntry[] transpositionTable;

    private static final int BOARD_SIZE = 8;

    /**
     * Empirically tested to work best.
     */
    private static final int TABLE_SIZE = 600000;

    /**
     * Create new transposition table.
     */
    public TranspositionTable() {
        this.transpositionTable = new TableEntry[TABLE_SIZE];
    }

    /**
     * Generated unique 64-bit numbers for each piece in a tile.
     */
    public static void generateZobristIdentifiers() {
        pieceIdentifiers = new long[128];
        for (int i = 0; i < pieceIdentifiers.length; i++) {
            pieceIdentifiers[i] = generateRandom64bLong();
        }
    }

    private static long generateRandom64bLong() {
        long randDivider = System.nanoTime() % 10 + 1;
        long rand = System.nanoTime() / randDivider;
        return rand;
    }

    /**
     * Generates Zobrist hash for a given board state. Hash = xor of all piece
     * in tile -identifiers
     *
     * @param board state of the game
     * @return hash code of the board state
     */
    public static long hashCode(int[][] board) {
        long hashCode = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                //tile number calculated left to right & top to bottom: i*8+j
                //black -> number*2, white: number*2+1
                if (board[i][j] == BLACK) {
                    hashCode ^= pieceIdentifiers[2 * (i * 8 + j)];
                }

                if (board[i][j] == WHITE) {
                    hashCode ^= pieceIdentifiers[2 * (i * 8 + j) + 1];
                }
            }
        }
        return hashCode;
    }

    /**
     * Empty the table between searches.
     */
    public void clear() {
        this.transpositionTable = new TableEntry[TABLE_SIZE];
    }

    /**
     * Add a new entry to the transposition table.
     *
     * @param e entry to add to table.
     */
    public void add(TableEntry e) {
        int tableLocation = (int) (e.getHashCode() % TABLE_SIZE);

        if (transpositionTable[tableLocation] == null) {
            transpositionTable[tableLocation] = e;
        } else {
            TableEntry entryInTable = transpositionTable[tableLocation];

            // exact replaces a/b, deeper replaces
            if ((e.getDepth() >= entryInTable.getDepth())
                    || (e.getType() == EXACT && entryInTable.getType() != EXACT)) {
                transpositionTable[tableLocation] = e;
            }
        }
    }

    /**
     * To check if any given board state has data in transposition table associated with it.
     * 
     * @param gameState board state to search
     * @return true if table has data
     */
    public boolean hasAssociatedData(int[][] gameState) {
        long hashCode = hashCode(gameState);
        int location = (int) (hashCode % TABLE_SIZE);
        if (transpositionTable[location] != null && transpositionTable[location].getHashCode() == hashCode) {
            return true;
        }
        return false;
    }

    /**
     * Get data associated with the searched game state.
     *
     * @param gameState state of the game searched.
     * @return associated tableEntry
     */
    public TableEntry get(int[][] gameState) {
        long hashCode = hashCode(gameState);
        int tableLocation = (int) (hashCode % TABLE_SIZE);
        if (transpositionTable[tableLocation].getHashCode() == hashCode) {
            return transpositionTable[tableLocation];
        }
        // if no data present, run hasAssociatedData before get to avoid
        return new TableEntry(new int[1][1], 0, ALPHA, 0, new int[1]);
    }
}


/*
ASIOITA JOITA PITTÄÄ TESTAILLA
- zobristit uniikkeja
- kaks deepeq lautaa hascodee samal taval
- clearin jälkee joka paikke on null
- add lisää ja voidaa hakee getil
- add lisää diipimmän ja voidaa hakee getil
*/
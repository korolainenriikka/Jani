/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static othello.api.Tile.*;

/**
 * Transposition table parts and pieces, trials n errors :) Currently working on
 * this unfamiliar ground, might become something useful might not.
 *
 * @author riikoro
 */
public class TranspositionTable {

    /**
     * Unique numbers representing a piece in a tile. index 0: black in [0][0],
     * 1: white in [0][0], 2: black in [0][1]...
     */
    private long[] pieceIdentifiers;
    /**
     * The actual table, not implemented yet
     */
    private TableEntry[] transpositionTable;

    private static final int BOARD_SIZE = 8;
    
    /**
     * Currently an arbitrary number, should be optimized
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
    public void generateZobristIdentifiers() {
        pieceIdentifiers = new long[128];
        for (int i = 0; i < pieceIdentifiers.length; i++) {
            pieceIdentifiers[i] = generateRandom64bLong();
        }
    }

    private long generateRandom64bLong() {
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
    public long hashCode(int[][] board) {
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
     * For efficient comparison-based hashing. Todo.
     * 
     * @param newBoard board state to be hashed
     * @param prevBoard previous board state
     * @param prevHash previous boards' hash
     * @return hash code of the new game state
     */
    public long hashCode(int[][] newBoard, int[][] prevBoard, long prevHash) {
        // find changed tiles-> xor old identifier & old indentifier
        // requires new board util
        return 1L;
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
     * TODO: add replacement policy; current: newer replaces older
     * one possibility: "replace if same depth or deeper"
     *
     * @param e entry to add to table.
     */
    public void add(TableEntry e) {
        int tableLocation = (int) e.getHashCode() % TABLE_SIZE;
        transpositionTable[tableLocation] = e;
    }

    /**
     * Get data associated with the searched game state. Returns null if table
     * has no associated data.
     * 
     * @param gameState state of the game searched.
     * @return associated tableEntry
     */
    public TableEntry get(int[][] gameState) {
        long hashCode = hashCode(gameState);
        int tableLocation = (int) hashCode % TABLE_SIZE;
        if (transpositionTable[tableLocation].getHashCode() == hashCode) {
            return transpositionTable[tableLocation];
        }
        return null;
    }
}

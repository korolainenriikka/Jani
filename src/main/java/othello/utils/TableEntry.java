/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

/**
 * Transposition table entry.
 * 
 * @author riikoro
 */
public class TableEntry {

    /**
     * Exact state hash for checking the right entry found
     */
    private long hashCode;
    /**
     * Computation depth at point of data storing
     */
    private int depth;
    /**
     * 0 = exact hash, 1 = alpha hash (exact value less than this) 2 = beta hash
     * (exact value more than this)
     */
    private int type;
    /**
     * Evaluation value of the position
     */
    private int value;
    /**
     * Best evaluated next move in this position
     */
    private int[] bestMove;

    /**
     * Initialize new entry.
     * 
     * @param hashCode full zobrist hash
     * @param depth depth of computation
     * @param type a, b or exact
     * @param bestMove best move at this stage
     */
    public TableEntry(long hashCode, int depth, int type, int[] bestMove) {
        this.hashCode = hashCode;
        this.depth = depth;
        this.type = type;
        this.bestMove = bestMove;
    }

    /**
     * Returns the exact hash value of this game state.
     * 
     * @return zobrist hash of the game state
     */
    public long getHashCode() {
        return hashCode;
    }

}

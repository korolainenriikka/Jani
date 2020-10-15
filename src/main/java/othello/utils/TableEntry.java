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
     * EXACT / ALPHA / BETA = the score at computed depth exactly / at least / less than the stored score
     * CHECK THIS
     */
    private EntryType type;
    /**
     * Evaluation value of the position
     */
    private int minimaxScore;
    /**
     * Best evaluated next move in this position
     */
    private int[] bestMove;

    /**
     * Initialize new entry.
     * 
     * @param depth depth of computation
     * @param type a, b or exact
     * @param bestMove best move at this stage
     */
    public TableEntry(int[][] board, int depth, EntryType type, int value, int[] bestMove) {
        this.hashCode = TranspositionTable.hashCode(board);
        this.depth = depth;
        this.type = type;
        this.minimaxScore = value;
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

    /**
     * Get depth param.
     * 
     * @return depth param
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Get entry type param.
     * 
     * @return depth param
     */
    public EntryType getType() {
        return type;
    }

    /**
     * Get minimaxScore param.
     * 
     * @return depth param
     */
    public int getMinimaxScore() {
        return minimaxScore;
    }

    /**
     * Get best move param.
     * 
     * @return depth param
     */
    public int[] getBestMove() {
        return bestMove;
    }
    
    

}

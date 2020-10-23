/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static othello.api.Tile.*;
import static othello.utils.EntryType.EXACT;

/**
 *
 * @author riikoro
 */
public class TranspositionTableTest {

    private TranspositionTable table;
    
    private int[][] openingBoard;
    
    @Before
    public void setUp() {
        this.table = new TranspositionTable();
        TranspositionTable.generateZobristIdentifiers();
        
        openingBoard = new int[8][8];
        openingBoard[3][3] = BLACK;
        openingBoard[3][4] = WHITE;
        openingBoard[4][3] = WHITE;
        openingBoard[4][4] = BLACK; 
    }
    
    @Test
    public void twoEqualBoardsHaveSameHashCode() { 
        int[][] board2 = new int[8][8];
        board2[3][3] = BLACK;
        board2[3][4] = WHITE;
        board2[4][3] = WHITE;
        board2[4][4] = BLACK;
        
        assertEquals(TranspositionTable.hashCode(openingBoard),
                TranspositionTable.hashCode(board2));
    }
    
    @Test
    public void tableIsEmptyAfterClear() {
        TableEntry e = new TableEntry(openingBoard, 4, EXACT, 1, new int[]{2,3});
        table.add(e);
        table.clear();
        assert(!table.hasAssociatedData(openingBoard));
    }
    
    @Test
    public void entryAddedIsFoundInTable() {
        TableEntry e = new TableEntry(openingBoard, 4, EXACT, 1, new int[]{2,3});
        table.add(e);
        assert(table.hasAssociatedData(openingBoard));
    }
    
    @Test
    public void replacingEntryIsFoundInTable() {
        TableEntry e1 = new TableEntry(openingBoard, 4, EXACT, 1, new int[]{2,3});
        table.add(e1);
        TableEntry e2 = new TableEntry(openingBoard, 5, EXACT, 100, new int[]{2,3});
        table.add(e2);
        assertEquals(100, table.get(openingBoard).getMinimaxScore());
    }
}

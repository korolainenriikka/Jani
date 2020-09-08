
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author riikoro
 */
public class JaniDummy {
    
    private int player;
    private int opponent;
    int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    int[][] board;
    
    public void startGame(int player){
        this.player = player;
        this.opponent = player == 1 ? 2 : 1;
    }
    
    public int[] makeMove(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(isAllowed(i,j)){
                    int [] move = {i,j};
                    return move;
                }
            }
        }
        return null;
    }
    
    public boolean isAllowed(int x, int y){
        //clockwise from top-left:
        for (int i = 0; i < 8; i++) {
            int nextrow = x + directions[i][0];
            int nextcol = y + directions[i][1];
            if (withinBoard(nextrow, nextcol) && board[nextrow][nextcol] == opponent) {
                while (withinBoard(nextrow, nextcol)) {
                    nextrow = nextrow + directions[i][0];
                    nextcol = nextcol + directions[i][1];
                    if (board[nextrow][nextcol] == opponent) {
                        continue;
                    } else if (board[nextrow][nextcol] == player) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }     
        return false;
    }
    
    private boolean withinBoard(int row, int col) {
        if (row >= 0 && col >= 0 && row < board.length && col < board.length) {
            return true;
        }
        return false;
    } 
}

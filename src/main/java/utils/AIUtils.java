/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Algorithm utilities: minimax, evaluating function (multiple if needed).
 *
 * @author riikoro
 */
public class AIUtils {

    /**
     * Algorithm for trying various game states and predicting their outcomes.
     *
     * @return 1 for now
     */
    public static int minimax() {
        //minimax implementation here
        return 1;
    }

    /**
     * First evaluating function returns 1, 0 or -1 and only evaluates if game
     * is over.
     *
     * @param board
     */
    public static void dummyEvaluator(final int[][] board) {
        //evaluating function for win/not
        //calls boardutils.winner (return: 1 win -1 lose 0 none), returns value
    }

    /*OTHER EVALUATORS:
    - utils: position / mobility / ... score calculator
    - board state calculator: sum position + mobility etc
     */
}

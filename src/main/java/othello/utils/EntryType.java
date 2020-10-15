/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

/**
 * Transposition table entry types.
 * ALPHA = lower bound, BETA = upper bound
 * 
 * @author riikoro
 */
public enum EntryType {
    EXACT, ALPHA, BETA
}

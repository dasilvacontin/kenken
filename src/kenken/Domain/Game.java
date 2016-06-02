/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author dasilvacontin
 */
public class Game extends DomainBase {
    private String player;
    private int lastPlayed;
    private Board board;
    private int[][] playerSolution;
    
    public Game(String player, Board board) {
        this.player = player;
        this.board = board;
    }
    
    /**
     * @return the player
     */
    public String getPlayer() {
        return player;
    }
    
    /**
     * @param player the player to set
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * @return the lastPlayed
     */
    public int getLastPlayed() {
        return lastPlayed;
    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}

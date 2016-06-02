/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author dasilvacontin
 */
public class Board extends DomainBase {
    private String designer;
    final private int size;
    private int[][] solution;
    private List<Region> regions;
    
    public Board(String designer, int size) {
        this.designer = designer;
        this.size = size;
    }
    
    @Override
    public String serialize() {
        String s = super.serialize() + " " + designer + " " + size;
        
        // serialize mat
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i == 0 && j == 0) {
                    s += " ";
                } else {
                    s += ",";
                }
                s += solution[i][j];
            }
        }
        
        // serialize region list
        /*
        s += " " + regions.size();
        for (Region r : regions) {
            s += " " + r.serialize();
        }
        */
        
        return s;
    }
    
    private Boolean isValidSquare(int i, int j) {
        return (i >= 0 && i < getSize() && j >= 0 && j < getSize());
    }
    
    public int getSquareSolution(int i, int j) {
        if (!isValidSquare(i, j)) {
            return -1;
        }
        return solution[i][j];
    }
    
    public int getSquareSolution(Square s) {
        return getSquareSolution(s.i, s.j);
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the designer
     */
    public String getDesigner() {
        return designer;
    }
    
    void validate() throws Exception {
        if (size < 3 || size > 6) {
            throw new Exception("Board size must be between 3 and 6.");
        }
    }
        
    private boolean generateRandomBoardStep(List<List<Integer>> rows,
            List<List<Integer>> columns, int i, int j) {
        if (j >= size) {
            j = 0;
            ++i;
            if (i >= size) {
                return true; // was able to place all nums in board
            }
        }
        List<Integer> rowLeft = rows.get(i);
        List<Integer> columnLeft = columns.get(j);
        boolean success;
        ListIterator<Integer> it = rowLeft.listIterator();
        Integer n;
        int ci;
        int ri = 0;
        while (it.hasNext()) {
            n = it.next();
            if ((ci = columnLeft.indexOf(n)) > -1) {
                // place n in board
                solution[i][j] = n;
                // remove from posibility lists
                it.remove();
                columnLeft.remove(ci);
                // call recursive
                success = generateRandomBoardStep(rows, columns, i, j + 1);
                if (success) {
                    return true;
                }
                // undo
                // this sucks, concurrent modification *sigh*
                it = rowLeft.listIterator(ri); 
                it.add(n);
                columnLeft.add(n);
                // no need to undo player solution square, will get overwritten
                ++ri;
            }
        }
        return false; // no valid boards were found
    }
    
    public void generateRandomBoard() {
        List<List<Integer>> rows = new ArrayList<>();
        List<List<Integer>> columns = new ArrayList<>();
        
        // generate remaining possibilities for row/column
        for (int i = 0; i < size; ++i) {
            List<Integer> row = new LinkedList<>();
            List<Integer> column = new LinkedList<>();
            for (int j = 0; j < size; ++j) {
                int n = j + 1;
                row.add(n);
                column.add(n);
            }
            rows.add(row);
            columns.add(column);
        }
        
        // reset solution, squares get initialized to 0
        solution = new int[size][size];
        
        // kick of recursivity
        boolean success = generateRandomBoardStep(rows, columns, 0, 0);
        System.out.println("success: " + success);
        for (int i = 0; i < size; ++i) {
            System.out.println(Arrays.toString(solution[i]));
        }
    }
}

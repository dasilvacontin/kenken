/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

/**
 *
 * @author dasilvacontin
 */
public class Square {
    final public int i;
    final public int j;
    
    public Square(int i, int j) {
        this.i = i;
        this.j = j;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Square))
            return false;
        Square other = (Square) obj;
        return i == other.i && j == other.j;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.i;
        hash = 53 * hash + this.j;
        return hash;
    }
}

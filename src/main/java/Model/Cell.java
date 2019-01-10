package Model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int col;
    private int row;
    private int value;
    private List<Integer> possibilities;

    public Cell(int row, int col, int value) {
        this.col = col;
        this.row = row;
        this.value = value;
        this.possibilities = new ArrayList<>();
    }

    public List<Integer> getPossibilities() {
        return possibilities;
    }

    public void addPossibility(int possibility){
        this.possibilities.add(possibility);
    }

    public boolean hasOnePossibility(){
        return this.possibilities.size() == 1;
    }

    public int returnResult(){
        return this.possibilities.get(0);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

package Model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int col;
    private int row;
    private int value;
    private List<Integer> possibilities;

    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.possibilities = new ArrayList<>();
    }

    Cell(Cell cell) {
        this.col = cell.getCol();
        this.row = cell.getRow();
        this.value = cell.getValue();
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

public class Cell {
    private int col;
    private int row;
    private int value;

    public Cell(int row, int col, int value) {
        this.col = col;
        this.row = row;
        this.value = value;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

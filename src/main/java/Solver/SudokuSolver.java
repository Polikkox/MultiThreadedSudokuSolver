package Solver;
import Model.Cell;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver   {
    private Cell[][] cells;
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    private SudokuSolver(Cell[][] cells, Cell cell, int value) {
        deepCopy(cells);
        setNewBoard(cell, value);
    }

    private void deepCopy(Cell[][] cells){
        Cell[][] celltemp = new Cell[9][9];
        for(int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                celltemp[row][col] = new Cell(cells[row][col].getRow(), cells[row][col].getCol(), cells[row][col].getValue());
            }
        }
        this.cells = celltemp;
    }

    SudokuSolver(Cell[][] cells){
        this.cells = cells;
    }

//    @Override
//    public void run() {
//        solve();
//    }

    boolean trySolve(){

            for(int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {

                    if (cells[row][col].getValue() == EMPTY) {
                        cells[row][col].getPossibilities().clear();
                        for (int number = 1; number <= SIZE; number++) {
                            if (checkConstrains(row, col, number)) {
                                cells[row][col].addPossibility(number);
                            }
                        }
                        if(cells[row][col].hasOnePossibility()){
                            cells[row][col].setValue(cells[row][col].returnResult());
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    List<SudokuSolver> getNewSudokuSolvers(Cell cell){
        List<SudokuSolver> newSudokuSolvers = new ArrayList<>();
        SudokuSolver newSS;
        for (int val: cell.getPossibilities()) {
            newSS = new SudokuSolver(this.cells, cell, val);
            newSudokuSolvers.add(newSS);
        }
        return newSudokuSolvers;
    }

    private void setNewBoard(Cell cell, int val){
        this.cells[cell.getRow()] [cell.getCol()].setValue(val);

    }


    Cell getCellWithMinPossibilities() {
        Cell cell = null;
        int minPossibilities = 9;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if(cells[row][col].getValue() == EMPTY){
                    if(cells[row][col].getPossibilities().size() < minPossibilities){
                        cell = cells[row][col];
                        minPossibilities = cell.getPossibilities().size();
                    }
                }

            }
        }
        return cell;
    }

    boolean isSudokuSolved() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if(cells[row][col].getValue() == EMPTY){
                    return false;
                }
            }

        }
        return true;
    }


    private boolean checkConstrains(int row, int col, int number) {
        return !isNumberInRow(row, number) && !isNumberInColumn(col, number) && !isNumberInSquare(row, col, number);

    }

    private boolean isNumberInSquare(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;


        for(int i = r; i < r + 3; i++){
            for(int j = c; j < c + 3; j++){
                if(cells[i][j].getValue() == number){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNumberInColumn(int col, int number) {
        for(int i = 0; i < SIZE; i++){
            if(cells[i][col].getValue() == number){
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInRow(int row, int number){

        for(int i = 0; i < SIZE; i++){
            if(cells[row][i].getValue() == number){
                return true;
            }
        }
        return false;
    }

    void display() {
        int rowCounter = 0;
        int colCounter = 0;
        System.out.println("\n-------------------------");
        for(int row = 0; row < SIZE; row++){
            System.out.print("| ");
            for(int col = 0; col < SIZE; col++){
                System.out.print(cells[row][col].getValue() + " ");
                if(rowCounter == 2){
                    System.out.print("| ");
                    rowCounter = -1;
                }
                rowCounter++;

            }
            if(colCounter == 2){
                System.out.println("\n-------------------------");
                colCounter = -1;
            }
            colCounter++;
            System.out.println();
        }
    }
}

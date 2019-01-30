package Solver;
import Model.Cell;
import Model.Sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver implements Runnable  {
    private static final int BOARD_SIZE = 9;
    private static final int NO_VALUE = 0;
    private Sudoku sudoku;

    public SudokuSolver(Sudoku sudoku){
        this.sudoku = sudoku;
        new Thread(this).start();
    }

    private SudokuSolver(Sudoku oldSudoku, Cell cell, int value) {
        this.sudoku = new Sudoku();
        this.sudoku.copyCellList(oldSudoku.getCellList());
        changeOneCell(cell, value);
        new Thread(this).start();
    }

    Sudoku getSudoku() {
        return sudoku;
    }

    @Override
    public void run() {
        while(isNotSolved()) {
            
        }
    }

    private boolean isNotSolved() {
        boolean isSolved = false;
        if(isSudokuSolved()){
            isSolved = true;
        }
        return isSolved;

    }

    boolean trySolve(){
        List<Cell> cellList = sudoku.getCellList();
            for(Cell c: cellList) {
                if (c.getValue() == NO_VALUE) {
                    c.getPossibilities().clear();
                    for (int number = 1; number <= BOARD_SIZE; number++) {
                        if (checkConstrains(c.getRow(), c.getCol(), number)) {
                            c.addPossibility(number);
                        }
                    }
                    if(c.hasOnePossibility()){
                        c.setValue(c.returnResult());
                        return true;
                    }
                }
            }
        return false;
    }

    List<SudokuSolver> getNewSudokuSolvers(Cell cell){
        List<SudokuSolver> newSudokuSolvers = new ArrayList<>();
        SudokuSolver newSS;
        for (int val: cell.getPossibilities()) {
            newSS = new SudokuSolver(this.sudoku, cell, val);
            newSudokuSolvers.add(newSS);
        }
        return newSudokuSolvers;
    }

    private void changeOneCell(Cell cell, int newValue){
        this.sudoku.changeValueOfCell(cell, newValue);
    }
    Cell getCellWithMinPossibilities() {
        Cell cell = null;
        List<Cell> cellList = sudoku.getCellList();

        int minPossibilities = 9;
        for (Cell c : cellList) {
                if(c.getValue() == NO_VALUE){
                    if(c.getPossibilities().size() < minPossibilities){
                        cell = c;
                        minPossibilities = cell.getPossibilities().size();
                    }
                }
        }
        return cell;
    }

    boolean isSudokuSolved() {
        return sudoku.getCellList().stream().noneMatch(c -> c.getValue() == NO_VALUE);
    }

    private boolean checkConstrains(int row, int col, int number) {
        return !isNumberInRow(row, number) && !isNumberInColumn(col, number) && !isNumberInSquare(row, col, number);

    }

    private boolean isNumberInSquare(int row, int col, int number) {
        int startRow = row - row % 3;
        int startColumn = col - col % 3;
        return sudoku.getCellList().stream().
                filter(c -> c.getRow() == startRow || c.getRow() == startRow + 1 || c.getRow() == startRow + 2).
                filter(c -> c.getCol() == startColumn || c.getCol() == startColumn + 1 || c.getCol() == startColumn + 2).
                anyMatch(c -> c.getValue() == number);
    }

    private boolean isNumberInColumn(int col, int number) {
        return sudoku.getCellList().stream().
                filter(c ->c.getCol() == col).
                anyMatch(c -> c.getValue() == number);
    }

    private boolean isNumberInRow(int row, int number) {
        return sudoku.getCellList().stream().
                filter(c -> c.getRow() == row).
                anyMatch(c -> c.getValue() == number);
    }
}

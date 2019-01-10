package Model;

import DAO.CSVReader;
import Solver.SudokuSolver;

public class Sudoku {
    private Cell[][] cells;


    public Cell[][] getCells() {
        return cells;
    }

    public Sudoku(Cell[][] cells) {
        this.cells = cells;
    }

    public Sudoku() {
        run();
    }



    public void run(){
        String csvFile = "src/main/resources/hardestSudoku.csv";
        CSVReader csvReader = new CSVReader();
        cells = csvReader.openCSV(csvFile);
    }
}

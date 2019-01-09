package Model;

import DAO.CSVReader;
import Solver.SudokuSolver;

public class Sudoku {

    public void run(){
        String csvFile = "src/main/resources/hardestSudoku.csv";
        CSVReader csvReader = new CSVReader();
        Cell[][] board = csvReader.openCSV(csvFile);

        SudokuSolver sudokuSolver = new SudokuSolver(board);
        sudokuSolver.solve();
        sudokuSolver.display();
    }
}

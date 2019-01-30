import Model.Sudoku;
import Solver.SudokuService;
import Solver.SudokuSolver;

public class Main {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Sudoku sudoku = new Sudoku("src/main/resources/hardestSudoku.csv");
        SudokuSolver firstSolver = new SudokuSolver(sudoku);
        SudokuService manager = new SudokuService();
        manager.findSolution(firstSolver);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println(estimatedTime + " miliseconds");
    }
}

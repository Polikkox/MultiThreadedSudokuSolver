

public class Sudoku {

    public void run(){
        String csvFile = "src/main/resources/sudoku.csv";
        CSVReader csvReader = new CSVReader();
        Cell[][] board = csvReader.openCSV(csvFile);

        SudokuSolver sudokuSolver = new SudokuSolver(board);
        if(sudokuSolver.solve()){
            System.out.println("Sudoku solved");
//            sudokuSolver.display();

        }
        else{
            System.out.println("Sudoku cannot be solved");
        }
    }
}

import Model.Sudoku;

public class Main {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        Sudoku sudoku = new Sudoku();
        sudoku.run();

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime /  1000000000.0 + " seconds");
    }
}

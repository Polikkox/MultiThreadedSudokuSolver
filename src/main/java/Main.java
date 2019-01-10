import Solver.SudokuManager;

public class Main {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        SudokuManager s = new SudokuManager();
        s.findSolution();

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println(estimatedTime + " miliseconds");
    }
}



public class SudokuSolver {
    private Cell[][] cells;
    private final int SIZE = 9;
    private final int EMPTY = 0;
    private static boolean[] valid;

    public SudokuSolver(Cell[][] cells){
        this.cells = cells;
    }

    public boolean solve() {

        for(int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (cells[row][col].getValue() == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (checkConstrains(row, col, number)) {
                            cells[row][col].setValue(number);
                            if (solve()) {
                                return true;
                            } else {
                                cells[row][col].setValue(EMPTY);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkConstrains(int row, int col, int number) {
        valid = new boolean[27];
        int threadIndex = 0;
//        threads[threadIndex++] = new Thread(new Is3x3Valid(i, j));
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

//    public void display() {
//        int rowCounter = 0;
//        int colCounter = 0;
//        System.out.println("\n-------------------------");
//        for(int row = 0; row < SIZE; row++){
//            System.out.print("| ");
//            for(int col = 0; col < SIZE; col++){
//                System.out.print(cells[row][col].getValue() + " ");
//                if(rowCounter == 2){
//                    System.out.print("| ");
//                    rowCounter = -1;
//                }
//                rowCounter++;
//
//            }
//            if(colCounter == 2){
//                System.out.println("\n-------------------------");
//                colCounter = -1;
//            }
//            colCounter++;
//            System.out.println();
//        }
//    }
}

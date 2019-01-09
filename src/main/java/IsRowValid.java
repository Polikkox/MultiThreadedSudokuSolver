public class IsRowValid implements Runnable{
    private final int SIZE = 9;
    private final int EMPTY = 0;
    private Cell[][] cells;
    private int row;
    private int number;
    private boolean flag;

    public IsRowValid(Cell[][] cells, int row, int number){
        this.cells = cells;
        this.row = row;
        this.number = number;
    }

    @Override
    public void run() {

        for(int i = 0; i < SIZE; i++){
            if(cells[row][i].getValue() == number){
                flag = true;
                return;
            }
        }
        flag = false;
    }

    public boolean getFlag(){
        return this.flag;
    }
}

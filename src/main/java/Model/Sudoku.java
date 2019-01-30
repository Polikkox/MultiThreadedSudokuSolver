package Model;

import DAO.CSVReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sudoku {
    private List<Cell> cellList = new ArrayList<>();


    public Sudoku(String filePath) {
        setCellListFromFile(filePath);
    }

    public Sudoku() {
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    private void setCellListFromFile(String filePath){
        CSVReader csvReader = new CSVReader();
        Cell[][] cellBoard = csvReader.openCSV(filePath);
        setCellList(cellBoard);
    }

    private void setCellList(Cell[][] cellBoard){
        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cellList.add(new Cell(row, col, cellBoard[row][col].getValue()));
            }
        }
    }

    public void copyCellList(List<Cell> originalCellList){
        originalCellList.forEach(c -> this.cellList.add(new Cell(c)));
    }

    public void changeValueOfCell(Cell cell, int newValue){
        cellList.stream().filter(c -> c.getRow() == cell.getRow() && c.getCol() == cell.getCol()).forEach(c -> c.setValue(newValue));
    }

    public void display() {
        int rowCounter = 0;
        int colCounter = 0;
        System.out.println("\n-------------------------");
        for(int row = 0; row < 9; row++){
            System.out.print("| ");
            for(int col = 0; col < 9; col++){
                System.out.print(getCell(row, col).getValue() + " ");
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

    private Cell getCell(int row, int col){
        return this.cellList.stream().filter(c -> c.getRow() == row && c.getCol() == col).collect(Collectors.toList()).get(0);
    }
}

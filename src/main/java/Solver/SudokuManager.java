package Solver;

import DAO.CSVReader;
import Model.Cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SudokuManager {
    private List<SudokuSolver> solutions = new ArrayList<>();

    public void findSolution(){

        String csvFile = "src/main/resources/hardestSudoku.csv";
        CSVReader csvReader = new CSVReader();
        Cell[][] board = csvReader.openCSV(csvFile);

        SudokuSolver mainSudoku = new SudokuSolver(board);
        solutions.add(mainSudoku);
        List<SudokuSolver> newSolversToAdd = new ArrayList<>();
        boolean isAnySoulution = true;
        while(isAnySoulution){

            solutions.addAll(newSolversToAdd);
            newSolversToAdd.clear();
            Iterator<SudokuSolver> iterator = solutions.iterator();
            while(iterator.hasNext()){
                SudokuSolver solver = iterator.next();
                while(solver.trySolve()){
                }
                if(solver.isSudokuSolved()){
                    solver.display();
                    isAnySoulution = false;
                }
                else{
                    Cell cellMin = solver.getCellWithMinPossibilities();
                    if(cellMin.getPossibilities().size() == 0){
                        iterator.remove();
                    }
                    else if(cellMin.getPossibilities().size() > 0){
                        newSolversToAdd.addAll(solver.getNewSudokuSolvers(cellMin));
                        iterator.remove();
                    }
                }
            }
        }
    }
}

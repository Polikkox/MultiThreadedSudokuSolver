package Solver;
import Model.Cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SudokuService {
    private List<SudokuSolver> solutions = new ArrayList<>();
    private static int counter = 1;

    public SudokuSolver findSolution(SudokuSolver firstSolver){

        List<SudokuSolver> newSolversToAdd = createFirstSolver(firstSolver);
        boolean isAnySolution = true;
        while(isAnySolution){
            Iterator<SudokuSolver> iterator = setCurrentPossibleSolutions(newSolversToAdd);



            while(iterator.hasNext()){
                SudokuSolver solver = iterator.next();
                while(solver.trySolve()){
                }
                if(solver.isSudokuSolved()){
                    solver.getSudoku().display();
                    System.out.println("Number of threads: " + counter);
                    return solver;
                }
                else{
                    Cell cellMin = solver.getCellWithMinPossibilities();
                    if(cellMin.getPossibilities().size() == 0){
                        iterator.remove();
                    }
                    else if(cellMin.getPossibilities().size() > 0){
                        runNewThreads(newSolversToAdd, iterator, solver, cellMin);
                    }
                }
            }
        }
        return null;
    }

    private Iterator<SudokuSolver> setCurrentPossibleSolutions(List<SudokuSolver> newSolversToAdd) {
        solutions.addAll(newSolversToAdd);
        newSolversToAdd.clear();
        return solutions.iterator();
    }

    private List<SudokuSolver> createFirstSolver(SudokuSolver firstSolver) {
        solutions.add(firstSolver);
        return new ArrayList<>();
    }

    private void runNewThreads(List<SudokuSolver> newSolversToAdd, Iterator<SudokuSolver> iterator, SudokuSolver solver, Cell cellMin) {
        counter += cellMin.getPossibilities().size();
        newSolversToAdd.addAll(solver.getNewSudokuSolvers(cellMin));
        iterator.remove();
    }
}

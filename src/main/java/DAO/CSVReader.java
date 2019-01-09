package DAO;

import Model.Cell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public Cell[][] openCSV(String csvFile) {

        String line = "";
        BufferedReader br = null;
        Integer[][] puzzle = new Integer[9][9];
        Cell[][] cells = new Cell[9][9];

        try {
            br = new BufferedReader(new FileReader(csvFile));
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] temp = line.trim().split(" {4}");
                for(int i = 0; i < temp.length; i++){
                    puzzle[index][i] = Integer.valueOf(temp[i]);
                    cells[index][i] = new Cell(index, i, Integer.valueOf(temp[i]));

                }
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
      return cells;
    }
}
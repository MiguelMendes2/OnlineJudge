package clubeProgramacao.Problems.Minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void Runner() {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
        String s;
        int numFields = 1;
        StringBuilder strB = new StringBuilder();
        try {
            while ((s = in.readLine()) != null) {

                int rows, cols;
                String[] split = s.split(" ");
                rows = Integer.parseInt(split[0]);
                cols = Integer.parseInt(split[1]);
                if(rows == 0 || cols == 0)
                    break;
                if (numFields != 1) {
                    strB.append(System.lineSeparator());
                    strB.append(System.lineSeparator());
                }
                strB.append("Field #" + numFields + ":");
                strB.append(System.lineSeparator());
                numFields++;
                // Read camp
                String[] camp = new String[rows];
                for(int i = 0; i < rows; i++) {
                    camp[i] = in.readLine();
                }

                Solver(camp, strB, rows, cols);
            }
            System.out.println(strB.toString());
            in.close();
        } catch (IOException e) {
            System.err.println("Error reading content. Wrong row or col number");
        }
    }

    private static void Solver(String[] camp, StringBuilder strB, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(camp[i].charAt(j) == '*') {
                    strB.append("*");
                } else {
                    strB.append(searchMines(camp, i, j));
                }
            }
            if (i != rows - 1)
                strB.append(System.lineSeparator());
        }
    }

    private static int searchMines(String[] camp, int row, int col) {
        int count = 0;
        for(int i = row - 1; i <= row + 1; i++) {
            for(int j = col - 1; j <= col + 1; j++) {
                if(validIndex(camp, i, j) && camp[i].charAt(j) == '*')
                    count++;
            }
        }
        return count;
    }

    private static boolean validIndex(String[] camp, int row, int col) {
        return 0 <= row && row < camp.length &&
            0 <= col && col < camp[0].length();
    }

    public static void main(String[] args) {
        Runner();
    }
}

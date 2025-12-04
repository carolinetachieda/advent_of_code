package Four;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FourDec {

    public static class Result {
        String[][] grid;
        int accessibleCount;
        int rows_count;
        int column_count;

        public Result(String[][] grid, int accessibleCount, int rows_count, int column_count) {
            this.grid = grid;
            this.accessibleCount = accessibleCount;
            this.rows_count = rows_count;
            this.column_count = column_count;
        }
    }

    public static Result checkPosition(String[][] grid, int rows_count, int column_count) {
        int accessibleCount = 0;
        String[][] newGrid = new String[rows_count][column_count];
        for (int i = 0; i < rows_count; i++) {
            for (int j = 0; j < column_count; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        for (int i = 0; i < rows_count; i++) {
            for (int j = 0; j < column_count; j++) {
                if (grid[i][j].equals("@")) {
                    int adjacentPaperCount = 0;

                    for (int di = -1; di <= 1; di++) { // check 8 directions
                        for (int dj = -1; dj <= 1; dj++) {

                            if (di == 0 && dj == 0)
                                continue; // skip midten

                            int ni = i + di;
                            int nj = j + dj;

                            if (ni >= 0 && ni < rows_count &&
                                    nj >= 0 && nj < column_count &&
                                    grid[ni][nj].equals("@")) {
                                adjacentPaperCount++;
                            }
                        }
                    }

                    // hvis der er færre end 4
                    if (adjacentPaperCount < 4) {
                        accessibleCount++;
                        newGrid[i][j] = "."; // fjern rullen
                    }
                }
            }
        }
        return new Result(newGrid, accessibleCount, rows_count, column_count);
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("Four/input.txt"));
            java.util.ArrayList<String[]> rows = new java.util.ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                rows.add(line.split(""));
            }
            String[][] grid = rows.toArray(new String[rows.size()][]);
            int accessibleTotalCount = 0; // tæller til tilgængelige ruller
            int rows_count = grid.length;
            int column_count = grid[0].length;
            int accessibleCount = 0;
            do {
                Result result = checkPosition(grid, rows_count, column_count);
                accessibleTotalCount += result.accessibleCount;
                accessibleCount = result.accessibleCount;
                grid = result.grid;
                System.out.println("Accessible paper rolls: " + result.accessibleCount);

            } while (accessibleCount > 0);
            System.out.println("Total accessible paper rolls: " + accessibleTotalCount);
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
/*
 * large grid -> Array af Array
 * fewer than four rolls of paper in the eight adjacent positions.
 * Read the grid into a 2D array
 * For each position containing @:
 * Count how many of the 8 adjacent positions also contain @
 * If the count is less than 4, increment the accessible counter
 * 
 * Stop once no more rolls of paper are accessible by a forklift.
 * Once a roll of paper is removed, the forklifts might be able to access more
 * rolls of paper, which they might also be able to remove.
 * Run while loop until no more rolls can be removed in an iteration.
 * return count, return map of remaining rolls - hvis jeg har kørt hele kortet
 * og der ikke er flere at fjerne
 * 
 */
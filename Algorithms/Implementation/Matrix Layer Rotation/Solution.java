import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static int rows = 0;
    static int cols = 0;

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {

        // Create a 2D temporary matrix with the values in matrix
        int[][] tempMatrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            List<Integer> currRow = matrix.get(i);
            for (int j = 0; j < cols; j++) {
                tempMatrix[i][j] = currRow.get(j);
            }
        }
        // Final matrix where result printed will be stored
        int[][] finalMatrix = new int[rows][cols];

        // Boundaries of rows and columns for inner squares of matrix
        int str = rows-1;
        int stc = cols-1;
        int k = 0; int l =0;
        Queue<Integer> q = new LinkedList<>();

        // As long as we know both k and l are less than str and stc respectively, we have another inner square to shift values
        while(k<str && l<stc) {
            // Calculate actual number of blocks we need to shift
            int totalBlocksInSquare = (2 * (str - k)) + (2 * (stc - l));
            int blocksToShift = r % totalBlocksInSquare;
            // Gather matrix values for last row of inner square
            for (int j = stc; j >= l; j--) {
                q.add(tempMatrix[str][j]);
            }
            // Gather matrix values for first column of inner square
            for (int i = (str-1); i >= k; i--) {
                q.add(tempMatrix[i][l]);
            }
            // Gather matrix values for first row of inner square
            for (int j = (l+1); j <= stc; j++) {
                q.add(tempMatrix[k][j]);
            }
            // Gather matrix values of last column of inner square
            for (int i = (k+1); i < str; i++) {
                q.add(tempMatrix[i][stc]);
            }
            // Adjust queue elements based on blocks we need to shift
            for (int i = 0; i < blocksToShift; i++) {
                q.add(q.remove());
            }
            // Shift matrix values for last row of inner square
            for (int j = stc; j >= l; j--) {
                finalMatrix[str][j] = q.remove();
            }
            // Shift matrix values for first column of inner square
            for (int i = (str-1); i >= k; i--) {
                finalMatrix[i][l] = q.remove();
            }
            // Shift matrix values for first row of inner square
            for (int j = (l+1); j <= stc; j++) {
                finalMatrix[k][j] = q.remove();
            }
            // Shift matrix values of last column of inner square
            for (int i = (k+1); i < str; i++) {
                finalMatrix[i][stc] = q.remove();
            }

            // Update values in order to go to the next inner square
            str--;
            stc--;
            k++;
            l++;
        }

        // Print the final matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(finalMatrix[i][j]);
                if (j != cols-1) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        rows = m;
        cols = n;

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
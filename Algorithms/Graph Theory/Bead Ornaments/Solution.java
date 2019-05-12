import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the beadOrnaments function below.
     */
    static void beadOrnaments(int t, int[] b) {

        BigInteger totalOrientations = BigInteger.ONE;
        if (t == 1) {
            // Just use Cayleys algorithm to find number of tree orientations
            BigInteger bValue = new BigInteger(Integer.toString(b[0]));
            totalOrientations = totalOrientations.multiply(bValue.pow(b[0]-2));
        } else {
            // Use Cayleys algorithm along with the fact any of the beads of a tree can be chosen to be connected to, for each color
            // Also consider the fact that we need to arrange the n trees in different ways
            BigInteger totalBeads = BigInteger.ZERO;
            for (int i = 0; i < b.length; i++) {
                BigInteger bValue = new BigInteger(Integer.toString(b[i]));
                totalOrientations = totalOrientations.multiply(bValue.pow(b[i]-1));
                totalBeads = totalBeads.add(bValue);
            }

            // Also consider the fact that we need to arrange the n trees in different ways, BigInteger.pow only takes positive values
            totalOrientations =  totalOrientations.multiply(totalBeads.pow(t-2));
        }

        System.out.println(totalOrientations.mod(new BigInteger(Integer.toString(1000000007))));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int tItr = 0; tItr < t; tItr++) {
            int bCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

            int[] b = new int[bCount];

            String[] bItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

            for (int bItr = 0; bItr < bCount; bItr++) {
                int bItem = Integer.parseInt(bItems[bItr]);
                b[bItr] = bItem;
            }

            beadOrnaments(b.length, b);
        }

        scanner.close();
    }
}
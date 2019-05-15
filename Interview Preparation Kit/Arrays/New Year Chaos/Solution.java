import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int getSum(int[] BITree, int index) {
        int sum = 0;
        index = index-1;
        while (index > 0) {
            sum += BITree[index];
            index -= (index & (-index));
        }

        return sum;
    }

    static void updateBIT(int[] BITree, int index, int maxIndex) {

        while (index <= maxIndex) {
            BITree[index] += 1;
            index += (index & (-index));
        }

    }

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {

        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < q.length; i++) {
            if (q[i] > maxValue) {
                maxValue = q[i];
            }
        }

        int[] BITree = new int[q.length + 1];

        int minimumBribes = 0;
        boolean tooChaotic = false;
        for (int i = q.length-1; i >= 0; i--) {
            int additionalBribes = getSum(BITree, q[i]);
            if (additionalBribes > 2) {
                tooChaotic = true;
                break;
            } else {
                minimumBribes += additionalBribes;
            }

            updateBIT(BITree, q[i], maxValue);
        }

        if (tooChaotic) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(minimumBribes);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
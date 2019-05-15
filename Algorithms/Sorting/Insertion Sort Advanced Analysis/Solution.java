import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static long[] BIT;

    static long getSum(int index) {
        long sum = 0;
        index = index-1;
        while (index > 0) {
            sum += BIT[index];
            index -= (index & (-index));
        }
        return sum;
    }

    static void updateBIT(int maxValue, int index) {
        while (index <= maxValue) {
            BIT[index] += 1;
            index += (index & (-index));
        }
    }

    // Complete the insertionSort function below.
    static long insertionSort(int[] arr) {

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        BIT = new long[maxValue+1];

        long inversions = 0;
        for (int i = arr.length-1; i >= 0; i--) {
            inversions += getSum(arr[i]);
            updateBIT(maxValue, arr[i]);
        }

        return inversions;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = insertionSort(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
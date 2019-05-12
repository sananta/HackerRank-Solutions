import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int ALPHABET_SIZE = 26;

    static class geneInfo {
        int index;
        int healthValue;

        geneInfo(int index, int healthValue) {
            this.index = index;
            this.healthValue = healthValue;
        }
    }

    static class Node {
        ArrayList<geneInfo> healthyGenes = new ArrayList<>();
        Node[] children = new Node[ALPHABET_SIZE];
    }

    static Node root;

    static void createTrie(String[] genes, int[] health) {
        Node temp;
        root = new Node();
        for (int i = 0; i < genes.length; i++) {
            temp = root;
            for (int j = 0; j < genes[i].length(); j++) {
                int value = genes[i].charAt(j) - 'a';
                if (temp.children[value] == null) {
                    temp.children[value] = new Node();
                }
                temp = temp.children[value];
            }
            temp.healthyGenes.add(new geneInfo(i, health[i]));
        }
    }

    static long getStringScore(String d, int first, int last) {
        Node temp = root;
        long score = 0;
        int transition = 0;
        while (transition < d.length()) {
            temp = root;
            for (int i = transition; i < d.length(); i++) {
                int value = d.charAt(i) - 'a';
                if (temp.children[value] == null) {
                    break;
                } else {
                    if (temp.children[value].healthyGenes != null) {
                        for (geneInfo healthyGene : temp.children[value].healthyGenes) {
                            if (healthyGene.index >= first && healthyGene.index <= last) {
                                score += healthyGene.healthValue;
                            }
                        }
                    }
                    temp = temp.children[value];
                }
            }
            transition++;
        }

        return score;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] genes = new String[n];

        String[] genesItems = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            genes[i] = genesItems[i];
        }

        int[] health = new int[n];

        String[] healthItems = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            health[i] = Integer.parseInt(healthItems[i]);
        }

        int s = Integer.parseInt(br.readLine());

        createTrie(genes, health);

        long minValue = Long.MAX_VALUE;
        long maxValue = Long.MIN_VALUE;
        long total = 0;

        for (int sItr = 0; sItr < s; sItr++) {

            String[] firstLastd = br.readLine().split(" ");

            int first = Integer.parseInt(firstLastd[0]);

            int last = Integer.parseInt(firstLastd[1]);

            String d = firstLastd[2];

            total = getStringScore(d, first, last);

            minValue = Math.min(minValue, total);
            maxValue = Math.max(maxValue, total);
        }

        System.out.println(minValue + " " + maxValue);

    }
}
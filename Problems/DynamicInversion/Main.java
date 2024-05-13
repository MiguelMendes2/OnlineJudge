package Problems.DynamicInversion;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import java.util.Map;

/**
 * Blocks Problem
 * You are given a permutation {1,2,3,…,n}. Remove m of them one by one, and output the number of
 * inversion pairs before each removal. The number of inversion pairs of an array A is the number of
 * ordered pairs (i, j) such that i < j and A[i] > A[j].
 */
public class Main {

    private static Map<Integer,Integer> permutation;

    public static void Runner() {
        Scanner scn = new Scanner(System.in);
        StringBuilder strB = new StringBuilder();
        int n = scn.nextInt();
        int m = scn.nextInt();
        int inversionPairs = initPermutation(n, scn);
        removeNumbers(m, scn, inversionPairs, strB);
        System.out.println(strB.toString());
        scn.close();
    }

    private static int initPermutation(int n, Scanner scn) {
        permutation = new LinkedHashMap<>();
        int inversionPairs = 0;
        for(int i = 0; i < n; i++) {
            permutation.put(scn.nextInt(), 0);
        }
        int skip = 1;
        for(int keyP : permutation.keySet()) {
            int counter = 0;
            for(int key : permutation.keySet()) {
                if(counter < skip) {
                    counter++;
                } else if (key < keyP) {
                    inversionPairs++;
                    permutation.put(keyP, permutation.get(keyP) + 1);
                }
            }
            skip++;
        }
        return inversionPairs;
    }

    private static void removeNumbers(int m, Scanner scn, int inversionPairs, StringBuilder strB) {
        for(int i = 0; i < m; i++) {
            int removed = scn.nextInt();
            strB.append(inversionPairs).append("\n");
            inversionPairs = removeNumber(removed, inversionPairs);
        }
    }

    private static int removeNumber(int removed, int inversionPairs) {
        for(Entry<Integer,Integer> set : permutation.entrySet()) {
            if(set.getKey() == removed) {
                inversionPairs -= set.getValue();
                break;
            }
            if(set.getKey() > removed) {
                inversionPairs--;
                permutation.put(set.getKey(), set.getValue() - 1);
            }
        }
        permutation.remove(removed);
        return inversionPairs;
    }

    public static void main(String[] args) {
        Runner();
    }
}

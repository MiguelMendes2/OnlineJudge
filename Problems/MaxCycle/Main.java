package Problems.MaxCycle;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The 3n+1 problem
 *
 * Given two numbers, we need to find the maximum cycle length between them.
 *
 * Input:
 * The input consists of several test cases. Each test case consists of two integers i and j. You can assume 0 < i, j < 10000.
 *
 * Output:
 * For each test case, print i, j and the maximum cycle length between i and j.
 *
 * Sample Input:
 * 1 10
 * 100 200
 * 201 210
 * 900 1000
 *
 * Sample Output:
 * 1 10 20
 * 100 200 125
 * 201 210 89
 * 900 1000 174
 *
 * @author fc59885 - Miguel Mendes
 */
public class Main {

    private static HashMap<Long, Long> prev = new HashMap<>();

    /**
     * Read the input and call the solver
     */
    public static void Runner() {
        Scanner scn = new Scanner(System.in);
        long first, second, max;

        while(scn.hasNext()) {
            first = scn.nextInt();
            second = scn.nextInt();
            max = GetMaxCycle(Math.min(first, second), Math.max(first, second));
            System.out.println(first + " " + second + " " + max);
        }
        scn.close();
    }

    /**
     * Get the maximum cycle length between two numbers´
     *
     * @param min the minimum number
     * @param max the maximum number
     * @return the maximum cycle length between min and max
     */
    private static long GetMaxCycle(long min, long max) {
        long maxCycleCounter = 0;
        for(long i = min; i <= max; i++) {
            maxCycleCounter = Math.max(maxCycleCounter, cycle(i));
        }
        return maxCycleCounter;
    }

    /**
     * Get the cycle length of a number
     *
     * @param n the number
     * @return the cycle length of n
     */
    private static long cycle(long n) {
        if (prev.containsKey(n))
            return prev.get(n);
        if (n == 1)
            return 1;
        if (n % 2 == 0) {
            long result =  1 + cycle(n / 2);
            prev.put(n, result);
            return result;
        }
        long result =  1 + cycle(3 * n + 1);
        prev.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        Runner();
    }
}
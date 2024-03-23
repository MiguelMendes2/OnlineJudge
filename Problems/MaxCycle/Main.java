package clubeProgramacao.Problems.MaxCycle;

import java.util.Scanner;

public class Main {

    public static void Runner() {
        Scanner scn = new Scanner(System.in);
        int first, second, max;
        while(scn.hasNext()) {
            first = scn.nextInt();
            second = scn.nextInt();
            max = GetMaxCycle(Math.min(first, second), Math.max(first, second));
            System.out.println(first + " " + second + " " + max);
        }
        scn.close();
    }

    private static int GetMaxCycle(int min, int max) {
        int maxCycleCounter = 0;
        for(int i = min; i <= max; i++) {
            maxCycleCounter = Math.max(maxCycleCounter, cycle(i));
        }
        return maxCycleCounter;
    }

    private static int cycle(int n) {
        if (n == 1)
            return 1;
        if (n % 2 == 0)
            return 1 + cycle(n / 2);
        return 1 + cycle(3 * n + 1);
    }

    public static void main(String[] args) {
        Runner();
    }
}
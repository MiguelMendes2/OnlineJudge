package clubeProgramacao.p100;

import java.util.Scanner;

public class Problem100 {

    public static void Runner() {
        System.out.println("Enter 2 numbers in following format (Enter 0 to leave):");
        System.out.println("1 10");
        Scanner scn = new Scanner(System.in);
        int first, second, max;
        while(scn.hasNext()) {
            first = scn.nextInt();
            second = scn.nextInt();
            if (first <= 0 || second <= 0)
                break;
            max = MaxCycle(Math.min(first, second), Math.max(first, second));
            System.out.println(first + " " + second + " " + max);
        }
        scn.close();
    }

    private static int MaxCycle(int min, int max) {
        int maxCycleCounter = 0;
        for(int i = min; i <= max; i++) {
            int result = cycle(i);
            maxCycleCounter = maxCycleCounter > result ? maxCycleCounter : result ;
        }
        return maxCycleCounter;
    }

    private static int cycle(int n) {
        if (n == 1)
            return 1;
        if (n % 2 == 0)
            return 1 + cycle(n / 2) ;
        return 1 + cycle(3 * n + 1);
    }

    public static void main(String[] args) {
        System.out.println("Enter 2 numbers in following format (Enter 0 to leave):");
        System.out.println("1 10");
        Scanner scn = new Scanner(System.in);
        int first, second, max;
        while(scn.hasNext()) {
            first = scn.nextInt();
            second = scn.nextInt();
            if (first <= 0 || second <= 0)
                break;
            max = MaxCycle(Math.min(first, second), Math.max(first, second));
            System.out.println(first + " " + second + " " + max);
        }
        scn.close();
    }
}
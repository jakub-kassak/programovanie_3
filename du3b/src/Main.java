import MultiSort.MultiSorter;
import finiteAutomaton.FiniteAutomaton;
import multiSearch.MultiSearchableArrayList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";

    private static String getString(int n) {
        String res = "";
        while (n > 0) {
            res = res + (n % 2 == 0 ? 'a': 'b');
            n /= 2;
        }
        return res;
    }

    private static boolean isAutomatonAccepting(final String s) {
        final FiniteAutomaton automaton = new FiniteAutomaton();
        for (int i = 0; i < s.length(); i++) {
            automaton.transition(s.charAt(i));
        }
        return automaton.isAcceptingState();
    }

    private static boolean isRegexAccepting(final String s) {
        final String pattern = "b*a(ab*a)*b(a*bb*a)*a*";
        return s.matches(pattern);
    }

    private static void testFiniteAutomaton() {
        System.out.println("testFiniteAutomaton()");
        String[] tests_easy = {"", "aab", "aaab", "bbaaabaaa"};
        boolean[] res_easy = {false, false, true, true};
        for (int i = 0; i < tests_easy.length; i++) {
            final boolean result = isAutomatonAccepting(tests_easy[i]);
            if (result != res_easy[i]) {
                System.err.println("Incorrect result on string " + tests_easy[i] +
                        "! correct: " + res_easy[i] + " vs automaton: " + result);
                System.err.flush();
            }
        }
        System.out.println("Easy tests completed");

        for (int i = 0; i < (1L<<20); i++) {
            final String tape = getString(i);
            final boolean aVal = isAutomatonAccepting(tape);
            final boolean rVal = isRegexAccepting(tape);
            if (aVal != rVal) {
                System.err.println(tape + "! regex " + rVal + " vs automaton " + aVal );
                System.err.flush();
                break;
            }
        }
        System.out.println("Exhaustive tests completed");
    }

    private static void testMultiSearch() {
        System.out.println("testMultiSearch()");
        final long totalStart = System.nanoTime();

        final ArrayList<Integer> A = new MultiSearchableArrayList<Integer>(Arrays.asList(3,4,5,-1,3,5,6,7,2,9,10,1,120, 20));
        int[] tests_easy = {2, 11, 120, 47};
        boolean[] res_easy = {true, false, true, false};
        for (int i = 0; i < tests_easy.length; i++) {
            final boolean result = A.contains(tests_easy[i]);
            if (result != res_easy[i]) {
                System.out.print(ANSI_RED);
                System.out.println("Incorrect result on value " + tests_easy[i] +
                        "! correct: " + res_easy[i] + " vs array: " + result);
                System.out.print(ANSI_RESET);
            }
        }


        final ArrayList<Integer> B = new MultiSearchableArrayList<Integer>();
        final ArrayList<Integer> B_control = new ArrayList<>();
        final Random r = new Random();
        for (int i = 0; i < 1000000; i++) {
            final int value = r.nextInt(10000000);
            B.add(value);
            B_control.add(value);
        }

        long totalMultiTime = 0;
        long totalSingleTime = 0;

        final int testsNum = 1000;
        for (int i = 0; i < testsNum; i++) {
            int value = r.nextInt(12000000);

            long start = System.nanoTime();
            final boolean test = B.contains(value);
            long finish = System.nanoTime();
            totalMultiTime += finish - start;

            start = System.nanoTime();
            final boolean control = B_control.contains(value);
            finish = System.nanoTime();
            totalSingleTime += finish - start;

            if (test != control) {
                System.out.print(ANSI_RED);
                System.out.println("Value " + value + " B: " + test + ", B_control: " + control);
                System.out.print(ANSI_RESET);
            }
        }

        final double avgSingle = (double)totalSingleTime / testsNum / 1000000; // ms
        final double avgMulti = (double)totalMultiTime / testsNum / 1000000; // ms
        System.out.println(ANSI_CYAN + "Average single time: " + new DecimalFormat("#0.0000").format(avgSingle) + "ms");
        System.out.println("Average multi time: " + new DecimalFormat("#0.0000").format(avgMulti)  + "ms");

        long totalFinish = System.nanoTime();
        if ((totalFinish - totalStart)/1000000 > 15000) System.out.print(ANSI_RED);
        System.out.println("Total runtime: " + (totalFinish - totalStart)/1000000 + "ms" + ANSI_RESET);

    }


    private static void testMultiSort() {
        System.out.println("testMultiSort()");
        final long totalStart = System.nanoTime();

        final ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3, 7, 29, 54, 0, -1, 53, 11, 14, 47, 42));
        final MultiSorter sorter = new MultiSorter(6);
        sorter.sort(A);
        System.out.println(A);

        final Random r = new Random();
        final ArrayList<Integer> A_control = new ArrayList<>();
        final int testsNum = 10;
        long totalSingleTime = 0;
        long totalMultiTime = 0;
        for (int test = 0; test < testsNum; test++) {
            System.out.println("Test #" + test);
            A.clear();
            A_control.clear();
            for (int i = 0; i < 10000000; i++) {
                final int value = r.nextInt(1000000);
                A.add(value);
                A_control.add(value);
            }
            long start = System.nanoTime();
            sorter.sort(A);
            long finish = System.nanoTime();
            totalMultiTime += finish - start;

            start = System.nanoTime();
            Collections.sort(A_control);
            finish = System.nanoTime();
            totalSingleTime += finish - start;

            for (int i = 0; i < A.size(); i++) {
                if (A.get(i).compareTo(A_control.get(i)) != 0) {
                    System.out.print(ANSI_RED);
                    System.out.println("elements #" + i + " don't match: " + A.get(i) + " vs " + A_control.get(i));
                    System.out.print(ANSI_RESET);
                    break;
                }
            }
        }
    final double avgSingle = (double)totalSingleTime / testsNum / 1000000; // ms
    final double avgMulti = (double)totalMultiTime / testsNum / 1000000; // ms
    System.out.println(ANSI_CYAN + "Average single time: " + new DecimalFormat("#0.0000").format(avgSingle) + "ms");
    System.out.println("Average multi time: " + new DecimalFormat("#0.0000").format(avgMulti)  + "ms");

    long totalFinish = System.nanoTime();
    if ((totalFinish - totalStart)/1000000 > 15000) System.out.print(ANSI_RED);
    System.out.println("Total runtime: " + (totalFinish - totalStart)/1000000 + "ms" + ANSI_RESET);

    }


    public static void main(String[] args) {
//        testFiniteAutomaton(); // odkomentujte, ak chcete mat bodovanu prvu podulohu
//        testMultiSearch(); // odkomentujte, ak chcete mat bodovanu druhu podulohu
        testMultiSort(); // odkomentujte, ak chcete mat bodovanu tretiu podulohu
    }
}

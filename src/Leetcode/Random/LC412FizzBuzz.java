package Leetcode.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC412FizzBuzz {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(fizzBuzz(n));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(i % 15 == 0 ){
                ans.add("FizzBuzz");
            }else if(i % 3 == 0){
                ans.add("Fizz");
            }else if (i % 5 == 0){
                ans.add("Buzz");
            }else {
                ans.add(""+i);
            }
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 412. Fizz Buzz)
    // Problem: https://leetcode.com/problems/fizz-buzz/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (n = 1, n = 30 with a second "FizzBuzz", and
    // n = 10000 = the maximum, checked by size / counts / last item). Expected values come from the
    // statement or from a brute force (build "Fizz" + "Buzz" by concatenation); the counts for
    // n = 10000 follow from integer division: 10000/15 = 666, 10000/3 - 666 = 2667,
    // 10000/5 - 666 = 1334.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: n = 3", Arrays.asList("1", "2", "Fizz"), () -> run(3));
        check("example 2: n = 5", Arrays.asList("1", "2", "Fizz", "4", "Buzz"), () -> run(5));
        check("example 3: n = 15",
                Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11",
                        "Fizz", "13", "14", "FizzBuzz"),
                () -> run(15));
        // edge: smallest n
        check("n = 1", Arrays.asList("1"), () -> run(1));
        // edge: n = 30, two full rounds -> a second "FizzBuzz" at the end
        check("n = 30",
                Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11",
                        "Fizz", "13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz", "Fizz",
                        "22", "23", "Fizz", "Buzz", "26", "Fizz", "28", "29", "FizzBuzz"),
                () -> run(30));
        // edge: n = 10000 (maximum): [size, #FizzBuzz, #Fizz, #Buzz, last item]
        check("n = 10000: size, counts and last item",
                Arrays.<Object>asList(10000, 666, 2667, 1334, "Buzz"),
                () -> summary(run(10000)));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static List<String> run(int n) {
        return LC412FizzBuzz.fizzBuzz(n);
    }

    private static List<Object> summary(List<String> answer) {
        int fizzBuzz = 0, fizz = 0, buzz = 0;
        for (String s : answer) {
            if (s.equals("FizzBuzz")) fizzBuzz++;
            else if (s.equals("Fizz")) fizz++;
            else if (s.equals("Buzz")) buzz++;
        }
        return Arrays.<Object>asList(answer.size(), fizzBuzz, fizz, buzz,
                answer.get(answer.size() - 1));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

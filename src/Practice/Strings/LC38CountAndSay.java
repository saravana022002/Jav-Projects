package Practice.Strings;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 38. Count and Say   [Medium]
// https://leetcode.com/problems/count-and-say/
// Topics: String
// ============================================================================
//
// PROBLEM
//   The count-and-say sequence starts with "1". Every later term is the previous term "read
//   out loud": each maximal run of equal digits is replaced by its length followed by the
//   digit. Example: "3322251" reads as two 3s, three 2s, one 5, one 1 -> "23" "32" "15" "11"
//   -> "23321511". Given n, return the n-th term of the sequence.
//
// EXAMPLES
//   n = 4  ->  "1211"   "1" -> "11" (one 1) -> "21" (two 1s) -> "1211" (one 2, one 1)
//   n = 1  ->  "1"      the base case
//
// CONSTRAINTS
//   1 <= n <= 30        (follow-up on LeetCode: solve it iteratively)
//
// INTUITION
//   There is no shortcut formula: term n can only be built from term n - 1. So start from
//   "1" and apply one read-out step n - 1 times. One step is a single left-to-right scan that
//   measures the length of every run of equal digits and writes "length digit".
//
// APPROACHES IN THIS FILE
//   A. countAndSayItr(n)         - loop: result = computeStr(result), n - 1 times
//                                  <- main() runs this one (the iterative follow-up)
//   B. countAndSay(n)            - top-down recursion: term n = computeStr(term n - 1), the
//                                  terms cached in the static map memo (key n -> term n)
//   C. computeRLE(i, n, string)  - a "bottom up" recursive helper that uses the same memo in
//                                  a different way; nothing calls it and it breaks once memo
//                                  is non-empty (see STATUS)
//   computeStr(string) is the shared helper that performs one read-out step.
//
// APPROACH (as implemented below, A with the helper computeStr)
//   1. countAndSayItr: result = "1" (term 1); for i = 2 .. n: result = computeStr(result),
//      so after the pass for i, result is term i. Return result.
//   2. computeStr scans its argument once. ch is the digit of the current run and count its
//      length so far (start: the first character, 1).
//   3. For each i: the inner while extends the run while string.charAt(i) equals ch
//      (count++, i++).
//   4. If characters remain, the run is over: append count, then ch, and start the next run
//      with ch = string.charAt(i), count = 1.
//   5. The loop never writes the final run, so append count and ch once more after it.
//
// DRY RUN   computeStr("1211")   (term 4 -> term 5); main's loop repeats this n - 1 times
//   ch = '1', count = 1
//   i=1  '2' != '1'  run over: append "11"                   ch = '2', count = 1
//   i=2  '1' != '2'  run over: append "12"                   ch = '1', count = 1
//   i=3  '1' == '1'  count = 2, i becomes 4 = end of string  nothing appended yet
//   after the loop: append "21"            stringBuilder = "11" + "12" + "21" = "111221"
//   So countAndSayItr(5) goes "1" -> "11" -> "21" -> "1211" -> "111221".
//
// COMPLEXITY   (L(n) = length of term n; it grows by about 1.3x per term)
//   Time : O(L(n)) for A - each step is linear in the previous term, and the lengths form a
//          geometric series, so the total is proportional to the last term. L(30) = 4462.
//   Space: O(L(n)) - A keeps the current and the previous term; B and C also keep every
//          term in memo (still O(L(n)) in total), and B recurses n levels deep
//
// GOTCHAS
//   - The terms grow exponentially, which is why n is limited to 30 (term 30 already has
//     4462 characters).
//   - The last run is written after the loop, because the loop writes a run only when it
//     meets the next, different digit.
//   - The inner while moves i; the for loop's own i++ then skips the character at i, which is
//     fine because ch and count were already reset to cover it.
//   - ch is a boxed Character; in ch == string.charAt(i) it is unboxed, so the letters are
//     compared by value.
//
// STATUS
//   Known issues. The code is left unchanged on purpose.
//   1. main() calls countAndSayItr(321), far outside the allowed range n <= 30. The terms
//      grow about 1.3x per step, so term 321 would need well over 10^30 characters: the run
//      never finishes and ends with java.lang.OutOfMemoryError (seen with a 128 MB heap).
//      The method itself is fine for 1 <= n <= 30, e.g. countAndSayItr(4) returns "1211".
//   2. computeRLE (unused) saves term i + 1 under key i in the same static memo that
//      countAndSay fills with term n under key n. On an empty memo a call works
//      (computeRLE(1, 5, "1") -> "111221"), but every later call, or any call after
//      countAndSay has run, hits the memo and returns an intermediate term instead of term
//      n (computeRLE(1, 5, "1") a second time returns "11").
//   countAndSayItr and countAndSay match a reference implementation for n = 1 .. 30.
//
// RELATED
//   - LC 443 String Compression - the same scan over runs of equal characters, writing the
//     letter and then the run length.
// ============================================================================
public class LC38CountAndSay {
    public static void main(String[] args) {
        // KNOWN ISSUE: 321 is far outside n <= 30, so this never finishes (OutOfMemoryError).
        // Use n <= 30, e.g. countAndSayItr(4) prints 1211. See STATUS.
        System.out.println(countAndSayItr(321));
    }

    // cache shared by approaches B and C (never cleared)
    static Map<Integer, String> memo = new HashMap<>();

    // Approach A (iterative): start from term 1 and apply one read-out step per extra term
    public static String countAndSayItr(int n) {
        if (n == 1) return "1";
        String result = "1";
        // after the pass for i, result holds term i
        for (int i = 2; i <= n; i++) {
            result = computeStr(result);
        }
        return result;
    }



    // Approach B: term n is the read-out of term n - 1; memo caches each term under its n
    // top down
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        // already computed -> reuse it
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        String prev = countAndSay(n - 1);
        String next = computeStr(prev);
        memo.put(n, next);
        return next;
    }

    // Approach C (not called anywhere): walks up from term i to term n. It stores term i + 1
    // under key i in the same memo that countAndSay uses for term n under key n, so a memo
    // hit returns the wrong term - see STATUS.
    // bottom up
    public static String computeRLE(int i, int n, String string){
        // string is term i; term n reached -> done
        if(i == n) {
            return string;
        }
        // memo hit: returns a stored intermediate term, not term n (the defect)
        if (memo.containsKey(i))
            return memo.get(i);
        // next = term i + 1, saved under key i
        String next = computeStr(string);
        memo.put(i, next);
        return computeRLE(i + 1, n, next);
    }




    // One read-out step: writes "length digit" for every run of equal digits in the argument.
    // O(length of the argument)
    public static String computeStr(String string){
        // ch = digit of the current run, count = its length so far
        Character ch = string.charAt(0);
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < string.length(); i++) {
            // extend the run while the next character is the same digit
            while (i < string.length() && ch == string.charAt(i)){
                count++;
                i++;
            }
            // run finished and characters remain: write it, then start a new run at i
            if(i < string.length()) {
                stringBuilder.append(count);
                stringBuilder.append(ch);
                ch = string.charAt(i);
                count = 1;
            }
        }
        // the last run has not been written yet
        stringBuilder.append(count);
        stringBuilder.append(ch);
        return stringBuilder.toString();
    }
}

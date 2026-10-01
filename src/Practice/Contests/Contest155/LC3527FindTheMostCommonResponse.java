package Practice.Contests.Contest155;

import java.util.*;

// ============================================================================
// LC 3527. Find the Most Common Response   [Medium]
// https://leetcode.com/problems/find-the-most-common-response/
// Topics: Array, Hash Table, String, Counting
// ============================================================================
//
// PROBLEM
//   responses[i] is the list of survey answers given on day i. Count how many DAYS each
//   answer appears on (repeating an answer within the same day counts only once) and
//   return the answer with the highest count. If several answers tie, return the
//   lexicographically smallest of them.
//
// EXAMPLES
//   [["good","ok","good","ok"],["ok","bad","good","ok","ok"],["good"],["bad"]]  ->  "good"
//       per day without repeats: [good,ok] [ok,bad,good] [good] [bad]
//       -> good 3, ok 2, bad 2
//   [["good","ok","good"],["ok","bad"],["bad","notsure"],["great","good"]]  ->  "bad"
//       good, ok and bad each appear on 2 days; "bad" is the smallest alphabetically
//
// CONSTRAINTS
//   1 <= responses.length <= 1000,   1 <= responses[i].length <= 1000,
//   1 <= responses[i][j].length <= 10,   lowercase English letters only
//
// INTUITION
//   Two small steps: drop the repeats inside each day (a Set does that), then count with a
//   Map from answer to number of days. After that the answer is the key with the largest
//   count; to break ties, collect every key that reaches the maximum and take the smallest
//   String.
//
// APPROACH (as implemented below)
//   1. stringVsCount = HashMap<answer, days>.
//   2. For each day: copy its list into a HashSet (removes repeats), then add 1 to the
//      count of every answer in that set.
//   3. First pass over the map: max = the largest count.
//   4. Second pass: put every answer whose count == max into maxStrs.
//   5. Return Collections.min(maxStrs), the lexicographically smallest of the tied answers.
//
// DRY RUN   main(): [good,ok] [ok,bad] [bad,notsure] [great,good]
//   day 1: good 1, ok 1
//   day 2: ok 2, bad 1
//   day 3: bad 2, notsure 1
//   day 4: great 1, good 2
//   counts: good 2, ok 2, bad 2, notsure 1, great 1  -> max = 2
//   maxStrs = {good, ok, bad}  ->  Collections.min = "bad"
//
// COMPLEXITY
//   Time : O(N) for N = total number of answers over all days (each is hashed once for the
//          per-day set and once for the map; answers have at most 10 letters), plus
//          O(U) to scan the map and a small min() over the tied answers
//   Space: O(U), U = number of distinct answers (the map; a day's set is never bigger)
//
// GOTCHAS
//   - Dedupe per DAY, not globally: an answer given five times on one day still counts once.
//   - HashMap iteration order is arbitrary, so ties must be settled explicitly with
//     Collections.min.
//   - max == entry.getValue() compares an int with an Integer, so the Integer is
//     unboxed and the values are compared numerically (no reference-equality trap).
//   - Collections.min would throw on an empty set; the constraints (every day has at least
//     one answer) guarantee there is at least one answer.
//
// RELATED
//   - LC 169 Majority Element (in this repo) - also finds the most frequent value with a
//     HashMap count.
// ============================================================================
public class LC3527FindTheMostCommonResponse {

    public static void main(String[] args) {
        // Example 2 after removing the repeats inside each day -> prints bad
        // (good, ok and bad tie with 2 days each; "bad" is the smallest)
        List<List<String>> responses = Arrays.asList(
                Arrays.asList("good", "ok"),
                Arrays.asList("ok", "bad"),
                Arrays.asList("bad", "notsure"),
                Arrays.asList("great", "good")
        );

        System.out.println(findCommonResponse(responses));
    }

    // Returns the answer that appears on the most days (smallest alphabetically on a tie).
    public static String findCommonResponse(List<List<String>> responses) {
        // answer -> number of days it appears on
        Map<String, Integer> stringVsCount = new HashMap<>();
        for (List<String> strings : responses){
            // a Set removes repeats within the same day
            Set<String> strs = new HashSet<>(strings);
            for(String s : strs){
                if(stringVsCount.containsKey(s)){
                    stringVsCount.put(s, stringVsCount.get(s) + 1);
                }else {
                    stringVsCount.put(s, 1);
                }
            }
        }
        int max = 0;
        Set<String> maxStrs = new HashSet<>();
        // pass 1: the highest count
        for(Map.Entry<String,Integer> entry : stringVsCount.entrySet()){
            Integer value = entry.getValue();
            if(max < value){
                max = value;
            }
        }
        // pass 2: every answer that reaches it (the tie group)
        for(Map.Entry<String,Integer> entry : stringVsCount.entrySet()){
            if(max == entry.getValue()){
                maxStrs.add(entry.getKey());
            }
        }
        // tie-break: the lexicographically smallest answer
        return Collections.min(maxStrs);
    }
}

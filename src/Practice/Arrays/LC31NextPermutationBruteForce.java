package Practice.Arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// ============================================================================
// LC 31. Next Permutation   [Medium]
// https://leetcode.com/problems/next-permutation/
// Topics: Array, Two Pointers
// ============================================================================
//
// PROBLEM
//   A permutation is one ordering of the numbers in nums. Sort all the orderings in
//   dictionary (lexicographic) order; the "next permutation" of nums is the ordering that
//   comes right after the current one. If the current one is the very last ordering, the
//   answer wraps around to the first: the numbers sorted ascending. Rearrange nums into
//   its next permutation in place, using only constant extra memory.
//
// EXAMPLES
//   nums = [1,2,3]  ->  [1,3,2]
//   nums = [3,2,1]  ->  [1,2,3]     the last ordering, so it wraps around to the smallest
//   nums = [1,1,5]  ->  [1,5,1]     equal numbers are allowed
//
// CONSTRAINTS
//   1 <= nums.length <= 100,   0 <= nums[i] <= 100
//
// INTUITION
//   This file takes the definition literally: write out ALL orderings, sort them, find the
//   current one and take its successor. It is easy to see but very expensive (n! orderings),
//   and it does not meet the "in place, constant memory" requirement. The efficient
//   solution is LC31NextPermutationOptimal.java (see SEE ALSO).
//
// APPROACH (as implemented below, approach2 - the method main() calls)
//   1. ans = every ordering of integers. recursivelyGeneratePermutationBySwap(index, ...)
//      swaps each element from position index onward into position index, recurses on
//      index + 1, swaps back, and at index == size stores a copy of the ordering.
//   2. ans.sort(...) with a comparator that compares two orderings element by element
//      (numerically), which is dictionary order.
//   3. Linear search: the first i whose arrayToLexicographical(ans.get(i)) equals that of
//      integers (the current ordering) becomes index. arrayToLexicographical glues the
//      digits together into one int, e.g. [1,2,3] -> 123.
//   4. Return ans.get(index + 1), the ordering right after the current one.
//
// DRY RUN   integers = [1,2,3]   (one of the few inputs this code handles correctly)
//   step 1  ans = [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,2,1] [3,1,2]    (swap order)
//   step 2  ans = [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]    (sorted)
//   step 3  key of integers = 123; ans.get(0) also has key 123 -> index = 0
//   step 4  return ans.get(1) = [1,3,2]
//   main() runs [12,2,3] instead and discards the result - see STATUS.
//
// COMPLEXITY
//   Time : O(n! * n^2 log n) - n! orderings, each copied in O(n); sorting them takes about
//          n! * log(n!) comparisons of O(n) each, and log(n!) is about n log n
//   Space: O(n * n!) - all n! orderings are stored at once (the recursion adds only O(n))
//
// GOTCHAS
//   - Swap-based generation does not produce dictionary order ([3,2,1] comes before
//     [3,1,2]), which is why the list has to be sorted.
//   - The second swap undoes the first one, so integers is back in its original order when
//     step 3 compares against it (approach2 does not change its input).
//   - Repeated numbers make the generator produce the same ordering more than once.
//
// RELATED
//   - LC 46 Permutations - generating all orderings by swap and backtrack, as step 1 does
//     (src/Practice/Backtracking/LC46Permutations.java).
//   - LC 47 Permutations II - the same generation when numbers repeat, without duplicates.
//   - LC 60 Permutation Sequence - jumps straight to the k-th ordering without listing them
//     (src/Practice/Math/LC60PermutationSequence.java).
//
// SEE ALSO
//   LC31NextPermutationOptimal.java - in place, O(n): find the rightmost ascent, swap it
//   with the rightmost larger element to its right, reverse the suffix
//
// STATUS
//   Known issue: approach2 (what main() runs) gives wrong answers or crashes on most inputs.
//   It is right only in a few lucky cases, for example [1,2,3] -> [1,3,2] (distinct numbers,
//   glued value <= 127) and any input that is already sorted ascending, such as [1,2,3,4] ->
//   [1,2,4,3] (the failed search leaves index = 0, which happens to be right there). Causes:
//   1. == on boxed Integers: arrayToLexicographical returns an Integer and == compares
//      references, which works only inside Java's Integer cache (-128..127). Above that
//      the current ordering is never found, index stays 0 and ans.get(1) is returned:
//        [12,2,3] -> [2,12,3]   (correct [12,3,2])      [3,2,1] -> [1,3,2]  (correct [1,2,3])
//        [1,3,2]  -> [1,3,2]    (correct [2,1,3])
//   2. Repeated numbers: the same ordering is stored several times, so ans.get(index + 1)
//      can be another copy of the current one: [1,1,5] -> [1,1,5] (correct [1,5,1]), which
//      means LeetCode's own example 3 fails.
//   3. No wrap-around: for the last ordering, ans.get(index + 1) is out of range, so
//      [2,1] throws IndexOutOfBoundsException (correct [1,2]); a single element [1] throws
//      too (correct [1]).
//   4. The glued-digits key is ambiguous and can overflow: [1,23] and [12,3] both give 123,
//      and a value above 2147483647 makes Integer.parseInt throw NumberFormatException.
//   Also: main() discards the result and prints nothing. approach1 is a leftover that only
//   PRINTS all orderings (it never looks for the next one) and throws IllegalStateException
//   (duplicate key) when nums has repeated numbers; its call in main() is commented out and
//   names a variable, integers, that does not exist there (the list is called arrayList).
//   The code is left unchanged on purpose.
// ============================================================================
public class LC31NextPermutationBruteForce {
    public static void main(String[] args) {
        // approach2 runs on [12,2,3] and its result is discarded (nothing is printed).
        // It actually returns [2,12,3]; the correct next permutation is [12,3,2] - see STATUS
        List<Integer> fixedSizeList = Arrays.asList(12, 2, 3);
        ArrayList<Integer> arrayList = new ArrayList<>(fixedSizeList);
//        approach1(integers);
        approach2( arrayList);
    }

    // Brute force: build all orderings, sort them, return the one after the current ordering.
    // Does not change its argument and has the defects listed in STATUS.
    private static  ArrayList<Integer> approach2( ArrayList<Integer> integers) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // step 1: collect all n! orderings
        recursivelyGeneratePermutationBySwap(0, integers, ans);
        // step 2: sort them in dictionary order - the first position that differs decides
        ans.sort((a, b) -> {
            int n = Math.min(a.size(), b.size());
            for (int i = 0; i < n; i++) {
                if (a.get(i) < b.get(i)) return -1;
                if (a.get(i) > b.get(i)) return 1;
            }
            return a.size() - b.size();
        });

        // step 3: find the current ordering in the sorted list.
        // BUG: == on two Integer objects is true only inside the Integer cache (<= 127);
        // if nothing matches, index stays 0. Repeated numbers also confuse it (see STATUS)
        int index = 0;
        for(int i= 0; i< ans.size(); i++){
            if (arrayToLexicographical(ans.get(i)) == arrayToLexicographical(integers)){
                index = i;
                break;
            }
        }

        // step 4: the successor. BUG: if the current ordering is the last one, index + 1 is
        // out of range instead of wrapping around to the smallest ordering
        return  ans.get(index+1);

    }

    // Glues the digits of all numbers into one int: [1,2,3] -> 123, [12,2,3] -> 1223.
    // Used as a key to recognise an ordering. Ambiguous ([1,23] and [12,3] both give 123)
    // and Integer.parseInt fails once the value passes 2147483647.
    private static Integer arrayToLexicographical(ArrayList<Integer> array){
        StringBuilder string = new StringBuilder();
        for(Integer num : array){
            string.append(num.toString());
        }
        return Integer.parseInt(string.toString());
    }

    // Adds every ordering of integers to ans (n! of them): choose what goes into position
    // index by swapping each element from index..end into it, recurse on index + 1, swap
    // back. Repeated numbers give repeated orderings.
    private static void recursivelyGeneratePermutationBySwap(int index, ArrayList<Integer> integers, ArrayList<ArrayList<Integer>> ans){
        if(index == integers.size()){
            // every position is fixed: keep a copy of this ordering
            ans.add(new ArrayList<>(integers));
            return;
        }
        for(int i=index; i < integers.size(); i++){
            // put the element at i into position index
            swapToNumberInAList(index, i, integers);
            recursivelyGeneratePermutationBySwap(index + 1, integers, ans);
            // undo the swap so the next i starts from the same arrangement
            swapToNumberInAList(index, i, integers);
        }
    }

    // Swaps the elements at positions posA and posB.
    private static void swapToNumberInAList(int posA, int posB, ArrayList<Integer> integers){
        int temp = integers.get(posA);
        integers.set(posA, integers.get(posB));
        integers.set(posB, temp);
    }

    // Leftover experiment (its call in main is commented out): prints every ordering using a
    // "taken or not" map instead of swaps - the LC 46 Permutations style. It does not look for
    // the next permutation, and Collectors.toMap throws on repeated numbers.
    private static void approach1(List<Integer> integers) {
        Map<Integer, Boolean> takenOrNot =integers.stream().collect(Collectors.toMap(Function.identity(), i -> false));
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        recursivelyGeneratePermutation(ans, permutation, integers, takenOrNot);
        ans.forEach(System.out::println);
    }

    // Helper of approach1: extend the partial ordering with every number not yet taken,
    // recurse, then un-take it (backtrack).
    private static void recursivelyGeneratePermutation(List<List<Integer>> ans, List<Integer> permutation, List<Integer> integers, Map<Integer, Boolean> takenOrNot) {
        if(permutation.size() == integers.size() ){
            ans.add(new ArrayList<>(permutation));
            return;
        }
        for(Map.Entry<Integer, Boolean> entry : takenOrNot.entrySet()){
            Integer takenElement = entry.getKey();
            boolean ifTaken = entry.getValue();
            if(!ifTaken){
                // take it: mark it used and append it to the partial ordering
                takenOrNot.put(takenElement, true);
                permutation.add(takenElement);
                recursivelyGeneratePermutation(ans, permutation, integers, takenOrNot);
                // un-take it (remove(Object), because takenElement is an Integer)
                permutation.remove(takenElement);
                takenOrNot.put(takenElement, false);
            }
        }
    }
}

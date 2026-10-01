package Practice.Contests.Apr20;

import java.util.HashSet;
import java.util.Set;

// ============================================================================
// LC 3522. Calculate Score After Performing Instructions   [Medium]
// https://leetcode.com/problems/calculate-score-after-performing-instructions/
// Topics: Array, Hash Table, String, Simulation
// ============================================================================
//
// PROBLEM
//   Two arrays of the same length n: instructions[i] is "add" or "jump", and values[i] is
//   a number. Start at index 0 with score 0 and keep going:
//     "add"  at i -> score += values[i], then move on to index i + 1
//     "jump" at i -> move to index i + values[i]; the score does not change
//   The process stops when the index leaves the array (i < 0 or i >= n) or when it is
//   about to run an instruction that was already run before (that one is NOT run again).
//   Return the score at that moment.
//
// EXAMPLES
//   instructions = ["jump","add","add","jump","add","jump"], values = [2,1,3,1,-2,-3]  ->  1
//       0 jumps to 2; 2 adds 3; 3 jumps to 4; 4 adds -2 (score 1); 5 jumps back to 2
//       - already run, so stop
//   instructions = ["jump","add","add"], values = [3,1,1]  ->  0     0 jumps out of the array
//   instructions = ["jump"], values = [0]                  ->  0     jumps to itself: revisit
//
// CONSTRAINTS
//   n == instructions.length == values.length,   1 <= n <= 10^5,
//   instructions[i] is "add" or "jump",   -10^5 <= values[i] <= 10^5
//
// INTUITION
//   Just do what the rules say. The only worry is an endless loop, but each step depends
//   only on the current index, so the first time an index repeats the walk is going round
//   in a circle. Remembering the indices already run in a HashSet catches that in O(1), and
//   it also means every instruction runs at most once, so the walk has at most n steps.
//
// APPROACH (as implemented below)
//   1. score = 0 (a long), visitedOnes = an empty HashSet of indices, i = 0.
//   2. While i has not been run yet AND 0 <= i < n:
//        add i to visitedOnes;
//        "jump" -> i = i + values[i];
//        "add"  -> score = score + values[i], i = i + 1.
//   3. Return score.
//
// DRY RUN   main(): instructions = [jump,add,add,jump,add,jump], values = [2,1,3,1,-2,-3]
//   i=0 jump 2  -> i = 2                score 0
//   i=2 add 3   -> i = 3                score 3
//   i=3 jump 1  -> i = 4                score 3
//   i=4 add -2  -> i = 5                score 1
//   i=5 jump -3 -> i = 2                score 1
//   i=2 is already in visitedOnes -> stop, return 1
//
// COMPLEXITY
//   Time : O(n) - every index is run at most once, and HashSet lookups are O(1) on average
//   Space: O(n) - the visited set
//
// GOTCHAS
//   - The index is marked as run before it is executed and the repeat check comes first,
//     so a revisited instruction is never executed a second time (as the rules require).
//   - score is a long: 10^5 additions of 10^5 add up to 10^10, which overflows an int.
//   - A jump can land below 0 or at n or beyond; the bounds test in the loop condition
//     ends the walk. i + values[i] itself cannot overflow (both are at most 10^5).
//   - A jump by 0 lands on itself, which is an immediate revisit (example 3).
//
// RELATED
//   - LC 141 Linked List Cycle (in this repo) - also follows a walk until a position
//     repeats; there it is done with slow / fast pointers instead of a visited set.
// ============================================================================
public class LC3522CalculateScoreAfterPerformingInstructions {
    public static void main(String[] args) {
        // Example 1 - prints 1
        String[] instructions = {"jump","add","add","jump","add","jump"};
        int[] values = {2,1,3,1,-2,-3};
        System.out.println(calculateScore(instructions, values));
    }

    // Simulates the process and returns the final score.
    public static long calculateScore(String[] instructions, int[] values) {
        // long: the total can reach 10^10
        long score = 0L;
        // indices of the instructions already run
        Set<Integer> visitedOnes = new HashSet<>();
        int i = 0;
        // stop on a revisit or when the index leaves the array
        while(!visitedOnes.contains(i) && i >= 0 && i < values.length){
            visitedOnes.add(i);
            if(instructions[i].equals("jump")){
                // jump: move, score unchanged
                i = i + values[i];
            }else if (instructions[i].equals("add")){
                // add: collect the value, go to the next instruction
                score = score + values[i];
                i = i + 1;
            }
        }
        return score;
    }
}

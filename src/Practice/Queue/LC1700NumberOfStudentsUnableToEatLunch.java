package Practice.Queue;

import java.util.Deque;
import java.util.LinkedList;

// ============================================================================
// LC 1700. Number of Students Unable to Eat Lunch   [Easy]
// https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
// Topics: Array, Stack, Queue, Simulation
// ============================================================================
//
// PROBLEM
//   A cafeteria has as many sandwiches as students. Sandwiches are circular (0) or square (1)
//   and lie in a stack; each student in the queue prefers one type. Repeat: look at the student
//   at the front and the sandwich on top of the stack.
//     - the student wants that sandwich -> takes it and leaves the queue;
//     - otherwise -> walks to the back of the queue, the sandwich stays on top.
//   This stops when no student left in the queue wants the top sandwich. students[j] is the
//   preference of the j-th student (j = 0 is the front), sandwiches[i] the type of the i-th
//   sandwich (i = 0 is the top). Return how many students never get a sandwich.
//
// EXAMPLES
//   students = [1,1,0,0],     sandwiches = [0,1,0,1]    ->  0
//       the line keeps rotating until a 0 reaches the front, and so on - everybody eats
//   students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1] ->  3
//       one 1 and two 0s eat; the third 0 sandwich is on top but only students who want a 1
//       are left
//
// CONSTRAINTS
//   1 <= students.length == sandwiches.length <= 100,   every value is 0 or 1
//
// INTUITION
//   The statement is a simulation, so simulate it: a Deque is the line (look at the front,
//   move the front to the back, remove the front). The only difficulty is knowing when to
//   stop, because if nobody wants the top sandwich the line would rotate forever. This
//   solution stops after a fixed budget of about 4 * n steps and returns the size of the
//   line at that moment - a guess that is not always right (see STATUS).
//
// APPROACH (as implemented below)
//   1. que = the students in order (addLast); i = 0 is the index of the top sandwich;
//      count = steps taken so far.
//   2. While the line is not empty:
//        que.peekFirst() != sandwiches[i]  -> que.addLast(que.pollFirst()): the front student
//                                             goes to the back
//        otherwise                          -> que.pollFirst() (the student eats and leaves)
//                                             and i++ (the next sandwich is on top)
//      After every step: if count > 4 * students.length, give up and return que.size();
//      then count++.
//   3. The line became empty: everybody ate, return 0.
//
// DRY RUN   students = [1,1,0,0], sandwiches = [0,1,0,1]   (example 1, what main() runs)
//   top 0 (i=0): front 1 -> goes to the back          que [1,0,0,1]
//                front 1 -> goes to the back          que [0,0,1,1]
//                front 0 -> eats, i = 1               que [0,1,1]
//   top 1 (i=1): front 0 -> goes to the back          que [1,1,0]
//                front 1 -> eats, i = 2               que [1,0]
//   top 0 (i=2): front 1 -> goes to the back          que [0,1]
//                front 0 -> eats, i = 3               que [1]
//   top 1 (i=3): front 1 -> eats, i = 4               que []
//   the line is empty after 8 steps (the budget is 4 * 4 = 16) -> return 0
//
// STATUS
//   Known issue: the cut-off after about 4 * n steps is a guess. When every student CAN
//   eat, the real process may need many more steps (students sometimes have to rotate past
//   a long block of the wrong type, up to about n^2 / 4 steps in total), so the method stops
//   too early and returns a wrong non-zero count. Checking every input, n <= 12 is always
//   right and the first failures appear at n = 13, e.g.
//     students = [1,1,1,1,1,1,0,0,0,0,0,0,0], sandwiches = [0,1,0,1,0,1,0,1,0,1,0,1,0]
//     returns 1, correct answer 0 (the full process needs 55 steps, the code allows 54).
//   A larger case: students = 50 ones followed by 50 zeros, sandwiches = [0,1] repeated 50
//   times returns 92, correct answer 0 (2600 steps needed, about 400 allowed).
//   The code is left unchanged on purpose.
//   Also: main() calls countStudents on example 1 but does not print the result (it is 0).
//
// COMPLEXITY
//   Time : O(n) - at most about 4 * n steps of O(1) each (only because of the cut-off; an
//          exact simulation could take O(n^2) steps)
//   Space: O(n) - the deque holds the students
//
// GOTCHAS
//   - The order of students hardly matters: a student who wants the top sandwich will reach
//     the front eventually, so the answer depends only on how many students want 0 and how
//     many want 1. Exact O(n) solution: count both types, walk down the sandwich stack and
//     stop at the first sandwich whose type nobody wants any more; the students still in
//     line at that point (n - i) are the answer.
//   - When students really are stuck, the line rotates without anyone leaving, the budget
//     runs out and que.size() is exactly the right answer. The error only happens when the
//     budget runs out while students could still have been served.
//   - No index error at sandwiches[i]: there are as many sandwiches as students, so the line
//     is empty (loop ends) before i reaches the length.
//
// RELATED
//   - LC 2073 Time Needed to Buy Tickets - another queue simulation in which the person at
//     the front goes to the back of the line (LC2073TimeNeededToBuyTickets.java in this
//     folder).
// ============================================================================
public class LC1700NumberOfStudentsUnableToEatLunch {
    public static void main(String[] args) {
        // example 1 - the result (0) is not printed
        int[] students = {1,1,0,0};
        int[] sandwiches = {0,1,0,1};
        countStudents(students, sandwiches);
    }

    // Simulates the lunch line; returns how many students are left without a sandwich.
    // Wrong on some inputs with n >= 13 because of the step cut-off (see STATUS).
    public static int countStudents(int[] students, int[] sandwiches) {

        // the line of students; the front of the deque is the front of the line
        Deque<Integer> que = new LinkedList<>();
        // steps taken so far
        int count = 0;
        // index of the sandwich currently on top of the stack
        int i = 0;
        for (int student : students) {
            que.addLast(student);
        }
        while (!que.isEmpty()){
            if(que.peekFirst() != sandwiches[i]) {
                // does not want it: back of the line
                que.addLast(que.pollFirst());
            }else {
                // takes the sandwich and leaves; the next sandwich is now on top
                que.pollFirst();
                i++;
            }
            // step budget used up: assume the rest of the line is stuck and return its size
            if(count > 4 * students.length) {
                return que.size();
            }
            count ++;
        }
        // everybody ate
        return 0;
    }
}

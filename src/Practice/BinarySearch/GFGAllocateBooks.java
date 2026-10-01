package Practice.BinarySearch;

// ============================================================================
// GFG - Allocate Minimum Pages   [Medium]
// https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
// Topics: Binary Search on the answer, Greedy, Arrays
// ============================================================================
//
// PROBLEM
//   arr[i] is the number of pages in book i and there are k students. Every student gets
//   a block of CONSECUTIVE books (at least one book), and every book goes to exactly one
//   student. A student's load is the total pages of the block. Among all valid splits,
//   find the one whose heaviest load is as small as possible and return that load.
//   If it cannot be done (more students than books) return -1.
//
// EXAMPLES
//   arr = [12,34,67,90], k = 2  ->  113    [12,34,67] | [90] has loads 113 and 90;
//                                          [12] | rest gives 191, [12,34] | [67,90] gives 157
//   arr = [15,17,20],    k = 5  ->  -1     5 students but only 3 books
//
// INTUITION
//   Searching over splits is hopeless, so search over the ANSWER instead. Ask a yes / no
//   question: "can nobody be given more than X pages?" It is easy to answer greedily -
//   give books to the current student while the load stays <= X, and start a new student
//   only when the next book would overflow. That uses the fewest students possible for
//   the limit X; if it is <= k, then X is achievable.
//   A larger X is never harder, so the answers go no, no, ..., yes, yes, ... and binary
//   search finds the first yes. The limit cannot be below the biggest single book (someone
//   must read it) and never needs to exceed the total of all pages (one student reads it all).
//
// APPROACH (as implemented below)
//   1. Guard: return -1 if the allocation is impossible (this condition is wrong - see
//      STATUS).
//   2. low = the biggest book, high = the sum of all books.
//   3. While low <= high: mid = (low + high) / 2.
//        isAllocationPossible(mid) true  -> high = mid - 1   (mid works, try a smaller one)
//        otherwise                       -> low = mid + 1    (mid is too small)
//   4. Return low - the smallest limit that works.
//   isAllocationPossible(barrier, ...) is the greedy check: start with allocatedStudents = 1
//   and pages = 0; for each book, if pages + book > barrier begin a new student
//   (allocatedStudents++, pages = book), else pages += book; finally compare
//   allocatedStudents with noOfStudents.
//
// DRY RUN   arr = [12,34,67,90], k = 2   (the search below the guard - see STATUS)
//   low = 90 (biggest book), high = 203 (all pages)
//   mid=146  [12,34,67] [90]        2 students, fits   -> high = 145
//   mid=117  [12,34,67] [90]        2 students, fits   -> high = 116
//   mid=103  [12,34] [67] [90]      3 students, no     -> low = 104
//   mid=110  [12,34] [67] [90]      3 students, no     -> low = 111
//   mid=113  [12,34,67] [90]        2 students, fits   -> high = 112
//   mid=111  [12,34] [67] [90]      3 students, no     -> low = 112
//   mid=112  [12,34] [67] [90]      3 students, no     -> low = 113
//   low (113) > high (112): stop, return 113
//
// STATUS
//   Known issue: the guard books.length > noOfStudents is inverted - the impossible case is
//   MORE students than books (noOfStudents > books.length). As written:
//     - more books than students (the usual case) always returns -1, so main() prints -1
//       for example 1 where the correct answer is 113;
//     - more students than books is not rejected: example 2 returns 20, correct is -1;
//     - only books == students gives a correct result (the answer is the biggest book).
//   With the guard flipped, the binary search below it returns the right answer.
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   Time : O(n * log(sum of pages)) - about log2(sum - max) rounds of binary search, each
//          running the O(n) greedy check (plus one O(n) pass for the bounds)
//   Space: O(1)
//
// GOTCHAS
//   - The books keep their order (each student reads a contiguous block), so no sorting.
//   - The check asks for AT MOST k students. That is enough: with at least k books a
//     block can always be split further without raising the heaviest load, so the
//     smallest limit for "at most k" is also the answer for exactly k.
//   - low + high >> 1 means (low + high) >> 1, because + binds tighter than >>.
//
// RELATED
//   - LC 1011 Capacity To Ship Packages Within D Days - the same problem in disguise:
//     packages shipped in order, minimise the heaviest day.
//   - LC 2616 Minimize the Maximum Difference of Pairs (in this repo) - another
//     "binary search the answer, greedy check" problem.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet (Binary Search); TUF article, also on
//   GFG, InterviewBit and Coding Ninjas). Also known as 'Allocate Books'.
//   Closest LeetCode problem: LC 410 Split Array Largest Sum
// ============================================================================
public class GFGAllocateBooks {
    public static void main(String[] args) {
        // GFG example 1: prints -1, but the correct answer is 113 - see STATUS
        // books with pages count
        int[] books = {12, 34, 67, 90};
        // no of students
        int noOfStudents = 2;
        System.out.println(binarySearch(books, noOfStudents));
    }

    // Returns the smallest possible "heaviest load", or -1 if the allocation is impossible.
    public static int binarySearch(int[] books, int noOfStudents){

        // BUG (see STATUS): inverted. Impossible means MORE students than books
        // (noOfStudents > books.length); this rejects the normal case instead.
        if(books.length > noOfStudents){
            return -1;
        }

        // the answer lies between the biggest single book and the total of all pages
        int low = 0;
        int high = 0;
        for (int book : books){
            low = Math.max(low, book);
            high += book;
        }
        int mid;
        while (low <= high){
            // (low + high) / 2 - the limit on pages per student that we try next
            mid = low + high >> 1;
            if(isAllocationPossible(mid, books, noOfStudents)){
                // mid is enough: remember it by looking for something smaller
                high = mid - 1;
            }else {
                // mid is too small: the limit must be larger
                low = mid + 1;
            }
        }
        // low ends on the smallest limit for which the allocation works
        return low;
    }

    // Greedy check: can the books, in order, be cut into at most noOfStudents blocks of
    // at most barrier pages each?
    private static boolean isAllocationPossible(int barrier, int[] books, int noOfStudents) {
        int allocatedStudents = 1;
        int pages = 0;
        for (int book : books) {
            // one book alone exceeds the limit (never true here: mid >= the biggest book)
            if (book > barrier)
                return false;
            // this book would overflow the current student: start a new student with it
            if (pages + book > barrier) {
                allocatedStudents += 1;
                pages = book;
            }else {
                pages += book;
            }
        }
        return allocatedStudents <= noOfStudents;
    }
}

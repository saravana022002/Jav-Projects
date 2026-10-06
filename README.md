# Java Problem Solving

My LeetCode and DSA practice in Java.

**267 LeetCode problems** (104 Easy, 133 Medium, 30 Hard) + **16 problems from GeeksforGeeks and interviews**, in 324 solution files, plus **189 practice skeletons** waiting to be solved.

## How to read a solution

Every file in `src/Practice/` and in the book-chapter folders of `src/Leetcode/` opens with a header that teaches the problem on its own (in `src/Leetcode/` it is Javadoc, so IntelliJ also shows it on hover and in Quick Documentation):

- **the link** to the problem and its difficulty (plus, in `src/Leetcode/ChNN...`, the book chapter and Practice number)
- **PROBLEM** in plain words, **EXAMPLES** and **CONSTRAINTS**
- **INTUITION** - why the idea works - and **APPROACH**, step by step, using the names in the code
- **DRY RUN** on a small input, **COMPLEXITY** with the reason, and **GOTCHAS**
- **STATUS** when the code has a known bug or is unfinished (the code itself is left as I wrote it)
- **SEE ALSO** when the same problem is solved in another file

Every file has a `main()` with a sample input, so you can run it directly from IntelliJ.

Every file in `src/Leetcode/` carries its own test cases (the official examples plus edge cases): run the class and `main()` prints PASS or FAIL for each case, then a summary line. In your solved files your own `main()` code runs first; the tests live in the TESTS section at the end of the class.

## Layout

| Folder | What is in it |
|---|---|
| `src/Practice/<Topic>/` | 258 solutions grouped by technique |
| `src/Leetcode/ChNN<Chapter>/` | 240 solutions grouped by the chapters of *Algorithmic Thinking* |
| `src/Leetcode/<Daily, Contest191, ...>/` | 15 solutions that are not in the book |

File names: `LC<number><Title>.java` for LeetCode, `GFG<Title>.java` for GeeksforGeeks, `Interview<Title>.java` for interview questions. When a problem is solved more than once, a suffix tells the versions apart: `LC1TwoSumHashMap.java`, `LC1TwoSumThreeApproaches.java`, or `V1` / `V2` when both use the same idea.

Status marks: :warning: known bug - :construction: unfinished (details in the file's STATUS section) - :white_large_square: not solved yet (an empty practice skeleton: description, variant stubs with time/space targets and the book chapters each variant needs, and tests in `main()` that pass once your solution is right).

## Topics

| Topic | Files | Easy / Medium / Hard | Focus |
|---|---|---|---|
| [Arrays](#arrays) | 26 | 22 / 4 / 0 | in-place tricks, scans, simulation |
| [Backtracking](#backtracking) | 10 | 0 / 7 / 3 | try, recurse, undo |
| [BinarySearch](#binarysearch) | 12 | 2 / 8 / 2 | search on sorted data or on the answer |
| [BinaryTree](#binarytree) | 19 | 6 / 11 / 2 | traversals, views, construction |
| [BitManipulation](#bitmanipulation) | 3 | 2 / 1 / 0 | masks, XOR, counting bits |
| [BST](#bst) | 9 | 4 / 4 / 1 | ordered-tree properties |
| [Contests](#contests) | 12 | 2 / 9 / 1 | weekly / biweekly contest problems |
| [Design](#design) | 9 | 3 / 4 / 2 | classes with an API (LRU, MinStack, ...) |
| [DynamicProgramming](#dynamicprogramming) | 3 | 0 / 2 / 1 | states and transitions |
| [Graph](#graph) | 5 | 0 / 4 / 1 | BFS / DFS / topological order |
| [Greedy](#greedy) | 18 | 5 / 11 / 2 | locally best choice that is provably safe |
| [HashMap](#hashmap) | 19 | 11 / 8 / 0 | lookup, counting, grouping |
| [Heap](#heap) | 9 | 2 / 4 / 3 | priority queues, top-k |
| [Intervals](#intervals) | 1 | 0 / 1 / 0 | sorting and merging ranges |
| [LinkedList](#linkedlist) | 17 | 7 / 9 / 1 | pointer manipulation |
| [Math](#math) | 16 | 8 / 6 / 2 | number theory, digits, formulas |
| [Matrix](#matrix) | 5 | 0 / 5 / 0 | 2-D grids |
| [PrefixSum](#prefixsum) | 4 | 0 / 4 / 0 | range sums in O(1) |
| [Queue](#queue) | 2 | 2 / 0 / 0 | FIFO simulation |
| [Recursion](#recursion) | 2 | 0 / 2 / 0 | divide into smaller calls |
| [SlidingWindow](#slidingwindow) | 7 | 1 / 5 / 1 | grow / shrink a window |
| [Sorting](#sorting) | 8 | 3 / 4 / 1 | sort first, or implement a sort |
| [Stack](#stack) | 15 | 2 / 11 / 2 | monotonic stacks, parsing |
| [Strings](#strings) | 12 | 6 / 6 / 0 | character processing |
| [TwoPointers](#twopointers) | 15 | 5 / 8 / 2 | pointers moving toward / with each other |
| [Book chapters](#leetcode-sets-by-book-chapter-srcleetcode) | 255 | | src/Leetcode, by chapter of the book |

## Practice problems (`src/Practice`)

### Arrays

[`src/Practice/Arrays`](src/Practice/Arrays) - in-place tricks, scans, simulation

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 31 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | Medium | [LC31NextPermutationBruteForce](src/Practice/Arrays/LC31NextPermutationBruteForce.java) | :warning: approach2 (run by main) is wrong/crashes on most inputs: Integer == comparison (only ... |
| 31 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | Medium | [LC31NextPermutationOptimal](src/Practice/Arrays/LC31NextPermutationOptimal.java) |  |
| 53 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | Medium | [LC53MaximumSubarray](src/Practice/Arrays/LC53MaximumSubarray.java) |  |
| 66 | [Plus One](https://leetcode.com/problems/plus-one/) | Easy | [LC66PlusOneCopyArray](src/Practice/Arrays/LC66PlusOneCopyArray.java) |  |
| 66 | [Plus One](https://leetcode.com/problems/plus-one/) | Easy | [LC66PlusOneInPlace](src/Practice/Arrays/LC66PlusOneInPlace.java) |  |
| 118 | [Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) | Easy | [LC118PascalsTriangleCombinationFormula](src/Practice/Arrays/LC118PascalsTriangleCombinationFormula.java) |  |
| 118 | [Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) | Easy | [LC118PascalsTrianglePreviousRow](src/Practice/Arrays/LC118PascalsTrianglePreviousRow.java) | :warning: approachOptimal (unused) is wrong/incomplete: N=5 gives ... |
| 121 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Easy | [LC121BestTimeToBuyAndSellStock](src/Practice/Arrays/LC121BestTimeToBuyAndSellStock.java) |  |
| 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | [LC169MajorityElementBoyerMoore](src/Practice/Arrays/LC169MajorityElementBoyerMoore.java) |  |
| 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | [LC169MajorityElementHashMap](src/Practice/Arrays/LC169MajorityElementHashMap.java) | :warning: majorityElementOptimal (unused) skips nums[0]: returns 2 for [3,2,3], 1 for ... |
| 229 | [Majority Element II](https://leetcode.com/problems/majority-element-ii/) | Medium | [LC229MajorityElementII](src/Practice/Arrays/LC229MajorityElementII.java) |  |
| 448 | [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) | Easy | [LC448FindAllNumbersDisappearedInAnArray](src/Practice/Arrays/LC448FindAllNumbersDisappearedInAnArray.java) | :warning: findDisappearedNumbersBetter skips nums[0] (loop starts at i=1): wrong whenever ... |
| 485 | [Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/) | Easy | [LC485MaxConsecutiveOnesV1](src/Practice/Arrays/LC485MaxConsecutiveOnesV1.java) |  |
| 485 | [Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/) | Easy | [LC485MaxConsecutiveOnesV2](src/Practice/Arrays/LC485MaxConsecutiveOnesV2.java) |  |
| 645 | [Set Mismatch](https://leetcode.com/problems/set-mismatch/) | Easy | [LC645SetMismatch](src/Practice/Arrays/LC645SetMismatch.java) |  |
| 941 | [Valid Mountain Array](https://leetcode.com/problems/valid-mountain-array/) | Easy | [LC941ValidMountainArray](src/Practice/Arrays/LC941ValidMountainArray.java) |  |
| 1470 | [Shuffle the Array](https://leetcode.com/problems/shuffle-the-array/) | Easy | [LC1470ShuffleTheArray](src/Practice/Arrays/LC1470ShuffleTheArray.java) |  |
| 1475 | [Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) | Easy | [LC1475FinalPricesWithASpecialDiscountInAShop](src/Practice/Arrays/LC1475FinalPricesWithASpecialDiscountInAShop.java) |  |
| 1550 | [Three Consecutive Odds](https://leetcode.com/problems/three-consecutive-odds/) | Easy | [LC1550ThreeConsecutiveOdds](src/Practice/Arrays/LC1550ThreeConsecutiveOdds.java) |  |
| 1929 | [Concatenation of Array](https://leetcode.com/problems/concatenation-of-array/) | Easy | [LC1929ConcatenationOfArray](src/Practice/Arrays/LC1929ConcatenationOfArray.java) |  |
| 2016 | [Maximum Difference Between Increasing Elements](https://leetcode.com/problems/maximum-difference-between-increasing-elements/) | Easy | [LC2016MaximumDifferenceBetweenIncreasingElements](src/Practice/Arrays/LC2016MaximumDifferenceBetweenIncreasingElements.java) |  |
| 2154 | [Keep Multiplying Found Values by Two](https://leetcode.com/problems/keep-multiplying-found-values-by-two/) | Easy | [LC2154KeepMultiplyingFoundValuesByTwo](src/Practice/Arrays/LC2154KeepMultiplyingFoundValuesByTwo.java) |  |
| 2200 | [Find All K-Distant Indices in an Array](https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/) | Easy | [LC2200FindAllKDistantIndicesInAnArray](src/Practice/Arrays/LC2200FindAllKDistantIndicesInAnArray.java) |  |
| 3069 | [Distribute Elements Into Two Arrays I](https://leetcode.com/problems/distribute-elements-into-two-arrays-i/) | Easy | [LC3069DistributeElementsIntoTwoArraysI](src/Practice/Arrays/LC3069DistributeElementsIntoTwoArraysI.java) |  |
| 3354 | [Make Array Elements Equal to Zero](https://leetcode.com/problems/make-array-elements-equal-to-zero/) | Easy | [LC3354MakeArrayElementsEqualToZero](src/Practice/Arrays/LC3354MakeArrayElementsEqualToZero.java) |  |
| 3423 | [Maximum Difference Between Adjacent Elements in a Circular Array](https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/) | Easy | [LC3423MaximumDifferenceBetweenAdjacentElementsInACircularArray](src/Practice/Arrays/LC3423MaximumDifferenceBetweenAdjacentElementsInACircularArray.java) |  |

### Backtracking

[`src/Practice/Backtracking`](src/Practice/Backtracking) - try, recurse, undo

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 22 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | [LC22GenerateParentheses](src/Practice/Backtracking/LC22GenerateParentheses.java) |  |
| 37 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) | Hard | [LC37SudokuSolver](src/Practice/Backtracking/LC37SudokuSolver.java) |  |
| 39 | [Combination Sum](https://leetcode.com/problems/combination-sum/) | Medium | [LC39CombinationSum](src/Practice/Backtracking/LC39CombinationSum.java) |  |
| 46 | [Permutations](https://leetcode.com/problems/permutations/) | Medium | [LC46Permutations](src/Practice/Backtracking/LC46Permutations.java) |  |
| 51 | [N-Queens](https://leetcode.com/problems/n-queens/) | Hard | [LC51NQueens](src/Practice/Backtracking/LC51NQueens.java) |  |
| 52 | [N-Queens II](https://leetcode.com/problems/n-queens-ii/) | Hard | [LC52NQueensII](src/Practice/Backtracking/LC52NQueensII.java) |  |
| 78 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | [LC78Subsets](src/Practice/Backtracking/LC78Subsets.java) | :warning: main() prints the working list ans (always [] after backtracking) instead of the ... |
| 90 | [Subsets II](https://leetcode.com/problems/subsets-ii/) | Medium | [LC90SubsetsII](src/Practice/Backtracking/LC90SubsetsII.java) |  |
| 131 | [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) | Medium | [LC131PalindromePartitioning](src/Practice/Backtracking/LC131PalindromePartitioning.java) |  |
| GFG | [Rat in a Maze](https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1) | Medium | [GFGRatInAMazeProblemI](src/Practice/Backtracking/GFGRatInAMazeProblemI.java) | :warning: Paths come out in R,D,L,U order (not lexicographic as GFG requires) and a walled ... |

### BinarySearch

[`src/Practice/BinarySearch`](src/Practice/BinarySearch) - search on sorted data or on the answer

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | [LC4MedianOfTwoSortedArrays](src/Practice/BinarySearch/LC4MedianOfTwoSortedArrays.java) |  |
| 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | [LC33SearchInRotatedSortedArray](src/Practice/BinarySearch/LC33SearchInRotatedSortedArray.java) |  |
| 69 | [Sqrt(x)](https://leetcode.com/problems/sqrtx/) | Easy | [LC69SqrtX](src/Practice/BinarySearch/LC69SqrtX.java) |  |
| 74 | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) | Medium | [LC74SearchA2DMatrix](src/Practice/BinarySearch/LC74SearchA2DMatrix.java) | :warning: A target smaller than matrix[0][0] ends the row search with rowEnd = -1 and the ... |
| 436 | [Find Right Interval](https://leetcode.com/problems/find-right-interval/) | Medium | [LC436FindRightInterval](src/Practice/BinarySearch/LC436FindRightInterval.java) | :warning: Speed only: the sorted wrapper list is a LinkedList, so get() inside binarySearch is ... |
| 540 | [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) | Medium | [LC540SingleElementInASortedArray](src/Practice/BinarySearch/LC540SingleElementInASortedArray.java) |  |
| 704 | [Binary Search](https://leetcode.com/problems/binary-search/) | Easy | [LC704BinarySearch](src/Practice/BinarySearch/LC704BinarySearch.java) |  |
| 2300 | [Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/) | Medium | [LC2300SuccessfulPairsOfSpellsAndPotions](src/Practice/BinarySearch/LC2300SuccessfulPairsOfSpellsAndPotions.java) |  |
| 2528 | [Maximize the Minimum Powered City](https://leetcode.com/problems/maximize-the-minimum-powered-city/) | Hard | [LC2528MaximizeTheMinimumPoweredCity](src/Practice/BinarySearch/LC2528MaximizeTheMinimumPoweredCity.java) | :construction: Stub: maxPower() only builds the difference array cnt[] and returns 0; the prefix ... |
| 2616 | [Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/) | Medium | [LC2616MinimizeTheMaximumDifferenceOfPairs](src/Practice/BinarySearch/LC2616MinimizeTheMaximumDifferenceOfPairs.java) |  |
| GFG | [Allocate Minimum Pages](https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1) | Medium | [GFGAllocateBooks](src/Practice/BinarySearch/GFGAllocateBooks.java) | :warning: Guard books.length > noOfStudents is inverted: example 1 ([12,34,67,90], k=2) prints ... |
| GFG | [K-th of Two Sorted Arrays](https://www.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1) | Medium | [GFGKthElementOfTwoSortedArrays](src/Practice/BinarySearch/GFGKthElementOfTwoSortedArrays.java) |  |

### BinaryTree

[`src/Practice/BinaryTree`](src/Practice/BinaryTree) - traversals, views, construction

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 94 | [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/) | Easy | [LC94BinaryTreeInorderTraversal](src/Practice/BinaryTree/LC94BinaryTreeInorderTraversal.java) |  |
| 100 | [Same Tree](https://leetcode.com/problems/same-tree/) | Easy | [LC100SameTree](src/Practice/BinaryTree/LC100SameTree.java) |  |
| 101 | [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) | Easy | [LC101SymmetricTree](src/Practice/BinaryTree/LC101SymmetricTree.java) |  |
| 102 | [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) | Medium | [LC102BinaryTreeLevelOrderTraversal](src/Practice/BinaryTree/LC102BinaryTreeLevelOrderTraversal.java) |  |
| 103 | [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) | Medium | [LC103BinaryTreeZigzagLevelOrderTraversal](src/Practice/BinaryTree/LC103BinaryTreeZigzagLevelOrderTraversal.java) |  |
| 104 | [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | Easy | [LC104MaximumDepthOfBinaryTree](src/Practice/BinaryTree/LC104MaximumDepthOfBinaryTree.java) |  |
| 105 | [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | Medium | [LC105ConstructBinaryTreeFromPreorderAndInorderTraversal](src/Practice/BinaryTree/LC105ConstructBinaryTreeFromPreorderAndInorderTraversal.java) |  |
| 106 | [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) | Medium | [LC106ConstructBinaryTreeFromInorderAndPostorderTraversal](src/Practice/BinaryTree/LC106ConstructBinaryTreeFromInorderAndPostorderTraversal.java) |  |
| 110 | [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) | Easy | [LC110BalancedBinaryTree](src/Practice/BinaryTree/LC110BalancedBinaryTree.java) |  |
| 114 | [Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) | Medium | [LC114FlattenBinaryTreeToLinkedList](src/Practice/BinaryTree/LC114FlattenBinaryTreeToLinkedList.java) |  |
| 116 | [Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/) | Medium | [LC116PopulatingNextRightPointersInEachNode](src/Practice/BinaryTree/LC116PopulatingNextRightPointersInEachNode.java) | :warning: connect(null) throws NullPointerException for the empty tree (official example 2 ... |
| 124 | [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) | Hard | [LC124BinaryTreeMaximumPathSum](src/Practice/BinaryTree/LC124BinaryTreeMaximumPathSum.java) |  |
| 199 | [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) | Medium | [LC199BinaryTreeRightSideView](src/Practice/BinaryTree/LC199BinaryTreeRightSideView.java) | :construction: no rightSideView(TreeNode) entry method and main() is empty; only the recursive ... |
| 236 | [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | Medium | [LC236LowestCommonAncestorOfABinaryTree](src/Practice/BinaryTree/LC236LowestCommonAncestorOfABinaryTree.java) |  |
| 543 | [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) | Easy | [LC543DiameterOfBinaryTree](src/Practice/BinaryTree/LC543DiameterOfBinaryTree.java) |  |
| 662 | [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) | Medium | [LC662MaximumWidthOfBinaryTree](src/Practice/BinaryTree/LC662MaximumWidthOfBinaryTree.java) |  |
| 987 | [Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) | Hard | [LC987VerticalOrderTraversalOfABinaryTree](src/Practice/BinaryTree/LC987VerticalOrderTraversalOfABinaryTree.java) | :warning: flatten loop does list.get(list.size() - 1).add(...) on the column's TreeMap (a key ... |
| GFG | [Bottom View of Binary Tree](https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1) | Medium | [GFGBottomViewOfBinaryTree](src/Practice/BinaryTree/GFGBottomViewOfBinaryTree.java) |  |
| GFG | [Top View of Binary Tree](https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1) | Medium | [GFGTopViewOfBinaryTree](src/Practice/BinaryTree/GFGTopViewOfBinaryTree.java) |  |

### BitManipulation

[`src/Practice/BitManipulation`](src/Practice/BitManipulation) - masks, XOR, counting bits

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 136 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | [LC136SingleNumber](src/Practice/BitManipulation/LC136SingleNumber.java) |  |
| 2438 | [Range Product Queries of Powers](https://leetcode.com/problems/range-product-queries-of-powers/) | Medium | [LC2438RangeProductQueriesOfPowers](src/Practice/BitManipulation/LC2438RangeProductQueriesOfPowers.java) |  |
| 3370 | [Smallest Number With All Set Bits](https://leetcode.com/problems/smallest-number-with-all-set-bits/) | Easy | [LC3370SmallestNumberWithAllSetBits](src/Practice/BitManipulation/LC3370SmallestNumberWithAllSetBits.java) |  |

### BST

[`src/Practice/BST`](src/Practice/BST) - ordered-tree properties

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 98 | [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) | Medium | [LC98ValidateBinarySearchTree](src/Practice/BST/LC98ValidateBinarySearchTree.java) | :warning: int sentinel bounds (Integer.MIN_VALUE / MAX_VALUE) with strict comparisons reject ... |
| 108 | [Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/) | Easy | [LC108ConvertSortedArrayToBinarySearchTree](src/Practice/BST/LC108ConvertSortedArrayToBinarySearchTree.java) | :warning: Left recursion is constructBst(nums, left, mid) instead of (left, mid - 1), so every ... |
| 230 | [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | Medium | [LC230KthSmallestElementInABST](src/Practice/BST/LC230KthSmallestElementInABST.java) |  |
| 235 | [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | Medium | [LC235LowestCommonAncestorOfABinarySearchTree](src/Practice/BST/LC235LowestCommonAncestorOfABinarySearchTree.java) |  |
| 653 | [Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/) | Easy | [LC653TwoSumIVInputIsABST](src/Practice/BST/LC653TwoSumIVInputIsABST.java) |  |
| 700 | [Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/) | Easy | [LC700SearchInABinarySearchTree](src/Practice/BST/LC700SearchInABinarySearchTree.java) |  |
| 1008 | [Construct Binary Search Tree from Preorder Traversal](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/) | Medium | [LC1008ConstructBinarySearchTreeFromPreorderTraversal](src/Practice/BST/LC1008ConstructBinarySearchTreeFromPreorderTraversal.java) | :warning: static index is never reset, so any second call in the same JVM returns null or a ... |
| 1373 | [Maximum Sum BST in Binary Tree](https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/) | Hard | [LC1373MaximumSumBSTInBinaryTree](src/Practice/BST/LC1373MaximumSumBSTInBinaryTree.java) |  |
| GFG | [Floor in BST](https://www.geeksforgeeks.org/problems/closest-neighbor-in-bst/1) | Easy | [GFGFloorInBST](src/Practice/BST/GFGFloorInBST.java) | :warning: No branch for root.val == x, so floor() loops forever whenever x is a key in the ... |

### Contests

[`src/Practice/Contests`](src/Practice/Contests) - weekly / biweekly contest problems

| # | Problem | Difficulty | Contest | Solution | Status |
|---|---|---|---|---|---|
| 3507 | [Minimum Pair Removal to Sort Array I](https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/) | Easy | Misc | [LC3507MinimumPairRemovalToSortArrayI](src/Practice/Contests/Misc/LC3507MinimumPairRemovalToSortArrayI.java) | :warning: The method does not simulate the min-sum pair merging: main()'s sorted input {1,2,2} ... |
| 3508 | [Implement Router](https://leetcode.com/problems/implement-router/) | Medium | Misc | [LC3508ImplementRouter](src/Practice/Contests/Misc/LC3508ImplementRouter.java) | :warning: Eviction in addPacket drops the oldest packet from the queue and key set but not its ... |
| 3522 | [Calculate Score After Performing Instructions](https://leetcode.com/problems/calculate-score-after-performing-instructions/) | Medium | Apr20 | [LC3522CalculateScoreAfterPerformingInstructions](src/Practice/Contests/Apr20/LC3522CalculateScoreAfterPerformingInstructions.java) |  |
| 3523 | [Make Array Non-decreasing](https://leetcode.com/problems/make-array-non-decreasing/) | Medium | Apr20 | [LC3523MakeArrayNonDecreasing](src/Practice/Contests/Apr20/LC3523MakeArrayNonDecreasing.java) |  |
| 3527 | [Find the Most Common Response](https://leetcode.com/problems/find-the-most-common-response/) | Medium | Contest155 | [LC3527FindTheMostCommonResponse](src/Practice/Contests/Contest155/LC3527FindTheMostCommonResponse.java) |  |
| 3529 | [Count Cells in Overlapping Horizontal and Vertical Substrings](https://leetcode.com/problems/count-cells-in-overlapping-horizontal-and-vertical-substrings/) | Medium | Contest155 | [LC3529CountCellsInOverlappingHorizontalAndVerticalSubstrings](src/Practice/Contests/Contest155/LC3529CountCellsInOverlappingHorizontalAndVerticalSubstrings.java) | :warning: Speed only: brute force O(m*n*L); on an all-'a' grid of 99856 cells L=1000 took 0.3 ... |
| 3572 | [Maximize Y-Sum by Picking a Triplet of Distinct X-Values](https://leetcode.com/problems/maximize-ysum-by-picking-a-triplet-of-distinct-xvalues/) | Medium | Contest158 | [LC3572MaximizeYSumByPickingATripletOfDistinctXValues](src/Practice/Contests/Contest158/LC3572MaximizeYSumByPickingATripletOfDistinctXValues.java) |  |
| 3573 | [Best Time to Buy and Sell Stock V](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/) | Medium | Contest158 | [LC3573BestTimeToBuyAndSellStockV](src/Practice/Contests/Contest158/LC3573BestTimeToBuyAndSellStockV.java) | :warning: DP is wrong: main() prints 16 and 49 instead of the LC answers 14 and 36. (1) the ... |
| 3574 | [Maximize Subarray GCD Score](https://leetcode.com/problems/maximize-subarray-gcd-score/) | Hard | Contest158 | [LC3574MaximizeSubarrayGCDScore](src/Practice/Contests/Contest158/LC3574MaximizeSubarrayGCDScore.java) | :warning: int overflow: maxScore/currentScore are int while len*gcd can exceed 2^31 ... |
| 3736 | [Minimum Moves to Equal Array Elements III](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/) | Easy | Contest169 | [LC3736MinimumMovesToEqualArrayElementsIII](src/Practice/Contests/Contest169/LC3736MinimumMovesToEqualArrayElementsIII.java) |  |
| 3737 | [Count Subarrays With Majority Element I](https://leetcode.com/problems/count-subarrays-with-majority-element-i/) | Medium | Contest169 | [LC3737CountSubarraysWithMajorityElementI](src/Practice/Contests/Contest169/LC3737CountSubarraysWithMajorityElementI.java) |  |
| 3738 | [Longest Non-Decreasing Subarray After Replacing at Most One Element](https://leetcode.com/problems/longest-non-decreasing-subarray-after-replacing-at-most-one-element/) | Medium | Contest169 | [LC3738LongestNonDecreasingSubarrayAfterReplacingAtMostOneElement](src/Practice/Contests/Contest169/LC3738LongestNonDecreasingSubarrayAfterReplacingAtMostOneElement.java) | :warning: Both methods are wrong: main()'s own input {3,-4,-2} prints 2 (correct 3: replace ... |

### Design

[`src/Practice/Design`](src/Practice/Design) - classes with an API (LRU, MinStack, ...)

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | [LC146LRUCache](src/Practice/Design/LC146LRUCache.java) |  |
| 155 | [Min Stack](https://leetcode.com/problems/min-stack/) | Medium | [LC155MinStack](src/Practice/Design/LC155MinStack.java) |  |
| 173 | [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) | Medium | [LC173BinarySearchTreeIterator](src/Practice/Design/LC173BinarySearchTreeIterator.java) |  |
| 225 | [Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/) | Easy | [LC225ImplementStackUsingQueues](src/Practice/Design/LC225ImplementStackUsingQueues.java) |  |
| 232 | [Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) | Easy | [LC232ImplementQueueUsingStacks](src/Practice/Design/LC232ImplementQueueUsingStacks.java) |  |
| 297 | [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | Hard | [LC297SerializeAndDeserializeBinaryTree](src/Practice/Design/LC297SerializeAndDeserializeBinaryTree.java) | :warning: deserialize() throws ArrayIndexOutOfBoundsException on every non-empty input (loop ... |
| 460 | [LFU Cache](https://leetcode.com/problems/lfu-cache/) | Hard | [LC460LFUCache](src/Practice/Design/LC460LFUCache.java) |  |
| 705 | [Design HashSet](https://leetcode.com/problems/design-hashset/) | Easy | [LC705DesignHashSet](src/Practice/Design/LC705DesignHashSet.java) |  |
| 1865 | [Finding Pairs With a Certain Sum](https://leetcode.com/problems/finding-pairs-with-a-certain-sum/) | Medium | [LC1865FindingPairsWithACertainSum](src/Practice/Design/LC1865FindingPairsWithACertainSum.java) |  |

### DynamicProgramming

[`src/Practice/DynamicProgramming`](src/Practice/DynamicProgramming) - states and transitions

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 62 | [Unique Paths](https://leetcode.com/problems/unique-paths/) | Medium | [LC62UniquePaths](src/Practice/DynamicProgramming/LC62UniquePaths.java) |  |
| 139 | [Word Break](https://leetcode.com/problems/word-break/) | Medium | [LC139WordBreak](src/Practice/DynamicProgramming/LC139WordBreak.java) |  |
| 1235 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | Hard | [LC1235MaximumProfitInJobScheduling](src/Practice/DynamicProgramming/LC1235MaximumProfitInJobScheduling.java) |  |

### Graph

[`src/Practice/Graph`](src/Practice/Graph) - BFS / DFS / topological order

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 133 | [Clone Graph](https://leetcode.com/problems/clone-graph/) | Medium | [LC133CloneGraph](src/Practice/Graph/LC133CloneGraph.java) | :warning: cloneGraph(null) (empty graph, LC example 3) throws NPE instead of returning null ... |
| 207 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | Medium | [LC207CourseSchedule](src/Practice/Graph/LC207CourseSchedule.java) | :warning: canFinish() (Kahn) is correct; the unused private canFinishDfs() uses undirected ... |
| 994 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | Medium | [LC994RottingOranges](src/Practice/Graph/LC994RottingOranges.java) |  |
| 1061 | [Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/) | Medium | [LC1061LexicographicallySmallestEquivalentString](src/Practice/Graph/LC1061LexicographicallySmallestEquivalentString.java) | :construction: Stub: smallestEquivalentString() always returns "" and main() is empty. |
| 1298 | [Maximum Candies You Can Get from Boxes](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/) | Hard | [LC1298MaximumCandiesYouCanGetFromBoxes](src/Practice/Graph/LC1298MaximumCandiesYouCanGetFromBoxes.java) |  |

### Greedy

[`src/Practice/Greedy`](src/Practice/Greedy) - locally best choice that is provably safe

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 135 | [Candy](https://leetcode.com/problems/candy/) | Hard | [LC135Candy](src/Practice/Greedy/LC135Candy.java) |  |
| 322 | [Coin Change](https://leetcode.com/problems/coin-change/) | Medium | [LC322CoinChange](src/Practice/Greedy/LC322CoinChange.java) | :warning: Largest-coin-first greedy is wrong for non-canonical coin sets: coins [1,3,4] amount ... |
| 455 | [Assign Cookies](https://leetcode.com/problems/assign-cookies/) | Easy | [LC455AssignCookies](src/Practice/Greedy/LC455AssignCookies.java) |  |
| 605 | [Can Place Flowers](https://leetcode.com/problems/can-place-flowers/) | Easy | [LC605CanPlaceFlowers](src/Practice/Greedy/LC605CanPlaceFlowers.java) |  |
| 1432 | [Max Difference You Can Get From Changing an Integer](https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/) | Medium | [LC1432MaxDifferenceYouCanGetFromChangingAnInteger](src/Practice/Greedy/LC1432MaxDifferenceYouCanGetFromChangingAnInteger.java) |  |
| 1526 | [Minimum Number of Increments on Subarrays to Form a Target Array](https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/) | Hard | [LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray](src/Practice/Greedy/LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray.java) |  |
| 1578 | [Minimum Time to Make Rope Colorful](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/) | Medium | [LC1578MinimumTimeToMakeRopeColorful](src/Practice/Greedy/LC1578MinimumTimeToMakeRopeColorful.java) |  |
| 1710 | [Maximum Units on a Truck](https://leetcode.com/problems/maximum-units-on-a-truck/) | Easy | [LC1710MaximumUnitsOnATruck](src/Practice/Greedy/LC1710MaximumUnitsOnATruck.java) |  |
| 2294 | [Partition Array Such That Maximum Difference Is K](https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/) | Medium | [LC2294PartitionArraySuchThatMaximumDifferenceIsK](src/Practice/Greedy/LC2294PartitionArraySuchThatMaximumDifferenceIsK.java) |  |
| 2566 | [Maximum Difference by Remapping a Digit](https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/) | Easy | [LC2566MaximumDifferenceByRemappingADigit](src/Practice/Greedy/LC2566MaximumDifferenceByRemappingADigit.java) |  |
| 3085 | [Minimum Deletions to Make String K-Special](https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/) | Medium | [LC3085MinimumDeletionsToMakeStringKSpecial](src/Practice/Greedy/LC3085MinimumDeletionsToMakeStringKSpecial.java) |  |
| 3170 | [Lexicographically Minimum String After Removing Stars](https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/) | Medium | [LC3170LexicographicallyMinimumStringAfterRemovingStars](src/Practice/Greedy/LC3170LexicographicallyMinimumStringAfterRemovingStars.java) |  |
| 3443 | [Maximum Manhattan Distance After K Changes](https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/) | Medium | [LC3443MaximumManhattanDistanceAfterKChanges](src/Practice/Greedy/LC3443MaximumManhattanDistanceAfterKChanges.java) |  |
| 3796 | [Find Maximum Value in a Constrained Sequence](https://leetcode.com/problems/find-maximum-value-in-a-constrained-sequence/) | Medium | [LC3796FindMaximumValueInAConstrainedSequence](src/Practice/Greedy/LC3796FindMaximumValueInAConstrainedSequence.java) | :warning: findMaxVal is an incorrect single forward fill: restrictions are assigned (not ... |
| GFG | [Fractional Knapsack](https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1) | Easy | [GFGFractionalKnapsack](src/Practice/Greedy/GFGFractionalKnapsack.java) |  |
| GFG | [Job Sequencing Problem](https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1) | Medium | [GFGJobSequencingProblem](src/Practice/Greedy/GFGJobSequencingProblem.java) |  |
| GFG | [Minimum Platforms](https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1) | Medium | [GFGMinimumPlatforms](src/Practice/Greedy/GFGMinimumPlatforms.java) |  |
| GFG | [N Meetings in One Room](https://www.geeksforgeeks.org/problems/activity-selection-1587115620/1) | Medium | [GFGNMeetingsInOneRoom](src/Practice/Greedy/GFGNMeetingsInOneRoom.java) |  |

### HashMap

[`src/Practice/HashMap`](src/Practice/HashMap) - lookup, counting, grouping

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | [LC1TwoSumComplementMap](src/Practice/HashMap/LC1TwoSumComplementMap.java) |  |
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | [LC1TwoSumHashMap](src/Practice/HashMap/LC1TwoSumHashMap.java) |  |
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | [LC1TwoSumThreeApproaches](src/Practice/HashMap/LC1TwoSumThreeApproaches.java) | :warning: twoSumBetter() (the method main() runs) returns the positions {l, r} in the sorted ... |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | [LC49GroupAnagramsPrimeProduct](src/Practice/HashMap/LC49GroupAnagramsPrimeProduct.java) | :warning: The long prime product overflows for longer words (LeetCode allows 100 letters), so ... |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | [LC49GroupAnagramsSortedKey](src/Practice/HashMap/LC49GroupAnagramsSortedKey.java) |  |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | [LC128LongestConsecutiveSequenceV1](src/Practice/HashMap/LC128LongestConsecutiveSequenceV1.java) | :warning: longestConsecutiveAmr (used by main) returns Integer.MIN_VALUE instead of 0 for an ... |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | [LC128LongestConsecutiveSequenceV2](src/Practice/HashMap/LC128LongestConsecutiveSequenceV2.java) | :warning: bruteSolution uses sentinel prevElement = -99, so a smallest value of -98 is counted ... |
| 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | [LC217ContainsDuplicate](src/Practice/HashMap/LC217ContainsDuplicate.java) |  |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | [LC242ValidAnagramV1](src/Practice/HashMap/LC242ValidAnagramV1.java) |  |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | [LC242ValidAnagramV2](src/Practice/HashMap/LC242ValidAnagramV2.java) |  |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | [LC242ValidAnagramV3](src/Practice/HashMap/LC242ValidAnagramV3.java) |  |
| 594 | [Longest Harmonious Subsequence](https://leetcode.com/problems/longest-harmonious-subsequence/) | Easy | [LC594LongestHarmoniousSubsequence](src/Practice/HashMap/LC594LongestHarmoniousSubsequence.java) |  |
| 1394 | [Find Lucky Integer in an Array](https://leetcode.com/problems/find-lucky-integer-in-an-array/) | Easy | [LC1394FindLuckyIntegerInAnArray](src/Practice/HashMap/LC1394FindLuckyIntegerInAnArray.java) |  |
| 1657 | [Determine if Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close/) | Medium | [LC1657DetermineIfTwoStringsAreClose](src/Practice/HashMap/LC1657DetermineIfTwoStringsAreClose.java) |  |
| 2615 | [Sum of Distances](https://leetcode.com/problems/sum-of-distances/) | Medium | [LC2615SumOfDistances](src/Practice/HashMap/LC2615SumOfDistances.java) | :warning: Wrong answers: takes Math.abs of a SIGNED sum instead of summing absolute distances ... |
| 3442 | [Maximum Difference Between Even and Odd Frequency I](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/) | Easy | [LC3442MaximumDifferenceBetweenEvenAndOddFrequencyI](src/Practice/HashMap/LC3442MaximumDifferenceBetweenEvenAndOddFrequencyI.java) |  |
| 3471 | [Find the Largest Almost Missing Integer](https://leetcode.com/problems/find-the-largest-almost-missing-integer/) | Easy | [LC3471FindTheLargestAlmostMissingInteger](src/Practice/HashMap/LC3471FindTheLargestAlmostMissingInteger.java) |  |
| 3583 | [Count Special Triplets](https://leetcode.com/problems/count-special-triplets/) | Medium | [LC3583CountSpecialTriplets](src/Practice/HashMap/LC3583CountSpecialTriplets.java) |  |
| Interview | Kth Most Frequent Number in an Array | Medium | [InterviewKthMostFrequentNumber](src/Practice/HashMap/InterviewKthMostFrequentNumber.java) |  |

### Heap

[`src/Practice/Heap`](src/Practice/Heap) - priority queues, top-k

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | [LC23MergeKSortedLists](src/Practice/Heap/LC23MergeKSortedLists.java) | :warning: mergeKListsRec (the D&C method main() runs) throws ArrayIndexOutOfBoundsException ... |
| 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | [LC215KthLargestElementInAnArrayManualHeap](src/Practice/Heap/LC215KthLargestElementInAnArrayManualHeap.java) |  |
| 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | [LC215KthLargestElementInAnArrayPriorityQueue](src/Practice/Heap/LC215KthLargestElementInAnArrayPriorityQueue.java) |  |
| 295 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Hard | [LC295FindMedianFromDataStream](src/Practice/Heap/LC295FindMedianFromDataStream.java) |  |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | [LC347TopKFrequentElements](src/Practice/Heap/LC347TopKFrequentElements.java) |  |
| 373 | [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) | Medium | [LC373FindKPairsWithSmallestSums](src/Practice/Heap/LC373FindKPairsWithSmallestSums.java) |  |
| 1046 | [Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) | Easy | [LC1046LastStoneWeight](src/Practice/Heap/LC1046LastStoneWeight.java) |  |
| 3318 | [Find X-Sum of All K-Long Subarrays I](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/) | Easy | [LC3318FindXSumOfAllKLongSubarraysI](src/Practice/Heap/LC3318FindXSumOfAllKLongSubarraysI.java) |  |
| 3321 | [Find X-Sum of All K-Long Subarrays II](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/) | Hard | [LC3321FindXSumOfAllKLongSubarraysII](src/Practice/Heap/LC3321FindXSumOfAllKLongSubarraysII.java) | :warning: Correct but brute force: recomputes every window from scratch, O((n-k+1)*(k + d log ... |

### Intervals

[`src/Practice/Intervals`](src/Practice/Intervals) - sorting and merging ranges

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | [LC56MergeIntervals](src/Practice/Intervals/LC56MergeIntervals.java) |  |

### LinkedList

[`src/Practice/LinkedList`](src/Practice/LinkedList) - pointer manipulation

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 2 | [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) | Medium | [LC2AddTwoNumbers](src/Practice/LinkedList/LC2AddTwoNumbers.java) |  |
| 19 | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | Medium | [LC19RemoveNthNodeFromEndOfListV1](src/Practice/LinkedList/LC19RemoveNthNodeFromEndOfListV1.java) |  |
| 19 | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | Medium | [LC19RemoveNthNodeFromEndOfListV2](src/Practice/LinkedList/LC19RemoveNthNodeFromEndOfListV2.java) |  |
| 21 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | [LC21MergeTwoSortedLists](src/Practice/LinkedList/LC21MergeTwoSortedLists.java) |  |
| 25 | [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) | Hard | [LC25ReverseNodesInKGroup](src/Practice/LinkedList/LC25ReverseNodesInKGroup.java) |  |
| 61 | [Rotate List](https://leetcode.com/problems/rotate-list/) | Medium | [LC61RotateList](src/Practice/LinkedList/LC61RotateList.java) | :warning: When k is a non-zero multiple of the list length (list of >= 2 nodes, e.g. [1,2,3] ... |
| 138 | [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) | Medium | [LC138CopyListWithRandomPointer](src/Practice/LinkedList/LC138CopyListWithRandomPointer.java) | :construction: Stub: copyRandomList returns the original head instead of a deep copy; main() builds ... |
| 141 | [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | Easy | [LC141LinkedListCycle](src/Practice/LinkedList/LC141LinkedListCycle.java) |  |
| 142 | [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) | Medium | [LC142LinkedListCycleII](src/Practice/LinkedList/LC142LinkedListCycleII.java) |  |
| 160 | [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) | Easy | [LC160IntersectionOfTwoLinkedLists](src/Practice/LinkedList/LC160IntersectionOfTwoLinkedLists.java) |  |
| 206 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | [LC206ReverseLinkedListIterative](src/Practice/LinkedList/LC206ReverseLinkedListIterative.java) |  |
| 206 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | [LC206ReverseLinkedListRecursive](src/Practice/LinkedList/LC206ReverseLinkedListRecursive.java) |  |
| 234 | [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) | Easy | [LC234PalindromeLinkedList](src/Practice/LinkedList/LC234PalindromeLinkedList.java) |  |
| 237 | [Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/) | Medium | [LC237DeleteNodeInALinkedList](src/Practice/LinkedList/LC237DeleteNodeInALinkedList.java) |  |
| 876 | [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) | Easy | [LC876MiddleOfTheLinkedList](src/Practice/LinkedList/LC876MiddleOfTheLinkedList.java) |  |
| 2058 | [Find the Minimum and Maximum Number of Nodes Between Critical Points](https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/) | Medium | [LC2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints](src/Practice/LinkedList/LC2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints.java) |  |
| 3217 | [Delete Nodes From Linked List Present in Array](https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/) | Medium | [LC3217DeleteNodesFromLinkedListPresentInArray](src/Practice/LinkedList/LC3217DeleteNodesFromLinkedListPresentInArray.java) |  |

### Math

[`src/Practice/Math`](src/Practice/Math) - number theory, digits, formulas

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 7 | [Reverse Integer](https://leetcode.com/problems/reverse-integer/) | Medium | [LC7ReverseInteger](src/Practice/Math/LC7ReverseInteger.java) |  |
| 9 | [Palindrome Number](https://leetcode.com/problems/palindrome-number/) | Easy | [LC9PalindromeNumber](src/Practice/Math/LC9PalindromeNumber.java) |  |
| 50 | [Pow(x, n)](https://leetcode.com/problems/powx-n/) | Medium | [LC50PowXN](src/Practice/Math/LC50PowXN.java) |  |
| 60 | [Permutation Sequence](https://leetcode.com/problems/permutation-sequence/) | Hard | [LC60PermutationSequence](src/Practice/Math/LC60PermutationSequence.java) |  |
| 231 | [Power of Two](https://leetcode.com/problems/power-of-two/) | Easy | [LC231PowerOfTwo](src/Practice/Math/LC231PowerOfTwo.java) |  |
| 440 | [K-th Smallest in Lexicographical Order](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/) | Hard | [LC440KThSmallestInLexicographicalOrder](src/Practice/Math/LC440KThSmallestInLexicographicalOrder.java) | :warning: count() uses int curr, so curr *= 10 overflows for n >= 214748365; main()'s input ... |
| 1295 | [Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/) | Easy | [LC1295FindNumbersWithEvenNumberOfDigits](src/Practice/Math/LC1295FindNumbersWithEvenNumberOfDigits.java) |  |
| 1344 | [Angle Between Hands of a Clock](https://leetcode.com/problems/angle-between-hands-of-a-clock/) | Medium | [LC1344AngleBetweenHandsOfAClock](src/Practice/Math/LC1344AngleBetweenHandsOfAClock.java) |  |
| 1513 | [Number of Substrings With Only 1s](https://leetcode.com/problems/number-of-substrings-with-only-1s/) | Medium | [LC1513NumberOfSubstringsWithOnly1s](src/Practice/Math/LC1513NumberOfSubstringsWithOnly1s.java) | :warning: int overflow in (end-start)*(end-start+1) for runs of >= 46341 ones: 100000 ones -> ... |
| 1979 | [Find Greatest Common Divisor of Array](https://leetcode.com/problems/find-greatest-common-divisor-of-array/) | Easy | [LC1979FindGreatestCommonDivisorOfArray](src/Practice/Math/LC1979FindGreatestCommonDivisorOfArray.java) |  |
| 2169 | [Count Operations to Obtain Zero](https://leetcode.com/problems/count-operations-to-obtain-zero/) | Easy | [LC2169CountOperationsToObtainZero](src/Practice/Math/LC2169CountOperationsToObtainZero.java) |  |
| 2929 | [Distribute Candies Among Children II](https://leetcode.com/problems/distribute-candies-among-children-ii/) | Medium | [LC2929DistributeCandiesAmongChildrenII](src/Practice/Math/LC2929DistributeCandiesAmongChildrenII.java) |  |
| 2965 | [Find Missing and Repeated Values](https://leetcode.com/problems/find-missing-and-repeated-values/) | Easy | [LC2965FindMissingAndRepeatedValues](src/Practice/Math/LC2965FindMissingAndRepeatedValues.java) |  |
| 3289 | [The Two Sneaky Numbers of Digitville](https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/) | Easy | [LC3289TheTwoSneakyNumbersOfDigitville](src/Practice/Math/LC3289TheTwoSneakyNumbersOfDigitville.java) |  |
| 3536 | [Maximum Product of Two Digits](https://leetcode.com/problems/maximum-product-of-two-digits/) | Easy | [LC3536MaximumProductOfTwoDigits](src/Practice/Math/LC3536MaximumProductOfTwoDigits.java) |  |
| 3577 | [Count the Number of Computer Unlocking Permutations](https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations/) | Medium | [LC3577CountTheNumberOfComputerUnlockingPermutations](src/Practice/Math/LC3577CountTheNumberOfComputerUnlockingPermutations.java) | :warning: factorial() overflows int from 13! on (14+ computers give a wrong answer) and ... |

### Matrix

[`src/Practice/Matrix`](src/Practice/Matrix) - 2-D grids

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 36 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | [LC36ValidSudoku](src/Practice/Matrix/LC36ValidSudoku.java) |  |
| 48 | [Rotate Image](https://leetcode.com/problems/rotate-image/) | Medium | [LC48RotateImage](src/Practice/Matrix/LC48RotateImage.java) |  |
| 73 | [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) | Medium | [LC73SetMatrixZeroes](src/Practice/Matrix/LC73SetMatrixZeroes.java) | :warning: main() crashes: approach2 sizes rowArr with m instead of n -> ... |
| 2125 | [Number of Laser Beams in a Bank](https://leetcode.com/problems/number-of-laser-beams-in-a-bank/) | Medium | [LC2125NumberOfLaserBeamsInABank](src/Practice/Matrix/LC2125NumberOfLaserBeamsInABank.java) |  |
| 2257 | [Count Unguarded Cells in the Grid](https://leetcode.com/problems/count-unguarded-cells-in-the-grid/) | Medium | [LC2257CountUnguardedCellsInTheGrid](src/Practice/Matrix/LC2257CountUnguardedCellsInTheGrid.java) |  |

### PrefixSum

[`src/Practice/PrefixSum`](src/Practice/PrefixSum) - range sums in O(1)

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | [LC560SubarraySumEqualsKV1](src/Practice/PrefixSum/LC560SubarraySumEqualsKV1.java) |  |
| 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | [LC560SubarraySumEqualsKV2](src/Practice/PrefixSum/LC560SubarraySumEqualsKV2.java) | :warning: Running sum does 'sum = sum + nums[0]' instead of nums[i], so it is wrong unless all ... |
| 2588 | [Count the Number of Beautiful Subarrays](https://leetcode.com/problems/count-the-number-of-beautiful-subarrays/) | Medium | [LC2588CountTheNumberOfBeautifulSubarrays](src/Practice/PrefixSum/LC2588CountTheNumberOfBeautifulSubarrays.java) |  |
| GFG | [Largest Subarray with 0 Sum](https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1) | Medium | [GFGLargestSubarrayWithZeroSum](src/Practice/PrefixSum/GFGLargestSubarrayWithZeroSum.java) |  |

### Queue

[`src/Practice/Queue`](src/Practice/Queue) - FIFO simulation

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 1700 | [Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/) | Easy | [LC1700NumberOfStudentsUnableToEatLunch](src/Practice/Queue/LC1700NumberOfStudentsUnableToEatLunch.java) | :warning: the ~4*n step cut-off is a heuristic: valid inputs whose full simulation needs more ... |
| 2073 | [Time Needed to Buy Tickets](https://leetcode.com/problems/time-needed-to-buy-tickets/) | Easy | [LC2073TimeNeededToBuyTickets](src/Practice/Queue/LC2073TimeNeededToBuyTickets.java) |  |

### Recursion

[`src/Practice/Recursion`](src/Practice/Recursion) - divide into smaller calls

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 386 | [Lexicographical Numbers](https://leetcode.com/problems/lexicographical-numbers/) | Medium | [LC386LexicographicalNumbers](src/Practice/Recursion/LC386LexicographicalNumbers.java) |  |
| GFG | [Subset Sums](https://www.geeksforgeeks.org/problems/subset-sums2234/1) | Medium | [GFGSubsetSums](src/Practice/Recursion/GFGSubsetSums.java) |  |

### SlidingWindow

[`src/Practice/SlidingWindow`](src/Practice/SlidingWindow) - grow / shrink a window

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | [LC3LongestSubstringWithoutRepeatingCharactersQueueWindow](src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersQueueWindow.java) | :warning: Unused private longestSubstring attempt is wrong ("abc" -> Integer.MIN_VALUE, "dvdf" ... |
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | [LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches](src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches.java) |  |
| 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | [LC239SlidingWindowMaximum](src/Practice/SlidingWindow/LC239SlidingWindowMaximum.java) | :warning: maxSlidingWindow (not run by main) starts max at 0, so an all-negative first window ... |
| 567 | [Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Medium | [LC567PermutationInString](src/Practice/SlidingWindow/LC567PermutationInString.java) |  |
| 904 | [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | Medium | [LC904FruitIntoBaskets](src/Practice/SlidingWindow/LC904FruitIntoBaskets.java) |  |
| 1984 | [Minimum Difference Between Highest and Lowest of K Scores](https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/) | Easy | [LC1984MinimumDifferenceBetweenHighestAndLowestOfKScores](src/Practice/SlidingWindow/LC1984MinimumDifferenceBetweenHighestAndLowestOfKScores.java) |  |
| 3795 | [Minimum Subarray Length With Distinct Sum At Least K](https://leetcode.com/problems/minimum-subarray-length-with-distinct-sum-at-least-k/) | Medium | [LC3795MinimumSubarrayLengthWithDistinctSumAtLeastK](src/Practice/SlidingWindow/LC3795MinimumSubarrayLengthWithDistinctSumAtLeastK.java) | :warning: main() calls minLength (first attempt) which never shrinks the window: [3,1,7], k=8 ... |

### Sorting

[`src/Practice/Sorting`](src/Practice/Sorting) - sort first, or implement a sort

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | [LC88MergeSortedArray](src/Practice/Sorting/LC88MergeSortedArray.java) | :construction: mergeTwoSortedArrays() is an incomplete swap-based attempt: it never sorts the two ... |
| 493 | [Reverse Pairs](https://leetcode.com/problems/reverse-pairs/) | Hard | [LC493ReversePairs](src/Practice/Sorting/LC493ReversePairs.java) |  |
| 912 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | Medium | [LC912SortAnArrayV1](src/Practice/Sorting/LC912SortAnArrayV1.java) |  |
| 912 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | Medium | [LC912SortAnArrayV2](src/Practice/Sorting/LC912SortAnArrayV2.java) | :warning: merge() allocates temp as new int[high + 1] instead of high - low + 1, so the ... |
| 1365 | [How Many Numbers Are Smaller Than the Current Number](https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/) | Easy | [LC1365HowManyNumbersAreSmallerThanTheCurrentNumber](src/Practice/Sorting/LC1365HowManyNumbersAreSmallerThanTheCurrentNumber.java) |  |
| 2099 | [Find Subsequence of Length K With the Largest Sum](https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/) | Easy | [LC2099FindSubsequenceOfLengthKWithTheLargestSum](src/Practice/Sorting/LC2099FindSubsequenceOfLengthKWithTheLargestSum.java) |  |
| 2966 | [Divide Array Into Arrays With Max Difference](https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/) | Medium | [LC2966DivideArrayIntoArraysWithMaxDifference](src/Practice/Sorting/LC2966DivideArrayIntoArraysWithMaxDifference.java) |  |
| GFG | [Count Inversions](https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1) | Medium | [GFGCountInversions](src/Practice/Sorting/GFGCountInversions.java) |  |

### Stack

[`src/Practice/Stack`](src/Practice/Stack) - monotonic stacks, parsing

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 20 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | [LC20ValidParentheses](src/Practice/Stack/LC20ValidParentheses.java) |  |
| 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | [LC84LargestRectangleInHistogramIndexStack](src/Practice/Stack/LC84LargestRectangleInHistogramIndexStack.java) |  |
| 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | [LC84LargestRectangleInHistogramPairStack](src/Practice/Stack/LC84LargestRectangleInHistogramPairStack.java) |  |
| 150 | [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | [LC150EvaluateReversePolishNotation](src/Practice/Stack/LC150EvaluateReversePolishNotation.java) |  |
| 496 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | Easy | [LC496NextGreaterElementI](src/Practice/Stack/LC496NextGreaterElementI.java) |  |
| 636 | [Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) | Medium | [LC636ExclusiveTimeOfFunctionsV1](src/Practice/Stack/LC636ExclusiveTimeOfFunctionsV1.java) | :construction: the 'end' branch is empty and every log is pushed twice; main() throws ... |
| 636 | [Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) | Medium | [LC636ExclusiveTimeOfFunctionsV2](src/Practice/Stack/LC636ExclusiveTimeOfFunctionsV2.java) |  |
| 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | [LC739DailyTemperaturesLeftToRight](src/Practice/Stack/LC739DailyTemperaturesLeftToRight.java) |  |
| 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | [LC739DailyTemperaturesRightToLeft](src/Practice/Stack/LC739DailyTemperaturesRightToLeft.java) |  |
| 901 | [Online Stock Span](https://leetcode.com/problems/online-stock-span/) | Medium | [LC901OnlineStockSpan](src/Practice/Stack/LC901OnlineStockSpan.java) |  |
| 1081 | [Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) | Medium | [LC1081SmallestSubsequenceOfDistinctCharacters](src/Practice/Stack/LC1081SmallestSubsequenceOfDistinctCharacters.java) | :warning: returns stk.toString() = "[a, b, c]" instead of "abc"; the algorithm itself is ... |
| 1441 | [Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations/) | Medium | [LC1441BuildAnArrayWithStackOperationsV1](src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV1.java) |  |
| 1441 | [Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations/) | Medium | [LC1441BuildAnArrayWithStackOperationsV2](src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV2.java) |  |
| 2434 | [Using a Robot to Print the Lexicographically Smallest String](https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/) | Medium | [LC2434UsingARobotToPrintTheLexicographicallySmallestString](src/Practice/Stack/LC2434UsingARobotToPrintTheLexicographicallySmallestString.java) |  |
| GFG | [Sort a stack](https://www.geeksforgeeks.org/problems/sort-a-stack/1) | Medium | [GFGSortAStack](src/Practice/Stack/GFGSortAStack.java) |  |

### Strings

[`src/Practice/Strings`](src/Practice/Strings) - character processing

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 8 | [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/) | Medium | [LC8StringToIntegerAtoi](src/Practice/Strings/LC8StringToIntegerAtoi.java) | :warning: Empty string or all-space string throws StringIndexOutOfBoundsException (s.charAt(i) ... |
| 13 | [Roman to Integer](https://leetcode.com/problems/roman-to-integer/) | Easy | [LC13RomanToInteger](src/Practice/Strings/LC13RomanToInteger.java) |  |
| 14 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | Easy | [LC14LongestCommonPrefixThreeApproaches](src/Practice/Strings/LC14LongestCommonPrefixThreeApproaches.java) |  |
| 14 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | Easy | [LC14LongestCommonPrefixVerticalScan](src/Practice/Strings/LC14LongestCommonPrefixVerticalScan.java) |  |
| 38 | [Count and Say](https://leetcode.com/problems/count-and-say/) | Medium | [LC38CountAndSay](src/Practice/Strings/LC38CountAndSay.java) | :warning: main() calls countAndSayItr(321), far outside n <= 30: the run never finishes and ... |
| 151 | [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) | Medium | [LC151ReverseWordsInAString](src/Practice/Strings/LC151ReverseWordsInAString.java) |  |
| 165 | [Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/) | Medium | [LC165CompareVersionNumbers](src/Practice/Strings/LC165CompareVersionNumbers.java) |  |
| 686 | [Repeated String Match](https://leetcode.com/problems/repeated-string-match/) | Medium | [LC686RepeatedStringMatch](src/Practice/Strings/LC686RepeatedStringMatch.java) | :warning: Rabin-Karp hash has no modulus and uses Math.pow in double cast to long: for /b/ = 8 ... |
| 2138 | [Divide a String Into Groups of Size k](https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/) | Easy | [LC2138DivideAStringIntoGroupsOfSizeK](src/Practice/Strings/LC2138DivideAStringIntoGroupsOfSizeK.java) |  |
| 2264 | [Largest 3-Same-Digit Number in String](https://leetcode.com/problems/largest-3-same-digit-number-in-string/) | Easy | [LC2264Largest3SameDigitNumberInString](src/Practice/Strings/LC2264Largest3SameDigitNumberInString.java) |  |
| 3330 | [Find the Original Typed String I](https://leetcode.com/problems/find-the-original-typed-string-i/) | Easy | [LC3330FindTheOriginalTypedStringI](src/Practice/Strings/LC3330FindTheOriginalTypedStringI.java) |  |
| 3403 | [Find the Lexicographically Largest String From the Box I](https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/) | Medium | [LC3403FindTheLexicographicallyLargestStringFromTheBoxI](src/Practice/Strings/LC3403FindTheLexicographicallyLargestStringFromTheBoxI.java) | :warning: numFriends == 1 returns the largest suffix instead of the whole word (word=gh ... |

### TwoPointers

[`src/Practice/TwoPointers`](src/Practice/TwoPointers) - pointers moving toward / with each other

| # | Problem | Difficulty | Solution | Status |
|---|---|---|---|---|
| 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | [LC5LongestPalindromicSubstringV1](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV1.java) | :warning: main() does not run the LC 5 method (its call is commented out); it runs the LC 1411 ... |
| 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | [LC5LongestPalindromicSubstringV2](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV2.java) |  |
| 15 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | [LC15ThreeSum](src/Practice/TwoPointers/LC15ThreeSum.java) | :warning: Both methods are correct, but betterSolution dedupes with ansFinal.contains() ... |
| 18 | [4Sum](https://leetcode.com/problems/4sum/) | Medium | [LC18FourSum](src/Practice/TwoPointers/LC18FourSum.java) |  |
| 26 | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Easy | [LC26RemoveDuplicatesFromSortedArray](src/Practice/TwoPointers/LC26RemoveDuplicatesFromSortedArray.java) |  |
| 27 | [Remove Element](https://leetcode.com/problems/remove-element/) | Easy | [LC27RemoveElementThreeApproaches](src/Practice/TwoPointers/LC27RemoveElementThreeApproaches.java) |  |
| 27 | [Remove Element](https://leetcode.com/problems/remove-element/) | Easy | [LC27RemoveElementTwoEndsSwap](src/Practice/TwoPointers/LC27RemoveElementTwoEndsSwap.java) |  |
| 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | [LC42TrappingRainWaterV1](src/Practice/TwoPointers/LC42TrappingRainWaterV1.java) |  |
| 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | [LC42TrappingRainWaterV2](src/Practice/TwoPointers/LC42TrappingRainWaterV2.java) |  |
| 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | [LC75SortColors](src/Practice/TwoPointers/LC75SortColors.java) |  |
| 283 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | Easy | [LC283MoveZeroes](src/Practice/TwoPointers/LC283MoveZeroes.java) |  |
| 287 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | Medium | [LC287FindTheDuplicateNumber](src/Practice/TwoPointers/LC287FindTheDuplicateNumber.java) |  |
| 1498 | [Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/) | Medium | [LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition](src/Practice/TwoPointers/LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition.java) |  |
| 3794 | [Reverse String Prefix](https://leetcode.com/problems/reverse-string-prefix/) | Easy | [LC3794ReverseStringPrefix](src/Practice/TwoPointers/LC3794ReverseStringPrefix.java) |  |
| GFG | [Merge Without Extra Space](https://www.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1) | Medium | [GFGMergeTwoSortedArraysWithoutExtraSpace](src/Practice/TwoPointers/GFGMergeTwoSortedArraysWithoutExtraSpace.java) | :warning: Array method: tail branches never advance pointA/pointB, so the remaining slots ... |

## LeetCode sets by book chapter (`src/Leetcode`)

Grouped by the chapters of *Algorithmic Thinking*; **Practice #** is the problem's place in that chapter's practice list. `Daily`, `Contest191`, `Random` and `ArithMetic` hold problems that are not in the book.

### Chapter 1 - Complexity, amortisation, and counting

[`src/Leetcode/Ch01ComplexityAmortisationAndCounting`](src/Leetcode/Ch01ComplexityAmortisationAndCounting)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 1441 | [Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations/) | Medium | 1 | [LC1441BuildAnArrayWithStackOperations](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC1441BuildAnArrayWithStackOperations.java) | in main() 7/7 |  |
| 1047 | [Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) | Easy | 2 | [LC1047RemoveAllAdjacentDuplicatesInString](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC1047RemoveAllAdjacentDuplicatesInString.java) | in main() 12/12 |  |
| 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | 3 | [LC739DailyTemperatures](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC739DailyTemperatures.java) | in main() 0/18 | :white_large_square: not solved yet |
| 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | 4 | [LC84LargestRectangleInHistogram](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC84LargestRectangleInHistogram.java) | in main() 0/15 | :white_large_square: not solved yet |
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | 5 | [LC3LongestSubstringWithoutRepeatingCharacters](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC3LongestSubstringWithoutRepeatingCharacters.java) | in main() 21/21 |  |
| 209 | [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) | Medium | 6 | [LC209MinimumSizeSubarraySum](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC209MinimumSizeSubarraySum.java) | in main() 0/18 | :white_large_square: not solved yet |
| 992 | [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) | Hard | 7 | [LC992SubarraysWithKDifferentIntegers](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC992SubarraysWithKDifferentIntegers.java) | in main() 12/12 |  |
| 1717 | [Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings/) | Medium | 8 | [LC1717MaximumScoreFromRemovingSubstrings](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC1717MaximumScoreFromRemovingSubstrings.java) | in main() 6/7 | :warning: static int ans is never reset, so only the first maximumGain() call per JVM run is ... |
| 380 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | Medium | 9 | [LC380InsertDeleteGetRandomO1](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC380InsertDeleteGetRandomO1.java) | in main() 14/14 |  |
| 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | 10 | [LC146LRUCache](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC146LRUCache.java) | in main() 0/12 | :white_large_square: not solved yet |
| 547 | [Number of Provinces](https://leetcode.com/problems/number-of-provinces/) | Medium | 11 | [LC547NumberOfProvinces](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC547NumberOfProvinces.java) | in main() 0/15 | :white_large_square: not solved yet |
| 204 | [Count Primes](https://leetcode.com/problems/count-primes/) | Medium | 12 | [LC204CountPrimes](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC204CountPrimes.java) | in main() 7/7 |  |

### Chapter 2 - Prefix sums and difference arrays

[`src/Leetcode/Ch02PrefixSumsAndDifferenceArrays`](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 1732 | [Find the Highest Altitude](https://leetcode.com/problems/find-the-highest-altitude/) | Easy | 1 | [LC1732FindTheHighestAltitude](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1732FindTheHighestAltitude.java) | in main() 6/6 |  |
| 303 | [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) | Easy | 2 | [LC303RangeSumQueryImmutable](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC303RangeSumQueryImmutable.java) | in main() 7/7 |  |
| 304 | [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) | Medium | 3 | [LC304RangeSumQuery2DImmutable](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC304RangeSumQuery2DImmutable.java) | in main() 0/12 | :white_large_square: not solved yet |
| 724 | [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) | Easy | 4 | [LC724FindPivotIndex](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC724FindPivotIndex.java) | in main() 14/14 |  |
| 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | 5 | [LC560SubarraySumEqualsK](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC560SubarraySumEqualsK.java) | in main() 0/15 | :white_large_square: not solved yet |
| 974 | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | Medium | 6 | [LC974SubarraySumsDivisibleByK](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC974SubarraySumsDivisibleByK.java) | in main() 4/6 | :warning: int[] map = new int[nums.length] must have k slots: remainders go up to k-1, so ... |
| 523 | [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) | Medium | 7 | [LC523ContinuousSubarraySum](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC523ContinuousSubarraySum.java) | in main() 7/7 |  |
| 525 | [Contiguous Array](https://leetcode.com/problems/contiguous-array/) | Medium | 8 | [LC525ContiguousArray](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC525ContiguousArray.java) | in main() 7/7 |  |
| 1442 | [Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/) | Medium | 9 | [LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR.java) | in main() 8/12 | :warning: countTripletsOptimal (not run by main) is wrong: int countTerm = ... |
| 1109 | [Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/) | Medium | 10 | [LC1109CorporateFlightBookings](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1109CorporateFlightBookings.java) | in main() 6/6 |  |
| 1094 | [Car Pooling](https://leetcode.com/problems/car-pooling/) | Medium | 11 | [LC1094CarPooling](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1094CarPooling.java) | in main() 12/12 |  |
| 1074 | [Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/) | Hard | 12 | [LC1074NumberOfSubmatricesThatSumToTarget](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1074NumberOfSubmatricesThatSumToTarget.java) | in main() 7/7 |  |
| 1991 | [Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) | Easy | - | [LC1991FindTheMiddleIndexInArray](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1991FindTheMiddleIndexInArray.java) | in main() 6/7 | :warning: When no middle index exists the method returns 0 instead of -1 (final `return 0;`). ... |

### Chapter 3 - Hashing as a search-to-lookup transform

[`src/Leetcode/Ch03HashingAsASearchToLookupTransform`](src/Leetcode/Ch03HashingAsASearchToLookupTransform)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | 1 | [LC217ContainsDuplicate](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC217ContainsDuplicate.java) | in main() 7/7 |  |
| 219 | [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) | Easy | 2 | [LC219ContainsDuplicateII](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC219ContainsDuplicateII.java) | in main() 8/8 |  |
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | 3 | [LC1TwoSum](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC1TwoSum.java) | in main() 7/7 |  |
| 1346 | [Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/) | Easy | 4 | [LC1346CheckIfNAndItsDoubleExist](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC1346CheckIfNAndItsDoubleExist.java) | in main() 8/8 |  |
| 2006 | [Count Number of Pairs With Absolute Difference K](https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/) | Easy | 5 | [LC2006CountNumberOfPairsWithAbsoluteDifferenceK](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC2006CountNumberOfPairsWithAbsoluteDifferenceK.java) | in main() 4/7 | :warning: countKDifference throws ArrayIndexOutOfBoundsException on valid input ... |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | 6 | [LC49GroupAnagrams](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC49GroupAnagrams.java) | in main() 7/7 |  |
| 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | 7 | [LC560SubarraySumEqualsK](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC560SubarraySumEqualsK.java) | in main() 0/15 | :white_large_square: not solved yet |
| 974 | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | Medium | 8 | [LC974SubarraySumsDivisibleByK](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC974SubarraySumsDivisibleByK.java) | in main() 6/6 |  |
| 523 | [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) | Medium | 9 | [LC523ContinuousSubarraySum](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC523ContinuousSubarraySum.java) | in main() 8/8 |  |
| 454 | [4Sum II](https://leetcode.com/problems/4sum-ii/) | Medium | 10 | [LC454FourSumII](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC454FourSumII.java) | in main() 6/6 |  |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | 11 | [LC128LongestConsecutiveSequence](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC128LongestConsecutiveSequence.java) | in main() 7/7 |  |
| 220 | [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) | Hard | 12 | [LC220ContainsDuplicateIII](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC220ContainsDuplicateIII.java) | in main() 8/8 |  |

### Chapter 4 - Frequency and multiset reasoning

[`src/Leetcode/Ch04FrequencyAndMultisetReasoning`](src/Leetcode/Ch04FrequencyAndMultisetReasoning)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | 1 | [LC242ValidAnagram](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC242ValidAnagram.java) | in main() 8/8 |  |
| 383 | [Ransom Note](https://leetcode.com/problems/ransom-note/) | Easy | 2 | [LC383RansomNote](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC383RansomNote.java) | in main() 3/7 | :warning: canConstruct tallies ransomNote instead of magazine (magazine is never read), so it ... |
| 409 | [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/) | Easy | 3 | [LC409LongestPalindrome](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC409LongestPalindrome.java) | in main() 9/9 |  |
| 387 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | 4 | [LC387FirstUniqueCharacterInAString](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC387FirstUniqueCharacterInAString.java) | in main() 18/18 |  |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | 5 | [LC49GroupAnagrams](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC49GroupAnagrams.java) | in main() 9/9 |  |
| 451 | [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/) | Medium | 6 | [LC451SortCharactersByFrequency](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC451SortCharactersByFrequency.java) | in main() 9/18 | :warning: frequencySortOpt (bucket sort, not called by main) starts its loop at maxFreq - 1 ... |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | 7 | [LC347TopKFrequentElements](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC347TopKFrequentElements.java) | in main() 9/9 |  |
| 438 | [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | Medium | 8 | [LC438FindAllAnagramsInAString](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC438FindAllAnagramsInAString.java) | in main() 21/22 | :warning: findAnagramsOpt (the method main() runs) returns null instead of an empty list when ... |
| 567 | [Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Medium | 9 | [LC567PermutationInString](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC567PermutationInString.java) | in main() 12/12 |  |
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | 10 | [LC3LongestSubstringWithoutRepeatingCharacters](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC3LongestSubstringWithoutRepeatingCharacters.java) | in main() 13/20 | :warning: lengthOfLongestSubstring (the method main() runs) adds s.charAt(i) instead of ... |
| 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | 11 | [LC76MinimumWindowSubstring](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC76MinimumWindowSubstring.java) | in main() 11/11 | :warning: performance only: results are correct, but minWindow re-counts and copies the window ... |
| 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | 12 | [LC169MajorityElement](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC169MajorityElement.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 5 - Bit manipulation as a set encoding

[`src/Leetcode/Ch05BitManipulationAsASetEncoding`](src/Leetcode/Ch05BitManipulationAsASetEncoding)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 191 | [Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/) | Easy | 1 | [LC191NumberOf1Bits](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC191NumberOf1Bits.java) | in main() 8/8 |  |
| 231 | [Power of Two](https://leetcode.com/problems/power-of-two/) | Easy | 2 | [LC231PowerOfTwo](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC231PowerOfTwo.java) | in main() 17/20 | :warning: isPowerOfTwo() (the one main() runs) returns true for EVERY negative n: n - 1 stays ... |
| 338 | [Counting Bits](https://leetcode.com/problems/counting-bits/) | Easy | 3 | [LC338CountingBits](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC338CountingBits.java) | in main() 7/12 | :warning: countBitsOpt() (not run by main) is wrong for every n > 0: loop starts at i = 0 ... |
| 136 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | 4 | [LC136SingleNumber](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC136SingleNumber.java) | in main() 6/6 |  |
| 268 | [Missing Number](https://leetcode.com/problems/missing-number/) | Easy | 5 | [LC268MissingNumber](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC268MissingNumber.java) | in main() 0/18 | :white_large_square: not solved yet |
| 260 | [Single Number III](https://leetcode.com/problems/single-number-iii/) | Medium | 6 | [LC260SingleNumberIII](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC260SingleNumberIII.java) | in main() 8/8 |  |
| 137 | [Single Number II](https://leetcode.com/problems/single-number-ii/) | Medium | 7 | [LC137SingleNumberII](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC137SingleNumberII.java) | in main() 9/9 |  |
| 78 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | 8 | [LC78Subsets](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC78Subsets.java) | in main() 6/6 |  |
| 1863 | [Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/) | Easy | 9 | [LC1863SumOfAllSubsetXORTotals](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC1863SumOfAllSubsetXORTotals.java) | in main() 7/8 | :warning: subsetXORSumOptimal() does not compute the XOR sum: despite its name it is the LC 78 ... |
| 318 | [Maximum Product of Word Lengths](https://leetcode.com/problems/maximum-product-of-word-lengths/) | Medium | 10 | [LC318MaximumProductOfWordLengths](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC318MaximumProductOfWordLengths.java) | in main() 8/8 |  |
| 1371 | [Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/) | Medium | 11 | [LC1371FindTheLongestSubstringContainingVowelsInEvenCounts](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC1371FindTheLongestSubstringContainingVowelsInEvenCounts.java) | in main() 12/18 | :warning: findTheLongestSubstring() (brute force, not run by main) is off by one: records j - ... |
| 1178 | [Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/) | Hard | 12 | [LC1178NumberOfValidWordsForEachPuzzle](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC1178NumberOfValidWordsForEachPuzzle.java) | in main() 7/7 | :warning: Correct but brute force O(P*W*L): ~1 s for 1000 puzzles x 10^5 random words, ~10.6 s ... |

### Chapter 6 - Sorting as a preprocessing step

[`src/Leetcode/Ch06SortingAsAPreprocessingStep`](src/Leetcode/Ch06SortingAsAPreprocessingStep)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | 1 | [LC88MergeSortedArray](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC88MergeSortedArray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 912 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | Medium | 2 | [LC912SortAnArray](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC912SortAnArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | 3 | [LC217ContainsDuplicate](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC217ContainsDuplicate.java) | in main() 0/18 | :white_large_square: not solved yet |
| 2418 | [Sort the People](https://leetcode.com/problems/sort-the-people/) | Easy | 4 | [LC2418SortThePeople](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC2418SortThePeople.java) | in main() 0/15 | :white_large_square: not solved yet |
| 973 | [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) | Medium | 5 | [LC973KClosestPointsToOrigin](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC973KClosestPointsToOrigin.java) | in main() 0/15 | :white_large_square: not solved yet |
| 15 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | 6 | [LC15ThreeSum](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC15ThreeSum.java) | in main() 0/18 | :white_large_square: not solved yet |
| 16 | [3Sum Closest](https://leetcode.com/problems/3sum-closest/) | Medium | 7 | [LC16ThreeSumClosest](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC16ThreeSumClosest.java) | in main() 0/15 | :white_large_square: not solved yet |
| 881 | [Boats to Save People](https://leetcode.com/problems/boats-to-save-people/) | Medium | 8 | [LC881BoatsToSavePeople](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC881BoatsToSavePeople.java) | in main() 0/18 | :white_large_square: not solved yet |
| 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | 9 | [LC56MergeIntervals](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC56MergeIntervals.java) | in main() 0/18 | :white_large_square: not solved yet |
| 452 | [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) | Medium | 10 | [LC452MinimumNumberOfArrowsToBurstBalloons](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC452MinimumNumberOfArrowsToBurstBalloons.java) | in main() 0/18 | :white_large_square: not solved yet |
| 179 | [Largest Number](https://leetcode.com/problems/largest-number/) | Medium | 11 | [LC179LargestNumber](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC179LargestNumber.java) | in main() 0/10 | :white_large_square: not solved yet |
| 406 | [Queue Reconstruction by Height](https://leetcode.com/problems/queue-reconstruction-by-height/) | Medium | 12 | [LC406QueueReconstructionByHeight](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC406QueueReconstructionByHeight.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 7 - Non-comparison sorts: counting, bucket, radix

[`src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix`](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 1051 | [Height Checker](https://leetcode.com/problems/height-checker/) | Easy | 1 | [LC1051HeightChecker](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1051HeightChecker.java) | in main() 0/12 | :white_large_square: not solved yet |
| 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | 2 | [LC75SortColors](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC75SortColors.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1122 | [Relative Sort Array](https://leetcode.com/problems/relative-sort-array/) | Easy | 3 | [LC1122RelativeSortArray](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1122RelativeSortArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1370 | [Increasing Decreasing String](https://leetcode.com/problems/increasing-decreasing-string/) | Easy | 4 | [LC1370IncreasingDecreasingString](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1370IncreasingDecreasingString.java) | in main() 0/10 | :white_large_square: not solved yet |
| 451 | [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/) | Medium | 5 | [LC451SortCharactersByFrequency](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC451SortCharactersByFrequency.java) | in main() 0/18 | :white_large_square: not solved yet |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | 6 | [LC347TopKFrequentElements](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC347TopKFrequentElements.java) | in main() 0/18 | :white_large_square: not solved yet |
| 692 | [Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/) | Medium | 7 | [LC692TopKFrequentWords](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC692TopKFrequentWords.java) | in main() 0/15 | :white_large_square: not solved yet |
| 274 | [H-Index](https://leetcode.com/problems/h-index/) | Medium | 8 | [LC274HIndex](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC274HIndex.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1030 | [Matrix Cells in Distance Order](https://leetcode.com/problems/matrix-cells-in-distance-order/) | Easy | 9 | [LC1030MatrixCellsInDistanceOrder](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1030MatrixCellsInDistanceOrder.java) | in main() 0/18 | :white_large_square: not solved yet |
| 220 | [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) | Hard | 10 | [LC220ContainsDuplicateIII](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC220ContainsDuplicateIII.java) | in main() 0/15 | :white_large_square: not solved yet |
| 164 | [Maximum Gap](https://leetcode.com/problems/maximum-gap/) | Medium | 11 | [LC164MaximumGap](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC164MaximumGap.java) | in main() 0/15 | :white_large_square: not solved yet |
| 912 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | Medium | 12 | [LC912SortAnArray](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC912SortAnArray.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 8 - Two pointers, converging

[`src/Leetcode/Ch08TwoPointersConverging`](src/Leetcode/Ch08TwoPointersConverging)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 344 | [Reverse String](https://leetcode.com/problems/reverse-string/) | Easy | 1 | [LC344ReverseString](src/Leetcode/Ch08TwoPointersConverging/LC344ReverseString.java) | in main() 0/10 | :white_large_square: not solved yet |
| 125 | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | Easy | 2 | [LC125ValidPalindrome](src/Leetcode/Ch08TwoPointersConverging/LC125ValidPalindrome.java) | in main() 0/12 | :white_large_square: not solved yet |
| 680 | [Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/) | Easy | 3 | [LC680ValidPalindromeII](src/Leetcode/Ch08TwoPointersConverging/LC680ValidPalindromeII.java) | in main() 0/12 | :white_large_square: not solved yet |
| 167 | [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | Medium | 4 | [LC167TwoSumIIInputArrayIsSorted](src/Leetcode/Ch08TwoPointersConverging/LC167TwoSumIIInputArrayIsSorted.java) | in main() 0/18 | :white_large_square: not solved yet |
| 977 | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | Easy | 5 | [LC977SquaresOfASortedArray](src/Leetcode/Ch08TwoPointersConverging/LC977SquaresOfASortedArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 11 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | Medium | 6 | [LC11ContainerWithMostWater](src/Leetcode/Ch08TwoPointersConverging/LC11ContainerWithMostWater.java) | in main() 0/10 | :white_large_square: not solved yet |
| 15 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | 7 | [LC15ThreeSum](src/Leetcode/Ch08TwoPointersConverging/LC15ThreeSum.java) | in main() 0/18 | :white_large_square: not solved yet |
| 16 | [3Sum Closest](https://leetcode.com/problems/3sum-closest/) | Medium | 8 | [LC16ThreeSumClosest](src/Leetcode/Ch08TwoPointersConverging/LC16ThreeSumClosest.java) | in main() 0/15 | :white_large_square: not solved yet |
| 18 | [4Sum](https://leetcode.com/problems/4sum/) | Medium | 9 | [LC18FourSum](src/Leetcode/Ch08TwoPointersConverging/LC18FourSum.java) | in main() 0/15 | :white_large_square: not solved yet |
| 611 | [Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number/) | Medium | 10 | [LC611ValidTriangleNumber](src/Leetcode/Ch08TwoPointersConverging/LC611ValidTriangleNumber.java) | in main() 0/15 | :white_large_square: not solved yet |
| 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | 11 | [LC42TrappingRainWater](src/Leetcode/Ch08TwoPointersConverging/LC42TrappingRainWater.java) | in main() 0/15 | :white_large_square: not solved yet |
| 986 | [Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/) | Medium | 12 | [LC986IntervalListIntersections](src/Leetcode/Ch08TwoPointersConverging/LC986IntervalListIntersections.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 9 - Sliding window, chasing

[`src/Leetcode/Ch09SlidingWindowChasing`](src/Leetcode/Ch09SlidingWindowChasing)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 643 | [Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/) | Easy | 1 | [LC643MaximumAverageSubarrayI](src/Leetcode/Ch09SlidingWindowChasing/LC643MaximumAverageSubarrayI.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1456 | [Maximum Number of Vowels in a Substring of Given Length](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/) | Medium | 2 | [LC1456MaximumNumberOfVowelsInASubstringOfGivenLength](src/Leetcode/Ch09SlidingWindowChasing/LC1456MaximumNumberOfVowelsInASubstringOfGivenLength.java) | in main() 0/18 | :white_large_square: not solved yet |
| 438 | [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | Medium | 3 | [LC438FindAllAnagramsInAString](src/Leetcode/Ch09SlidingWindowChasing/LC438FindAllAnagramsInAString.java) | in main() 0/15 | :white_large_square: not solved yet |
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | 4 | [LC3LongestSubstringWithoutRepeatingCharacters](src/Leetcode/Ch09SlidingWindowChasing/LC3LongestSubstringWithoutRepeatingCharacters.java) | in main() 0/18 | :white_large_square: not solved yet |
| 1004 | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) | Medium | 5 | [LC1004MaxConsecutiveOnesIII](src/Leetcode/Ch09SlidingWindowChasing/LC1004MaxConsecutiveOnesIII.java) | in main() 0/15 | :white_large_square: not solved yet |
| 424 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | Medium | 6 | [LC424LongestRepeatingCharacterReplacement](src/Leetcode/Ch09SlidingWindowChasing/LC424LongestRepeatingCharacterReplacement.java) | in main() 0/15 | :white_large_square: not solved yet |
| 209 | [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) | Medium | 7 | [LC209MinimumSizeSubarraySum](src/Leetcode/Ch09SlidingWindowChasing/LC209MinimumSizeSubarraySum.java) | in main() 0/18 | :white_large_square: not solved yet |
| 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | 8 | [LC76MinimumWindowSubstring](src/Leetcode/Ch09SlidingWindowChasing/LC76MinimumWindowSubstring.java) | in main() 0/18 | :white_large_square: not solved yet |
| 713 | [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) | Medium | 9 | [LC713SubarrayProductLessThanK](src/Leetcode/Ch09SlidingWindowChasing/LC713SubarrayProductLessThanK.java) | in main() 0/15 | :white_large_square: not solved yet |
| 930 | [Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/) | Medium | 10 | [LC930BinarySubarraysWithSum](src/Leetcode/Ch09SlidingWindowChasing/LC930BinarySubarraysWithSum.java) | in main() 0/15 | :white_large_square: not solved yet |
| 992 | [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) | Hard | 11 | [LC992SubarraysWithKDifferentIntegers](src/Leetcode/Ch09SlidingWindowChasing/LC992SubarraysWithKDifferentIntegers.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1438 | [Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) | Medium | 12 | [LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit](src/Leetcode/Ch09SlidingWindowChasing/LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.java) | in main() 0/18 | :white_large_square: not solved yet |

### Chapter 10 - Binary search on a sorted array

[`src/Leetcode/Ch10BinarySearchOnASortedArray`](src/Leetcode/Ch10BinarySearchOnASortedArray)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 704 | [Binary Search](https://leetcode.com/problems/binary-search/) | Easy | 1 | [LC704BinarySearch](src/Leetcode/Ch10BinarySearchOnASortedArray/LC704BinarySearch.java) | in main() 0/15 | :white_large_square: not solved yet |
| 35 | [Search Insert Position](https://leetcode.com/problems/search-insert-position/) | Easy | 2 | [LC35SearchInsertPosition](src/Leetcode/Ch10BinarySearchOnASortedArray/LC35SearchInsertPosition.java) | in main() 0/18 | :white_large_square: not solved yet |
| 34 | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | Medium | 3 | [LC34FindFirstAndLastPositionOfElementInSortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC34FindFirstAndLastPositionOfElementInSortedArray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 278 | [First Bad Version](https://leetcode.com/problems/first-bad-version/) | Easy | 4 | [LC278FirstBadVersion](src/Leetcode/Ch10BinarySearchOnASortedArray/LC278FirstBadVersion.java) | in main() 0/9 | :white_large_square: not solved yet |
| 852 | [Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) | Medium | 5 | [LC852PeakIndexInAMountainArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC852PeakIndexInAMountainArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 162 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | Medium | 6 | [LC162FindPeakElement](src/Leetcode/Ch10BinarySearchOnASortedArray/LC162FindPeakElement.java) | in main() 0/15 | :white_large_square: not solved yet |
| 540 | [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) | Medium | 7 | [LC540SingleElementInASortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC540SingleElementInASortedArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 153 | [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | Medium | 8 | [LC153FindMinimumInRotatedSortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC153FindMinimumInRotatedSortedArray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | 9 | [LC33SearchInRotatedSortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC33SearchInRotatedSortedArray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 81 | [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) | Medium | 10 | [LC81SearchInRotatedSortedArrayII](src/Leetcode/Ch10BinarySearchOnASortedArray/LC81SearchInRotatedSortedArrayII.java) | in main() 0/10 | :white_large_square: not solved yet |
| 74 | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) | Medium | 11 | [LC74SearchA2DMatrix](src/Leetcode/Ch10BinarySearchOnASortedArray/LC74SearchA2DMatrix.java) | in main() 0/15 | :white_large_square: not solved yet |
| 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | 12 | [LC4MedianOfTwoSortedArrays](src/Leetcode/Ch10BinarySearchOnASortedArray/LC4MedianOfTwoSortedArrays.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 11 - Binary search on the answer

[`src/Leetcode/Ch11BinarySearchOnTheAnswer`](src/Leetcode/Ch11BinarySearchOnTheAnswer)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 69 | [Sqrt(x)](https://leetcode.com/problems/sqrtx/) | Easy | 1 | [LC69SqrtX](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC69SqrtX.java) | in main() 0/15 | :white_large_square: not solved yet |
| 875 | [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) | Medium | 2 | [LC875KokoEatingBananas](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC875KokoEatingBananas.java) | in main() 0/12 | :white_large_square: not solved yet |
| 1011 | [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) | Medium | 3 | [LC1011CapacityToShipPackagesWithinDDays](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1011CapacityToShipPackagesWithinDDays.java) | in main() 0/12 | :white_large_square: not solved yet |
| 410 | [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) | Hard | 4 | [LC410SplitArrayLargestSum](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC410SplitArrayLargestSum.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1482 | [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/) | Medium | 5 | [LC1482MinimumNumberOfDaysToMakeMBouquets](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1482MinimumNumberOfDaysToMakeMBouquets.java) | in main() 0/12 | :white_large_square: not solved yet |
| 1552 | [Magnetic Force Between Two Balls](https://leetcode.com/problems/magnetic-force-between-two-balls/) | Medium | 6 | [LC1552MagneticForceBetweenTwoBalls](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1552MagneticForceBetweenTwoBalls.java) | in main() 0/15 | :white_large_square: not solved yet |
| 2226 | [Maximum Candies Allocated to K Children](https://leetcode.com/problems/maximum-candies-allocated-to-k-children/) | Medium | 7 | [LC2226MaximumCandiesAllocatedToKChildren](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC2226MaximumCandiesAllocatedToKChildren.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1870 | [Minimum Speed to Arrive on Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time/) | Medium | 8 | [LC1870MinimumSpeedToArriveOnTime](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1870MinimumSpeedToArriveOnTime.java) | in main() 0/12 | :white_large_square: not solved yet |
| 1631 | [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) | Medium | 9 | [LC1631PathWithMinimumEffort](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1631PathWithMinimumEffort.java) | in main() 0/18 | :white_large_square: not solved yet |
| 378 | [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) | Medium | 10 | [LC378KthSmallestElementInASortedMatrix](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC378KthSmallestElementInASortedMatrix.java) | in main() 0/15 | :white_large_square: not solved yet |
| 719 | [Find K-th Smallest Pair Distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance/) | Hard | 11 | [LC719FindKThSmallestPairDistance](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC719FindKThSmallestPairDistance.java) | in main() 0/18 | :white_large_square: not solved yet |
| 1044 | [Longest Duplicate Substring](https://leetcode.com/problems/longest-duplicate-substring/) | Hard | 12 | [LC1044LongestDuplicateSubstring](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1044LongestDuplicateSubstring.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 12 - Cyclic sort and index-as-address

[`src/Leetcode/Ch12CyclicSortAndIndexAsAddress`](src/Leetcode/Ch12CyclicSortAndIndexAsAddress)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 448 | [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) | Easy | 1 | [LC448FindAllNumbersDisappearedInAnArray](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC448FindAllNumbersDisappearedInAnArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 442 | [Find All Duplicates in an Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/) | Medium | 2 | [LC442FindAllDuplicatesInAnArray](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC442FindAllDuplicatesInAnArray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 645 | [Set Mismatch](https://leetcode.com/problems/set-mismatch/) | Easy | 3 | [LC645SetMismatch](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC645SetMismatch.java) | in main() 0/15 | :white_large_square: not solved yet |
| 268 | [Missing Number](https://leetcode.com/problems/missing-number/) | Easy | 4 | [LC268MissingNumber](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC268MissingNumber.java) | in main() 0/18 | :white_large_square: not solved yet |
| 287 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | Medium | 5 | [LC287FindTheDuplicateNumber](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC287FindTheDuplicateNumber.java) | in main() 0/18 | :white_large_square: not solved yet |
| 41 | [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) | Hard | 6 | [LC41FirstMissingPositive](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC41FirstMissingPositive.java) | in main() 0/18 | :white_large_square: not solved yet |
| 1539 | [Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/) | Easy | 7 | [LC1539KthMissingPositiveNumber](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC1539KthMissingPositiveNumber.java) | in main() 0/15 | :white_large_square: not solved yet |
| 73 | [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) | Medium | 8 | [LC73SetMatrixZeroes](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC73SetMatrixZeroes.java) | in main() 0/15 | :white_large_square: not solved yet |
| 289 | [Game of Life](https://leetcode.com/problems/game-of-life/) | Medium | 9 | [LC289GameOfLife](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC289GameOfLife.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1920 | [Build Array from Permutation](https://leetcode.com/problems/build-array-from-permutation/) | Easy | 10 | [LC1920BuildArrayFromPermutation](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC1920BuildArrayFromPermutation.java) | in main() 0/10 | :white_large_square: not solved yet |
| 189 | [Rotate Array](https://leetcode.com/problems/rotate-array/) | Medium | 11 | [LC189RotateArray](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC189RotateArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 765 | [Couples Holding Hands](https://leetcode.com/problems/couples-holding-hands/) | Hard | 12 | [LC765CouplesHoldingHands](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC765CouplesHoldingHands.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 13 - Quickselect and partition

[`src/Leetcode/Ch13QuickselectAndPartition`](src/Leetcode/Ch13QuickselectAndPartition)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 283 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | Easy | 1 | [LC283MoveZeroes](src/Leetcode/Ch13QuickselectAndPartition/LC283MoveZeroes.java) | in main() 0/15 | :white_large_square: not solved yet |
| 905 | [Sort Array By Parity](https://leetcode.com/problems/sort-array-by-parity/) | Easy | 2 | [LC905SortArrayByParity](src/Leetcode/Ch13QuickselectAndPartition/LC905SortArrayByParity.java) | in main() 0/15 | :white_large_square: not solved yet |
| 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | 3 | [LC75SortColors](src/Leetcode/Ch13QuickselectAndPartition/LC75SortColors.java) | in main() 0/15 | :white_large_square: not solved yet |
| 2161 | [Partition Array According to Given Pivot](https://leetcode.com/problems/partition-array-according-to-given-pivot/) | Medium | 4 | [LC2161PartitionArrayAccordingToGivenPivot](src/Leetcode/Ch13QuickselectAndPartition/LC2161PartitionArrayAccordingToGivenPivot.java) | in main() 0/15 | :white_large_square: not solved yet |
| 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | 5 | [LC215KthLargestElementInAnArray](src/Leetcode/Ch13QuickselectAndPartition/LC215KthLargestElementInAnArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 973 | [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) | Medium | 6 | [LC973KClosestPointsToOrigin](src/Leetcode/Ch13QuickselectAndPartition/LC973KClosestPointsToOrigin.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1985 | [Find the Kth Largest Integer in the Array](https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/) | Medium | 7 | [LC1985FindTheKthLargestIntegerInTheArray](src/Leetcode/Ch13QuickselectAndPartition/LC1985FindTheKthLargestIntegerInTheArray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | 8 | [LC347TopKFrequentElements](src/Leetcode/Ch13QuickselectAndPartition/LC347TopKFrequentElements.java) | in main() 0/18 | :white_large_square: not solved yet |
| 462 | [Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/) | Medium | 9 | [LC462MinimumMovesToEqualArrayElementsII](src/Leetcode/Ch13QuickselectAndPartition/LC462MinimumMovesToEqualArrayElementsII.java) | in main() 0/15 | :white_large_square: not solved yet |
| 324 | [Wiggle Sort II](https://leetcode.com/problems/wiggle-sort-ii/) | Medium | 10 | [LC324WiggleSortII](src/Leetcode/Ch13QuickselectAndPartition/LC324WiggleSortII.java) | in main() 0/15 | :white_large_square: not solved yet |
| 703 | [Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) | Easy | 11 | [LC703KthLargestElementInAStream](src/Leetcode/Ch13QuickselectAndPartition/LC703KthLargestElementInAStream.java) | in main() 0/15 | :white_large_square: not solved yet |
| 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | 12 | [LC4MedianOfTwoSortedArrays](src/Leetcode/Ch13QuickselectAndPartition/LC4MedianOfTwoSortedArrays.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 14 - Merge and the k-way merge

[`src/Leetcode/Ch14MergeAndTheKWayMerge`](src/Leetcode/Ch14MergeAndTheKWayMerge)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 21 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | 1 | [LC21MergeTwoSortedLists](src/Leetcode/Ch14MergeAndTheKWayMerge/LC21MergeTwoSortedLists.java) | in main() 0/18 | :white_large_square: not solved yet |
| 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | 2 | [LC88MergeSortedArray](src/Leetcode/Ch14MergeAndTheKWayMerge/LC88MergeSortedArray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 977 | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | Easy | 3 | [LC977SquaresOfASortedArray](src/Leetcode/Ch14MergeAndTheKWayMerge/LC977SquaresOfASortedArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 986 | [Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/) | Medium | 4 | [LC986IntervalListIntersections](src/Leetcode/Ch14MergeAndTheKWayMerge/LC986IntervalListIntersections.java) | in main() 0/15 | :white_large_square: not solved yet |
| 912 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | Medium | 5 | [LC912SortAnArray](src/Leetcode/Ch14MergeAndTheKWayMerge/LC912SortAnArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 148 | [Sort List](https://leetcode.com/problems/sort-list/) | Medium | 6 | [LC148SortList](src/Leetcode/Ch14MergeAndTheKWayMerge/LC148SortList.java) | in main() 0/18 | :white_large_square: not solved yet |
| 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | 7 | [LC23MergeKSortedLists](src/Leetcode/Ch14MergeAndTheKWayMerge/LC23MergeKSortedLists.java) | in main() 0/18 | :white_large_square: not solved yet |
| 378 | [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) | Medium | 8 | [LC378KthSmallestElementInASortedMatrix](src/Leetcode/Ch14MergeAndTheKWayMerge/LC378KthSmallestElementInASortedMatrix.java) | in main() 0/15 | :white_large_square: not solved yet |
| 373 | [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) | Medium | 9 | [LC373FindKPairsWithSmallestSums](src/Leetcode/Ch14MergeAndTheKWayMerge/LC373FindKPairsWithSmallestSums.java) | in main() 0/15 | :white_large_square: not solved yet |
| 315 | [Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/) | Hard | 10 | [LC315CountOfSmallerNumbersAfterSelf](src/Leetcode/Ch14MergeAndTheKWayMerge/LC315CountOfSmallerNumbersAfterSelf.java) | in main() 0/18 | :white_large_square: not solved yet |
| 493 | [Reverse Pairs](https://leetcode.com/problems/reverse-pairs/) | Hard | 11 | [LC493ReversePairs](src/Leetcode/Ch14MergeAndTheKWayMerge/LC493ReversePairs.java) | in main() 0/15 | :white_large_square: not solved yet |
| 327 | [Count of Range Sum](https://leetcode.com/problems/count-of-range-sum/) | Hard | 12 | [LC327CountOfRangeSum](src/Leetcode/Ch14MergeAndTheKWayMerge/LC327CountOfRangeSum.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 15 - Stacks and expression structure

[`src/Leetcode/Ch15StacksAndExpressionStructure`](src/Leetcode/Ch15StacksAndExpressionStructure)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 20 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | 1 | [LC20ValidParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC20ValidParentheses.java) | in main() 0/16 | :white_large_square: not solved yet |
| 921 | [Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) | Medium | 2 | [LC921MinimumAddToMakeParenthesesValid](src/Leetcode/Ch15StacksAndExpressionStructure/LC921MinimumAddToMakeParenthesesValid.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1249 | [Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/) | Medium | 3 | [LC1249MinimumRemoveToMakeValidParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC1249MinimumRemoveToMakeValidParentheses.java) | in main() 0/18 | :white_large_square: not solved yet |
| 32 | [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) | Hard | 4 | [LC32LongestValidParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC32LongestValidParentheses.java) | in main() 0/18 | :white_large_square: not solved yet |
| 1047 | [Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) | Easy | 5 | [LC1047RemoveAllAdjacentDuplicatesInString](src/Leetcode/Ch15StacksAndExpressionStructure/LC1047RemoveAllAdjacentDuplicatesInString.java) | in main() 0/10 | :white_large_square: not solved yet |
| 71 | [Simplify Path](https://leetcode.com/problems/simplify-path/) | Medium | 6 | [LC71SimplifyPath](src/Leetcode/Ch15StacksAndExpressionStructure/LC71SimplifyPath.java) | in main() 0/8 | :white_large_square: not solved yet |
| 856 | [Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/) | Medium | 7 | [LC856ScoreOfParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC856ScoreOfParentheses.java) | in main() 0/18 | :white_large_square: not solved yet |
| 394 | [Decode String](https://leetcode.com/problems/decode-string/) | Medium | 8 | [LC394DecodeString](src/Leetcode/Ch15StacksAndExpressionStructure/LC394DecodeString.java) | in main() 0/18 | :white_large_square: not solved yet |
| 150 | [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | 9 | [LC150EvaluateReversePolishNotation](src/Leetcode/Ch15StacksAndExpressionStructure/LC150EvaluateReversePolishNotation.java) | in main() 0/12 | :white_large_square: not solved yet |
| 227 | [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) | Medium | 10 | [LC227BasicCalculatorII](src/Leetcode/Ch15StacksAndExpressionStructure/LC227BasicCalculatorII.java) | in main() 0/18 | :white_large_square: not solved yet |
| 224 | [Basic Calculator](https://leetcode.com/problems/basic-calculator/) | Hard | 11 | [LC224BasicCalculator](src/Leetcode/Ch15StacksAndExpressionStructure/LC224BasicCalculator.java) | in main() 0/18 | :white_large_square: not solved yet |
| 726 | [Number of Atoms](https://leetcode.com/problems/number-of-atoms/) | Hard | 12 | [LC726NumberOfAtoms](src/Leetcode/Ch15StacksAndExpressionStructure/LC726NumberOfAtoms.java) | in main() 0/18 | :white_large_square: not solved yet |

### Chapter 16 - The monotonic stack

[`src/Leetcode/Ch16TheMonotonicStack`](src/Leetcode/Ch16TheMonotonicStack)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | 1 | [LC739DailyTemperatures](src/Leetcode/Ch16TheMonotonicStack/LC739DailyTemperatures.java) | in main() 0/18 | :white_large_square: not solved yet |
| 496 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | Easy | 2 | [LC496NextGreaterElementI](src/Leetcode/Ch16TheMonotonicStack/LC496NextGreaterElementI.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1475 | [Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) | Easy | 3 | [LC1475FinalPricesWithASpecialDiscountInAShop](src/Leetcode/Ch16TheMonotonicStack/LC1475FinalPricesWithASpecialDiscountInAShop.java) | in main() 0/12 | :white_large_square: not solved yet |
| 503 | [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) | Medium | 4 | [LC503NextGreaterElementII](src/Leetcode/Ch16TheMonotonicStack/LC503NextGreaterElementII.java) | in main() 0/10 | :white_large_square: not solved yet |
| 901 | [Online Stock Span](https://leetcode.com/problems/online-stock-span/) | Medium | 5 | [LC901OnlineStockSpan](src/Leetcode/Ch16TheMonotonicStack/LC901OnlineStockSpan.java) | in main() 0/8 | :white_large_square: not solved yet |
| 1944 | [Number of Visible People in a Queue](https://leetcode.com/problems/number-of-visible-people-in-a-queue/) | Hard | 6 | [LC1944NumberOfVisiblePeopleInAQueue](src/Leetcode/Ch16TheMonotonicStack/LC1944NumberOfVisiblePeopleInAQueue.java) | in main() 0/10 | :white_large_square: not solved yet |
| 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | 7 | [LC84LargestRectangleInHistogram](src/Leetcode/Ch16TheMonotonicStack/LC84LargestRectangleInHistogram.java) | in main() 0/15 | :white_large_square: not solved yet |
| 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | 8 | [LC42TrappingRainWater](src/Leetcode/Ch16TheMonotonicStack/LC42TrappingRainWater.java) | in main() 0/15 | :white_large_square: not solved yet |
| 907 | [Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) | Medium | 9 | [LC907SumOfSubarrayMinimums](src/Leetcode/Ch16TheMonotonicStack/LC907SumOfSubarrayMinimums.java) | in main() 0/15 | :white_large_square: not solved yet |
| 2104 | [Sum of Subarray Ranges](https://leetcode.com/problems/sum-of-subarray-ranges/) | Medium | 10 | [LC2104SumOfSubarrayRanges](src/Leetcode/Ch16TheMonotonicStack/LC2104SumOfSubarrayRanges.java) | in main() 0/12 | :white_large_square: not solved yet |
| 1856 | [Maximum Subarray Min-Product](https://leetcode.com/problems/maximum-subarray-min-product/) | Medium | 11 | [LC1856MaximumSubarrayMinProduct](src/Leetcode/Ch16TheMonotonicStack/LC1856MaximumSubarrayMinProduct.java) | in main() 0/12 | :white_large_square: not solved yet |
| 402 | [Remove K Digits](https://leetcode.com/problems/remove-k-digits/) | Medium | 12 | [LC402RemoveKDigits](src/Leetcode/Ch16TheMonotonicStack/LC402RemoveKDigits.java) | in main() 0/12 | :white_large_square: not solved yet |

### Chapter 17 - The monotonic deque

[`src/Leetcode/Ch17TheMonotonicDeque`](src/Leetcode/Ch17TheMonotonicDeque)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | 1 | [LC239SlidingWindowMaximum](src/Leetcode/Ch17TheMonotonicDeque/LC239SlidingWindowMaximum.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1696 | [Jump Game VI](https://leetcode.com/problems/jump-game-vi/) | Medium | 2 | [LC1696JumpGameVI](src/Leetcode/Ch17TheMonotonicDeque/LC1696JumpGameVI.java) | in main() 0/18 | :white_large_square: not solved yet |
| 1425 | [Constrained Subsequence Sum](https://leetcode.com/problems/constrained-subsequence-sum/) | Hard | 3 | [LC1425ConstrainedSubsequenceSum](src/Leetcode/Ch17TheMonotonicDeque/LC1425ConstrainedSubsequenceSum.java) | in main() 0/18 | :white_large_square: not solved yet |
| 1438 | [Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) | Medium | 4 | [LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit](src/Leetcode/Ch17TheMonotonicDeque/LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.java) | in main() 0/18 | :white_large_square: not solved yet |
| 2762 | [Continuous Subarrays](https://leetcode.com/problems/continuous-subarrays/) | Medium | 5 | [LC2762ContinuousSubarrays](src/Leetcode/Ch17TheMonotonicDeque/LC2762ContinuousSubarrays.java) | in main() 0/14 | :white_large_square: not solved yet |
| 2398 | [Maximum Number of Robots Within Budget](https://leetcode.com/problems/maximum-number-of-robots-within-budget/) | Hard | 6 | [LC2398MaximumNumberOfRobotsWithinBudget](src/Leetcode/Ch17TheMonotonicDeque/LC2398MaximumNumberOfRobotsWithinBudget.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1499 | [Max Value of Equation](https://leetcode.com/problems/max-value-of-equation/) | Hard | 7 | [LC1499MaxValueOfEquation](src/Leetcode/Ch17TheMonotonicDeque/LC1499MaxValueOfEquation.java) | in main() 0/15 | :white_large_square: not solved yet |
| 862 | [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) | Hard | 8 | [LC862ShortestSubarrayWithSumAtLeastK](src/Leetcode/Ch17TheMonotonicDeque/LC862ShortestSubarrayWithSumAtLeastK.java) | in main() 0/18 | :white_large_square: not solved yet |
| 918 | [Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/) | Medium | 9 | [LC918MaximumSumCircularSubarray](src/Leetcode/Ch17TheMonotonicDeque/LC918MaximumSumCircularSubarray.java) | in main() 0/18 | :white_large_square: not solved yet |
| 1687 | [Delivering Boxes from Storage to Ports](https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/) | Hard | 10 | [LC1687DeliveringBoxesFromStorageToPorts](src/Leetcode/Ch17TheMonotonicDeque/LC1687DeliveringBoxesFromStorageToPorts.java) | in main() 0/18 | :white_large_square: not solved yet |
| 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | 11 | [LC84LargestRectangleInHistogram](src/Leetcode/Ch17TheMonotonicDeque/LC84LargestRectangleInHistogram.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 18 - Heaps and the k-selection family

[`src/Leetcode/Ch18HeapsAndTheKSelectionFamily`](src/Leetcode/Ch18HeapsAndTheKSelectionFamily)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 1046 | [Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) | Easy | 1 | [LC1046LastStoneWeight](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC1046LastStoneWeight.java) | in main() 0/10 | :white_large_square: not solved yet |
| 703 | [Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) | Easy | 2 | [LC703KthLargestElementInAStream](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC703KthLargestElementInAStream.java) | in main() 0/15 | :white_large_square: not solved yet |
| 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | 3 | [LC215KthLargestElementInAnArray](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC215KthLargestElementInAnArray.java) | in main() 0/15 | :white_large_square: not solved yet |
| 973 | [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) | Medium | 4 | [LC973KClosestPointsToOrigin](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC973KClosestPointsToOrigin.java) | in main() 0/15 | :white_large_square: not solved yet |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | 5 | [LC347TopKFrequentElements](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC347TopKFrequentElements.java) | in main() 0/18 | :white_large_square: not solved yet |
| 692 | [Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/) | Medium | 6 | [LC692TopKFrequentWords](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC692TopKFrequentWords.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1167 | [Minimum Cost to Connect Sticks](https://leetcode.com/problems/minimum-cost-to-connect-sticks/) | Medium | 7 | [LC1167MinimumCostToConnectSticks](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC1167MinimumCostToConnectSticks.java) | in main() 0/18 | :white_large_square: not solved yet |
| 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | 8 | [LC23MergeKSortedLists](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC23MergeKSortedLists.java) | in main() 0/18 | :white_large_square: not solved yet |
| 502 | [IPO](https://leetcode.com/problems/ipo/) | Hard | 9 | [LC502IPO](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC502IPO.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1642 | [Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/) | Medium | 10 | [LC1642FurthestBuildingYouCanReach](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC1642FurthestBuildingYouCanReach.java) | in main() 0/18 | :white_large_square: not solved yet |
| 871 | [Minimum Number of Refueling Stops](https://leetcode.com/problems/minimum-number-of-refueling-stops/) | Hard | 11 | [LC871MinimumNumberOfRefuelingStops](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC871MinimumNumberOfRefuelingStops.java) | in main() 0/12 | :white_large_square: not solved yet |
| 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | 12 | [LC239SlidingWindowMaximum](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC239SlidingWindowMaximum.java) | in main() 0/15 | :white_large_square: not solved yet |

### Chapter 19 - Two heaps and the running median

[`src/Leetcode/Ch19TwoHeapsAndTheRunningMedian`](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 703 | [Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) | Easy | 1 | [LC703KthLargestElementInAStream](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC703KthLargestElementInAStream.java) | in main() 0/15 | :white_large_square: not solved yet |
| 295 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Hard | 2 | [LC295FindMedianFromDataStream](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC295FindMedianFromDataStream.java) | in main() 0/12 | :white_large_square: not solved yet |
| 2102 | [Sequentially Ordinal Rank Tracker](https://leetcode.com/problems/sequentially-ordinal-rank-tracker/) | Hard | 3 | [LC2102SequentiallyOrdinalRankTracker](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC2102SequentiallyOrdinalRankTracker.java) | in main() 0/12 | :white_large_square: not solved yet |
| 480 | [Sliding Window Median](https://leetcode.com/problems/sliding-window-median/) | Hard | 4 | [LC480SlidingWindowMedian](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC480SlidingWindowMedian.java) | in main() 0/15 | :white_large_square: not solved yet |
| 218 | [The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/) | Hard | 5 | [LC218TheSkylineProblem](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC218TheSkylineProblem.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1825 | [Finding MK Average](https://leetcode.com/problems/finding-mk-average/) | Hard | 6 | [LC1825FindingMKAverage](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1825FindingMKAverage.java) | in main() 0/12 | :white_large_square: not solved yet |
| 3013 | [Divide an Array Into Subarrays With Minimum Cost II](https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/) | Hard | 7 | [LC3013DivideAnArrayIntoSubarraysWithMinimumCostII](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC3013DivideAnArrayIntoSubarraysWithMinimumCostII.java) | in main() 0/12 | :white_large_square: not solved yet |
| 2163 | [Minimum Difference in Sums After Removal of Elements](https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/) | Hard | 8 | [LC2163MinimumDifferenceInSumsAfterRemovalOfElements](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC2163MinimumDifferenceInSumsAfterRemovalOfElements.java) | in main() 0/10 | :white_large_square: not solved yet |
| 502 | [IPO](https://leetcode.com/problems/ipo/) | Hard | 9 | [LC502IPO](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC502IPO.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1801 | [Number of Orders in the Backlog](https://leetcode.com/problems/number-of-orders-in-the-backlog/) | Medium | 10 | [LC1801NumberOfOrdersInTheBacklog](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1801NumberOfOrdersInTheBacklog.java) | in main() 0/10 | :white_large_square: not solved yet |
| 1670 | [Design Front Middle Back Queue](https://leetcode.com/problems/design-front-middle-back-queue/) | Medium | 11 | [LC1670DesignFrontMiddleBackQueue](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1670DesignFrontMiddleBackQueue.java) | in main() 0/8 | :white_large_square: not solved yet |
| 1649 | [Create Sorted Array through Instructions](https://leetcode.com/problems/create-sorted-array-through-instructions/) | Hard | 12 | [LC1649CreateSortedArrayThroughInstructions](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1649CreateSortedArrayThroughInstructions.java) | in main() 0/18 | :white_large_square: not solved yet |

### Chapter 20 - Intervals and sweep line

[`src/Leetcode/Ch20IntervalsAndSweepLine`](src/Leetcode/Ch20IntervalsAndSweepLine)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | 1 | [LC56MergeIntervals](src/Leetcode/Ch20IntervalsAndSweepLine/LC56MergeIntervals.java) | in main() 0/18 | :white_large_square: not solved yet |
| 57 | [Insert Interval](https://leetcode.com/problems/insert-interval/) | Medium | 2 | [LC57InsertInterval](src/Leetcode/Ch20IntervalsAndSweepLine/LC57InsertInterval.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1288 | [Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/) | Medium | 3 | [LC1288RemoveCoveredIntervals](src/Leetcode/Ch20IntervalsAndSweepLine/LC1288RemoveCoveredIntervals.java) | in main() 0/10 | :white_large_square: not solved yet |
| 2848 | [Points That Intersect With Cars](https://leetcode.com/problems/points-that-intersect-with-cars/) | Easy | 4 | [LC2848PointsThatIntersectWithCars](src/Leetcode/Ch20IntervalsAndSweepLine/LC2848PointsThatIntersectWithCars.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1854 | [Maximum Population Year](https://leetcode.com/problems/maximum-population-year/) | Easy | 5 | [LC1854MaximumPopulationYear](src/Leetcode/Ch20IntervalsAndSweepLine/LC1854MaximumPopulationYear.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1094 | [Car Pooling](https://leetcode.com/problems/car-pooling/) | Medium | 6 | [LC1094CarPooling](src/Leetcode/Ch20IntervalsAndSweepLine/LC1094CarPooling.java) | in main() 0/15 | :white_large_square: not solved yet |
| 1109 | [Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/) | Medium | 7 | [LC1109CorporateFlightBookings](src/Leetcode/Ch20IntervalsAndSweepLine/LC1109CorporateFlightBookings.java) | in main() 0/15 | :white_large_square: not solved yet |
| 253 | [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) | Medium | 8 | [LC253MeetingRoomsII](src/Leetcode/Ch20IntervalsAndSweepLine/LC253MeetingRoomsII.java) | in main() 0/15 | :white_large_square: not solved yet |
| 2251 | [Number of Flowers in Full Bloom](https://leetcode.com/problems/number-of-flowers-in-full-bloom/) | Hard | 9 | [LC2251NumberOfFlowersInFullBloom](src/Leetcode/Ch20IntervalsAndSweepLine/LC2251NumberOfFlowersInFullBloom.java) | in main() 0/15 | :white_large_square: not solved yet |
| 732 | [My Calendar III](https://leetcode.com/problems/my-calendar-iii/) | Hard | 10 | [LC732MyCalendarIII](src/Leetcode/Ch20IntervalsAndSweepLine/LC732MyCalendarIII.java) | in main() 0/12 | :white_large_square: not solved yet |
| 218 | [The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/) | Hard | 11 | [LC218TheSkylineProblem](src/Leetcode/Ch20IntervalsAndSweepLine/LC218TheSkylineProblem.java) | in main() 0/15 | :white_large_square: not solved yet |
| 850 | [Rectangle Area II](https://leetcode.com/problems/rectangle-area-ii/) | Hard | 12 | [LC850RectangleAreaII](src/Leetcode/Ch20IntervalsAndSweepLine/LC850RectangleAreaII.java) | in main() 0/15 | :white_large_square: not solved yet |

### ArithMetic (not in the book)

[`src/Leetcode/ArithMetic`](src/Leetcode/ArithMetic)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 3658 | [GCD of Odd and Even Sums](https://leetcode.com/problems/gcd-of-odd-and-even-sums/) | Easy | - | [LC3658GCDofOddandEvenSums](src/Leetcode/ArithMetic/LC3658GCDofOddandEvenSums.java) | in main() 6/6 |  |

### Contest191 (not in the book)

[`src/Leetcode/Contest191`](src/Leetcode/Contest191)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 4048 | [Count Values With Equally Spaced Occurrences I](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-i/) | Easy | - | [LC191CountSpecialIntegers1](src/Leetcode/Contest191/LC191CountSpecialIntegers1.java) | in main() 7/7 |  |
| 4049 | [Count Values With Equally Spaced Occurrences II](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-ii/) | Medium | - | [LC191CountSpecialIntegers2](src/Leetcode/Contest191/LC191CountSpecialIntegers2.java) | in main() 7/7 |  |
| 4050 | [Minimum Days to Score Exactly N Points](https://leetcode.com/problems/minimum-days-to-score-exactly-n-points/) | Medium | - | [LC191minDays](src/Leetcode/Contest191/LC191minDays.java) | in main() 3/7 | :warning: Wrong answers: minDays(2)=2 (expected 3), minDays(9)=5 (expected 6), minDays(12)=6 ... |

### Daily (not in the book)

[`src/Leetcode/Daily`](src/Leetcode/Daily)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 836 | [Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/) | Easy | - | [RectangleOverLap](src/Leetcode/Daily/RectangleOverLap.java) | in main() 7/7 |  |
| 1658 | [Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/) | Medium | - | [LC1658MinimumOperationstoReduceXtoZero](src/Leetcode/Daily/LC1658MinimumOperationstoReduceXtoZero.java) | in main() 7/7 |  |
| 2094 | [Finding 3-Digit Even Numbers](https://leetcode.com/problems/finding-3-digit-even-numbers/) | Easy | - | [LC2094Find3DigitEvenNumbers](src/Leetcode/Daily/LC2094Find3DigitEvenNumbers.java) | in main() 7/7 |  |
| 3483 | [Unique 3-Digit Even Numbers](https://leetcode.com/problems/unique-3-digit-even-numbers/) | Easy | - | [LC3483Unique3DigitsEvenNumbers](src/Leetcode/Daily/LC3483Unique3DigitsEvenNumbers.java) | in main() 8/8 |  |
| 3498 | [Reverse Degree of a String](https://leetcode.com/problems/reverse-degree-of-a-string/) | Easy | - | [LC3498ReverseDegreeString](src/Leetcode/Daily/LC3498ReverseDegreeString.java) | in main() 6/6 |  |
| 3550 | [Smallest Index With Digit Sum Equal to Index](https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/) | Easy | - | [LC3550SmallestIndex](src/Leetcode/Daily/LC3550SmallestIndex.java) | in main() 7/7 |  |
| 3718 | [Smallest Missing Multiple of K](https://leetcode.com/problems/smallest-missing-multiple-of-k/) | Easy | - | [LC3718missingMultiple](src/Leetcode/Daily/LC3718missingMultiple.java) | in main() 6/6 |  |
| 3870 | [Count Commas in Range](https://leetcode.com/problems/count-commas-in-range/) | Easy | - | [LC3870CountCommasInRange](src/Leetcode/Daily/LC3870CountCommasInRange.java) | - | :construction: Empty stub: the class has no method and no main(), the problem (count commas in ... |
| 3876 | [Construct Uniform Parity Array II](https://leetcode.com/problems/construct-uniform-parity-array-ii/) | Medium | - | [LC3876ConstructUniformParityArrayII](src/Leetcode/Daily/LC3876ConstructUniformParityArrayII.java) | in main() 7/7 |  |
| 4034 | [Minimum Bishop Moves to Reach Target](https://leetcode.com/problems/minimum-bishop-moves-to-reach-target/) | Medium | - | [LCQ1MinBishopMoves](src/Leetcode/Daily/LCQ1MinBishopMoves.java) | in main() 7/7 |  |

### Random (not in the book)

[`src/Leetcode/Random`](src/Leetcode/Random)

| # | Problem | Difficulty | Practice # | Solution | Test | Status |
|---|---|---|---|---|---|---|
| 412 | [Fizz Buzz](https://leetcode.com/problems/fizz-buzz/) | Easy | - | [LC412FizzBuzz](src/Leetcode/Random/LC412FizzBuzz.java) | in main() 6/6 |  |

## Every LeetCode problem by number

<details>
<summary>Show all problems sorted by number, with every file that solves each one</summary>

| # | Problem | Difficulty | Solution files |
|---|---|---|---|
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | [LC1TwoSumComplementMap](src/Practice/HashMap/LC1TwoSumComplementMap.java)<br>[LC1TwoSumThreeApproaches](src/Practice/HashMap/LC1TwoSumThreeApproaches.java)<br>[LC1TwoSumHashMap](src/Practice/HashMap/LC1TwoSumHashMap.java)<br>[LC1TwoSum](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC1TwoSum.java) |
| 2 | [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) | Medium | [LC2AddTwoNumbers](src/Practice/LinkedList/LC2AddTwoNumbers.java) |
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | [LC3LongestSubstringWithoutRepeatingCharactersQueueWindow](src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersQueueWindow.java)<br>[LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches](src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches.java)<br>[LC3LongestSubstringWithoutRepeatingCharacters](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC3LongestSubstringWithoutRepeatingCharacters.java)<br>[LC3LongestSubstringWithoutRepeatingCharacters](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC3LongestSubstringWithoutRepeatingCharacters.java)<br>[LC3LongestSubstringWithoutRepeatingCharacters](src/Leetcode/Ch09SlidingWindowChasing/LC3LongestSubstringWithoutRepeatingCharacters.java) (not solved yet) |
| 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | [LC4MedianOfTwoSortedArrays](src/Practice/BinarySearch/LC4MedianOfTwoSortedArrays.java)<br>[LC4MedianOfTwoSortedArrays](src/Leetcode/Ch10BinarySearchOnASortedArray/LC4MedianOfTwoSortedArrays.java) (not solved yet)<br>[LC4MedianOfTwoSortedArrays](src/Leetcode/Ch13QuickselectAndPartition/LC4MedianOfTwoSortedArrays.java) (not solved yet) |
| 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | [LC5LongestPalindromicSubstringV1](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV1.java)<br>[LC5LongestPalindromicSubstringV2](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV2.java) |
| 7 | [Reverse Integer](https://leetcode.com/problems/reverse-integer/) | Medium | [LC7ReverseInteger](src/Practice/Math/LC7ReverseInteger.java) |
| 8 | [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/) | Medium | [LC8StringToIntegerAtoi](src/Practice/Strings/LC8StringToIntegerAtoi.java) |
| 9 | [Palindrome Number](https://leetcode.com/problems/palindrome-number/) | Easy | [LC9PalindromeNumber](src/Practice/Math/LC9PalindromeNumber.java) |
| 11 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | Medium | [LC11ContainerWithMostWater](src/Leetcode/Ch08TwoPointersConverging/LC11ContainerWithMostWater.java) (not solved yet) |
| 13 | [Roman to Integer](https://leetcode.com/problems/roman-to-integer/) | Easy | [LC13RomanToInteger](src/Practice/Strings/LC13RomanToInteger.java) |
| 14 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | Easy | [LC14LongestCommonPrefixThreeApproaches](src/Practice/Strings/LC14LongestCommonPrefixThreeApproaches.java)<br>[LC14LongestCommonPrefixVerticalScan](src/Practice/Strings/LC14LongestCommonPrefixVerticalScan.java) |
| 15 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | [LC15ThreeSum](src/Practice/TwoPointers/LC15ThreeSum.java)<br>[LC15ThreeSum](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC15ThreeSum.java) (not solved yet)<br>[LC15ThreeSum](src/Leetcode/Ch08TwoPointersConverging/LC15ThreeSum.java) (not solved yet) |
| 16 | [3Sum Closest](https://leetcode.com/problems/3sum-closest/) | Medium | [LC16ThreeSumClosest](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC16ThreeSumClosest.java) (not solved yet)<br>[LC16ThreeSumClosest](src/Leetcode/Ch08TwoPointersConverging/LC16ThreeSumClosest.java) (not solved yet) |
| 18 | [4Sum](https://leetcode.com/problems/4sum/) | Medium | [LC18FourSum](src/Practice/TwoPointers/LC18FourSum.java)<br>[LC18FourSum](src/Leetcode/Ch08TwoPointersConverging/LC18FourSum.java) (not solved yet) |
| 19 | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | Medium | [LC19RemoveNthNodeFromEndOfListV1](src/Practice/LinkedList/LC19RemoveNthNodeFromEndOfListV1.java)<br>[LC19RemoveNthNodeFromEndOfListV2](src/Practice/LinkedList/LC19RemoveNthNodeFromEndOfListV2.java) |
| 20 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | [LC20ValidParentheses](src/Practice/Stack/LC20ValidParentheses.java)<br>[LC20ValidParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC20ValidParentheses.java) (not solved yet) |
| 21 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | [LC21MergeTwoSortedLists](src/Practice/LinkedList/LC21MergeTwoSortedLists.java)<br>[LC21MergeTwoSortedLists](src/Leetcode/Ch14MergeAndTheKWayMerge/LC21MergeTwoSortedLists.java) (not solved yet)<br>[GFGMergeTwoSortedArraysWithoutExtraSpace](src/Practice/TwoPointers/GFGMergeTwoSortedArraysWithoutExtraSpace.java) (second problem in this file) |
| 22 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | [LC22GenerateParentheses](src/Practice/Backtracking/LC22GenerateParentheses.java) |
| 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | [LC23MergeKSortedLists](src/Practice/Heap/LC23MergeKSortedLists.java)<br>[LC23MergeKSortedLists](src/Leetcode/Ch14MergeAndTheKWayMerge/LC23MergeKSortedLists.java) (not solved yet)<br>[LC23MergeKSortedLists](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC23MergeKSortedLists.java) (not solved yet) |
| 25 | [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) | Hard | [LC25ReverseNodesInKGroup](src/Practice/LinkedList/LC25ReverseNodesInKGroup.java) |
| 26 | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Easy | [LC26RemoveDuplicatesFromSortedArray](src/Practice/TwoPointers/LC26RemoveDuplicatesFromSortedArray.java) |
| 27 | [Remove Element](https://leetcode.com/problems/remove-element/) | Easy | [LC27RemoveElementThreeApproaches](src/Practice/TwoPointers/LC27RemoveElementThreeApproaches.java)<br>[LC27RemoveElementTwoEndsSwap](src/Practice/TwoPointers/LC27RemoveElementTwoEndsSwap.java) |
| 31 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | Medium | [LC31NextPermutationOptimal](src/Practice/Arrays/LC31NextPermutationOptimal.java)<br>[LC31NextPermutationBruteForce](src/Practice/Arrays/LC31NextPermutationBruteForce.java) |
| 32 | [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) | Hard | [LC32LongestValidParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC32LongestValidParentheses.java) (not solved yet) |
| 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | [LC33SearchInRotatedSortedArray](src/Practice/BinarySearch/LC33SearchInRotatedSortedArray.java)<br>[LC33SearchInRotatedSortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC33SearchInRotatedSortedArray.java) (not solved yet) |
| 34 | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | Medium | [LC34FindFirstAndLastPositionOfElementInSortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC34FindFirstAndLastPositionOfElementInSortedArray.java) (not solved yet) |
| 35 | [Search Insert Position](https://leetcode.com/problems/search-insert-position/) | Easy | [LC35SearchInsertPosition](src/Leetcode/Ch10BinarySearchOnASortedArray/LC35SearchInsertPosition.java) (not solved yet) |
| 36 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | [LC36ValidSudoku](src/Practice/Matrix/LC36ValidSudoku.java) |
| 37 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) | Hard | [LC37SudokuSolver](src/Practice/Backtracking/LC37SudokuSolver.java) |
| 38 | [Count and Say](https://leetcode.com/problems/count-and-say/) | Medium | [LC38CountAndSay](src/Practice/Strings/LC38CountAndSay.java) |
| 39 | [Combination Sum](https://leetcode.com/problems/combination-sum/) | Medium | [LC39CombinationSum](src/Practice/Backtracking/LC39CombinationSum.java) |
| 41 | [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) | Hard | [LC41FirstMissingPositive](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC41FirstMissingPositive.java) (not solved yet) |
| 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | [LC42TrappingRainWaterV2](src/Practice/TwoPointers/LC42TrappingRainWaterV2.java)<br>[LC42TrappingRainWaterV1](src/Practice/TwoPointers/LC42TrappingRainWaterV1.java)<br>[LC42TrappingRainWater](src/Leetcode/Ch08TwoPointersConverging/LC42TrappingRainWater.java) (not solved yet)<br>[LC42TrappingRainWater](src/Leetcode/Ch16TheMonotonicStack/LC42TrappingRainWater.java) (not solved yet) |
| 46 | [Permutations](https://leetcode.com/problems/permutations/) | Medium | [LC46Permutations](src/Practice/Backtracking/LC46Permutations.java) |
| 48 | [Rotate Image](https://leetcode.com/problems/rotate-image/) | Medium | [LC48RotateImage](src/Practice/Matrix/LC48RotateImage.java) |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | [LC49GroupAnagramsPrimeProduct](src/Practice/HashMap/LC49GroupAnagramsPrimeProduct.java)<br>[LC49GroupAnagramsSortedKey](src/Practice/HashMap/LC49GroupAnagramsSortedKey.java)<br>[LC49GroupAnagrams](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC49GroupAnagrams.java)<br>[LC49GroupAnagrams](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC49GroupAnagrams.java) |
| 50 | [Pow(x, n)](https://leetcode.com/problems/powx-n/) | Medium | [LC50PowXN](src/Practice/Math/LC50PowXN.java) |
| 51 | [N-Queens](https://leetcode.com/problems/n-queens/) | Hard | [LC51NQueens](src/Practice/Backtracking/LC51NQueens.java) |
| 52 | [N-Queens II](https://leetcode.com/problems/n-queens-ii/) | Hard | [LC52NQueensII](src/Practice/Backtracking/LC52NQueensII.java) |
| 53 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | Medium | [LC53MaximumSubarray](src/Practice/Arrays/LC53MaximumSubarray.java) |
| 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | [LC56MergeIntervals](src/Practice/Intervals/LC56MergeIntervals.java)<br>[LC56MergeIntervals](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC56MergeIntervals.java) (not solved yet)<br>[LC56MergeIntervals](src/Leetcode/Ch20IntervalsAndSweepLine/LC56MergeIntervals.java) (not solved yet) |
| 57 | [Insert Interval](https://leetcode.com/problems/insert-interval/) | Medium | [LC57InsertInterval](src/Leetcode/Ch20IntervalsAndSweepLine/LC57InsertInterval.java) (not solved yet) |
| 60 | [Permutation Sequence](https://leetcode.com/problems/permutation-sequence/) | Hard | [LC60PermutationSequence](src/Practice/Math/LC60PermutationSequence.java) |
| 61 | [Rotate List](https://leetcode.com/problems/rotate-list/) | Medium | [LC61RotateList](src/Practice/LinkedList/LC61RotateList.java) |
| 62 | [Unique Paths](https://leetcode.com/problems/unique-paths/) | Medium | [LC62UniquePaths](src/Practice/DynamicProgramming/LC62UniquePaths.java) |
| 66 | [Plus One](https://leetcode.com/problems/plus-one/) | Easy | [LC66PlusOneCopyArray](src/Practice/Arrays/LC66PlusOneCopyArray.java)<br>[LC66PlusOneInPlace](src/Practice/Arrays/LC66PlusOneInPlace.java) |
| 69 | [Sqrt(x)](https://leetcode.com/problems/sqrtx/) | Easy | [LC69SqrtX](src/Practice/BinarySearch/LC69SqrtX.java)<br>[LC69SqrtX](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC69SqrtX.java) (not solved yet) |
| 71 | [Simplify Path](https://leetcode.com/problems/simplify-path/) | Medium | [LC71SimplifyPath](src/Leetcode/Ch15StacksAndExpressionStructure/LC71SimplifyPath.java) (not solved yet) |
| 73 | [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) | Medium | [LC73SetMatrixZeroes](src/Practice/Matrix/LC73SetMatrixZeroes.java)<br>[LC73SetMatrixZeroes](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC73SetMatrixZeroes.java) (not solved yet) |
| 74 | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) | Medium | [LC74SearchA2DMatrix](src/Practice/BinarySearch/LC74SearchA2DMatrix.java)<br>[LC74SearchA2DMatrix](src/Leetcode/Ch10BinarySearchOnASortedArray/LC74SearchA2DMatrix.java) (not solved yet) |
| 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | [LC75SortColors](src/Practice/TwoPointers/LC75SortColors.java)<br>[LC75SortColors](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC75SortColors.java) (not solved yet)<br>[LC75SortColors](src/Leetcode/Ch13QuickselectAndPartition/LC75SortColors.java) (not solved yet) |
| 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | [LC76MinimumWindowSubstring](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC76MinimumWindowSubstring.java)<br>[LC76MinimumWindowSubstring](src/Leetcode/Ch09SlidingWindowChasing/LC76MinimumWindowSubstring.java) (not solved yet) |
| 78 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | [LC78Subsets](src/Practice/Backtracking/LC78Subsets.java)<br>[LC78Subsets](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC78Subsets.java) |
| 81 | [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) | Medium | [LC81SearchInRotatedSortedArrayII](src/Leetcode/Ch10BinarySearchOnASortedArray/LC81SearchInRotatedSortedArrayII.java) (not solved yet) |
| 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | [LC84LargestRectangleInHistogramIndexStack](src/Practice/Stack/LC84LargestRectangleInHistogramIndexStack.java)<br>[LC84LargestRectangleInHistogramPairStack](src/Practice/Stack/LC84LargestRectangleInHistogramPairStack.java)<br>[LC84LargestRectangleInHistogram](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC84LargestRectangleInHistogram.java) (not solved yet)<br>[LC84LargestRectangleInHistogram](src/Leetcode/Ch16TheMonotonicStack/LC84LargestRectangleInHistogram.java) (not solved yet)<br>[LC84LargestRectangleInHistogram](src/Leetcode/Ch17TheMonotonicDeque/LC84LargestRectangleInHistogram.java) (not solved yet) |
| 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | [LC88MergeSortedArray](src/Practice/Sorting/LC88MergeSortedArray.java)<br>[LC88MergeSortedArray](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC88MergeSortedArray.java) (not solved yet)<br>[LC88MergeSortedArray](src/Leetcode/Ch14MergeAndTheKWayMerge/LC88MergeSortedArray.java) (not solved yet) |
| 90 | [Subsets II](https://leetcode.com/problems/subsets-ii/) | Medium | [LC90SubsetsII](src/Practice/Backtracking/LC90SubsetsII.java) |
| 94 | [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/) | Easy | [LC94BinaryTreeInorderTraversal](src/Practice/BinaryTree/LC94BinaryTreeInorderTraversal.java) |
| 98 | [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) | Medium | [LC98ValidateBinarySearchTree](src/Practice/BST/LC98ValidateBinarySearchTree.java) |
| 100 | [Same Tree](https://leetcode.com/problems/same-tree/) | Easy | [LC100SameTree](src/Practice/BinaryTree/LC100SameTree.java) |
| 101 | [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) | Easy | [LC101SymmetricTree](src/Practice/BinaryTree/LC101SymmetricTree.java) |
| 102 | [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) | Medium | [LC102BinaryTreeLevelOrderTraversal](src/Practice/BinaryTree/LC102BinaryTreeLevelOrderTraversal.java) |
| 103 | [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) | Medium | [LC103BinaryTreeZigzagLevelOrderTraversal](src/Practice/BinaryTree/LC103BinaryTreeZigzagLevelOrderTraversal.java) |
| 104 | [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | Easy | [LC104MaximumDepthOfBinaryTree](src/Practice/BinaryTree/LC104MaximumDepthOfBinaryTree.java) |
| 105 | [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | Medium | [LC105ConstructBinaryTreeFromPreorderAndInorderTraversal](src/Practice/BinaryTree/LC105ConstructBinaryTreeFromPreorderAndInorderTraversal.java) |
| 106 | [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) | Medium | [LC106ConstructBinaryTreeFromInorderAndPostorderTraversal](src/Practice/BinaryTree/LC106ConstructBinaryTreeFromInorderAndPostorderTraversal.java) |
| 108 | [Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/) | Easy | [LC108ConvertSortedArrayToBinarySearchTree](src/Practice/BST/LC108ConvertSortedArrayToBinarySearchTree.java) |
| 110 | [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) | Easy | [LC110BalancedBinaryTree](src/Practice/BinaryTree/LC110BalancedBinaryTree.java) |
| 114 | [Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) | Medium | [LC114FlattenBinaryTreeToLinkedList](src/Practice/BinaryTree/LC114FlattenBinaryTreeToLinkedList.java) |
| 116 | [Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/) | Medium | [LC116PopulatingNextRightPointersInEachNode](src/Practice/BinaryTree/LC116PopulatingNextRightPointersInEachNode.java) |
| 118 | [Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) | Easy | [LC118PascalsTriangleCombinationFormula](src/Practice/Arrays/LC118PascalsTriangleCombinationFormula.java)<br>[LC118PascalsTrianglePreviousRow](src/Practice/Arrays/LC118PascalsTrianglePreviousRow.java) |
| 121 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Easy | [LC121BestTimeToBuyAndSellStock](src/Practice/Arrays/LC121BestTimeToBuyAndSellStock.java) |
| 124 | [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) | Hard | [LC124BinaryTreeMaximumPathSum](src/Practice/BinaryTree/LC124BinaryTreeMaximumPathSum.java) |
| 125 | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | Easy | [LC125ValidPalindrome](src/Leetcode/Ch08TwoPointersConverging/LC125ValidPalindrome.java) (not solved yet) |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | [LC128LongestConsecutiveSequenceV1](src/Practice/HashMap/LC128LongestConsecutiveSequenceV1.java)<br>[LC128LongestConsecutiveSequenceV2](src/Practice/HashMap/LC128LongestConsecutiveSequenceV2.java)<br>[LC128LongestConsecutiveSequence](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC128LongestConsecutiveSequence.java) |
| 131 | [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) | Medium | [LC131PalindromePartitioning](src/Practice/Backtracking/LC131PalindromePartitioning.java) |
| 133 | [Clone Graph](https://leetcode.com/problems/clone-graph/) | Medium | [LC133CloneGraph](src/Practice/Graph/LC133CloneGraph.java) |
| 135 | [Candy](https://leetcode.com/problems/candy/) | Hard | [LC135Candy](src/Practice/Greedy/LC135Candy.java) |
| 136 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | [LC136SingleNumber](src/Practice/BitManipulation/LC136SingleNumber.java)<br>[LC136SingleNumber](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC136SingleNumber.java) |
| 137 | [Single Number II](https://leetcode.com/problems/single-number-ii/) | Medium | [LC137SingleNumberII](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC137SingleNumberII.java) |
| 138 | [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) | Medium | [LC138CopyListWithRandomPointer](src/Practice/LinkedList/LC138CopyListWithRandomPointer.java) |
| 139 | [Word Break](https://leetcode.com/problems/word-break/) | Medium | [LC139WordBreak](src/Practice/DynamicProgramming/LC139WordBreak.java) |
| 141 | [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | Easy | [LC141LinkedListCycle](src/Practice/LinkedList/LC141LinkedListCycle.java) |
| 142 | [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) | Medium | [LC142LinkedListCycleII](src/Practice/LinkedList/LC142LinkedListCycleII.java) |
| 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | [LC146LRUCache](src/Practice/Design/LC146LRUCache.java)<br>[LC146LRUCache](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC146LRUCache.java) (not solved yet) |
| 148 | [Sort List](https://leetcode.com/problems/sort-list/) | Medium | [LC148SortList](src/Leetcode/Ch14MergeAndTheKWayMerge/LC148SortList.java) (not solved yet) |
| 150 | [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | [LC150EvaluateReversePolishNotation](src/Practice/Stack/LC150EvaluateReversePolishNotation.java)<br>[LC150EvaluateReversePolishNotation](src/Leetcode/Ch15StacksAndExpressionStructure/LC150EvaluateReversePolishNotation.java) (not solved yet) |
| 151 | [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) | Medium | [LC151ReverseWordsInAString](src/Practice/Strings/LC151ReverseWordsInAString.java) |
| 153 | [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | Medium | [LC153FindMinimumInRotatedSortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC153FindMinimumInRotatedSortedArray.java) (not solved yet) |
| 155 | [Min Stack](https://leetcode.com/problems/min-stack/) | Medium | [LC155MinStack](src/Practice/Design/LC155MinStack.java) |
| 160 | [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) | Easy | [LC160IntersectionOfTwoLinkedLists](src/Practice/LinkedList/LC160IntersectionOfTwoLinkedLists.java) |
| 162 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | Medium | [LC162FindPeakElement](src/Leetcode/Ch10BinarySearchOnASortedArray/LC162FindPeakElement.java) (not solved yet) |
| 164 | [Maximum Gap](https://leetcode.com/problems/maximum-gap/) | Medium | [LC164MaximumGap](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC164MaximumGap.java) (not solved yet) |
| 165 | [Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/) | Medium | [LC165CompareVersionNumbers](src/Practice/Strings/LC165CompareVersionNumbers.java) |
| 167 | [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | Medium | [LC167TwoSumIIInputArrayIsSorted](src/Leetcode/Ch08TwoPointersConverging/LC167TwoSumIIInputArrayIsSorted.java) (not solved yet) |
| 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | [LC169MajorityElementHashMap](src/Practice/Arrays/LC169MajorityElementHashMap.java)<br>[LC169MajorityElementBoyerMoore](src/Practice/Arrays/LC169MajorityElementBoyerMoore.java)<br>[LC169MajorityElement](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC169MajorityElement.java) (not solved yet) |
| 173 | [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) | Medium | [LC173BinarySearchTreeIterator](src/Practice/Design/LC173BinarySearchTreeIterator.java) |
| 179 | [Largest Number](https://leetcode.com/problems/largest-number/) | Medium | [LC179LargestNumber](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC179LargestNumber.java) (not solved yet) |
| 189 | [Rotate Array](https://leetcode.com/problems/rotate-array/) | Medium | [LC189RotateArray](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC189RotateArray.java) (not solved yet) |
| 191 | [Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/) | Easy | [LC191NumberOf1Bits](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC191NumberOf1Bits.java) |
| 199 | [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) | Medium | [LC199BinaryTreeRightSideView](src/Practice/BinaryTree/LC199BinaryTreeRightSideView.java) |
| 204 | [Count Primes](https://leetcode.com/problems/count-primes/) | Medium | [LC204CountPrimes](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC204CountPrimes.java) |
| 206 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | [LC206ReverseLinkedListIterative](src/Practice/LinkedList/LC206ReverseLinkedListIterative.java)<br>[LC206ReverseLinkedListRecursive](src/Practice/LinkedList/LC206ReverseLinkedListRecursive.java) |
| 207 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | Medium | [LC207CourseSchedule](src/Practice/Graph/LC207CourseSchedule.java) |
| 209 | [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) | Medium | [LC209MinimumSizeSubarraySum](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC209MinimumSizeSubarraySum.java) (not solved yet)<br>[LC209MinimumSizeSubarraySum](src/Leetcode/Ch09SlidingWindowChasing/LC209MinimumSizeSubarraySum.java) (not solved yet) |
| 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | [LC215KthLargestElementInAnArrayPriorityQueue](src/Practice/Heap/LC215KthLargestElementInAnArrayPriorityQueue.java)<br>[LC215KthLargestElementInAnArrayManualHeap](src/Practice/Heap/LC215KthLargestElementInAnArrayManualHeap.java)<br>[LC215KthLargestElementInAnArray](src/Leetcode/Ch13QuickselectAndPartition/LC215KthLargestElementInAnArray.java) (not solved yet)<br>[LC215KthLargestElementInAnArray](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC215KthLargestElementInAnArray.java) (not solved yet) |
| 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | [LC217ContainsDuplicate](src/Practice/HashMap/LC217ContainsDuplicate.java)<br>[LC217ContainsDuplicate](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC217ContainsDuplicate.java)<br>[LC217ContainsDuplicate](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC217ContainsDuplicate.java) (not solved yet) |
| 218 | [The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/) | Hard | [LC218TheSkylineProblem](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC218TheSkylineProblem.java) (not solved yet)<br>[LC218TheSkylineProblem](src/Leetcode/Ch20IntervalsAndSweepLine/LC218TheSkylineProblem.java) (not solved yet) |
| 219 | [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) | Easy | [LC219ContainsDuplicateII](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC219ContainsDuplicateII.java) |
| 220 | [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) | Hard | [LC220ContainsDuplicateIII](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC220ContainsDuplicateIII.java)<br>[LC220ContainsDuplicateIII](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC220ContainsDuplicateIII.java) (not solved yet) |
| 224 | [Basic Calculator](https://leetcode.com/problems/basic-calculator/) | Hard | [LC224BasicCalculator](src/Leetcode/Ch15StacksAndExpressionStructure/LC224BasicCalculator.java) (not solved yet) |
| 225 | [Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/) | Easy | [LC225ImplementStackUsingQueues](src/Practice/Design/LC225ImplementStackUsingQueues.java) |
| 227 | [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) | Medium | [LC227BasicCalculatorII](src/Leetcode/Ch15StacksAndExpressionStructure/LC227BasicCalculatorII.java) (not solved yet) |
| 229 | [Majority Element II](https://leetcode.com/problems/majority-element-ii/) | Medium | [LC229MajorityElementII](src/Practice/Arrays/LC229MajorityElementII.java) |
| 230 | [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | Medium | [LC230KthSmallestElementInABST](src/Practice/BST/LC230KthSmallestElementInABST.java) |
| 231 | [Power of Two](https://leetcode.com/problems/power-of-two/) | Easy | [LC231PowerOfTwo](src/Practice/Math/LC231PowerOfTwo.java)<br>[LC231PowerOfTwo](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC231PowerOfTwo.java) |
| 232 | [Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) | Easy | [LC232ImplementQueueUsingStacks](src/Practice/Design/LC232ImplementQueueUsingStacks.java) |
| 234 | [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) | Easy | [LC234PalindromeLinkedList](src/Practice/LinkedList/LC234PalindromeLinkedList.java) |
| 235 | [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | Medium | [LC235LowestCommonAncestorOfABinarySearchTree](src/Practice/BST/LC235LowestCommonAncestorOfABinarySearchTree.java) |
| 236 | [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | Medium | [LC236LowestCommonAncestorOfABinaryTree](src/Practice/BinaryTree/LC236LowestCommonAncestorOfABinaryTree.java) |
| 237 | [Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/) | Medium | [LC237DeleteNodeInALinkedList](src/Practice/LinkedList/LC237DeleteNodeInALinkedList.java) |
| 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | [LC239SlidingWindowMaximum](src/Practice/SlidingWindow/LC239SlidingWindowMaximum.java)<br>[LC239SlidingWindowMaximum](src/Leetcode/Ch17TheMonotonicDeque/LC239SlidingWindowMaximum.java) (not solved yet)<br>[LC239SlidingWindowMaximum](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC239SlidingWindowMaximum.java) (not solved yet) |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | [LC242ValidAnagramV1](src/Practice/HashMap/LC242ValidAnagramV1.java)<br>[LC242ValidAnagramV2](src/Practice/HashMap/LC242ValidAnagramV2.java)<br>[LC242ValidAnagramV3](src/Practice/HashMap/LC242ValidAnagramV3.java)<br>[LC242ValidAnagram](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC242ValidAnagram.java) |
| 253 | [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) | Medium | [LC253MeetingRoomsII](src/Leetcode/Ch20IntervalsAndSweepLine/LC253MeetingRoomsII.java) (not solved yet) |
| 260 | [Single Number III](https://leetcode.com/problems/single-number-iii/) | Medium | [LC260SingleNumberIII](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC260SingleNumberIII.java) |
| 268 | [Missing Number](https://leetcode.com/problems/missing-number/) | Easy | [LC268MissingNumber](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC268MissingNumber.java) (not solved yet)<br>[LC268MissingNumber](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC268MissingNumber.java) (not solved yet) |
| 274 | [H-Index](https://leetcode.com/problems/h-index/) | Medium | [LC274HIndex](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC274HIndex.java) (not solved yet) |
| 278 | [First Bad Version](https://leetcode.com/problems/first-bad-version/) | Easy | [LC278FirstBadVersion](src/Leetcode/Ch10BinarySearchOnASortedArray/LC278FirstBadVersion.java) (not solved yet) |
| 283 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | Easy | [LC283MoveZeroes](src/Practice/TwoPointers/LC283MoveZeroes.java)<br>[LC283MoveZeroes](src/Leetcode/Ch13QuickselectAndPartition/LC283MoveZeroes.java) (not solved yet) |
| 287 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | Medium | [LC287FindTheDuplicateNumber](src/Practice/TwoPointers/LC287FindTheDuplicateNumber.java)<br>[LC287FindTheDuplicateNumber](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC287FindTheDuplicateNumber.java) (not solved yet) |
| 289 | [Game of Life](https://leetcode.com/problems/game-of-life/) | Medium | [LC289GameOfLife](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC289GameOfLife.java) (not solved yet) |
| 295 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Hard | [LC295FindMedianFromDataStream](src/Practice/Heap/LC295FindMedianFromDataStream.java)<br>[LC295FindMedianFromDataStream](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC295FindMedianFromDataStream.java) (not solved yet) |
| 297 | [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | Hard | [LC297SerializeAndDeserializeBinaryTree](src/Practice/Design/LC297SerializeAndDeserializeBinaryTree.java) |
| 303 | [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) | Easy | [LC303RangeSumQueryImmutable](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC303RangeSumQueryImmutable.java) |
| 304 | [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) | Medium | [LC304RangeSumQuery2DImmutable](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC304RangeSumQuery2DImmutable.java) (not solved yet) |
| 315 | [Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/) | Hard | [LC315CountOfSmallerNumbersAfterSelf](src/Leetcode/Ch14MergeAndTheKWayMerge/LC315CountOfSmallerNumbersAfterSelf.java) (not solved yet) |
| 318 | [Maximum Product of Word Lengths](https://leetcode.com/problems/maximum-product-of-word-lengths/) | Medium | [LC318MaximumProductOfWordLengths](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC318MaximumProductOfWordLengths.java) |
| 322 | [Coin Change](https://leetcode.com/problems/coin-change/) | Medium | [LC322CoinChange](src/Practice/Greedy/LC322CoinChange.java) |
| 324 | [Wiggle Sort II](https://leetcode.com/problems/wiggle-sort-ii/) | Medium | [LC324WiggleSortII](src/Leetcode/Ch13QuickselectAndPartition/LC324WiggleSortII.java) (not solved yet) |
| 327 | [Count of Range Sum](https://leetcode.com/problems/count-of-range-sum/) | Hard | [LC327CountOfRangeSum](src/Leetcode/Ch14MergeAndTheKWayMerge/LC327CountOfRangeSum.java) (not solved yet) |
| 338 | [Counting Bits](https://leetcode.com/problems/counting-bits/) | Easy | [LC338CountingBits](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC338CountingBits.java) |
| 344 | [Reverse String](https://leetcode.com/problems/reverse-string/) | Easy | [LC344ReverseString](src/Leetcode/Ch08TwoPointersConverging/LC344ReverseString.java) (not solved yet) |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | [LC347TopKFrequentElements](src/Practice/Heap/LC347TopKFrequentElements.java)<br>[LC347TopKFrequentElements](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC347TopKFrequentElements.java)<br>[LC347TopKFrequentElements](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC347TopKFrequentElements.java) (not solved yet)<br>[LC347TopKFrequentElements](src/Leetcode/Ch13QuickselectAndPartition/LC347TopKFrequentElements.java) (not solved yet)<br>[LC347TopKFrequentElements](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC347TopKFrequentElements.java) (not solved yet) |
| 373 | [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) | Medium | [LC373FindKPairsWithSmallestSums](src/Practice/Heap/LC373FindKPairsWithSmallestSums.java)<br>[LC373FindKPairsWithSmallestSums](src/Leetcode/Ch14MergeAndTheKWayMerge/LC373FindKPairsWithSmallestSums.java) (not solved yet) |
| 378 | [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) | Medium | [LC378KthSmallestElementInASortedMatrix](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC378KthSmallestElementInASortedMatrix.java) (not solved yet)<br>[LC378KthSmallestElementInASortedMatrix](src/Leetcode/Ch14MergeAndTheKWayMerge/LC378KthSmallestElementInASortedMatrix.java) (not solved yet) |
| 380 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | Medium | [LC380InsertDeleteGetRandomO1](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC380InsertDeleteGetRandomO1.java) |
| 383 | [Ransom Note](https://leetcode.com/problems/ransom-note/) | Easy | [LC383RansomNote](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC383RansomNote.java) |
| 386 | [Lexicographical Numbers](https://leetcode.com/problems/lexicographical-numbers/) | Medium | [LC386LexicographicalNumbers](src/Practice/Recursion/LC386LexicographicalNumbers.java) |
| 387 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | [LC387FirstUniqueCharacterInAString](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC387FirstUniqueCharacterInAString.java) |
| 394 | [Decode String](https://leetcode.com/problems/decode-string/) | Medium | [LC394DecodeString](src/Leetcode/Ch15StacksAndExpressionStructure/LC394DecodeString.java) (not solved yet) |
| 402 | [Remove K Digits](https://leetcode.com/problems/remove-k-digits/) | Medium | [LC402RemoveKDigits](src/Leetcode/Ch16TheMonotonicStack/LC402RemoveKDigits.java) (not solved yet) |
| 406 | [Queue Reconstruction by Height](https://leetcode.com/problems/queue-reconstruction-by-height/) | Medium | [LC406QueueReconstructionByHeight](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC406QueueReconstructionByHeight.java) (not solved yet) |
| 409 | [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/) | Easy | [LC409LongestPalindrome](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC409LongestPalindrome.java) |
| 410 | [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) | Hard | [LC410SplitArrayLargestSum](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC410SplitArrayLargestSum.java) (not solved yet) |
| 412 | [Fizz Buzz](https://leetcode.com/problems/fizz-buzz/) | Easy | [LC412FizzBuzz](src/Leetcode/Random/LC412FizzBuzz.java) |
| 424 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | Medium | [LC424LongestRepeatingCharacterReplacement](src/Leetcode/Ch09SlidingWindowChasing/LC424LongestRepeatingCharacterReplacement.java) (not solved yet) |
| 436 | [Find Right Interval](https://leetcode.com/problems/find-right-interval/) | Medium | [LC436FindRightInterval](src/Practice/BinarySearch/LC436FindRightInterval.java) |
| 438 | [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | Medium | [LC438FindAllAnagramsInAString](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC438FindAllAnagramsInAString.java)<br>[LC438FindAllAnagramsInAString](src/Leetcode/Ch09SlidingWindowChasing/LC438FindAllAnagramsInAString.java) (not solved yet) |
| 440 | [K-th Smallest in Lexicographical Order](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/) | Hard | [LC440KThSmallestInLexicographicalOrder](src/Practice/Math/LC440KThSmallestInLexicographicalOrder.java) |
| 442 | [Find All Duplicates in an Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/) | Medium | [LC442FindAllDuplicatesInAnArray](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC442FindAllDuplicatesInAnArray.java) (not solved yet) |
| 448 | [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) | Easy | [LC448FindAllNumbersDisappearedInAnArray](src/Practice/Arrays/LC448FindAllNumbersDisappearedInAnArray.java)<br>[LC448FindAllNumbersDisappearedInAnArray](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC448FindAllNumbersDisappearedInAnArray.java) (not solved yet) |
| 451 | [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/) | Medium | [LC451SortCharactersByFrequency](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC451SortCharactersByFrequency.java)<br>[LC451SortCharactersByFrequency](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC451SortCharactersByFrequency.java) (not solved yet) |
| 452 | [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) | Medium | [LC452MinimumNumberOfArrowsToBurstBalloons](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC452MinimumNumberOfArrowsToBurstBalloons.java) (not solved yet) |
| 454 | [4Sum II](https://leetcode.com/problems/4sum-ii/) | Medium | [LC454FourSumII](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC454FourSumII.java) |
| 455 | [Assign Cookies](https://leetcode.com/problems/assign-cookies/) | Easy | [LC455AssignCookies](src/Practice/Greedy/LC455AssignCookies.java) |
| 460 | [LFU Cache](https://leetcode.com/problems/lfu-cache/) | Hard | [LC460LFUCache](src/Practice/Design/LC460LFUCache.java) |
| 462 | [Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/) | Medium | [LC462MinimumMovesToEqualArrayElementsII](src/Leetcode/Ch13QuickselectAndPartition/LC462MinimumMovesToEqualArrayElementsII.java) (not solved yet) |
| 480 | [Sliding Window Median](https://leetcode.com/problems/sliding-window-median/) | Hard | [LC480SlidingWindowMedian](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC480SlidingWindowMedian.java) (not solved yet) |
| 485 | [Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/) | Easy | [LC485MaxConsecutiveOnesV1](src/Practice/Arrays/LC485MaxConsecutiveOnesV1.java)<br>[LC485MaxConsecutiveOnesV2](src/Practice/Arrays/LC485MaxConsecutiveOnesV2.java) |
| 493 | [Reverse Pairs](https://leetcode.com/problems/reverse-pairs/) | Hard | [LC493ReversePairs](src/Practice/Sorting/LC493ReversePairs.java)<br>[LC493ReversePairs](src/Leetcode/Ch14MergeAndTheKWayMerge/LC493ReversePairs.java) (not solved yet) |
| 496 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | Easy | [LC496NextGreaterElementI](src/Practice/Stack/LC496NextGreaterElementI.java)<br>[LC496NextGreaterElementI](src/Leetcode/Ch16TheMonotonicStack/LC496NextGreaterElementI.java) (not solved yet) |
| 502 | [IPO](https://leetcode.com/problems/ipo/) | Hard | [LC502IPO](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC502IPO.java) (not solved yet)<br>[LC502IPO](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC502IPO.java) (not solved yet) |
| 503 | [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) | Medium | [LC503NextGreaterElementII](src/Leetcode/Ch16TheMonotonicStack/LC503NextGreaterElementII.java) (not solved yet) |
| 523 | [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) | Medium | [LC523ContinuousSubarraySum](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC523ContinuousSubarraySum.java)<br>[LC523ContinuousSubarraySum](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC523ContinuousSubarraySum.java) |
| 525 | [Contiguous Array](https://leetcode.com/problems/contiguous-array/) | Medium | [LC525ContiguousArray](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC525ContiguousArray.java) |
| 540 | [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) | Medium | [LC540SingleElementInASortedArray](src/Practice/BinarySearch/LC540SingleElementInASortedArray.java)<br>[LC540SingleElementInASortedArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC540SingleElementInASortedArray.java) (not solved yet) |
| 543 | [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) | Easy | [LC543DiameterOfBinaryTree](src/Practice/BinaryTree/LC543DiameterOfBinaryTree.java) |
| 547 | [Number of Provinces](https://leetcode.com/problems/number-of-provinces/) | Medium | [LC547NumberOfProvinces](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC547NumberOfProvinces.java) (not solved yet) |
| 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | [LC560SubarraySumEqualsKV1](src/Practice/PrefixSum/LC560SubarraySumEqualsKV1.java)<br>[LC560SubarraySumEqualsKV2](src/Practice/PrefixSum/LC560SubarraySumEqualsKV2.java)<br>[LC560SubarraySumEqualsK](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC560SubarraySumEqualsK.java) (not solved yet)<br>[LC560SubarraySumEqualsK](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC560SubarraySumEqualsK.java) (not solved yet) |
| 567 | [Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Medium | [LC567PermutationInString](src/Practice/SlidingWindow/LC567PermutationInString.java)<br>[LC567PermutationInString](src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC567PermutationInString.java) |
| 594 | [Longest Harmonious Subsequence](https://leetcode.com/problems/longest-harmonious-subsequence/) | Easy | [LC594LongestHarmoniousSubsequence](src/Practice/HashMap/LC594LongestHarmoniousSubsequence.java) |
| 605 | [Can Place Flowers](https://leetcode.com/problems/can-place-flowers/) | Easy | [LC605CanPlaceFlowers](src/Practice/Greedy/LC605CanPlaceFlowers.java) |
| 611 | [Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number/) | Medium | [LC611ValidTriangleNumber](src/Leetcode/Ch08TwoPointersConverging/LC611ValidTriangleNumber.java) (not solved yet) |
| 636 | [Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) | Medium | [LC636ExclusiveTimeOfFunctionsV1](src/Practice/Stack/LC636ExclusiveTimeOfFunctionsV1.java)<br>[LC636ExclusiveTimeOfFunctionsV2](src/Practice/Stack/LC636ExclusiveTimeOfFunctionsV2.java) |
| 643 | [Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/) | Easy | [LC643MaximumAverageSubarrayI](src/Leetcode/Ch09SlidingWindowChasing/LC643MaximumAverageSubarrayI.java) (not solved yet) |
| 645 | [Set Mismatch](https://leetcode.com/problems/set-mismatch/) | Easy | [LC645SetMismatch](src/Practice/Arrays/LC645SetMismatch.java)<br>[LC645SetMismatch](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC645SetMismatch.java) (not solved yet)<br>[LC2965FindMissingAndRepeatedValues](src/Practice/Math/LC2965FindMissingAndRepeatedValues.java) (second problem in this file) |
| 653 | [Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/) | Easy | [LC653TwoSumIVInputIsABST](src/Practice/BST/LC653TwoSumIVInputIsABST.java) |
| 662 | [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) | Medium | [LC662MaximumWidthOfBinaryTree](src/Practice/BinaryTree/LC662MaximumWidthOfBinaryTree.java) |
| 680 | [Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/) | Easy | [LC680ValidPalindromeII](src/Leetcode/Ch08TwoPointersConverging/LC680ValidPalindromeII.java) (not solved yet) |
| 686 | [Repeated String Match](https://leetcode.com/problems/repeated-string-match/) | Medium | [LC686RepeatedStringMatch](src/Practice/Strings/LC686RepeatedStringMatch.java) |
| 692 | [Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/) | Medium | [LC692TopKFrequentWords](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC692TopKFrequentWords.java) (not solved yet)<br>[LC692TopKFrequentWords](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC692TopKFrequentWords.java) (not solved yet) |
| 700 | [Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/) | Easy | [LC700SearchInABinarySearchTree](src/Practice/BST/LC700SearchInABinarySearchTree.java) |
| 703 | [Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) | Easy | [LC703KthLargestElementInAStream](src/Leetcode/Ch13QuickselectAndPartition/LC703KthLargestElementInAStream.java) (not solved yet)<br>[LC703KthLargestElementInAStream](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC703KthLargestElementInAStream.java) (not solved yet)<br>[LC703KthLargestElementInAStream](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC703KthLargestElementInAStream.java) (not solved yet) |
| 704 | [Binary Search](https://leetcode.com/problems/binary-search/) | Easy | [LC704BinarySearch](src/Practice/BinarySearch/LC704BinarySearch.java)<br>[LC704BinarySearch](src/Leetcode/Ch10BinarySearchOnASortedArray/LC704BinarySearch.java) (not solved yet) |
| 705 | [Design HashSet](https://leetcode.com/problems/design-hashset/) | Easy | [LC705DesignHashSet](src/Practice/Design/LC705DesignHashSet.java) |
| 713 | [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) | Medium | [LC713SubarrayProductLessThanK](src/Leetcode/Ch09SlidingWindowChasing/LC713SubarrayProductLessThanK.java) (not solved yet) |
| 719 | [Find K-th Smallest Pair Distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance/) | Hard | [LC719FindKThSmallestPairDistance](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC719FindKThSmallestPairDistance.java) (not solved yet) |
| 724 | [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) | Easy | [LC724FindPivotIndex](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC724FindPivotIndex.java) |
| 726 | [Number of Atoms](https://leetcode.com/problems/number-of-atoms/) | Hard | [LC726NumberOfAtoms](src/Leetcode/Ch15StacksAndExpressionStructure/LC726NumberOfAtoms.java) (not solved yet) |
| 732 | [My Calendar III](https://leetcode.com/problems/my-calendar-iii/) | Hard | [LC732MyCalendarIII](src/Leetcode/Ch20IntervalsAndSweepLine/LC732MyCalendarIII.java) (not solved yet) |
| 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | [LC739DailyTemperaturesRightToLeft](src/Practice/Stack/LC739DailyTemperaturesRightToLeft.java)<br>[LC739DailyTemperaturesLeftToRight](src/Practice/Stack/LC739DailyTemperaturesLeftToRight.java)<br>[LC739DailyTemperatures](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC739DailyTemperatures.java) (not solved yet)<br>[LC739DailyTemperatures](src/Leetcode/Ch16TheMonotonicStack/LC739DailyTemperatures.java) (not solved yet) |
| 765 | [Couples Holding Hands](https://leetcode.com/problems/couples-holding-hands/) | Hard | [LC765CouplesHoldingHands](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC765CouplesHoldingHands.java) (not solved yet) |
| 836 | [Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/) | Easy | [RectangleOverLap](src/Leetcode/Daily/RectangleOverLap.java) |
| 850 | [Rectangle Area II](https://leetcode.com/problems/rectangle-area-ii/) | Hard | [LC850RectangleAreaII](src/Leetcode/Ch20IntervalsAndSweepLine/LC850RectangleAreaII.java) (not solved yet) |
| 852 | [Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) | Medium | [LC852PeakIndexInAMountainArray](src/Leetcode/Ch10BinarySearchOnASortedArray/LC852PeakIndexInAMountainArray.java) (not solved yet) |
| 856 | [Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/) | Medium | [LC856ScoreOfParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC856ScoreOfParentheses.java) (not solved yet) |
| 862 | [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) | Hard | [LC862ShortestSubarrayWithSumAtLeastK](src/Leetcode/Ch17TheMonotonicDeque/LC862ShortestSubarrayWithSumAtLeastK.java) (not solved yet) |
| 871 | [Minimum Number of Refueling Stops](https://leetcode.com/problems/minimum-number-of-refueling-stops/) | Hard | [LC871MinimumNumberOfRefuelingStops](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC871MinimumNumberOfRefuelingStops.java) (not solved yet) |
| 875 | [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) | Medium | [LC875KokoEatingBananas](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC875KokoEatingBananas.java) (not solved yet) |
| 876 | [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) | Easy | [LC876MiddleOfTheLinkedList](src/Practice/LinkedList/LC876MiddleOfTheLinkedList.java) |
| 881 | [Boats to Save People](https://leetcode.com/problems/boats-to-save-people/) | Medium | [LC881BoatsToSavePeople](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC881BoatsToSavePeople.java) (not solved yet) |
| 901 | [Online Stock Span](https://leetcode.com/problems/online-stock-span/) | Medium | [LC901OnlineStockSpan](src/Practice/Stack/LC901OnlineStockSpan.java)<br>[LC901OnlineStockSpan](src/Leetcode/Ch16TheMonotonicStack/LC901OnlineStockSpan.java) (not solved yet) |
| 904 | [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | Medium | [LC904FruitIntoBaskets](src/Practice/SlidingWindow/LC904FruitIntoBaskets.java) |
| 905 | [Sort Array By Parity](https://leetcode.com/problems/sort-array-by-parity/) | Easy | [LC905SortArrayByParity](src/Leetcode/Ch13QuickselectAndPartition/LC905SortArrayByParity.java) (not solved yet) |
| 907 | [Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) | Medium | [LC907SumOfSubarrayMinimums](src/Leetcode/Ch16TheMonotonicStack/LC907SumOfSubarrayMinimums.java) (not solved yet) |
| 912 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | Medium | [LC912SortAnArrayV1](src/Practice/Sorting/LC912SortAnArrayV1.java)<br>[LC912SortAnArrayV2](src/Practice/Sorting/LC912SortAnArrayV2.java)<br>[LC912SortAnArray](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC912SortAnArray.java) (not solved yet)<br>[LC912SortAnArray](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC912SortAnArray.java) (not solved yet)<br>[LC912SortAnArray](src/Leetcode/Ch14MergeAndTheKWayMerge/LC912SortAnArray.java) (not solved yet) |
| 918 | [Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/) | Medium | [LC918MaximumSumCircularSubarray](src/Leetcode/Ch17TheMonotonicDeque/LC918MaximumSumCircularSubarray.java) (not solved yet) |
| 921 | [Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) | Medium | [LC921MinimumAddToMakeParenthesesValid](src/Leetcode/Ch15StacksAndExpressionStructure/LC921MinimumAddToMakeParenthesesValid.java) (not solved yet) |
| 930 | [Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/) | Medium | [LC930BinarySubarraysWithSum](src/Leetcode/Ch09SlidingWindowChasing/LC930BinarySubarraysWithSum.java) (not solved yet) |
| 941 | [Valid Mountain Array](https://leetcode.com/problems/valid-mountain-array/) | Easy | [LC941ValidMountainArray](src/Practice/Arrays/LC941ValidMountainArray.java) |
| 973 | [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) | Medium | [LC973KClosestPointsToOrigin](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC973KClosestPointsToOrigin.java) (not solved yet)<br>[LC973KClosestPointsToOrigin](src/Leetcode/Ch13QuickselectAndPartition/LC973KClosestPointsToOrigin.java) (not solved yet)<br>[LC973KClosestPointsToOrigin](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC973KClosestPointsToOrigin.java) (not solved yet) |
| 974 | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | Medium | [LC974SubarraySumsDivisibleByK](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC974SubarraySumsDivisibleByK.java)<br>[LC974SubarraySumsDivisibleByK](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC974SubarraySumsDivisibleByK.java) |
| 977 | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | Easy | [LC977SquaresOfASortedArray](src/Leetcode/Ch08TwoPointersConverging/LC977SquaresOfASortedArray.java) (not solved yet)<br>[LC977SquaresOfASortedArray](src/Leetcode/Ch14MergeAndTheKWayMerge/LC977SquaresOfASortedArray.java) (not solved yet) |
| 986 | [Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/) | Medium | [LC986IntervalListIntersections](src/Leetcode/Ch08TwoPointersConverging/LC986IntervalListIntersections.java) (not solved yet)<br>[LC986IntervalListIntersections](src/Leetcode/Ch14MergeAndTheKWayMerge/LC986IntervalListIntersections.java) (not solved yet) |
| 987 | [Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) | Hard | [LC987VerticalOrderTraversalOfABinaryTree](src/Practice/BinaryTree/LC987VerticalOrderTraversalOfABinaryTree.java) |
| 992 | [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) | Hard | [LC992SubarraysWithKDifferentIntegers](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC992SubarraysWithKDifferentIntegers.java)<br>[LC992SubarraysWithKDifferentIntegers](src/Leetcode/Ch09SlidingWindowChasing/LC992SubarraysWithKDifferentIntegers.java) (not solved yet) |
| 994 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | Medium | [LC994RottingOranges](src/Practice/Graph/LC994RottingOranges.java) |
| 1004 | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) | Medium | [LC1004MaxConsecutiveOnesIII](src/Leetcode/Ch09SlidingWindowChasing/LC1004MaxConsecutiveOnesIII.java) (not solved yet) |
| 1008 | [Construct Binary Search Tree from Preorder Traversal](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/) | Medium | [LC1008ConstructBinarySearchTreeFromPreorderTraversal](src/Practice/BST/LC1008ConstructBinarySearchTreeFromPreorderTraversal.java) |
| 1011 | [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) | Medium | [LC1011CapacityToShipPackagesWithinDDays](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1011CapacityToShipPackagesWithinDDays.java) (not solved yet) |
| 1030 | [Matrix Cells in Distance Order](https://leetcode.com/problems/matrix-cells-in-distance-order/) | Easy | [LC1030MatrixCellsInDistanceOrder](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1030MatrixCellsInDistanceOrder.java) (not solved yet) |
| 1044 | [Longest Duplicate Substring](https://leetcode.com/problems/longest-duplicate-substring/) | Hard | [LC1044LongestDuplicateSubstring](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1044LongestDuplicateSubstring.java) (not solved yet) |
| 1046 | [Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) | Easy | [LC1046LastStoneWeight](src/Practice/Heap/LC1046LastStoneWeight.java)<br>[LC1046LastStoneWeight](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC1046LastStoneWeight.java) (not solved yet) |
| 1047 | [Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) | Easy | [LC1047RemoveAllAdjacentDuplicatesInString](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC1047RemoveAllAdjacentDuplicatesInString.java)<br>[LC1047RemoveAllAdjacentDuplicatesInString](src/Leetcode/Ch15StacksAndExpressionStructure/LC1047RemoveAllAdjacentDuplicatesInString.java) (not solved yet) |
| 1051 | [Height Checker](https://leetcode.com/problems/height-checker/) | Easy | [LC1051HeightChecker](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1051HeightChecker.java) (not solved yet) |
| 1061 | [Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/) | Medium | [LC1061LexicographicallySmallestEquivalentString](src/Practice/Graph/LC1061LexicographicallySmallestEquivalentString.java) |
| 1074 | [Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/) | Hard | [LC1074NumberOfSubmatricesThatSumToTarget](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1074NumberOfSubmatricesThatSumToTarget.java) |
| 1081 | [Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) | Medium | [LC1081SmallestSubsequenceOfDistinctCharacters](src/Practice/Stack/LC1081SmallestSubsequenceOfDistinctCharacters.java) |
| 1094 | [Car Pooling](https://leetcode.com/problems/car-pooling/) | Medium | [LC1094CarPooling](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1094CarPooling.java)<br>[LC1094CarPooling](src/Leetcode/Ch20IntervalsAndSweepLine/LC1094CarPooling.java) (not solved yet) |
| 1109 | [Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/) | Medium | [LC1109CorporateFlightBookings](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1109CorporateFlightBookings.java)<br>[LC1109CorporateFlightBookings](src/Leetcode/Ch20IntervalsAndSweepLine/LC1109CorporateFlightBookings.java) (not solved yet) |
| 1122 | [Relative Sort Array](https://leetcode.com/problems/relative-sort-array/) | Easy | [LC1122RelativeSortArray](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1122RelativeSortArray.java) (not solved yet) |
| 1167 | [Minimum Cost to Connect Sticks](https://leetcode.com/problems/minimum-cost-to-connect-sticks/) | Medium | [LC1167MinimumCostToConnectSticks](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC1167MinimumCostToConnectSticks.java) (not solved yet) |
| 1178 | [Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/) | Hard | [LC1178NumberOfValidWordsForEachPuzzle](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC1178NumberOfValidWordsForEachPuzzle.java) |
| 1235 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | Hard | [LC1235MaximumProfitInJobScheduling](src/Practice/DynamicProgramming/LC1235MaximumProfitInJobScheduling.java) |
| 1249 | [Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/) | Medium | [LC1249MinimumRemoveToMakeValidParentheses](src/Leetcode/Ch15StacksAndExpressionStructure/LC1249MinimumRemoveToMakeValidParentheses.java) (not solved yet) |
| 1288 | [Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/) | Medium | [LC1288RemoveCoveredIntervals](src/Leetcode/Ch20IntervalsAndSweepLine/LC1288RemoveCoveredIntervals.java) (not solved yet) |
| 1295 | [Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/) | Easy | [LC1295FindNumbersWithEvenNumberOfDigits](src/Practice/Math/LC1295FindNumbersWithEvenNumberOfDigits.java) |
| 1298 | [Maximum Candies You Can Get from Boxes](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/) | Hard | [LC1298MaximumCandiesYouCanGetFromBoxes](src/Practice/Graph/LC1298MaximumCandiesYouCanGetFromBoxes.java) |
| 1344 | [Angle Between Hands of a Clock](https://leetcode.com/problems/angle-between-hands-of-a-clock/) | Medium | [LC1344AngleBetweenHandsOfAClock](src/Practice/Math/LC1344AngleBetweenHandsOfAClock.java) |
| 1346 | [Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/) | Easy | [LC1346CheckIfNAndItsDoubleExist](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC1346CheckIfNAndItsDoubleExist.java) |
| 1365 | [How Many Numbers Are Smaller Than the Current Number](https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/) | Easy | [LC1365HowManyNumbersAreSmallerThanTheCurrentNumber](src/Practice/Sorting/LC1365HowManyNumbersAreSmallerThanTheCurrentNumber.java) |
| 1370 | [Increasing Decreasing String](https://leetcode.com/problems/increasing-decreasing-string/) | Easy | [LC1370IncreasingDecreasingString](src/Leetcode/Ch07NonComparisonSortsCountingBucketRadix/LC1370IncreasingDecreasingString.java) (not solved yet) |
| 1371 | [Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/) | Medium | [LC1371FindTheLongestSubstringContainingVowelsInEvenCounts](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC1371FindTheLongestSubstringContainingVowelsInEvenCounts.java) |
| 1373 | [Maximum Sum BST in Binary Tree](https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/) | Hard | [LC1373MaximumSumBSTInBinaryTree](src/Practice/BST/LC1373MaximumSumBSTInBinaryTree.java) |
| 1394 | [Find Lucky Integer in an Array](https://leetcode.com/problems/find-lucky-integer-in-an-array/) | Easy | [LC1394FindLuckyIntegerInAnArray](src/Practice/HashMap/LC1394FindLuckyIntegerInAnArray.java) |
| 1411 | [Number of Ways to Paint N × 3 Grid](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/) | Hard | [LC5LongestPalindromicSubstringV1](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV1.java) (second problem in this file) |
| 1425 | [Constrained Subsequence Sum](https://leetcode.com/problems/constrained-subsequence-sum/) | Hard | [LC1425ConstrainedSubsequenceSum](src/Leetcode/Ch17TheMonotonicDeque/LC1425ConstrainedSubsequenceSum.java) (not solved yet) |
| 1432 | [Max Difference You Can Get From Changing an Integer](https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/) | Medium | [LC1432MaxDifferenceYouCanGetFromChangingAnInteger](src/Practice/Greedy/LC1432MaxDifferenceYouCanGetFromChangingAnInteger.java) |
| 1438 | [Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) | Medium | [LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit](src/Leetcode/Ch09SlidingWindowChasing/LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.java) (not solved yet)<br>[LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit](src/Leetcode/Ch17TheMonotonicDeque/LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.java) (not solved yet) |
| 1441 | [Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations/) | Medium | [LC1441BuildAnArrayWithStackOperationsV1](src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV1.java)<br>[LC1441BuildAnArrayWithStackOperationsV2](src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV2.java)<br>[LC1441BuildAnArrayWithStackOperations](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC1441BuildAnArrayWithStackOperations.java) |
| 1442 | [Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/) | Medium | [LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR.java) |
| 1456 | [Maximum Number of Vowels in a Substring of Given Length](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/) | Medium | [LC1456MaximumNumberOfVowelsInASubstringOfGivenLength](src/Leetcode/Ch09SlidingWindowChasing/LC1456MaximumNumberOfVowelsInASubstringOfGivenLength.java) (not solved yet) |
| 1470 | [Shuffle the Array](https://leetcode.com/problems/shuffle-the-array/) | Easy | [LC1470ShuffleTheArray](src/Practice/Arrays/LC1470ShuffleTheArray.java) |
| 1475 | [Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) | Easy | [LC1475FinalPricesWithASpecialDiscountInAShop](src/Practice/Arrays/LC1475FinalPricesWithASpecialDiscountInAShop.java)<br>[LC1475FinalPricesWithASpecialDiscountInAShop](src/Leetcode/Ch16TheMonotonicStack/LC1475FinalPricesWithASpecialDiscountInAShop.java) (not solved yet) |
| 1482 | [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/) | Medium | [LC1482MinimumNumberOfDaysToMakeMBouquets](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1482MinimumNumberOfDaysToMakeMBouquets.java) (not solved yet) |
| 1498 | [Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/) | Medium | [LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition](src/Practice/TwoPointers/LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition.java) |
| 1499 | [Max Value of Equation](https://leetcode.com/problems/max-value-of-equation/) | Hard | [LC1499MaxValueOfEquation](src/Leetcode/Ch17TheMonotonicDeque/LC1499MaxValueOfEquation.java) (not solved yet) |
| 1513 | [Number of Substrings With Only 1s](https://leetcode.com/problems/number-of-substrings-with-only-1s/) | Medium | [LC1513NumberOfSubstringsWithOnly1s](src/Practice/Math/LC1513NumberOfSubstringsWithOnly1s.java) |
| 1526 | [Minimum Number of Increments on Subarrays to Form a Target Array](https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/) | Hard | [LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray](src/Practice/Greedy/LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray.java) |
| 1539 | [Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/) | Easy | [LC1539KthMissingPositiveNumber](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC1539KthMissingPositiveNumber.java) (not solved yet) |
| 1550 | [Three Consecutive Odds](https://leetcode.com/problems/three-consecutive-odds/) | Easy | [LC1550ThreeConsecutiveOdds](src/Practice/Arrays/LC1550ThreeConsecutiveOdds.java) |
| 1552 | [Magnetic Force Between Two Balls](https://leetcode.com/problems/magnetic-force-between-two-balls/) | Medium | [LC1552MagneticForceBetweenTwoBalls](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1552MagneticForceBetweenTwoBalls.java) (not solved yet) |
| 1578 | [Minimum Time to Make Rope Colorful](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/) | Medium | [LC1578MinimumTimeToMakeRopeColorful](src/Practice/Greedy/LC1578MinimumTimeToMakeRopeColorful.java) |
| 1631 | [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) | Medium | [LC1631PathWithMinimumEffort](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1631PathWithMinimumEffort.java) (not solved yet) |
| 1642 | [Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/) | Medium | [LC1642FurthestBuildingYouCanReach](src/Leetcode/Ch18HeapsAndTheKSelectionFamily/LC1642FurthestBuildingYouCanReach.java) (not solved yet) |
| 1649 | [Create Sorted Array through Instructions](https://leetcode.com/problems/create-sorted-array-through-instructions/) | Hard | [LC1649CreateSortedArrayThroughInstructions](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1649CreateSortedArrayThroughInstructions.java) (not solved yet) |
| 1657 | [Determine if Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close/) | Medium | [LC1657DetermineIfTwoStringsAreClose](src/Practice/HashMap/LC1657DetermineIfTwoStringsAreClose.java) |
| 1658 | [Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/) | Medium | [LC1658MinimumOperationstoReduceXtoZero](src/Leetcode/Daily/LC1658MinimumOperationstoReduceXtoZero.java) |
| 1670 | [Design Front Middle Back Queue](https://leetcode.com/problems/design-front-middle-back-queue/) | Medium | [LC1670DesignFrontMiddleBackQueue](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1670DesignFrontMiddleBackQueue.java) (not solved yet) |
| 1687 | [Delivering Boxes from Storage to Ports](https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/) | Hard | [LC1687DeliveringBoxesFromStorageToPorts](src/Leetcode/Ch17TheMonotonicDeque/LC1687DeliveringBoxesFromStorageToPorts.java) (not solved yet) |
| 1696 | [Jump Game VI](https://leetcode.com/problems/jump-game-vi/) | Medium | [LC1696JumpGameVI](src/Leetcode/Ch17TheMonotonicDeque/LC1696JumpGameVI.java) (not solved yet) |
| 1700 | [Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/) | Easy | [LC1700NumberOfStudentsUnableToEatLunch](src/Practice/Queue/LC1700NumberOfStudentsUnableToEatLunch.java) |
| 1710 | [Maximum Units on a Truck](https://leetcode.com/problems/maximum-units-on-a-truck/) | Easy | [LC1710MaximumUnitsOnATruck](src/Practice/Greedy/LC1710MaximumUnitsOnATruck.java) |
| 1717 | [Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings/) | Medium | [LC1717MaximumScoreFromRemovingSubstrings](src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC1717MaximumScoreFromRemovingSubstrings.java) |
| 1732 | [Find the Highest Altitude](https://leetcode.com/problems/find-the-highest-altitude/) | Easy | [LC1732FindTheHighestAltitude](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1732FindTheHighestAltitude.java) |
| 1801 | [Number of Orders in the Backlog](https://leetcode.com/problems/number-of-orders-in-the-backlog/) | Medium | [LC1801NumberOfOrdersInTheBacklog](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1801NumberOfOrdersInTheBacklog.java) (not solved yet) |
| 1825 | [Finding MK Average](https://leetcode.com/problems/finding-mk-average/) | Hard | [LC1825FindingMKAverage](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC1825FindingMKAverage.java) (not solved yet) |
| 1854 | [Maximum Population Year](https://leetcode.com/problems/maximum-population-year/) | Easy | [LC1854MaximumPopulationYear](src/Leetcode/Ch20IntervalsAndSweepLine/LC1854MaximumPopulationYear.java) (not solved yet) |
| 1856 | [Maximum Subarray Min-Product](https://leetcode.com/problems/maximum-subarray-min-product/) | Medium | [LC1856MaximumSubarrayMinProduct](src/Leetcode/Ch16TheMonotonicStack/LC1856MaximumSubarrayMinProduct.java) (not solved yet) |
| 1863 | [Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/) | Easy | [LC1863SumOfAllSubsetXORTotals](src/Leetcode/Ch05BitManipulationAsASetEncoding/LC1863SumOfAllSubsetXORTotals.java) |
| 1865 | [Finding Pairs With a Certain Sum](https://leetcode.com/problems/finding-pairs-with-a-certain-sum/) | Medium | [LC1865FindingPairsWithACertainSum](src/Practice/Design/LC1865FindingPairsWithACertainSum.java) |
| 1870 | [Minimum Speed to Arrive on Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time/) | Medium | [LC1870MinimumSpeedToArriveOnTime](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC1870MinimumSpeedToArriveOnTime.java) (not solved yet) |
| 1920 | [Build Array from Permutation](https://leetcode.com/problems/build-array-from-permutation/) | Easy | [LC1920BuildArrayFromPermutation](src/Leetcode/Ch12CyclicSortAndIndexAsAddress/LC1920BuildArrayFromPermutation.java) (not solved yet) |
| 1929 | [Concatenation of Array](https://leetcode.com/problems/concatenation-of-array/) | Easy | [LC1929ConcatenationOfArray](src/Practice/Arrays/LC1929ConcatenationOfArray.java) |
| 1944 | [Number of Visible People in a Queue](https://leetcode.com/problems/number-of-visible-people-in-a-queue/) | Hard | [LC1944NumberOfVisiblePeopleInAQueue](src/Leetcode/Ch16TheMonotonicStack/LC1944NumberOfVisiblePeopleInAQueue.java) (not solved yet) |
| 1979 | [Find Greatest Common Divisor of Array](https://leetcode.com/problems/find-greatest-common-divisor-of-array/) | Easy | [LC1979FindGreatestCommonDivisorOfArray](src/Practice/Math/LC1979FindGreatestCommonDivisorOfArray.java) |
| 1984 | [Minimum Difference Between Highest and Lowest of K Scores](https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/) | Easy | [LC1984MinimumDifferenceBetweenHighestAndLowestOfKScores](src/Practice/SlidingWindow/LC1984MinimumDifferenceBetweenHighestAndLowestOfKScores.java) |
| 1985 | [Find the Kth Largest Integer in the Array](https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/) | Medium | [LC1985FindTheKthLargestIntegerInTheArray](src/Leetcode/Ch13QuickselectAndPartition/LC1985FindTheKthLargestIntegerInTheArray.java) (not solved yet) |
| 1991 | [Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) | Easy | [LC1991FindTheMiddleIndexInArray](src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1991FindTheMiddleIndexInArray.java) |
| 2006 | [Count Number of Pairs With Absolute Difference K](https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/) | Easy | [LC2006CountNumberOfPairsWithAbsoluteDifferenceK](src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC2006CountNumberOfPairsWithAbsoluteDifferenceK.java) |
| 2016 | [Maximum Difference Between Increasing Elements](https://leetcode.com/problems/maximum-difference-between-increasing-elements/) | Easy | [LC2016MaximumDifferenceBetweenIncreasingElements](src/Practice/Arrays/LC2016MaximumDifferenceBetweenIncreasingElements.java) |
| 2058 | [Find the Minimum and Maximum Number of Nodes Between Critical Points](https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/) | Medium | [LC2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints](src/Practice/LinkedList/LC2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints.java) |
| 2073 | [Time Needed to Buy Tickets](https://leetcode.com/problems/time-needed-to-buy-tickets/) | Easy | [LC2073TimeNeededToBuyTickets](src/Practice/Queue/LC2073TimeNeededToBuyTickets.java) |
| 2094 | [Finding 3-Digit Even Numbers](https://leetcode.com/problems/finding-3-digit-even-numbers/) | Easy | [LC2094Find3DigitEvenNumbers](src/Leetcode/Daily/LC2094Find3DigitEvenNumbers.java) |
| 2099 | [Find Subsequence of Length K With the Largest Sum](https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/) | Easy | [LC2099FindSubsequenceOfLengthKWithTheLargestSum](src/Practice/Sorting/LC2099FindSubsequenceOfLengthKWithTheLargestSum.java) |
| 2102 | [Sequentially Ordinal Rank Tracker](https://leetcode.com/problems/sequentially-ordinal-rank-tracker/) | Hard | [LC2102SequentiallyOrdinalRankTracker](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC2102SequentiallyOrdinalRankTracker.java) (not solved yet) |
| 2104 | [Sum of Subarray Ranges](https://leetcode.com/problems/sum-of-subarray-ranges/) | Medium | [LC2104SumOfSubarrayRanges](src/Leetcode/Ch16TheMonotonicStack/LC2104SumOfSubarrayRanges.java) (not solved yet) |
| 2125 | [Number of Laser Beams in a Bank](https://leetcode.com/problems/number-of-laser-beams-in-a-bank/) | Medium | [LC2125NumberOfLaserBeamsInABank](src/Practice/Matrix/LC2125NumberOfLaserBeamsInABank.java) |
| 2138 | [Divide a String Into Groups of Size k](https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/) | Easy | [LC2138DivideAStringIntoGroupsOfSizeK](src/Practice/Strings/LC2138DivideAStringIntoGroupsOfSizeK.java) |
| 2154 | [Keep Multiplying Found Values by Two](https://leetcode.com/problems/keep-multiplying-found-values-by-two/) | Easy | [LC2154KeepMultiplyingFoundValuesByTwo](src/Practice/Arrays/LC2154KeepMultiplyingFoundValuesByTwo.java) |
| 2161 | [Partition Array According to Given Pivot](https://leetcode.com/problems/partition-array-according-to-given-pivot/) | Medium | [LC2161PartitionArrayAccordingToGivenPivot](src/Leetcode/Ch13QuickselectAndPartition/LC2161PartitionArrayAccordingToGivenPivot.java) (not solved yet) |
| 2163 | [Minimum Difference in Sums After Removal of Elements](https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/) | Hard | [LC2163MinimumDifferenceInSumsAfterRemovalOfElements](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC2163MinimumDifferenceInSumsAfterRemovalOfElements.java) (not solved yet) |
| 2169 | [Count Operations to Obtain Zero](https://leetcode.com/problems/count-operations-to-obtain-zero/) | Easy | [LC2169CountOperationsToObtainZero](src/Practice/Math/LC2169CountOperationsToObtainZero.java) |
| 2200 | [Find All K-Distant Indices in an Array](https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/) | Easy | [LC2200FindAllKDistantIndicesInAnArray](src/Practice/Arrays/LC2200FindAllKDistantIndicesInAnArray.java) |
| 2226 | [Maximum Candies Allocated to K Children](https://leetcode.com/problems/maximum-candies-allocated-to-k-children/) | Medium | [LC2226MaximumCandiesAllocatedToKChildren](src/Leetcode/Ch11BinarySearchOnTheAnswer/LC2226MaximumCandiesAllocatedToKChildren.java) (not solved yet) |
| 2251 | [Number of Flowers in Full Bloom](https://leetcode.com/problems/number-of-flowers-in-full-bloom/) | Hard | [LC2251NumberOfFlowersInFullBloom](src/Leetcode/Ch20IntervalsAndSweepLine/LC2251NumberOfFlowersInFullBloom.java) (not solved yet) |
| 2257 | [Count Unguarded Cells in the Grid](https://leetcode.com/problems/count-unguarded-cells-in-the-grid/) | Medium | [LC2257CountUnguardedCellsInTheGrid](src/Practice/Matrix/LC2257CountUnguardedCellsInTheGrid.java) |
| 2264 | [Largest 3-Same-Digit Number in String](https://leetcode.com/problems/largest-3-same-digit-number-in-string/) | Easy | [LC2264Largest3SameDigitNumberInString](src/Practice/Strings/LC2264Largest3SameDigitNumberInString.java) |
| 2294 | [Partition Array Such That Maximum Difference Is K](https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/) | Medium | [LC2294PartitionArraySuchThatMaximumDifferenceIsK](src/Practice/Greedy/LC2294PartitionArraySuchThatMaximumDifferenceIsK.java) |
| 2300 | [Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/) | Medium | [LC2300SuccessfulPairsOfSpellsAndPotions](src/Practice/BinarySearch/LC2300SuccessfulPairsOfSpellsAndPotions.java) |
| 2398 | [Maximum Number of Robots Within Budget](https://leetcode.com/problems/maximum-number-of-robots-within-budget/) | Hard | [LC2398MaximumNumberOfRobotsWithinBudget](src/Leetcode/Ch17TheMonotonicDeque/LC2398MaximumNumberOfRobotsWithinBudget.java) (not solved yet) |
| 2418 | [Sort the People](https://leetcode.com/problems/sort-the-people/) | Easy | [LC2418SortThePeople](src/Leetcode/Ch06SortingAsAPreprocessingStep/LC2418SortThePeople.java) (not solved yet) |
| 2434 | [Using a Robot to Print the Lexicographically Smallest String](https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/) | Medium | [LC2434UsingARobotToPrintTheLexicographicallySmallestString](src/Practice/Stack/LC2434UsingARobotToPrintTheLexicographicallySmallestString.java) |
| 2438 | [Range Product Queries of Powers](https://leetcode.com/problems/range-product-queries-of-powers/) | Medium | [LC2438RangeProductQueriesOfPowers](src/Practice/BitManipulation/LC2438RangeProductQueriesOfPowers.java) |
| 2528 | [Maximize the Minimum Powered City](https://leetcode.com/problems/maximize-the-minimum-powered-city/) | Hard | [LC2528MaximizeTheMinimumPoweredCity](src/Practice/BinarySearch/LC2528MaximizeTheMinimumPoweredCity.java) |
| 2566 | [Maximum Difference by Remapping a Digit](https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/) | Easy | [LC2566MaximumDifferenceByRemappingADigit](src/Practice/Greedy/LC2566MaximumDifferenceByRemappingADigit.java) |
| 2588 | [Count the Number of Beautiful Subarrays](https://leetcode.com/problems/count-the-number-of-beautiful-subarrays/) | Medium | [LC2588CountTheNumberOfBeautifulSubarrays](src/Practice/PrefixSum/LC2588CountTheNumberOfBeautifulSubarrays.java) |
| 2615 | [Sum of Distances](https://leetcode.com/problems/sum-of-distances/) | Medium | [LC2615SumOfDistances](src/Practice/HashMap/LC2615SumOfDistances.java) |
| 2616 | [Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/) | Medium | [LC2616MinimizeTheMaximumDifferenceOfPairs](src/Practice/BinarySearch/LC2616MinimizeTheMaximumDifferenceOfPairs.java) |
| 2762 | [Continuous Subarrays](https://leetcode.com/problems/continuous-subarrays/) | Medium | [LC2762ContinuousSubarrays](src/Leetcode/Ch17TheMonotonicDeque/LC2762ContinuousSubarrays.java) (not solved yet) |
| 2848 | [Points That Intersect With Cars](https://leetcode.com/problems/points-that-intersect-with-cars/) | Easy | [LC2848PointsThatIntersectWithCars](src/Leetcode/Ch20IntervalsAndSweepLine/LC2848PointsThatIntersectWithCars.java) (not solved yet) |
| 2929 | [Distribute Candies Among Children II](https://leetcode.com/problems/distribute-candies-among-children-ii/) | Medium | [LC2929DistributeCandiesAmongChildrenII](src/Practice/Math/LC2929DistributeCandiesAmongChildrenII.java) |
| 2965 | [Find Missing and Repeated Values](https://leetcode.com/problems/find-missing-and-repeated-values/) | Easy | [LC2965FindMissingAndRepeatedValues](src/Practice/Math/LC2965FindMissingAndRepeatedValues.java) |
| 2966 | [Divide Array Into Arrays With Max Difference](https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/) | Medium | [LC2966DivideArrayIntoArraysWithMaxDifference](src/Practice/Sorting/LC2966DivideArrayIntoArraysWithMaxDifference.java) |
| 3013 | [Divide an Array Into Subarrays With Minimum Cost II](https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/) | Hard | [LC3013DivideAnArrayIntoSubarraysWithMinimumCostII](src/Leetcode/Ch19TwoHeapsAndTheRunningMedian/LC3013DivideAnArrayIntoSubarraysWithMinimumCostII.java) (not solved yet) |
| 3069 | [Distribute Elements Into Two Arrays I](https://leetcode.com/problems/distribute-elements-into-two-arrays-i/) | Easy | [LC3069DistributeElementsIntoTwoArraysI](src/Practice/Arrays/LC3069DistributeElementsIntoTwoArraysI.java) |
| 3085 | [Minimum Deletions to Make String K-Special](https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/) | Medium | [LC3085MinimumDeletionsToMakeStringKSpecial](src/Practice/Greedy/LC3085MinimumDeletionsToMakeStringKSpecial.java) |
| 3170 | [Lexicographically Minimum String After Removing Stars](https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/) | Medium | [LC3170LexicographicallyMinimumStringAfterRemovingStars](src/Practice/Greedy/LC3170LexicographicallyMinimumStringAfterRemovingStars.java) |
| 3217 | [Delete Nodes From Linked List Present in Array](https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/) | Medium | [LC3217DeleteNodesFromLinkedListPresentInArray](src/Practice/LinkedList/LC3217DeleteNodesFromLinkedListPresentInArray.java) |
| 3289 | [The Two Sneaky Numbers of Digitville](https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/) | Easy | [LC3289TheTwoSneakyNumbersOfDigitville](src/Practice/Math/LC3289TheTwoSneakyNumbersOfDigitville.java) |
| 3318 | [Find X-Sum of All K-Long Subarrays I](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/) | Easy | [LC3318FindXSumOfAllKLongSubarraysI](src/Practice/Heap/LC3318FindXSumOfAllKLongSubarraysI.java) |
| 3321 | [Find X-Sum of All K-Long Subarrays II](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/) | Hard | [LC3321FindXSumOfAllKLongSubarraysII](src/Practice/Heap/LC3321FindXSumOfAllKLongSubarraysII.java) |
| 3330 | [Find the Original Typed String I](https://leetcode.com/problems/find-the-original-typed-string-i/) | Easy | [LC3330FindTheOriginalTypedStringI](src/Practice/Strings/LC3330FindTheOriginalTypedStringI.java) |
| 3354 | [Make Array Elements Equal to Zero](https://leetcode.com/problems/make-array-elements-equal-to-zero/) | Easy | [LC3354MakeArrayElementsEqualToZero](src/Practice/Arrays/LC3354MakeArrayElementsEqualToZero.java) |
| 3370 | [Smallest Number With All Set Bits](https://leetcode.com/problems/smallest-number-with-all-set-bits/) | Easy | [LC3370SmallestNumberWithAllSetBits](src/Practice/BitManipulation/LC3370SmallestNumberWithAllSetBits.java) |
| 3403 | [Find the Lexicographically Largest String From the Box I](https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/) | Medium | [LC3403FindTheLexicographicallyLargestStringFromTheBoxI](src/Practice/Strings/LC3403FindTheLexicographicallyLargestStringFromTheBoxI.java) |
| 3423 | [Maximum Difference Between Adjacent Elements in a Circular Array](https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/) | Easy | [LC3423MaximumDifferenceBetweenAdjacentElementsInACircularArray](src/Practice/Arrays/LC3423MaximumDifferenceBetweenAdjacentElementsInACircularArray.java) |
| 3442 | [Maximum Difference Between Even and Odd Frequency I](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/) | Easy | [LC3442MaximumDifferenceBetweenEvenAndOddFrequencyI](src/Practice/HashMap/LC3442MaximumDifferenceBetweenEvenAndOddFrequencyI.java) |
| 3443 | [Maximum Manhattan Distance After K Changes](https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/) | Medium | [LC3443MaximumManhattanDistanceAfterKChanges](src/Practice/Greedy/LC3443MaximumManhattanDistanceAfterKChanges.java) |
| 3471 | [Find the Largest Almost Missing Integer](https://leetcode.com/problems/find-the-largest-almost-missing-integer/) | Easy | [LC3471FindTheLargestAlmostMissingInteger](src/Practice/HashMap/LC3471FindTheLargestAlmostMissingInteger.java) |
| 3483 | [Unique 3-Digit Even Numbers](https://leetcode.com/problems/unique-3-digit-even-numbers/) | Easy | [LC3483Unique3DigitsEvenNumbers](src/Leetcode/Daily/LC3483Unique3DigitsEvenNumbers.java) |
| 3498 | [Reverse Degree of a String](https://leetcode.com/problems/reverse-degree-of-a-string/) | Easy | [LC3498ReverseDegreeString](src/Leetcode/Daily/LC3498ReverseDegreeString.java) |
| 3507 | [Minimum Pair Removal to Sort Array I](https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/) | Easy | [LC3507MinimumPairRemovalToSortArrayI](src/Practice/Contests/Misc/LC3507MinimumPairRemovalToSortArrayI.java) |
| 3508 | [Implement Router](https://leetcode.com/problems/implement-router/) | Medium | [LC3508ImplementRouter](src/Practice/Contests/Misc/LC3508ImplementRouter.java) |
| 3522 | [Calculate Score After Performing Instructions](https://leetcode.com/problems/calculate-score-after-performing-instructions/) | Medium | [LC3522CalculateScoreAfterPerformingInstructions](src/Practice/Contests/Apr20/LC3522CalculateScoreAfterPerformingInstructions.java) |
| 3523 | [Make Array Non-decreasing](https://leetcode.com/problems/make-array-non-decreasing/) | Medium | [LC3523MakeArrayNonDecreasing](src/Practice/Contests/Apr20/LC3523MakeArrayNonDecreasing.java) |
| 3527 | [Find the Most Common Response](https://leetcode.com/problems/find-the-most-common-response/) | Medium | [LC3527FindTheMostCommonResponse](src/Practice/Contests/Contest155/LC3527FindTheMostCommonResponse.java) |
| 3529 | [Count Cells in Overlapping Horizontal and Vertical Substrings](https://leetcode.com/problems/count-cells-in-overlapping-horizontal-and-vertical-substrings/) | Medium | [LC3529CountCellsInOverlappingHorizontalAndVerticalSubstrings](src/Practice/Contests/Contest155/LC3529CountCellsInOverlappingHorizontalAndVerticalSubstrings.java) |
| 3536 | [Maximum Product of Two Digits](https://leetcode.com/problems/maximum-product-of-two-digits/) | Easy | [LC3536MaximumProductOfTwoDigits](src/Practice/Math/LC3536MaximumProductOfTwoDigits.java) |
| 3550 | [Smallest Index With Digit Sum Equal to Index](https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/) | Easy | [LC3550SmallestIndex](src/Leetcode/Daily/LC3550SmallestIndex.java) |
| 3572 | [Maximize Y-Sum by Picking a Triplet of Distinct X-Values](https://leetcode.com/problems/maximize-ysum-by-picking-a-triplet-of-distinct-xvalues/) | Medium | [LC3572MaximizeYSumByPickingATripletOfDistinctXValues](src/Practice/Contests/Contest158/LC3572MaximizeYSumByPickingATripletOfDistinctXValues.java) |
| 3573 | [Best Time to Buy and Sell Stock V](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/) | Medium | [LC3573BestTimeToBuyAndSellStockV](src/Practice/Contests/Contest158/LC3573BestTimeToBuyAndSellStockV.java) |
| 3574 | [Maximize Subarray GCD Score](https://leetcode.com/problems/maximize-subarray-gcd-score/) | Hard | [LC3574MaximizeSubarrayGCDScore](src/Practice/Contests/Contest158/LC3574MaximizeSubarrayGCDScore.java) |
| 3577 | [Count the Number of Computer Unlocking Permutations](https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations/) | Medium | [LC3577CountTheNumberOfComputerUnlockingPermutations](src/Practice/Math/LC3577CountTheNumberOfComputerUnlockingPermutations.java) |
| 3583 | [Count Special Triplets](https://leetcode.com/problems/count-special-triplets/) | Medium | [LC3583CountSpecialTriplets](src/Practice/HashMap/LC3583CountSpecialTriplets.java) |
| 3658 | [GCD of Odd and Even Sums](https://leetcode.com/problems/gcd-of-odd-and-even-sums/) | Easy | [LC3658GCDofOddandEvenSums](src/Leetcode/ArithMetic/LC3658GCDofOddandEvenSums.java) |
| 3718 | [Smallest Missing Multiple of K](https://leetcode.com/problems/smallest-missing-multiple-of-k/) | Easy | [LC3718missingMultiple](src/Leetcode/Daily/LC3718missingMultiple.java) |
| 3736 | [Minimum Moves to Equal Array Elements III](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/) | Easy | [LC3736MinimumMovesToEqualArrayElementsIII](src/Practice/Contests/Contest169/LC3736MinimumMovesToEqualArrayElementsIII.java) |
| 3737 | [Count Subarrays With Majority Element I](https://leetcode.com/problems/count-subarrays-with-majority-element-i/) | Medium | [LC3737CountSubarraysWithMajorityElementI](src/Practice/Contests/Contest169/LC3737CountSubarraysWithMajorityElementI.java) |
| 3738 | [Longest Non-Decreasing Subarray After Replacing at Most One Element](https://leetcode.com/problems/longest-non-decreasing-subarray-after-replacing-at-most-one-element/) | Medium | [LC3738LongestNonDecreasingSubarrayAfterReplacingAtMostOneElement](src/Practice/Contests/Contest169/LC3738LongestNonDecreasingSubarrayAfterReplacingAtMostOneElement.java) |
| 3794 | [Reverse String Prefix](https://leetcode.com/problems/reverse-string-prefix/) | Easy | [LC3794ReverseStringPrefix](src/Practice/TwoPointers/LC3794ReverseStringPrefix.java) |
| 3795 | [Minimum Subarray Length With Distinct Sum At Least K](https://leetcode.com/problems/minimum-subarray-length-with-distinct-sum-at-least-k/) | Medium | [LC3795MinimumSubarrayLengthWithDistinctSumAtLeastK](src/Practice/SlidingWindow/LC3795MinimumSubarrayLengthWithDistinctSumAtLeastK.java) |
| 3796 | [Find Maximum Value in a Constrained Sequence](https://leetcode.com/problems/find-maximum-value-in-a-constrained-sequence/) | Medium | [LC3796FindMaximumValueInAConstrainedSequence](src/Practice/Greedy/LC3796FindMaximumValueInAConstrainedSequence.java) |
| 3870 | [Count Commas in Range](https://leetcode.com/problems/count-commas-in-range/) | Easy | [LC3870CountCommasInRange](src/Leetcode/Daily/LC3870CountCommasInRange.java) |
| 3876 | [Construct Uniform Parity Array II](https://leetcode.com/problems/construct-uniform-parity-array-ii/) | Medium | [LC3876ConstructUniformParityArrayII](src/Leetcode/Daily/LC3876ConstructUniformParityArrayII.java) |
| 4034 | [Minimum Bishop Moves to Reach Target](https://leetcode.com/problems/minimum-bishop-moves-to-reach-target/) | Medium | [LCQ1MinBishopMoves](src/Leetcode/Daily/LCQ1MinBishopMoves.java) |
| 4048 | [Count Values With Equally Spaced Occurrences I](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-i/) | Easy | [LC191CountSpecialIntegers1](src/Leetcode/Contest191/LC191CountSpecialIntegers1.java) |
| 4049 | [Count Values With Equally Spaced Occurrences II](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-ii/) | Medium | [LC191CountSpecialIntegers2](src/Leetcode/Contest191/LC191CountSpecialIntegers2.java) |
| 4050 | [Minimum Days to Score Exactly N Points](https://leetcode.com/problems/minimum-days-to-score-exactly-n-points/) | Medium | [LC191minDays](src/Leetcode/Contest191/LC191minDays.java) |

</details>

# Java Problem Solving

My LeetCode and DSA practice in Java.

**267 LeetCode problems** (104 Easy, 133 Medium, 30 Hard) + **16 problems from GeeksforGeeks and interviews**, in 324 solution files.

## How to read a solution

Every file in `src/Practice/` opens with a header that teaches the problem on its own:

- **the link** to the problem and its difficulty
- **PROBLEM** in plain words, **EXAMPLES** and **CONSTRAINTS**
- **INTUITION** - why the idea works - and **APPROACH**, step by step, using the names in the code
- **DRY RUN** on a small input, **COMPLEXITY** with the reason, and **GOTCHAS**
- **STATUS** when the code has a known bug or is unfinished (the code itself is left as I wrote it)
- **SEE ALSO** when the same problem is solved in another file

Every file has a `main()` with a sample input, so you can run it directly from IntelliJ.

## Layout

| Folder | What is in it |
|---|---|
| `src/Practice/<Topic>/` | 258 solutions grouped by technique |
| `src/Leetcode/<Set>/` | 66 solutions from my curated LeetCode sets |

File names: `LC<number><Title>.java` for LeetCode, `GFG<Title>.java` for GeeksforGeeks, `Interview<Title>.java` for interview questions. When a problem is solved more than once, a suffix tells the versions apart: `LC1TwoSumHashMap.java`, `LC1TwoSumThreeApproaches.java`, or `V1` / `V2` when both use the same idea.

Status marks: :warning: known bug - :construction: unfinished (details in the file's STATUS section).

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
| [Curated LeetCode sets](#curated-leetcode-sets-srcleetcode) | 66 | | my original sets, unchanged |

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

## Curated LeetCode sets (`src/Leetcode`)

These are kept exactly as I organised them.

### ArithMetic

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 204 | [Count Primes](https://leetcode.com/problems/count-primes/) | Medium | [LC204CountPrimes](src/Leetcode/ArithMetic/LC204CountPrimes.java) |
| 3658 | [GCD of Odd and Even Sums](https://leetcode.com/problems/gcd-of-odd-and-even-sums/) | Easy | [LC3658GCDofOddandEvenSums](src/Leetcode/ArithMetic/LC3658GCDofOddandEvenSums.java) |

### BitManipulationAsASetEncoding5

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 78 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | [LC78Subsets](src/Leetcode/BitManipulationAsASetEncoding5/LC78Subsets.java) |
| 136 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | [LC136SingleNumber](src/Leetcode/BitManipulationAsASetEncoding5/LC136SingleNumber.java) |
| 137 | [Single Number II](https://leetcode.com/problems/single-number-ii/) | Medium | [LC137SingleNumber](src/Leetcode/BitManipulationAsASetEncoding5/LC137SingleNumber.java) |
| 191 | [Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/) | Easy | [LC191NumberFo1Bits](src/Leetcode/BitManipulationAsASetEncoding5/LC191NumberFo1Bits.java) |
| 231 | [Power of Two](https://leetcode.com/problems/power-of-two/) | Easy | [LC231IsPowerOfTwo](src/Leetcode/BitManipulationAsASetEncoding5/LC231IsPowerOfTwo.java) |
| 260 | [Single Number III](https://leetcode.com/problems/single-number-iii/) | Medium | [LC260SingleNumberIII](src/Leetcode/BitManipulationAsASetEncoding5/LC260SingleNumberIII.java) |
| 318 | [Maximum Product of Word Lengths](https://leetcode.com/problems/maximum-product-of-word-lengths/) | Medium | [LC318MaximumProductofWordLengths](src/Leetcode/BitManipulationAsASetEncoding5/LC318MaximumProductofWordLengths.java) |
| 338 | [Counting Bits](https://leetcode.com/problems/counting-bits/) | Easy | [LC338CountingBits](src/Leetcode/BitManipulationAsASetEncoding5/LC338CountingBits.java) |
| 1178 | [Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/) | Hard | [LC1178NumberOfValidWordsforEachPuzzle](src/Leetcode/BitManipulationAsASetEncoding5/LC1178NumberOfValidWordsforEachPuzzle.java) |
| 1371 | [Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/) | Medium | [LC1371FindTheLongestSubstring](src/Leetcode/BitManipulationAsASetEncoding5/LC1371FindTheLongestSubstring.java) |
| 1863 | [Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/) | Easy | [LC1863SumOfAllSubsetXORTotals](src/Leetcode/BitManipulationAsASetEncoding5/LC1863SumOfAllSubsetXORTotals.java) |

### Contest191

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 4048 | [Count Values With Equally Spaced Occurrences I](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-i/) | Easy | [LC191CountSpecialIntegers1](src/Leetcode/Contest191/LC191CountSpecialIntegers1.java) |
| 4049 | [Count Values With Equally Spaced Occurrences II](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-ii/) | Medium | [LC191CountSpecialIntegers2](src/Leetcode/Contest191/LC191CountSpecialIntegers2.java) |
| 4050 | [Minimum Days to Score Exactly N Points](https://leetcode.com/problems/minimum-days-to-score-exactly-n-points/) | Medium | [LC191minDays](src/Leetcode/Contest191/LC191minDays.java) |

### CountingStack

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | [LC3lengthOfLongestSubstring](src/Leetcode/CountingStack/LC3lengthOfLongestSubstring.java) |
| 380 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | Medium | [LC380RandomizedSet](src/Leetcode/CountingStack/LC380RandomizedSet.java) |
| 992 | [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) | Hard | [LC992SubarraysWithKDistinct](src/Leetcode/CountingStack/LC992SubarraysWithKDistinct.java) |
| 1047 | [Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) | Easy | [LC1047RemoveDuplicates](src/Leetcode/CountingStack/LC1047RemoveDuplicates.java) |
| 1441 | [Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations/) | Medium | [LC1441BuildArrayWithStack](src/Leetcode/CountingStack/LC1441BuildArrayWithStack.java) |
| 1717 | [Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings/) | Medium | [LC1717maximumGain](src/Leetcode/CountingStack/LC1717maximumGain.java) |

### Daily

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 836 | [Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/) | Easy | [RectangleOverLap](src/Leetcode/Daily/RectangleOverLap.java) |
| 1658 | [Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/) | Medium | [LC1658MinimumOperationstoReduceXtoZero](src/Leetcode/Daily/LC1658MinimumOperationstoReduceXtoZero.java) |
| 2094 | [Finding 3-Digit Even Numbers](https://leetcode.com/problems/finding-3-digit-even-numbers/) | Easy | [LC2094Find3DigitEvenNumbers](src/Leetcode/Daily/LC2094Find3DigitEvenNumbers.java) |
| 3483 | [Unique 3-Digit Even Numbers](https://leetcode.com/problems/unique-3-digit-even-numbers/) | Easy | [LC3483Unique3DigitsEvenNumbers](src/Leetcode/Daily/LC3483Unique3DigitsEvenNumbers.java) |
| 3498 | [Reverse Degree of a String](https://leetcode.com/problems/reverse-degree-of-a-string/) | Easy | [LC3498ReverseDegreeString](src/Leetcode/Daily/LC3498ReverseDegreeString.java) |
| 3550 | [Smallest Index With Digit Sum Equal to Index](https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/) | Easy | [LC3550SmallestIndex](src/Leetcode/Daily/LC3550SmallestIndex.java) |
| 3718 | [Smallest Missing Multiple of K](https://leetcode.com/problems/smallest-missing-multiple-of-k/) | Easy | [LC3718missingMultiple](src/Leetcode/Daily/LC3718missingMultiple.java) |
| 3870 | [Count Commas in Range](https://leetcode.com/problems/count-commas-in-range/) | Easy | [LC3870CountCommasInRange](src/Leetcode/Daily/LC3870CountCommasInRange.java) |
| 3876 | [Construct Uniform Parity Array II](https://leetcode.com/problems/construct-uniform-parity-array-ii/) | Medium | [LC3876ConstructUniformParityArrayII](src/Leetcode/Daily/LC3876ConstructUniformParityArrayII.java) |
| 4034 | [Minimum Bishop Moves to Reach Target](https://leetcode.com/problems/minimum-bishop-moves-to-reach-target/) | Medium | [LCQ1MinBishopMoves](src/Leetcode/Daily/LCQ1MinBishopMoves.java) |

### FrequencyAndMultisetResoning4

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | [LC3LengthOfLongestSubstring](src/Leetcode/FrequencyAndMultisetResoning4/LC3LengthOfLongestSubstring.java) |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | [LC49GroupAnagrams](src/Leetcode/FrequencyAndMultisetResoning4/LC49GroupAnagrams.java) |
| 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | [LC76MinimumWindowSubstring](src/Leetcode/FrequencyAndMultisetResoning4/LC76MinimumWindowSubstring.java) |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | [LC242ValidAnagram](src/Leetcode/FrequencyAndMultisetResoning4/LC242ValidAnagram.java) |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | [LC347TopKFrequent](src/Leetcode/FrequencyAndMultisetResoning4/LC347TopKFrequent.java) |
| 383 | [Ransom Note](https://leetcode.com/problems/ransom-note/) | Easy | [LC383RansomNote](src/Leetcode/FrequencyAndMultisetResoning4/LC383RansomNote.java) |
| 387 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | [LC387FirstUniqueCharacters](src/Leetcode/FrequencyAndMultisetResoning4/LC387FirstUniqueCharacters.java) |
| 409 | [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/) | Easy | [LC409LongestPalindrome](src/Leetcode/FrequencyAndMultisetResoning4/LC409LongestPalindrome.java) |
| 438 | [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | Medium | [LC438findAnagrams](src/Leetcode/FrequencyAndMultisetResoning4/LC438findAnagrams.java) |
| 451 | [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/) | Medium | [LC451FrequencySort](src/Leetcode/FrequencyAndMultisetResoning4/LC451FrequencySort.java) |
| 567 | [Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Medium | [LC567PermutationinString](src/Leetcode/FrequencyAndMultisetResoning4/LC567PermutationinString.java) |

### HashMapAsLookup3

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | [LC1TwoSum](src/Leetcode/HashMapAsLookup3/LC1TwoSum.java) |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | [LC49GroupAnagrams](src/Leetcode/HashMapAsLookup3/LC49GroupAnagrams.java) |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | [LC128LongestConsecutive](src/Leetcode/HashMapAsLookup3/LC128LongestConsecutive.java) |
| 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | [LC217ContainsDuplicate](src/Leetcode/HashMapAsLookup3/LC217ContainsDuplicate.java) |
| 219 | [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) | Easy | [Lc219ContainsDuplicate2](src/Leetcode/HashMapAsLookup3/Lc219ContainsDuplicate2.java) |
| 220 | [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) | Hard | [LC220ContainsDuplicate3](src/Leetcode/HashMapAsLookup3/LC220ContainsDuplicate3.java) |
| 454 | [4Sum II](https://leetcode.com/problems/4sum-ii/) | Medium | [LC454FourSumCount](src/Leetcode/HashMapAsLookup3/LC454FourSumCount.java) |
| 523 | [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) | Medium | [LC523ContinuousSubArraySum](src/Leetcode/HashMapAsLookup3/LC523ContinuousSubArraySum.java) |
| 974 | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | Medium | [LC974SubarraysDivByK](src/Leetcode/HashMapAsLookup3/LC974SubarraysDivByK.java) |
| 1346 | [Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/) | Easy | [LC1346CheckIfExist](src/Leetcode/HashMapAsLookup3/LC1346CheckIfExist.java) |
| 2006 | [Count Number of Pairs With Absolute Difference K](https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/) | Easy | [LC2006CountKDifference](src/Leetcode/HashMapAsLookup3/LC2006CountKDifference.java) |

### PrefixSum2

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 303 | [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) | Easy | [LC303NumArray](src/Leetcode/PrefixSum2/LC303NumArray.java) |
| 523 | [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) | Medium | [LC523ContinuousSubarraySum](src/Leetcode/PrefixSum2/LC523ContinuousSubarraySum.java) |
| 525 | [Contiguous Array](https://leetcode.com/problems/contiguous-array/) | Medium | [LC525ContiguousArray](src/Leetcode/PrefixSum2/LC525ContiguousArray.java) |
| 724 | [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) | Easy | [LC724FindPivotIndex](src/Leetcode/PrefixSum2/LC724FindPivotIndex.java) |
| 974 | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | Medium | [LC974SubArraysSumDivByK](src/Leetcode/PrefixSum2/LC974SubArraysSumDivByK.java) |
| 1074 | [Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/) | Hard | [LC1074NumSubmatrixSumTarget](src/Leetcode/PrefixSum2/LC1074NumSubmatrixSumTarget.java) |
| 1094 | [Car Pooling](https://leetcode.com/problems/car-pooling/) | Medium | [LC1094CarPooling](src/Leetcode/PrefixSum2/LC1094CarPooling.java) |
| 1109 | [Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/) | Medium | [LC1109corpFlightBookings](src/Leetcode/PrefixSum2/LC1109corpFlightBookings.java) |
| 1442 | [Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/) | Medium | [LC1442CountTriplets](src/Leetcode/PrefixSum2/LC1442CountTriplets.java) |
| 1732 | [Find the Highest Altitude](https://leetcode.com/problems/find-the-highest-altitude/) | Easy | [LC1732largestAltitude](src/Leetcode/PrefixSum2/LC1732largestAltitude.java) |
| 1991 | [Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) | Easy | [LC1991findMiddleIndex](src/Leetcode/PrefixSum2/LC1991findMiddleIndex.java) |

### Random

| # | Problem | Difficulty | Solution |
|---|---|---|---|
| 412 | [Fizz Buzz](https://leetcode.com/problems/fizz-buzz/) | Easy | [LC412FizzBuzz](src/Leetcode/Random/LC412FizzBuzz.java) |

## Every LeetCode problem by number

<details>
<summary>Show all problems sorted by number, with every file that solves each one</summary>

| # | Problem | Difficulty | Solution files |
|---|---|---|---|
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | [LC1TwoSumComplementMap](src/Practice/HashMap/LC1TwoSumComplementMap.java)<br>[LC1TwoSumThreeApproaches](src/Practice/HashMap/LC1TwoSumThreeApproaches.java)<br>[LC1TwoSumHashMap](src/Practice/HashMap/LC1TwoSumHashMap.java)<br>[LC1TwoSum](src/Leetcode/HashMapAsLookup3/LC1TwoSum.java) |
| 2 | [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) | Medium | [LC2AddTwoNumbers](src/Practice/LinkedList/LC2AddTwoNumbers.java) |
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | [LC3LongestSubstringWithoutRepeatingCharactersQueueWindow](src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersQueueWindow.java)<br>[LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches](src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches.java)<br>[LC3lengthOfLongestSubstring](src/Leetcode/CountingStack/LC3lengthOfLongestSubstring.java)<br>[LC3LengthOfLongestSubstring](src/Leetcode/FrequencyAndMultisetResoning4/LC3LengthOfLongestSubstring.java) |
| 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | [LC4MedianOfTwoSortedArrays](src/Practice/BinarySearch/LC4MedianOfTwoSortedArrays.java) |
| 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | [LC5LongestPalindromicSubstringV1](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV1.java)<br>[LC5LongestPalindromicSubstringV2](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV2.java) |
| 7 | [Reverse Integer](https://leetcode.com/problems/reverse-integer/) | Medium | [LC7ReverseInteger](src/Practice/Math/LC7ReverseInteger.java) |
| 8 | [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/) | Medium | [LC8StringToIntegerAtoi](src/Practice/Strings/LC8StringToIntegerAtoi.java) |
| 9 | [Palindrome Number](https://leetcode.com/problems/palindrome-number/) | Easy | [LC9PalindromeNumber](src/Practice/Math/LC9PalindromeNumber.java) |
| 13 | [Roman to Integer](https://leetcode.com/problems/roman-to-integer/) | Easy | [LC13RomanToInteger](src/Practice/Strings/LC13RomanToInteger.java) |
| 14 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | Easy | [LC14LongestCommonPrefixThreeApproaches](src/Practice/Strings/LC14LongestCommonPrefixThreeApproaches.java)<br>[LC14LongestCommonPrefixVerticalScan](src/Practice/Strings/LC14LongestCommonPrefixVerticalScan.java) |
| 15 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | [LC15ThreeSum](src/Practice/TwoPointers/LC15ThreeSum.java) |
| 18 | [4Sum](https://leetcode.com/problems/4sum/) | Medium | [LC18FourSum](src/Practice/TwoPointers/LC18FourSum.java) |
| 19 | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | Medium | [LC19RemoveNthNodeFromEndOfListV1](src/Practice/LinkedList/LC19RemoveNthNodeFromEndOfListV1.java)<br>[LC19RemoveNthNodeFromEndOfListV2](src/Practice/LinkedList/LC19RemoveNthNodeFromEndOfListV2.java) |
| 20 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | [LC20ValidParentheses](src/Practice/Stack/LC20ValidParentheses.java) |
| 21 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | [LC21MergeTwoSortedLists](src/Practice/LinkedList/LC21MergeTwoSortedLists.java)<br>[GFGMergeTwoSortedArraysWithoutExtraSpace](src/Practice/TwoPointers/GFGMergeTwoSortedArraysWithoutExtraSpace.java) (second problem in this file) |
| 22 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | [LC22GenerateParentheses](src/Practice/Backtracking/LC22GenerateParentheses.java) |
| 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | [LC23MergeKSortedLists](src/Practice/Heap/LC23MergeKSortedLists.java) |
| 25 | [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) | Hard | [LC25ReverseNodesInKGroup](src/Practice/LinkedList/LC25ReverseNodesInKGroup.java) |
| 26 | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Easy | [LC26RemoveDuplicatesFromSortedArray](src/Practice/TwoPointers/LC26RemoveDuplicatesFromSortedArray.java) |
| 27 | [Remove Element](https://leetcode.com/problems/remove-element/) | Easy | [LC27RemoveElementThreeApproaches](src/Practice/TwoPointers/LC27RemoveElementThreeApproaches.java)<br>[LC27RemoveElementTwoEndsSwap](src/Practice/TwoPointers/LC27RemoveElementTwoEndsSwap.java) |
| 31 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | Medium | [LC31NextPermutationOptimal](src/Practice/Arrays/LC31NextPermutationOptimal.java)<br>[LC31NextPermutationBruteForce](src/Practice/Arrays/LC31NextPermutationBruteForce.java) |
| 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | [LC33SearchInRotatedSortedArray](src/Practice/BinarySearch/LC33SearchInRotatedSortedArray.java) |
| 36 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | [LC36ValidSudoku](src/Practice/Matrix/LC36ValidSudoku.java) |
| 37 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) | Hard | [LC37SudokuSolver](src/Practice/Backtracking/LC37SudokuSolver.java) |
| 38 | [Count and Say](https://leetcode.com/problems/count-and-say/) | Medium | [LC38CountAndSay](src/Practice/Strings/LC38CountAndSay.java) |
| 39 | [Combination Sum](https://leetcode.com/problems/combination-sum/) | Medium | [LC39CombinationSum](src/Practice/Backtracking/LC39CombinationSum.java) |
| 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | [LC42TrappingRainWaterV2](src/Practice/TwoPointers/LC42TrappingRainWaterV2.java)<br>[LC42TrappingRainWaterV1](src/Practice/TwoPointers/LC42TrappingRainWaterV1.java) |
| 46 | [Permutations](https://leetcode.com/problems/permutations/) | Medium | [LC46Permutations](src/Practice/Backtracking/LC46Permutations.java) |
| 48 | [Rotate Image](https://leetcode.com/problems/rotate-image/) | Medium | [LC48RotateImage](src/Practice/Matrix/LC48RotateImage.java) |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | [LC49GroupAnagramsPrimeProduct](src/Practice/HashMap/LC49GroupAnagramsPrimeProduct.java)<br>[LC49GroupAnagramsSortedKey](src/Practice/HashMap/LC49GroupAnagramsSortedKey.java)<br>[LC49GroupAnagrams](src/Leetcode/FrequencyAndMultisetResoning4/LC49GroupAnagrams.java)<br>[LC49GroupAnagrams](src/Leetcode/HashMapAsLookup3/LC49GroupAnagrams.java) |
| 50 | [Pow(x, n)](https://leetcode.com/problems/powx-n/) | Medium | [LC50PowXN](src/Practice/Math/LC50PowXN.java) |
| 51 | [N-Queens](https://leetcode.com/problems/n-queens/) | Hard | [LC51NQueens](src/Practice/Backtracking/LC51NQueens.java) |
| 52 | [N-Queens II](https://leetcode.com/problems/n-queens-ii/) | Hard | [LC52NQueensII](src/Practice/Backtracking/LC52NQueensII.java) |
| 53 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | Medium | [LC53MaximumSubarray](src/Practice/Arrays/LC53MaximumSubarray.java) |
| 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | [LC56MergeIntervals](src/Practice/Intervals/LC56MergeIntervals.java) |
| 60 | [Permutation Sequence](https://leetcode.com/problems/permutation-sequence/) | Hard | [LC60PermutationSequence](src/Practice/Math/LC60PermutationSequence.java) |
| 61 | [Rotate List](https://leetcode.com/problems/rotate-list/) | Medium | [LC61RotateList](src/Practice/LinkedList/LC61RotateList.java) |
| 62 | [Unique Paths](https://leetcode.com/problems/unique-paths/) | Medium | [LC62UniquePaths](src/Practice/DynamicProgramming/LC62UniquePaths.java) |
| 66 | [Plus One](https://leetcode.com/problems/plus-one/) | Easy | [LC66PlusOneCopyArray](src/Practice/Arrays/LC66PlusOneCopyArray.java)<br>[LC66PlusOneInPlace](src/Practice/Arrays/LC66PlusOneInPlace.java) |
| 69 | [Sqrt(x)](https://leetcode.com/problems/sqrtx/) | Easy | [LC69SqrtX](src/Practice/BinarySearch/LC69SqrtX.java) |
| 73 | [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) | Medium | [LC73SetMatrixZeroes](src/Practice/Matrix/LC73SetMatrixZeroes.java) |
| 74 | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) | Medium | [LC74SearchA2DMatrix](src/Practice/BinarySearch/LC74SearchA2DMatrix.java) |
| 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | [LC75SortColors](src/Practice/TwoPointers/LC75SortColors.java) |
| 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | [LC76MinimumWindowSubstring](src/Leetcode/FrequencyAndMultisetResoning4/LC76MinimumWindowSubstring.java) |
| 78 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | [LC78Subsets](src/Practice/Backtracking/LC78Subsets.java)<br>[LC78Subsets](src/Leetcode/BitManipulationAsASetEncoding5/LC78Subsets.java) |
| 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | [LC84LargestRectangleInHistogramIndexStack](src/Practice/Stack/LC84LargestRectangleInHistogramIndexStack.java)<br>[LC84LargestRectangleInHistogramPairStack](src/Practice/Stack/LC84LargestRectangleInHistogramPairStack.java) |
| 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | [LC88MergeSortedArray](src/Practice/Sorting/LC88MergeSortedArray.java) |
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
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | [LC128LongestConsecutiveSequenceV1](src/Practice/HashMap/LC128LongestConsecutiveSequenceV1.java)<br>[LC128LongestConsecutiveSequenceV2](src/Practice/HashMap/LC128LongestConsecutiveSequenceV2.java)<br>[LC128LongestConsecutive](src/Leetcode/HashMapAsLookup3/LC128LongestConsecutive.java) |
| 131 | [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) | Medium | [LC131PalindromePartitioning](src/Practice/Backtracking/LC131PalindromePartitioning.java) |
| 133 | [Clone Graph](https://leetcode.com/problems/clone-graph/) | Medium | [LC133CloneGraph](src/Practice/Graph/LC133CloneGraph.java) |
| 135 | [Candy](https://leetcode.com/problems/candy/) | Hard | [LC135Candy](src/Practice/Greedy/LC135Candy.java) |
| 136 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | [LC136SingleNumber](src/Practice/BitManipulation/LC136SingleNumber.java)<br>[LC136SingleNumber](src/Leetcode/BitManipulationAsASetEncoding5/LC136SingleNumber.java) |
| 137 | [Single Number II](https://leetcode.com/problems/single-number-ii/) | Medium | [LC137SingleNumber](src/Leetcode/BitManipulationAsASetEncoding5/LC137SingleNumber.java) |
| 138 | [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) | Medium | [LC138CopyListWithRandomPointer](src/Practice/LinkedList/LC138CopyListWithRandomPointer.java) |
| 139 | [Word Break](https://leetcode.com/problems/word-break/) | Medium | [LC139WordBreak](src/Practice/DynamicProgramming/LC139WordBreak.java) |
| 141 | [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | Easy | [LC141LinkedListCycle](src/Practice/LinkedList/LC141LinkedListCycle.java) |
| 142 | [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) | Medium | [LC142LinkedListCycleII](src/Practice/LinkedList/LC142LinkedListCycleII.java) |
| 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | [LC146LRUCache](src/Practice/Design/LC146LRUCache.java) |
| 150 | [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | [LC150EvaluateReversePolishNotation](src/Practice/Stack/LC150EvaluateReversePolishNotation.java) |
| 151 | [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) | Medium | [LC151ReverseWordsInAString](src/Practice/Strings/LC151ReverseWordsInAString.java) |
| 155 | [Min Stack](https://leetcode.com/problems/min-stack/) | Medium | [LC155MinStack](src/Practice/Design/LC155MinStack.java) |
| 160 | [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) | Easy | [LC160IntersectionOfTwoLinkedLists](src/Practice/LinkedList/LC160IntersectionOfTwoLinkedLists.java) |
| 165 | [Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/) | Medium | [LC165CompareVersionNumbers](src/Practice/Strings/LC165CompareVersionNumbers.java) |
| 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | [LC169MajorityElementHashMap](src/Practice/Arrays/LC169MajorityElementHashMap.java)<br>[LC169MajorityElementBoyerMoore](src/Practice/Arrays/LC169MajorityElementBoyerMoore.java) |
| 173 | [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) | Medium | [LC173BinarySearchTreeIterator](src/Practice/Design/LC173BinarySearchTreeIterator.java) |
| 191 | [Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/) | Easy | [LC191NumberFo1Bits](src/Leetcode/BitManipulationAsASetEncoding5/LC191NumberFo1Bits.java) |
| 199 | [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) | Medium | [LC199BinaryTreeRightSideView](src/Practice/BinaryTree/LC199BinaryTreeRightSideView.java) |
| 204 | [Count Primes](https://leetcode.com/problems/count-primes/) | Medium | [LC204CountPrimes](src/Leetcode/ArithMetic/LC204CountPrimes.java) |
| 206 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | [LC206ReverseLinkedListIterative](src/Practice/LinkedList/LC206ReverseLinkedListIterative.java)<br>[LC206ReverseLinkedListRecursive](src/Practice/LinkedList/LC206ReverseLinkedListRecursive.java) |
| 207 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | Medium | [LC207CourseSchedule](src/Practice/Graph/LC207CourseSchedule.java) |
| 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | [LC215KthLargestElementInAnArrayPriorityQueue](src/Practice/Heap/LC215KthLargestElementInAnArrayPriorityQueue.java)<br>[LC215KthLargestElementInAnArrayManualHeap](src/Practice/Heap/LC215KthLargestElementInAnArrayManualHeap.java) |
| 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | [LC217ContainsDuplicate](src/Practice/HashMap/LC217ContainsDuplicate.java)<br>[LC217ContainsDuplicate](src/Leetcode/HashMapAsLookup3/LC217ContainsDuplicate.java) |
| 219 | [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) | Easy | [Lc219ContainsDuplicate2](src/Leetcode/HashMapAsLookup3/Lc219ContainsDuplicate2.java) |
| 220 | [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) | Hard | [LC220ContainsDuplicate3](src/Leetcode/HashMapAsLookup3/LC220ContainsDuplicate3.java) |
| 225 | [Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/) | Easy | [LC225ImplementStackUsingQueues](src/Practice/Design/LC225ImplementStackUsingQueues.java) |
| 229 | [Majority Element II](https://leetcode.com/problems/majority-element-ii/) | Medium | [LC229MajorityElementII](src/Practice/Arrays/LC229MajorityElementII.java) |
| 230 | [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | Medium | [LC230KthSmallestElementInABST](src/Practice/BST/LC230KthSmallestElementInABST.java) |
| 231 | [Power of Two](https://leetcode.com/problems/power-of-two/) | Easy | [LC231PowerOfTwo](src/Practice/Math/LC231PowerOfTwo.java)<br>[LC231IsPowerOfTwo](src/Leetcode/BitManipulationAsASetEncoding5/LC231IsPowerOfTwo.java) |
| 232 | [Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) | Easy | [LC232ImplementQueueUsingStacks](src/Practice/Design/LC232ImplementQueueUsingStacks.java) |
| 234 | [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) | Easy | [LC234PalindromeLinkedList](src/Practice/LinkedList/LC234PalindromeLinkedList.java) |
| 235 | [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | Medium | [LC235LowestCommonAncestorOfABinarySearchTree](src/Practice/BST/LC235LowestCommonAncestorOfABinarySearchTree.java) |
| 236 | [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | Medium | [LC236LowestCommonAncestorOfABinaryTree](src/Practice/BinaryTree/LC236LowestCommonAncestorOfABinaryTree.java) |
| 237 | [Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/) | Medium | [LC237DeleteNodeInALinkedList](src/Practice/LinkedList/LC237DeleteNodeInALinkedList.java) |
| 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | [LC239SlidingWindowMaximum](src/Practice/SlidingWindow/LC239SlidingWindowMaximum.java) |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | [LC242ValidAnagramV1](src/Practice/HashMap/LC242ValidAnagramV1.java)<br>[LC242ValidAnagramV2](src/Practice/HashMap/LC242ValidAnagramV2.java)<br>[LC242ValidAnagramV3](src/Practice/HashMap/LC242ValidAnagramV3.java)<br>[LC242ValidAnagram](src/Leetcode/FrequencyAndMultisetResoning4/LC242ValidAnagram.java) |
| 260 | [Single Number III](https://leetcode.com/problems/single-number-iii/) | Medium | [LC260SingleNumberIII](src/Leetcode/BitManipulationAsASetEncoding5/LC260SingleNumberIII.java) |
| 283 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | Easy | [LC283MoveZeroes](src/Practice/TwoPointers/LC283MoveZeroes.java) |
| 287 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | Medium | [LC287FindTheDuplicateNumber](src/Practice/TwoPointers/LC287FindTheDuplicateNumber.java) |
| 295 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Hard | [LC295FindMedianFromDataStream](src/Practice/Heap/LC295FindMedianFromDataStream.java) |
| 297 | [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | Hard | [LC297SerializeAndDeserializeBinaryTree](src/Practice/Design/LC297SerializeAndDeserializeBinaryTree.java) |
| 303 | [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) | Easy | [LC303NumArray](src/Leetcode/PrefixSum2/LC303NumArray.java) |
| 318 | [Maximum Product of Word Lengths](https://leetcode.com/problems/maximum-product-of-word-lengths/) | Medium | [LC318MaximumProductofWordLengths](src/Leetcode/BitManipulationAsASetEncoding5/LC318MaximumProductofWordLengths.java) |
| 322 | [Coin Change](https://leetcode.com/problems/coin-change/) | Medium | [LC322CoinChange](src/Practice/Greedy/LC322CoinChange.java) |
| 338 | [Counting Bits](https://leetcode.com/problems/counting-bits/) | Easy | [LC338CountingBits](src/Leetcode/BitManipulationAsASetEncoding5/LC338CountingBits.java) |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | [LC347TopKFrequentElements](src/Practice/Heap/LC347TopKFrequentElements.java)<br>[LC347TopKFrequent](src/Leetcode/FrequencyAndMultisetResoning4/LC347TopKFrequent.java) |
| 373 | [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) | Medium | [LC373FindKPairsWithSmallestSums](src/Practice/Heap/LC373FindKPairsWithSmallestSums.java) |
| 380 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | Medium | [LC380RandomizedSet](src/Leetcode/CountingStack/LC380RandomizedSet.java) |
| 383 | [Ransom Note](https://leetcode.com/problems/ransom-note/) | Easy | [LC383RansomNote](src/Leetcode/FrequencyAndMultisetResoning4/LC383RansomNote.java) |
| 386 | [Lexicographical Numbers](https://leetcode.com/problems/lexicographical-numbers/) | Medium | [LC386LexicographicalNumbers](src/Practice/Recursion/LC386LexicographicalNumbers.java) |
| 387 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | [LC387FirstUniqueCharacters](src/Leetcode/FrequencyAndMultisetResoning4/LC387FirstUniqueCharacters.java) |
| 409 | [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/) | Easy | [LC409LongestPalindrome](src/Leetcode/FrequencyAndMultisetResoning4/LC409LongestPalindrome.java) |
| 412 | [Fizz Buzz](https://leetcode.com/problems/fizz-buzz/) | Easy | [LC412FizzBuzz](src/Leetcode/Random/LC412FizzBuzz.java) |
| 436 | [Find Right Interval](https://leetcode.com/problems/find-right-interval/) | Medium | [LC436FindRightInterval](src/Practice/BinarySearch/LC436FindRightInterval.java) |
| 438 | [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | Medium | [LC438findAnagrams](src/Leetcode/FrequencyAndMultisetResoning4/LC438findAnagrams.java) |
| 440 | [K-th Smallest in Lexicographical Order](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/) | Hard | [LC440KThSmallestInLexicographicalOrder](src/Practice/Math/LC440KThSmallestInLexicographicalOrder.java) |
| 448 | [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) | Easy | [LC448FindAllNumbersDisappearedInAnArray](src/Practice/Arrays/LC448FindAllNumbersDisappearedInAnArray.java) |
| 451 | [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/) | Medium | [LC451FrequencySort](src/Leetcode/FrequencyAndMultisetResoning4/LC451FrequencySort.java) |
| 454 | [4Sum II](https://leetcode.com/problems/4sum-ii/) | Medium | [LC454FourSumCount](src/Leetcode/HashMapAsLookup3/LC454FourSumCount.java) |
| 455 | [Assign Cookies](https://leetcode.com/problems/assign-cookies/) | Easy | [LC455AssignCookies](src/Practice/Greedy/LC455AssignCookies.java) |
| 460 | [LFU Cache](https://leetcode.com/problems/lfu-cache/) | Hard | [LC460LFUCache](src/Practice/Design/LC460LFUCache.java) |
| 485 | [Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/) | Easy | [LC485MaxConsecutiveOnesV1](src/Practice/Arrays/LC485MaxConsecutiveOnesV1.java)<br>[LC485MaxConsecutiveOnesV2](src/Practice/Arrays/LC485MaxConsecutiveOnesV2.java) |
| 493 | [Reverse Pairs](https://leetcode.com/problems/reverse-pairs/) | Hard | [LC493ReversePairs](src/Practice/Sorting/LC493ReversePairs.java) |
| 496 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | Easy | [LC496NextGreaterElementI](src/Practice/Stack/LC496NextGreaterElementI.java) |
| 523 | [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) | Medium | [LC523ContinuousSubArraySum](src/Leetcode/HashMapAsLookup3/LC523ContinuousSubArraySum.java)<br>[LC523ContinuousSubarraySum](src/Leetcode/PrefixSum2/LC523ContinuousSubarraySum.java) |
| 525 | [Contiguous Array](https://leetcode.com/problems/contiguous-array/) | Medium | [LC525ContiguousArray](src/Leetcode/PrefixSum2/LC525ContiguousArray.java) |
| 540 | [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) | Medium | [LC540SingleElementInASortedArray](src/Practice/BinarySearch/LC540SingleElementInASortedArray.java) |
| 543 | [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) | Easy | [LC543DiameterOfBinaryTree](src/Practice/BinaryTree/LC543DiameterOfBinaryTree.java) |
| 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | [LC560SubarraySumEqualsKV1](src/Practice/PrefixSum/LC560SubarraySumEqualsKV1.java)<br>[LC560SubarraySumEqualsKV2](src/Practice/PrefixSum/LC560SubarraySumEqualsKV2.java) |
| 567 | [Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Medium | [LC567PermutationInString](src/Practice/SlidingWindow/LC567PermutationInString.java)<br>[LC567PermutationinString](src/Leetcode/FrequencyAndMultisetResoning4/LC567PermutationinString.java) |
| 594 | [Longest Harmonious Subsequence](https://leetcode.com/problems/longest-harmonious-subsequence/) | Easy | [LC594LongestHarmoniousSubsequence](src/Practice/HashMap/LC594LongestHarmoniousSubsequence.java) |
| 605 | [Can Place Flowers](https://leetcode.com/problems/can-place-flowers/) | Easy | [LC605CanPlaceFlowers](src/Practice/Greedy/LC605CanPlaceFlowers.java) |
| 636 | [Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) | Medium | [LC636ExclusiveTimeOfFunctionsV1](src/Practice/Stack/LC636ExclusiveTimeOfFunctionsV1.java)<br>[LC636ExclusiveTimeOfFunctionsV2](src/Practice/Stack/LC636ExclusiveTimeOfFunctionsV2.java) |
| 645 | [Set Mismatch](https://leetcode.com/problems/set-mismatch/) | Easy | [LC645SetMismatch](src/Practice/Arrays/LC645SetMismatch.java)<br>[LC2965FindMissingAndRepeatedValues](src/Practice/Math/LC2965FindMissingAndRepeatedValues.java) (second problem in this file) |
| 653 | [Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/) | Easy | [LC653TwoSumIVInputIsABST](src/Practice/BST/LC653TwoSumIVInputIsABST.java) |
| 662 | [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) | Medium | [LC662MaximumWidthOfBinaryTree](src/Practice/BinaryTree/LC662MaximumWidthOfBinaryTree.java) |
| 686 | [Repeated String Match](https://leetcode.com/problems/repeated-string-match/) | Medium | [LC686RepeatedStringMatch](src/Practice/Strings/LC686RepeatedStringMatch.java) |
| 700 | [Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/) | Easy | [LC700SearchInABinarySearchTree](src/Practice/BST/LC700SearchInABinarySearchTree.java) |
| 704 | [Binary Search](https://leetcode.com/problems/binary-search/) | Easy | [LC704BinarySearch](src/Practice/BinarySearch/LC704BinarySearch.java) |
| 705 | [Design HashSet](https://leetcode.com/problems/design-hashset/) | Easy | [LC705DesignHashSet](src/Practice/Design/LC705DesignHashSet.java) |
| 724 | [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) | Easy | [LC724FindPivotIndex](src/Leetcode/PrefixSum2/LC724FindPivotIndex.java) |
| 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | [LC739DailyTemperaturesRightToLeft](src/Practice/Stack/LC739DailyTemperaturesRightToLeft.java)<br>[LC739DailyTemperaturesLeftToRight](src/Practice/Stack/LC739DailyTemperaturesLeftToRight.java) |
| 836 | [Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/) | Easy | [RectangleOverLap](src/Leetcode/Daily/RectangleOverLap.java) |
| 876 | [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) | Easy | [LC876MiddleOfTheLinkedList](src/Practice/LinkedList/LC876MiddleOfTheLinkedList.java) |
| 901 | [Online Stock Span](https://leetcode.com/problems/online-stock-span/) | Medium | [LC901OnlineStockSpan](src/Practice/Stack/LC901OnlineStockSpan.java) |
| 904 | [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | Medium | [LC904FruitIntoBaskets](src/Practice/SlidingWindow/LC904FruitIntoBaskets.java) |
| 912 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | Medium | [LC912SortAnArrayV1](src/Practice/Sorting/LC912SortAnArrayV1.java)<br>[LC912SortAnArrayV2](src/Practice/Sorting/LC912SortAnArrayV2.java) |
| 941 | [Valid Mountain Array](https://leetcode.com/problems/valid-mountain-array/) | Easy | [LC941ValidMountainArray](src/Practice/Arrays/LC941ValidMountainArray.java) |
| 974 | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | Medium | [LC974SubarraysDivByK](src/Leetcode/HashMapAsLookup3/LC974SubarraysDivByK.java)<br>[LC974SubArraysSumDivByK](src/Leetcode/PrefixSum2/LC974SubArraysSumDivByK.java) |
| 987 | [Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) | Hard | [LC987VerticalOrderTraversalOfABinaryTree](src/Practice/BinaryTree/LC987VerticalOrderTraversalOfABinaryTree.java) |
| 992 | [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) | Hard | [LC992SubarraysWithKDistinct](src/Leetcode/CountingStack/LC992SubarraysWithKDistinct.java) |
| 994 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | Medium | [LC994RottingOranges](src/Practice/Graph/LC994RottingOranges.java) |
| 1008 | [Construct Binary Search Tree from Preorder Traversal](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/) | Medium | [LC1008ConstructBinarySearchTreeFromPreorderTraversal](src/Practice/BST/LC1008ConstructBinarySearchTreeFromPreorderTraversal.java) |
| 1046 | [Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) | Easy | [LC1046LastStoneWeight](src/Practice/Heap/LC1046LastStoneWeight.java) |
| 1047 | [Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) | Easy | [LC1047RemoveDuplicates](src/Leetcode/CountingStack/LC1047RemoveDuplicates.java) |
| 1061 | [Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/) | Medium | [LC1061LexicographicallySmallestEquivalentString](src/Practice/Graph/LC1061LexicographicallySmallestEquivalentString.java) |
| 1074 | [Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/) | Hard | [LC1074NumSubmatrixSumTarget](src/Leetcode/PrefixSum2/LC1074NumSubmatrixSumTarget.java) |
| 1081 | [Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) | Medium | [LC1081SmallestSubsequenceOfDistinctCharacters](src/Practice/Stack/LC1081SmallestSubsequenceOfDistinctCharacters.java) |
| 1094 | [Car Pooling](https://leetcode.com/problems/car-pooling/) | Medium | [LC1094CarPooling](src/Leetcode/PrefixSum2/LC1094CarPooling.java) |
| 1109 | [Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/) | Medium | [LC1109corpFlightBookings](src/Leetcode/PrefixSum2/LC1109corpFlightBookings.java) |
| 1178 | [Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/) | Hard | [LC1178NumberOfValidWordsforEachPuzzle](src/Leetcode/BitManipulationAsASetEncoding5/LC1178NumberOfValidWordsforEachPuzzle.java) |
| 1235 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | Hard | [LC1235MaximumProfitInJobScheduling](src/Practice/DynamicProgramming/LC1235MaximumProfitInJobScheduling.java) |
| 1295 | [Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/) | Easy | [LC1295FindNumbersWithEvenNumberOfDigits](src/Practice/Math/LC1295FindNumbersWithEvenNumberOfDigits.java) |
| 1298 | [Maximum Candies You Can Get from Boxes](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/) | Hard | [LC1298MaximumCandiesYouCanGetFromBoxes](src/Practice/Graph/LC1298MaximumCandiesYouCanGetFromBoxes.java) |
| 1344 | [Angle Between Hands of a Clock](https://leetcode.com/problems/angle-between-hands-of-a-clock/) | Medium | [LC1344AngleBetweenHandsOfAClock](src/Practice/Math/LC1344AngleBetweenHandsOfAClock.java) |
| 1346 | [Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/) | Easy | [LC1346CheckIfExist](src/Leetcode/HashMapAsLookup3/LC1346CheckIfExist.java) |
| 1365 | [How Many Numbers Are Smaller Than the Current Number](https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/) | Easy | [LC1365HowManyNumbersAreSmallerThanTheCurrentNumber](src/Practice/Sorting/LC1365HowManyNumbersAreSmallerThanTheCurrentNumber.java) |
| 1371 | [Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/) | Medium | [LC1371FindTheLongestSubstring](src/Leetcode/BitManipulationAsASetEncoding5/LC1371FindTheLongestSubstring.java) |
| 1373 | [Maximum Sum BST in Binary Tree](https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/) | Hard | [LC1373MaximumSumBSTInBinaryTree](src/Practice/BST/LC1373MaximumSumBSTInBinaryTree.java) |
| 1394 | [Find Lucky Integer in an Array](https://leetcode.com/problems/find-lucky-integer-in-an-array/) | Easy | [LC1394FindLuckyIntegerInAnArray](src/Practice/HashMap/LC1394FindLuckyIntegerInAnArray.java) |
| 1411 | [Number of Ways to Paint N × 3 Grid](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/) | Hard | [LC5LongestPalindromicSubstringV1](src/Practice/TwoPointers/LC5LongestPalindromicSubstringV1.java) (second problem in this file) |
| 1432 | [Max Difference You Can Get From Changing an Integer](https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/) | Medium | [LC1432MaxDifferenceYouCanGetFromChangingAnInteger](src/Practice/Greedy/LC1432MaxDifferenceYouCanGetFromChangingAnInteger.java) |
| 1441 | [Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations/) | Medium | [LC1441BuildAnArrayWithStackOperationsV1](src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV1.java)<br>[LC1441BuildAnArrayWithStackOperationsV2](src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV2.java)<br>[LC1441BuildArrayWithStack](src/Leetcode/CountingStack/LC1441BuildArrayWithStack.java) |
| 1442 | [Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/) | Medium | [LC1442CountTriplets](src/Leetcode/PrefixSum2/LC1442CountTriplets.java) |
| 1470 | [Shuffle the Array](https://leetcode.com/problems/shuffle-the-array/) | Easy | [LC1470ShuffleTheArray](src/Practice/Arrays/LC1470ShuffleTheArray.java) |
| 1475 | [Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) | Easy | [LC1475FinalPricesWithASpecialDiscountInAShop](src/Practice/Arrays/LC1475FinalPricesWithASpecialDiscountInAShop.java) |
| 1498 | [Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/) | Medium | [LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition](src/Practice/TwoPointers/LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition.java) |
| 1513 | [Number of Substrings With Only 1s](https://leetcode.com/problems/number-of-substrings-with-only-1s/) | Medium | [LC1513NumberOfSubstringsWithOnly1s](src/Practice/Math/LC1513NumberOfSubstringsWithOnly1s.java) |
| 1526 | [Minimum Number of Increments on Subarrays to Form a Target Array](https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/) | Hard | [LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray](src/Practice/Greedy/LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray.java) |
| 1550 | [Three Consecutive Odds](https://leetcode.com/problems/three-consecutive-odds/) | Easy | [LC1550ThreeConsecutiveOdds](src/Practice/Arrays/LC1550ThreeConsecutiveOdds.java) |
| 1578 | [Minimum Time to Make Rope Colorful](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/) | Medium | [LC1578MinimumTimeToMakeRopeColorful](src/Practice/Greedy/LC1578MinimumTimeToMakeRopeColorful.java) |
| 1657 | [Determine if Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close/) | Medium | [LC1657DetermineIfTwoStringsAreClose](src/Practice/HashMap/LC1657DetermineIfTwoStringsAreClose.java) |
| 1658 | [Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/) | Medium | [LC1658MinimumOperationstoReduceXtoZero](src/Leetcode/Daily/LC1658MinimumOperationstoReduceXtoZero.java) |
| 1700 | [Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/) | Easy | [LC1700NumberOfStudentsUnableToEatLunch](src/Practice/Queue/LC1700NumberOfStudentsUnableToEatLunch.java) |
| 1710 | [Maximum Units on a Truck](https://leetcode.com/problems/maximum-units-on-a-truck/) | Easy | [LC1710MaximumUnitsOnATruck](src/Practice/Greedy/LC1710MaximumUnitsOnATruck.java) |
| 1717 | [Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings/) | Medium | [LC1717maximumGain](src/Leetcode/CountingStack/LC1717maximumGain.java) |
| 1732 | [Find the Highest Altitude](https://leetcode.com/problems/find-the-highest-altitude/) | Easy | [LC1732largestAltitude](src/Leetcode/PrefixSum2/LC1732largestAltitude.java) |
| 1863 | [Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/) | Easy | [LC1863SumOfAllSubsetXORTotals](src/Leetcode/BitManipulationAsASetEncoding5/LC1863SumOfAllSubsetXORTotals.java) |
| 1865 | [Finding Pairs With a Certain Sum](https://leetcode.com/problems/finding-pairs-with-a-certain-sum/) | Medium | [LC1865FindingPairsWithACertainSum](src/Practice/Design/LC1865FindingPairsWithACertainSum.java) |
| 1929 | [Concatenation of Array](https://leetcode.com/problems/concatenation-of-array/) | Easy | [LC1929ConcatenationOfArray](src/Practice/Arrays/LC1929ConcatenationOfArray.java) |
| 1979 | [Find Greatest Common Divisor of Array](https://leetcode.com/problems/find-greatest-common-divisor-of-array/) | Easy | [LC1979FindGreatestCommonDivisorOfArray](src/Practice/Math/LC1979FindGreatestCommonDivisorOfArray.java) |
| 1984 | [Minimum Difference Between Highest and Lowest of K Scores](https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/) | Easy | [LC1984MinimumDifferenceBetweenHighestAndLowestOfKScores](src/Practice/SlidingWindow/LC1984MinimumDifferenceBetweenHighestAndLowestOfKScores.java) |
| 1991 | [Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) | Easy | [LC1991findMiddleIndex](src/Leetcode/PrefixSum2/LC1991findMiddleIndex.java) |
| 2006 | [Count Number of Pairs With Absolute Difference K](https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/) | Easy | [LC2006CountKDifference](src/Leetcode/HashMapAsLookup3/LC2006CountKDifference.java) |
| 2016 | [Maximum Difference Between Increasing Elements](https://leetcode.com/problems/maximum-difference-between-increasing-elements/) | Easy | [LC2016MaximumDifferenceBetweenIncreasingElements](src/Practice/Arrays/LC2016MaximumDifferenceBetweenIncreasingElements.java) |
| 2058 | [Find the Minimum and Maximum Number of Nodes Between Critical Points](https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/) | Medium | [LC2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints](src/Practice/LinkedList/LC2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints.java) |
| 2073 | [Time Needed to Buy Tickets](https://leetcode.com/problems/time-needed-to-buy-tickets/) | Easy | [LC2073TimeNeededToBuyTickets](src/Practice/Queue/LC2073TimeNeededToBuyTickets.java) |
| 2094 | [Finding 3-Digit Even Numbers](https://leetcode.com/problems/finding-3-digit-even-numbers/) | Easy | [LC2094Find3DigitEvenNumbers](src/Leetcode/Daily/LC2094Find3DigitEvenNumbers.java) |
| 2099 | [Find Subsequence of Length K With the Largest Sum](https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/) | Easy | [LC2099FindSubsequenceOfLengthKWithTheLargestSum](src/Practice/Sorting/LC2099FindSubsequenceOfLengthKWithTheLargestSum.java) |
| 2125 | [Number of Laser Beams in a Bank](https://leetcode.com/problems/number-of-laser-beams-in-a-bank/) | Medium | [LC2125NumberOfLaserBeamsInABank](src/Practice/Matrix/LC2125NumberOfLaserBeamsInABank.java) |
| 2138 | [Divide a String Into Groups of Size k](https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/) | Easy | [LC2138DivideAStringIntoGroupsOfSizeK](src/Practice/Strings/LC2138DivideAStringIntoGroupsOfSizeK.java) |
| 2154 | [Keep Multiplying Found Values by Two](https://leetcode.com/problems/keep-multiplying-found-values-by-two/) | Easy | [LC2154KeepMultiplyingFoundValuesByTwo](src/Practice/Arrays/LC2154KeepMultiplyingFoundValuesByTwo.java) |
| 2169 | [Count Operations to Obtain Zero](https://leetcode.com/problems/count-operations-to-obtain-zero/) | Easy | [LC2169CountOperationsToObtainZero](src/Practice/Math/LC2169CountOperationsToObtainZero.java) |
| 2200 | [Find All K-Distant Indices in an Array](https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/) | Easy | [LC2200FindAllKDistantIndicesInAnArray](src/Practice/Arrays/LC2200FindAllKDistantIndicesInAnArray.java) |
| 2257 | [Count Unguarded Cells in the Grid](https://leetcode.com/problems/count-unguarded-cells-in-the-grid/) | Medium | [LC2257CountUnguardedCellsInTheGrid](src/Practice/Matrix/LC2257CountUnguardedCellsInTheGrid.java) |
| 2264 | [Largest 3-Same-Digit Number in String](https://leetcode.com/problems/largest-3-same-digit-number-in-string/) | Easy | [LC2264Largest3SameDigitNumberInString](src/Practice/Strings/LC2264Largest3SameDigitNumberInString.java) |
| 2294 | [Partition Array Such That Maximum Difference Is K](https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/) | Medium | [LC2294PartitionArraySuchThatMaximumDifferenceIsK](src/Practice/Greedy/LC2294PartitionArraySuchThatMaximumDifferenceIsK.java) |
| 2300 | [Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/) | Medium | [LC2300SuccessfulPairsOfSpellsAndPotions](src/Practice/BinarySearch/LC2300SuccessfulPairsOfSpellsAndPotions.java) |
| 2434 | [Using a Robot to Print the Lexicographically Smallest String](https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/) | Medium | [LC2434UsingARobotToPrintTheLexicographicallySmallestString](src/Practice/Stack/LC2434UsingARobotToPrintTheLexicographicallySmallestString.java) |
| 2438 | [Range Product Queries of Powers](https://leetcode.com/problems/range-product-queries-of-powers/) | Medium | [LC2438RangeProductQueriesOfPowers](src/Practice/BitManipulation/LC2438RangeProductQueriesOfPowers.java) |
| 2528 | [Maximize the Minimum Powered City](https://leetcode.com/problems/maximize-the-minimum-powered-city/) | Hard | [LC2528MaximizeTheMinimumPoweredCity](src/Practice/BinarySearch/LC2528MaximizeTheMinimumPoweredCity.java) |
| 2566 | [Maximum Difference by Remapping a Digit](https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/) | Easy | [LC2566MaximumDifferenceByRemappingADigit](src/Practice/Greedy/LC2566MaximumDifferenceByRemappingADigit.java) |
| 2588 | [Count the Number of Beautiful Subarrays](https://leetcode.com/problems/count-the-number-of-beautiful-subarrays/) | Medium | [LC2588CountTheNumberOfBeautifulSubarrays](src/Practice/PrefixSum/LC2588CountTheNumberOfBeautifulSubarrays.java) |
| 2615 | [Sum of Distances](https://leetcode.com/problems/sum-of-distances/) | Medium | [LC2615SumOfDistances](src/Practice/HashMap/LC2615SumOfDistances.java) |
| 2616 | [Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/) | Medium | [LC2616MinimizeTheMaximumDifferenceOfPairs](src/Practice/BinarySearch/LC2616MinimizeTheMaximumDifferenceOfPairs.java) |
| 2929 | [Distribute Candies Among Children II](https://leetcode.com/problems/distribute-candies-among-children-ii/) | Medium | [LC2929DistributeCandiesAmongChildrenII](src/Practice/Math/LC2929DistributeCandiesAmongChildrenII.java) |
| 2965 | [Find Missing and Repeated Values](https://leetcode.com/problems/find-missing-and-repeated-values/) | Easy | [LC2965FindMissingAndRepeatedValues](src/Practice/Math/LC2965FindMissingAndRepeatedValues.java) |
| 2966 | [Divide Array Into Arrays With Max Difference](https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/) | Medium | [LC2966DivideArrayIntoArraysWithMaxDifference](src/Practice/Sorting/LC2966DivideArrayIntoArraysWithMaxDifference.java) |
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

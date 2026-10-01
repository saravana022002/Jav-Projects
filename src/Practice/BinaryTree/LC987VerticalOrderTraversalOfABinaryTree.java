package Practice.BinaryTree;

import java.util.*;

// ============================================================================
// LC 987. Vertical Order Traversal of a Binary Tree   [Hard]
// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
// Topics: Hash Table, Tree, Depth-First Search, Breadth-First Search, Sorting, Binary Tree
// ============================================================================
//
// PROBLEM
//   Put the tree on a grid: the root is at (row 0, column 0); a node at (row, col) has its
//   left child at (row + 1, col - 1) and its right child at (row + 1, col + 1).
//   Return the values grouped by column, from the leftmost column to the rightmost one. Inside
//   a column list the values from top to bottom (by row). If several nodes share the same
//   row AND column, list those in increasing order of their values.
//
// EXAMPLES
//   root = [3,9,20,null,null,15,7]  ->  [[9],[3,15],[20],[7]]
//       columns -1, 0, 1, 2: column 0 holds 3 (row 0) and 15 (row 2), top to bottom
//   root = [1,2,3,4,5,6,7]          ->  [[4],[2],[1,5,6],[3],[7]]
//       5 and 6 both sit at row 2, column 0, so the smaller value 5 goes first (swapping
//       them in the input, as in [1,2,3,4,6,5,7], gives the same answer)
//
// CONSTRAINTS
//   1 <= number of nodes <= 1000,   0 <= Node.val <= 1000
//
// INTUITION
//   First give every node its coordinates (row, col) with a level-order walk. Then the
//   answer is just a sort with three keys: column, then row, then value. Let Java's sorted
//   collections do it: a TreeMap from column to (a TreeMap from row to a PriorityQueue of
//   values). The outer TreeMap iterates columns left to right, the inner one rows top to
//   bottom, and a PriorityQueue (a min-heap) pops the values of one cell in increasing order.
//   Flattening the structure in that order produces the answer.
//
// APPROACH (as implemented below)
//   1. queue holds Tuple(x, y, node) where x = column and y = row; start with (0, 0, root).
//      map is the column -> row -> PriorityQueue structure.
//   2. Level-order walk: poll a tuple, file node.val into map[x][y] (creating the inner
//      TreeMap / PriorityQueue the first time), then queue the children at
//      (x - 1, y + 1) and (x + 1, y + 1).
//   3. Flatten: for every column (map.values()) append a new empty list to ans, then for
//      every row of that column (top to bottom) drain its PriorityQueue (increasing values)
//      into that column's list.
//      THIS STEP IS BROKEN as written - see STATUS.
//   4. Return ans.
//
// DRY RUN   root = [3,9,20,null,null,15,7]   (example 1, what the algorithm is meant to do)
//   step 2   (col, row): 3 at (0,0); 9 at (-1,1); 20 at (1,1); 15 at (0,2); 7 at (2,2)
//            map: -1 -> {1:[9]}   0 -> {0:[3], 2:[15]}   1 -> {1:[20]}   2 -> {2:[7]}
//   step 3   column -1 -> [9];  column 0 -> [3, 15];  column 1 -> [20];  column 2 -> [7]
//   result   [[9],[3,15],[20],[7]]
//   (The code as written never gets to produce this - see STATUS.)
//
// STATUS
//   Known issue: the flatten loop does list.get(list.size() - 1).add(queues.poll()), but
//   here "list" is the column's TreeMap of rows, not the answer. TreeMap.get(key) looks up a
//   ROW NUMBER equal to (rows in this column - 1), it does not take the "last element", and
//   the values are never added to the result lists.
//     - Example 1 throws a NullPointerException (column -1 has the single row 1, so get(0)
//       finds nothing).
//     - A column whose only row is row 0, e.g. the single-node tree [1], makes get(0) return
//       the very queue that is being drained: every polled value is put straight back and
//       the loop never ends (the program hangs).
//   Expected for example 1: [[9],[3,15],[20],[7]]. The fix is to add to the last list of
//   the answer: ans.get(ans.size() - 1).add(queues.poll()) - on a copy of this file that
//   gives the expected output for all three official examples.
//   The code is left unchanged on purpose.
//   Also: main() is empty. TreeNode and Tuple are non-static inner classes.
//
// COMPLEXITY   (of the intended algorithm)
//   Time : O(n log n) - each node goes through a few TreeMap / PriorityQueue operations
//   Space: O(n) - the tuples in the queue plus the map, which together hold every node
//
// GOTCHAS
//   - Only the flatten step is broken; the walk and the TreeMap / PriorityQueue structure
//     are fine.
//   - The tie rule (same row AND same column -> increasing value) is the reason for the
//     PriorityQueue. It is what separates this problem from LC 314, where such nodes keep
//     their left-to-right order.
//   - x is the column and y the row: moving to a child always adds 1 to the row, and
//     subtracts or adds 1 to the column.
//   - list.get(list.size() - 1) is a List idiom for "last element"; on a TreeMap, get() takes
//     a key, not an index.
//
// RELATED
//   - GFGBottomViewOfBinaryTree.java and GFGTopViewOfBinaryTree.java - the same columns,
//     but they keep only one node per column.
//   - LC 314 Binary Tree Vertical Order Traversal - the simpler premium version: no value
//     tie-break inside a cell.
//   - LC 102 Binary Tree Level Order Traversal - the level-order walk used in step 2.
// ============================================================================
public class LC987VerticalOrderTraversalOfABinaryTree {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }

    // Intended: the values grouped by column (left to right), each column top to bottom and
    // by value inside a cell. As written, the flatten loop below never fills the result.
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        Queue<Tuple> queue = new LinkedList<>();
        // column -> (row -> values in that cell, smallest first); the TreeMaps keep both sorted
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        // the root is at column 0, row 0
        queue.add(new Tuple(0, 0, root));
        // step 2: level-order walk that files every value under its (column, row)
        while (!queue.isEmpty()){
            Tuple tuple = queue.poll();
            int x = tuple.x;
            int y = tuple.y;
            TreeNode treeNode = tuple.node;
            if(!map.containsKey(x)){
                map.put(x, new TreeMap<>());
            }
            map.get(x).putIfAbsent(y, new PriorityQueue<>());
            map.get(x).get(y).add(treeNode.val);
            // a left child is one row down and one column left, a right child one column right
            if(treeNode.left != null){
                queue.add(new Tuple( x - 1, y + 1, treeNode.left));
            }
            if(treeNode.right != null){
                queue.add(new Tuple( x + 1, y + 1, treeNode.right));
            }
        }
        // step 3: flatten, column by column (left to right)
        List<List<Integer>> ans = new ArrayList<>();
        for(TreeMap<Integer, PriorityQueue<Integer>> list : map.values()){
            // one result list per column
            ans.add(new ArrayList<>());
            // rows top to bottom
            for (PriorityQueue<Integer> queues : list.values()){
                while(!queues.isEmpty()){
                    // BUG (see STATUS): "list" is the column's TreeMap, so this does a KEY lookup.
                    // Intended: ans.get(ans.size() - 1).add(queues.poll());
                    list.get(list.size() - 1).add(queues.poll());
                }
            }
        }
        return ans;
    }

    // a node together with its column (x) and row (y)
    class Tuple{
        TreeNode node;
        int x;
        int y;
        Tuple(int x, int y, TreeNode node){
            this.x = x;
            this.y = y;
            this.node = node;
        }
    }
    // minimal LeetCode-style node; a non-static inner class, so it needs an outer instance
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
}

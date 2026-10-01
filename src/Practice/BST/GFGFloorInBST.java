package Practice.BST;

// ============================================================================
// GFG - Floor in BST   [Easy]
// https://www.geeksforgeeks.org/problems/closest-neighbor-in-bst/1
// Topics: Binary Search Tree, Binary Search
// ============================================================================
//
// PROBLEM
//   You get the root of a binary search tree and a number x (GFG calls it k).
//   Return the "floor" of x: the largest value stored in the tree that is <= x.
//   If every value in the tree is bigger than x there is no floor - return -1.
//
// EXAMPLES
//   root = [10,7,15,2,8,11,16], x = 14                ->  11   15 and 16 are too big,
//                                                              11 is the largest of the rest
//   root = [5,2,12,1,3,9,21,N,N,N,N,N,N,19,25], x = 24 ->  21   only 25 is too big
//   same tree, x = 4                                   ->  3
//
// INTUITION
//   In a BST everything left of a node is smaller and everything right of it is bigger,
//   so one comparison per node tells us which side can still hold a better answer:
//     node > x  : the node and its whole right subtree are too big -> go left
//     node < x  : the node is a valid floor, and a bigger valid one can only be in its
//                 right subtree -> remember it and go right
//     node == x : x itself is in the tree, so x is the floor -> stop
//   Every new candidate lies in the right subtree of the previous one, so it is bigger;
//   the last candidate remembered on the way down is the answer.
//
// APPROACH (as implemented below)
//   1. ans = 0 holds the best floor seen so far (GFG wants -1 when none - see STATUS).
//   2. While root != null:
//        root.val > x -> root = root.left
//        root.val < x -> ans = root.val, then root = root.right
//        (there is no branch for root.val == x - see STATUS)
//   3. Return ans.
//
// DRY RUN   root = [10,7,15,2,8,11,16], x = 14   (main() is empty, so this is example 1)
//   root=10  : 10 < 14 -> ans = 10, go right
//   root=15  : 15 > 14 -> go left
//   root=11  : 11 < 14 -> ans = 11, go right
//   root=null: loop ends -> return 11
//
// STATUS
//   Known issue 1: there is no case for root.val == x, so root never moves and the loop
//   runs forever whenever x is a value in the tree (x = 8 or x = 10 on the example-1
//   tree hangs; the answer should be x itself).
//   Known issue 2: ans starts at 0, so when no floor exists it returns 0 instead of -1
//   (x = 1 on the example-1 tree returns 0).
//   main() is empty, so running the file prints nothing.
//   The code is left unchanged on purpose.
//
// COMPLEXITY   (for inputs where it terminates)
//   Time : O(h) - one step down per iteration, h = tree height (log n if balanced,
//          n if the tree is a chain)
//   Space: O(1) - only ans and the moving root pointer
//
// GOTCHAS
//   - x being in the tree is the best case, not a special one: its floor is x itself.
//   - Node is a non-static inner class, so static code must build it through an outer
//     object: new GFGFloorInBST().new Node(5).
//
// RELATED
//   - LC 700 Search in a Binary Search Tree - the same one-path descent, but it looks for
//     an exact match instead of the best value <= x.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGFloorInBST {

    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }


    // Returns the largest key <= x (the floor). Hangs if x is in the tree and returns 0
    // instead of -1 when there is no floor - see STATUS.
    public static int floor(Node root, int x) {
        // best floor so far; should start at -1 so "no floor" is reported correctly
        int ans = 0;
        // walk a single root-to-leaf path, like a normal BST search
        while(root != null){
            if(root.val > x){
                // too big: this node and its right subtree are out -> look left
                root = root.left;
            } else if (root.val < x) {
                // fits: record it, then try to find a bigger one that still fits (right side)
                ans = root.val;
                root = root.right;
            }
            // missing: root.val == x -> should return x; root stays put -> infinite loop
        }
        return ans;
    }

    // tree node - non-static, so create it with new GFGFloorInBST().new Node(v)
    public class Node {
        int val;
        Node left;
        Node right;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}

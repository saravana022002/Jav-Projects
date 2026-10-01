package Practice.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ============================================================================
// LC 235. Lowest Common Ancestor of a Binary Search Tree   [Medium]
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// Topics: Tree, Depth-First Search, Binary Search Tree, Binary Tree, Binary Lifting,
//         Lowest Common Ancestor
// ============================================================================
//
// PROBLEM
//   You get a BST and two different nodes p and q that are both in it. Return their
//   lowest common ancestor (LCA): the deepest node that has both p and q in its subtree.
//   A node counts as part of its own subtree, so if p sits above q, the answer is p.
//
// EXAMPLES
//   root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8  ->  6   2 is left of 6, 8 is right
//   same tree, p = 2, q = 4                            ->  2   4 is below 2, and 2 counts
//                                                              as its own ancestor
//   root = [2,1], p = 2, q = 1                         ->  2
//
// CONSTRAINTS
//   2 <= number of nodes <= 10^5,   -10^9 <= Node.val <= 10^9,   all values unique,
//   p != q,   p and q are both in the tree
//
// INTUITION
//   Searching a BST for a value follows one fixed path from the root. The searches for p
//   and q walk together while both values lie on the same side of the current node. The
//   node where they part ways (one goes left, the other right) - or where one of them is
//   found - is the lowest node above both: the LCA. Any deeper node is on one path only.
//
// APPROACH (as implemented below, in lowestCommonAncestor)
//   1. addNodes(root, p) collects the search path from root to p (root first, p last):
//      add the current node, stop when its val equals the target's, otherwise step left
//      if the current value is bigger, else right. Same for q.
//   2. Walk both lists together up to the shorter length (min). At the first index i
//      where they hold different nodes, index i - 1 was the last shared node -> return
//      pNodes.get(i - 1).
//   3. No difference: one path is a prefix of the other (p is above q or the reverse), so
//      the last node of the shorter path, pNodes.get(i - 1) with i == min, is the LCA.
//
// DRY RUN   root = [6,2,8,0,4,7,9,null,null,3,5]
//   p = 2, q = 8 : pNodes = [6,2], qNodes = [6,8]
//                  i=0 same (6), i=1: 2 != 8 -> return pNodes.get(0) = 6
//   p = 2, q = 4 : pNodes = [6,2], qNodes = [6,2,4], min = 2
//                  i=0 same, i=1 same, loop ends with i = 2 -> return pNodes.get(1) = 2
//   lcaOfBst, p = 2, q = 8: at 6, 2 < 6 but 8 > 6 -> they split -> return 6
//
// APPROACHES IN THIS FILE
//   A. lowestCommonAncestor() - keep both search paths, return the last shared node
//                               - O(h) time, O(h) space   <- the LeetCode method name
//   B. lcaOfBst()             - one pointer walks down until p and q stop being on the
//                               same side - O(h) time, O(1) space
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode is a non-static inner
//   class (only a TreeNode(int x) constructor), so nodes are created through an instance:
//   new LC235LowestCommonAncestorOfABinarySearchTree().new TreeNode(5).
//
// COMPLEXITY   (h = tree height: about log n when balanced, up to n for a chain)
//   A. Time : O(h) - two root-to-node walks, then one pass over at most h + 1 pairs
//      Space: O(h) - the two path lists
//   B. Time : O(h) - one step down per iteration
//      Space: O(1) - only the moving root pointer
//
// GOTCHAS
//   - "A node is its own ancestor": if p is above q the answer is p (example 2). A handles
//     it when the loop runs out, B in the else branch (root.val == p.val is on neither side).
//   - i - 1 is never -1: both paths start with root, so index 0 always matches.
//   - The paths are compared with != (same object), which works because both lists are
//     taken from the same tree.
//
// RELATED
//   - LC 236 Lowest Common Ancestor of a Binary Tree - no value order to steer by, so it
//     has to search both subtrees of every node.
// ============================================================================
public class LC235LowestCommonAncestorOfABinarySearchTree {

    // LeetCode's tree node for this problem (non-static inner class)
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
          val = x;
      }
    }
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }
    // Approach A: the LCA is the last node shared by the root->p and root->q search paths.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pNodes = addNodes(root, p);
        List<TreeNode> qNodes = addNodes(root, q);
        int i = 0;
        int min = Math.min(pNodes.size(), qNodes.size());
        // the paths agree from the root down to the LCA and differ right after it
        while (i < min){
            if(qNodes.get(i) != pNodes.get(i)){
                // first difference -> the previous node was the last shared one
                return pNodes.get(i - 1);
            }
            i++;
        }
        // one path is a prefix of the other: its last node (p or q itself) is the LCA
        return pNodes.get(i - 1);
    }

    // Approach B: walk down while p and q are on the same side of root; the first node
    // where they split (or that is p or q) is the LCA. O(h) time, O(1) space.
    public TreeNode lcaOfBst(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
           if(p.val < root.val && q.val < root.val){
               // both smaller -> the LCA is in the left subtree
               root = root.left;
           }else if (p.val > root.val && q.val > root.val){
               // both bigger -> the LCA is in the right subtree
               root = root.right;
           } else {
               // they split here, or root is p or q -> root is the LCA
               return root;
           }
        }
        // only reached if p or q is not in the tree
        return null;
    }

    // Returns the nodes on the BST search path from dummy (the root) down to node,
    // both ends included.
    public static List<TreeNode> addNodes(TreeNode dummy, TreeNode node){
        List<TreeNode> nodes = new ArrayList<>();
        while(dummy != null){
            nodes.add(dummy);
            if(dummy.val == node.val){
                break;
            }
            // ordinary BST search step
            dummy = (dummy.val > node.val) ? dummy.left : dummy.right;
        }
        return nodes;
    }

}

package Practice.Design;

import java.util.LinkedList;
import java.util.Queue;

// ============================================================================
// LC 297. Serialize and Deserialize Binary Tree   [Hard]
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// Topics: String, Tree, Depth-First Search, Breadth-First Search, Design, Binary Tree
// ============================================================================
//
// PROBLEM
//   Design a codec for binary trees with two methods:
//     - serialize(root)    turn the tree into a String.
//     - deserialize(data)  turn such a String back into a tree.
//   The string format is your choice. The only rule is that deserialize(serialize(root))
//   rebuilds exactly the original tree - same shape, same values. The tree may be empty
//   (root = null), and that case has to survive the round trip too.
//
// EXAMPLES
//   root = [1,2,3,null,null,4,5]  ->  [1,2,3,null,null,4,5]          1
//                                                                   / \
//     the round trip gives back the same tree                      2   3
//                                                                     / \
//                                                                    4   5
//   root = []  ->  []    the empty tree comes back empty
//
// CONSTRAINTS
//   0 <= number of nodes <= 10^4,   -1000 <= Node.val <= 1000
//
// INTUITION
//   The values alone do not fix the shape: "1,2" could mean 2 is the left OR the right
//   child. Writing a marker ("#") for every missing child removes the doubt.
//   In level order (BFS) the nodes are written parent by parent, and each real node's two
//   child slots (left, right) appear later in that same parent order. So a reader can
//   replay it with a queue of parents: take the next parent, the next two tokens are its
//   left and right child, and every real child joins the queue as a future parent.
//
// APPROACH (as implemented below)
//   serialize(root)
//     1. root == null -> return null (see STATUS).
//     2. BFS with a queue that also holds null children. Poll a node:
//          null -> append "#,"
//          real -> append "val," and offer its left and right child, even when null.
//     3. Return the string. It ends with a trailing comma.
//   deserialize(data)
//     4. "" -> null. Split on "," into values, build the root from values[0], queue it.
//     5. Loop: poll a parent; token values[i] is its left child, token values[++i] its
//        right child. A token other than "#" becomes a new node that is linked to the
//        parent and queued.
//     Step 5 is meant to start at i = 1 and build each node from values[i]; the code
//     starts at i = 0 and always uses values[0], so it breaks (see STATUS).
//
// DRY RUN   root = [1,2,3,null,null,4,5]   (checked with a test harness)
//   serialize
//     queue [1]          poll 1 -> "1,"   offer 2, 3
//     queue [2,3]        poll 2 -> "2,"   offer null, null
//     queue [3,#,#]      poll 3 -> "3,"   offer 4, 5
//     queue [#,#,4,5]    poll #, # -> "#,#,"   poll 4, 5 -> "4,5,"   offer 4 nulls
//     queue [#,#,#,#]    -> "#,#,#,#,"
//     result "1,2,3,#,#,4,5,#,#,#,#,"   (11 tokens = 5 nodes + 6 null children)
//   deserialize, as written   (values has 11 tokens, index 0..10)
//     i=0  parent 1        values[0]="1" -> left child,  values[1]="2" -> right child
//          (both get value values[0] = 1: the root token was read twice)
//     i=2, 4, 6, 8 ...     each pass eats 2 tokens
//     i=10 parent ...      values[10]="#", then values[++i] = values[11]
//          -> ArrayIndexOutOfBoundsException
//   deserialize, as intended (i from 1, values[i])
//     parent 1 <- "2","3"   parent 2 <- "#","#"   parent 3 <- "4","5"
//     parent 4 <- "#","#"   parent 5 <- "#","#"   i = 11 -> done, the tree is rebuilt
//
// STATUS
//   Known issue: deserialize() throws on every non-empty input, so no tree is rebuilt:
//     - the loop starts at i = 0, so the root's own token is read again as its left child;
//     - every new node gets Integer.parseInt(values[0]) (the root value) instead of
//       values[i];
//     - there are always 2n + 1 tokens (odd), and each pass reads two of them, so the
//       last pass reads values[values.length]: [1,2,3,null,null,4,5] and even the single
//       node [7] throw ArrayIndexOutOfBoundsException (checked).
//     A fix would start the loop at i = 1 and build nodes from values[i].
//   Known issue: the empty tree fails - serialize(null) returns null, and deserialize(null)
//   then throws a NullPointerException at data.isEmpty().
//   There is no main(), so running the file does nothing. LeetCode names the class Codec,
//   and TreeNode is a non-static inner class here, so test code creates nodes with
//   codec.new TreeNode(1).
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   Time : O(n) - serialize polls each node once and writes 2n + 1 tokens (a tree with n
//          nodes has n + 1 null children); deserialize walks those tokens once
//   Space: O(n) - the output string / values array of 2n + 1 tokens, plus the queue,
//          which holds up to one level (with its nulls)
//
// GOTCHAS
//   - Offer null children too: the "#" markers are what make the shape recoverable.
//   - Use a LinkedList as the queue: it accepts null, ArrayDeque throws on offer(null).
//   - String.split drops trailing empty strings, so the final "," adds no extra token.
//   - Both sides must use the same order: the reader's parent queue must hand out parents
//     in the order serialize wrote them, two tokens per parent.
//   - Values can be negative or several digits long, so tokens need a separator (",").
//
// RELATED
//   - LC 449 Serialize and Deserialize BST - the BST version (this file used to sit in a
//     BST folder). This generic codec works there too, but a BST can be rebuilt from its
//     preorder alone, without "#" markers.
//   - LC 102 Binary Tree Level Order Traversal - the same queue-driven BFS; solved in
//     this repo as LC102BinaryTreeLevelOrderTraversal.java.
// ============================================================================
public class LC297SerializeAndDeserializeBinaryTree {

    // Returns the tree in level order, e.g. "1,2,#,", where "#" marks a missing child.
    public String serialize(TreeNode root) {
        // empty tree: returns null, which deserialize() cannot read back (see STATUS)
        if (root == null)
            return null;
        StringBuilder str = new StringBuilder();
        // LinkedList accepts null elements, so missing children can be queued as well
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode popped = queue.poll();
            // a missing child: write the marker; it has no children to queue
            if (popped == null) {
                str.append("#,");
                continue;
            }
            // a real node: write its value and queue BOTH children, even null ones
            str.append(popped.val).append(",");
            queue.offer(popped.left);
            queue.offer(popped.right);
        }
        return str.toString();
    }

    // Meant to rebuild the tree from serialize()'s string.
    // BROKEN: throws on every non-empty input (see STATUS).
    public TreeNode deserialize(String data) {
        // "" -> empty tree; a null string (serialize(null)) throws here instead
        if (data.isEmpty())
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        // "1,2,#," -> [1, 2, #]  (split drops the trailing empty token)
        String[] values = data.split(",");
        // values[0] is the root; queued nodes are the parents still waiting for children
        TreeNode treeNode = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(treeNode);
        // each pass gives the next parent two tokens: values[i] = left, values[++i] = right
        // BUG: should start at i = 1 - starting at 0 re-reads the root token, and the last
        // pass's ++i runs past the end (ArrayIndexOutOfBoundsException)
        for (int i = 0; i < values.length; i++) {
            TreeNode parent = queue.poll();
            if (!values[i].equals("#")) {
                // BUG: values[0] should be values[i]
                TreeNode left = new TreeNode(Integer.parseInt(values[0]));
                parent.left = left;
                queue.offer(left);

            }
            if (!values[++i].equals("#")) {
                // BUG: values[0] should be values[i]
                TreeNode right = new TreeNode(Integer.parseInt(values[0]));
                parent.right = right;
                queue.offer(right);
            }
        }
        return treeNode;
    }

    // LeetCode supplies TreeNode. Here it is a non-static inner class, so nodes are
    // created through a codec instance: codec.new TreeNode(1).
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

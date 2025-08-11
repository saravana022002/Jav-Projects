package SDE.BinarySearchTree2;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    public String serialize(TreeNode root) {
        if (root == null)
            return null;
        StringBuilder str = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode popped = queue.poll();
            if (popped == null) {
                str.append("#,");
                continue;
            }
            str.append(popped.val).append(",");
            queue.offer(popped.left);
            queue.offer(popped.right);
        }
        return str.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        String[] values = data.split(",");
        TreeNode treeNode = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(treeNode);
        for (int i = 0; i < values.length; i++) {
            TreeNode parent = queue.poll();
            if (!values[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[0]));
                parent.left = left;
                queue.offer(left);

            }
            if (!values[++i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[0]));
                parent.right = right;
                queue.offer(right);
            }
        }
        return treeNode;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

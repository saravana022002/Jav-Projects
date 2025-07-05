package SDE.BinaryTree3;

import java.util.HashMap;
import java.util.Map;

public class BuildTreeForInAndPostOrder {
    public static void main(String[] args) {

    }

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

    public TreeNode buildTree(int[] postOrder, int[] inorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return buildTree(postOrder, 0, postOrder.length - 1, 0, inorder.length - 1, inOrderMap);
    }



    public TreeNode buildTree(int[] postorder, int postStart, int postEnd, int inStart, int inEnd, Map<Integer, Integer> inOrderMap) {
        if(postStart > postEnd || inStart > inEnd){
            return null;
        }
        TreeNode treeNode = new TreeNode(postorder[postEnd]);
        int inRoot = inOrderMap.get(treeNode.val);
        int numsLeft = inRoot - inStart;

        treeNode.left = buildTree(postorder, postStart, postStart + numsLeft - 1 , inStart, inRoot - 1, inOrderMap);
        treeNode.right = buildTree(postorder, postStart + numsLeft, postEnd - 1, inRoot + 1, inEnd, inOrderMap);

        return treeNode;
    }
}

package SDE.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindLCAInBST {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
          val = x;
      }
    }
    public static void main(String[] args) {

    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pNodes = addNodes(root, p);
        List<TreeNode> qNodes = addNodes(root, q);
        int i = 0;
        int min = Math.min(pNodes.size(), qNodes.size());
        while (i < min){
            if(qNodes.get(i) != pNodes.get(i)){
                return pNodes.get(i - 1);
            }
            i++;
        }
        return pNodes.get(i - 1);
    }

    public TreeNode lcaOfBst(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
           if(p.val < root.val && q.val < root.val){
               root = root.left;
           }else if (p.val > root.val && q.val > root.val){
               root = root.right;
           } else {
               return root;
           }
        }
        return null;
    }

    public static List<TreeNode> addNodes(TreeNode dummy, TreeNode node){
        List<TreeNode> nodes = new ArrayList<>();
        while(dummy != null){
            nodes.add(dummy);
            if(dummy.val == node.val){
                break;
            }
            dummy = (dummy.val > node.val) ? dummy.left : dummy.right;
        }
        return nodes;
    }

}

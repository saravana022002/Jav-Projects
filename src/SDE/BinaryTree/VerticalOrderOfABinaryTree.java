package SDE.BinaryTree;

import java.util.*;

public class VerticalOrderOfABinaryTree {
    public static void main(String[] args) {

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        Queue<Tuple> queue = new LinkedList<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        queue.add(new Tuple(0, 0, root));
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
            if(treeNode.left != null){
                queue.add(new Tuple( x - 1, y + 1, treeNode.left));
            }
            if(treeNode.right != null){
                queue.add(new Tuple( x + 1, y + 1, treeNode.right));
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(TreeMap<Integer, PriorityQueue<Integer>> list : map.values()){
            ans.add(new ArrayList<>());
            for (PriorityQueue<Integer> queues : list.values()){
                while(!queues.isEmpty()){
                    list.get(list.size() - 1).add(queues.poll());
                }
            }
        }
        return ans;
    }

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

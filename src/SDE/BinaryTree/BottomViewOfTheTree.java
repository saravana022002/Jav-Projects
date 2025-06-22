package SDE.BinaryTree;

import java.util.*;

public class BottomViewOfTheTree {
    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.right = new Node(6);
        System.out.println(bottomView(root));
    }

    public static ArrayList<Integer> bottomView(Node root) {
        TreeMap<Integer, Integer> horVsNode = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        root.hd = 0;
        horVsNode.put(root.hd, root.data);
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                horVsNode.put(node.hd, node.data);
                if(node.left != null){
                    node.left.hd = node.hd - 1;
                    queue.offer(node.left);
                }
                if(node.right != null){
                    node.right.hd = node.hd + 1;
                    queue.offer(node.right);
                }
            }
        }
        return new ArrayList<>(horVsNode.values());
    }
    public static class Node {
        int data;
        int hd;
        Node left, right;
        public Node(int key)
        {
            data = key;
            hd = Integer.MAX_VALUE;
            left = right = null;
        }
    }
}

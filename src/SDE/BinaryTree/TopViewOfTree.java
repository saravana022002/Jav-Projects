package SDE.BinaryTree;

import java.util.*;

public class TopViewOfTree {
    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.right = new Node(6);
        System.out.println(topView(root));
    }
    public static ArrayList<Integer> topView(Node root) {
        TreeMap<Integer, Integer> horVsNode = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        horVsNode.put(0, root.data);
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                if(!horVsNode.containsKey(pair.hd)) {
                    horVsNode.put(pair.hd, pair.node.data);
                }
                if(pair.node.left != null){
                    queue.offer(new Pair(pair.node.left, pair.hd - 1));
                }
                if(pair.node.right != null){
                    queue.offer(new Pair(pair.node.right, pair.hd + 1));
                }
            }
        }
        return new ArrayList<>(horVsNode.values());
    }
    static class Pair{
        int hd;
        Node node;
        Pair(Node node, int hd)
        {
            this.node = node;
            this.hd = hd;
        }
    }
    public static class Node {
        int data;
        Node left, right;
        public Node(int key)
        {
            data = key;
            left = right = null;
        }
    }
}

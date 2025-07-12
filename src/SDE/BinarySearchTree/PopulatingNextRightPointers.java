package SDE.BinarySearchTree;

import SDE.BinaryTree2.BinaryTreeLevelOrderTraversal;

import java.util.*;

public class PopulatingNextRightPointers {
    public static void main(String[] args) {
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n2 = new Node(2, n4, n5, null);
        Node n3 = new Node(3, n6, n7, null);
        Node root = new Node(1, n2, n3, null);
        connect(root);
    }

    public static Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int queueSize = queue.size();
            Node prev = null;
            for (int i = 0; i < queueSize; i++) {
                Node nude = queue.poll();
                if (prev != null) {
                    prev.next = nude;
                }
                prev = nude;
                if(nude.left != null){
                    queue.offer(nude.left);
                }
                if(nude.right != null){
                    queue.offer(nude.right);
                }

            }
        }
        return root;
    }


    public Node connectitcut(Node root) {
        if (root == null) return null;
        dfs(root);
        return root;
    }

    public static Node dfs(Node root) {
        if(root == null || root.left == null || root.right == null){
            return null;
        }
        root.left.next = root.right;
        if(root.next != null){
            root.right.next = root.next.left;
        }
        dfs(root.left);
        dfs(root.right);
        return root;
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

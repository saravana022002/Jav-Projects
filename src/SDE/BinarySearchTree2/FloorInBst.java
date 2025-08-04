package SDE.BinarySearchTree2;

public class FloorInBst {

    public static void main(String[] args) {

    }


    public static int floor(Node root, int x) {
        int ans = 0;
        while(root != null){
            if(root.val > x){
                root = root.left;
            } else if (root.val < x) {
                ans = root.val;
                root = root.right;
            }
        }
        return ans;
    }

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

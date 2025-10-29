package SDE.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {


static class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

// set neighbors
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        nodeMap = new HashMap<>();
        cloneGraph(node1);
    }

    static Map<Node, Node> nodeMap;
    public static Node cloneGraph(Node node) {
        if(nodeMap.containsKey(node)){
            return nodeMap.get(node);
        }
        Node clone = new Node(node.val);
        nodeMap.put(node, clone);
        for(Node neighbor : node.neighbors){
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}

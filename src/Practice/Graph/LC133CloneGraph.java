package Practice.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ============================================================================
// LC 133. Clone Graph   [Medium]
// https://leetcode.com/problems/clone-graph/
// Topics: Hash Table, Depth-First Search, Breadth-First Search, Graph Theory
// ============================================================================
//
// PROBLEM
//   You get one node of a connected, undirected graph. Every node has an int val and a
//   list of its neighbours. Build a deep copy: a brand-new Node for every original node,
//   wired exactly the same way, and return the copy of the node you were given. No node
//   of the copy may be one of the originals. An empty graph arrives as null -> return null.
//
// EXAMPLES
//   adjList = [[2,4],[1,3],[2,4],[1,3]]  ->  [[2,4],[1,3],[2,4],[1,3]]
//        a square 1-2-3-4-1; the copy has the same shape, made of new objects
//   adjList = [[]]  ->  [[]]      a single node with no neighbours
//   adjList = []    ->  []        empty graph: the given node is null
//
// CONSTRAINTS
//   The number of nodes is in the range [0, 100].
//   1 <= Node.val <= 100, and Node.val is unique for each node.
//   No repeated edges and no self-loops. The graph is connected.
//
// INTUITION
//   Walk the graph with DFS and keep a map "original -> clone". The map does two jobs:
//     - it finds the clone of a neighbour that was already copied, so shared nodes are
//       copied only once, and
//     - it is the visited set, so a cycle (1 -> 2 -> 1) does not recurse forever.
//   The trick is to put a node's clone into the map BEFORE visiting its neighbours; a
//   cycle that comes back to it then finds the (still unfinished) clone and stops.
//
// APPROACH (as implemented below)
//   1. If nodeMap already has this node, return its clone.
//   2. Otherwise create clone = new Node(node.val) and store nodeMap.put(node, clone).
//   3. For every neighbor, add cloneGraph(neighbor) to clone.neighbors.
//   4. Return clone.
//
// DRY RUN   the square from main():  1:[2,4]  2:[1,3]  3:[2,4]  4:[1,3]   (x' = clone)
//   clone(1): new 1', map {1}
//     clone(2): new 2', map {1,2}
//       clone(1): in map -> 1'                       2'.neighbors = [1']
//       clone(3): new 3', map {1,2,3}
//         clone(2): in map -> 2'                     3'.neighbors = [2']
//         clone(4): new 4', map {1,2,3,4}; 1 and 3 are in map -> 4'.neighbors = [1',3']
//                                                    3'.neighbors = [2',4']
//                                                    2'.neighbors = [1',3']
//     clone(4): in map -> 4'                         1'.neighbors = [2',4']
//   returns 1' - every clone has the same neighbour lists as its original
//
// STATUS
//   Known issue: cloneGraph(null) - the empty graph, example 3 - throws a
//   NullPointerException at node.val instead of returning null.
//   Known issue: nodeMap is a static field that only main() creates. Calling cloneGraph()
//   on its own (as LeetCode does) throws a NullPointerException, and the map is never
//   cleared between calls.
//   main() builds the clone but prints nothing.
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   Time : O(V + E) - each node is cloned once, and each neighbour entry is followed
//          once (an undirected edge appears in two lists).
//   Space: O(V) - nodeMap holds V entries and the recursion can go V calls deep.
//
// GOTCHAS
//   - Store the clone in the map before the neighbour loop, not after it, or a cycle
//     recurses forever.
//   - The map is keyed by object identity (Node has no equals/hashCode), which is what
//     we want: two different nodes are never mixed up.
//
// RELATED
//   - LC 138 Copy List with Random Pointer - the same "original -> copy" map trick on a
//     linked list (solved in src/Practice/LinkedList/LC138CopyListWithRandomPointer.java).
//   - LC 1490 Clone N-ary Tree - the same deep copy on a tree, where no visited map is
//     needed because there are no cycles.
// ============================================================================
public class LC133CloneGraph {


// LeetCode's node: a value plus the list of adjacent nodes
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
        // Example 1: the square 1-2-3-4-1; its clone should read [[2,4],[1,3],[2,4],[1,3]].
        // The clone is built correctly, but nothing is printed.
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
        // the map must exist before the first call - cloneGraph() never creates it
        nodeMap = new HashMap<>();
        cloneGraph(node1);
    }

    // original node -> its clone; also serves as the "visited" set of the DFS
    static Map<Node, Node> nodeMap;
    // Returns the clone of node, cloning everything reachable from it (DFS).
    public static Node cloneGraph(Node node) {
        // step 1: already cloned (maybe still in progress higher up) -> reuse; stops cycles
        if(nodeMap.containsKey(node)){
            return nodeMap.get(node);
        }
        // step 2: register the clone BEFORE the neighbours, so a cycle back here finds it
        Node clone = new Node(node.val);
        nodeMap.put(node, clone);
        // step 3: connect the clone to the clones of the neighbours
        for(Node neighbor : node.neighbors){
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}

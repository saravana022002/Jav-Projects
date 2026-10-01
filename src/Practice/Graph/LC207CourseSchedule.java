package Practice.Graph;

// unused import - nothing below refers to it, but the file compiles only while that class exists
import Practice.Stack.LC84LargestRectangleInHistogramPairStack;

import java.util.*;

// ============================================================================
// LC 207. Course Schedule   [Medium]
// https://leetcode.com/problems/course-schedule/
// Topics: Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort,
//         Directed Acyclic Graph
// ============================================================================
//
// PROBLEM
//   There are numCourses courses, 0..numCourses-1. Each pair [a, b] in prerequisites
//   means "you must finish b before you can start a". Return true if there is an order
//   in which every course can be finished, false otherwise.
//   In graph terms: draw an edge b -> a for every pair; the answer is true exactly when
//   this directed graph has no cycle.
//
// EXAMPLES
//   numCourses = 2, prerequisites = [[1,0]]        ->  true    take 0, then 1
//   numCourses = 2, prerequisites = [[1,0],[0,1]]  ->  false   0 and 1 wait on each other
//
// CONSTRAINTS
//   1 <= numCourses <= 2000
//   0 <= prerequisites.length <= 5000,   prerequisites[i].length == 2
//   0 <= a_i, b_i < numCourses,   all pairs are unique
//
// INTUITION
//   A course whose prerequisites are all done (in-degree 0) can be taken right now.
//   Taking it removes one pending prerequisite from every course that depends on it,
//   which may free more courses. Keep going. Courses on a cycle always wait for each
//   other, so their in-degree never drops to 0 and they are never taken. Therefore:
//   every course gets taken  <=>  there is no cycle.
//
// APPROACHES IN THIS FILE
//   A. canFinish()    - Kahn's algorithm (BFS topological sort) - O(V + E)   <- correct
//   B. canFinishDfs() - DFS cycle check with parent tracking. That check is meant for
//                       UNDIRECTED graphs, so it is wrong here (see STATUS). Private, unused.
//
// APPROACH (as implemented below, canFinish)
//   1. Build graph[preReq] -> list of courses that need it, and inDegree[course] = its
//      number of prerequisites.
//   2. Put every course with inDegree 0 into the queue.
//   3. Poll a course, count it in finished, and decrement inDegree of each course that
//      depends on it; a course that reaches 0 joins the queue.
//   4. Return finished == numCourses.
//
// DRY RUN   numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]   (0 -> 1,2 -> 3)
//   graph 0:[1,2]  1:[3]  2:[3]      inDegree [0,1,1,2]      queue [0]
//   poll 0  finished 1   inDegree[1] -> 0, inDegree[2] -> 0   queue [1,2]
//   poll 1  finished 2   inDegree[3] -> 1                     queue [2]
//   poll 2  finished 3   inDegree[3] -> 0                     queue [3]
//   poll 3  finished 4   (no dependents)                      queue []
//   finished == 4 -> true.   With [[1,0],[0,1]] no course starts at 0 -> finished 0 -> false.
//
// STATUS
//   main() is empty, so running the file prints nothing.
//   Known issue (unused method): canFinishDfs() adds every edge in both directions and
//   flags any loop of the UNDIRECTED picture. For the DAG above it finds 0-1-3-2-0 and
//   returns false, but the correct answer is true. canFinish() is not affected.
//   The Pair class and the LC84 import are unused. The code is left unchanged on purpose.
//
// COMPLEXITY   (canFinish)
//   Time : O(V + E) - building the lists touches every course and pair once; each course
//          is polled once and each edge is decremented once.
//   Space: O(V + E) - adjacency lists, plus the inDegree array and the queue.
//
// GOTCHAS
//   - Edge direction: for [a, b] the edge is b -> a (prerequisite first), and it is a's
//     in-degree that goes up.
//   - A directed-graph DFS needs three states (not seen / on the current path / done);
//     only reaching a node that is still on the current path means a cycle. A plain
//     visited[] plus "not my parent" is the undirected test and gives false alarms.
//
// RELATED
//   - LC 210 Course Schedule II - the same Kahn's algorithm, but return the order taken.
//   - LC 261 Graph Valid Tree - an undirected graph, where the parent-tracking DFS of
//     canFinishDfs() is the right cycle test.
// ============================================================================
public class LC207CourseSchedule {
    public static void main(String[] args) {
        // empty - nothing is run or printed (see STATUS)

    }
    // Approach A (correct): Kahn's BFS topological sort. True if all courses can be taken.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // step 1: edge preReq -> course; inDegree = prerequisites not taken yet
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preReq = prerequisite[1];
            graph.get(preReq).add(course);
            inDegree[course]++;
        }

        // step 2: courses with no prerequisites can be taken right away
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        // step 3: take a course, then release the courses that were waiting on it
        int finished = 0;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            finished++;
            for (int element : graph.get(top)) {
                inDegree[element]--;
                if (inDegree[element] == 0) {
                    queue.offer(element);
                }
            }
        }

        // step 4: courses stuck on a cycle were never taken
        return finished == numCourses;
    }

    // Approach B (private, unused, WRONG for directed graphs): undirected cycle detection.
    // Returns false for the DAG [[1,0],[2,0],[3,1],[3,2]] although true is correct.
    private boolean canFinishDfs(int numCourses, int[][] prerequisites) {
        boolean[] vis = new boolean[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // each edge is stored both ways, so the direction of the prerequisite is lost
        for (int[] prerequisite : prerequisites){
            int child = prerequisite[0];
            int parent = prerequisite[1];
            adj.get(parent).add(child);
            adj.get(child).add(parent);
        }

        // a loop in any component -> "cannot finish"
        for (int i = 0; i < numCourses; i++) {
            if(!vis[i]) {
                if (dfs(i, -1, adj, vis)) {
                    return false;
                }
            }
        }
        return true;
    }


    // True if an undirected loop is reachable from node; parent = the node we came from.
    private boolean dfs(int node, int parent, List<List<Integer>> adj, boolean[] vis){
        vis[node] = true;
        for (int element : adj.get(node)){
            if(!vis[element]){
                if(dfs(element, node, adj, vis)) {
                    return true;
                }
            }else if(element != parent){
                // already visited and not where we came from -> closes a loop
                return true;
            }
        }
        return false;
    }

    // unused helper class
    class Pair{
        int parent;
        int child;
        Pair(int parent, int child){
            this.parent = parent;
            this.child = child;
        }
    }
}

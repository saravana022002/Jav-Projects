package SDE.Graph;

import SDE.StackAndQueue.LargestRectangleInHistogram;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {

    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

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

        return finished == numCourses;
    }

    private boolean canFinishDfs(int numCourses, int[][] prerequisites) {
        boolean[] vis = new boolean[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites){
            int child = prerequisite[0];
            int parent = prerequisite[1];
            adj.get(parent).add(child);
            adj.get(child).add(parent);
        }

        for (int i = 0; i < numCourses; i++) {
            if(!vis[i]) {
                if (dfs(i, -1, adj, vis)) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean dfs(int node, int parent, List<List<Integer>> adj, boolean[] vis){
        vis[node] = true;
        for (int element : adj.get(node)){
            if(!vis[element]){
                if(dfs(element, node, adj, vis)) {
                    return true;
                }
            }else if(element != parent){
                return true;
            }
        }
        return false;
    }

    class Pair{
        int parent;
        int child;
        Pair(int parent, int child){
            this.parent = parent;
            this.child = child;
        }
    }
}

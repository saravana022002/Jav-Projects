package Quests.Queue;

import java.util.Deque;
import java.util.LinkedList;

public class CountStudents {
    public static void main(String[] args) {
        int[] students = {1,1,0,0};
        int[] sandwiches = {0,1,0,1};
        countStudents(students, sandwiches);
    }

    public static int countStudents(int[] students, int[] sandwiches) {

        Deque<Integer> que = new LinkedList<>();
        int count = 0;
        int i = 0;
        for (int student : students) {
            que.addLast(student);
        }
        while (!que.isEmpty()){
            if(que.peekFirst() != sandwiches[i]) {
                que.addLast(que.pollFirst());
            }else {
                que.pollFirst();
                i++;
            }
            if(count > 4 * students.length) {
                return que.size();
            }
            count ++;
        }
        return 0;
    }
}

package SDE.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NMeetings {
    public static void main(String[] args) {
        int[] start = {25, 0, 14, 24, 18, 3, 17};
        int[] end = {29, 25, 24, 26, 25, 23, 18};
        maxMeetings(start, end);
    }
    public static int maxMeetings(int start[], int end[]) {
        int size = start.length;
        List<Meeting> meetings = new ArrayList<>();
        for(int i = 0; i < size; i++){
            meetings.add(new Meeting(start[i], end[i], i + 1));
        }

        Collections.sort(meetings, (a, b) -> a.end != b.end ? a.end - b.end : a.start - b.start);

        int count = 1;
        System.out.println(meetings.get(0).pos);
        Meeting compare = meetings.get(0);
        for (int i = 1; i < size; i++) {
            if(meetings.get(i).start > compare.end){
                count ++;
                System.out.println(meetings.get(i).pos);
                compare = meetings.get(i);
            }
        }
        return count;
    }
    static class Meeting {
        int start;
        int end;
        int pos;

        Meeting(int start, int end, int pos){
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
    }
}

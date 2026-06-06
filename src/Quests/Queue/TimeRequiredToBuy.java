package Quests.Queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TimeRequiredToBuy {
    public static void main(String[] args) {
        int[] tickets = {5,1,1,1};
        int k = 0;
        System.out.println(timeRequiredToBuy(tickets, k));
    }

    static class  Pair{
        int key;
        int value;
        Pair(int key, int value){
            this.key = key;
            this.value =  value;
        }
    }
    public static int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Pair> que = new LinkedList<>();
        for (int i = 0 ; i < tickets.length; i++) {
            que.offer(new Pair(i, tickets[i]));
        }
        int count = 0;
        Pair temp = null;
        while (!que.isEmpty() && (que.peek().key != k || (que.peek().key == k && que.peek().value != 1))){
            temp = que.poll();
            if(temp.value > 1){
                temp.value--;
                que.offer(temp);
            }
            count++;
        }
        return count + 1;
    }

}

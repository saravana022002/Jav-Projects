package SDE.DailyChallenge;

import java.util.ArrayList;
import java.util.List;

public class LexicalOrder {
    public static void main(String[] args) {
        lexicalOrder(113);
    }
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(result, i, n);
        }
        return result;
    }

    public static void dfs(List<Integer> result, int curr, int num){
        if(curr > num){
            return;
        }
        result.add(curr);
        for (int digit = 0; digit <= 9; digit++) {
            int next = curr * 10 + digit;
            if(next > num){
                return;
            }
            dfs(result, next, num);
        }
    }
}

package SDE.Recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        partition();
    }

    public static List<List<String>> partition() {
        String s = "aabb";
        List<List<String>> res = new ArrayList<>();
        List<String> part = new ArrayList<>();
        partitionSubStr(s, 0,  part, res);
        System.out.println(res);
        return res;
    }

    static void partitionSubStr(String s, int i, List<String> part, List<List<String>> res){
        if(i >= s.length()){
            res.add(new ArrayList<>(part));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if(isPalindrome(s.substring(i, j + 1))){
                part.add(s.substring(i, j + 1));
                partitionSubStr(s, j + 1, part, res);
                part.remove(part.size() - 1);
            }
        }
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}

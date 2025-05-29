package SDE.MiscProblemsLeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        parenthesis(0, 0, n, new ArrayList<>(), ans);
        return ans;
    }

    private static void parenthesis(int left, int right, int n, List<Character> characters, List<String> ans) {
        if(left == right && left == n){
            String result = characters.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            ans.add(result);
        }

        if (left < n) {
            characters.add('(');
            parenthesis(left + 1, right, n, characters, ans);
            characters.remove(characters.size() - 1);
        }

        if (right < left) {
            characters.add(')');
            parenthesis(left, right + 1, n, characters, ans);
            characters.remove(characters.size() - 1);
        }
    }
}

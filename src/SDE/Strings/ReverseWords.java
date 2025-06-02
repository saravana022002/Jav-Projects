package SDE.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseWords {
    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(reverseWords(s));
    }
    public static String reverseWords(String s) {
        List<String> words = new ArrayList<>();

        int starting = 0;
        boolean isWord = false;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != ' '){
                if(!isWord){
                    starting = i;
                    isWord = true;
                }
            }else {
                if(isWord){
                    words.add(s.substring(starting, i));
                    isWord = false;
                }
            }
        }
        if (isWord) {
            words.add(s.substring(starting));
        }
        Collections.reverse(words);
        return String.join(" ", words);

    }
}

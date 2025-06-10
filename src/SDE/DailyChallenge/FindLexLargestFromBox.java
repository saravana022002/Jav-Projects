package SDE.DailyChallenge;

public class FindLexLargestFromBox {


    public static void main(String[] args) {
        String word = "aann";
        int numFriends = 2;
        System.out.println(answerString(word, numFriends));
    }
    public static String answerString(String word, int numFriends) {
        int strLen = word.length();
        int maxLength = strLen - numFriends + 1;
        String max = word.substring(0, maxLength);
        for (int i = 1; i < strLen; i++) {
            int last = i + maxLength;
            if(strLen < last){
                last = strLen;
            }
            word.indexOf(max);
            String substring = word.substring(i, last);
            if(substring.compareTo(max) > 0 ){
                max = substring;
            }
        }
        return max;
    }
}

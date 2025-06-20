package SDE.DailyChallenge;

import java.util.List;

public class MaxDiffRemappingaDigit {
    public static void main(String[] args) {
        int num = 90;
        System.out.println(minMaxDifference(num));
    }

    public static int minMaxDifference(int num) {
        return maxNum(num) - minNum(num);
    }

    public static int maxNum(int num){
        String strNum = String.valueOf(num);
        char[] characters = strNum.toCharArray();
        char mapper = 's';
        int j = 0;
        for (j = 0; j < characters.length; j++) {
            if(characters[j] != '9'){
                mapper = characters[j];
                characters[j] = '9';
                break;
            }
        }

        for (int i = j; i < characters.length; i++) {
            if(characters[i] == mapper){
                characters[i] = '9';
            }
        }
        int number = 0;
        for (char c : characters) {
            number = number * 10 + (c - '0');
        }
        return number;
    }

    public static int minNum(int num){
        String strNum = String.valueOf(num);
        char[] characters = strNum.toCharArray();
        char mapper = 's';
        int j = 0;
        for (j = 0; j < characters.length; j++) {
            if(characters[j] != '0'){
                mapper = characters[j];
                characters[j] = '0';
                break;
            }
        }

        for (int i = j; i < characters.length; i++) {
            if(characters[i] == mapper){
                characters[i] = '0';
            }
        }
        int number = 0;
        for (char c : characters) {
            number = number * 10 + (c - '0');
        }
        return number;
    }

}

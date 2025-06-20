package SDE.DailyChallenge;

public class MaxDiffInteger {
    public static void main(String[] args) {
        int num = 1101057;
        System.out.println(maxDiff(num));
    }

    public static int maxDiff(int num) {
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

        if(characters[0] != '1'){
            mapper = characters[0];
            for (int i = 0; i < characters.length; i++) {
                if (characters[i] == mapper) {
                    characters[i] = '1';
                }
            }
        }else {
            for (j = 1; j < characters.length; j++) {
                if (characters[j] != '0' && characters[j] != characters[0]) {
                    mapper = characters[j];
                    characters[j] = '0';
                    break;
                }
            }
            for (int i = j; i < characters.length; i++) {
                if (characters[i] == mapper) {
                    characters[i] = '0';
                }
            }
        }
        int number = 0;
        for (char c : characters) {
            number = number * 10 + (c - '0');
        }
        return number;
    }
}

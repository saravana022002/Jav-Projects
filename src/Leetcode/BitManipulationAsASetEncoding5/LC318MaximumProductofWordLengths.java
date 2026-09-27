package Leetcode.BitManipulationAsASetEncoding5;

public class LC318MaximumProductofWordLengths {
    public static void main(String[] args) {
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
        System.out.println(maxProduct(words));
    }

    public static int maxProduct(String[] words) {
        int[][] strEnc = new int[words.length][2];
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            for (int j = 0; j < str.length(); j++) {
                strEnc[i][1] |=  1 << (str.charAt(j) - 'a');
            }
            strEnc[i][0] = words[i].length();
        }

        int max = 0;
        for (int i = 0; i < strEnc.length; i++) {
            for (int j = i + 1; j < strEnc.length; j++) {
                if((strEnc[i][1] & strEnc[j][1]) == 0){
                    max = Math.max(max, strEnc[i][0] * strEnc[j][0]);
                }
            }
        }
        return max;
    }
}

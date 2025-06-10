package SDE.Strings;

public class RepeatedStringMatch {
    public static void main(String[] args) {
        String a = "abc";
        String b = "cabcabca";
        System.out.println(repeatedStringMatch(a, b));
    }

    public static int repeatedStringMatch(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();

        int repeatTimes = (lenB + lenA - 1) / lenA;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= repeatTimes; i++) {
            stringBuilder.append(a);
        }
        String text = stringBuilder.toString();


        long hash = calculateHash(b);
        long hashComp = calculateHash(text.substring(0, lenB));

        for (int i = 0; i <= text.length() - lenB; i++) {
            if(hash == hashComp){
                if(b.equals(text.substring(i, i + lenB))){
                    return repeatTimes;
                }
            }
            if(i < text.length() - lenB) {
                hashComp = updateHash(hashComp, text.charAt(i), text.charAt(i + lenB), lenB);
            }
        }

        repeatTimes++;
        stringBuilder.append(a);
        text = stringBuilder.toString();
        hashComp = calculateHash(text.substring(0, lenB));
        for (int i = 0; i <= text.length() - lenB; i++) {
            if(hash == hashComp){
                if(b.equals(text.substring(i, i + lenB))){
                    return repeatTimes;
                }
            }
            if(i < text.length() - lenB) {
                hashComp = updateHash(hashComp, text.charAt(i), text.charAt(i + lenB), lenB);
            }
        }
        return -1;
    }


    public static final int PRIME = 101;
    private static long calculateHash(String a){
        long hash = 0;
        for (int i = 0; i < a.length(); i++) {
            hash = (long) (hash + a.charAt(i) * Math.pow(PRIME, i));
        }
        return hash;
    }
    private static long updateHash(long prevHash, Character oldChar, Character newChar, int pattern){
        long newHash = (prevHash - oldChar) / PRIME;
        newHash = (long) (newHash + newChar * Math.pow(PRIME, pattern - 1));
        return newHash;
    }

}

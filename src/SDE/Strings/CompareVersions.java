package SDE.Strings;

public class CompareVersions {
    public static void main(String[] args) {
        String version1 = "1.01";
        String version2 = "1.001";
        System.out.println(compareVersion(version1, version2));
    }
    public static int compareVersion(String version1, String version2) {
        String[] ver1strs = version1.split("\\.");
        String[] ver2strs = version2.split("\\.");

        int num1;
        int num2;
        for (int i = 0; i < Math.max(ver1strs.length, ver2strs.length); i++) {
            num1 = i < ver1strs.length ? Integer.parseInt(ver1strs[i]) : 0;
            num2 = i < ver2strs.length ? Integer.parseInt(ver2strs[i]) : 0;
            if(num1 < num2){
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }
        return 0;
    }
}

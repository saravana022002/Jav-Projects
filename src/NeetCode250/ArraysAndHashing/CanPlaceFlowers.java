package NeetCode250.ArraysAndHashing;

public class CanPlaceFlowers {
    public static void main(String[] args) {
        int[] flowerbed = {0};
        int n = 1;
        System.out.println(canPlaceFlowers(flowerbed, n));
    }
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

        if(flowerbed.length == 1 && n == 1){
            return flowerbed[0] == 0;
        }

        for (int i = 0; i < flowerbed.length; i++) {
            if(n == 0){
                return true;
            }
            if(flowerbed[i] == 1){
               continue;
            }
            if (i > 0 && i < flowerbed.length - 1) {
                if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    n--;
                    flowerbed[i] = 1;
                }
            }else if(i == 0 && flowerbed[i + 1] == 0){
                n--;
                flowerbed[i] = 1;
            }else if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0){
                n--;
                flowerbed[i] = 1;
            }
        }
        return n == 0;
    }
}

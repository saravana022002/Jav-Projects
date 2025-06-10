package Contest158;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxSumDistinctTriplet {
    public static void main(String[] args) {
        int[] x = {1,2,1,2};
        int[] y = {4,5,6,7};
        System.out.println(maxSumDistinctTriplet(x, y));
    }
    public static int maxSumDistinctTriplet(int[] x, int[] y) {
        List<NumStore> stores = new ArrayList<>();
        for (int i = 0; i < y.length; i++) {
            stores.add(new NumStore(x[i], y[i], i));
        }

        stores.sort(Comparator.comparingInt((NumStore a) -> a.right).reversed());
        int x1 = 0, y1 = 0, z1 = 0;
        int x2 = 0, y2 = 0, z2 = 0;
        for(NumStore store : stores){
            if(x1 == 0){
                x1 = store.left;
                x2 = store.right;
                continue;
            }
            if(y1 == 0 && store.left != x1){
                y1 = store.left;
                y2 = store.right;
                continue;
            }
            if(z1 == 0 && store.left != y1 && store.left != x1){
                z1 = store.left;
                z2 = store.right;
                continue;
            }

            if(z2 != 0 && y2 != 0 && x2 != 0){
                break;
            }
        }

        if(z2 == 0 || y2 == 0 || x2 == 0){
            return -1;
        }

        return x2 + y2 + z2;

    }

    static class NumStore{
        int left;
        int right;
        int index;
        NumStore(int left, int right, int index){
            this.left = left;
            this.right = right;
            this.index = index;
        }
    }
}

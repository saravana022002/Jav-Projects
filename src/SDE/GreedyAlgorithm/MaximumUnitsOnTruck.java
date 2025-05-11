package SDE.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumUnitsOnTruck {
    public static void main(String[] args) {
        int[][] boxTypes = {{1,3},{2,2},{3,1}};
        int truckSize = 4;
        System.out.println(maximumUnits(boxTypes, truckSize));
    }

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));
        int maxProfit = 0;
        for(int[] boxInfo : boxTypes){
            if(truckSize > 0 && boxInfo[0] < truckSize ){
                truckSize = truckSize - boxInfo[0];
                maxProfit = maxProfit + boxInfo[0] * boxInfo[1];
            }else {
                maxProfit = maxProfit + truckSize * boxInfo[1];
                truckSize = 0;
            }
        }
        return maxProfit;
    }
    static class BoxInfo{
        int numberOfBoxes;
        int numberOfUnitsPerBox;
        BoxInfo(int numberOfBoxes, int numberOfUnitsPerBox){
            this.numberOfBoxes = numberOfBoxes;
            this.numberOfUnitsPerBox = numberOfUnitsPerBox;
        }
    }
}

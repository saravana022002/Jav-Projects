package Leetcode.CountingStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class LC380RandomizedSet {
    public static void main(String[] args) {

    }

    Map<Integer, Integer> map;
    ArrayList<Integer> arr;
    public LC380RandomizedSet() {
        this.map = new HashMap<>();
        this.arr = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(this.map.get(val) == null) {
            this.arr.add(val);
            this.map.put(val, this.arr.size() - 1);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if(this.map.get(val) != null) {
            int elementIndex = this.map.get(val);
            int lastIndex = this.arr.size() - 1;

            this.arr.set(elementIndex, this.arr.get(lastIndex));
            this.map.put(this.arr.get(lastIndex), elementIndex);

            this.map.remove(val);
            this.arr.remove(lastIndex);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, this.arr.size());
        return this.arr.get(randomNum);
    }

}

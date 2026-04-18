package NeetCode250.ArraysAndHashing;

import java.util.Arrays;

class CustomHashSet {

    static class  MyHashSet {

        int[] hashtable;
        public MyHashSet() {
            this.hashtable = new int[(int) Math.pow(10, 6) + 5];
            Arrays.fill(this.hashtable, -1);
        }

        public void add(int key) {
            this.hashtable[key + 2]  = key;
        }

        public void remove(int key) {
            this.hashtable[key + 2]  = -1;
        }

        public boolean contains(int key) {
            return this.hashtable[key + 2] != -1;
        }
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1000000);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // return False, (already removed)
    }
}
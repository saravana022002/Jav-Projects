package Practice.Design;

import java.util.Arrays;

// ============================================================================
// LC 705. Design HashSet   [Easy]
// https://leetcode.com/problems/design-hashset/
// Topics: Array, Hash Table, Linked List, Design, Hash Function
// ============================================================================
//
// PROBLEM
//   Build a set of non-negative ints from scratch, without Java's HashSet, HashMap or any
//   other ready-made hash table. Three operations are needed:
//     - add(key)        put key in the set (adding it again changes nothing)
//     - remove(key)     take key out; if it is not there, do nothing
//     - contains(key)   true if key is in the set right now, otherwise false
//
// EXAMPLES
//   new MyHashSet()
//   add(1), add(2)
//   contains(1)  -> true
//   contains(3)  -> false     3 was never added
//   add(2)                    2 is already inside, nothing changes
//   contains(2)  -> true
//   remove(2)
//   contains(2)  -> false     it was taken out
//   Output: [null, null, null, true, false, null, true, null, false]
//
// CONSTRAINTS
//   0 <= key <= 10^6
//   At most 10^4 calls will be made to add, remove, and contains.
//
// INTUITION
//   A general hash set squeezes huge keys into a small table with a hash function and then
//   has to cope with collisions (two keys landing in one bucket). Here the keys are small
//   and bounded - 0..10^6 - so all of that can be skipped: give EVERY possible key its own
//   slot and use the key as the array index ("direct addressing"). Two keys can never
//   share a slot, so there are no collisions to handle.
//   The slot holds the key itself and -1 means "empty". Keys are >= 0, so a stored key is
//   never mistaken for the -1 marker.
//
// APPROACH (as implemented below)
//   1. Constructor: allocate hashtable with 10^6 + 5 slots and fill every slot with -1.
//   2. add(key):       hashtable[key + 2] = key.   A second add rewrites the same value.
//   3. remove(key):    hashtable[key + 2] = -1.    Removing a missing key changes nothing.
//   4. contains(key):  hashtable[key + 2] != -1.
//   The "+ 2" just shifts every key two slots to the right. It is harmless but not needed
//   (index key would work too). The highest slot used is 10^6 + 2 and the array has room.
//
// DRY RUN   what main() runs (slot = key + 2; main() does not print the results)
//   start         all 1000005 slots are -1
//   add(1000000)  slot 1000002 = 1000000    (the last valid slot is 1000004)
//   add(2)        slot 4 = 2
//   contains(1)   slot 3 is -1  -> false    (the LC example adds 1 first, main() adds 10^6)
//   contains(3)   slot 5 is -1  -> false
//   add(2)        slot 4 = 2 again, no change
//   contains(2)   slot 4 is 2   -> true
//   remove(2)     slot 4 = -1
//   contains(2)   slot 4 is -1  -> false
//
// STATUS
//   Correct for keys 0..10^6: key + 2 stays inside the array and a stored key is never -1
//   (the LC example was replayed and checked).
//   main() replays the LC example but begins with add(1000000) instead of add(1), and it
//   prints nothing. With that main() the four contains() calls give false, false, true,
//   false. The trailing "// set = ..." / "// return ..." notes are LeetCode's own
//   annotations, so they only fit the original call sequence.
//   LeetCode expects a top-level class MyHashSet; here it is a static nested class of the
//   package-private class LC705DesignHashSet - move it out to submit.
//
// COMPLEXITY
//   Time : add / remove / contains are O(1) - a single array access each.
//          The constructor is O(M) with M = 10^6, because of Arrays.fill.
//   Space: O(M) - one int per possible key (about 4 MB), however few keys are stored.
//
// GOTCHAS
//   - This only works because the key range is small and known. For keys from an
//     unbounded range you need a hash function plus buckets (chaining).
//   - Keys outside the contract break it: -1 would look absent (it equals the marker) and
//     a key above 10^6 + 2 runs off the end of the array.
//
// RELATED
//   - LC 706 Design HashMap - the same design with a value stored for each key.
//   - LC 1206 Design Skiplist - another container built from scratch, but ordered and
//     far harder than this one.
// ============================================================================
class LC705DesignHashSet {

    // Direct-address table: slot key + 2 holds the key itself, -1 means "not in the set".
    static class  MyHashSet {

        int[] hashtable;
        public MyHashSet() {
            // 10^6 + 5 slots: the largest key (10^6) maps to slot 10^6 + 2, which fits
            this.hashtable = new int[(int) Math.pow(10, 6) + 5];
            // every slot starts empty; real keys are >= 0, so -1 can never be a key
            Arrays.fill(this.hashtable, -1);
        }

        // store the key in its own slot; adding it twice rewrites the same value
        public void add(int key) {
            this.hashtable[key + 2]  = key;
        }

        // put the "empty" marker back; harmless if the key was never there
        public void remove(int key) {
            this.hashtable[key + 2]  = -1;
        }

        // the key is in the set exactly when its slot is not the empty marker
        public boolean contains(int key) {
            return this.hashtable[key + 2] != -1;
        }
    }

    public static void main(String[] args) {
        // The LC example, except the first add is 1000000 (the largest allowed key) rather
        // than 1. Nothing is printed. The four contains() calls return: false, false,
        // true, false. The trailing notes are LeetCode's originals for the first sequence.
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
package SDE.StackAndQueue;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    int capacity;
    int minFrequency;
    int curSize;

    Map<Integer, DoubleLinkedList> freqMap;
    Map<Integer, DLLNode> cache;

    public LFUCache(int capacity){
        this.capacity = capacity;
        this.curSize = 0;
        this.minFrequency = 0;

        this.freqMap = new HashMap<>();
        this.cache = new HashMap<>();
    }

    public int get(int key){
        DLLNode curr = cache.get(key);
        if(curr == null){
            return -1;
        }
        updateNode(curr);
        return curr.value;
    }

    public void put(int key, int value){
        DLLNode curr = cache.get(key);
        if(curr != null){
            curr.value = value;
            updateNode(curr);
        }else {
            curSize++;
            DLLNode newNode = new DLLNode(key, value);
            if(curSize > capacity){
                DoubleLinkedList list = freqMap.get(minFrequency);
                DLLNode deletableNode = list.tail.prev;
                list.removeNode(deletableNode);
                cache.remove(deletableNode.key);
                curSize --;
            }
            minFrequency = 1;
            DoubleLinkedList currList = freqMap.getOrDefault(1, new DoubleLinkedList());
            currList.addNode(newNode);
            freqMap.put(1, currList);
            cache.put(key, newNode);
        }
    }

    private void updateNode(DLLNode curr) {
        int currFreq = curr.frequency;
        DoubleLinkedList currList = freqMap.get(currFreq);
        currList.removeNode(curr);
        if(currFreq == minFrequency  && currList.size == 0 ){
            minFrequency++;
        }
        curr.frequency++;
        DoubleLinkedList newList = freqMap.getOrDefault(curr.frequency, new DoubleLinkedList());
        newList.addNode(curr);
        freqMap.put(curr.frequency, newList);
    }


    class DoubleLinkedList{
        int size;
        DLLNode tail;
        DLLNode head;
        public DoubleLinkedList(){
            this.size = 0;
            this.tail = new DLLNode(0, 0);
            this.head = new DLLNode(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public void addNode(DLLNode node){
            DLLNode nextNode = head.next;
            node.next = nextNode;
            node.prev = head;
            nextNode.prev = node;
            head.next = node;
            size++;
        }

        public void removeNode(DLLNode node){
            DLLNode prevNode = node.prev;
            DLLNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
    }
    class DLLNode{
        int key;
        int value;
        int frequency;
        DLLNode next;
        DLLNode prev;
        public DLLNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
            this.next = null;
            this.prev = null;
        }
    }
}

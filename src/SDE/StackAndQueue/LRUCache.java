package SDE.StackAndQueue;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {



    public static void main(String[] args) {
        LRUCache obj = new LRUCache(4);
        int param_1 = obj.get(4);
        obj.put(4,2);
    }


    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public int get(int key) {

        if (!map.containsKey(key)) return -1;
        // deleting a node
        Node temp = map.get(key);
        remove(temp);
        // inserting in after head
        insertAfterHead(temp);
        return temp.value;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAfterHead(node);
        }else {
            if (map.size() == capacity) {
                // Remove LRU node from list and map
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertAfterHead(newNode);
            map.put(key, newNode);
        }
    }


    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAfterHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }



    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}

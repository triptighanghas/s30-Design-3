//TC: O(1)
//SC: O(capacity)
//approach: double linkedlist and hashing

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Node head, tail;
    Map<Integer, Node> map;
    int capacity, count;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (null == node) return -1;
        update(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (null == node){
            node = new Node(key, value);
            map.put(key, node);
            add(node);
            ++count;
        }else {
            node.value = value;
            update(node);
        }

        if (count > capacity){
            Node deleteNode = tail.prev;
            remove(deleteNode);
            map.remove(deleteNode.key);
            --count;
        }
    }

    public void update(Node n){
        remove(n);
        add(n);
    }

    public void remove(Node node) {
        Node before = node.prev;
        Node after = node.next;
        before.next = after;
        after.prev = before;
    }

    public void add(Node node){
        Node after= head.next;
        head.next=node;
        node.prev=head;
        node.next=after;
        after.prev=node;
    }
}

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    Node() {
        this(0, 0);
    }
}

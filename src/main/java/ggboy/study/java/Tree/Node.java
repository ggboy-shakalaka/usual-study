package ggboy.study.java.Tree;

public class Node<K, V> {
    private final int hash;
    private final K key;
    private V value;
    private Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public int getHash() {
        return this.hash;
    }

    public Node<K, V> getNext() {
        return this.next;
    }

    public V addNode(Node<K, V> node) {
        V returnValue = null;
        if (this.eq(node.key)) {
            returnValue = this.value;
            this.value = node.value;
        } else {
            if (this.next == null) {
                this.next = node;
            } else {
                return this.next.addNode(node);
            }
        }
        return returnValue;
    }

    public V getValue(K key) {
        if (this.eq(key)) {
            return this.value;
        } else {
            if (this.next == null) {
                return null;
            } else {
                return this.next.getValue(key);
            }
        }
    }

    public V remove(K key) {
        if (this.next == null) {
            return null;
        }
        V value = null;
        if (this.next.eq(key)) {
            value = this.next.value;
            this.next = this.next.next;
            return value;
        } else {
            return this.next.remove(key);
        }
    }
    
    public int size(int length) {
    	if (next != null) {
    		length = this.next.size(length);
    	}
    	
    	return ++length;
    }
    
    public String toString() {
    	return this.key + ":" + this.value;
    }

    public boolean eq(K key) {
        return this.key.equals(key);
    }
}
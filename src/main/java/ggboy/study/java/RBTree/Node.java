package ggboy.study.java.RBTree;

public class Node<K, V> {
    private int hash;
    private Entry<K, V> current;
    private Node<K, V> left;
    private Node<K, V> right;
    private Node<K, V> parent;
    private boolean color = true;   // default red

    Node(int hash, Entry<K, V> entry) {
        this.current = entry;
        this.hash = hash;
    }

    void setLeft(Node<K, V> tree) {
        setParent(tree, this);
        this.left = tree;
    }

    Node<K, V> getLeft() {
        return this.left;
    }

    void setRight(Node<K, V> tree) {
        setParent(tree, this);
        this.right = tree;
    }

    Node<K, V> getRight() {
        return this.right;
    }

    void setParent(Node<K, V> tree) {
        this.parent = tree;
    }

    Node<K, V> getParent() {
        return this.parent;
    }

    int getHash() {
        return this.hash;
    }

    boolean color() {
        return this.color;
    }

    void color(boolean color) {
        this.color = color;
    }

    V putEntry(K key, V value) {
        if (this.current == null) {
            this.current = new Entry<K, V>(key, value);
            return null;
        }

        Entry<K, V> entry = this.current;
        while (entry != null) {
            if (entry.eq(key)) {
                V tempValue = entry.value;
                entry.value = value;
                return tempValue;
            }

            if (entry.next == null) {
                entry.next = new Entry<K, V>(key, value);;
                return null;
            }
            entry = entry.next;
        }

        return null;
    }

    V getValue(K key) {
        Entry<K, V> entry = this.current;
        while (entry != null) {
            if (entry.eq(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public String toString() {
        Entry<K, V> node = this.current;
        StringBuilder sb = new StringBuilder("");
        while (node != null) {
            sb.append(node.toString());
            node = node.next;
        }
        return sb.toString();
    }

    // static method --------------------------------------

    static final <K, V> void setParent(Node<K, V> child, Node<K, V> parent) {
        if (child != null) {
            child.parent = parent;
        }
    }

    static class Entry<K, V> {
        final K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        boolean eq(K key) {
            if (this.key == null) {
                return this.key == key;
            }
            return this.key.equals(key);
        }

        public String toString() {
            return this.key + ":" + this.value;
        }
    }
}

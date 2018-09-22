package ggboy.study.java.Tree;

public class SelfMap<K, V> {

    private final Tree<K, V> tree = new Tree<K, V>();

    public V put(K key, V value) {
        return tree.addTree(new Node<K, V>(key, value));
    }

    public V get(K key) {
        return tree.get(key);
    }

    public V remove(K key) {
        return tree.remove(key);
    }
    
    public int size() {
    	return tree.size();
    }
}

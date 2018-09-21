package mustry.study.consensus;

import java.util.Map;

public class Data<K, V> {
	private Map<K, V> map;
	private long count;
	
	public Data(Map<K, V> data) {
		this.map = data;
	}
	
	public long getCount() {
		return count;
	}
	
	public boolean contains(V value) {
		if (map == null || map.size() == 0)
			return false;

		return map.values().contains(value);
	}
	
	public void put(K key, V value) {
		map.put(key, value);
		count++;
	}
	
	public void remove(K key) {
		map.remove(key);
		count++;
	}
	
	public int size() {
		return map.size();
	}
	
	public boolean equals(Data<K, V> data) {
		if (data == null)
			return false;
		return map.equals(data.map) && count == data.count;
	}
}
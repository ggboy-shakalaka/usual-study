package mustry.study.consensus;

import mustry.common.exception.CommonUtilException;
import mustry.common.utils.bean.BeanUtil;

public abstract class Consensus<K, V> {
	protected Data<K, V> data;
	private V value;

	public void startRequest() {
		while (true) {
			request(null);
			break;
		}
	}

	private void request(Consensus<K, V> consensus) {
		Data<K, V> respData = consensus.read();
		if (respData == null || data.getCount() > respData.getCount())
			return;
		
		if (respData.equals(data)) {
			return;
		}

		if (!respData.contains(value))
			respData.put(getKeyName(value), value);
		data = respData;
	}

	public Data<K, V> read() {
		try {
			return BeanUtil.deepCloneBean(data);
		} catch (CommonUtilException e) {
			return null;
		}
	}

	protected abstract K getKeyName(V value);
}

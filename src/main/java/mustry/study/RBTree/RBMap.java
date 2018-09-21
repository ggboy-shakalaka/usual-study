package mustry.study.RBTree;

public class RBMap<K, V> {
	private Node<K, V> tree;
	private final static boolean RED = true;
	private final static boolean BLACK = false;
	private int size;

	public V get(K key) {
		Node<K, V> node = this.tree;
		int hash = hash(key);
		while (node != null) {
			if (hash == node.getHash()) {
				return node.getValue(key);
			} else if (hash < node.getHash()) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		return null;
	}

	V put(K key, V value) {
		int hash = hash(key);
		if (this.tree == null) {
			this.tree = new Node<K, V>(hash, new Node.Entry<K, V>(key, value));
			size += 1;
			fixAfterInsert(this.tree);
			return null;
		}

		Node<K, V> node = this.tree;
		while (node != null) {
			if (hash == node.getHash()) {
				return node.putEntry(key, value);
			} else if (hash < node.getHash()) {
				if (node.getLeft() != null) {
					node = node.getLeft();
					continue;
				}

				node.setLeft(new Node<K, V>(hash, new Node.Entry<K, V>(key, value)));
				size += 1;
				fixAfterInsert(node.getLeft());
				break;
			} else {
				if (node.getRight() != null) {
					node = node.getRight();
					continue;
				}

				node.setRight(new Node<K, V>(hash, new Node.Entry<K, V>(key, value)));
				size += 1;
				fixAfterInsert(node.getRight());
				break;
			}
		}
		return null;
	}

	/**
	 * O O \ \ A 左旋 B /\ /\ 1 B --> A 3 /\ /\ 2 3 1 2
	 */
	private void leftRotate(Node<K, V> tree) {
		if (rightOf(tree) == null) {
			return;
		}
		Node<K, V> right = rightOf(tree);
		// 转换父子节点的父节点
		changeParent(tree, right);
		// 将A的右节点设置为B的左节点
		tree.setRight(leftOf(right));
		// 将B的左节点设置为A
		right.setLeft(tree);
	}

	/**
	 * O O \ \ A 右旋 B /\ /\ B 1 --> 2 A /\ /\ 2 3 3 1
	 */
	private void rightRotate(Node<K, V> tree) {
		if (leftOf(tree) == null) {
			return;
		}
		Node<K, V> left = leftOf(tree);
		changeParent(tree, left);
		tree.setLeft(rightOf(left));
		left.setRight(tree);
	}

	/**
	 * 交换父子节点的父节点，并修改祖父节点对父节点的引用
	 * 
	 * @param parent
	 * @param child
	 */
	private void changeParent(Node<K, V> parent, Node<K, V> child) {
		// 修改父节点引用
		if (parentOf(parent) == null) {
			// 为空说明为根节点
			this.tree = child;
			child.setParent(null);
		} else {
			if (leftOf(parentOf(parent)) == parent) {
				parentOf(parent).setLeft(child);
			} else {
				parentOf(parent).setRight(child);
			}
		}
	}

	private void fixAfterInsert(Node<K, V> node) {
		while (node != null) {
			// 父节点为空说明为根节点
			if (parentOf(node) == null) {
				node.color(BLACK);
				break;
			}
			// 父节点为黑色，则正常插入
			if (colorOf(parentOf(node)) == BLACK) {
				break;
			}
			// 叔叔节点为红色，重新涂色并将祖父节点作为当前节点添加至循环
			if (colorOf(uncle(node)) == RED) {
				setColor(parentOf(node), BLACK);
				setColor(uncle(node), BLACK);
				setColor(parentOf(parentOf(node)), RED);
				node = parentOf(parentOf(node));
				continue;
			}

			// 父节点为左子节点 --> 旋转
			if (leftOf(parentOf(parentOf(node))) == parentOf(node)) {
				// 当前节点为右子节点 --> 旋转
				if (node == rightOf(parentOf(node))) {
					node = parentOf(node);
					leftRotate(node);
				}
				setColor(parentOf(node), BLACK);
				setColor(parentOf(parentOf(node)), RED);
				rightRotate(parentOf(parentOf(node)));
			} else if (rightOf(parentOf(parentOf(node))) == parentOf(node)) {
				// 当前节点为左子节点 --> 旋转
				if (node == leftOf(parentOf(node))) {
					node = parentOf(node);
					rightRotate(node);
				}
				setColor(parentOf(node), BLACK);
				setColor(parentOf(parentOf(node)), RED);
				leftRotate(parentOf(parentOf(node)));
			}

			break;
		}
	}

	int size() {
		return this.size;
	}

	// static method --------------------------------------

	private static boolean colorOf(Node<?, ?> node) {
		return node == null ? BLACK : node.color();
	}

	private static void setColor(Node<?, ?> node, boolean color) {
		if (node != null) {
			node.color(color);
		}
	}

	private static <K, V> Node<K, V> uncle(Node<K, V> node) {
		return parentOf(node) == leftOf(parentOf(parentOf(node))) ? rightOf(parentOf(parentOf(node)))
				: leftOf(parentOf(parentOf(node)));
	}

	private static <K, V> Node<K, V> leftOf(Node<K, V> node) {
		return node == null ? null : node.getLeft();
	}

	private static <K, V> Node<K, V> rightOf(Node<K, V> node) {
		return node == null ? null : node.getRight();
	}

	private static <K, V> Node<K, V> parentOf(Node<K, V> node) {
		return node == null ? null : node.getParent();
	}

	private static int hash(Object key) {
		return key.hashCode();
	}
}
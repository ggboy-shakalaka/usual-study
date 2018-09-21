package mustry.study.Tree;

public class Tree<K, V> {
	private Node<K, V> current;
	private Tree<K, V> left;
	private Tree<K, V> right;
	private Tree<K, V> parent;

	Tree() {
	}

	public Tree(Tree<K, V> parent, Node<K, V> node) {
		this.parent = parent;
		this.current = node;
	}

	public V addTree(Node<K, V> node) {
		if (this.current == null) {
			this.current = node;
			return null;
		}

		if (this.current.getHash() == node.getHash()) {
			return this.current.addNode(node);
		} else if (this.current.getHash() > node.getHash()) {
			if (this.left == null) {
				this.left = new Tree<K, V>(this, node);
			} else {
				return this.left.addTree(node);
			}
		} else if (this.current.getHash() < node.getHash()) {
			if (this.right == null) {
				this.right = new Tree<K, V>(this, node);
			} else {
				return this.right.addTree(node);
			}
		}

		return null;
	}

	public V get(K key) {
		if (this.current == null) {
			return null;
		}

		if (this.current.getHash() == key.hashCode()) {
			return this.getValue(key);
		} else if (this.current.getHash() > key.hashCode()) {
			if (this.left == null) {
				return null;
			} else {
				return this.left.get(key);
			}
		} else if (this.current.getHash() < key.hashCode()) {
			if (this.right == null) {
				return null;
			} else {
				return this.right.get(key);
			}
		}

		return null;
	}

	public int size() {
		if (this.current == null) {
			return 0;
		}

		return this.size(0);
	}

	private int size(int length) {

		if (this.left != null) {
			length = this.left.size(length);
		}

		if (this.right != null) {
			length = this.right.size(length);
		}

		if (this.current != null) {
			length = this.current.size(length);
		}

		return length;
	}

	public V remove(K key) {
		if (this.current == null) {
			return null;
		}

		if (this.current.getHash() == key.hashCode()) {
			return this.remove(this, key);
		} else if (this.current.getHash() > key.hashCode()) {
			if (this.left == null) {
				return null;
			} else {
				return this.left.remove(key);
			}
		} else if (this.current.getHash() < key.hashCode()) {
			if (this.right == null) {
				return null;
			} else {
				return this.right.remove(key);
			}
		}

		return null;
	}

	private V remove(Tree<K, V> tree, K key) {
		if (tree == null) {
			return null;
		}

		V value = null;
		if (tree.eq(key)) {
			value = tree.getValue(key);
			// 如果当前节点还存在值时，后一个将前一个值覆盖
			if (tree.current.getNext() != null) {
				tree.current = tree.current.getNext();
				return value;
			}

			// 当前节点不存在值，则重构节点树
			buildTree(tree);
			return value;
		} else {
			return tree.current.remove(key);
		}
	}

	private void buildTree(Tree<K, V> tree) {
		// 同时具有左右节点
		if (tree.left != null && tree.right != null) {
			// 获取到右子节点中最小的那个，做为置换节点
			Tree<K, V> replaceNode = this.minValue(tree.right);

			// 进行置换
			tree.current = replaceNode.current;

			// 删除置换节点
			if (replaceNode.right == null) {
				if (replaceNode.parent.left == replaceNode)
					replaceNode.parent.left = null;
				else
					replaceNode.parent.right = null;
			} else {
				changeTree(replaceNode, replaceNode.right);
			}
			return;
		}

		// 仅有左子节点，将左子节点替换为根节点
		if (tree.left != null) {
			changeTree(tree, tree.left);
			return;
		}

		// 仅有右子节点，将右子节点替换为根节点
		if (tree.right != null) {
			changeTree(tree, tree.right);
			return;
		}

		// 无左右子节点，删除当前对象的current
		if (tree.parent == null) {
			tree.current = null;
		} else {
			if (tree.parent.left == tree) {
				tree.parent.left = null;
			} else {
				tree.parent.right = null;
			}
		}
	}

	private void changeTree(Tree<K, V> fatherTree, Tree<K, V> childTree) {
		fatherTree.current = childTree.current;
		fatherTree.left = childTree.left;
		fatherTree.right = childTree.right;
	}

	@SuppressWarnings("unused")
	private Tree<K, V> maxValue(Tree<K, V> tree) {
		Tree<K, V> result = tree;
		while (result.right != null) {
			result = result.right;
		}
		return result;
	}

	private Tree<K, V> minValue(Tree<K, V> tree) {
		Tree<K, V> result = tree;
		while (result.left != null) {
			result = result.left;
		}
		return result;
	}

	private boolean eq(K key) {
		return this.current.eq(key);
	}

	private V getValue(K key) {
		return this.current.getValue(key);
	}

	public String toString() {
		return this.current.toString();
	}
}
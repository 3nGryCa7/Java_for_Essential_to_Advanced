package define;

public class Node<T> {
	T entry;
	Node next;
	public Node(T entry) {
		this.entry = entry;
		next = null;
	}
}

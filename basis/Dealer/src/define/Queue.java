package Dealer.src.define;

public interface Queue<T> {

	void clear();

	boolean append(T entry);

	T serve() throws QueueEmptyException;

	int size();

	boolean isEmpty();

	boolean isFull();

	void forEach(Process<T> process);
		
}

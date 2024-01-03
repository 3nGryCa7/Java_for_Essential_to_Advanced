package define;

public class QueueEmptyException extends Exception {
	public QueueEmptyException() {
		super("Cannot serve since queue is empty");
	}
}

package seminar.queue.decorator;

public class QueueDecorator implements Queue {
	private Queue _queue;

	public QueueDecorator(Queue queue) {
		_queue = queue;
	}

	@Override
	public void enqueue(int i) {
		_queue.enqueue(i);
	}

	@Override
	public void dequeue() {
		_queue.dequeue();
	}

	@Override
	public int peek() {
		return _queue.peek();
	}

	@Override
	public boolean isEmpty() {
		return _queue.isEmpty();
	}

	@Override
	public boolean isFull() {
		return _queue.isFull();
	}

	@Override
	public int size() {
		return _queue.size();
	}

}

package seminar.queue.interfacing;

public class MonitorableQueue implements Queue {
	private int _highWaterMark = 0;
	private int _currentSize;
	private Queue _queue;

	public MonitorableQueue(int size) {
		this(new SimpleQueue(size));
	}

	public MonitorableQueue(Queue queue) {
		_queue = queue;
	}

	@Override
	public void enqueue(int item) {
		if(++_currentSize > _highWaterMark) {
			_highWaterMark = _currentSize;
		}

		_queue.enqueue(item);
	}

	@Override
	public void dequeue() {
		--_currentSize;

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

	public int getMaxiumumSize() {
		return _highWaterMark;
	}

}

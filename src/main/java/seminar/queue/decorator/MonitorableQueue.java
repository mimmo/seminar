package seminar.queue.decorator;

public class MonitorableQueue extends QueueDecorator {
	private int _highWaterMark = 0;
	private int _currentSize;

	public MonitorableQueue(Queue queue) {
		super(queue);
	}

	@Override
	public void enqueue(int item) {
		if(++_currentSize > _highWaterMark) {
			_highWaterMark = _currentSize;
		}

		super.enqueue(item);
	}

	@Override
	public void dequeue() {
		--_currentSize;

		super.dequeue();
	}

	public Object getMaxiumumSize() {
		return _highWaterMark;
	}
}

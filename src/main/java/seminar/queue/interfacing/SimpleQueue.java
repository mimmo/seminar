package seminar.queue.interfacing;

import java.util.ArrayList;
import java.util.List;

public class SimpleQueue implements Queue {
	public List<Integer> _queue;
	public int _capacity;

	public SimpleQueue(int size) {
		_queue = new ArrayList<>(size);
		_capacity = size;
	}

	@Override
	public void enqueue(int item) {
		if (_queue.size() < _capacity) {
			_queue.add(item);
		}
	}

	@Override
	public int size() {
		return _queue.size();
	}

	@Override
	public void dequeue() {
		if (_queue.size() > 0) {
			_queue.remove(0);
		}
	}

	@Override
	public int peek() {
		try {
			return _queue.get(0);
		} catch (Throwable e) {
			System.out.println("The queue is empty");
		}

		return -999;
	}

	@Override
	public boolean isFull() {
		return _queue.size() == _capacity;
	}

	@Override
	public boolean isEmpty() {
		return _queue.size() == 0;
	}
}

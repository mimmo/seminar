package seminar.queue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import seminar.queue.interfacing.MonitorableQueue;

public class MonitorableQueueTest {
	MonitorableQueue _queue;

	@Before
	public void setUp() {
		_queue = new MonitorableQueue(10);
	}

	@Test
	public void HighWaterMarkWithoutItems() {
		assertThat(_queue.getMaxiumumSize()).isEqualTo(0);
	}

	@Test
	public void MaximumSize() {
		_queue.enqueue(1);
		_queue.enqueue(2);
		_queue.enqueue(3);
		_queue.enqueue(4);
		_queue.enqueue(5);
		assertThat(_queue.getMaxiumumSize()).isEqualTo(5);
	}

	@Test
	public void MaximumSizeAfterRemoveSome() {
		_queue.enqueue(1);
		_queue.enqueue(2);
		_queue.enqueue(3);
		_queue.enqueue(4);
		_queue.enqueue(5);
		assertThat(_queue.getMaxiumumSize()).isEqualTo(5);
		_queue.dequeue();
		_queue.dequeue();
		assertThat(_queue.getMaxiumumSize()).isEqualTo(5);
	}

}

package seminar.queue;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import seminar.queue.interfacing.SimpleQueue;

public class SimpleQueueTest {
	private SimpleQueue _queue;

	@Before
	public void setUp() {
		_queue = new SimpleQueue(10);
		_queue.enqueue(1);
	}

	@Test
	public void AddItem() {
		assertThat(_queue.size()).isEqualTo(1);
	}

	@Test
	public void RemoveItem() {
		_queue.dequeue();
		assertThat(_queue.size()).isEqualTo(0);
	}

	@Test
	public void GetFrontElement() {
		_queue.enqueue(2);
		_queue.enqueue(3);
		_queue.enqueue(4);
		assertThat(_queue.peek()).isEqualTo(1);
	}

	@Test
	public void FullQueue() {
		_queue = new SimpleQueue(1);
		_queue.enqueue(1);
		assertThat(_queue.isFull()).isTrue();
	}

	@Test
	public void EmptyQueue() {
		_queue.dequeue();
		assertThat(_queue.isEmpty()).isTrue();
	}

	@Test
	public void FullQueueAddItem() {
		_queue = new SimpleQueue(2);
		_queue.enqueue(1);
		_queue.enqueue(2);
		assertThat(_queue.isFull()).isTrue();
		_queue.enqueue(3);
		assertThat(_queue.size()).isEqualTo(2);
	}

	@Test
	public void EmptyQueueRemoveItem() {
		_queue = new SimpleQueue(2);
		assertThat(_queue.isEmpty()).isTrue();
		_queue.dequeue();
		assertThat(_queue.size()).isEqualTo(0);
	}

	@Test
	public void KOEmptyQueueGetFrontElement() {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		_queue = new SimpleQueue(2);
		assertThat(_queue.isEmpty()).isTrue();
		_queue.peek();
		assertThat(out.toString()).isEqualTo("The queue is empty\n");

		System.setOut(standardOut);
	}

}

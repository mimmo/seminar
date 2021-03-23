package seminar.queue.decorator;

public class PrintableQueue extends QueueDecorator {
	public PrintableQueue(Queue queue) {
		super(queue);
	}

	@Override
	public void enqueue(int item) {
		System.out.println("Enqueue: " + item);

		super.enqueue(item);
	}

	@Override
	public void dequeue() {
		System.out.println("Dequeue!");

		super.dequeue();
	}

	@Override
	public int peek() {
		System.out.println("Peeking: " + super.peek());
		return super.peek();
	}
}

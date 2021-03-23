package seminar.queue.decorator;

public class Client {
	public static void main(String[] args) {
		SimpleQueue queue = new SimpleQueue(10);
		queue.enqueue(1);
		System.out.println(queue.size());

		MonitorableQueue mqueue = new MonitorableQueue(new SimpleQueue(10));
		mqueue.enqueue(2);
		mqueue.enqueue(3);
		mqueue.enqueue(4);
		System.out.println(mqueue.getMaxiumumSize());

		PrintableQueue pqueue = new PrintableQueue(new SimpleQueue(10));
		pqueue.enqueue(5);
		pqueue.peek();
		pqueue.dequeue();
		pqueue.isEmpty();

		QueueDecorator que = new PrintableQueue(mqueue);
		que.enqueue(10);
		que.dequeue();
		que.dequeue();
		que.dequeue();
		que.peek();
		System.out.println(mqueue.getMaxiumumSize());
	}
}

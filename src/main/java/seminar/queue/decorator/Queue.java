package seminar.queue.decorator;

public interface Queue {
	public void enqueue(int i);
	public void dequeue();
	public int peek();
	public boolean isEmpty();
	public boolean isFull();
	public int size();
}


public interface QueueI {
	//adds an object/element to the back of the queue
	public void enQ(Object o);
	//returns and the removes the item from the front of the queue
	public Object deQ() throws EmptyQueueException;
	//shows the object at the front of the queue
	public Object front() throws EmptyQueueException;
	//return length of queue
	public int length();
	//check if queue is empty
	public boolean isEmpty();
	//empty queue
	public void clear();
}

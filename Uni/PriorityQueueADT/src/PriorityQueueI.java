
public interface PriorityQueueI {
	public void add(Comparable c);
	public Comparable removeLeast() throws EmptyQueueException;
	public Comparable inspectLeast() throws EmptyQueueException;
	public boolean isEmpty();
	public int size();
	public void clear();
}

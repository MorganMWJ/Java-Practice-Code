
public interface StackI {
	//pop off the stack
	public Object pop() throws EmptyStackException;
	//push onto the stack
	public void push(Object o);
	//top(peek) of the the stack
	public Object top() throws EmptyStackException;
	//size of stack 
	public int size();
	//is the stack empty
	public boolean isEmpty();
	//clear the stack
	public void clear();
}

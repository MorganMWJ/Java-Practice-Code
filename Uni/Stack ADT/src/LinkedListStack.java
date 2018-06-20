
public class LinkedListStack implements StackI{
	//Linked list stack
	private StackNode head;//only know about the one on top
	private int size=0;
	
	public LinkedListStack(){
		head = null;
		size = 0;
	}
	
	public Object pop() throws EmptyStackException{
		if(this.isEmpty()){
			throw new EmptyStackException("Cannot 'pop' of an empty stack!");
		}
		Object poppedData = this.head.dataInNode;
		this.head=this.head.nextNode;
		this.size--;
		return poppedData;
	}
	
	public void push(Object o){
		StackNode newHead = new StackNode(o,this.head);
		this.head = newHead;
		this.size++;
	}
	
	public Object top() throws EmptyStackException{
		if(this.isEmpty()){
			throw new EmptyStackException("The stack is empty!");
		}
		return this.head.dataInNode;
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return (head==null); //or could have: (size==0)
	}
	
	public void clear(){
		//will clear the stack because all nodes will be set to null
		//because they are all only accessed through the head instance variable
		//(in other words) they are all garbage collected by java
		this.head = null;
		this.size = 0;
	}
	
	//////////INNER CLASS/////////////
	class StackNode {
		// a private inner class of LikedListStack
		//because it is only used by that class
		
		StackNode nextNode;
		//  Note the recursive definition each node only knows about the one below it in the stack
		Object dataInNode;
		
		public StackNode(Object data, StackNode next){
			this.dataInNode = data;
			this.nextNode = next;
		}
		
	}
}

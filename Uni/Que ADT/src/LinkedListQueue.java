
public class LinkedListQueue implements QueueI{
	private LinkedListNode head, tail;
	private int length;
	
	public LinkedListQueue(){
		head = null;
		tail = null;
		length = 0;
	}
	
	public void enQ(Object o){
		LinkedListNode new_tail = new LinkedListNode(o,null);
		//if only element in list both head and tail must point to it
		if(head==null){
			this.head=new_tail;
			this.tail=new_tail;
		}
		else{
			//the tail node's pointer must point to new_tail now(instead of null) to 'link' the list
			this.tail.next=new_tail;
			//now point the tail to the end
			this.tail=new_tail;
		}
		length++;
	}
	
	public Object deQ() throws EmptyQueueException{
		if(head==null){
			throw new EmptyQueueException("ERROR - There are no elements in the queue.");
		}
		Object tempObject = this.head.data;
		head = head.next;
		length--;
		return tempObject;
	}
	
	public Object front() throws EmptyQueueException{
		if(head==null){
			throw new EmptyQueueException("ERROR - There are no elements in the queue.");
		}
		return this.head.data;
	}
	
	public int length(){
		return length;
	}
	
	public boolean isEmpty(){
		return head==null;
	}
	
	public void clear(){
		this.head=null; //head is the only link to every other node (except the tail) so when set to null everything else is also
		this.tail=null;
		this.length=0;
	}
/////////////////PRIVATE INNER CLASS/////////////////////////////////////////////////////////
	//singularly linked list node- containing an object and a pointer to another node
	class LinkedListNode{
		Object data;
		LinkedListNode next;
		
		public LinkedListNode(Object data, LinkedListNode next){
			this.data = data;
			this.next = next;
		}
		//Don't need getters/setters because it is a inner class - the LinkedListQueue class has access to it's instance variables
	}

}

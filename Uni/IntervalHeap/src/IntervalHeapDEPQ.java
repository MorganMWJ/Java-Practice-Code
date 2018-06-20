public class IntervalHeapDEPQ implements DEPQ{
	int size;
	LLNode top;
	
	/**
	 * This will construct the Interval Heap initially setting it's size equal to zero
	 * Upon construction the tree will have no nodes.
	 */
	public IntervalHeapDEPQ(){
		this.size = 0;
	}
	
	/**
	 * This method inspects the smallest item in the heap(queue)
	 * 
	 * Complexity Analysis: 
	 * Method is constant time O(1)
	 * 
	 * @throws An empty queue exception if trying to get data from an empty heap.
	 * @return The smallest element in the double ended priority queue.
	 */
	@Override
	public Comparable inspectLeast(){
		if(size==0){
			throw new EmptyHeapException();
		}
		return top.smallerData;
	}
	
	/**
	 * This method inspects the greatest item in the heap(queue)
	 * 
	 * Complexity Analysis: 
	 * Method is constant time O(1)
	 * 
	 * @throws An empty queue exception if trying to get data from an empty heap.
	 * @return The largest element in the double ended priority queue.
	 */
	@Override
	public Comparable inspectMost(){
		if(size==0){
			throw new EmptyHeapException();
		}
		return top.biggestData;
	}
	
	
	@Override
	public void add(Comparable c){

	}
	


	@Override
	public Comparable getLeast(){
		
	}


	@Override
	public Comparable getMost(){

	}
	
	 /** 
	 * Method used for checking if the heap is empty or not
	 * 
	 * Complexity Analysis: 
	 * Method is constant time O(1)
	 * 
	 * @return a boolean stating if the heap is empty(true) or not(false)
	 */
	@Override
	public boolean isEmpty() {
		return size<1;
	}
	
	/**
	 * Gets the current size of the tree.
	 * 
	 * Complexity Analysis: 
	 * Method is constant time O(1)
	 * 
	 * @return size of tree/queue
	 */
	@Override
	public int size() {	
		return size;
	}
	
	/**
	 * Empties the content of the binary search tree(our queue).
	 * It does this by setting the size to zero and all the referenced nodes to null,
	 * allowing Java's garbage collector to remove all other linked nodes from memory. 
	 * 
	 * Complexity Analysis: 
	 * Method has O(n) time complexity because the garbage collector will have to go through each node
	 * individually to remove them all from memory.
	 * 
	 * @return Nothing.
	 */	
	public void clear(){
		top=null;
		size=0;
	}
	
	//////////////////////////INNER CLASS//////////////////////////////
	class LLNode{
		Comparable smallerData;
		Comparable biggerData;
		LLNode leftChild;
		LLNode rightChild;
		boolean isFull;
		
		public LLNode(Comparable data, LLNode leftNode, LLNode rightNode){
			this.data = data;
			this.leftChild = leftNode;
			this.rightChild = rightNode;
			this.isFull = false;
		}
		
		public String toString(){
			return "{" + smallerData + "," + biggerData + "}";
		}
	}
	
	///////////////////////INNER EXCEPTION CLASS////////////////////////
	class EmptyHeapException extends RuntimeException{
		//extending RuntimeException instead of Exception means
		//that the exception doesn't need to be handled
		
		public EmptyHeapException(String msg){
			super(msg);
		}

		public EmptyHeapException(){
			super();
		}
	}
}

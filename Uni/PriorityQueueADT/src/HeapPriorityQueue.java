
public class HeapPriorityQueue implements PriorityQueueI{
	//least is always at the top of the heap(start of the array)
	//when the least is removed the bottom of the head is put in its place and bubbled back down the heap into its proper position.
	//When a new Item is inserted into the heap it must be bubbled up to its proper position 
	
	private Comparable[] heap; //heap is stored as a list of comparable items
	private int size;
	
	public HeapPriorityQueue(int maxsize){
		//start by defining a max size for the array(heap)
		this.heap = new Comparable[maxsize];
		this.size = 0;
	}

	@Override
	public void add(Comparable c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comparable removeLeast() throws EmptyQueueException {
		if(size==0){
			throw new EmptyQueueException("There are no elements in the prioity queue!");
		}
		//decrement the size
		size--;		
		//change the heap
		Comparable least = heap[0];
		Comparable last = heap[size];
		heap[size] = null;
		heap[0] = last;
		//if one item in heap return it(if size after -1 is now 0)
		if(size==0){
			return least;
		}
		
		//bubble down the lowest path
		int current = 0;  //starting index at the top of heap
		int chosenChild;
		while(true){
			int leftChild = (current*2) + 1;//index of left child
			int rightchild = (current*2) + 2;//index of right child
			
			if()
			
		}
	}

	@Override
	public Comparable inspectLeast() throws EmptyQueueException {
		if(size<0){
			throw new EmptyQueueException("There are no elements in the prioity queue!");
		}
		return heap[0];
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for(int i=0;i<size;i++){
			heap[i]=null;
		}
		size = 0;		
	}
	
	
}

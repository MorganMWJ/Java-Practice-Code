package cs21120.depq;

/**
 * The BinarySearchTreeDEPQ class implements a double ended priority queue using a binary search tree data structure.
 * The Binary Search tree is not balanced meaning there is the possibility of the add,getMost and getLeast methods becoming inefficient.
 * The Binary Search Tree is built using linked nodes.
 * 
 * I chose to implement a Binary Search Tree because it allows for the best time complexity possible providing it is balanced.
 * Since I have been unable to implement a balanced binary tree it is now not as efficient as other possible solutions. 
 * 
 * Notes:
 * 		-I have created an inner exception class 'EmptyQueueException' to throw unchecked errors when an inappropriate methods are called on an empty queue.
 * 		-There is an unneeded clear() method used in my getMost() and getLeast() methods, although the clear() method is O(n) complexity 
 * 		 it's use in these methods is always constant time because it only sets exactly 3 references to null.
 * 		-I used recursion to iterate over the tree for the add method
 * 		-I used loops to iterate over the tree for the getMost and getLeast methods
 * 
 * Self Evaluation:
 * I believe I should be awarded 75% of the marks for this assignment.
 * All my methods function correctly. Each of my methods have properly formatted javadoc.
 * Each of my methods javadoc contains both a description and a complexity analysis.
 * Some of my methods efficiency is not ideal although still better than the basic array implementations in most cases.
 * 
 * If I were to re-do this assignment I would certainly use an interval heap for the DEPQs implementation.
 * An interval heap achieves the best possible efficiency but isn't to difficult to code. 
 * @author Morgan Jones (mwj7)
 * @version 1.0
 */
public class Mwj7DEPQ implements DEPQ{
	
	/**The size of the tree*/
	int size;
	/**A reference to the root node of the tree*/
	LLNode root;
	/**A reference to the node with the smallest data in the tree */
	LLNode least;
	/**A reference to the node with the largest data in the tree */
	LLNode most;
	
	/**
	 * This will construct the Binary Search Tree initially setting it's size equal to zero
	 * Upon construction the tree will have no nodes.
	 */
	public Mwj7DEPQ(){
		this.size = 0;
	}
	
	/**
	 * This method inspects the smallest item in the tree(queue)
	 * It throws an EmptyQueueException if the tree is empty
	 * 
	 * Complexity Analysis: 
	 * Method is constant time O(1)
	 * 
	 * @return The smallest element in the double ended priority queue.
	 */
	@Override
	public Comparable inspectLeast(){
		if(size==0){
			throw new EmptyQueueException();
		}
		return least.data;
	}
	
	/**
	 * This method inspects the greatest item in the tree(queue)
	 * It throws an EmptyQueueException if the tree is empty
	 *
	 * Complexity Analysis: 
	 * Method is constant time O(1)
	 * 
	 * @return The largest element in the double ended priority queue.
	 */
	@Override
	public Comparable inspectMost(){
		if(size==0){
			throw new EmptyQueueException();
		}
		return most.data;
	}
	
	/**
	 * This method adds items into the queue(tree) 
	 * It utilizes a recursive helper method to traverse through levels of the tree.
	 * It then adds the new item as either the right or left child of the node that was
	 * returned by the recursive findNodeToAppend() method.
	 * 
	 * Complexity Analysis:
	 * O(n) in the worst case because the Binary Search Tree is unbalanced.
	 * The worst case being when items are added in ascending or descending order,
	 * this scenario would lead to the data structure practically becoming a linked list.
	 * 
	 * However in the best case this method would have O(logn) time complexity
	 * and in the average case this method would be also more closer to O(logn).
	 * This is because every level of the tree you pass you effectively half your search.
	 * 
	 * @param c 		The comparable item to be added to the double ended priority queue.
	 * @return 			nothing.
	 */
	@Override
	public void add(Comparable c){
		//increment size
		this.size++;
		LLNode nodeToAdd = new LLNode(c,null,null);
		// whenever we insert into a binary tree it will always become a leaf in the tree
		if(size==1){
			root=nodeToAdd;
			most=nodeToAdd;
			least=nodeToAdd;
			return;
		}
		//Find the node the new item will become a child of
		LLNode nodeToAppendTo = findNodeToAppend(c, root);
		//place new item as either right or left child of that node
		if(c.compareTo(nodeToAppendTo.data) < 1){
			if(nodeToAppendTo==least){
				least=nodeToAdd;
			}
			nodeToAppendTo.leftChild = nodeToAdd; 
		}
		else{
			if(nodeToAppendTo==most){
				most=nodeToAdd;
			}
			nodeToAppendTo.rightChild = nodeToAdd; 
		}
	}
	
	/**
	 * This is a recursive private helper method that is used by the 'add' method.
	 * It starts at the root and iterates down the tree comparing the element to insert/add along the way.
	 * When it finds the node that the new element should be added too it returns that node. 
	 * 
	 * This is part of the add method that defines it's time complexity
	 * In the worst case being called at most n times O(n) 
	 * In the best case being called at most log2n times O(logn)
	 * 
	 * @param elementToAdd		A comparable element that is being inserted into the tree.
	 * @param currNode			A pre-existing node that this method is currently comparing the new item to.
	 * @return 					The pre-existing node that the new element will become a child of.
	 */
	private LLNode findNodeToAppend(Comparable elementToAdd, LLNode currNode) {
		if(elementToAdd.compareTo(currNode.data) < 1){
			if(currNode.leftChild==null){
				return currNode;
			}
			return findNodeToAppend(elementToAdd, currNode.leftChild);
		}
		else{
			if(currNode.rightChild==null){
				return currNode;
			}
			return findNodeToAppend(elementToAdd, currNode.rightChild);
			
		}
	}

	/**
	 * This method is used to remove the smallest item from the queue and find the next largest item in it.
	 * This method is equal and opposite to the getLeast method.
	 * 
	 * It starts at the root and moves right down the tree until it finds the previous least at which point it removes the least
	 * and either returns the least's parent or finds the leftmost node of the previous least's right sub tree.
	 * I have to look at 3 nodes when traversing down the tree because the nodes hold no reference to their parents.   
	 * (i.e. When the current node's right child's right child equals null I know the current node is the parent of the previous most.)
	 * 
	 * Complexity Analysis:
	 * This method is dependent on how balanced the tree is.
	 * If completely balanced the tree would be O(logn)
	 * If completely unbalanced the tree would have O(n) 
	 * In the average case the time complexity is O(logn)
	 * 
	 *@return The smallest element in the double ended priority queue.
	 */
	@Override
	public Comparable getLeast(){
		boolean foundNewleast = false;
		boolean shiftedRight = false; 
		
		if(size==0){
			throw new EmptyQueueException();
		}
		//decrement the size
		size--;
		//copy least
		LLNode tempLeast = least;
		////////////////////IF REMOVING LAST EMLEMENT IN THE TREE////////////////////////////	
		if(size==0){
			this.clear();//this call is constant time because n=1 so O(n) => O(1) in this instance
			return tempLeast.data;
		}
		////////////START FOM ROOT UNLESS ROOT IS CURRENT LEAST//////////////////////////////
		//if the root was the least then bubble to the new least from the roots right child
		if(least==root){
			root=root.rightChild;
			least=root;
			shiftedRight=true;
		}
		else{//bubble to new least from the root itself
			least = root;
		}
		////////////////////LOOP TO FIND NEW LEAST///////////////////////////////////////////
		while(!foundNewleast){
			///###DEBUG###///	
			//treePositionDEBUG("least");	
			///###DEBUG###///
			if(shiftedRight){
				if(least.leftChild==null){
					foundNewleast=true;
					break;					
				}
				else{
					least=least.leftChild;
				}
			}
			
			else if(least.leftChild.leftChild==null){
				//we are at the parent node of the previous least
				if(least.leftChild.rightChild!=null){
					//then shift to the right child of the old least
					//and remove the old least from the data structure
					//at the leftmost position in this right sub tree will be the new least
					LLNode treeToSearch = least.leftChild.rightChild;
					least.leftChild = null;//remove the old least
					least.leftChild = treeToSearch;//replace it with the old leastimum's right child
					least=treeToSearch;//keep searching for the new least starting from this node
					shiftedRight=true; //now having shifted to a right child for the first and only time
				}
				else{
					least.leftChild=null;
					foundNewleast=true;
				}
			}
			
			else{
				least = least.leftChild;	
			}
			////////////////////////////////////////////////////////////////////////////////////						
		}
		//treePositionDEBUG("least");
		return tempLeast.data;
	}

	/**
	 * This method is used to remove the largest item from the queue and find the next largest item in it.
	 * This method is equal and opposite to the getLeast method.
	 * 
	 * It starts at the root and moves right down the tree until it finds the previous most at which point it removes the most
	 * and either returns the most's parent or finds the rightmost node of the previous most's left sub tree.
	 * I have to look at 3 nodes when traversing down the tree because the nodes hold no reference to their parents.   
	 * (i.e. When the current node's right child's right child equals null I know the current node is the parent of the previous most.)
	 * 
	 * Complexity Analysis:
	 * This method is also dependent on how balanced the tree is.
	 * If completely balanced the tree would be O(logn)
	 * If completely unbalanced the tree would have O(n) 
	 * In the average case the time complexity is O(logn)
	 * 
	 * @return The largest element in the double ended priority queue.
	 */
	@Override
	public Comparable getMost(){
		boolean foundNewmost = false;
		boolean shiftedLeft = false; 
		
		if(size==0){
			throw new EmptyQueueException();
		}
		//decrement the size
		size--;
		//copy most
		LLNode tempMost = most;
		//If removing last element in tree
		////////////////////IF REMOVING LAST EMLEMENT IN THE TREE////////////////////////////	
		if(size==0){
			this.clear();
			return tempMost.data;
		}
		////////////START FOM ROOT UNLESS ROOT IS CURRENT MOST//////////////////////////////
		//either bubble to new root from the root or it's left child
		if(most==root){//bubble to new most from roots left child if root is the biggest
			root=root.leftChild;
			most=root;
			shiftedLeft=true;
		}
		else{//bubble to new most from the root itself
			most = root;
		}
		////////////////////LOOP TO FIND NEW MOST///////////////////////////////////////////
		while(!foundNewmost){
			///###DEBUG###///	
			//treePositionDEBUG("most");	
			///###DEBUG###///
			if(shiftedLeft){
				if(most.rightChild==null){
					foundNewmost=true;
					break;					
				}
				else{
					most=most.rightChild;
				}
			}
			
			else if(most.rightChild.rightChild==null){
				//we are at the parent node of the previous most
				if(most.rightChild.leftChild!=null){
					//then shift to the left child of the previous most
					LLNode treeToSearch = most.rightChild.leftChild;//save the left sub tree of the previous most
					most.rightChild = null;//remove the previous most
					most.rightChild=treeToSearch;//replace it with the it's right child
					most=treeToSearch;//keep searching for the new most starting from this node
					shiftedLeft=true;//now having shifted to a left child for the first and only time
				}
				else{
					most.rightChild=null;
					foundNewmost=true;
				}
			}
			
			else{
				most = most.rightChild;	
			}
		////////////////////////////////////////////////////////////////////////////////////			
		}
		//treePositionDEBUG("most");
		return tempMost.data;
	}
	
	 /** 
	 * Method used for checking if the tree is empty or not
	 * 
	 * Complexity Analysis: 
	 * Method is constant time O(1)
	 * 
	 * @return a boolean stating if the tree is empty(true) or not(false)
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
	 * I have used this function in my above code but only ever when there was a size of 0 
	 * therefore it does not affect the time complexity of those methods because its use in 
	 * those specific situations is constant time O(1).
	 * 
	 * @return Nothing.
	 */	
	public void clear(){
		most=null;
		least=null;
		root=null;
		size=0;
	}
	
	//////////////////////////INNER CLASS//////////////////////////////
	/**
	 * This is the linked list node class.
	 * Each node contains one data item
	 * and two links to both of it's children nodes.
	 * @author Morgan Jones(mwj7)
	 *
	 */
	class LLNode{
		/**The data this node holds*/
		Comparable data;
		/**A pointer to this nodes left child */
		LLNode leftChild;
		/**A pointer to this nodes right child */
		LLNode rightChild;
		
		/**
		 * This constructs a node upon construction both of the nodes parents will be set to null.
		 * 		 
		 * @param data
		 * @param leftNode
		 * @param rightNode
		 */
		public LLNode(Comparable data, LLNode leftNode, LLNode rightNode){
			this.data = data;
			this.leftChild = leftNode;
			this.rightChild = rightNode;
		}
		
		/**
		 * Debug toString used in debugging
		 */
		public String toString(){
			return "{" + data + "}";
		}
	}
	
	///////////////////////INNER EXCEPTION CLASS////////////////////////
	/**
	 * This class is a subclass of RuntimeException(an unchecked exception)
	 * Therefore this exception is also unchecked.
	 * @author Morgan Jones (mwj7)
	 *
	 */
	class EmptyQueueException extends RuntimeException{
		//extending RuntimeException instead of Exception means
		//that the exception doesn't need to be handled
		
		public EmptyQueueException(String msg){
			super(msg);
		}

		public EmptyQueueException(){
			super();
		}
	}
	/*
	////////////////////////DEBUG METHODS//////////////////////////////////
	private void treePositionDEBUG(String mostOrleast) {
		if(mostOrleast=="least"){	
			System.out.println("Current least position: " + least.toString());//#
			if(least.leftChild==null){
				System.out.println("Current least left child: {-}");//#
			}
			else{
				System.out.println("Current least left child: " + least.leftChild.toString());//#
			}
			if(least.rightChild==null){
				System.out.println("Current least right child: {-}");//#
			}
			else{
				System.out.println("Current least right child: " + least.rightChild.toString());//#
			}
		}
		else{
			System.out.println("Current most position: " + most.toString());//#
			if(most.leftChild==null){
				System.out.println("Current most left child: {-}");//#
			}
			else{
				System.out.println("Current most left child: " + most.leftChild.toString());//#
			}
			if(most.rightChild==null){
				System.out.println("Current most right child: {-}");//#
			}
			else{
				System.out.println("Current most right child: " + most.rightChild.toString());//#
			}
		}
		System.out.println("\n");
	}
	
	public void debugInstance(){
		//DEBUG
		System.out.println("least: " + least);
		System.out.println("most: " + most);
		System.out.println("ROOT: " + root);
		System.out.println("SIZE: " + size);
		//DEBUG
	}*/
}


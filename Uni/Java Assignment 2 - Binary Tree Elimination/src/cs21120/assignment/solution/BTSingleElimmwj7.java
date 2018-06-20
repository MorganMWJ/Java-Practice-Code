package cs21120.assignment.solution;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import cs21120.assignment.CompetitionManager;
import cs21120.assignment.IBinaryTree;
import cs21120.assignment.IManager;
import cs21120.assignment.Match;
import cs21120.assignment.NoNextMatchException;
/**
 * This class implements a single elimination competition using a binary tree as a main data structure.
 * 
 * This class keeps track of it's binary tree by keeping a reference to the root node and
 * a current node which is used as matches are played and nodes updated. I have used a ArrayList
 * 'nodesInPosition' to hold references to all nodes in 'breadth first' order this is so when the
 * getPosition() method is called any node can be easily retrieved from this array list. All unevaluated
 * matches are represented in a stack of binary tree nodes called 'matches', the nodes are then popped
 * off when nextMatch() is called to get the next pairing in the competition.
 * 
 * The stack, ArrayList and binary tree used in this solution are not created until the setPlayers()
 * method is called therefore when using this class the user should always call the setPlayers() method
 * before using any of the others methods. Before the players  
 * 
 * Self Evaluation:
 * I believe I should be awarded 90% of the mark because although I have completely implemented 
 * all of the methods using clear and concise code. I have also provided good quality java
 * documentation for each method along with in-method comments for each step in my methods.
 * 
 * I have made use of the java.util stack and queue as suggested in the assignment brief.
 * 
 * @author Morgan Jones (mwj7)
 *
 */
public class BTSingleElimmwj7 implements IManager{
	
	/**A reference to the root node of the binary tree*/
	private BinaryTree root;
	/**A reference to the current node being processed in the tree*/
	private BinaryTree currentNode;
	/**A stack holding a list of nodes that represent all unevaluated matches in the tournament*/
	private Stack<BinaryTree> matches;
	/**References to all nodes in breadth-first order*/
	private ArrayList<BinaryTree> nodesInPosition; 
	
	/**
	 * Constructor used to set the instance variables to a starting state to prevent errors from calling 
	 * other methods before the tree and stack have been built by the setPlayers() method. 
	 */
	public BTSingleElimmwj7(){
		root = null;
		currentNode = null;
		
		matches = new Stack<BinaryTree>();
		nodesInPosition = new ArrayList<BinaryTree>();	
	}
	
	/**
	 * This method builds the binary tree and appends the players to leaves of that tree.
	 * It then uses this tree to build a stack of nodes to keep track of the order of competition matches. 
	 * 
	 * @param players - A list of the players/teams in the tournament.
	 * @return Nothing.
	 */
	@Override
	public void setPlayers(ArrayList<String> players) {
		
		//Initialize binary tree of players/teams
		root = buildCompetitionTree(players);
		
		//Initialize the stack of unevaluated matches
		matches = buildMatchStack();
	}

	/**
	 * This is a recursive helper method used by the 'setPlayers' method 
	 * It creates a new node at each recursion and passes half the ArrayList
	 * to the left child of that node and half to the right child of that node
	 * 
	 * The recursion terminates when an ArrayList of size 1 is passed to the method.
	 * At this point a leaf node is created with a players name set.
	 * 
	 * @param players - A list of the players/teams in the tournament.
	 * @return The root of a tree/subtree with it's leaf nodes set to represent the players passed to the method.
	 */
	private BinaryTree buildCompetitionTree(ArrayList<String> players){
		//recursion termination
		if(players.size()==1){
			return new BinaryTree(players.get(0));
		}
		
		//create a new node
		BinaryTree newNode = new BinaryTree();
		//split the list of players into two
		ArrayList<String> firstHalf = new ArrayList<String>(players.subList(0, players.size()/2));
		ArrayList<String> secondHalf = new ArrayList<String>(players.subList(players.size()/2, players.size()));
		//pass half the list to the left subtree
		newNode.leftChild = buildCompetitionTree(firstHalf);
		//pass half the list to the right subtree
		newNode.rightChild = buildCompetitionTree(secondHalf);
		
		return newNode;
	}
	
	/**
	 * This method uses a queue to traverse the initial binary tree and retrieve all nodes that represent unevaluated matches.
	 * It does this by placing them into a stack in an order that is suitable for the matches to be evaluated as popped of the stack.
	 * This method uses the stack and queue algorithm stated in the assignment specification.
	 * 
	 * As this takes place all nodes are also added to the 'nodesInPosition' ArrayList this allows any node in the tree to be easily
	 * retrieved by the 'getPosition()' method. 
	 * 
	 * @return A stack of nodes representing all unevaluated matches in the tournament.
	 */
	private Stack<BinaryTree> buildMatchStack(){
		Stack<BinaryTree> matchStack = new Stack<BinaryTree>();
		Queue<BinaryTree> traversalQue = new LinkedList<BinaryTree>();
		
		traversalQue.add(root);
		
		//keep track of the position of tree nodes (for getPosition() method)
		nodesInPosition.add(root);
		
		while(!traversalQue.isEmpty()){
			//remove the first node in the queue
			BinaryTree node = traversalQue.remove();
			//if it has two children put it in the stack and add it's children to the queue
			if(node.leftChild!=null && node.rightChild!=null){
				matchStack.add(node);
				traversalQue.add(node.leftChild);
				traversalQue.add(node.rightChild);
				
				//keep track of the position of tree nodes (for getPosition() method)
				nodesInPosition.add(node.leftChild);
				nodesInPosition.add(node.rightChild);
			}
		}
		return matchStack;
	}
	
	/**
	 * Method used for checking if the tournament is finished or not.
	 * i.e. if an unresolved match(node) is left in the 'matches' stack
	 * 
	 * @return A Boolean representing if there are matches left to play(true) or not(false)
	 */
	@Override
	public boolean hasNextMatch() {
		return !matches.empty();
	}
	
	/**
	 * This method gets the next node of the stack of unevaluated matches
	 * Then creates a new match from the children of that node and returns it.
	 * 
	 * @return nextMatch - returns the next match to be evaluated.
	 * @throws A NoNextMatchException if there are no matches left to play.
	 */
	@Override
	public Match nextMatch() throws NoNextMatchException {
		if(!this.hasNextMatch()){
			throw new NoNextMatchException("There are no matches left to play.");
		}
		//pop a node off the match stack
		currentNode = matches.pop();
		//create a new match with the new current node's children
		Match nextMatch = new Match(currentNode.leftChild.name, currentNode.rightChild.name);
		//return that match
		return nextMatch;
	}
	
	/**
	 * Sets the score for the current node/unevaluated match.
	 * Then copies the name of the winning player to its parent node.
	 * 
	 * @param p1 - Player 1's score in the match.
	 * @param p2 - Player 2's score in the match.
	 * @return Nothing.
	 */
	@Override
	public void setMatchScore(int p1, int p2) {
		currentNode.leftChild.score = p1;
		currentNode.rightChild.score = p2;
		if(p1>p2){
			currentNode.name = currentNode.leftChild.name;
		}
		else{
			currentNode.name = currentNode.rightChild.name;
		}
	}

	/**
	 * Method gets the name of player/team at a given position in the binary tree.
	 * The position being numbered left to right (breadth first).
	 * 
	 * Method returns null if the tournament is still going on or the integer passed 
	 * is larger than the number of nodes in the tree.
	 * 
	 * @param n - A place in the competition tree.
	 * @return name of the player/team that is in that node in the tree.
	 */
	@Override
	public String getPosition(int n) {
		// if matches have not finished return null
		// if n is greater than the amount nodes/positions in the tree
		if(this.hasNextMatch() || nodesInPosition.size() < n){
			return null;
		}
		return nodesInPosition.get(n).name;
	}

	/**
	 * Gets the root node of the binary tree representing the competition.
	 * 
	 * @return root of competition tree
	 */
	@Override
	public IBinaryTree getCompetitionTree() {
		return root;
	}

	/**
	 * This is a private inner class implementing the IBinaryTree interface.
	 * This class represents the nodes used to build the binary tree.
	 * 
	 * Each node stores the name and score of a player/team and a link to its left and right subtrees.
	 * @author Morgan Jones(mwj7)
	 *
	 */
	class BinaryTree implements IBinaryTree{
			
			/**The name of a player or team*/
			private String name;
			/**The score of a player or team in a particular match*/
			private int score;
			/**T the left subtree (the left child node) of this node*/
			private BinaryTree leftChild;
			/**The right subtree (the right child node) of this node*/
			private BinaryTree rightChild;
			
			/**
			 * Constructor to create a binary tree leaf node with a player at that node
			 * @param name - name of the player/team at this node
			 */
			BinaryTree(String name){
				this.name = name;
				this.score = 0;
				this.leftChild = null;
				this.rightChild = null;
			}
			
			/**
			 * Constructor to create a node with no player/team yet set
			 */
			BinaryTree(){
				this.leftChild = null;
				this.rightChild = null;
			}
			
			/**
			 * This method gets the root node of this nodes left subtree.
			 */
			@Override
			public IBinaryTree getLeft() {
				return leftChild;
			}
	
			/**
			 * This method gets the root node of this nodes right subtree.
			 */
			@Override
			public IBinaryTree getRight() {
				return rightChild;
			}
	
			/**
			 * This method gets the name of the player/team at this node.
			 */
			@Override
			public String getPlayer() {
				return name;
			}
	
			/**
			 * This method gets the score of the player/team at this node.
			 */
			@Override
			public int getScore() {
				return score;
			}
			
		}
	
	/**
	 * Main method to create competition manager and run competition.
	 * @param args - The name of the input text file.
	 */
	public static void main(String[] args) {
		CompetitionManager cm = new CompetitionManager(new BTSingleElimmwj7());
		try {
			cm.runCompetition(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("The file '" + args[0] + "' was not found!");
		}
	}
	
}
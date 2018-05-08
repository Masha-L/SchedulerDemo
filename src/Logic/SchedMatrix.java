package Logic;

import java.util.ArrayList;

/**
 * Manages holding the adjacency matrix for the schedule nodes
 *
 * @author Maria and Sofia
 */
public class SchedMatrix {
	
	// The adjacency matrix 
	private boolean[][] matrix;
	//Holds the degree of vertex in the adjacency matrix
	private int[] degrees;
	// Holds the number of valid subjects
	private int numValid;

	/**
	 * Constructor for the matrix
	 * Takes the list of chosen classes from controller
	 * 
	 * @param nodes the list of the schedule nodes
	 */
	public SchedMatrix(ArrayList<SchedNode> nodes, int numS) {	

		//sets the number of the nodes
		int numNodes = nodes.size();	
		//builds matrix out of the passed nodes
		buildMatrix(nodes);
		//initializes an array 
		degrees = new int[numNodes];
		//number of valid nodes
		numValid = numNodes;
		//assigns validity to all the nodes
	    assignValidity(numS);
	}


	/**
	 * Fills the adjacency matrix with boolean values 
	 * where true stands for a conflict of two nodes
	 * 
	 * @param nodes the list of nodes to fill the matrix
	 */
	private void buildMatrix(ArrayList<SchedNode> nodes) {


		//sets up the matrix
		int matrixSize = nodes.size();	
		matrix = new boolean[matrixSize][matrixSize];

		//creates two empty nodes
		SchedNode node, anotherNode;		
		
		//traverses the matrix
		for (int row = 0; row < matrixSize; row++) {
			node = nodes.get(row);
			//column number
			for (int col = 0; col < matrixSize; col++) {
				anotherNode = nodes.get(col);
				//sets nodes in conflict if there is one
				if (node.conflicts(anotherNode)) {
					addConflict(row, col);
				}
			}
		}
	}


	/**
	 * Itteratively counts nodes degrees to determine if they can be used in schedules
	 * 
	 * @param numS the number of subjects the student wants to take
	 */
	private void assignValidity(int numS) {
		// True if iteration is not over
		boolean changed = true;

		while(changed) {
			changed = false;
			// Checks if any nodes became invalid during the last iteration
			for (int node = 0; node < matrix.length; node++) {
				if (becameInvalid(node, numS)) {
					changed = true;
				}
			}
		}
	}



	 /** Increases the node's degree if appropriate or sets it as invalid
	  * Calculates the current degree of the node and compares it to the 
	  * desired number of classes. 
	  * Updates the degree if appropriate, otherwise sets the node invalid
	  * 
	  * @param node - the node to calculate the degree
	  * @param numS - the preferred number of classes 
	  * @return false if the node did not become invalid
	  */
	private boolean becameInvalid(int node, int numS) {
		
		//checks if the node is valid
		if(isValid(node)) {
			//recalculates the degree of the node
			int degree = vertexDegree(node);		
			//if the degree is less than it should be sets node invalid
			if (degree < numS) {
				setNodeInvalid(node);
				return true;
			}
			else
				//updates the degree of the node
				degrees[node] = degree;
		}
		
		return false;
	}

	/**
	 * Calculates the degree of a node from matrix
	 * 
	 * @param row the number of the node in the matrix
	 * 
	 * @return the degree of the node
	 */
	private int vertexDegree(int node) {
		
		int degree = 0;
		//calculates the degree of the node 
		//if the other node is valid and not conflicting - increments the degree
		for(int i = 0; i < matrix.length; i++) {	
			if(isValid(node) && !areInConflict(node, i))
				degree++;
		}

		return degree;
	}

	/**
	 * Sets the node invalid for schedule (invalid == true)
	 * 
	 * @param node the node to be set invalid
	 */
	private void setNodeInvalid(int node) {
		
		//sets the node invalid
		degrees[node] = -1;
		//decrements the number of valid nodes
		numValid--;
	}

 
	/**
	 * Checks if the node is valid
	 * 
	 * @param node the node to be checked
	 * @return true if the node is valid
	 */
	private boolean isValid(int node) {
		//true, if the node is valid
		return degrees[node] != -1;
	}

	/**
	 * Creates an array to choose combinations from
	 * 
	 * @return array of indexes to choose schedules from
	 */
	public int[] validNodes() {
		//array size of all valid nodes
		int[] chooseFrom = new int[numValid];

		int currentIndex = 0;
		//adds all indexes of valid nodes to the array
		for (int node = 0; node < matrix.length; node++) {
			if (isValid(node)) {
				chooseFrom[currentIndex] = node;
				currentIndex++;
			}
		}
		return chooseFrom;
	}

	/**
	 * Adds an edge between two nodes if there is a conflict between them
	 * 
	 * @param oneNode the first node
	 * @param anotherNode the second node
	 */
	private void addConflict(int oneNode, int anotherNode) {
		//sets the apropriate grid of the matrix to true 
		matrix[oneNode][anotherNode] = true;
	}

	/**
	 * Checks if two nodes are in conflict
	 * 
	 * @param oneNode the first node 
	 * @param anotherNode the second node
	 * @return true if they are in conflict
	 */
	public boolean areInConflict(int oneNode, int anotherNode) {		
		return matrix[oneNode][anotherNode];
	}

	/**
	 * Returns connections of the node to the others
	 * 
	 * @param node the node to get the connections to 
	 * @return the array of connections
	 */
	public boolean[] getNodeConnections(int node) {
		return matrix[node];
	}
}

package Logic;

import java.util.ArrayList;

/**
 * Holds the adjacency matrix for the schedule graph.
 *
 * @author Maria and Sofia
 */
public class Matrix {

	// The adjacency matrix 
	private boolean[][] matrix;
	// Holds the degree of vertex in the adjacency matrix
	private int[] degrees;
	// Holds the number of valid subjects
	private int numValid;

	/**
	 * Constructor for the matrix
	 * Takes the list of the chosen classes from the controller
	 * 
	 * @param nodes - the list of the schedule nodes
	 * @param numS - the preferred number of classes 
	 */
	public Matrix(ArrayList<Node> nodes, int numS) {	
		// Creates the matrix of the right size
		int numNodes = nodes.size();	
		matrix = new boolean[numNodes][numNodes];
		fillMatrix(nodes);

		// Initializes the array to hold the node's degrees
		degrees = new int[numNodes];
		// Initially, all nodes are valid
		numValid = numNodes;
		// Assigns validity to the nodes using their degrees
		assignValidity(numS);
	}


	/**
	 * Fills the adjacency matrix with boolean values 
	 * where 'true' stands for a conflict between two nodes
	 * 
	 * @param nodes - the list of nodes to fill the matrix with
	 */
	private void fillMatrix(ArrayList<Node> nodes) {

		// Gets the size of the matrix
		int matrixSize = matrix.length; 

		Node node, anotherNode; 

		// Gets each two nodes's intersection and compares them
		for (int row = 0; row < matrixSize; row++) {
			node = nodes.get(row);
			for (int col = row; col < matrixSize; col++) {
				if (row == col)
					addConflict(row, col);
				else {
					anotherNode = nodes.get(col);
					if (node.conflicts(anotherNode)) 
						addConflict(row, col);
				}
			}
		}
	}


	/**
	 * Iteratively counts nodes's degrees to determine 
	 * if they can be used in schedules
	 * 
	 * @param numS - the preferred number of classes 
	 */
	private void assignValidity(int numS) {
		// True if iteration is not over
		boolean changed = true;

		while (changed) {
			changed = false;
			// Checks if any nodes became invalid during the last iteration
			for (int node = 0; node < matrix.length; node++) {
				if (becameInvalid(node, numS)) {
					changed = true;
				}
			}
		}
	}


	/** 
	 * Increases the node's degree if appropriate or sets it as invalid.
	 * Calculates the current degree of the node and compares it to the 
	 * desired number of classes. 
	 * Updates the degree if appropriate, otherwise sets the node invalid.
	 * 
	 * @param node - the node to calculate the degree
	 * @param numS - the preferred number of classes 
	 * @return false if the node did not become invalid
	 */
	private boolean becameInvalid(int node, int numS) {

		/*
		 * If the node is valid, recalculates and updates 
		 * its degree in the array, setting it either
		 * valid or invalid.
		 */
		if (isValid(node)) {
			int degree = vertexDegree(node);		
			if (degree < numS) {
				setNodeInvalid(node);
				return true;
			}
			else
				degrees[node] = degree;
		}

		return false;
	}

	/**
	 * Calculates the degree of a node.
	 * 
	 * @param node - the node in the matrix
	 * @return the degree of the node
	 */
	private int vertexDegree(int node) {
		/*
		 * Increments the degree counter for each node that
		 * is valid and does not conflict with the given one 
		 */
		int degree = 0;
		for (int otherNode = 0; otherNode < matrix.length; otherNode++) {	
			if (isValid(otherNode) && !areInConflict(node, otherNode))
				degree++;
		}

		return degree;
	}

	/**
	 * Sets the node invalid to be in a schedule
	 * 
	 * @param node - the invalid node
	 */
	private void setNodeInvalid(int node) {
		degrees[node] = -1;
		// Decrements the number of the valid nodes
		numValid--;
	}


	/**
	 * Checks if the node is valid
	 * 
	 * @param node - the node to be checked
	 * @return true if the node is valid
	 */
	private boolean isValid(int node) {
		return degrees[node] != -1;
	}

	/**
	 * Creates an array that contains only the indices 
	 * of the valid nodes. This array is then used to 
	 * create schedule options.
	 * 
	 * @return array of the valid indices
	 */
	public int[] validNodes() {

		int[] chooseFrom = new int[numValid];
		int currentIndex = 0;

		// Adds all valid indices to the array
		for (int node = 0; node < matrix.length; node++) {
			if (isValid(node)) {
				chooseFrom[currentIndex] = node;
				currentIndex++;
			}
		}

		return chooseFrom;
	}

	/**
	 * Adds an edge between two nodes, if there is a conflict.
	 * 
	 * @param oneNode - the first node
	 * @param anotherNode - the second node
	 */
	private void addConflict(int oneNode, int anotherNode) {	
		matrix[oneNode][anotherNode] = true;
		matrix[anotherNode][oneNode] = true;
	}

	/**
	 * Checks if two nodes are in conflict
	 * 
	 * @param oneNode - the first node 
	 * @param anotherNode - the second node
	 * @return true if the nodes are in conflict
	 */
	public boolean areInConflict(int oneNode, int anotherNode) {		
		return matrix[oneNode][anotherNode];
	}

	/**
	 * Returns all the relations of the node with the others
	 * 
	 * @param node - the node 
	 * @return the boolean[] 
	 */
	public boolean[] getNodeConnections(int node) {
		return matrix[node];
	}
}
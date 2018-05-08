package Logic;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Database.Subject;

/**
 * The main logic class that creates the schedule options
 * 
 * @author Maria and Sofia
 */
public class SchedSolver {

	/*
	 * Holds the schedule options represented
	 * by the indexes of the nodes
	 */
	private ArrayList<int[]> scheduleNodes = new ArrayList<int[]>();
	// The node matrix
	private SchedMatrix matrix;
	// The ordered list of all schedule nodes
	private ArrayList<SchedNode> allNodes;

	/**
	 * Constructor.
	 * Creates the list of nodes, builds the adjacency matrix,
	 * and generates the list of schedules.
	 * 
	 * @param classes chosen by user
	 * @param numS the number of the subjects the student wants to take
	 */
	public SchedSolver(ArrayList<Subject> classes, int numS) {

		classesToNodes(classes);
		matrix = new SchedMatrix(allNodes, numS);	
		
		createValidSchedules(numS);
	}


	/**
	 * Creates all possible combinations of valid subjects and adds them to the list of schedules
	 *
	 * @param sched is an empty array of the size K 
	 * @param validNodes is an array of all valid vertices 
	 * @param leftToAdd is (K - gen) for the number of subjects left to add
	 * @param gen is the level of recursion (generation)
	 * @param startFrom is the pointer to the next element to be filled
	 */

	private void traverseGraph(int[] sched, int[] chFrom, int leftToAdd, int gen, int startFrom) {

		//checks that we are not out of bounds yet and haven't explored all posible options
		if (gen < sched.length && startFrom < chFrom.length) {

			//sets up the pointer to the element to be added
			for (int newElm = gen; newElm < sched.length; newElm++) {
				//sets up the pointer in the array of the potential options
				for (int nextElm = startFrom; nextElm < chFrom.length; nextElm++) {
					//adds the new element
					sched[newElm] = chFrom[nextElm];
					//if this schedule is complete
					if (leftToAdd == 1) {
						//checks if the chosen subgraph is complete
						if (isComplete(sched, 0)) {
							 //scheduleList is the list of all complete schedules
							scheduleNodes.add(sched.clone());
						}
					}
					//recursive call on the next cycle
					traverseGraph(sched, chFrom, leftToAdd - 1, newElm + 1, nextElm + 1);
				}
			}
		}
	}	

	/**
	 * Checks if the graph is complete
	 * Employs recursion to compare subject with all subsequent ones
	 * 
	 * @param schedule of schedule nodes (vertexes of a graph) 
	 * @param gen the round of recursion
	 * @return true if graph is complete, false if it isn't
	 */
	private boolean isComplete(int[] schedule, int gen) {

		boolean[] compareTo = matrix.getNodeConnections(schedule[gen]);

		// gen+1 in order not to compare to itself
		for (int i = gen + 1; i < schedule.length; i++) {
			//if there is a conflict - the combination not valid, return false 
			if (compareTo[schedule[i]] == true)
				return false;
		}
		// if the method hasn't checked all the elements, recursion
		if (gen < schedule.length - 1)
			return isComplete(schedule, gen + 1);

		return true;
	}

	/**
	 * Traverses the graph to find every combination possible
	 * and verifies the schedules 
	 *  
	 * @param numS 
	 */
	private void createValidSchedules(int numS) {
		
		traverseGraph(new int[numS], matrix.validNodes(), numS, 0, 0);	
	}


	public DefaultListModel<String> getScheduleOptions() {

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		StringBuilder helper;
		int scheduleNumber = 1;

		if (!scheduleNodes.isEmpty()) {
			for (int[] list : scheduleNodes) {
				listModel.addElement("#" + scheduleNumber);
				for (int num : list) {
					helper = new StringBuilder();
					SchedNode node = allNodes.get(num);
					helper.append(node.getSubjectName());
					if (node.getLectureSection() != null) {
						if (node.getLabSection() != null) {
							helper.append(" (" + node.getLectureName() + " + " + node.getLabName() + ")");
						}
						else {
							helper.append(" (" + node.getLectureName() + ")");
						}
					}
					else {
						helper.append(" (" + node.getLabName() + ")");
					}
					listModel.addElement(helper.toString());
				}
				listModel.addElement(System.getProperty("line.separator"));
				scheduleNumber++;
			}
		}
		else {
			listModel.addElement("No possible schedules found!");
		}

		return listModel;
	}



	/**
	 * Turns the subjects lectures and labs into individual nodes
	 * 
	 * @param classes the chosen classes
	 */
	private void classesToNodes(ArrayList<Subject> classes) {	

		allNodes = new ArrayList<SchedNode>();

		for (Subject subject : classes) {
			allNodes.addAll(subject.getAllNodes());
		}	
	}

	// THIS IS WRITTEN FOR THE TESTER
	public ArrayList<int[]> getSchLists() {

		return scheduleNodes;	
	}
}

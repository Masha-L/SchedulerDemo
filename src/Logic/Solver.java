package Logic;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import Database.Subject;

/**
 * The main logic class that creates the schedule options
 * 
 * @author Maria and Sofia
 */
public class Solver {

	// The schedule options represented by the node indexes
	private ArrayList<int[]> scheduleOptions = new ArrayList<int[]>();
	// The node matrix
	private Matrix matrix;
	// The ordered list of all schedule nodes
	private ArrayList<Node> allNodes;

	/**
	 * Constructor.
	 * Creates the list of nodes, builds the adjacency matrix,
	 * and generates the list of schedules.
	 * 
	 * @param classes - chosen by user
	 * @param numS - the number of the subjects the student wants to take
	 */
	public Solver(ArrayList<Subject> classes, int numS) {
		classesToNodes(classes);
		matrix = new Matrix(allNodes, numS);	

		createValidSchedules(numS);
	}


	/**
	 * Creates all possible combinations of K elements using N valid subjects 
	 * and adds each valid combination to the list of schedules 
	 *
	 * @param sched - the empty array of size K
	 * @param validNodes - the array of valid vertices 
	 * @param gen - the level of recursion (generation)
	 * @param startFrom - points to the next place in the array to fill
	 */
	private void traverseGraph(int[] sched, int[] chFrom, int gen, int startFrom) {

		// Checks that we are not out of bounds yet and haven't explored all possible options
		if (gen < sched.length && startFrom < chFrom.length) {

			/*
			 * At first, sets the pointer to the next place in the empty 
			 * array to be filled. Next, sets the pointer to the next 
			 * available element in the array of options. Adds the
			 * available element at the empty place and checks if the 
			 * schedule is complete (the array is full). 
			 * If the schedule is complete, checks it for validity. Adds
			 * the valid schedule to the list of options. If the schedule
			 * is not complete yet, recursively calls on the next cycle
			 * going to the next available option.
			 */
			for (int newElm = gen; newElm < sched.length; newElm++) {
				for (int nextElm = startFrom; nextElm < chFrom.length; nextElm++) {
					sched[newElm] = chFrom[nextElm];
					
					if (gen == sched.length - 1) {
						if (isComplete(sched, 0)) {
							scheduleOptions.add(sched.clone());
						}
					}

					traverseGraph(sched, chFrom, newElm + 1, nextElm + 1);
				}
			}
		}
	}	

	/**
	 * Checks if the graph is complete. Employs recursion 
	 * to compare each subject with the other ones
	 * 
	 * @param schedule - the array of the node indices
	 * @param gen - the round of recursion
	 * @return true if the graph is complete
	 */
	private boolean isComplete(int[] schedule, int gen) {

		// The node's relations to the other nodes
		boolean[] conflicts = matrix.getNodeConnections(schedule[gen]);

		// gen+1 in order not to compare to itself
		for (int i = gen + 1; i < schedule.length; i++) {
			// If there is a conflict, returns false 
			if (conflicts[schedule[i]] == true)
				return false;
		}
		// If the method hasn't checked all the elements, recursion
		if (gen < schedule.length - 1)
			return isComplete(schedule, gen + 1);

		return true;
	}

	/**
	 * Traverses the graph to find every combination possible
	 * and verifies the schedules 
	 *  
	 * @param numS - the preferred number of classes
	 */
	private void createValidSchedules(int numS) {
		/*
		 * Creates an empty array for the schedules;
		 * gets the valid nodes from the matrix;
		 * uses the preferred number of classes
		 * and the start indices for recursion.
		 */
		traverseGraph(new int[numS], matrix.validNodes(), 0, 0);	
	}


	/**
	 * Creates the list with all the valid schedule options.
	 * 
	 * @return the list of the schedule options
	 */
	public DefaultListModel<String> getScheduleOptions() {

		// This is messy and scary, and at this point I am too lazy to comment it
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		StringBuilder helper;
		int scheduleNumber = 1;

		if (!scheduleOptions.isEmpty()) {
			for (int[] list : scheduleOptions) {
				listModel.addElement("#" + scheduleNumber);
				for (int num : list) {
					helper = new StringBuilder();
					Node node = allNodes.get(num);
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
	 * Turns the subjects lectures and labs into unique nodes
	 * 
	 * @param classes - the subjects to form the nodes from
	 */
	private void classesToNodes(ArrayList<Subject> classes) {	
		allNodes = new ArrayList<Node>();

		for (Subject subject : classes) {
			allNodes.addAll(subject.getAllNodes());
		}	
	}

	// FOR THE TESTER	
	public int getScheduleOptionsNumber() {
		return scheduleOptions.size();
	}
}

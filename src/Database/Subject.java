package Database;
import java.util.ArrayList;
import Logic.SchedNode;

/**
 * Holds the information about a subject: 
 * the lists of lecture sections and the labs;
 * its name, description, and the number of credits.
 */
public class Subject implements Comparable {

	// The name of the subject
	private String name;
	// The list of lectures (may be null)
	private ArrayList<Section> lectures;
	// The list of labs (may be null)
	private ArrayList<Section> labs;

	/**
	 * Constructor for a subject (name).
	 * The method is required for the GUI.
	 * 
	 * @param name the name of the subject
	 */
	public Subject(String name) {
		this.name = name;
	}

	/**
	 * Constructs a subject (name, lectures, labs)
	 * 
	 * @param name the name
	 * @param lectures the list of lectures
	 * @param labs the list of labs
	 */
	public Subject(String name, ArrayList<Section> lectures, ArrayList<Section> labs) {
		this.name = name;
		this.lectures = lectures;
		this.labs = labs;
	}

	/**
	 * Returns the list of the subject's nodes
	 * 
	 * @precondition: subject has at least one lecture section or one lab
	 * @return the list of the subject's nodes
	 */
	public ArrayList<SchedNode> getAllNodes() {

		ArrayList<SchedNode> nodeList = new ArrayList<SchedNode>();

		// A node with no lectures
		if (lectures.isEmpty()) {
			for (Section lab : labs) {
				nodeList.add(new SchedNode(null, lab, this));

			}
		}

		// A node with no labs
		else if (labs.isEmpty()) {
			for (Section lecture : lectures) {
				nodeList.add(new SchedNode(lecture, null, this));

			}
		}

		// A node with both labs and lectures
		else {
			for (Section lecture : lectures) {
				for (Section lab : labs) {
					nodeList.add(new SchedNode(lecture, lab, this));
				}

			}
		}

		return nodeList;
	}

	/**
	 * Returns the name of the subject
	 * 
	 * @return the subject's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the string representation of the subject
	 * 
	 * @return the string representation of the subject
	 */
	public String toString() {
		return name;
	}

	/**
	 * Compares the subject to the other object
	 * 
	 * @return 0 if the subjects are equal
	 */
	@Override
	public int compareTo(Object o) {
		if (this != o) {
			return -1;
		}
		return 0;
	}
}
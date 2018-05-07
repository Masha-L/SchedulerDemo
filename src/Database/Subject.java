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
	// The list of lectures
	private ArrayList<Section> lectures;
	// The list of labs (may be null)
	private ArrayList<Section> labs;

	/**
	 * Constructor 
	 * 
	 * @param name
	 */
	public Subject(String name) {
		this.name = name;
	}

	/**
	 * Constructs a subject
	 * 
	 * @param name the name
	 * @param description the description
	 * @param lectures the list of lectures
	 * @param labs the list of labs
	 * @param credits the number of credits
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

		if (lectures.isEmpty()) {
			for (Section lab : labs) {
				nodeList.add(new SchedNode(null, lab, this));

			}
		}

		else if (labs.isEmpty()) {
			for (Section lecture : lectures) {
				nodeList.add(new SchedNode(lecture, null, this));

			}
		}

		else {
			for (Section lecture : lectures) {
				for (Section lab : labs) {
					nodeList.add(new SchedNode(lecture, lab, this));
				}

			}
		}

		return nodeList;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Object o) {
		if (this != o) {
			return -1;
		}
		return 0;
	}
}
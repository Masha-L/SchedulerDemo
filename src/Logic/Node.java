package Logic;
import Database.Section;
import Database.Subject;

/**
 * A unique combination of a subject's lab and lecture sections.
 * 
 * @author Maria and Sofia
 */
public class Node {
	// The lecture section
	private Section lectureSection; 
	// The lab section
	private Section labSection;
	// The reference to the subject
	private Subject subject;

	/**
	 * Constructor
	 * 
	 * @param lectureSection - a lecture section 
	 * @param labSection - a lab section 
	 * @param subject - the subject
	 */
	public Node(Section lectureSection, Section labSection, Subject subject) {
		this.lectureSection = lectureSection;
		this.labSection = labSection;
		this.subject = subject;
	}

	/**
	 * Returns the subject of the node
	 * 
	 * @return the subject reference
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Returns the lecture section
	 * 
	 * @return the lecture section of the node
	 */
	public Section getLectureSection() {
		return lectureSection;
	}

	/**
	 * Returns the lab section
	 * 
	 * @return the lab section of the node 
	 */
	public Section getLabSection() {
		return labSection;
	}


	/**
	 * Checks if this node conflicts with the other
	 * 
	 * @param node - the node to compare with
	 * @return true if there is a conflict
	 */
	public boolean conflicts(Node node) {

		// If the subject is the same, there is a conflict.
		if (subject.equals(node.getSubject())) 
			return true;

		Section lectures = node.getLectureSection();
		Section labs = node.getLabSection();

		// Checks if lectures and/or labs of the nodes have conflicts
		if (lectureSection != null && lectures != null)
			if (lectureSection.conflicts(lectures))
				return true;

		if (lectureSection != null && labs != null)
			if (lectureSection.conflicts(labs))
				return true;

		if (labSection != null && lectures != null)
			if (labSection.conflicts(lectures)) 
				return true;

		if (labSection != null && labs != null)
			if (labSection.conflicts(labs)) 
				return true;

		return false;
	}

	/**
	 * Returns the subject name
	 * 
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subject.getName();
	}

	/**
	 * Returns the name of the lab
	 * 
	 * @return the lab name
	 */
	public String getLabName() {
		if (labSection != null) {
			return labSection.toString();		
		}
		return null;
	}

	/**
	 * Returns the lecture name
	 * 
	 * @return the lecture name 
	 */
	public String getLectureName() {
		if (lectureSection != null) {
			return lectureSection.toString();
		}
		return null;
	}
}

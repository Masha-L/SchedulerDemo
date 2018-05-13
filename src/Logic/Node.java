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
		
		if (!conflicts(lectureSection, lectures))
			if (!conflicts(lectureSection, labs))
				if (!conflicts(labSection, lectures))
					if (!conflicts(labSection, labs))
						return false;
		return true;
	}

	/**
	 * Checks if two sections are in conflict.
	 * 
	 * @param sec1 - the first section
	 * @param sec2 - the second section
	 * @return true if there is a conflict
	 */
	private boolean conflicts(Section sec1, Section sec2) {
		if (sec1 != null && sec2 != null)
			if (sec1.conflicts(sec2)) 
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

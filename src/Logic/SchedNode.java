package Logic;
import Database.Section;
import Database.Subject;

/**
 * The schedule node - combination of a lab section and lecture section
 * Contains a reference to the Subject
 * 
 * @author Maria and Sofia
 */
public class SchedNode {
	//lecture section
	private Section lectureSection; 
	//lab section
	private Section labSection;
	//subject reference
	private Subject subject;

	/**
	 * Constructor
	 * 
	 * @param lectureSection - a lecture section for this node
	 * @param labSection - a lab section for this node 
	 * @param subject - a reference to the subject
	 */
	public SchedNode(Section lectureSection, Section labSection, Subject subject) {
		this.lectureSection = lectureSection;
		this.labSection = labSection;
		this.subject = subject;
	}

	/**
	 * Returns the subject of the node
	 * 
	 * @return the subject the reference to the course
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Returns the lecture section
	 * 
	 * @return lectureSection the lecture section of the node
	 */
	public Section getLectureSection() {
		return lectureSection;
	}

	/**
	 * Returns the lab section
	 * 
	 * @return labSection the lab section of the node 
	 */
	public Section getLabSection() {
		return labSection;
	}


	/**
	 * Checks if the node conflicts with this one
	 * 
	 * @param node the node to compare with
	 * @return true if there is a conflict
	 */
	public boolean conflicts(SchedNode node) {

		//checks if the nodes are the same subject nodes 
		// sets them in conflict if they are
		if (subject.equals(node.getSubject())) 
			return true;

		Section lectures = node.getLectureSection();
		Section labs = node.getLabSection();

		//checks if lectures conflict with each other
		if (lectureSection != null && lectures != null)
			if (lectureSection.conflicts(lectures))
				return true;

		//checks if lectures from this node and labs from another are in conflict
		if (lectureSection != null && labs != null)
			if (lectureSection.conflicts(labs))
				return true;

		//checks if labs from this node and lectures from another are in conflict
		if (labSection != null && lectures != null)
			if (labSection.conflicts(lectures)) 
				return true;

		//checks if labs from this node and labs from another are in conflict
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

	public String getLabName() {
		if (labSection != null) {
			return labSection.getName();		
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
			return lectureSection.getName();
		}
		return null;
	}
}

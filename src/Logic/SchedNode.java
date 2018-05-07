package Logic;
import Database.Section;
import Database.Subject;

public class SchedNode {
	private Section lectureSection; 
	private Section labSection;
	private Subject subject;

	/**
	 * Constructor
	 * 
	 * @param lectureSection
	 * @param labSection
	 * @param subject 
	 */
	public SchedNode(Section lectureSection, Section labSection, Subject subject) {
		this.lectureSection = lectureSection;
		this.labSection = labSection;
		this.subject = subject;
	}

	/**
	 * Returns the subject of the node
	 * 
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Returns the lecture section
	 * 
	 * @return lectureSection
	 */
	public Section getLectureSection() {
		return lectureSection;
	}

	/**
	 * Returns the lab section
	 * 
	 * @return labSection
	 */
	public Section getLabSection() {
		return labSection;
	}


	/**
	 * Checks if the node conflicts with this one
	 * 
	 * @param node
	 * @return true if there is a conflict
	 */
	public boolean conflicts(SchedNode node) {

		if (subject.equals(node.getSubject())) 
			return true;

		Section lectures = node.getLectureSection();
		Section labs = node.getLabSection();

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

	public String getSubjectName() {
		return subject.getName();
	}

	public String getLabName() {
		if (labSection != null) {
			return labSection.getName();		
		}
		return null;
	}

	public String getLectureName() {
		if (lectureSection != null) {
			return lectureSection.getName();
		}
		return null;
	}
}

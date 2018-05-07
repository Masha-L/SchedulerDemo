package Database;

import java.util.ArrayList;

/**
 * Holds the list of lectures for a given section.
 */
public class Section {

	// An array of classes/labs for the section
	private ArrayList<TimePeriod> classes;

	// Subject/name reference
	private String name;
	
	/**
	 * Constructs a section (a set of lectures / a lab)
	 * 
	 * @param subject
	 * @param classes
	 * @param isLab
	 */
	public Section(String name, ArrayList<TimePeriod> classes) {
		this.name = name;
		this.classes = classes;
	}

	/**
	 * Returns the section's classes
	 * 
	 * @return classes
	 */
	public ArrayList<TimePeriod> getClasses() {
		return classes;
	}

	public boolean conflicts(Section section) {

		ArrayList<TimePeriod> otherClasses = section.getClasses();

		for (TimePeriod firstClass : classes)
			for (TimePeriod secondClass : otherClasses) 
				if (firstClass.overlap(secondClass))
					return true;		

		return false;
	}

	public String getName() {
		return name;
	}
}

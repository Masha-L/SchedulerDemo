package Database;

import java.util.ArrayList;

/**
 * Holds the information for a given section:
 * the list of lectures/labs and the name.
 */
public class Section {

	// An array of classes/labs for the section
	private ArrayList<TimePeriod> classes;

	// The name of the section (e.g. Lab #1)
	private String name;
	
	/**
	 * Constructs a section (a set of lectures / a lab)
	 * 
	 * @param name the name of the section
	 * @param classes the list of classes
	 */
	public Section(String name, ArrayList<TimePeriod> classes) {
		this.name = name;
		this.classes = classes;
	}

	/**
	 * Checks if the section conflicts with the other.
	 * 
	 * @return false if there is no conflict
	 */
	public boolean conflicts(Section section) {
		// Gets the classes of the other section
		ArrayList<TimePeriod> otherClasses = section.getClasses();

		// Compares all classes to find a conflict
		for (TimePeriod firstClass : classes)
			for (TimePeriod secondClass : otherClasses) 
				if (firstClass.overlap(secondClass))
					return true;		
		return false;
	}

	/**
	 * Returns the list of section's classes
	 * 
	 * @return classes the classes
	 */
	public ArrayList<TimePeriod> getClasses() {
		return classes;
	}
		
	/**
	 * Returns the name of the section
	 * 
	 * @return name of the section
	 */
	public String getName() {
		return name;
	}
}

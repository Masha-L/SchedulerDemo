package Database;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
/**
 * Provides the database for the offered classes.
 * Uses TreeMap to map departments' names to the list 
 * of subjects they offer.
 * 
 * @author Maria and Sofia
 */
public class Database {

	private TreeMap <String, ArrayList<Subject>> classesTable;

	/**
	 * Constructor.
	 * Initializes the TreeMap.
	 */
	public Database() {
		classesTable = new TreeMap<String, ArrayList<Subject>>();
	}

	/**
	 * Adds a new subject to the database
	 * 
	 * @param department the department's name
	 * @param subject the subject to add
	 */
	public void addSubject(String department, Subject subject) {
		/*
		 *  Creates a new department, if it did not exist prior.
		 *  Then, adds the subject to the department.
		 */

		if (classesTable.get(department) == null) {
			classesTable.put(department, new ArrayList<Subject>());
		}
		classesTable.get(department).add(subject);
	}

	/**
	 * Returns the departments' names listed.
	 * The method is required for the GUI.
	 * 
	 * @return the array of departments' names
	 */
	public String[] getDepartmentNames() {
		/*
		 * Gets the keys for the table and 
		 * puts the into a string array
		 */
		
		Set<String> keySet = classesTable.keySet();
		String[] names = new String[keySet.size() + 1];
		
		// The default value for the combo box
		names[0] = "Select Department";

		int count = 1;
		for (String name : keySet) {
			names[count] = name;
			count++;
		}

		return names;
	}


	/**
	 * Returns all subjects of the given department.
	 * The method is required for the GUI.
	 * 
	 * @param depName department's name
	 * @return the array of subjects
	 */
	public Subject[] getClasses(String depName) {
		/*
		 * Gets the entry for the department's name 
		 * and lists the names of every subject
		 * that belongs to this department.
		 */

		if (depName == "Select Department") {
			return null;
		}

		ArrayList<Subject> subjectList = classesTable.get(depName);
		Subject[] subjects = new Subject[subjectList.size() + 1];
		
		// The default value for the combo box
		subjects[0] = new Subject("");

		int count = 1;
		for (Subject subj : subjectList) {
			subjects[count] = subj;
			count++;
		}

		return subjects;
	}
}
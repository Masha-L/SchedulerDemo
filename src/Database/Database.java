package Database;
import java.util.ArrayList;
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
	private ArrayList <String> departmentNames;

	/**
	 * Constructor.
	 * Initializes the TreeMap.
	 */
	public Database() {
		classesTable = new TreeMap<String, ArrayList<Subject>>();
		departmentNames = new ArrayList<String>();
	}

	/**
	 * Adds a new subject to the database
	 * 
	 * @param department - the department's name
	 * @param subject - the subject to add
	 */
	public void addSubject(String department, Subject subject) {
		/*
		 *  Creates a new department, if it did not exist before.
		 *  Adds department name to the list of names.
		 *  Then, adds the subject to the department.
		 */
		ArrayList<Subject> currentDepartment = classesTable.get(department);
		
		if (currentDepartment == null) {		
			departmentNames.add(department);
			
			ArrayList<Subject> subjectList = new ArrayList<Subject>();
			subjectList.add(subject);
			classesTable.put(department, subjectList);
		}
		else
			currentDepartment.add(subject);
	}

	/**
	 * Puts the department names into a string array.
	 * The method is required for the GUI.
	 * 
	 * @return the array of departments' names
	 */
	public String[] getDepartmentNames() {
		String[] names = new String[departmentNames.size() + 1];
		
		// The default value for the combo box
		names[0] = "Select Department";

		int count = 1;
		for (String name : departmentNames) {
			names[count] = name;
			count++;
		}

		return names;
	}


	/**
	 * Returns all subjects of the given department.
	 * The method is required for the GUI.
	 * 
	 * @param depName - the department's name
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
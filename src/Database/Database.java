package Database;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
/**
 * Provides the database of the offered classes.
 * 
 */
public class Database {

	private TreeMap <String, ArrayList<Subject>> classesTable = new TreeMap<String, ArrayList<Subject>>();

	/**
	 * Initial constructor
	 */
	public Database() {

	}

	/**
	 * Adds a new subject
	 * 
	 * @param department
	 * @param subject
	 */
	public void addSubject(String department, Subject subject) {	
		if (classesTable.get(department) == null) {
			classesTable.put(department, new ArrayList<Subject>());
		}
		classesTable.get(department).add(subject);
	}

	/**
	 * Returns the array of departments' names
	 * @return
	 */
	public String[] getDepartmentNames() {
		Set<String> keySet = classesTable.keySet();

		String[] names = new String[keySet.size() + 1];
		names[0] = "Select Department";

		int count = 1;
		for (String name : keySet) {
			names[count] = name;
			count++;
		}

		return names;
	}


	/**
	 * 
	 * 
	 * @param depName department's name
	 * @return the list of classes
	 */
	public Subject[] getClasses(String depName) {

		if (depName == "Select Department") {
			return null;
		}

		ArrayList<Subject> subjectList = classesTable.get(depName);
		
		Subject[] subjects = new Subject[subjectList.size() + 1];
		subjects[0] = new Subject("");

		int count = 1;
		for (Subject subj : subjectList) {
			subjects[count] = subj;
			count++;
		}

		return subjects;
	}
}


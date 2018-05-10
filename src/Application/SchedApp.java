package Application;
import java.util.ArrayList;
import javax.swing.JFrame;
import Database.Database;
import Database.Section;
import Database.Subject;
import Database.TimePeriod;
import View.SchedController;

/**
 * The main class that runs the application. 
 * 
 * @author Maria and Sofia
 */
public class SchedApp {

	/**
	 * The main method.
	 * Sets up the controller, prepares the database.
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Scheduler");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SchedController(createTestDatabase()));
		frame.setVisible(true);
	}

	/**
	 * Predefined contents of the database
	 * 
	 * @return the database
	 */
	private static Database createTestDatabase() {	

		// First Year Chinese
		ArrayList<TimePeriod> chineseLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> chineseLecture2 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> chineseLab1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> chineseLab2 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes1 = new ArrayList<Section>();
		ArrayList<Section> labs1 = new ArrayList<Section>();
		chineseLecture1.add(new TimePeriod(9.30, 10.20, 1));
		chineseLecture1.add(new TimePeriod(9.30, 10.20, 3));
		chineseLecture1.add(new TimePeriod(9.30, 10.20, 5));
		chineseLecture2.add(new TimePeriod(11.00, 11.50, 1));
		chineseLecture2.add(new TimePeriod(11.00, 11.50, 3));
		chineseLecture2.add(new TimePeriod(11.00, 11.50, 5));
		chineseLab1.add(new TimePeriod(10.00, 10.50, 2));	
		chineseLab1.add(new TimePeriod(10.00, 10.50, 4));	
		chineseLab2.add(new TimePeriod(11.30, 12.20, 2));	
		chineseLab2.add(new TimePeriod(11.30, 12.20, 4));	
		classes1.add(new Section("Lecture 1", chineseLecture1));
		classes1.add(new Section("Lecture 2", chineseLecture2));
		labs1.add(new Section("Lab 1", chineseLab1));
		labs1.add(new Section("Lab 2", chineseLab2));		

		// First Year Korean
		ArrayList<TimePeriod> koreanLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> koreanLecture2 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes2 = new ArrayList<Section>();
		ArrayList<Section> labs2 = new ArrayList<Section>();
		koreanLecture1.add(new TimePeriod(9.30, 10.20, 1));
		koreanLecture1.add(new TimePeriod(9.30, 10.20, 3));
		koreanLecture1.add(new TimePeriod(9.30, 10.20, 5));
		koreanLecture2.add(new TimePeriod(11.00, 11.50, 1));
		koreanLecture2.add(new TimePeriod(11.00, 11.50, 3));
		koreanLecture2.add(new TimePeriod(11.00, 11.50, 5));
		classes2.add(new Section("Lecture 1", koreanLecture1));
		classes2.add(new Section("Lecture 2", koreanLecture2));

		// Cell and Molecular Biology
		ArrayList<TimePeriod> cellLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> cellLab1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> cellLab2 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> cellLab3 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> cellLab4 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes3 = new ArrayList<Section>();
		ArrayList<Section> labs3 = new ArrayList<Section>();
		cellLecture1.add(new TimePeriod(11.00, 12.15, 1));
		cellLecture1.add(new TimePeriod(11.00, 12.15, 3));
		cellLecture1.add(new TimePeriod(11.00, 12.15, 5));
		cellLab1.add(new TimePeriod(13.30, 16.20, 1));
		cellLab2.add(new TimePeriod(13.30, 16.20, 2));
		cellLab3.add(new TimePeriod(13.30, 16.20, 3));
		cellLab4.add(new TimePeriod(13.30, 16.20, 4));
		classes3.add(new Section("Lecture", koreanLecture1));
		labs3.add(new Section("Lab 1", cellLab1));
		labs3.add(new Section("Lab 2", cellLab2));
		labs3.add(new Section("Lab 3", cellLab3));
		labs3.add(new Section("Lab 4", cellLab4));

		// Teaching Children Science
		ArrayList<TimePeriod> teachingLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes4 = new ArrayList<Section>();
		ArrayList<Section> labs4 = new ArrayList<Section>();
		teachingLecture1.add(new TimePeriod(13.30, 16.20, 5));
		classes4.add(new Section("", teachingLecture1));

		// Intro to Computing Systems
		ArrayList<TimePeriod> introLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> introLab1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> introLab2 = new ArrayList<TimePeriod>();	
		ArrayList<Section> classes5 = new ArrayList<Section>();
		ArrayList<Section> labs5 = new ArrayList<Section>();
		introLecture1.add(new TimePeriod(11.30, 12.45, 2));
		introLecture1.add(new TimePeriod(11.30, 12.45, 4));
		introLab1.add(new TimePeriod(8.30, 9.20, 5));
		introLab2.add(new TimePeriod(9.55, 10.45, 5));			
		classes5.add(new Section("Lecture", introLecture1));
		labs5.add(new Section("Lab 1", introLab1));
		labs5.add(new Section("Lab 2", introLab2));		

		// Algorithms
		ArrayList<TimePeriod> algoLecture1 = new ArrayList<TimePeriod>();	
		ArrayList<Section> classes6 = new ArrayList<Section>();
		ArrayList<Section> labs6 = new ArrayList<Section>();
		algoLecture1.add(new TimePeriod(14.55, 16.10, 1));
		algoLecture1.add(new TimePeriod(14.55, 16.10, 3));
		algoLecture1.add(new TimePeriod(14.55, 15.45, 5));		
		classes6.add(new Section("", algoLecture1));		

		// Introduction to Hip Hop
		ArrayList<TimePeriod> hipHopLecture1 = new ArrayList<TimePeriod>();	
		ArrayList<Section> classes7 = new ArrayList<Section>();
		ArrayList<Section> labs7 = new ArrayList<Section>();
		hipHopLecture1.add(new TimePeriod(9.30, 10.45, 1));
		hipHopLecture1.add(new TimePeriod(9.30, 10.45, 3));
		hipHopLecture1.add(new TimePeriod(9.30, 10.45, 5));		
		classes7.add(new Section("", hipHopLecture1));

		// West African Dance
		ArrayList<TimePeriod> westDanceLecture1 = new ArrayList<TimePeriod>();		
		ArrayList<Section> classes8 = new ArrayList<Section>();
		ArrayList<Section> labs8 = new ArrayList<Section>();
		westDanceLecture1.add(new TimePeriod(13.30, 16.10, 2));	
		classes8.add(new Section("", westDanceLecture1));

		// Macroeconomic Theory
		ArrayList<TimePeriod> macroLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes9 = new ArrayList<Section>();
		ArrayList<Section> labs9 = new ArrayList<Section>();
		macroLecture1.add(new TimePeriod(13.30, 14.45, 2));
		macroLecture1.add(new TimePeriod(13.30, 14.45, 4));	
		classes9.add(new Section("", macroLecture1));

		// Microeconomic Theory
		ArrayList<TimePeriod> microLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> microLecture2 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes10 = new ArrayList<Section>();
		ArrayList<Section> labs10 = new ArrayList<Section>();
		microLecture1.add(new TimePeriod(9.30, 10.45, 1));
		microLecture1.add(new TimePeriod(9.30, 10.45, 3));	
		microLecture2.add(new TimePeriod(11.00, 12.15, 1));
		microLecture2.add(new TimePeriod(11.00, 12.15, 3));	
		classes10.add(new Section("Lecture 1", microLecture1));
		classes10.add(new Section("Lecture 2", microLecture1));

		// Shakespeare
		ArrayList<TimePeriod> shakeLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes11 = new ArrayList<Section>();
		ArrayList<Section> labs11 = new ArrayList<Section>();
		shakeLecture1.add(new TimePeriod(13.30, 14.45, 1));
		shakeLecture1.add(new TimePeriod(13.30, 14.45, 3));	
		classes11.add(new Section("", shakeLecture1));

		// African American Literature I
		ArrayList<TimePeriod> afroLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes12 = new ArrayList<Section>();
		ArrayList<Section> labs12 = new ArrayList<Section>();
		afroLecture1.add(new TimePeriod(14.55, 16.10, 1));
		afroLecture1.add(new TimePeriod(14.55, 16.10, 3));	
		classes12.add(new Section("", afroLecture1));

		// Linear Algebra
		ArrayList<TimePeriod> linearLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> linearLecture2 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> linearLecture3 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes13 = new ArrayList<Section>();
		ArrayList<Section> labs13 = new ArrayList<Section>();
		linearLecture1.add(new TimePeriod(11.30, 12.45, 2));
		linearLecture1.add(new TimePeriod(11.30, 12.45, 4));
		linearLecture1.add(new TimePeriod(8.30, 9.20, 5));
		linearLecture2.add(new TimePeriod(8.30, 9.45, 2));
		linearLecture2.add(new TimePeriod(8.30, 9.45, 4));
		linearLecture2.add(new TimePeriod(8.30, 9.20, 5));
		linearLecture3.add(new TimePeriod(9.30, 10.45, 1));
		linearLecture3.add(new TimePeriod(9.30, 10.45, 3));
		linearLecture3.add(new TimePeriod(9.30, 10.20, 5));
		classes13.add(new Section("Lecture 1", linearLecture1));
		classes13.add(new Section("Lecture 2", linearLecture2));
		classes13.add(new Section("Lecture 3", linearLecture3));

		// Discrete Mathematics
		ArrayList<TimePeriod> discreteLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> discreteLecture2 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> discreteLecture3 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes14 = new ArrayList<Section>();
		ArrayList<Section> labs14 = new ArrayList<Section>();
		discreteLecture1.add(new TimePeriod(11.00, 12.15, 1));
		discreteLecture1.add(new TimePeriod(11.00, 12.15, 3));
		discreteLecture1.add(new TimePeriod(11.00, 12.15, 5));
		discreteLecture2.add(new TimePeriod(13.30, 14.45, 1));
		discreteLecture2.add(new TimePeriod(13.30, 14.45, 3));
		discreteLecture2.add(new TimePeriod(13.30, 14.45, 5));
		discreteLecture3.add(new TimePeriod(9.30, 10.45, 1));
		discreteLecture3.add(new TimePeriod(9.30, 10.45, 3));
		discreteLecture3.add(new TimePeriod(9.30, 10.45, 5));
		classes14.add(new Section("Lecture 1", discreteLecture1));
		classes14.add(new Section("Lecture 2", discreteLecture2));
		classes14.add(new Section("Lecture 3", discreteLecture3));

		// Electromagnetism
		ArrayList<TimePeriod> electroLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> electroLab1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> electroLab2 = new ArrayList<TimePeriod>();			
		ArrayList<Section> classes15 = new ArrayList<Section>();
		ArrayList<Section> labs15 = new ArrayList<Section>();
		electroLecture1.add(new TimePeriod(11.00, 12.15, 1));
		electroLecture1.add(new TimePeriod(11.00, 12.15, 3));
		electroLecture1.add(new TimePeriod(11.00, 12.15, 5));
		electroLab1.add(new TimePeriod(13.30, 16.20, 1));
		electroLab2.add(new TimePeriod(13.30, 16.20, 2));	
		classes15.add(new Section("Lecture", electroLecture1));
		labs15.add(new Section("Lab 1", electroLab1));
		labs15.add(new Section("Lab 2", electroLab2));	

		// Intro to Math Methods
		ArrayList<TimePeriod> mathMethLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> mathMethLab1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> mathMethLab2 = new ArrayList<TimePeriod>();			
		ArrayList<Section> classes16 = new ArrayList<Section>();
		ArrayList<Section> labs16 = new ArrayList<Section>();
		mathMethLecture1.add(new TimePeriod(14.55, 16.10, 1));
		mathMethLecture1.add(new TimePeriod(14.55, 16.10, 3));
		mathMethLab1.add(new TimePeriod(13.30, 16.20, 5));
		mathMethLab2.add(new TimePeriod(14.55, 15.45, 5));	
		classes16.add(new Section("Lecture", mathMethLecture1));
		labs16.add(new Section("Lab 1", mathMethLab1));
		labs16.add(new Section("Lab 2", mathMethLab2));	

		// Theories of Personality
		ArrayList<TimePeriod> persLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes17 = new ArrayList<Section>();
		ArrayList<Section> labs17 = new ArrayList<Section>();
		persLecture1.add(new TimePeriod(8.30, 9.45, 2));
		persLecture1.add(new TimePeriod(8.30, 9.45, 4));	
		classes17.add(new Section("", persLecture1));

		// Developmental Psychology
		ArrayList<TimePeriod> developLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes18 = new ArrayList<Section>();
		ArrayList<Section> labs18 = new ArrayList<Section>();
		developLecture1.add(new TimePeriod(9.30, 10.45, 1));
		developLecture1.add(new TimePeriod(9.30, 10.45, 3));	
		classes18.add(new Section("", developLecture1));

		// Sociology of Media
		ArrayList<TimePeriod> mediaLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes19 = new ArrayList<Section>();
		ArrayList<Section> labs19 = new ArrayList<Section>();
		mediaLecture1.add(new TimePeriod(13.30, 14.45, 2));
		mediaLecture1.add(new TimePeriod(13.30, 14.45, 4));	
		classes19.add(new Section("", mediaLecture1));

		// Criminology
		ArrayList<TimePeriod> crimLecture1 = new ArrayList<TimePeriod>();
		ArrayList<Section> classes20 = new ArrayList<Section>();
		ArrayList<Section> labs20 = new ArrayList<Section>();
		crimLecture1.add(new TimePeriod(11.00, 11.50, 1));
		crimLecture1.add(new TimePeriod(11.00, 11.50, 3));	
		crimLecture1.add(new TimePeriod(11.00, 11.50, 5));
		classes20.add(new Section("", crimLecture1));

		Database data = new Database();	
		data.addSubject("Asian Studies", new Subject("First Year Chinese", classes1, labs1));
		data.addSubject("Asian Studies", new Subject("First Year Korean", classes2, labs2));
		data.addSubject("Biological Sciences", new Subject("Cell and Molecular Biology", classes3, labs3));
		data.addSubject("Biological Sciences", new Subject("Teaching Children Science", classes4, labs4));
		data.addSubject("Computer Science", new Subject("Intro to Comp Systems", classes5, labs5));
		data.addSubject("Computer Science", new Subject("Algorithms", classes6, labs6));
		data.addSubject("Dance", new Subject("Introduction to Hip Hop", classes7, labs7));
		data.addSubject("Dance", new Subject("West African Dance", classes8, labs8));
		data.addSubject("Economics", new Subject("Macroeconomic Theory", classes9, labs9));
		data.addSubject("Economics", new Subject("Microeconomic Theory", classes10, labs10));
		data.addSubject("English", new Subject("Shakespeare", classes11, labs11));
		data.addSubject("English", new Subject("African American Literature I", classes12, labs12));
		data.addSubject("Mathematics", new Subject("Linear Algebra", classes13, labs13));
		data.addSubject("Mathematics", new Subject("Discrete Mathematics", classes14, labs14));
		data.addSubject("Physics", new Subject("Electromagnetism", classes15, labs15));
		data.addSubject("Physics", new Subject("Intro to Math Methods", classes16, labs16));
		data.addSubject("Psychology", new Subject("Theories of Personality", classes17, labs17));
		data.addSubject("Psychology", new Subject("Developmental Psychology", classes18, labs18));
		data.addSubject("Sociology", new Subject("Sociology of Media", classes19, labs19));
		data.addSubject("Sociology", new Subject("Criminology", classes20, labs20));	

		return data;
	}
}

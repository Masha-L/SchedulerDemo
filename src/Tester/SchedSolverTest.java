package Tester;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import Database.Section;
import Database.Subject;
import Database.TimePeriod;
import Logic.SchedSolver;

public class SchedSolverTest {

	public ArrayList<Subject> myClasses() {
		ArrayList<TimePeriod> introLecture1 = new ArrayList<TimePeriod>();
		introLecture1.add(new TimePeriod(11.30, 12.45, 2));
		introLecture1.add(new TimePeriod(11.30, 12.45, 4));
		ArrayList<TimePeriod> introLab1 = new ArrayList<TimePeriod>();
		introLab1.add(new TimePeriod(8.30, 9.20, 5));
		ArrayList<TimePeriod> introLab2 = new ArrayList<TimePeriod>();
		introLab2.add(new TimePeriod(9.55, 10.45, 5));				
		ArrayList<Section> classes1 = new ArrayList<Section>();
		ArrayList<Section> labs1 = new ArrayList<Section>();
		classes1.add(new Section("", introLecture1));
		labs1.add(new Section("Lab 1", introLab1));
		labs1.add(new Section("Lab 2", introLab2));		
		Subject intro = new Subject("Intro to Comp Systems", classes1, labs1);
		
		
		ArrayList<TimePeriod> algoLecture1 = new ArrayList<TimePeriod>();
		algoLecture1.add(new TimePeriod(14.55, 16.10, 1));
		algoLecture1.add(new TimePeriod(14.55, 16.10, 3));
		algoLecture1.add(new TimePeriod(14.55, 15.45, 5));			
		ArrayList<Section> classes2 = new ArrayList<Section>();
		ArrayList<Section> labs2 = new ArrayList<Section>();
		classes2.add(new Section("", algoLecture1));		
		Subject algo = new Subject("Algorithms", classes2, labs2);
		
		
		ArrayList<TimePeriod> electroLecture1 = new ArrayList<TimePeriod>();
		electroLecture1.add(new TimePeriod(13.30, 16.20, 2));
		electroLecture1.add(new TimePeriod(13.30, 16.20, 4));				
		ArrayList<Section> classes3 = new ArrayList<Section>();
		ArrayList<Section> labs3 = new ArrayList<Section>();
		classes3.add(new Section("", electroLecture1));
		Subject electro = new Subject("Electronics", classes3, labs3);
		
		
		ArrayList<TimePeriod> emLecture1 = new ArrayList<TimePeriod>();
		emLecture1.add(new TimePeriod(9.30, 10.45, 1));
		emLecture1.add(new TimePeriod(9.30, 10.45, 3));			
		ArrayList<Section> classes4 = new ArrayList<Section>();
		ArrayList<Section> labs4 = new ArrayList<Section>();
		classes4.add(new Section("", emLecture1));
		Subject emTheory = new Subject("E&M Theory", classes4, labs4);
		
		
		ArrayList<TimePeriod> statMechLecture1 = new ArrayList<TimePeriod>();
		statMechLecture1.add(new TimePeriod(13.30, 14.45, 1));
		statMechLecture1.add(new TimePeriod(13.30, 14.45, 3));
		statMechLecture1.add(new TimePeriod(13.30, 14.20, 5));				
		ArrayList<Section> classes5 = new ArrayList<Section>();
		ArrayList<Section> labs5 = new ArrayList<Section>();
		classes5.add(new Section("", statMechLecture1));
		Subject statMech = new Subject("Stat Mechanics", classes5, labs5);
		
		
		ArrayList<TimePeriod> animaLecture1 = new ArrayList<TimePeriod>();
		animaLecture1.add(new TimePeriod(12.30, 13.50, 2));
		animaLecture1.add(new TimePeriod(12.30, 13.50, 4));				
		ArrayList<Section> classes6 = new ArrayList<Section>();
		ArrayList<Section> labs6 = new ArrayList<Section>();
		classes6.add(new Section("", animaLecture1));
		Subject anima = new Subject("Animals/Robots", classes6, labs6);
		
		
		ArrayList<TimePeriod> aiResLecture1 = new ArrayList<TimePeriod>();
		aiResLecture1.add(new TimePeriod(10.30, 11.50, 2));
		aiResLecture1.add(new TimePeriod(10.30, 11.50, 4));				
		ArrayList<Section> classes7 = new ArrayList<Section>();
		ArrayList<Section> labs7 = new ArrayList<Section>();
		classes7.add(new Section("", aiResLecture1));
		Subject aiRes = new Subject("Research in AI", classes7, labs7);
		
		
		ArrayList<TimePeriod> lynkLecture1 = new ArrayList<TimePeriod>();
		lynkLecture1.add(new TimePeriod(19.15, 22.05, 1));			
		ArrayList<Section> classes8 = new ArrayList<Section>();
		ArrayList<Section> labs8 = new ArrayList<Section>();
		classes8.add(new Section("", lynkLecture1));
		Subject lynk = new Subject("Lynk Seminar", classes8, labs8);
		
		
		ArrayList<TimePeriod> psychLecture1 = new ArrayList<TimePeriod>();
		psychLecture1.add(new TimePeriod(9.30, 10.45, 1));		
		psychLecture1.add(new TimePeriod(9.30, 10.45, 3));	
		psychLecture1.add(new TimePeriod(9.30, 10.20, 5));	
		ArrayList<Section> classes9 = new ArrayList<Section>();
		ArrayList<Section> labs9 = new ArrayList<Section>();
		classes9.add(new Section("", psychLecture1));
		Subject psych = new Subject("Intro Psych", classes9, labs9);
		
		
		ArrayList<TimePeriod> socioLecture1 = new ArrayList<TimePeriod>();
		ArrayList<TimePeriod> socioLecture2 = new ArrayList<TimePeriod>();
		socioLecture1.add(new TimePeriod(10.00, 11.15, 2));		
		socioLecture1.add(new TimePeriod(10.00, 11.15, 4));	
		socioLecture2.add(new TimePeriod(13.30, 14.45, 1));	
		socioLecture2.add(new TimePeriod(13.30, 14.45, 3));	
		ArrayList<Section> classes10 = new ArrayList<Section>();
		ArrayList<Section> labs10 = new ArrayList<Section>();
		classes10.add(new Section("", socioLecture1));
		classes10.add(new Section("", socioLecture2));
		Subject socio = new Subject("Intro Socio", classes10, labs10);
		
		
		ArrayList<Subject> myClassesList = new ArrayList<Subject>();
		myClassesList.add(intro);
		myClassesList.add(algo);
		myClassesList.add(electro);
		myClassesList.add(emTheory);
		myClassesList.add(statMech);
		myClassesList.add(anima);
		myClassesList.add(aiRes);
		myClassesList.add(lynk);
		//myClassesList.add(psych);
		//myClassesList.add(socio);
		
		return myClassesList;
	}


	@Test
	/**
	 * ==================  CURRENTLY IS THE MAIN TEST  ==================
	 */
	public void scheduleTest() {
		
		// DISPLAY THE TIME
		
		// IMPLEMENT ISCHOSEN
		
		// IMPLEMENT CREDITS
		
		SchedSolver testSched = new SchedSolver(myClasses(), 5);
		assertEquals(testSched.getSchLists().size(), 21);
		//testSched.printScheduleOptions();
		
		//assertEquals(testSched.getSchListsTEST(), 21);
	}

}

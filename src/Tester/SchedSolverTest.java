package Tester;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Database.Section;
import Database.Subject;
import Database.TimePeriod;
import Logic.Solver;

public class SchedSolverTest {
	private Subject korean, cellMolecular, compSystems, algorithms, electromag, mathMethods;
	private Solver solver;

	@Before
	public void myClasses() {
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
		korean = new Subject("First Year Korean", classes2, labs2);

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
		classes3.add(new Section("Lecture", cellLecture1));
		labs3.add(new Section("Lab 1", cellLab1));
		labs3.add(new Section("Lab 2", cellLab2));
		labs3.add(new Section("Lab 3", cellLab3));
		labs3.add(new Section("Lab 4", cellLab4));
		cellMolecular = new Subject("Cell and Molecular Biology", classes3, labs3);

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
		compSystems = new Subject("Intro to Comp Systems", classes5, labs5);

		// Algorithms
		ArrayList<TimePeriod> algoLecture1 = new ArrayList<TimePeriod>();	
		ArrayList<Section> classes6 = new ArrayList<Section>();
		ArrayList<Section> labs6 = new ArrayList<Section>();
		algoLecture1.add(new TimePeriod(14.55, 16.10, 1));
		algoLecture1.add(new TimePeriod(14.55, 16.10, 3));
		algoLecture1.add(new TimePeriod(14.55, 15.45, 5));		
		classes6.add(new Section("", algoLecture1));	
		algorithms = new Subject("Algorithms", classes6, labs6);

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
		electromag = new Subject("Electromagnetism", classes15, labs15);		

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
		mathMethods = new Subject("Intro to Math Methods", classes16, labs16);
	}

	/**
	 * One Subject Schedule (one passed)
	 */
	@Test	
	public void test1() {
		ArrayList<Subject> classes = new ArrayList<Subject>();
		classes.add(cellMolecular);
		solver = new Solver(classes, 1);
		assertEquals(solver.getScheduleOptionsNumber(), 4);
	}


	/** 
	 * One Subjects Schedule (two passed)
	 */
	@Test	
	public void test2() {
		ArrayList<Subject> classes1 = new ArrayList<Subject>();
		classes1.add(cellMolecular);
		classes1.add(algorithms);
		solver = new Solver(classes1, 1);
		assertEquals(solver.getScheduleOptionsNumber(), 5);	

		ArrayList<Subject> classes2 = new ArrayList<Subject>();
		classes2.add(cellMolecular);
		classes2.add(korean);
		solver = new Solver(classes2, 1);
		assertEquals(solver.getScheduleOptionsNumber(), 6);

		ArrayList<Subject> classes3 = new ArrayList<Subject>();
		classes3.add(mathMethods);
		classes3.add(compSystems);
		solver = new Solver(classes3, 1);
		assertEquals(solver.getScheduleOptionsNumber(), 4);
	}

	/**
	 * One Subject Schedule (two passed, conflict)
	 */ 
	@Test	
	public void test3() {
		ArrayList<Subject> classes = new ArrayList<Subject>();
		classes.add(cellMolecular);
		classes.add(electromag);
		solver = new Solver(classes, 1);
		assertEquals(solver.getScheduleOptionsNumber(), 6);
	}

	/**
	 * Two Subjects Schedule (two passed, conflict)
	 */
	@Test	
	public void test4() {
		ArrayList<Subject> classes = new ArrayList<Subject>();
		classes.add(cellMolecular);
		classes.add(electromag);
		solver = new Solver(classes, 2);
		assertEquals(solver.getScheduleOptionsNumber(), 0);
	}


	// Two Subjects Schedule (two passed, no conflict)
	@Test	
	public void test5() {
		ArrayList<Subject> classes = new ArrayList<Subject>();
		classes.add(mathMethods);
		classes.add(compSystems);
		solver = new Solver(classes, 2);
		assertEquals(solver.getScheduleOptionsNumber(), 4);
	}

	/**
	 * TEST ME!!!
	 */
	//@Test
	public void test6() {

	}
}

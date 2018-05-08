package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Database.Database;
import Database.Subject;
import Logic.SchedSolver;

/**
 * Sets up the UI, displays the interactive elements.
 * Takes the user input and sends it to the logic segment.
 *
 * @author Maria and Sofia
 */
public class SchedController extends JPanel implements ActionListener {
	
	//Error messages
	public static final String REPETITIONS_MESSAGE = "No repetitions allowed!";
	public static final String EMPTY_FIELD_MESSAGE = "Fill out all the fields!";
	public static final String FEW_CLASSES_MESSAGE = "Not enough classes chosen!";

	//the combobox data
	private static final int MIN_NUMBER_CLASSES = 1;
	private static final int MAX_NUMBER_CLASSES = 8;

	private Database database;
	private JPanel selectionPanel = new JPanel();
	
	private JLabel greeting = new JLabel("What classes do you have in mind? ");
	private JLabel numberLabel = new JLabel("The optimal number of classes is");
	
	private JComboBox<Integer> classesNumber = new JComboBox<Integer>();
	private ArrayList<JComboBox<Subject>> chosenSubjects = new ArrayList<JComboBox<Subject>>();

	/**
	 * Creates a new controller for the app
	 * 
	 * @param data the subject database 
	 */
	public SchedController(Database data) {
		super(new BorderLayout());
		
		database = data;

		setupElements();
		createMainDisplay();
	}

	/**
	 * 
	 * @return
	 */
	private JPanel choiceWindows() {

		JPanel panel = new JPanel(new FlowLayout());
		JComboBox<String> mainComboBox = new JComboBox<String>(database.getDepartmentNames());
		JComboBox<Subject> subComboBox = new JComboBox<Subject>();

		mainComboBox.putClientProperty("JComboBox.isTableCellEditor", true);
		mainComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String item = (String) mainComboBox.getSelectedItem();
				Subject[] o = database.getClasses(item);

				if (o == null) {
					subComboBox.setModel( new DefaultComboBoxModel() );
					subComboBox.setEnabled(false);
				}
				else {
					subComboBox.setModel( new DefaultComboBoxModel( o ) );
					subComboBox.setEnabled(true);
				}

			}		
		});

		subComboBox.setPrototypeDisplayValue(new Subject("XXXXXXXXXXXXXXXXXXX"));
		subComboBox.setEnabled(false);
		chosenSubjects.add(subComboBox);

		panel.add(mainComboBox);
		panel.add(subComboBox);
		return panel;
	}

	/**
	 * The button to run the generation
	 * @return
	 */
	private JButton createOKButton() {
		JButton okButton = new JButton("Let's go!");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer numClasses = (Integer) classesNumber.getSelectedItem();
				if (numClasses == null) {
					numClasses = MIN_NUMBER_CLASSES;
				}

				ArrayList<Subject> validInput = checkStatus(numClasses);
				if (validInput != null) {
					SchedSolver testSched = new SchedSolver(validInput, numClasses);

					removeAll();
					createScheduleDisplay(testSched.getScheduleOptions());
					revalidate();
					repaint();
				}
			}
		});
		return okButton;
	}

	/**
	 * Checks if the algorithm is ready to run 
	 * 
	 * @param numClasses
	 * @return
	 */
	private ArrayList<Subject> checkStatus(int numClasses) {	
		ArrayList<Subject> validInput = collectValidSubjects(collectInput());

		if (numClasses > validInput.size()) {					
			if (numClasses == chosenSubjects.size()) {
				if (!allFilled()) {
					invalidStatusMessage(EMPTY_FIELD_MESSAGE);
					return null;
				}
				invalidStatusMessage(REPETITIONS_MESSAGE);
				return null;
			}		
			invalidStatusMessage(FEW_CLASSES_MESSAGE);
			return null;
		}

		if (allFilled()) {
			if (!noRepetitions()) {
				invalidStatusMessage(REPETITIONS_MESSAGE);
				return null;
			}		
		}

		return validInput;
	}

	/**
	 * Checks for repetitions 
	 * @return
	 */
	private boolean canAddNewSubject() {
		if (!allFilled()) {
			invalidStatusMessage(EMPTY_FIELD_MESSAGE);
			return false;
		}

		if (!noRepetitions()) {
			invalidStatusMessage(REPETITIONS_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Checks if it is reasonable to add a new field for a new subject
	 * 
	 * @precondition every field is not empty
	 * @return
	 */
	private boolean allFilled() {
		for (JComboBox<Subject> box : chosenSubjects) {		
			Subject subject = (Subject) box.getSelectedItem();
			if (subject == null || subject.toString().equals("")) {
				return false;
			}			
		}
		return true;
	}

	/**
	 * Checks for repetitions 
	 * @return
	 */
	private boolean noRepetitions() {		
		ArrayList<Subject> subjects = collectInput();
		return collectValidSubjects(subjects).size() == subjects.size();
	}


	/**
	 * Collects all the valid subjects
	 * 
	 * @return
	 */
	private ArrayList<Subject> collectValidSubjects(ArrayList<Subject> classes) {	
		ArrayList<Subject> validSubjects = new ArrayList<Subject>();

		for (Subject subject : classes) {
			if (!subject.toString().equals("") && !validSubjects.contains(subject)) {
				validSubjects.add(subject);
			}
		}
		return validSubjects;
	}


	/**
	 * Collects subjects from the JComboBoxes
	 * 
	 * @return
	 */
	private ArrayList<Subject> collectInput() {		
		ArrayList<Subject> classes = new ArrayList<Subject>();

		for (JComboBox<Subject> box : chosenSubjects) {	
			Subject subject = (Subject) box.getSelectedItem();
			if (subject != null) {
				classes.add(subject);
			}
		}

		return classes;
	}

	/**
	 * The button to input a new subject
	 * @return
	 */
	private JButton createNewFieldButton() {
		JButton newField = new JButton("Add Subject");
		newField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (canAddNewSubject()) {
					selectionPanel.add(choiceWindows(), chosenSubjects.size() - 1);
					revalidate();
					repaint();
				}
			}
		});
		return newField;
	}

	/**
	 * Try again button
	 * 
	 * @return
	 */
	private JButton createTryAgainButton() {
		JButton tryAgain = new JButton("Try Again");
		tryAgain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				createMainDisplay();		
				revalidate();
				repaint();
			}

		});

		return tryAgain;
	}


	/**
	 * Sets up the greeting and the selection panel
	 * @return
	 */
	private void setupElements() {
		greeting.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 20));
		greeting.setHorizontalAlignment(JLabel.CENTER);
		
		for (int number = MIN_NUMBER_CLASSES; number <= MAX_NUMBER_CLASSES; number++) {
			classesNumber.addItem(number);
		}
		
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < MIN_NUMBER_CLASSES; i++) {
			selectionPanel.add(choiceWindows());
		}
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(createNewFieldButton());
		selectionPanel.add(buttonPanel);
	}
	
	/**
	 * Pane for the class selection
	 * @return
	 */
	private JScrollPane createSelectionPane() {
		JScrollPane scroll = new JScrollPane(selectionPanel);
		scroll.setPreferredSize(new Dimension(getWidth(), getWidth()/2));
		return scroll;
	}

	/**
	 * Input panel for the main display
	 * @return
	 */
	private JPanel createInputPanel() {
		JPanel lowerPanel = new JPanel(new BorderLayout());
		JPanel numberPanel = new JPanel(new FlowLayout());
		JPanel okButtonPanel = new JPanel(new FlowLayout());

		numberPanel.add(numberLabel);
		numberPanel.add(classesNumber);
		okButtonPanel.add(createOKButton(), BorderLayout.CENTER);

		lowerPanel.add(numberPanel, BorderLayout.NORTH);
		lowerPanel.add(okButtonPanel, BorderLayout.SOUTH);

		return lowerPanel;
	}

	/**
	 * Panel for the schedule JList 
	 * @param list
	 * @return
	 */
	private JScrollPane createSchedulePanel(JList<String> list) {		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(getWidth(), 4*getHeight()/5));
		return scroll;
	}

	/**
	 * Display for class selection
	 */
	private void createMainDisplay() {	
		JPanel mainPanel = new JPanel(new BorderLayout());	
		mainPanel.add(greeting, BorderLayout.NORTH);
		mainPanel.add(createSelectionPane(), BorderLayout.CENTER);
		mainPanel.add(createInputPanel(), BorderLayout.SOUTH);
		add(new JScrollPane(mainPanel));
	}

	/**
	 * Display for schedules
	 * @param model
	 */
	private void createScheduleDisplay(DefaultListModel<String> model) {		
		JList<String> schedules = new JList<String>(model);	
		schedules.setSelectionModel(new NoSelectionModel());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(createTryAgainButton());
		JPanel mainPanel = new JPanel(new BorderLayout());			
		mainPanel.add(createSchedulePanel(schedules), BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(new JScrollPane(mainPanel));
	}


	/**
	 * Shows the invalid status message
	 * @param message
	 */
	private void invalidStatusMessage(String message) {

		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private static class NoSelectionModel extends DefaultListSelectionModel {

		@Override
		public void setAnchorSelectionIndex(final int anchorIndex) {}

		@Override
		public void setLeadAnchorNotificationEnabled(final boolean flag) {}

		@Override
		public void setLeadSelectionIndex(final int leadIndex) {}

		@Override
		public void setSelectionInterval(final int index0, final int index1) { }
	}
} 

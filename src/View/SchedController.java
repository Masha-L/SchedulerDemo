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

	// The text for the error messages
	public static final String REPETITIONS_MESSAGE = "No repetitions allowed!";
	public static final String EMPTY_FIELD_MESSAGE = "Fill out all the fields!";
	public static final String FEW_CLASSES_MESSAGE = "Not enough classes chosen!";

	// The number of minimum/maximum classes the user is allowed to take
	private static final int MIN_NUMBER_CLASSES = 1;
	private static final int MAX_NUMBER_CLASSES = 8;

	// The subject database
	private Database database;
	// The panel that holds the class selection combo boxes
	private JPanel selectionPanel = new JPanel();

	// The combo box for the preferred number of classes
	private JComboBox<Integer> classesNumber = new JComboBox<Integer>();
	
	// The labels for the interface
	private JLabel greeting = new JLabel("What classes do you have in mind? ");
	private JLabel numberLabel = new JLabel("The optimal number of classes is");

	// The list of the chosen subjects
	private ArrayList<JComboBox<Subject>> chosenSubjects = new ArrayList<JComboBox<Subject>>();

	/**
	 * Creates a new controller for the app
	 * 
	 * @param data - the subject database 
	 */
	public SchedController(Database data) {
		super(new BorderLayout());
		database = data;

		setupElements();
		createMainDisplay();
	}

	/**
	 * Creates a single line panel for class selection
	 * 
	 * @return the panel with the class selection boxes
	 */
	private JPanel choiceWindows() {

		// Creates the main and the secondary combo box
		JComboBox<String> mainComboBox = new JComboBox<String>(database.getDepartmentNames());
		JComboBox<Subject> subComboBox = new JComboBox<Subject>();

		// Sets up the main combo box
		mainComboBox.putClientProperty("JComboBox.isTableCellEditor", true);
		mainComboBox.addActionListener(new ActionListener() {

			/**
			 * If the main box is clicked, sets the 
			 * secondary box in the appropriate way.
			 * 
			 * @param e - the action event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String item = (String) mainComboBox.getSelectedItem();
				Subject[] o = database.getClasses(item);

				if (o == null) {
					subComboBox.setModel(new DefaultComboBoxModel());
					subComboBox.setEnabled(false);
				}
				else {
					subComboBox.setModel(new DefaultComboBoxModel(o));
					subComboBox.setEnabled(true);
				}

			}		
		});

		// Sets up the secondary combo box
		subComboBox.setPrototypeDisplayValue(new Subject("XXXXXXXXXXXXXXXXXXX"));
		subComboBox.setEnabled(false);
		chosenSubjects.add(subComboBox);

		// Creates the combo box panel
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(mainComboBox);
		panel.add(subComboBox);
		return panel;
	}

	/**
	 * The button to generate schedule options
	 * 
	 * @return the button
	 */
	private JButton createOKButton() {
		JButton okButton = new JButton("Let's go!");
		okButton.addActionListener(new ActionListener() {

			/**
			 * Responds to the click
			 * 
			 * @param e - the action event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// Checks the minimum number of classes
				Integer numClasses = (Integer) classesNumber.getSelectedItem();
				if (numClasses == null) {
					numClasses = MIN_NUMBER_CLASSES;
				}

				// If the input is valid, generate schedules
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
	 * @param numClasses - the number of classes to take
	 * @return null if the algorithm is not ready to run
	 * 		   otherwise, the list of subjects to consider
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
	 * Checks if it is reasonable to add a new combo box panel
	 * 
	 * @return true if can add the panel 
	 */
	private boolean canAddNewSubject() {
		// Decline, if there are empty lines
		if (!allFilled()) {
			invalidStatusMessage(EMPTY_FIELD_MESSAGE);
			return false;
		}

		// Decline, if there are repetitions
		if (!noRepetitions()) {
			invalidStatusMessage(REPETITIONS_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Checks if all selection boxes are filled out
	 * 
	 * @return true if all boxes are filled out
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
	 * Checks the selection boxes for repetitions 
	 * 
	 * @return true if there are no repetitions
	 */
	private boolean noRepetitions() {		
		ArrayList<Subject> subjects = collectInput();
		return collectValidSubjects(subjects).size() == subjects.size();
	}


	/**
	 * Collects all the valid subjects from the selection boxes
	 * 
	 * @return the list of unique subjects
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
	 * Collects all subjects from the boxes
	 * 
	 * @return the list of all the subjects from the boxes
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
	 * The button to create a new combo box line panel
	 * 
	 * @return the button
	 */
	private JButton createNewFieldButton() {
		JButton newField = new JButton("Add Subject");
		newField.addActionListener(new ActionListener() {

			/**
			 * If a panel can be added, creates new input boxes.
			 * 
			 * @param e - the action event
			 */
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
	 * Creates the 'try again' button
	 * 
	 * @return the button
	 */
	private JButton createTryAgainButton() {
		JButton tryAgain = new JButton("Try Again");
		tryAgain.addActionListener(new ActionListener() {

			/**
			 * Returns the display to the selection screen
			 * 
			 * @param e - the action event
			 */
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
	 * Sets up the initial program display.
	 */
	private void setupElements() {
		// Sets up the greeting label
		greeting.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 20));
		greeting.setHorizontalAlignment(JLabel.CENTER);

		// Sets up the class number selection box
		for (int number = MIN_NUMBER_CLASSES; number <= MAX_NUMBER_CLASSES; number++) {
			classesNumber.addItem(number);
		}

		// Creates the selection panel
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < MIN_NUMBER_CLASSES; i++) {
			selectionPanel.add(choiceWindows());
		}
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(createNewFieldButton());
		selectionPanel.add(buttonPanel);
	}

	/**
	 * Creates the scrollable pane for the class selection panel
	 * 
	 * @return the scrollable pane
	 */
	private JScrollPane createSelectionPane() {
		JScrollPane scroll = new JScrollPane(selectionPanel);
		scroll.setPreferredSize(new Dimension(getWidth(), getWidth()/2));
		return scroll;
	}

	/**
	 * Creates the panel for the main display that holds
	 * the class number selection and the 'go' button
	 * 
	 * @return the panel 
	 */
	private JPanel createInputPanel() {
		// Creates the class number selection panel
		JPanel numberPanel = new JPanel(new FlowLayout());
		numberPanel.add(numberLabel);
		numberPanel.add(classesNumber);
		
		// Creates the 'go' button panel
		JPanel okButtonPanel = new JPanel(new FlowLayout());
		okButtonPanel.add(createOKButton(), BorderLayout.CENTER);

		// Creates the main panel for the elements
		JPanel lowerPanel = new JPanel(new BorderLayout());
		lowerPanel.add(numberPanel, BorderLayout.NORTH);
		lowerPanel.add(okButtonPanel, BorderLayout.SOUTH);

		return lowerPanel;
	}

	/**
	 * Creates the scrollable pane for the list  
	 * with the schedule options.
	 * 
	 * @param list - the list with the schedules
	 * @return the scrollable pane
	 */
	private JScrollPane createSchedulePanel(JList<String> list) {		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(getWidth(), 4*getHeight()/5));
		return scroll;
	}

	/**
	 * Creates the main display for class selection
	 */
	private void createMainDisplay() {	
		JPanel mainPanel = new JPanel(new BorderLayout());	
		mainPanel.add(greeting, BorderLayout.NORTH);
		mainPanel.add(createSelectionPane(), BorderLayout.CENTER);
		mainPanel.add(createInputPanel(), BorderLayout.SOUTH);
		add(new JScrollPane(mainPanel));
	}

	/**
	 * Creates the secondary display for schedule options
	 * 
	 * @param model - the model for the list
	 */
	private void createScheduleDisplay(DefaultListModel<String> model) {
		// Creates the schedule options list
		JList<String> schedules = new JList<String>(model);	
		schedules.setSelectionModel(new NoSelectionModel());
		
		// Creates the 'try again' button
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(createTryAgainButton());
		
		// Creates the scrollable panel
		JPanel mainPanel = new JPanel(new BorderLayout());			
		mainPanel.add(createSchedulePanel(schedules), BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(new JScrollPane(mainPanel));
	}


	/**
	 * Shows the invalid status message
	 * 
	 * @param message - the text of the message
	 */
	private void invalidStatusMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * The listener method for the action elements.
	 * 
	 * @param e - the action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	/**
	 * The NoSelectionModel for the schedule option list.
	 */
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

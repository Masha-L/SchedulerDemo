package Database;

/**
 * Provides an abstraction for a single class. 
 * Uses 24-hour format to hold the time (0.00 - 23.59) 
 * Uses a short number to hold a day of the week (1 - Mon, 2 - Tue ... 7 - Sun).
 *
 */
public class TimePeriod {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Start time of the class
	private double startTime;
	// End time of the class
	private double endTime;
	// Weekday of the class
	private int weekDay;
	
	/**
	 * Constructs a Time Period.
	 *
	 * @param startTime the start time of the class
	 * @param endTime the end time of the class 
	 * @param weekDay the day of the week      
	 * @param facultyName name of the teacher
	 * @param roomAddress the room address
	 */	
	public TimePeriod(double startTime, double endTime, int weekDay) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekDay = weekDay;
	}

	/**
	 * Checks if two classes overlap
	 * 
	 * @param timePeriod another time period 
	 * @return true if they overlap and false if they do not
	 */
	public boolean overlap(TimePeriod timePeriod) {

		// We probably will need a buffer time margin
		
		if (weekDay == timePeriod.getWeekDay()) {
			if (startTime <= timePeriod.getEndTime() && startTime >= timePeriod.getStartTime())
				return true;
			if (timePeriod.getStartTime() <= endTime && timePeriod.getStartTime() >= startTime)
				return true;
		}
		return false;
		
	}

	public double getStartTime() {
		return startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public int getWeekDay() {
		return weekDay;
	}
}

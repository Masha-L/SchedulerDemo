package Database;

/**
 * Provides an abstraction for a single class time. 
 * Uses 24-hour format to hold the start/end time (0.00 - 23.59) 
 * Uses a short number to hold a day of the week (1 - Mon, 2 - Tue ... 7 - Sun).
 */
public class TimePeriod {
	// Start time of the class
	private double startTime;
	// End time of the class
	private double endTime;
	// Week day of the class
	private int weekDay;
	
	/**
	 * Constructs a Time Period.
	 *
	 * @param startTime the start time of the class
	 * @param endTime the end time of the class 
	 * @param weekDay the day of the week   
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
		
		if (weekDay == timePeriod.getWeekDay()) {
			if (startTime <= timePeriod.getEndTime() && startTime >= timePeriod.getStartTime())
				return true;
			if (timePeriod.getStartTime() <= endTime && timePeriod.getStartTime() >= startTime)
				return true;
		}
		return false;	
	}

	/**
	 * Returns the start time
	 * 
	 * @return start time
	 */
	public double getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time
	 * 
	 * @return end time
	 */
	public double getEndTime() {
		return endTime;
	}

	/**
	 * Returns the week day
	 * 
	 * @return week day
	 */
	public int getWeekDay() {
		return weekDay;
	}
}

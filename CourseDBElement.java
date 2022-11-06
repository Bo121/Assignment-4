
public class CourseDBElement implements Comparable<CourseDBElement>{

	/**
	 * String id The Name of the class
	 */
	public String id;
	/**
	 * int crn The specific number for the class
	 */
	public int crn;
	/**
	 * int credits The number of classes of the class
	 */
	public int credits;
	/**
	 * String roomNum The room number of the class
	 */
	public String roomNum;
	/**
	 * String instructor The instructor of the class
	 */
	public String instructor;

	/**
	 * The default constructor set id, roomNum, and instructor to "", and set crn and credits to 0
	 */
	public CourseDBElement() {
		id = roomNum = instructor = "";
		crn = credits = 0;
	}
	
	/**
	 * This constructor sets roomNum, instructor, set crn and credits to the argument they took in 
	 * @param id The Name of the class
	 * @param crn The specific number for the class
	 * @param credits The number of classes of the class
	 * @param roomNum The room number of the class
	 * @param instructor The instructor of the class
	 */
	public CourseDBElement(String id, int crn, int credits, String roomNum, String instructor) {
		this.id = id;
		this.crn = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}

	/**
	 * This method sets the CRN
	 * @param parseInt
	 */
	public void setCRN(int parseInt) {
		this.crn = parseInt;
	}
	
	/**
	 * This method returns the CRN 
	 * @return crn number
	 */
	public int getCRN() {
		return crn;
	}

	/**
	 * This method returns the room number of the class
	 * @return the room number of the class
	 */
	public String getRoomNum() {
		return roomNum;
	}
	
	/**
	 * This method sets the room number
	 * @param roomNum the room number of the class
	 */
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	/**
	 * This method sets the id of the class
	 * @param id the id of the class
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * This method returns the ID of the class
	 * @return the ID of the class
	 */
	public String getID() {
		return id;
	}

	/**
	 * This method returns the credits of the class
	 * @return the credits of the class
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * This method returns the credits of the class
	 * @return the credits of the class
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
	 * This method returns the instructor of the class
	 * @return the instructor of the class
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * This method sets the instructor of the class
	 * @param id the instructor of the class
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	/**
	 * This method returns a string
	 */
	public String toString() {
		return "Course:" + id + " CRN:" + crn + " Credits:" + credits 
				+ " Instructor:" + instructor+ " Room:" + roomNum;
	}

	/**
	 * This method returns an integer based on the crn
	 */
	@Override
	public int compareTo(CourseDBElement ele) {
		if(crn > ele.crn)
			return 1;
		else if(crn < ele.crn)
			return -1;
		else
			return 0;
	}
}

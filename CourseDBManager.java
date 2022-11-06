import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CourseDBManager implements CourseDBManagerInterface{

	private CourseDBStructure DBStructure = new CourseDBStructure(9);
	
	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement ele = new CourseDBElement(id,crn,credits,roomNum,instructor);
		DBStructure.add(ele);
	}

	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		try {
			Scanner file = new Scanner(input);
			while(file.hasNext()) {
				String id = file.next();
				int crn = file.nextInt();
				int credits  = file.nextInt();
				String roomNum = file.next();
				String instructor = file.nextLine();
				add(id,crn,credits,roomNum,instructor);
			}
			file.close();
		}
		catch(FileNotFoundException e){
			e.getMessage();
		}
	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> temp = new ArrayList<String>();

		for (int i=0; i<DBStructure.getTableSize(); i++) {
			DBStructure.head = DBStructure.buckets[i];
			while(DBStructure.head!=null) {
				temp.add("\n"+DBStructure.head.value.toString());
				DBStructure.head = DBStructure.head.next;
			}
		}

		return temp;
	}
	
	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	@Override
	public CourseDBElement get(int crn) {

		int index = DBStructure.hashFunction(String.valueOf(crn))% DBStructure.getTableSize();
		DBStructure.head = DBStructure.buckets[index];

		if(DBStructure.head == null) {
			return null;
		}
		
		while(DBStructure.head != null) {
			if(DBStructure.head.key == crn) {
				System.out.println(DBStructure.head.value);
				return DBStructure.head.value;
			}
			else if(DBStructure.head.key != crn){
				DBStructure.head = DBStructure.head.next;
				if(DBStructure.head == null) {
					return null;
				}
			}
		}
		
		return null;
	}
}

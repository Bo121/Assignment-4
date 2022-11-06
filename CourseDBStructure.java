import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CourseDBStructure implements CourseDBStructureInterface{

	/**
	 * Number of buckets in the hash table
	 */
	private int numOfBuckets; 
	/**
	 * Number of key-value pairs
	 */
	private int size; 
	/**
	 * The first value in that bucket
	 */
	Node head; 
	/**
	 * Create a hash table
	 */
	Node buckets[]; 
	
	/**
	 * This constructor sets the number of buckets based on the given argument, and initialize
	 * the number of nodes to 0
	 * @param EstimatedCourses the estimated number of courses in the bucket
	 */
	public CourseDBStructure(int EstimatedCourses) {
		numOfBuckets = makePrime(EstimatedCourses);
		buckets = new Node[numOfBuckets];
		size = 0;
	}
	
	public CourseDBStructure(String string, int i) {
		numOfBuckets = i;
		size = 0;
		buckets = new Node[numOfBuckets];
	}
	
	class Node{
		int key;
		CourseDBElement value;
		Node next;
		
		public Node(int key, CourseDBElement value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * This method makes the length of the hash table
	 * @param EstimatedCourses the estimated course that will be stored in the bucket
	 * @return the integer
	 */
	public static int makePrime(int EstimatedCourses) {
		
		int num = (int)(EstimatedCourses/1.5);
		int k = (int)(num - 3)/4;
		
		while (isPrime((4*(k+1))+3) == false) {
			k++;
		}
		int length = (k+1)*4+3;
		return length;
	}
	
	/**
	 * This method determines if the integer is a prime number
	 * @param length the length of the bucket
	 * @return if the number is a prime number
	 */
	public static boolean isPrime(int length) {
		
		if(length <= 1)
			return false;
		for (int i=2; i<length; i++) {
			if (length % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method takes a String argument, and make a hash code for the string
	 * @param s The string needs to be converted to a hash code
	 * @return a hash code
	 */
	public int hashFunction(String s) {
		int hash = 0;
		int n = s.length();
		for (int i = 0; i < n; i++) {
			hash = 31 * hash + s.charAt(i);
		}
		return hash;
	}
	
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hash code
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	@Override
	public void add(CourseDBElement cde1) {
		
		int index = hashFunction(String.valueOf(cde1.crn))% numOfBuckets;
		Node head = buckets[index];
	
		while(head != null) {
			if(head.key == cde1.crn) {
				head.value = cde1;
				return;
			}
			head = head.next;
		}
		head = buckets[index];
		Node node = new Node(cde1.crn, cde1);
		node.next = head;
		buckets[index] = node;
		size++;
	}
	
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		
		int index = hashFunction(String.valueOf(crn)) % numOfBuckets;
		head = buckets[index];

		if(head == null) {
			throw new IOException();
		}
		
		while(head != null) {
			if(head.key == crn) {
				System.out.println(head.value);
				return head.value;
			}
			else if(head.key != crn){
				head = head.next;
				if(head == null) {
					throw new IOException();
				}
			}
		}
		return null;
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
		for (int i=0; i<numOfBuckets; i++) {
			Node head = buckets[i];
			while(head!=null) {
				temp.add(head.value.toString());
				head = head.next;
			}
		}
		return temp;
	}

	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	@Override
	public int getTableSize() {
		return numOfBuckets;
	}

}

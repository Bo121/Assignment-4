

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();
	
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("ARTT203",30508,3,"CC450","Jason");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	@Test
	public void testShowAll() {
		dataMgr.add("ARTT203",30508,3,"CC450","Jason");
		dataMgr.add("ARTT203",30509,3,"CC450","Bob");
		dataMgr.add("ARTT101",30507,3,"CC450","Acy");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:ARTT203 CRN:30508 Credits:3 Instructor:Jason Room:CC450");
		assertEquals(list.get(1),"\nCourse:ARTT203 CRN:30509 Credits:3 Instructor:Bob Room:CC450");
		assertEquals(list.get(2),"\nCourse:ARTT101 CRN:30507 Credits:3 Instructor:Acy Room:CC450");
	}
	
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test2.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("ARTT203 30508 3 CC450 Jason");
			inFile.print("ARTT101 30580 3 CC450 Acy");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("ARTT203",dataMgr.get(30508).getID());
			assertEquals("ARTT101",dataMgr.get(30580).getID());
			assertEquals("CC450",dataMgr.get(30508).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}

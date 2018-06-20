package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTest {

	@Test
	public void testToString() {
		Date d = new Date(2,12,2018);
		System.out.println(d.toString(true));
		assertEquals(true, d.toString(true).equals("02/12/2018"));
		System.out.println(d.toString(false));
		assertEquals(true, d.toString(false).equals("2018-12-02"));
	}
	
	@Test
	public void testParseDate(){
		
	}

}

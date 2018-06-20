package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class MealTest {

	@Test
	public void testToString() {
		Meal m = new Meal(new Date(1,12,2001), Person.MORGAN, "Good Food");
		System.out.println(m);
		assertEquals(true, m.toString().equals("1/12/2001|Morgan|Good Food"));
	}

}

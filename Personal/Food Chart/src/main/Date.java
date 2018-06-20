package main;

import main.exceptions.InvalidDateException;


public class Date {
	
	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	//compares date by year month and day in that order
	public int compareTo(Date otherDate){
		if(this.year == otherDate.year){
			if(this.month == otherDate.month){
				if(this.day == otherDate.day){
					return 0; //dates are equal
				}
				if(this.day > otherDate.day){
					return 1;
				}
				return -1;
			}
			if(this.month > otherDate.month){
				return 1;
			}
			return -1;
		}
		if(this.year > otherDate.year){
			return 1;
		}
		return -1;
	}
	
	public String toString(boolean uiFormat) {
		String dayPrefix = "";
		String monthPrefix = "";
		
		if(day < 10){
			dayPrefix = "0";
		}
		if(month < 10){
			monthPrefix = "0";
		}
		
		if(uiFormat){
			return dayPrefix + day + "/" + monthPrefix + month + "/" + year;
		}
		else{
			return year + "-" + monthPrefix + month + "-" + dayPrefix + day;
		}
	}
	
	/**
	 * Parses date if it matches format: dd/mm/yyyy OR yyyy-mm-dd
	 * @param text
	 * @return The parsed date
	 * @throws InvalidDateException
	 */
	public static Date parseDate(String text) throws InvalidDateException{
		int d = 0,m = 0,y = 0;
		String parts[] = text.split("/");
		try {
			if(parts.length==3){
				d = Integer.parseInt(parts[0]);
				m = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
			}
			else{
				parts = text.split("-");
				y = Integer.parseInt(parts[0]);
				m = Integer.parseInt(parts[1]);
				d = Integer.parseInt(parts[2]);
			}
		} catch (NumberFormatException e) {
			throw new InvalidDateException();
		}
		
		if(d<1 || d>31){
			throw new InvalidDateException();
		}
		
		if(m<1 || m >12){
			throw new InvalidDateException();
		}
		
		return new Date(d, m, y);	
	}
	
	
	
}

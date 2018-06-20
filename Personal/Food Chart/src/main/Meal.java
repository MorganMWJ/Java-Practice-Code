package main;

public class Meal {
	private Date date;
	private Person person;
	private String desc;
	
	
	public Meal(Date date, Person person, String desc){
		this.date = date;
		this.person = person;
		this.desc = desc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String toString(){
		return String.format("%s|%s|%s", date.toString(true), person.getName(), desc);
	}

}

package main;

import main.exceptions.InvalidPersonException;

public enum Person {
	MORGAN("Morgan"),
	PATRICK("Patrick"),
	BEN("Ben"),
	WILL("Will");
	
	private final String name;
	
	Person(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static int getNumOfPeople(){
		return Person.values().length;
	}
	
	public static Person parsePerson(String person) throws InvalidPersonException{
		Person res = null;
		if(person.equals(MORGAN.getName())){
			res = MORGAN;
		}
		else if(person.equals(PATRICK.getName())){
			res = PATRICK;
		}
		else if(person.equals(BEN.getName())){
			res = BEN;
		}
		else if(person.equals(WILL.getName())){
			res = WILL;
		}
		if(res == null){
			throw new InvalidPersonException();
		}
		return res;
	}
	
}

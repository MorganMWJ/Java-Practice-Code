package main.database;
import java.sql.*;
import java.util.ArrayList;

import main.Meal;
import main.Person;
import main.Date;

public class DatabaseAccess{

	public int getPersonMealCount(Person p) {
		return getPersonMeals(p).size();
	}
	
	public int getAllMealCount(){
		return getAllMeals().size();
	}

	public ArrayList<Meal> getPersonMeals(Person p) {
		try{
			ArrayList<Meal> resultMeals = new ArrayList<Meal>();
			
			//get connection to database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/food_chart?autoReconnect=true&useSSL=false",
					"Morgan", "return0.1");
			//create statement
			Statement stmt = conn.createStatement();
			//execute query/stored procedure
			ResultSet rs = stmt.executeQuery("CALL get_person_meals('" + p.getName() + "')");
			//process result set
			while(rs.next()){
				Meal m = new Meal(Date.parseDate(rs.getString(1)),p,rs.getString(2));
				resultMeals.add(m);
			}
			//close connection
			conn.close();
			
			return resultMeals;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Meal> getAllMeals() {
		try{
			ArrayList<Meal> resultMeals = new ArrayList<Meal>();
			
			//get connection to database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/food_chart?autoReconnect=true&useSSL=false",
					"Morgan", "return0.1");
			//create statement
			Statement stmt = conn.createStatement();
			//execute query/stored procedure
			ResultSet rs = stmt.executeQuery("CALL get_all_meals()");
			//process result set
			while(rs.next()){
				Meal m = new Meal(Date.parseDate(rs.getString(1)),Person.parsePerson(rs.getString(3)),rs.getString(2));
				resultMeals.add(m);
			}
			//close connection
			conn.close();
			
			return resultMeals;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeMeal(Date date){
		try{			
			//get connection to database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/food_chart?autoReconnect=true&useSSL=false",
					"Morgan", "return0.1");
			//create statement
			Statement stmt = conn.createStatement();
			//execute query/stored procedure
			stmt.executeQuery("CALL remove_meal('" + date.toString(false) + "')");

			//close connection
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addMeal(Meal meal){
		try{			
			//get connection to database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/food_chart?autoReconnect=true&useSSL=false",
					"Morgan", "return0.1");
			//create statement
			Statement stmt = conn.createStatement();
			//execute query/stored procedure
			stmt.executeQuery("CALL add_meal('" + meal.getDate().toString(false) + "','" + meal.getDesc() + "','" + meal.getPerson().getName() + "')");

			//close connection
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

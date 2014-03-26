package dbas2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mathaxxare {
	
	private Connection c;
	
	public Mathaxxare() {
		c = null;
	}
	
	public void connect(String db, String user, String password){
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://nestor2.csc.kth.se:5432/"+db,
					user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
	
	public void addIngredient(String name, float quantity, String unit) throws SQLException {
		Statement s = c.createStatement();
		s.executeUpdate("INSERT INTO in_kitchen(name, quantity, unit) VALUES('"+name+"',"+quantity+",'"+unit+"')");
	}
	
	public void addRecipe(String name, String type, String description) throws SQLException {
		Statement s = c.createStatement();
		s.executeUpdate("INSERT INTO recipe(name, type, description) VALUES('"+name+"','"+ type +"','"+ description +"')");
	}
	
	public void listIngredients() throws SQLException {
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery("SELECT * FROM in_kitchen");
		
		System.out.println("List of ingredients:");
		while (r.next()) {
			System.out.println(r.getString("name") + " - " + r.getFloat("quantity") + " - " +r.getString("unit"));
		}
	}
	
	public void listRecipes() throws SQLException {
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery("SELECT * FROM recipe");
		System.out.println("List of recipes:");
		while(r.next()) {
			System.out.println(r.getString("name"));
			System.out.println("\t" + r.getString("type"));
			System.out.println("\t" + r.getString("description"));
		}
	}

}
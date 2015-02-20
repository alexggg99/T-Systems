package testProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("TEST !");
		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Where is your MySQL JDBC Driver?");
//			e.printStackTrace();
//			return;
//		}
		
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement prSt= null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testerdb","tester", "tester");
			stmt = connection.createStatement();
			
			String sql = "Select * from tickets t, passanger p where t.passanger = p.id  and p.name like '%May%';";
			prSt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSet pr = prSt.executeQuery();
			 while(pr.next()){
		         //Retrieve by column name
		         int id  = pr.getInt("id");
		         String name = pr.getString("name");

		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Name: " + name);
		         System.out.println();
		      }
			
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
		if (connection != null) {
			System.out.println();
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
	}

}

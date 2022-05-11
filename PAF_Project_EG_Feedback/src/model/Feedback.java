package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {
	//Connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project_eg", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	//Insert
	public String insertFeedback(String name, String contactNumber, String email, String message)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into feedback (`F_ID`,`F_Name`,`F_ContactNo`,`F_Email`,`F_Message`)" + " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, contactNumber);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, message);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readFeedbacks();
			output = "{\"status\":\"success\", \"data\": \"" +
			newItems + "\"}";

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//Read data
	public String readFeedbacks()
	{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Name</th><th>Contact Number</th>" +
						"<th>Email</th>" +
						"<th>Message</th>" +
						"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from feedback";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String F_ID = Integer.toString(rs.getInt("F_ID"));
					String F_Name = rs.getString("F_Name");
					String F_ContactNo = rs.getString("F_ContactNo");
					String F_Email = rs.getString("F_Email");
					String F_Message = rs.getString("F_Message");
					
					// Add into the html table
					output += "<tr><td><input id='hidFeedbackIDUpdate' name='hidFeedbackIDUpdate' type='hidden' value='" + F_ID + "'>"+ F_Name + "</td>";
					output += "<td>" + F_ContactNo + "</td>";
					output += "<td>" + F_Email + "</td>";
					output += "<td>" + F_Message + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				            + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
				            + F_ID + "'>" + "</td></tr>";
					
					
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the feedback details.";
				System.err.println(e.getMessage());
			}
			
		return output;
	}

	
	public String updateFeedback(String ID, String name, String contactNumber, String email, String message)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE feedback SET F_Name=?,F_ContactNo=?,F_Email=?,F_Message=? WHERE F_ID=?";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, contactNumber);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, message);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readFeedbacks();
			output = "{\"status\":\"success\", \"data\": \"" +
			newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
					System.err.println(e.getMessage());
		}
		
		return output;
	}
		
	public String deleteFeedback(String F_ID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from feedback where F_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(F_ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readFeedbacks();
			output = "{\"status\":\"success\", \"data\": \"" +
			newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
					System.err.println(e.getMessage());
		}
		
		return output;
	}
}


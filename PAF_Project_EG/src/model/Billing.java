package model;
import java.sql.*;

public class Billing {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");

	 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eg_project","root" ,"");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
public String addBills(String B_Cus_ID, String B_Cus_Name, String B_Cus_Email, String B_Cus_Contact, String B_Use_Points, String B_Use_Val, String B_Add_Charge, String B_Prev_Outsand, String B_Tot_Amt_Pay, String B_Red_Nor )
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 	String query ="insert into billing values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, B_Cus_ID);
		 preparedStmt.setString(3, B_Cus_Name);
		 preparedStmt.setString(4, B_Cus_Email);
		 preparedStmt.setString(5, B_Cus_Contact);
		 preparedStmt.setDouble(6, Double.parseDouble(B_Use_Points));
		 preparedStmt.setDouble(7, Double.parseDouble(B_Use_Val));
		 preparedStmt.setDouble(8, Double.parseDouble(B_Add_Charge));
		 preparedStmt.setDouble(9, Double.parseDouble(B_Prev_Outsand));
		 preparedStmt.setDouble(10,Double.parseDouble(B_Tot_Amt_Pay));
		 preparedStmt.setString(11, B_Red_Nor);
		 //execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Bill Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting the Bill.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readBills()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	
	 output = "<table border='1'><tr><th>Project Code</th><th>Customer ID</th>" +
			  "<th>Customer Name</th>" +
			  "<th>Customer Email</th>" +
			  "<th>Customer Contact</th>" +
			  "<th>Used Units</th>" +
			  "<th>Amount for Usage</th>" +
			  "<th>Additional Charge</th>" +
			  "<th>Previous Outstanding</th>" +
			  "<th>Total Amount</th>" +
			  "<th>Red Bill or Normal</th>" +
			  "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from Billing";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String B_ID = Integer.toString(rs.getInt("B_ID"));
	 String B_Cus_ID = rs.getString("B_Cus_ID");
	 String B_Cus_Name = rs.getString("B_Cus_Name");
	 String B_Cus_Email = rs.getString("B_Cus_Email");
	 String B_Cus_Contact = rs.getString("B_Cus_Contact");
	 String B_Use_Points = Double.toString(rs.getDouble("B_Use_Points"));
	 String B_Use_Val = Double.toString(rs.getDouble("B_Use_Val"));
	 String B_Add_Charge = Double.toString(rs.getDouble("B_Add_Charge"));
	 String B_Prev_Outsand = Double.toString(rs.getDouble("B_Prev_Outsand"));
	 String B_Tot_Amt_Pay = Double.toString(rs.getDouble("B_Tot_Amt_Pay"));
	 String B_Red_Nor = rs.getString("B_Red_Nor");
	 
	 output += "<tr><td>" + B_ID + "</td>";
	 output += "<td>" + B_Cus_ID + "</td>";
	 output += "<td>" + B_Cus_Name + "</td>";
	 output += "<td>" + B_Cus_Email + "</td>";
	 output += "<td>" + B_Cus_Contact + "</td>";
	 output += "<td>" + B_Use_Points + "</td>";
	 output += "<td>" + B_Use_Val + "</td>";
	 output += "<td>" + B_Add_Charge + "</td>";
	 output += "<td>" + B_Prev_Outsand + "</td>";
	 output += "<td>" + B_Tot_Amt_Pay + "</td>";
	 output += "<td>" + B_Red_Nor + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='billing.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='B_ID' type='hidden' value='" + B_ID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Bills.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateBills(String B_ID, String B_Cus_ID, String B_Cus_Name, String B_Cus_Email, String B_Cus_Contact, String B_Use_Points, String B_Use_Val, String B_Add_Charge, String B_Prev_Outsand, String B_Tot_Amt_Pay, String B_Red_Nor)
	
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE billing SET B_Cus_ID=?,B_Cus_Name=?,B_Cus_Email=?,B_Cus_Contact=?,B_Use_Points=?,B_Use_Val=?,B_Add_Charge=?,B_Prev_Outsand=?,B_Tot_Amt_Pay=?,B_Red_Nor=? WHERE B_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, B_Cus_ID);
		 preparedStmt.setString(2, B_Cus_Name);
		 preparedStmt.setString(3, B_Cus_Email);
		 preparedStmt.setString(4, B_Cus_Contact);
		 preparedStmt.setDouble(5, Double.parseDouble(B_Use_Points));
		 preparedStmt.setDouble(6, Double.parseDouble(B_Use_Val));
		 preparedStmt.setDouble(7, Double.parseDouble(B_Add_Charge));
		 preparedStmt.setDouble(8, Double.parseDouble(B_Prev_Outsand));
		 preparedStmt.setDouble(9, Double.parseDouble(B_Tot_Amt_Pay));
		 preparedStmt.setString(10, B_Red_Nor);
		 preparedStmt.setInt(11, Integer.parseInt(B_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the Bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteBills(String B_ID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from billing where B_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(B_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the Bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		} 



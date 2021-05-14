package model;

import java.sql.*;

public class Researcher {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3307/gb?serverTimezone=UTC", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertResearcher(String pid, String dname,  String desc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into researchers(`ID`,`ProId`,`DeveloperName`,`ProDesc`)"
	 + " values (?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2,pid);
	 preparedStmt.setString(3, dname);
	 preparedStmt.setString(4, desc);
	// execute the statement

	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the researchers.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readResearchers()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Project ID</th><th>Developer Name</th>" +
	 "<th>Project Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from researchers";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String ID = Integer.toString(rs.getInt("ID"));
	 String ProId = rs.getString("ProId");
	 String DeveloperName = rs.getString("DeveloperName");
	 String ProDesc = rs.getString("ProDesc");
	 // Add into the html table
	 output += "<tr><td>" + ProId + "</td>";
	 output += "<td>" + DeveloperName + "</td>";
	 output += "<td>" + ProDesc + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='researchers.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" +ID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the researchers.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateResearcher(String ID, String pid, String pname, String desc)

	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE researchers SET ProId=?,DeveloperName=?,ProDesc=? WHERE ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, pid);
	 preparedStmt.setString(2, pname);
	 preparedStmt.setString(3, desc);
	 preparedStmt.setInt(4, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the researchers.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteResearcher(String ID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from researchers where ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the researchers.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
}

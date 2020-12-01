package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.DatabaseConnection;
import dto.Customer;
import servercon.WebServer;

public class CustomerDAO {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
// Email Id Exist Or Not -------------------------------------------------------------

	public boolean checkID(String customerID)
	{
		boolean b= false;
		try {
			con = DatabaseConnection.getConnection();
			//-----------  SELECT * FROM customer WHERE ID = 'prateekharyani145@gmail.com';  --------------//
			pst = con.prepareStatement("SELECT * FROM customer WHERE ID = ?");
			
			pst .setString(1, customerID);
			rs = pst.executeQuery();
			if( ! (rs.isBeforeFirst() ) )
				b = true;
			con.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Duplicate Entry Check in CustomerDAO.checkID");
		}
		return b;
		
	}
	
// REGISTRATION ---------------------------------------------------------------------------------
	public boolean registerCustomer(Customer obj)
	{
		boolean b= false;
		try {
			con = DatabaseConnection.getConnection();

				String Verification_Link = WebServer.MYSERVER + "/db_Customer_Verify.jsp?customerID=" + obj.getId() ;
				boolean mailSent = MailDAO.sendMail(obj.getId()," Verification Mail From Slick "," Please click on following link to verify yourself with Slick <a href="+Verification_Link+"> VERIFY</a>");
                
				if(mailSent)
				{
					System.out.println(" Verification Mail Has been Sent to "+obj.getId());
					b= true;
				}
				else
					System.out.println("\n Verification Mail Has Not been Sent to \n"+obj.getId());
			
			
			if(b) {
			
			//INSERT INTO customer VALUES ('prateekharyani145@gmail.com','java@123','Prateek','Haryani','8962162898',0,0);  -------------------------------
			pst = con.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?)");
			
			pst.setString(1, obj.getId());
			pst.setString(2, obj.getPassword());
			pst.setString(3, obj.getFirstName());
			pst.setString(4, obj.getLastName());
			pst.setString(5, obj.getMobileNumber());
			pst.setInt(6, 0);
			pst.setInt(7, 0);
			
			int count = pst.executeUpdate();
			if(count>0)
				System.out.println(count+" Customer Updated \n");
			else {
				System.out.println("Customer Not Updated . Please Check in CustomerDAO.registerCustomer");
				b= false;
			}
			con.close();
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("\n Problem in Registration Check in CustomerDAO.registerCustomer \n");
		}
		return b;

	}
	//Verification-------------------------------------------------------------------------
	public boolean verifyCustomer(String customerID) {
			boolean b= false;
			try {
	            con = DatabaseConnection.getConnection();

	            // SELECT * FROM customer WHERE ID = 'prateekharyani2018@gmail.com'
	            pst = con.prepareStatement("SELECT * FROM customer WHERE ID = ? ");

	            pst.setString(1, customerID);
	            rs = pst.executeQuery();
	            if(rs.isBeforeFirst())
	            {
	                pst = con.prepareStatement("Update Customer set verified = 1 Where id = ?");
	                pst.setString(1, customerID);
	                int count = pst.executeUpdate();
	                if(count > 0) {
	                    b = true;
	        			System.out.println("Verification Done Successful ");

	                }

	            }
	            con.close();
			}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Verification Not Done . Please Check CustomerDAO.verifyCustomer");
		}
		return b;
	}
	
	
	// LOGIN ------------------------------------------------------------------------------
	public boolean checkCustomerCredentials(Customer obj) {
		boolean b = false;
		try {
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("\n Problem in Login Check in CustomerDAO.checkCustomerCredentials \n");
		}
		return b;
	}

	

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbcon.DatabaseConnection;
import dto.ReportedIncidentsByCustomer;

public class ReportedIncidentsByCustomerDAO {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	//  DISPLAY CUSTOMER INCIDENTS FOR ADMIN
	public ArrayList<ReportedIncidentsByCustomer> getAllIncidentsByCustomer(){
			ArrayList<ReportedIncidentsByCustomer> CustomerIncidentList= null;
			try {
	            con = DatabaseConnection.getConnection();
	            pst = con.prepareStatement("SELECT * FROM reportedincidentsbycustomers");
	            rs = pst.executeQuery();
	            if(rs.isBeforeFirst())
	            {
	                CustomerIncidentList = new ArrayList<>();
	                
	                while(rs.next())
	                {
	                    ReportedIncidentsByCustomer obj = new ReportedIncidentsByCustomer();
	                    obj.setId(rs.getInt(1));
	                    obj.setIncidentDescription(rs.getString(2));
	                    obj.setPostedDate(rs.getTimestamp(3)+"");
	                    obj.setExpertID(rs.getString(4));
	                    obj.setCustomerID(rs.getString(5));
	                    obj.setQuestionID(rs.getInt(6));
	                    CustomerIncidentList.add(obj);
	                    
	                }
	            }
	            
	            con.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return CustomerIncidentList;
	}

}

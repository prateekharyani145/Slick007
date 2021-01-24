package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.DatabaseConnection;

// DISPLAY ALL EXPERT DETAILS FOR ADMIN ---------------------------
public class QuestionSubCategoryDAO {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public String getSubCategoryName(int subCategoryID) {
		String subCategoryName= "";
		try {
	        con = DatabaseConnection.getConnection();
            
	        pst = con.prepareStatement("select subCategoryName from QuestionSubCategory "
	                    + "where id = ?");
	            
	            pst.setInt(1, subCategoryID);
	            
	            rs = pst.executeQuery();
	            
	            if(rs.isBeforeFirst())
	            {
	                rs.next();
	                
	                subCategoryName =  rs.getString("subCategoryName");
	            }
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return subCategoryName;
	}


}

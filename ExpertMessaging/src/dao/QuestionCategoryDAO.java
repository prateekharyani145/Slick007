package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbcon.DatabaseConnection;
import dto.QuestionCategory;
public class QuestionCategoryDAO {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	// Get All Details through question category for Admin  

	public ArrayList<QuestionCategory> getAllQuestionCategories() {
		ArrayList<QuestionCategory> questionCategoryList= null;
		try {
	        con = DatabaseConnection.getConnection();
	        pst = con.prepareStatement("select * from QuestionCategory");
	        rs = pst.executeQuery();

	        if(rs.isBeforeFirst())
	        {
	        	questionCategoryList = new ArrayList<>();
	            
	            while(rs.next())
	            {
	                QuestionCategory obj = new QuestionCategory();
	                obj.setId(rs.getInt(1));
	                obj.setCategoryName(rs.getString(2));
	                questionCategoryList.add(obj);
	            }
	        }
	        
	        con.close();

			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return questionCategoryList;
	}
// DISPLAY EXPERT DETAILS FOR ADMIN 
	public String getCategoryName(int categoryID) {
		String categoryName= "";
		try {
		       con = DatabaseConnection.getConnection();
	            
		        pst = con.prepareStatement("select categoryName from QuestionCategory "
		                    + "where id = ?");
		            
		            pst.setInt(1, categoryID);
		            
		            rs = pst.executeQuery();
		            
		            if(rs.isBeforeFirst())
		            {
		                rs.next();
		                
		                categoryName =  rs.getString("categoryName");
		            }
		            
		            con.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return categoryName;
	}
	
	
}




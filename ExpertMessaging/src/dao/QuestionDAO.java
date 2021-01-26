package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbcon.DatabaseConnection;

import dto.Question;

public class QuestionDAO {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public Question getQuestionById(int questionID) {
		Question question= null;
		try {
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return question;
	}
	
	public ArrayList<Question> getAllUnAnsweredQuestionsForCustomer(String customerID){
		ArrayList<Question> questionList= null;
		try {
	        con = DatabaseConnection.getConnection();
	        
	        pst = con.prepareStatement("select * from Question where customerID = ? and "
	                + "status = false");
	        
	        pst.setString(1, customerID);
	        
	        rs = pst.executeQuery();
	        
	        if(rs.isBeforeFirst())
	        {
	        	questionList = new ArrayList<>();
	            
	            while(rs.next())
	            {
	                Question object = new Question();
	                object.setId(rs.getInt(1));
	                object.setQuestionTitle(rs.getString(2));
	                object.setQuestionDescription(rs.getString(3));
	                object.setPostedDate(rs.getTimestamp(4)+"");
	                object.setStatus(rs.getBoolean(5));
	                object.setVisibility(rs.getBoolean(6));
	                object.setCategoryID(rs.getInt(7));
	                object.setSubCategoryID(rs.getInt(8));
	                object.setCustomerID(rs.getString(9));
	                object.setExpertID(rs.getString(10));
	                
	                questionList.add(object);
	            }
	        }
	        con.close();

			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return questionList;
	}

public boolean addQuestion(Question obj) {
	boolean b= false;
	try {
        con = DatabaseConnection.getConnection();
        
        pst = con.prepareStatement("insert into Question(questionDescription,status,visibility,"
                + "categoryID,subCategoryID,customerID,expertID,questionTitle) values(?,?,?,?,?,?,?,?)");
        
        pst.setString(1, obj.getQuestionDescription());
        pst.setBoolean(2, false);
        pst.setBoolean(3, obj.isVisibility());
        pst.setInt(4, obj.getCategoryID());
        pst.setInt(5, obj.getSubCategoryID());
        pst.setString(6, obj.getCustomerID());
        pst.setString(7, obj.getExpertID());
        pst.setString(8, obj.getQuestionTitle());
        
        int count = pst.executeUpdate();
        
        if(count > 0)
            b = true;
        
        con.close();

		
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
	return b;
}

}

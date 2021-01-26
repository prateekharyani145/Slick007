package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import dbcon.DatabaseConnection;
import dto.Expert;

public class ExpertDAO {
Connection con;
PreparedStatement pst;
ResultSet rs;
public ArrayList<Expert> getAllExpertsDetails(){
	ArrayList<Expert> expertList= null;
	try {
        con = DatabaseConnection.getConnection();
        
        pst = con.prepareStatement("select * from Expert");
        
        rs = pst.executeQuery();
        
        if(rs.isBeforeFirst())
        {
            expertList = new ArrayList<>();
            
            while(rs.next())
            {
                Expert obj = new Expert();
                obj.setId(rs.getString(1));
                obj.setPassword(rs.getString(2));
                obj.setBlocked(rs.getBoolean(3));
                obj.setCategoryID(rs.getInt(4));
                obj.setSubCategoryID(rs.getInt(5));
                
                expertList.add(obj);
            }
        }
        
        con.close();

		
	}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return expertList;
}

public boolean registerExpert(Expert object) {
	boolean b= false;
	try {
        
            con = DatabaseConnection.getConnection();

            String genpass = UUID.randomUUID().toString().substring(0, 5);            
            
            pst = con.prepareStatement("insert into Expert values(?,?,?,?,?)");
            
            pst.setString(1, object.getId());
            pst.setString(2, genpass);
            pst.setBoolean(3, false);
            pst.setInt(4, object.getCategoryID());
            pst.setInt(5, object.getSubCategoryID());
            
            int count = pst.executeUpdate();
            
            if(count > 0)
            {
                boolean mailSent = MailDAO.sendMail(object.getId(), 
"Registration Mail From ExpertMessaging", 
"Congrulations!!! <br> Dear Expert,<br>Your account has been created on Slick. <br>Following is your login id and "
        + "password : <br> "
        + "<b> login-id : "+object.getId()+"</b><br>"+
        "<b> password : "+genpass+"</b>");
                
                if(mailSent)
                {
                    System.out.println("registration mail has been "
                        + "sent to "+object.getId());
                    b = true;
                }
                else
                    System.out.println("registration mail has not been "
                        + "sent to "+object.getId());
            }
            
            con.close();

		
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
	return b;
}
public String getExpertByCategorySubCategory(int categoryID,int subCategoryID) {
	String expertID= "";
	try {
         con = DatabaseConnection.getConnection();
         
         pst = con.prepareStatement("Select id From Expert Where categoryID = ? AND subCategoryID = ?");
         
         pst.setInt(1, categoryID);
         pst.setInt(2, subCategoryID);
         
         rs = pst.executeQuery();
         
         if(rs.isBeforeFirst())
         {
             rs.next();
             expertID = rs.getString("id");
         }

		
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
	return expertID;		
}



}

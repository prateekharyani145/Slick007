package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

}

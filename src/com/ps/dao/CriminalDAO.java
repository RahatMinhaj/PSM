package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.gui.Dashboard;
import com.ps.model.criminalModel;
import com.ps.model.departmentModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CriminalDAO implements IcommonDAO<criminalModel>{

    @Override
    public int create(criminalModel t) {
        int statement = 0;
        
        dbConnection con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO criminal_information (criminal_id, case_ID, criminal_first_name, criminal_last_name, criminal_father_name,arrestOff, criminal_mother_name, criminal_gender, criminal_age, arrested_or_not, arresting_time, previous_casees, criminal_location, criminalp_location, criminal_mobile, remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");
            
            
            ps.setString(1, t.getCriminal_id());
            ps.setString(2, t.getCase_ID()); 
            ps.setString(3, t.getCriminal_first_name()); 
            ps.setString(4, t.getCriminal_last_name()); 
            ps.setString(5, t.getCriminal_father_name()); 
            ps.setString(6, t.getCriminal_mother_name()); 
            ps.setString(7, t.getArrestOff()); 
            ps.setString(8, t.getCriminal_gender()); 
            ps.setString(9, t.getCriminal_age()); 
            ps.setString(10, t.getArrested_or_not()); 
            ps.setString(11, t.getArresting_time()); 
            ps.setString(12, t.getPrevious_casees()); 
            ps.setString(13, t.getCriminal_location()); 
            ps.setString(14, t.getCriminalp_location());
            ps.setString(15, t.getCriminal_mobile()); 
            ps.setString(16, t.getCriminal_remarks()); 
            
            
            int rs = ps.executeUpdate();
            statement = rs;
      
        } catch (Exception e) {
            System.out.println(e);
        }
        
       return statement;
    } 

    @Override
    public List<criminalModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<criminalModel> edit(criminalModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
    


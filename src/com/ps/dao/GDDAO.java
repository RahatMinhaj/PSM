
package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.model.GDCatModel;
import com.ps.model.GDModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GDDAO implements IcommonDAO<GDModel>{

    @Override
    public int create(GDModel t) {
        int statement = 0;
        
         dbConnection con = new dbConnection();
        con = new dbConnection();
        System.out.println("test1");
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO general_diary (gd_id, gd_victim_id, gd_victim_first_name,gd_victim_last_name,gd_victim_mobile, gd_victim_location, gd_victim_gender,gd_victim_age, gd_police_name, gd_type,gd_open_date,gd_open_time, gd_status ) VALUES (? , ?, ?,?,?,?,? , ?, ?,?,?,?,?);");
            System.out.println("test2");
            ps.setString(1, t.getGd_id());
            ps.setString(2, t.getGd_victim_id()); 
            ps.setString(3, t.getGd_victim_first_name()); 
            ps.setString(4, t.getGd_victim_last_name()); 
            ps.setString(5, t.getGd_victim_mobile()); 
            ps.setString(6, t.getGd_victim_location()); 
            ps.setString(7, t.getGd_victim_gender()); 
            ps.setInt(8, t.getGd_victim_age()); 
            ps.setString(9, t.getGd_police_name()); 
            ps.setString(10, t.getGd_type()); 
            ps.setString(11, t.getGd_open_date()); 
            ps.setString(12, t.getGd_open_time()); 
            ps.setString(13, t.getGd_status()); 
            int rs = ps.executeUpdate();
            
            statement = rs;
            System.out.println("test3");
        } catch (Exception e) {
            System.out.println(e + " : From GD DAO");
        }
       return statement;
      
    }


    @Override
    public List<GDModel> getAll() {
        return null;

    }

    @Override
    public List<GDModel> edit(GDModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateVal(GDModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}

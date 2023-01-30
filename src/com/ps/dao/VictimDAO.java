
package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.model.VictimModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.util.List;

public class VictimDAO implements IcommonDAO<VictimModel>{

    @Override
    public int create(VictimModel t) {
        int statement = 0;
        
        dbConnection con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO victim_information (victim_id, criminal_id, victim_first_name, victim_last_name,case_id, victim_gender, victim_father, victim_mother,victim_phone, victim_age, victim_gd_yes_or_no, victim_gd_id, victim_present_ad, victim_permanent_ad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");
            
            
            ps.setString(1, t.getVictim_id());
            ps.setString(2, t.getCriminal_id()); 
            ps.setString(3, t.getCriminal_id()); 
            ps.setString(4, t.getVictim_first_name()); 
            ps.setString(5, t.getVictim_last_name()); 
            ps.setString(6, t.getCase_id()); 
            ps.setString(7, t.getVictim_gender()); 
            ps.setString(8, t.getVictim_father_name()); 
            ps.setString(9, t.getVictim_mother_name()); 
            ps.setString(10, t.getVictim_phone()); 
            ps.setString(11, t.getVictim_age()); 
            ps.setString(12, t.getVictim_gd_yes_or_no()); 
            ps.setString(13, t.getVictim_presentAd()); 
            ps.setString(14, t.getVictim_permanentAd());

            int rs = ps.executeUpdate();
            statement = rs;
      
        } catch (Exception e) {
            System.out.println(e);
        }
        
       return statement;

       
    }

    @Override
    public List<VictimModel> edit(VictimModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VictimModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}

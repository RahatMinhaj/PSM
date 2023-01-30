
package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.gui.DashBoard2;
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

public class GDCatagoryDao implements IcommonDAO<GDCatModel>{

    @Override
    public int create(GDCatModel t) {
          int statement = 0;
        
         dbConnection con = new dbConnection();
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO gd_catagory (gd_cat_id, gd_cat_name) VALUES (? , ?);");
            ps.setString(1, t.getGd_cat_id());
            ps.setString(2, t.getGd_cat_name()); 
            int rs = ps.executeUpdate();
            
            statement = rs;         
        } catch (Exception e) {
            System.out.println(e + " : From GD Category DAO");
        }finally{
              try {
                  con.dbConnect().close();
              } catch (SQLException ex) {
                  System.out.println("GD Category DAO Connection Close Problem : " + ex);
              }
        }
       return statement;
    }

    @Override
    public List<GDCatModel> edit(GDCatModel t) {
        List GDCatList = new ArrayList();
        dbConnection con = new dbConnection();
        
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("select * from gd_catagory where gd_cat_id = ?");
            ps.setString(1, t.getGd_cat_id());
            
            System.out.println(t.getGd_cat_id());

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                t.setGd_cat_id(rs.getString("gd_cat_id"));
                t.setGd_cat_name(rs.getString("gd_cat_name"));
                
                GDCatList.add(t);
            }else{
                System.out.println("No value Found");
            }
                    
                    
                    } catch (SQLException ex) {
            Logger.getLogger(GDDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return GDCatList;
    }

    @Override
    public List<GDCatModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateVal(GDCatModel t) {
         int statement = 0;
         dbConnection con = new dbConnection();
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("UPDATE gd_catagory SET gd_cat_id = ?, gd_cat_name = ? WHERE gd_cat_id = ?;");
            ps.setString(1, t.getGd_cat_id());
            ps.setString(2, t.getGd_cat_name()); 
            ps.setString(3, t.getGd_cat_id()); 
            int rs = ps.executeUpdate();
            statement = rs;
            
        } catch (Exception e) {
            System.out.println(e + " : From GD Category DAO");
        }finally{
              try {
                  con.dbConnect().close();
              } catch (SQLException ex) {
                  System.out.println("GD Category DAO Connection Close Problem : " + ex);
              }
        }
        return statement;
    }

   
    
}

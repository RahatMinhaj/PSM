package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.model.CourtModel;
import com.ps.model.rankModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CourtDAO implements IcommonDAO<CourtModel>{
    
    dbConnection con;

    @Override
    public int create(CourtModel t) {
         int statement = 0;
        
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO court_details (court_id, court_name, court_location) VALUES (? , ?, ?);");
            ps.setString(1, t.getCourt_id());
            ps.setString(2, t.getCourt_name()); 
            ps.setString(3, t.getCourt_location()); 
            int rs = ps.executeUpdate();
            
            statement = rs;         
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
       return statement;
    }


    @Override
    public List<CourtModel> getAll() {
        List courtList  = new ArrayList();
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM court_details");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CourtModel cm = new CourtModel();
                cm.setCourt_id(rs.getString("court_id"));
                cm.setCourt_name(rs.getString("court_name"));
                cm.setCourt_location(rs.getString("court_location"));
                
                courtList.add(cm);
                
                
            }
        } catch (Exception e) {
            System.err.println(e  + " Court get all exception");
        }
        return courtList;
        
    }
    
    
    public void showCourtTable(JTable table){
    DefaultTableModel dftm = new DefaultTableModel();
        
        
        
        dftm.addColumn("Court ID");
        dftm.addColumn("Court Name");
        dftm.addColumn("Court Location");
        
        
        
            con = new dbConnection();
         try {
             
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM court_details;");  
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String courtID = rs.getString("court_id");
                String courtName = rs.getString("court_name");
                String courtLocation = rs.getString("court_location");
                
                Object[] deptRowData = {courtID, courtName,courtLocation};
                dftm.addRow(deptRowData);
            }
            table.setModel(dftm);
            
        } catch (Exception e) {
             System.out.println("Court Data not extracted : " + e);
        }finally{
             try {
                 con.dbConnect().close();
             } catch (Exception e) {
                 System.out.println("close problem");
             }
         }
    
    }

    @Override
    public List<CourtModel> edit(CourtModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}

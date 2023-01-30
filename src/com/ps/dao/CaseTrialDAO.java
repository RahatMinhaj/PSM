
package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.model.CaseTrialModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CaseTrialDAO implements IcommonDAO<CaseTrialModel>{

    @Override
    public int create(CaseTrialModel t) {
 
        int statement = 0;
        dbConnection con = new dbConnection();
        
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO case_trial (case_id, court_name, court_facing_date) VALUES (? , ?, ?);");
            ps.setString(1, t.getCase_id());
            ps.setString(2, t.getCourt_name()); 
            ps.setString(3, t.getCourt_facing_date()); 
            int rs = ps.executeUpdate();
            
            statement = rs;         
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
       return statement;
       
    }

    @Override
    public List<CaseTrialModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

   public void showCaseTrialTable(JTable table){
    DefaultTableModel dftm = new DefaultTableModel();
        
        
        
        dftm.addColumn("Case ID");
        dftm.addColumn("Court Name");
        dftm.addColumn("Case Trial Date");
            dbConnection con = new dbConnection();
         try {
             
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM case_trial;");  
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String caseID = rs.getString("case_id");
                String courtName = rs.getString("court_name");
                String CourtFacingDate = rs.getString("court_facing_date");
                
                Object[] deptRowData = {caseID, courtName,CourtFacingDate};
                dftm.addRow(deptRowData);
            }
            table.setModel(dftm);
            
        } catch (Exception e) {
             System.out.println("Case Trial not extracted : " + e);
        }finally{
             try {
                 con.dbConnect().close();
             } catch (Exception e) {
                 System.out.println("close problem");
             }
         }
    
    }
   
   
   public List<CaseTrialModel> findHearingByCaseID(CaseTrialModel t){
        int statement = 0;
        List<CaseTrialModel> list = new ArrayList<>();
        
        dbConnection con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM case_trial WHERE case_id = ?;");
            ps.setString(1, t.getCase_id());
            
            ResultSet rs = ps.executeQuery();
            
           while(rs.next()){
                CaseTrialModel ctm = new CaseTrialModel();
                ctm.setCase_id(rs.getString("case_id"));
                System.err.println(rs.getString("case_id"));
                ctm.setCourt_name(rs.getString("court_name"));
                System.err.println(rs.getString("court_name"));
                ctm.setCourt_facing_date(rs.getString("court_facing_date"));
                System.err.println(rs.getString("court_facing_date"));
//                System.out.println(rs.getString("department_id"));
//                System.out.println(rs.getString("department_name"));

////                dsb.showDeptID(rs.getString("department_id"), rs.getString("department_name")); 
//                System.out.println(rs.getString("department_id")  + " : deptID");
//                System.out.println(rs.getString(rs.getString("department_name") + " : deptName"));
////                System.out.println("print 2");
                
                list.add(ctm);

            }
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
       return list;
        
   }

    @Override
    public List<CaseTrialModel> edit(CaseTrialModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

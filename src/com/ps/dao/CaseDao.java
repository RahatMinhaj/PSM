/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.model.CaseModel;
import com.ps.model.CourtModel;
import com.ps.model.criminalModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Minhajul Islam
 */
public class CaseDao implements IcommonDAO<CaseModel>{

    @Override
    public int create(CaseModel t) {
       
        int status = 0;
        dbConnection con = new dbConnection();

        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO case_information(case_id, accused_id, victim_id, police_id, crime_type, case_open_date, offence_time, offence_location, witness_name, witness_location, witness_mobile, case_details, case_evidence, case_open_time,case_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? );");

            ps.setString(1, t.getCase_id());
            ps.setString(2, t.getAccused_id());
            ps.setString(3, t.getVictim_id());
            ps.setString(4, t.getPolice_id());
            ps.setString(5, t.getCrime_type());
            ps.setString(6, t.getCase_open_date());
            ps.setString(7, t.getOffence_time());
            ps.setString(8, t.getOffence_location());
            ps.setString(9, t.getWitness_name());
            ps.setString(10, t.getWitness_location());
            ps.setString(11, t.getWitness_mobile());
            ps.setString(12, t.getCase_details());
            ps.setString(13, t.getCase_evidence());
            ps.setString(14, t.getCase_open_time());
            ps.setString(15, t.getCase_status());

            int rs = ps.executeUpdate();
            status = rs;

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }


    @Override
    public List<CaseModel> getAll() {
          List caseList  = new ArrayList();
          dbConnection con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM case_information");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CaseModel cm = new CaseModel();
                cm.setCase_id(rs.getString("case_id"));
                caseList.add(cm);
 
            }
        } catch (Exception e) {
            System.err.println(e  + " Error Occuring from CaseList");
        }
        return caseList;
        
        
        
    }
    
    public void showCaseTable(JTable table){
        DefaultTableModel dftm = new DefaultTableModel();
        
        dftm.addColumn("Case ID");
        dftm.addColumn("Accused ID");
        dftm.addColumn("Victim ID");
        dftm.addColumn("Case Taken By");
        dftm.addColumn("Crime Type");
        dftm.addColumn("Offence Time");
        dftm.addColumn("Offence Location");
        dftm.addColumn("Witness Name");
        dftm.addColumn("Witness Location");
        dftm.addColumn("Witness Mobile");
        dftm.addColumn("Case Evidence");
        dftm.addColumn("Case Details");
        dftm.addColumn("Case Creating Date");
        dftm.addColumn("Case Status");
            dbConnection con = new dbConnection();
         try {
             
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM case_information");  
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String caseID = rs.getString("case_id");
                String accusedID = rs.getString("accused_id");
                String victimID = rs.getString("victim_id");
                String policeID = rs.getString("police_id");
                String crimeType = rs.getString("crime_type");
                String offenceTime = rs.getString("offence_time");
                String offenceLocation = rs.getString("offence_location");
                String witnessName = rs.getString("witness_name");
                String witnessLocation = rs.getString("witness_Location");
                String witnessMobile = rs.getString("witness_mobile");
                String caseEvidence = rs.getString("case_evidence");
                String caseDetails = rs.getString("case_details");
                String caseDate = rs.getString("case_open_date");
                String caseTime = rs.getString("case_open_time");
                String caseStatus = rs.getString("case_status");
                
                Object[] rankRowData = {caseID, accusedID,victimID,policeID,crimeType,offenceTime,offenceLocation,witnessName,witnessLocation,witnessMobile,caseEvidence,caseDetails,caseStatus,caseDate,caseTime};
                dftm.addRow(rankRowData);
            }
            table.setModel(dftm);
            
        } catch (Exception e) {
             System.err.println("showCaseTable : " + e);
        }finally{
             try {
                 con.dbConnect().close();
             } catch (Exception e) {
                 System.out.println("close problem");
             }
         }
        
    }
    
    public void caseAssignedOfficerTable(JTable table){
        DefaultTableModel dftm = new DefaultTableModel();
        
        dftm.addColumn("Assigned Police Name");
        dftm.addColumn("Case ID");
        dftm.addColumn("Accused ID");
        dftm.addColumn("Victim ID");
        dftm.addColumn("Crime Type");
        dftm.addColumn("Offence Time");
        dftm.addColumn("Offence Location");
        dftm.addColumn("Offence Location");
        dftm.addColumn("Witness Name");
        dftm.addColumn("Witness Location");
        dftm.addColumn("Witness Mobile");
        dftm.addColumn("Case Evidence");
        dftm.addColumn("Case Details");
        dftm.addColumn("Case Creating Date");
            dbConnection con = new dbConnection();
         try {
             
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM case_information where case_status = 'Pending';");  
      
            ResultSet rs = ps.executeQuery();
     

            while(rs.next()){
                String caseID = rs.getString("case_id");
                String policeID = rs.getString("police_id");
                String caseDetails = rs.getString("case_details");
                String caseDate = rs.getString("case_open_date");
                String caseTime = rs.getString("case_open_time");
                String caseStatus = rs.getString("case_status");
                
                Object[] rankRowData = {caseID,policeID,caseDetails,caseDate,caseTime,caseStatus};
                dftm.addRow(rankRowData);
            }
             System.out.println("checkassign");
            table.setModel(dftm);
            
        } catch (Exception e) {
             System.err.println("caseAssignedOfficerTable : " + e);
        }finally{
             try {
                 con.dbConnect().close();
             } catch (Exception e) {
                 System.out.println("close problem");
             }
         }

    };
    
    
    
    public void showPendingCase(JTable table){
 
    DefaultTableModel dftm = new DefaultTableModel();
        

        dftm.addColumn("Case ID");
        dftm.addColumn("Assigned Police Name");
        dftm.addColumn("Case Details");
        dftm.addColumn("Case Status");
        dftm.addColumn("Case Creating Date");
        dftm.addColumn("Case Creating Time");
            dbConnection con = new dbConnection();
         try {
             
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM case_information where case_status = 'Pending';");  
      
            ResultSet rs = ps.executeQuery();
     

            while(rs.next()){
                String caseID = rs.getString("case_id");
                String policeID = rs.getString("police_id");
                String caseDetails = rs.getString("case_details");
                String caseDate = rs.getString("case_open_date");
                String caseTime = rs.getString("case_open_time");
                String caseStatus = rs.getString("case_status");
                
                Object[] rankRowData = {caseID,policeID,caseDetails,caseStatus,caseDate,caseTime};
                dftm.addRow(rankRowData);
            }
            table.setModel(dftm);
            
        } catch (Exception e) {
             System.out.println("showPendingCase : " + e);
        }finally{
             try {
                 con.dbConnect().close();
             } catch (Exception e) {
                 System.out.println("close problem");
             }
         }

        
    };

    @Override
    public List<CaseModel> edit(CaseModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateVal(CaseModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}




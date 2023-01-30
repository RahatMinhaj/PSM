package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.gui.Dashboard;
import com.ps.model.departmentModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DepartmentDao implements IcommonDAO<departmentModel>{
    departmentModel dept;
    dbConnection con;
    
    
    

    @Override
    public int create(departmentModel t) {
        int statement = 0;
        
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO department (department_id, department_name) VALUES (? , ?);");
            ps.setString(1, t.getDepartment_id());
            ps.setString(2, t.getDepartment_name()); 
            int rs = ps.executeUpdate();
            statement = rs;
      
        } catch (Exception e) {
            System.out.println(e);
        }
        
       return statement;
    } 

////    @Override
////    public int edit(departmentModel t) {
////         int statement = 0;
////        
////        con = new dbConnection();
////        try {
////            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM department WHERE department_id = ?;");
////            ps.setString(1, t.getDepartment_id());
////            ResultSet rs = ps.executeQuery();
////            
////            if(rs.next()){
////                Dashboard dsb = new Dashboard();
//////                System.out.println(rs.getString("department_id"));
//////                System.out.println(rs.getString("department_name"));
////
////////                dsb.showDeptID(rs.getString("department_id"), rs.getString("department_name")); 
//////                System.out.println(rs.getString("department_id")  + " : deptID");
//////                System.out.println(rs.getString(rs.getString("department_name") + " : deptName"));
////////                System.out.println("print 2");
////
////            }
//            
//            
//            
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        
//       return statement;
//        
//    }
//    
//    
    
    
    public void showDeptTable(JTable table){
        DefaultTableModel dftm = new DefaultTableModel();
        
        
        
        dftm.addColumn("Department ID");
        dftm.addColumn("Department Name");
        
        
        
            con = new dbConnection();
         try {
             
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM department");  
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String deptID = rs.getString("department_id");
                String deptName = rs.getString("department_name");
                
                Object[] deptRowData = {deptID, deptName};
                dftm.addRow(deptRowData);
            }
            table.setModel(dftm);
            
        } catch (Exception e) {
             System.out.println("Data not extracted : " + e);
        }finally{
             try {
                 con.dbConnect().close();
             } catch (Exception e) {
                 System.out.println("close problem");
             }
         }
        
    }

    @Override
    public List<departmentModel> getAll() {
        List deptList  = new ArrayList();
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM department");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                dept = new departmentModel();
                dept.setDepartment_id(rs.getString("department_id"));
                dept.setDepartment_name(rs.getString("department_name"));
                
                deptList.add(dept);
                
                
            }
        } catch (Exception e) {
        }
        
        
        
        
        
        
        
        return deptList;
        
        
        
    }
   
    
 
    public departmentModel getByID(departmentModel t) {
         int statement = 0;
        departmentModel dm = new departmentModel();
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM department WHERE department_id = ?;");
            ps.setString(1, t.getDepartment_id());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                dm.setDepartment_name(rs.getString("department_name"));
                Dashboard dsb = new Dashboard();
//                System.out.println(rs.getString("department_id"));
//                System.out.println(rs.getString("department_name"));

////                dsb.showDeptID(rs.getString("department_id"), rs.getString("department_name")); 
//                System.out.println(rs.getString("department_id")  + " : deptID");
//                System.out.println(rs.getString(rs.getString("department_name") + " : deptName"));
////                System.out.println("print 2");

            }
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
       return dm;
        
    }

    @Override
    public List<departmentModel> edit(departmentModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


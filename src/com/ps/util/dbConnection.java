package com.ps.util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class dbConnection {
    
    public Connection dbConnect (){
        Connection con = null;
        try {
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/psConnection", "root", "root");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e  + "an Error From Databse"
                   + "");
        }
        
        return con;
    }
    
}

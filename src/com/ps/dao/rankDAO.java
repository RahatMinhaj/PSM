package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.model.rankModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class rankDAO implements IcommonDAO<rankModel>{
    
    rankModel rank;
    dbConnection con;
    
    
    
    @Override
    public List<rankModel> getAll() {
        con = new dbConnection();
        List rankList = new ArrayList();
        String sql = "select * from police_rank";
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                rank = new rankModel();
                rank.setRank_id(rs.getString("rank_id"));
                rank.setRank_name(rs.getString("rank_name"));
                
                rankList.add(rank);
                
            }
            
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
        
        
        
        
        
        return rankList;
 
    }
    

    @Override
    public int create(rankModel t) {
        int statement = 0;
        
        con = new dbConnection();
        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO police_rank (rank_id, rank_name) VALUES (? , ?);");
            ps.setString(1, t.getRank_id());
            ps.setString(2, t.getRank_name()); 
            int rs = ps.executeUpdate();
            statement = rs;         
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
       return statement;
    }
    
    
    public void showRankTable(JTable table){
        DefaultTableModel dftm = new DefaultTableModel();
        
        dftm.addColumn("Rank ID");
        dftm.addColumn("Rank Name");
        
        
        
            dbConnection con = new dbConnection();
         try {
             
            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM police_rank");  
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String rankID = rs.getString("rank_id");
                String rankName = rs.getString("rank_name");
                
                Object[] rankRowData = {rankID, rankName};
                dftm.addRow(rankRowData);
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
    public List<rankModel> edit(rankModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}

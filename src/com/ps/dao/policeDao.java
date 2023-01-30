package com.ps.dao;

import com.ps.common.IcommonDAO;
import com.ps.model.policeModel;
import com.ps.util.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class policeDao implements IcommonDAO<policeModel> {

    @Override
    public int create(policeModel t) {
        int status = 0;
        dbConnection con = new dbConnection();

        try {
            PreparedStatement ps = con.dbConnect().prepareStatement("INSERT INTO police_information(police_id, batch_number, first_name, last_name, email, father_name, gender, birth_date, personal_phone, office_phone, rank_name, weapon_id, weapon_serial, department_name, present_address, permanent_address, placement_date, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            ps.setString(1, t.getPolice_id());
            ps.setString(2, t.getBatch_number());
            ps.setString(3, t.getFirst_name());
            ps.setString(4, t.getLast_name());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getFather_name());
            ps.setString(7, t.getGender());
            ps.setString(8, t.getBirth_date());
            ps.setString(9, t.getPersonal_phone());
            ps.setString(10, t.getOffice_phone());
            ps.setString(11, t.getRank_name());
            ps.setString(12, t.getWeapon_id());
            ps.setString(13, t.getWeapon_serial());
            ps.setString(14, t.getDepartment_name());
            ps.setString(15, t.getPresent_adress());
            ps.setString(16, t.getPermanent_address());
            ps.setString(17, t.getJoin_date());
            ps.setString(18, t.getCreation_date());

            int rs = ps.executeUpdate();
            status = rs;

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;

    }

//    @Override
//    public int edit(policeModel t) {
//        int status = 0;
//        dbConnection con = new dbConnection();
//
//        try {
//            PreparedStatement ps = con.dbConnect().prepareStatement("");
//
//            ps.setString(1, t.getPolice_id());
//            ps.setString(2, t.getBatch_number());
//            ps.setString(3, t.getFirst_name());
//            ps.setString(4, t.getLast_name());
//            ps.setString(5, t.getEmail());
//            ps.setString(6, t.getFather_name());
//            ps.setString(7, t.getGender());
//            ps.setString(8, t.getBirth_date());
//            ps.setString(9, t.getPersonal_phone());
//            ps.setString(10, t.getOffice_phone());
//            ps.setString(12, t.getRank_name());
//            ps.setString(13, t.getWeapon_id());
//            ps.setString(14, t.getWeapon_serial());
//            ps.setString(16, t.getDepartment_name());
//            ps.setString(17, t.getPresent_adress());
//            ps.setString(18, t.getPermanent_address());
//            ps.setString(19, t.getJoin_date());
//            ps.setString(20, t.getCreation_date());
//
//            int rs = ps.executeUpdate();
//            status = rs;
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return status;
//
//    }

    public void showPoliceTable(JTable table) {
        DefaultTableModel dftm = new DefaultTableModel();

        dftm.addColumn("Police ID");
        dftm.addColumn("Batch Number");
        dftm.addColumn("First Name");
        dftm.addColumn("Last Name");
        dftm.addColumn("Email");
        dftm.addColumn("Father's Name");
        dftm.addColumn("Personal Phone");
        dftm.addColumn("Office Phone");
        dftm.addColumn("Rank ID");
        dftm.addColumn("Rank Name");
        dftm.addColumn("Weapon ID");
        dftm.addColumn("Weapon Serial");
        dftm.addColumn("Weapon Assigned");
        dftm.addColumn("Department ID");
        dftm.addColumn("Department Name");
        dftm.addColumn("Present Address");
        dftm.addColumn("Permanent Address ");
        dftm.addColumn("Join Date");
        dftm.addColumn("Creation Date");

        dbConnection con = new dbConnection();
        try {
            
            

            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM police_information;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String polID = rs.getString("police_id");
                String polBatch = rs.getString("batch_number");
                String polFirstName = rs.getString("first_name");
                String polLasttName = rs.getString("last_name");
                String polEmail = rs.getString("email");
                String polFather = rs.getString("father_name");
                String polGender = rs.getString("gender");
                String polBdate = rs.getString("birth_date");
                String polPersonalPhone = rs.getString("personal_phone");
                String polOfficePhone = rs.getString("office_phone");
                String polRankID = rs.getString("rank_id");
                String polRankName = rs.getString("rank_name");
                String polweaponID = rs.getString("weapon_id");
                String polweaponSerial = rs.getString("weapon_serial");
                String polDepartmentID = rs.getString("department_id");
                String polDepartmentName = rs.getString("department_name");
                String polPresentAddress = rs.getString("present_address");
                String polPermanentAddress = rs.getString("permanent_address");
                String polJoinDate = rs.getString("placement_date");
                String polCreationDate = rs.getString("creation_date");

                Object[] rankRowData = {polID, polBatch, polFirstName, polLasttName, polEmail, polFather, polGender, polBdate, polPersonalPhone, polOfficePhone, polRankID, polRankName, polweaponID, polweaponSerial, polDepartmentID, polDepartmentName, polPresentAddress, polPermanentAddress, polJoinDate, polCreationDate};
                dftm.addRow(rankRowData);
            }
            table.setModel(dftm);

        } catch (Exception e) {
            System.out.println("Data not extracted : " + e);
        } finally {
            try {
                con.dbConnect().close();
            } catch (Exception e) {
                System.out.println("close problem");
            }
        }

    }

    public void showOfficerList(JTable table) {
        DefaultTableModel dftm = new DefaultTableModel();

        dftm.addColumn("Police ID");
        dftm.addColumn("Batch Number");
        dftm.addColumn("First Name");
        dftm.addColumn("Last Name");
        dftm.addColumn("Email");
        dftm.addColumn("Father's Name");
        dftm.addColumn("Personal Phone");
        dftm.addColumn("Office Phone");
        dftm.addColumn("Rank ID");
        dftm.addColumn("Rank Name");
        dftm.addColumn("Weapon ID");
        dftm.addColumn("Weapon Serial");
        dftm.addColumn("Weapon Assigned");
        dftm.addColumn("Department ID");
        dftm.addColumn("Department Name");
        dftm.addColumn("Present Address");
        dftm.addColumn("Permanent Address ");
        dftm.addColumn("Join Date");
        dftm.addColumn("Creation Date");

        dbConnection con = new dbConnection();
        try {

            PreparedStatement ps = con.dbConnect().prepareStatement("SELECT * FROM police_information where rank_name = 'super' OR rank_name = 'Officer';");
            ResultSet rs = ps.executeQuery();
            

            while (rs.next()) {
                System.out.println("");
                String polID = rs.getString("police_id");
                String polBatch = rs.getString("batch_number");
                String polFirstName = rs.getString("first_name");
                String polLasttName = rs.getString("last_name");
                String polEmail = rs.getString("email");
                String polFather = rs.getString("father_name");
                String polGender = rs.getString("gender");
                String polBdate = rs.getString("birth_date");
                String polPersonalPhone = rs.getString("personal_phone");
                String polOfficePhone = rs.getString("office_phone");
                String polRankID = rs.getString("rank_id");
                String polRankName = rs.getString("rank_name");
                String polweaponID = rs.getString("weapon_id");
                String polweaponSerial = rs.getString("weapon_serial");
                String polDepartmentID = rs.getString("department_id");
                String polDepartmentName = rs.getString("department_name");
                String polPresentAddress = rs.getString("present_address");
                String polPermanentAddress = rs.getString("permanent_address");
                String polJoinDate = rs.getString("placement_date");
                String polCreationDate = rs.getString("creation_date");

                Object[] rankRowData = {polID, polBatch, polFirstName, polLasttName, polEmail, polFather, polGender, polBdate, polPersonalPhone, polOfficePhone, polRankID, polRankName, polweaponID, polweaponSerial, polDepartmentID, polDepartmentName, polPresentAddress, polPermanentAddress, polJoinDate, polCreationDate};
                dftm.addRow(rankRowData);
            }
            table.setModel(dftm);

        } catch (Exception e) {
            System.out.println("Data not extracted : " + e);
        } finally {
            try {
                con.dbConnect().close();
            } catch (Exception e) {
                System.out.println("close problem");
            }
        }

    }

    @Override
    public List<policeModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<policeModel> edit(policeModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

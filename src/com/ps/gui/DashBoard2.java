/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ps.gui;

import com.ps.common.IcommonGUI;
import com.ps.dao.GDCatagoryDao;
import com.ps.dao.GDDAO;
import com.ps.model.GDCatModel;
import com.ps.model.GDModel;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Minhajul Islam
 */
public class DashBoard2 extends javax.swing.JFrame implements IcommonGUI {

    //=======Model List=========
    GDModel gdm;
    GDCatModel gdcm;
    
    
    //=======Dao List=========
    GDDAO gd;
    GDCatagoryDao gdcdao;


    //Tables
    DefaultTableModel dftm;

    
    public DashBoard2() {
        initComponents();
        setLocationRelativeTo(null);
        
    
    }

    
    
    //======================GD Interface========================
    
    @Override
    public void GDFillreset() {
        GDTypeIDSearchBox.setText("");
        GDNameSearcBox.setText("");
        
    }

    
    
     @Override
    public void GDCatGetEditVal() {
        
        gdcdao = new GDCatagoryDao();
        gdcm = new GDCatModel();
        
        String SearchBox = editGDSearchBox.getText();
        gdcm.setGd_cat_id(SearchBox);
        

        gdcdao.edit(gdcm);
        
        String dbCatID = gdcdao.edit(gdcm).get(0).getGd_cat_id();
        String dbCatName = gdcdao.edit(gdcm).get(0).getGd_cat_name();
        
        GDTypeIDSearchBox.setText(dbCatID);
        GDNameSearcBox.setText(dbCatName);

    }

    
      @Override
    public void GDCatUpdateVal() {
          gdcm = new GDCatModel();
        gdcdao = new GDCatagoryDao();
        
        
        gdcm.setGd_cat_id(GDTypeIDSearchBox.getText());
        gdcm.setGd_cat_name(GDNameSearcBox.getText());
        
        int status = gdcdao.updateVal(gdcm);
        
        
           if (status > 0) {
            JOptionPane.showMessageDialog(null, "Value Updated!");
               GDFillreset();
        } else {
            JOptionPane.showMessageDialog(null, "Value is not Updated");
        }
        
        
        

    }
    
    
    
     @Override
    public void createGD() {
          gdm = new GDModel();
          gd = new GDDAO();
        
          String gdID = GDidBox.getText();
          String GDVictim = GDVictimIDBox.getText();
          String GDVicFirst = GDVictimFirstNameBox.getText();
          String GDVicLast = GDVictimLastNameBox.getText();
          String GDVicMob = GDVictimMobileBox.getText();
          String GDVicLoc = GDVictimLocationBox.getText(); 
          String GDVictimGender = null; 
        if(GDGenderBox.getSelectedItem().equals("Male")){
            GDVictimGender = "Male";
        }else{
            GDVictimGender = "Female";
        }
        
        int GDVicAge = Integer.parseInt(GDVictimAgeBox.getText());
//        String GDPol = GDRecievePoliceBox.getSelectedItem().toString();
        String GDDetails = GDDetailsBox.getText();
        String GDStatus = GDStatusBox.getSelectedItem().toString();
  
        gdm.setGd_id(gdID);
        gdm.setGd_victim_id(GDVictim);
        gdm.setGd_victim_first_name(GDVicFirst);
        gdm.setGd_victim_last_name(GDVicLast);
        gdm.setGd_victim_mobile(GDVicMob);
        gdm.setGd_victim_location(GDVicLoc);
        gdm.setGd_victim_gender(GDVictimGender);
        gdm.setGd_victim_age(GDVicAge);
//        gdm.setGd_police_name(GDPol);
        gdm.setGd_details(GDDetails);
        
        java.util.Date cd = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm aa");
        String caseCreationDate= df.format(cd);
        String caseCreationTime= tf.format(cd);
        
        gdm.setGd_open_date(caseCreationDate);
        gdm.setGd_open_time(caseCreationTime);
        gdm.setGd_status(GDStatus);
         System.out.println(GDStatus + " : Status");
        
      
        
        
        
        int status = gd.create(gdm);
        
        if (status > 0) {
            JOptionPane.showMessageDialog(null, "New GD Created!");
        } else {
            JOptionPane.showMessageDialog(null, "New GD is Not Created!");
        }
        
        
        
    }
    
     @Override
    public void createGDCat() {
        gdcm = new GDCatModel();
        gdcdao = new GDCatagoryDao();
        
        gdcm.setGd_cat_id(GDTypeIDBox.getText());
        gdcm.setGd_cat_name(GDTypeNameBox.getText());
        
        int status = gdcdao.create(gdcm);
        
        if (status > 0) {
            JOptionPane.showMessageDialog(null, "New GD Category Added!");
        } else {
            JOptionPane.showMessageDialog(null, "GD Category is Not Added!!");
        }
       
    }


   

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menubox = new javax.swing.JPanel();
        userName19 = new javax.swing.JLabel();
        activity1 = new javax.swing.JLabel();
        logOut1 = new javax.swing.JLabel();
        addNewAdmin1 = new javax.swing.JLabel();
        editProfile1 = new javax.swing.JLabel();
        personalInfo1 = new javax.swing.JLabel();
        comInfo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        policeInfo3 = new javax.swing.JLabel();
        dash5 = new javax.swing.JLabel();
        dash6 = new javax.swing.JLabel();
        userName20 = new javax.swing.JLabel();
        userName21 = new javax.swing.JLabel();
        dash7 = new javax.swing.JLabel();
        dash8 = new javax.swing.JLabel();
        titlebar = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        menuitem = new javax.swing.JPanel();
        GDboard = new javax.swing.JPanel();
        buttonPane7 = new javax.swing.JPanel();
        adpolb6 = new javax.swing.JButton();
        createrankb6 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        createdeptb5 = new javax.swing.JButton();
        solvedcaseb7 = new javax.swing.JButton();
        editpolb6 = new javax.swing.JButton();
        jButton95 = new javax.swing.JButton();
        GDboardDynamic = new javax.swing.JPanel();
        GDhome = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        policeTable5 = new javax.swing.JTable();
        createNewGD = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        GDVictimFirstNameBox = new javax.swing.JTextField();
        jLabel250 = new javax.swing.JLabel();
        jLabel251 = new javax.swing.JLabel();
        jLabel252 = new javax.swing.JLabel();
        GDVictimIDBox = new javax.swing.JTextField();
        jLabel257 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel258 = new javax.swing.JLabel();
        GDidBox = new javax.swing.JTextField();
        GDVictimLocationBox = new javax.swing.JTextField();
        GDVictimMobileBox = new javax.swing.JTextField();
        jLabel259 = new javax.swing.JLabel();
        jLabel260 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        GDVictimAgeBox = new javax.swing.JTextField();
        jLabel270 = new javax.swing.JLabel();
        jLabel271 = new javax.swing.JLabel();
        GDGenderBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        GDDetailsBox = new javax.swing.JTextArea();
        GDRecievePoliceBox = new javax.swing.JComboBox();
        jLabel272 = new javax.swing.JLabel();
        GDTypeBox = new javax.swing.JComboBox();
        GDVictimLastNameBox = new javax.swing.JTextField();
        jLabel338 = new javax.swing.JLabel();
        GDStatusBox = new javax.swing.JComboBox();
        createGDtype = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton44 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jLabel285 = new javax.swing.JLabel();
        GDTypeIDBox = new javax.swing.JTextField();
        jLabel286 = new javax.swing.JLabel();
        GDTypeNameBox = new javax.swing.JTextField();
        jLabel287 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        deptTable2 = new javax.swing.JTable();
        jLabel288 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jLabel289 = new javax.swing.JLabel();
        GDTypeIDSearchBox = new javax.swing.JTextField();
        jLabel290 = new javax.swing.JLabel();
        GDNameSearcBox = new javax.swing.JTextField();
        editGDSearchBox = new javax.swing.JTextField();
        jButton57 = new javax.swing.JButton();
        jLabel292 = new javax.swing.JLabel();
        jLabel293 = new javax.swing.JLabel();
        GDstatus = new javax.swing.JPanel();
        GDclosed3 = new javax.swing.JPanel();
        jScrollPane41 = new javax.swing.JScrollPane();
        GDStatusPendingTable = new javax.swing.JTable();
        jLabel471 = new javax.swing.JLabel();
        GDclosed2 = new javax.swing.JPanel();
        jScrollPane40 = new javax.swing.JScrollPane();
        GDStatusWithdrwanTable = new javax.swing.JTable();
        jLabel470 = new javax.swing.JLabel();
        GDclosed = new javax.swing.JPanel();
        jScrollPane38 = new javax.swing.JScrollPane();
        GDStatusSolvedTable = new javax.swing.JTable();
        jLabel469 = new javax.swing.JLabel();
        editGD = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        GDVictimFirstNameSearchBox = new javax.swing.JTextField();
        jLabel273 = new javax.swing.JLabel();
        jLabel274 = new javax.swing.JLabel();
        jLabel275 = new javax.swing.JLabel();
        GDVictimSearchIDBox = new javax.swing.JTextField();
        jLabel276 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jLabel277 = new javax.swing.JLabel();
        GDSearchIDBox = new javax.swing.JTextField();
        GDVictimLocationSearchBox = new javax.swing.JTextField();
        GDVictimMobileSearchBox = new javax.swing.JTextField();
        jLabel278 = new javax.swing.JLabel();
        jLabel279 = new javax.swing.JLabel();
        jLabel280 = new javax.swing.JLabel();
        GDVictimAgeSearchBox = new javax.swing.JTextField();
        jLabel281 = new javax.swing.JLabel();
        jLabel282 = new javax.swing.JLabel();
        GDGenderSearchBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        GDDetailsSearchBox = new javax.swing.JTextArea();
        GDRecievedPoliceIDSearchBox = new javax.swing.JComboBox();
        jLabel283 = new javax.swing.JLabel();
        GDTypeSearchBox = new javax.swing.JComboBox();
        jLabel284 = new javax.swing.JLabel();
        GDSearchByIDBox = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        GDVictimLastNameSearchBox = new javax.swing.JTextField();
        weaponBoard = new javax.swing.JPanel();
        buttonPane3 = new javax.swing.JPanel();
        adpolb2 = new javax.swing.JButton();
        createrankb2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        createdeptb1 = new javax.swing.JButton();
        solvedcaseb3 = new javax.swing.JButton();
        editpolb2 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        weaponBoardDynamic = new javax.swing.JPanel();
        wbhome = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        policeTable1 = new javax.swing.JTable();
        createNewWeapon = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        polFirstNameBox1 = new javax.swing.JTextField();
        jLabel241 = new javax.swing.JLabel();
        jLabel242 = new javax.swing.JLabel();
        jLabel243 = new javax.swing.JLabel();
        polLastNameBox1 = new javax.swing.JTextField();
        jLabel248 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel253 = new javax.swing.JLabel();
        polIDBox1 = new javax.swing.JTextField();
        polFatherNameBox1 = new javax.swing.JTextField();
        polMailBox1 = new javax.swing.JTextField();
        jLabel256 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        createWPCat = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton43 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jLabel262 = new javax.swing.JLabel();
        deptIDBox1 = new javax.swing.JTextField();
        jLabel263 = new javax.swing.JLabel();
        deptNameBox1 = new javax.swing.JTextField();
        jLabel264 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        deptTable1 = new javax.swing.JTable();
        jLabel265 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jLabel266 = new javax.swing.JLabel();
        deptIDBox6 = new javax.swing.JTextField();
        jLabel267 = new javax.swing.JLabel();
        deptNameBox6 = new javax.swing.JTextField();
        editDeptSearchBar1 = new javax.swing.JTextField();
        jButton53 = new javax.swing.JButton();
        jLabel268 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        weaponStock = new javax.swing.JPanel();
        jLabel291 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        weaponStockTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        editWeapon = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        polFirstNameBox2 = new javax.swing.JTextField();
        jLabel244 = new javax.swing.JLabel();
        jLabel245 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        polLastNameBox2 = new javax.swing.JTextField();
        jLabel249 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel254 = new javax.swing.JLabel();
        jLabel255 = new javax.swing.JLabel();
        polIDBox2 = new javax.swing.JTextField();
        polFatherNameBox2 = new javax.swing.JTextField();
        polMailBox2 = new javax.swing.JTextField();
        jLabel247 = new javax.swing.JLabel();
        polIDBox3 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        vehicleboard = new javax.swing.JPanel();
        buttonPane4 = new javax.swing.JPanel();
        adpolb3 = new javax.swing.JButton();
        createrankb3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        createdeptb2 = new javax.swing.JButton();
        solvedcaseb4 = new javax.swing.JButton();
        editpolb3 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        vehicleboardDynamic = new javax.swing.JPanel();
        vehicleHome = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        policeTable2 = new javax.swing.JTable();
        addNewVehicle = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        polFirstNameBox5 = new javax.swing.JTextField();
        jLabel294 = new javax.swing.JLabel();
        jLabel295 = new javax.swing.JLabel();
        jLabel296 = new javax.swing.JLabel();
        polLastNameBox5 = new javax.swing.JTextField();
        jLabel297 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jLabel298 = new javax.swing.JLabel();
        polIDBox7 = new javax.swing.JTextField();
        polFatherNameBox7 = new javax.swing.JTextField();
        polMailBox5 = new javax.swing.JTextField();
        jLabel299 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel316 = new javax.swing.JLabel();
        polMailBox7 = new javax.swing.JTextField();
        jLabel317 = new javax.swing.JLabel();
        polFatherNameBox9 = new javax.swing.JTextField();
        createVHcat = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jButton45 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jLabel300 = new javax.swing.JLabel();
        deptIDBox3 = new javax.swing.JTextField();
        jLabel301 = new javax.swing.JLabel();
        deptNameBox3 = new javax.swing.JTextField();
        jLabel302 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        deptTable3 = new javax.swing.JTable();
        jLabel303 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jLabel304 = new javax.swing.JLabel();
        deptIDBox8 = new javax.swing.JTextField();
        jLabel305 = new javax.swing.JLabel();
        deptNameBox8 = new javax.swing.JTextField();
        editDeptSearchBar3 = new javax.swing.JTextField();
        jButton62 = new javax.swing.JButton();
        jLabel306 = new javax.swing.JLabel();
        jLabel307 = new javax.swing.JLabel();
        weaponStock1 = new javax.swing.JPanel();
        jLabel308 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        weaponStockTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        editVehicle = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel315 = new javax.swing.JLabel();
        polIDBox9 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jLabel318 = new javax.swing.JLabel();
        polIDBox10 = new javax.swing.JTextField();
        jLabel319 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel320 = new javax.swing.JLabel();
        polFirstNameBox7 = new javax.swing.JTextField();
        jLabel321 = new javax.swing.JLabel();
        polLastNameBox7 = new javax.swing.JTextField();
        jLabel322 = new javax.swing.JLabel();
        polMailBox8 = new javax.swing.JTextField();
        jLabel323 = new javax.swing.JLabel();
        polFatherNameBox10 = new javax.swing.JTextField();
        jLabel324 = new javax.swing.JLabel();
        polMailBox9 = new javax.swing.JTextField();
        jLabel325 = new javax.swing.JLabel();
        polFatherNameBox11 = new javax.swing.JTextField();
        dumpboard = new javax.swing.JPanel();
        buttonPane5 = new javax.swing.JPanel();
        adpolb4 = new javax.swing.JButton();
        createrankb4 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        createdeptb3 = new javax.swing.JButton();
        solvedcaseb5 = new javax.swing.JButton();
        editpolb4 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        dumpDynamicBoard = new javax.swing.JPanel();
        dumpHome = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        policeTable3 = new javax.swing.JTable();
        addNewDumpItem = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        polFirstNameBox6 = new javax.swing.JTextField();
        jLabel309 = new javax.swing.JLabel();
        jLabel310 = new javax.swing.JLabel();
        jLabel311 = new javax.swing.JLabel();
        polLastNameBox6 = new javax.swing.JTextField();
        jLabel312 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jLabel313 = new javax.swing.JLabel();
        polIDBox8 = new javax.swing.JTextField();
        polFatherNameBox8 = new javax.swing.JTextField();
        polMailBox6 = new javax.swing.JTextField();
        jLabel314 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox();
        jLabel326 = new javax.swing.JLabel();
        jLabel327 = new javax.swing.JLabel();
        polFatherNameBox12 = new javax.swing.JTextField();
        jComboBox15 = new javax.swing.JComboBox();
        jLabel346 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox();
        jLabel347 = new javax.swing.JLabel();
        polFatherNameBox15 = new javax.swing.JTextField();
        jLabel348 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        createDumpcat = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jButton46 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jLabel328 = new javax.swing.JLabel();
        deptIDBox4 = new javax.swing.JTextField();
        jLabel329 = new javax.swing.JLabel();
        deptNameBox4 = new javax.swing.JTextField();
        jLabel330 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        deptTable4 = new javax.swing.JTable();
        jLabel331 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jButton65 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jLabel332 = new javax.swing.JLabel();
        deptIDBox9 = new javax.swing.JTextField();
        jLabel333 = new javax.swing.JLabel();
        deptNameBox9 = new javax.swing.JTextField();
        editDeptSearchBar4 = new javax.swing.JTextField();
        jButton67 = new javax.swing.JButton();
        jLabel334 = new javax.swing.JLabel();
        jLabel335 = new javax.swing.JLabel();
        dumpStock = new javax.swing.JPanel();
        jLabel336 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        weaponStockTable2 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        editDump = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jLabel337 = new javax.swing.JLabel();
        polIDBox11 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jLabel349 = new javax.swing.JLabel();
        polLastNameBox9 = new javax.swing.JTextField();
        polFirstNameBox9 = new javax.swing.JTextField();
        polFatherNameBox16 = new javax.swing.JTextField();
        jLabel350 = new javax.swing.JLabel();
        polMailBox10 = new javax.swing.JTextField();
        jLabel351 = new javax.swing.JLabel();
        jLabel352 = new javax.swing.JLabel();
        jLabel353 = new javax.swing.JLabel();
        polIDBox13 = new javax.swing.JTextField();
        jLabel354 = new javax.swing.JLabel();
        polFatherNameBox17 = new javax.swing.JTextField();
        jComboBox17 = new javax.swing.JComboBox();
        jLabel355 = new javax.swing.JLabel();
        jLabel356 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox();
        jLabel357 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jComboBox19 = new javax.swing.JComboBox();
        jLabel358 = new javax.swing.JLabel();
        polFatherNameBox18 = new javax.swing.JTextField();
        jLabel359 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menubox.setBackground(new java.awt.Color(51, 51, 51));
        menubox.setPreferredSize(new java.awt.Dimension(250, 650));
        menubox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userName19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userName19.setForeground(new java.awt.Color(255, 255, 255));
        menubox.add(userName19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        activity1.setForeground(new java.awt.Color(51, 255, 0));
        activity1.setText("Online");
        menubox.add(activity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        logOut1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logOut1.setForeground(new java.awt.Color(255, 255, 255));
        logOut1.setText("Log Out");
        menubox.add(logOut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 60, -1));

        addNewAdmin1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addNewAdmin1.setForeground(new java.awt.Color(255, 255, 255));
        addNewAdmin1.setText("Add New Admin");
        menubox.add(addNewAdmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, 100, -1));

        editProfile1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editProfile1.setForeground(new java.awt.Color(255, 255, 255));
        editProfile1.setText("Edit Your Profile");
        menubox.add(editProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 110, -1));

        personalInfo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        personalInfo1.setForeground(new java.awt.Color(153, 153, 153));
        personalInfo1.setText("Personal Information");
        menubox.add(personalInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 150, -1));

        comInfo.setBackground(new java.awt.Color(0, 51, 51));
        comInfo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        comInfo.setForeground(new java.awt.Color(255, 255, 255));
        comInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comInfo.setText("Police Page");
        comInfo.setOpaque(true);
        menubox.add(comInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 50));

        jLabel1.setText("jLabel1");
        menubox.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, -1, -1));

        policeInfo3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        policeInfo3.setForeground(new java.awt.Color(255, 255, 255));
        policeInfo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        policeInfo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-modern-server-computer-stacked-on-each-other-24.png"))); // NOI18N
        policeInfo3.setText(" GD Information");
        policeInfo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                policeInfo3MouseClicked(evt);
            }
        });
        menubox.add(policeInfo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 250, 50));

        dash5.setBackground(new java.awt.Color(0, 51, 51));
        dash5.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        dash5.setForeground(new java.awt.Color(255, 255, 255));
        dash5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash5.setText("Back");
        dash5.setOpaque(true);
        dash5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash5MouseExited(evt);
            }
        });
        menubox.add(dash5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 250, 50));

        dash6.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        dash6.setForeground(new java.awt.Color(255, 255, 255));
        dash6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-police-car-24.png"))); // NOI18N
        dash6.setText(" Vehicle Information");
        dash6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash6MouseExited(evt);
            }
        });
        menubox.add(dash6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 250, 50));

        userName20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userName20.setForeground(new java.awt.Color(255, 255, 255));
        userName20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/police.png"))); // NOI18N
        menubox.add(userName20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 70, 70));

        userName21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userName21.setForeground(new java.awt.Color(255, 255, 255));
        userName21.setText("MinhajulIslam");
        menubox.add(userName21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        dash7.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        dash7.setForeground(new java.awt.Color(255, 255, 255));
        dash7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-tow-truck-50 (1).png"))); // NOI18N
        dash7.setText(" Weapon Information");
        dash7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash7MouseExited(evt);
            }
        });
        menubox.add(dash7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 250, 50));

        dash8.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        dash8.setForeground(new java.awt.Color(255, 255, 255));
        dash8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-tow-truck-50 (1).png"))); // NOI18N
        dash8.setText(" Dumpyard");
        dash8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash8MouseExited(evt);
            }
        });
        menubox.add(dash8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 250, 50));

        jPanel1.add(menubox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 680));

        titlebar.setBackground(new java.awt.Color(0, 51, 51));
        titlebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        titlebar.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, -1, -1));

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(153, 153, 153));
        jLabel142.setText("Version 1.0");
        jLabel142.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        titlebar.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 234, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Police Station Management System");
        titlebar.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        titlebar.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, -1, -1));

        jPanel1.add(titlebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1000, 50));

        menuitem.setBackground(new java.awt.Color(204, 255, 204));
        menuitem.setLayout(new java.awt.CardLayout());

        GDboard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane7.setBackground(new java.awt.Color(234, 238, 243));
        buttonPane7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adpolb6.setText("Add New GD");
        adpolb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpolb6ActionPerformed(evt);
            }
        });
        buttonPane7.add(adpolb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, 50));

        createrankb6.setText("GD Type");
        createrankb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createrankb6ActionPerformed(evt);
            }
        });
        buttonPane7.add(createrankb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 130, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Police Information Update / Delete Or Modify");
        buttonPane7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        createdeptb5.setText("GD Status");
        createdeptb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createdeptb5ActionPerformed(evt);
            }
        });
        buttonPane7.add(createdeptb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 130, 50));

        solvedcaseb7.setText("Demo");
        solvedcaseb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcaseb7ActionPerformed(evt);
            }
        });
        buttonPane7.add(solvedcaseb7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 130, 50));

        editpolb6.setText("GD Edit");
        editpolb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpolb6ActionPerformed(evt);
            }
        });
        buttonPane7.add(editpolb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 130, 50));

        jButton95.setText("Demo ");
        jButton95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton95ActionPerformed(evt);
            }
        });
        buttonPane7.add(jButton95, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 130, 50));

        GDboardDynamic.setBackground(new java.awt.Color(252, 250, 246));
        GDboardDynamic.setPreferredSize(new java.awt.Dimension(860, 492));
        GDboardDynamic.setLayout(new java.awt.CardLayout());

        GDhome.setBackground(new java.awt.Color(234, 238, 243));
        GDhome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("All The GD List:");
        GDhome.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane37.setBackground(new java.awt.Color(252, 250, 246));

        policeTable5.setBackground(new java.awt.Color(234, 238, 243));
        policeTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane37.setViewportView(policeTable5);

        GDhome.add(jScrollPane37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        GDboardDynamic.add(GDhome, "card5");

        createNewGD.setBackground(new java.awt.Color(252, 250, 246));
        createNewGD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("Please Input GD Details:");
        createNewGD.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton14.setText("Submit");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        createNewGD.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createNewGD.add(GDVictimFirstNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 200, 30));

        jLabel250.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel250.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel250.setText("Victim's First Name:");
        createNewGD.add(jLabel250, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 210, 30));

        jLabel251.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel251.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel251.setText("Victim's Mobile:");
        createNewGD.add(jLabel251, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 230, 30));

        jLabel252.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel252.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel252.setText("GD ID:");
        createNewGD.add(jLabel252, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 110, 30));
        createNewGD.add(GDVictimIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 200, 30));

        jLabel257.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel257.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel257.setText("Victim's Last Name:");
        createNewGD.add(jLabel257, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 130, 30));

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton15.setText("Back");
        createNewGD.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel258.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel258.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel258.setText("Victim's Location:");
        createNewGD.add(jLabel258, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 140, 30));

        GDidBox.setText("GD-");
        GDidBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GDidBoxActionPerformed(evt);
            }
        });
        createNewGD.add(GDidBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 200, 30));
        createNewGD.add(GDVictimLocationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 200, 30));
        createNewGD.add(GDVictimMobileBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 200, 30));

        jLabel259.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel259.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel259.setText("GD Victim ID:");
        createNewGD.add(jLabel259, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 160, 30));

        jLabel260.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel260.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel260.setText("Victim's Gender:");
        createNewGD.add(jLabel260, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 230, 30));

        jLabel261.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel261.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel261.setText("Victim's Age:");
        createNewGD.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 140, 30));
        createNewGD.add(GDVictimAgeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 200, 30));

        jLabel270.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel270.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel270.setText("GD Recieved Police Name:");
        createNewGD.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 230, 30));

        jLabel271.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel271.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel271.setText("GD Details:");
        createNewGD.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 140, 30));

        GDGenderBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        createNewGD.add(GDGenderBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 200, 30));

        GDDetailsBox.setColumns(20);
        GDDetailsBox.setRows(5);
        jScrollPane1.setViewportView(GDDetailsBox);

        createNewGD.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 200, 110));

        createNewGD.add(GDRecievePoliceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 200, 30));

        jLabel272.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel272.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel272.setText("GD Type:");
        createNewGD.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 230, 30));

        createNewGD.add(GDTypeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 200, 30));
        createNewGD.add(GDVictimLastNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 200, 30));

        jLabel338.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel338.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel338.setText("GD Status:");
        createNewGD.add(jLabel338, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 230, 30));

        GDStatusBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Solved", "Withdrawn" }));
        createNewGD.add(GDStatusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 200, 30));

        GDboardDynamic.add(createNewGD, "card2");

        createGDtype.setBackground(new java.awt.Color(252, 250, 246));
        createGDtype.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(252, 250, 246));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton44.setText("Reset");
        jPanel15.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton54.setText("Create");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel285.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel285.setText("GD Type ID:");
        jPanel15.add(jLabel285, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel15.add(GDTypeIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel286.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel286.setText("GD Type Name:");
        jPanel15.add(jLabel286, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel15.add(GDTypeNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel287.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel287.setText("Create New GD Type/Category");
        jPanel15.add(jLabel287, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        createGDtype.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel16.setBackground(new java.awt.Color(204, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deptTable2.setBackground(new java.awt.Color(252, 250, 246));
        deptTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane21.setViewportView(deptTable2);

        jPanel16.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel288.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel288.setText("GD Type/Category List:");
        jPanel16.add(jLabel288, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createGDtype.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel17.setBackground(new java.awt.Color(252, 250, 246));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton55.setText("Edit Departments");
        jPanel17.add(jButton55, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton56.setText("Find");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton56, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel289.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel289.setText("GD Type ID:");
        jPanel17.add(jLabel289, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 30));
        jPanel17.add(GDTypeIDSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel290.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel290.setText("GD Type Name:");
        jPanel17.add(jLabel290, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel17.add(GDNameSearcBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel17.add(editGDSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton57.setText("Edit");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel292.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel292.setText("GD Type ID:");
        jPanel17.add(jLabel292, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel293.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel293.setText("Edit GD Type");
        jPanel17.add(jLabel293, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        createGDtype.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        GDboardDynamic.add(createGDtype, "card3");

        GDstatus.setBackground(new java.awt.Color(252, 250, 246));
        GDstatus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GDclosed3.setBackground(new java.awt.Color(204, 204, 255));
        GDclosed3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GDStatusPendingTable.setBackground(new java.awt.Color(252, 250, 246));
        GDStatusPendingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane41.setViewportView(GDStatusPendingTable);

        GDclosed3.add(jScrollPane41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 340, 470));

        jLabel471.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel471.setText("GD Status Pending");
        GDclosed3.add(jLabel471, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 2, -1, 50));

        GDstatus.add(GDclosed3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 510));

        GDclosed2.setBackground(new java.awt.Color(255, 153, 153));
        GDclosed2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GDStatusWithdrwanTable.setBackground(new java.awt.Color(252, 250, 246));
        GDStatusWithdrwanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane40.setViewportView(GDStatusWithdrwanTable);

        GDclosed2.add(jScrollPane40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 320, 470));

        jLabel470.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel470.setText("GD Status Withdrwan");
        GDclosed2.add(jLabel470, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 50));

        GDstatus.add(GDclosed2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 320, 510));

        GDclosed.setBackground(new java.awt.Color(204, 255, 255));
        GDclosed.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GDStatusSolvedTable.setBackground(new java.awt.Color(252, 250, 246));
        GDStatusSolvedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane38.setViewportView(GDStatusSolvedTable);

        GDclosed.add(jScrollPane38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 340, 470));

        jLabel469.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel469.setText("GD Status Solved");
        GDclosed.add(jLabel469, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 50));

        GDstatus.add(GDclosed, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 340, 510));

        GDboardDynamic.add(GDstatus, "card3");

        editGD.setBackground(new java.awt.Color(252, 250, 246));
        editGD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Please Input GD Details:");
        editGD.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton16.setText("Find");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        editGD.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 90, 30));
        editGD.add(GDVictimFirstNameSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 200, 30));

        jLabel273.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel273.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel273.setText("Victim's First Name:");
        editGD.add(jLabel273, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 210, 30));

        jLabel274.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel274.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel274.setText("Victim's Mobile:");
        editGD.add(jLabel274, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 230, 30));

        jLabel275.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel275.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel275.setText("GD ID:");
        editGD.add(jLabel275, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 110, 30));
        editGD.add(GDVictimSearchIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 200, 30));

        jLabel276.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel276.setText("Victim's Last Name:");
        editGD.add(jLabel276, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 130, 30));

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setText("Back");
        editGD.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel277.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel277.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel277.setText("Victim's Location:");
        editGD.add(jLabel277, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 140, 30));

        GDSearchIDBox.setText("GD-");
        GDSearchIDBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GDSearchIDBoxActionPerformed(evt);
            }
        });
        editGD.add(GDSearchIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 200, 30));
        editGD.add(GDVictimLocationSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 200, 30));
        editGD.add(GDVictimMobileSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 200, 30));

        jLabel278.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel278.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel278.setText("GD Victim ID:");
        editGD.add(jLabel278, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 160, 30));

        jLabel279.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel279.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel279.setText("Victim's Gender:");
        editGD.add(jLabel279, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 230, 30));

        jLabel280.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel280.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel280.setText("Victim's Age:");
        editGD.add(jLabel280, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 140, 30));
        editGD.add(GDVictimAgeSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 200, 30));

        jLabel281.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel281.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel281.setText("GD Recieved Police Name:");
        editGD.add(jLabel281, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 230, 30));

        jLabel282.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel282.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel282.setText("GD Details:");
        editGD.add(jLabel282, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 140, 30));

        GDGenderSearchBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        editGD.add(GDGenderSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 200, 30));

        GDDetailsSearchBox.setColumns(20);
        GDDetailsSearchBox.setRows(5);
        jScrollPane2.setViewportView(GDDetailsSearchBox);

        editGD.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, 200, 110));

        editGD.add(GDRecievedPoliceIDSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 200, 30));

        jLabel283.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel283.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel283.setText("GD Type:");
        editGD.add(jLabel283, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 230, 30));

        editGD.add(GDTypeSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 200, 30));

        jLabel284.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel284.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel284.setText("Find Your GD By ID:");
        editGD.add(jLabel284, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 140, 30));

        GDSearchByIDBox.setText("GD-");
        GDSearchByIDBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GDSearchByIDBoxActionPerformed(evt);
            }
        });
        editGD.add(GDSearchByIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 200, 30));

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton18.setText("Update");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        editGD.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        editGD.add(GDVictimLastNameSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 200, 30));

        GDboardDynamic.add(editGD, "card2");

        javax.swing.GroupLayout GDboardLayout = new javax.swing.GroupLayout(GDboard);
        GDboard.setLayout(GDboardLayout);
        GDboardLayout.setHorizontalGroup(
            GDboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(GDboardDynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        GDboardLayout.setVerticalGroup(
            GDboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(GDboardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(GDboardDynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(GDboard, "card2");

        weaponBoard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane3.setBackground(new java.awt.Color(234, 238, 243));
        buttonPane3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adpolb2.setText("Add New Weapon");
        adpolb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpolb2ActionPerformed(evt);
            }
        });
        buttonPane3.add(adpolb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, 50));

        createrankb2.setText("Weapon Type");
        createrankb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createrankb2ActionPerformed(evt);
            }
        });
        buttonPane3.add(createrankb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 130, 50));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Police Information Update / Delete Or Modify");
        buttonPane3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        createdeptb1.setText("Ammos & Gun Stock");
        createdeptb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createdeptb1ActionPerformed(evt);
            }
        });
        buttonPane3.add(createdeptb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 130, 50));

        solvedcaseb3.setText("Demo");
        solvedcaseb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcaseb3ActionPerformed(evt);
            }
        });
        buttonPane3.add(solvedcaseb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 130, 50));

        editpolb2.setText("Edit Weapon");
        editpolb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpolb2ActionPerformed(evt);
            }
        });
        buttonPane3.add(editpolb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 130, 50));

        jButton49.setText("Demo ");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });
        buttonPane3.add(jButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 130, 50));

        weaponBoardDynamic.setBackground(new java.awt.Color(252, 250, 246));
        weaponBoardDynamic.setPreferredSize(new java.awt.Dimension(860, 492));
        weaponBoardDynamic.setLayout(new java.awt.CardLayout());

        wbhome.setBackground(new java.awt.Color(234, 238, 243));
        wbhome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("All The Weapon List:");
        wbhome.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane17.setBackground(new java.awt.Color(252, 250, 246));

        policeTable1.setBackground(new java.awt.Color(234, 238, 243));
        policeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane17.setViewportView(policeTable1);

        wbhome.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        weaponBoardDynamic.add(wbhome, "card5");

        createNewWeapon.setBackground(new java.awt.Color(252, 250, 246));
        createNewWeapon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("Please Input Weapon Details");
        createNewWeapon.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("Submit");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        createNewWeapon.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createNewWeapon.add(polFirstNameBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 200, 30));

        jLabel241.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel241.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel241.setText("Weapon Name:");
        createNewWeapon.add(jLabel241, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 110, 30));

        jLabel242.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel242.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel242.setText("Weapon Quantity (Put in Number):");
        createNewWeapon.add(jLabel242, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 230, 30));

        jLabel243.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel243.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel243.setText("Weapon ID:");
        createNewWeapon.add(jLabel243, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 110, 30));
        createNewWeapon.add(polLastNameBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 200, 30));

        jLabel248.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel248.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel248.setText("Weapon Serial");
        createNewWeapon.add(jLabel248, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 110, 30));

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("Back");
        createNewWeapon.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel253.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel253.setText("Weapon Issue Date:");
        createNewWeapon.add(jLabel253, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 140, 30));

        polIDBox1.setText("WP-");
        createNewWeapon.add(polIDBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 200, 30));
        createNewWeapon.add(polFatherNameBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 200, 30));
        createNewWeapon.add(polMailBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 200, 30));

        jLabel256.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel256.setText("Weapon Cat/Type:");
        createNewWeapon.add(jLabel256, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 140, 30));

        createNewWeapon.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 200, 30));

        weaponBoardDynamic.add(createNewWeapon, "card2");

        createWPCat.setBackground(new java.awt.Color(252, 250, 246));
        createWPCat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(252, 250, 246));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton43.setText("Edit Departments");
        jPanel12.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton50.setText("Create");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel262.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel262.setText("Category ID:");
        jPanel12.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel12.add(deptIDBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel263.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel263.setText("Category Name:");
        jPanel12.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel12.add(deptNameBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel264.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel264.setText("Create New Weapon Type/Category");
        jPanel12.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        createWPCat.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel13.setBackground(new java.awt.Color(204, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deptTable1.setBackground(new java.awt.Color(252, 250, 246));
        deptTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane19.setViewportView(deptTable1);

        jPanel13.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel265.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel265.setText("Weapon Category List:");
        jPanel13.add(jLabel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createWPCat.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel14.setBackground(new java.awt.Color(252, 250, 246));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton51.setText("Edit Departments");
        jPanel14.add(jButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton52.setText("Find");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel266.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel266.setText("Cat ID:");
        jPanel14.add(jLabel266, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel14.add(deptIDBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel267.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel267.setText("Category Name:");
        jPanel14.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel14.add(deptNameBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel14.add(editDeptSearchBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton53.setText("Edit");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel268.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel268.setText("Category ID:");
        jPanel14.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel269.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel269.setText("Edit Weapon Category");
        jPanel14.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        createWPCat.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        weaponBoardDynamic.add(createWPCat, "card3");

        weaponStock.setBackground(new java.awt.Color(252, 250, 246));
        weaponStock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel291.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel291.setText("The Total Weapon Stock:");
        weaponStock.add(jLabel291, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        weaponStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane20.setViewportView(weaponStockTable);

        weaponStock.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 410));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("250");
        weaponStock.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 100, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Total Guns:");
        weaponStock.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 100, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("250");
        weaponStock.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Total Ammos:");
        weaponStock.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 120, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Total Guards:");
        weaponStock.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 120, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("250");
        weaponStock.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, 100, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("250");
        weaponStock.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, 100, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Total Pistol:");
        weaponStock.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 120, -1));

        weaponBoardDynamic.add(weaponStock, "card5");

        editWeapon.setBackground(new java.awt.Color(252, 250, 246));
        editWeapon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("Edit Weapon Details:");
        editWeapon.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setText("Find");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        editWeapon.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 90, 30));
        editWeapon.add(polFirstNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 200, 30));

        jLabel244.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel244.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel244.setText("Weapon Name:");
        editWeapon.add(jLabel244, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 110, 30));

        jLabel245.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel245.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel245.setText("Weapon Quantity (Put in Number):");
        editWeapon.add(jLabel245, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 230, 30));

        jLabel246.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel246.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel246.setText("Weapon ID:");
        editWeapon.add(jLabel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 110, 30));
        editWeapon.add(polLastNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 200, 30));

        jLabel249.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel249.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel249.setText("Weapon Serial");
        editWeapon.add(jLabel249, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 110, 30));

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setText("Back");
        editWeapon.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 440, 150, 40));

        jLabel254.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel254.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel254.setText("Weapon Cat/Type:");
        editWeapon.add(jLabel254, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 140, 30));

        jLabel255.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel255.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel255.setText("Weapon Issue Date:");
        editWeapon.add(jLabel255, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 140, 30));

        polIDBox2.setText("WP-");
        editWeapon.add(polIDBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 200, 30));
        editWeapon.add(polFatherNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, 200, 30));
        editWeapon.add(polMailBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 200, 30));

        jLabel247.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel247.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel247.setText("Find Weapon By ID:");
        editWeapon.add(jLabel247, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 180, 30));

        polIDBox3.setText("WP-");
        editWeapon.add(polIDBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 200, 30));

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton13.setText("Submit");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        editWeapon.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 170, 40));

        editWeapon.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 200, 30));

        weaponBoardDynamic.add(editWeapon, "card2");

        javax.swing.GroupLayout weaponBoardLayout = new javax.swing.GroupLayout(weaponBoard);
        weaponBoard.setLayout(weaponBoardLayout);
        weaponBoardLayout.setHorizontalGroup(
            weaponBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(weaponBoardDynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        weaponBoardLayout.setVerticalGroup(
            weaponBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(weaponBoardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(weaponBoardDynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(weaponBoard, "card2");

        vehicleboard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane4.setBackground(new java.awt.Color(234, 238, 243));
        buttonPane4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adpolb3.setText("Add New Dump Item");
        adpolb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpolb3ActionPerformed(evt);
            }
        });
        buttonPane4.add(adpolb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, 50));

        createrankb3.setText("Dump Category");
        createrankb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createrankb3ActionPerformed(evt);
            }
        });
        buttonPane4.add(createrankb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 130, 50));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Police Information Update / Delete Or Modify");
        buttonPane4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        createdeptb2.setText("Dump Stock");
        createdeptb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createdeptb2ActionPerformed(evt);
            }
        });
        buttonPane4.add(createdeptb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 130, 50));

        solvedcaseb4.setText("Demo");
        solvedcaseb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcaseb4ActionPerformed(evt);
            }
        });
        buttonPane4.add(solvedcaseb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 130, 50));

        editpolb3.setText("Dump Edit");
        editpolb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpolb3ActionPerformed(evt);
            }
        });
        buttonPane4.add(editpolb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 130, 50));

        jButton58.setText("Demo ");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });
        buttonPane4.add(jButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 130, 50));

        vehicleboardDynamic.setBackground(new java.awt.Color(252, 250, 246));
        vehicleboardDynamic.setPreferredSize(new java.awt.Dimension(860, 492));
        vehicleboardDynamic.setLayout(new java.awt.CardLayout());

        vehicleHome.setBackground(new java.awt.Color(234, 238, 243));
        vehicleHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("All The Vehicle List:");
        vehicleHome.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane18.setBackground(new java.awt.Color(252, 250, 246));

        policeTable2.setBackground(new java.awt.Color(234, 238, 243));
        policeTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane18.setViewportView(policeTable2);

        vehicleHome.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        vehicleboardDynamic.add(vehicleHome, "card5");

        addNewVehicle.setBackground(new java.awt.Color(252, 250, 246));
        addNewVehicle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("Please Input Vehicle Details");
        addNewVehicle.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton19.setText("Submit");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        addNewVehicle.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        addNewVehicle.add(polFirstNameBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 200, 30));

        jLabel294.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel294.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel294.setText("Vehicle Name:");
        addNewVehicle.add(jLabel294, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 110, 30));

        jLabel295.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel295.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel295.setText("Vehicle Number:");
        addNewVehicle.add(jLabel295, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 230, 30));

        jLabel296.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel296.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel296.setText("Vehicle ID:");
        addNewVehicle.add(jLabel296, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 110, 30));
        addNewVehicle.add(polLastNameBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 200, 30));

        jLabel297.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel297.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel297.setText("Vehicle Model:");
        addNewVehicle.add(jLabel297, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 110, 30));

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton20.setText("Back");
        addNewVehicle.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel298.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel298.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel298.setText("Vehicle Driver:");
        addNewVehicle.add(jLabel298, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 140, 30));

        polIDBox7.setText("VCL-");
        polIDBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polIDBox7ActionPerformed(evt);
            }
        });
        addNewVehicle.add(polIDBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 200, 30));
        addNewVehicle.add(polFatherNameBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 200, 30));
        addNewVehicle.add(polMailBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 200, 30));

        jLabel299.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel299.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel299.setText("Vehicle Type:");
        addNewVehicle.add(jLabel299, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 140, 30));

        addNewVehicle.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 200, 30));

        jLabel316.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel316.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel316.setText("Vehicle Issue Date:");
        addNewVehicle.add(jLabel316, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 230, 30));
        addNewVehicle.add(polMailBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 200, 30));

        jLabel317.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel317.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel317.setText("Vehicle Gasoline:");
        addNewVehicle.add(jLabel317, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 140, 30));
        addNewVehicle.add(polFatherNameBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 200, 30));

        vehicleboardDynamic.add(addNewVehicle, "card2");

        createVHcat.setBackground(new java.awt.Color(252, 250, 246));
        createVHcat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(252, 250, 246));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton45.setText("Edit Departments");
        jPanel18.add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton59.setText("Create");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel300.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel300.setText("Vehicle Type ID:");
        jPanel18.add(jLabel300, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel18.add(deptIDBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel301.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel301.setText("Vehicle Type Name:");
        jPanel18.add(jLabel301, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel18.add(deptNameBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel302.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel302.setText("Create Vehicle Type/Category");
        jPanel18.add(jLabel302, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        createVHcat.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel19.setBackground(new java.awt.Color(204, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deptTable3.setBackground(new java.awt.Color(252, 250, 246));
        deptTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane22.setViewportView(deptTable3);

        jPanel19.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel303.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel303.setText("Vehicle Category List:");
        jPanel19.add(jLabel303, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createVHcat.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel20.setBackground(new java.awt.Color(252, 250, 246));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton60.setText("Edit Departments");
        jPanel20.add(jButton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton61.setText("Find");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton61, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel304.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel304.setText("Type ID:");
        jPanel20.add(jLabel304, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel20.add(deptIDBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel305.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel305.setText("Vehicle Type Name:");
        jPanel20.add(jLabel305, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel20.add(deptNameBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel20.add(editDeptSearchBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton62.setText("Edit");
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel306.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel306.setText("Vehicle Type ID:");
        jPanel20.add(jLabel306, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel307.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel307.setText("Edit Vehicle Category");
        jPanel20.add(jLabel307, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        createVHcat.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        vehicleboardDynamic.add(createVHcat, "card3");

        weaponStock1.setBackground(new java.awt.Color(252, 250, 246));
        weaponStock1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel308.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel308.setText("The Total Weapon Stock:");
        weaponStock1.add(jLabel308, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        weaponStockTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane23.setViewportView(weaponStockTable1);

        weaponStock1.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 410));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("250");
        weaponStock1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 100, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Total Guns:");
        weaponStock1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 100, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("250");
        weaponStock1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 100, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Total Ammos:");
        weaponStock1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 120, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Total Guards:");
        weaponStock1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 120, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("250");
        weaponStock1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, 100, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("250");
        weaponStock1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, 100, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Total Pistol:");
        weaponStock1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 120, -1));

        vehicleboardDynamic.add(weaponStock1, "card5");

        editVehicle.setBackground(new java.awt.Color(252, 250, 246));
        editVehicle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("Edit Vehicle Details:");
        editVehicle.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton21.setText("Find");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        editVehicle.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 90, 30));

        jButton22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton22.setText("Back");
        editVehicle.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 440, 150, 40));

        jLabel315.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel315.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel315.setText("Find Weapon By ID:");
        editVehicle.add(jLabel315, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 180, 30));

        polIDBox9.setText("VCL-");
        editVehicle.add(polIDBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 200, 30));

        jButton23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton23.setText("Submit");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        editVehicle.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 170, 40));

        jLabel318.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel318.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel318.setText("Vehicle ID:");
        editVehicle.add(jLabel318, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 110, 30));

        polIDBox10.setText("VCL-");
        polIDBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polIDBox10ActionPerformed(evt);
            }
        });
        editVehicle.add(polIDBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 200, 30));

        jLabel319.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel319.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel319.setText("Vehicle Type:");
        editVehicle.add(jLabel319, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 140, 30));

        editVehicle.add(jComboBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 200, 30));

        jLabel320.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel320.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel320.setText("Vehicle Name:");
        editVehicle.add(jLabel320, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 110, 30));
        editVehicle.add(polFirstNameBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 200, 30));

        jLabel321.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel321.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel321.setText("Vehicle Model:");
        editVehicle.add(jLabel321, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 110, 30));
        editVehicle.add(polLastNameBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 200, 30));

        jLabel322.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel322.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel322.setText("Vehicle Number:");
        editVehicle.add(jLabel322, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 230, 30));
        editVehicle.add(polMailBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 200, 30));

        jLabel323.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel323.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel323.setText("Vehicle Driver:");
        editVehicle.add(jLabel323, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 140, 30));
        editVehicle.add(polFatherNameBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 200, 30));

        jLabel324.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel324.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel324.setText("Vehicle Issue Date:");
        editVehicle.add(jLabel324, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 230, 30));
        editVehicle.add(polMailBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 200, 30));

        jLabel325.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel325.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel325.setText("Vehicle Gasoline:");
        editVehicle.add(jLabel325, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 140, 30));
        editVehicle.add(polFatherNameBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 200, 30));

        vehicleboardDynamic.add(editVehicle, "card2");

        javax.swing.GroupLayout vehicleboardLayout = new javax.swing.GroupLayout(vehicleboard);
        vehicleboard.setLayout(vehicleboardLayout);
        vehicleboardLayout.setHorizontalGroup(
            vehicleboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(vehicleboardDynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        vehicleboardLayout.setVerticalGroup(
            vehicleboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(vehicleboardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(vehicleboardDynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(vehicleboard, "card2");

        dumpboard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane5.setBackground(new java.awt.Color(234, 238, 243));
        buttonPane5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adpolb4.setText("Add New Dump Item");
        adpolb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpolb4ActionPerformed(evt);
            }
        });
        buttonPane5.add(adpolb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, 50));

        createrankb4.setText("Dump Type");
        createrankb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createrankb4ActionPerformed(evt);
            }
        });
        buttonPane5.add(createrankb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 130, 50));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Police Information Update / Delete Or Modify");
        buttonPane5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        createdeptb3.setText("Dump Details");
        createdeptb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createdeptb3ActionPerformed(evt);
            }
        });
        buttonPane5.add(createdeptb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 130, 50));

        solvedcaseb5.setText("Demo");
        solvedcaseb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcaseb5ActionPerformed(evt);
            }
        });
        buttonPane5.add(solvedcaseb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 130, 50));

        editpolb4.setText("Dump Edit");
        editpolb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpolb4ActionPerformed(evt);
            }
        });
        buttonPane5.add(editpolb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 130, 50));

        jButton63.setText("Demo ");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });
        buttonPane5.add(jButton63, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 130, 50));

        dumpDynamicBoard.setBackground(new java.awt.Color(252, 250, 246));
        dumpDynamicBoard.setPreferredSize(new java.awt.Dimension(860, 492));
        dumpDynamicBoard.setLayout(new java.awt.CardLayout());

        dumpHome.setBackground(new java.awt.Color(234, 238, 243));
        dumpHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("All The Dump List:");
        dumpHome.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane24.setBackground(new java.awt.Color(252, 250, 246));

        policeTable3.setBackground(new java.awt.Color(234, 238, 243));
        policeTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane24.setViewportView(policeTable3);

        dumpHome.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        dumpDynamicBoard.add(dumpHome, "card5");

        addNewDumpItem.setBackground(new java.awt.Color(252, 250, 246));
        addNewDumpItem.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setText("Please Input Vehicle Details");
        addNewDumpItem.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jButton24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton24.setText("Submit");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        addNewDumpItem.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        addNewDumpItem.add(polFirstNameBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 200, 30));

        jLabel309.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel309.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel309.setText("Item Name:");
        addNewDumpItem.add(jLabel309, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 110, 30));

        jLabel310.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel310.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel310.setText("Cease Date:");
        addNewDumpItem.add(jLabel310, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 30));

        jLabel311.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel311.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel311.setText("Dump Item ID:");
        addNewDumpItem.add(jLabel311, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 110, 30));
        addNewDumpItem.add(polLastNameBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 200, 30));

        jLabel312.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel312.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel312.setText("Serial (If Have):");
        addNewDumpItem.add(jLabel312, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 110, 30));

        jButton25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton25.setText("Back");
        addNewDumpItem.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel313.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel313.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel313.setText("Cease Location:");
        addNewDumpItem.add(jLabel313, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 140, 30));

        polIDBox8.setText("Dump-");
        polIDBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polIDBox8ActionPerformed(evt);
            }
        });
        addNewDumpItem.add(polIDBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 200, 30));
        addNewDumpItem.add(polFatherNameBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 200, 30));
        addNewDumpItem.add(polMailBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 200, 30));

        jLabel314.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel314.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel314.setText("Dump Type:");
        addNewDumpItem.add(jLabel314, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 140, 30));

        addNewDumpItem.add(jComboBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 200, 30));

        jLabel326.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel326.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel326.setText("Ceased Police:");
        addNewDumpItem.add(jLabel326, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 230, 30));

        jLabel327.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel327.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel327.setText("Owner Name:");
        addNewDumpItem.add(jLabel327, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 140, 30));
        addNewDumpItem.add(polFatherNameBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 200, 30));

        addNewDumpItem.add(jComboBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 200, 30));

        jLabel346.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel346.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel346.setText("Owner Mobile:");
        addNewDumpItem.add(jLabel346, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 230, 30));

        addNewDumpItem.add(jComboBox16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 200, 30));

        jLabel347.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel347.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel347.setText("Owner Location:");
        addNewDumpItem.add(jLabel347, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 140, 30));
        addNewDumpItem.add(polFatherNameBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 200, 30));

        jLabel348.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel348.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel348.setText("Dump Reason:");
        addNewDumpItem.add(jLabel348, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 230, 30));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        addNewDumpItem.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 580, 60));

        dumpDynamicBoard.add(addNewDumpItem, "card2");

        createDumpcat.setBackground(new java.awt.Color(252, 250, 246));
        createDumpcat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(252, 250, 246));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton46.setText("Edit Departments");
        jPanel21.add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton64.setText("Create");
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel328.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel328.setText("Vehicle Type ID:");
        jPanel21.add(jLabel328, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel21.add(deptIDBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel329.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel329.setText("Vehicle Type Name:");
        jPanel21.add(jLabel329, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel21.add(deptNameBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel330.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel330.setText("Create Vehicle Type/Category");
        jPanel21.add(jLabel330, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        createDumpcat.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel22.setBackground(new java.awt.Color(204, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deptTable4.setBackground(new java.awt.Color(252, 250, 246));
        deptTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane25.setViewportView(deptTable4);

        jPanel22.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel331.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel331.setText("Vehicle Category List:");
        jPanel22.add(jLabel331, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createDumpcat.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel23.setBackground(new java.awt.Color(252, 250, 246));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton65.setText("Edit Departments");
        jPanel23.add(jButton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton66.setText("Find");
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel332.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel332.setText("Type ID:");
        jPanel23.add(jLabel332, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel23.add(deptIDBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel333.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel333.setText("Vehicle Type Name:");
        jPanel23.add(jLabel333, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel23.add(deptNameBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel23.add(editDeptSearchBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton67.setText("Edit");
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton67, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel334.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel334.setText("Vehicle Type ID:");
        jPanel23.add(jLabel334, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel335.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel335.setText("Edit Vehicle Category");
        jPanel23.add(jLabel335, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        createDumpcat.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        dumpDynamicBoard.add(createDumpcat, "card3");

        dumpStock.setBackground(new java.awt.Color(252, 250, 246));
        dumpStock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel336.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel336.setText("The Total Weapon Stock:");
        dumpStock.add(jLabel336, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        weaponStockTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane26.setViewportView(weaponStockTable2);

        dumpStock.add(jScrollPane26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 410));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("250");
        dumpStock.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 100, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Total Guns:");
        dumpStock.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 100, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("250");
        dumpStock.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 100, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Total Ammos:");
        dumpStock.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 120, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Total Guards:");
        dumpStock.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 120, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("250");
        dumpStock.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, 100, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("250");
        dumpStock.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, 100, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Total Pistol:");
        dumpStock.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 120, -1));

        dumpDynamicBoard.add(dumpStock, "card5");

        editDump.setBackground(new java.awt.Color(252, 250, 246));
        editDump.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("Edit Dump Details:");
        editDump.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton26.setText("Find");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        editDump.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 90, 30));

        jButton27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton27.setText("Back");
        editDump.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 440, 150, 40));

        jLabel337.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel337.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel337.setText("Find Weapon By ID:");
        editDump.add(jLabel337, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 180, 30));

        polIDBox11.setText("Dump-");
        editDump.add(polIDBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 200, 30));

        jButton28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton28.setText("Submit");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        editDump.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 170, 40));

        jLabel349.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel349.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel349.setText("Dump Item ID:");
        editDump.add(jLabel349, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 110, 30));
        editDump.add(polLastNameBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 200, 30));
        editDump.add(polFirstNameBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 200, 30));
        editDump.add(polFatherNameBox16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 200, 30));

        jLabel350.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel350.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel350.setText("Cease Date:");
        editDump.add(jLabel350, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 240, 230, 30));
        editDump.add(polMailBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 200, 30));

        jLabel351.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel351.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel351.setText("Owner Mobile:");
        editDump.add(jLabel351, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 320, 230, 30));

        jLabel352.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel352.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel352.setText("Cease Location:");
        editDump.add(jLabel352, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 140, 30));

        jLabel353.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel353.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel353.setText("Serial (If Have):");
        editDump.add(jLabel353, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 110, 30));

        polIDBox13.setText("Dump-");
        polIDBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polIDBox13ActionPerformed(evt);
            }
        });
        editDump.add(polIDBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 200, 30));

        jLabel354.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel354.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel354.setText("Dump Reason:");
        editDump.add(jLabel354, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 360, 230, 30));
        editDump.add(polFatherNameBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 200, 30));

        editDump.add(jComboBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 200, 30));

        jLabel355.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel355.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel355.setText("Owner Name:");
        editDump.add(jLabel355, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 140, 30));

        jLabel356.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel356.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel356.setText("Owner Location:");
        editDump.add(jLabel356, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 140, 30));

        editDump.add(jComboBox18, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 200, 30));

        jLabel357.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel357.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel357.setText("Ceased Police:");
        editDump.add(jLabel357, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 280, 230, 30));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        editDump.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 580, 60));

        editDump.add(jComboBox19, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 200, 30));

        jLabel358.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel358.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel358.setText("Item Name:");
        editDump.add(jLabel358, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 110, 30));
        editDump.add(polFatherNameBox18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 200, 30));

        jLabel359.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel359.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel359.setText("Dump Type:");
        editDump.add(jLabel359, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 140, 30));

        dumpDynamicBoard.add(editDump, "card2");

        javax.swing.GroupLayout dumpboardLayout = new javax.swing.GroupLayout(dumpboard);
        dumpboard.setLayout(dumpboardLayout);
        dumpboardLayout.setHorizontalGroup(
            dumpboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dumpDynamicBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dumpboardLayout.setVerticalGroup(
            dumpboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(dumpboardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(dumpDynamicBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(dumpboard, "card2");

        jPanel1.add(menuitem, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 1000, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        setState(ICONIFIED);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void dash5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dash5MouseExited

    private void dash5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dash5MouseEntered

    private void dash5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dash5MouseClicked

    private void dash6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dash6MouseExited

    private void dash6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dash6MouseEntered

    private void dash6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash6MouseClicked
        // TODO add your handling code here:
        weaponBoard.setVisible(false);
        dumpboard.setVisible(false);
        GDboard.setVisible(false);
        vehicleboard.setVisible(true);
    }//GEN-LAST:event_dash6MouseClicked

    private void policeInfo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_policeInfo3MouseClicked
        // TODO add your handling code here:
        
        weaponBoard.setVisible(false);
        vehicleboard.setVisible(false);
        dumpboard.setVisible(false);
        GDboard.setVisible(true);
        
        
        
        
        
        
        
    }//GEN-LAST:event_policeInfo3MouseClicked

    private void dash7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash7MouseClicked
        // TODO add your handling code here:

        vehicleboard.setVisible(false);
        dumpboard.setVisible(false);
        GDboard.setVisible(false);
        weaponBoard.setVisible(true);
        
    }//GEN-LAST:event_dash7MouseClicked

    private void dash7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dash7MouseEntered

    private void dash7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dash7MouseExited

    private void adpolb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpolb2ActionPerformed
        // TODO add your handling code here:
        
        wbhome.setVisible(false);
        createWPCat.setVisible(false);
        weaponStock.setVisible(false);
        editWeapon.setVisible(false);
        createNewWeapon.setVisible(true);

        
    }//GEN-LAST:event_adpolb2ActionPerformed

    private void createrankb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createrankb2ActionPerformed
        // TODO add your handling code here:
                
        wbhome.setVisible(false);
        weaponStock.setVisible(false);
        editWeapon.setVisible(false);
        createNewWeapon.setVisible(false);
        createWPCat.setVisible(true);
        
        
        
        
    }//GEN-LAST:event_createrankb2ActionPerformed

    private void createdeptb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createdeptb1ActionPerformed
        // TODO add your handling code here:
        
        wbhome.setVisible(false);
        editWeapon.setVisible(false);
        createNewWeapon.setVisible(false);
        createWPCat.setVisible(false);
        weaponStock.setVisible(true);
        
        
    }//GEN-LAST:event_createdeptb1ActionPerformed

    private void solvedcaseb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcaseb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solvedcaseb3ActionPerformed

    private void editpolb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpolb2ActionPerformed
        // TODO add your handling code here:
        
        wbhome.setVisible(false);
        createNewWeapon.setVisible(false);
        createWPCat.setVisible(false);
        weaponStock.setVisible(false);
        editWeapon.setVisible(true);
    }//GEN-LAST:event_editpolb2ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton53ActionPerformed

    private void adpolb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpolb6ActionPerformed
        // TODO add your handling code here:
     
        GDhome.setVisible(false);
        createGDtype.setVisible(false);
        GDstatus.setVisible(false);
        editGD.setVisible(false);
        createNewGD.setVisible(true);
    }//GEN-LAST:event_adpolb6ActionPerformed

    private void createrankb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createrankb6ActionPerformed
        // TODO add your handling code here:
         GDhome.setVisible(false);
        GDstatus.setVisible(false);
        editGD.setVisible(false);
        createNewGD.setVisible(false);
        createGDtype.setVisible(true);
    }//GEN-LAST:event_createrankb6ActionPerformed

    private void createdeptb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createdeptb5ActionPerformed
        // TODO add your handling code here:
        GDhome.setVisible(false);
        editGD.setVisible(false);
        createNewGD.setVisible(false);
        createGDtype.setVisible(false);
        GDstatus.setVisible(true);
        
    }//GEN-LAST:event_createdeptb5ActionPerformed

    private void solvedcaseb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcaseb7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solvedcaseb7ActionPerformed

    private void editpolb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpolb6ActionPerformed
        // TODO add your handling code here:
        
        GDhome.setVisible(false);
        createNewGD.setVisible(false);
        createGDtype.setVisible(false);
        GDstatus.setVisible(false);
        editGD.setVisible(true);
        
        
    }//GEN-LAST:event_editpolb6ActionPerformed

    private void jButton95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton95ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton95ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        createGD();
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void GDidBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GDidBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GDidBoxActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void GDSearchIDBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GDSearchIDBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GDSearchIDBoxActionPerformed

    private void GDSearchByIDBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GDSearchByIDBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GDSearchByIDBoxActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed

        createGDCat();

// TODO add your handling code here:
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
        GDCatGetEditVal();
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        // TODO add your handling code here:
        
        GDCatUpdateVal();

    }//GEN-LAST:event_jButton57ActionPerformed

    private void adpolb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpolb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adpolb3ActionPerformed

    private void createrankb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createrankb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createrankb3ActionPerformed

    private void createdeptb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createdeptb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createdeptb2ActionPerformed

    private void solvedcaseb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcaseb4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solvedcaseb4ActionPerformed

    private void editpolb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpolb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editpolb3ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton62ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void polIDBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polIDBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polIDBox7ActionPerformed

    private void polIDBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polIDBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polIDBox10ActionPerformed

    private void adpolb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpolb4ActionPerformed
        // TODO add your handling code here:


        
        
    }//GEN-LAST:event_adpolb4ActionPerformed

    private void createrankb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createrankb4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createrankb4ActionPerformed

    private void createdeptb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createdeptb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createdeptb3ActionPerformed

    private void solvedcaseb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcaseb5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solvedcaseb5ActionPerformed

    private void editpolb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpolb4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editpolb4ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void polIDBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polIDBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polIDBox8ActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void polIDBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polIDBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polIDBox13ActionPerformed

    private void dash8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash8MouseClicked
        // TODO add your handling code here:
        
        vehicleboard.setVisible(false);
        GDboard.setVisible(false);
        weaponBoard.setVisible(true);
        dumpboard.setVisible(true);
        
    }//GEN-LAST:event_dash8MouseClicked

    private void dash8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dash8MouseEntered

    private void dash8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dash8MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashBoard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashBoard2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea GDDetailsBox;
    private javax.swing.JTextArea GDDetailsSearchBox;
    private javax.swing.JComboBox GDGenderBox;
    private javax.swing.JComboBox GDGenderSearchBox;
    private javax.swing.JTextField GDNameSearcBox;
    private javax.swing.JComboBox GDRecievePoliceBox;
    private javax.swing.JComboBox GDRecievedPoliceIDSearchBox;
    private javax.swing.JTextField GDSearchByIDBox;
    private javax.swing.JTextField GDSearchIDBox;
    private javax.swing.JComboBox GDStatusBox;
    private javax.swing.JTable GDStatusPendingTable;
    private javax.swing.JTable GDStatusSolvedTable;
    private javax.swing.JTable GDStatusWithdrwanTable;
    private javax.swing.JComboBox GDTypeBox;
    private javax.swing.JTextField GDTypeIDBox;
    private javax.swing.JTextField GDTypeIDSearchBox;
    private javax.swing.JTextField GDTypeNameBox;
    private javax.swing.JComboBox GDTypeSearchBox;
    private javax.swing.JTextField GDVictimAgeBox;
    private javax.swing.JTextField GDVictimAgeSearchBox;
    private javax.swing.JTextField GDVictimFirstNameBox;
    private javax.swing.JTextField GDVictimFirstNameSearchBox;
    private javax.swing.JTextField GDVictimIDBox;
    private javax.swing.JTextField GDVictimLastNameBox;
    private javax.swing.JTextField GDVictimLastNameSearchBox;
    private javax.swing.JTextField GDVictimLocationBox;
    private javax.swing.JTextField GDVictimLocationSearchBox;
    private javax.swing.JTextField GDVictimMobileBox;
    private javax.swing.JTextField GDVictimMobileSearchBox;
    private javax.swing.JTextField GDVictimSearchIDBox;
    private javax.swing.JPanel GDboard;
    private javax.swing.JPanel GDboardDynamic;
    private javax.swing.JPanel GDclosed;
    private javax.swing.JPanel GDclosed2;
    private javax.swing.JPanel GDclosed3;
    private javax.swing.JPanel GDhome;
    private javax.swing.JTextField GDidBox;
    private javax.swing.JPanel GDstatus;
    private javax.swing.JLabel activity1;
    private javax.swing.JLabel addNewAdmin1;
    private javax.swing.JPanel addNewDumpItem;
    private javax.swing.JPanel addNewVehicle;
    private javax.swing.JButton adpolb2;
    private javax.swing.JButton adpolb3;
    private javax.swing.JButton adpolb4;
    private javax.swing.JButton adpolb6;
    private javax.swing.JPanel buttonPane3;
    private javax.swing.JPanel buttonPane4;
    private javax.swing.JPanel buttonPane5;
    private javax.swing.JPanel buttonPane7;
    private javax.swing.JLabel comInfo;
    private javax.swing.JPanel createDumpcat;
    private javax.swing.JPanel createGDtype;
    private javax.swing.JPanel createNewGD;
    private javax.swing.JPanel createNewWeapon;
    private javax.swing.JPanel createVHcat;
    private javax.swing.JPanel createWPCat;
    private javax.swing.JButton createdeptb1;
    private javax.swing.JButton createdeptb2;
    private javax.swing.JButton createdeptb3;
    private javax.swing.JButton createdeptb5;
    private javax.swing.JButton createrankb2;
    private javax.swing.JButton createrankb3;
    private javax.swing.JButton createrankb4;
    private javax.swing.JButton createrankb6;
    private javax.swing.JLabel dash5;
    private javax.swing.JLabel dash6;
    private javax.swing.JLabel dash7;
    private javax.swing.JLabel dash8;
    private javax.swing.JTextField deptIDBox1;
    private javax.swing.JTextField deptIDBox3;
    private javax.swing.JTextField deptIDBox4;
    private javax.swing.JTextField deptIDBox6;
    private javax.swing.JTextField deptIDBox8;
    private javax.swing.JTextField deptIDBox9;
    private javax.swing.JTextField deptNameBox1;
    private javax.swing.JTextField deptNameBox3;
    private javax.swing.JTextField deptNameBox4;
    private javax.swing.JTextField deptNameBox6;
    private javax.swing.JTextField deptNameBox8;
    private javax.swing.JTextField deptNameBox9;
    private javax.swing.JTable deptTable1;
    private javax.swing.JTable deptTable2;
    private javax.swing.JTable deptTable3;
    private javax.swing.JTable deptTable4;
    private javax.swing.JPanel dumpDynamicBoard;
    private javax.swing.JPanel dumpHome;
    private javax.swing.JPanel dumpStock;
    private javax.swing.JPanel dumpboard;
    private javax.swing.JTextField editDeptSearchBar1;
    private javax.swing.JTextField editDeptSearchBar3;
    private javax.swing.JTextField editDeptSearchBar4;
    private javax.swing.JPanel editDump;
    private javax.swing.JPanel editGD;
    private javax.swing.JTextField editGDSearchBox;
    private javax.swing.JLabel editProfile1;
    private javax.swing.JPanel editVehicle;
    private javax.swing.JPanel editWeapon;
    private javax.swing.JButton editpolb2;
    private javax.swing.JButton editpolb3;
    private javax.swing.JButton editpolb4;
    private javax.swing.JButton editpolb6;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton95;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox16;
    private javax.swing.JComboBox jComboBox17;
    private javax.swing.JComboBox jComboBox18;
    private javax.swing.JComboBox jComboBox19;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel245;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel247;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel255;
    private javax.swing.JLabel jLabel256;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel266;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
    private javax.swing.JLabel jLabel273;
    private javax.swing.JLabel jLabel274;
    private javax.swing.JLabel jLabel275;
    private javax.swing.JLabel jLabel276;
    private javax.swing.JLabel jLabel277;
    private javax.swing.JLabel jLabel278;
    private javax.swing.JLabel jLabel279;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel280;
    private javax.swing.JLabel jLabel281;
    private javax.swing.JLabel jLabel282;
    private javax.swing.JLabel jLabel283;
    private javax.swing.JLabel jLabel284;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel286;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel288;
    private javax.swing.JLabel jLabel289;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel290;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel292;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel295;
    private javax.swing.JLabel jLabel296;
    private javax.swing.JLabel jLabel297;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel299;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel301;
    private javax.swing.JLabel jLabel302;
    private javax.swing.JLabel jLabel303;
    private javax.swing.JLabel jLabel304;
    private javax.swing.JLabel jLabel305;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel310;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel312;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JLabel jLabel314;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel317;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel331;
    private javax.swing.JLabel jLabel332;
    private javax.swing.JLabel jLabel333;
    private javax.swing.JLabel jLabel334;
    private javax.swing.JLabel jLabel335;
    private javax.swing.JLabel jLabel336;
    private javax.swing.JLabel jLabel337;
    private javax.swing.JLabel jLabel338;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel346;
    private javax.swing.JLabel jLabel347;
    private javax.swing.JLabel jLabel348;
    private javax.swing.JLabel jLabel349;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JLabel jLabel351;
    private javax.swing.JLabel jLabel352;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel357;
    private javax.swing.JLabel jLabel358;
    private javax.swing.JLabel jLabel359;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel469;
    private javax.swing.JLabel jLabel470;
    private javax.swing.JLabel jLabel471;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JLabel logOut1;
    private javax.swing.JPanel menubox;
    private javax.swing.JPanel menuitem;
    private javax.swing.JLabel personalInfo1;
    private javax.swing.JTextField polFatherNameBox1;
    private javax.swing.JTextField polFatherNameBox10;
    private javax.swing.JTextField polFatherNameBox11;
    private javax.swing.JTextField polFatherNameBox12;
    private javax.swing.JTextField polFatherNameBox15;
    private javax.swing.JTextField polFatherNameBox16;
    private javax.swing.JTextField polFatherNameBox17;
    private javax.swing.JTextField polFatherNameBox18;
    private javax.swing.JTextField polFatherNameBox2;
    private javax.swing.JTextField polFatherNameBox7;
    private javax.swing.JTextField polFatherNameBox8;
    private javax.swing.JTextField polFatherNameBox9;
    private javax.swing.JTextField polFirstNameBox1;
    private javax.swing.JTextField polFirstNameBox2;
    private javax.swing.JTextField polFirstNameBox5;
    private javax.swing.JTextField polFirstNameBox6;
    private javax.swing.JTextField polFirstNameBox7;
    private javax.swing.JTextField polFirstNameBox9;
    private javax.swing.JTextField polIDBox1;
    private javax.swing.JTextField polIDBox10;
    private javax.swing.JTextField polIDBox11;
    private javax.swing.JTextField polIDBox13;
    private javax.swing.JTextField polIDBox2;
    private javax.swing.JTextField polIDBox3;
    private javax.swing.JTextField polIDBox7;
    private javax.swing.JTextField polIDBox8;
    private javax.swing.JTextField polIDBox9;
    private javax.swing.JTextField polLastNameBox1;
    private javax.swing.JTextField polLastNameBox2;
    private javax.swing.JTextField polLastNameBox5;
    private javax.swing.JTextField polLastNameBox6;
    private javax.swing.JTextField polLastNameBox7;
    private javax.swing.JTextField polLastNameBox9;
    private javax.swing.JTextField polMailBox1;
    private javax.swing.JTextField polMailBox10;
    private javax.swing.JTextField polMailBox2;
    private javax.swing.JTextField polMailBox5;
    private javax.swing.JTextField polMailBox6;
    private javax.swing.JTextField polMailBox7;
    private javax.swing.JTextField polMailBox8;
    private javax.swing.JTextField polMailBox9;
    private javax.swing.JLabel policeInfo3;
    private javax.swing.JTable policeTable1;
    private javax.swing.JTable policeTable2;
    private javax.swing.JTable policeTable3;
    private javax.swing.JTable policeTable5;
    private javax.swing.JButton solvedcaseb3;
    private javax.swing.JButton solvedcaseb4;
    private javax.swing.JButton solvedcaseb5;
    private javax.swing.JButton solvedcaseb7;
    private javax.swing.JPanel titlebar;
    private javax.swing.JLabel userName19;
    private javax.swing.JLabel userName20;
    private javax.swing.JLabel userName21;
    private javax.swing.JPanel vehicleHome;
    private javax.swing.JPanel vehicleboard;
    private javax.swing.JPanel vehicleboardDynamic;
    private javax.swing.JPanel wbhome;
    private javax.swing.JPanel weaponBoard;
    private javax.swing.JPanel weaponBoardDynamic;
    private javax.swing.JPanel weaponStock;
    private javax.swing.JPanel weaponStock1;
    private javax.swing.JTable weaponStockTable;
    private javax.swing.JTable weaponStockTable1;
    private javax.swing.JTable weaponStockTable2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void rankCreate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deptCreate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editDept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createPolice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCriminal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void caseReset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createVictim() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCourt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCaseTrialDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   

  

   
   

   

   

   
   

    

}

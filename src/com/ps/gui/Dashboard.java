/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ps.gui;

import com.ps.common.IcommonGUI;
import com.ps.dao.CaseDao;
import com.ps.dao.CaseTrialDAO;
import com.ps.dao.CourtDAO;
import com.ps.dao.CriminalDAO;
import com.ps.dao.DepartmentDao;
import com.ps.dao.VictimDAO;
import com.ps.dao.policeDao;
import com.ps.dao.rankDAO;
import com.ps.model.CaseModel;
import com.ps.model.CaseTrialModel;
import com.ps.model.CourtModel;
import com.ps.model.VictimModel;
import com.ps.model.criminalModel;
import com.ps.model.departmentModel;
import com.ps.model.policeModel;
import com.ps.model.rankModel;
import java.awt.HeadlessException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Minhajul Islam
 */
public class Dashboard extends javax.swing.JFrame implements IcommonGUI {

    //dao list
    rankDAO rankDao;
    DepartmentDao departmentDao;
    policeDao policeDao;
    CriminalDAO crimDAO;
    CaseDao caseDao;
    VictimDAO victimDao;
    CourtDAO courtDao;
    CaseTrialDAO casetmDao;
    
    

    //model list
    rankModel Rankmodel;
    departmentModel DepartmentModel;
    policeModel Policemodel;
    criminalModel CriminalModel;
    CaseModel caseModel;
    VictimModel victimModel;
    CourtModel courtModel;
    CaseTrialModel casetm;

    //Tables
    DefaultTableModel dftm;

    
    public Dashboard() {
        initComponents();
        setLocationRelativeTo(null);
        
        
        

        ///////table mechanism
        
        caseDao = new CaseDao();
        caseDao.showCaseTable(caseAssignedPolice);
        caseDao.showPendingCase(pendingCaseTable);
        
        departmentDao = new DepartmentDao();
        departmentDao.showDeptTable(deptTable);
        
        courtDao = new CourtDAO();
        courtDao.showCourtTable(courtTable);



        rankDao = new rankDAO();
        rankDao.showRankTable(rankTable);

        policeDao = new policeDao();
        policeDao.showPoliceTable(policeTable);
        policeDao.showOfficerList(countPolice);
        
        
        
        casetmDao = new CaseTrialDAO();
        casetmDao.showCaseTrialTable(caseTrialTable);
        
        
//       List<CaseModel> caseIDL = getCaseID();
//        for (int i = 0; i < caseIDL.size(); i++) {
//            getCaseIDBox.addItem(caseIDL.get(i).getCase_id());
////            polDeptSel.addItem(rankL.get(i).getRank_id());
//        }
        
         List<CaseModel> caseL = getCaseIDList();
        for (int i = 0; i < caseL.size(); i++) {
            getCaseIDBox.addItem(caseL.get(i).getCase_id());
//            polDeptSel.addItem(rankL.get(i).getRank_id());
        }
        
        
        
         List<CourtModel> courtL = getCourt();
        for (int i = 0; i < courtL.size(); i++) {
            getCourListbox.addItem(courtL.get(i).getCourt_name());
//            polDeptSel.addItem(rankL.get(i).getRank_id());
        }
        
        
        
        
        
        List<rankModel> rankL = getRank();
        for (int i = 0; i < rankL.size(); i++) {
            polDeptSel.addItem(rankL.get(i).getRank_name());
//            polDeptSel.addItem(rankL.get(i).getRank_id());
        }
        
        
       
        

        List<departmentModel> deptL = getDept();
        for (int i = 0; i < deptL.size(); i++) {
            polRankItem.addItem(deptL.get(i).getDepartment_name());
            System.out.println(deptL.get(i).getDepartment_name());

        }
        new CaseDao().getAll();
        

    }

    
    //===========================Case Trial Interface============================
    
     @Override
    public void createCaseTrialDate() {
        casetm = new CaseTrialModel();
        casetmDao = new CaseTrialDAO();
        
        casetm.setCase_id(getCaseIDBox.getSelectedItem().toString());
        casetm.setCourt_name(getCourListbox.getSelectedItem().toString());
        casetm.setCourt_facing_date(courtFacingDateChoosed.getDate().toString());
        
        int status = casetmDao.create(casetm);
        
        if (status > 0) {
            JOptionPane.showMessageDialog(null, "New Case Trial Added!");
        } else {
            JOptionPane.showMessageDialog(null, "Case Trial is not Added!");
        }
        
        
        
        
        
        
        
    }
    
    public void getCourseInformation(){
        casetm = new CaseTrialModel();
        casetmDao = new CaseTrialDAO();
        
        
        String trialCaseIDFInd = trialCaseIDFindBox.getText();
        casetm.setCase_id(trialCaseIDFInd);
        List<CaseTrialModel> list = casetmDao.findHearingByCaseID(casetm);
        
        
        TrialCaseList t = new TrialCaseList();
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.sendData(list);
        t.setVisible(true);

    }
    
    
    
    //===========================Court Interface============================
    
     @Override
    public void createCourt() {
        courtDao = new CourtDAO();
        courtModel = new CourtModel();
        
        
        courtModel.setCourt_id(courtIDBox.getText());
        courtModel.setCourt_name(courtNameBox.getText());
        courtModel.setCourt_location(courtLocationBox.getText());
        
        int status = courtDao.create(courtModel);
        
        if (status > 0) {
            JOptionPane.showMessageDialog(null, "Court Added!");
        } else {
            JOptionPane.showMessageDialog(null, "Court is not Added!");
        }
        
        
        
        
        

    }
    
        List<CourtModel> getCourt() {
            courtDao = new CourtDAO();
            return courtDao.getAll();

        }
    
    //===========================Victim Interface============================
    
    @Override
    public void createVictim() {
        victimDao = new VictimDAO();
        victimModel = new VictimModel();
        
        
        victimModel.setVictim_id(victimIDBox.getText());
        caseVictimIDBox.setText(victimIDBox.getText());
        
        victimModel.setCriminal_id(victimCriminalIDBox.getText());
        victimModel.setVictim_first_name(victimFirstNameBox.getText());
        victimModel.setVictim_last_name(victimLastNameBox.getText());
        
        victimModel.setCase_id(victimCaseIDBox.getText());
        caseIDBox.setText(victimCaseIDBox.getText());
        
        String victimGender = null;
        if(victimGenderBox.getSelectedItem().equals("Male")){
            victimGender = "Male";
        }else{
            victimGender = "Female";
        }
 
        victimModel.setVictim_gender(victimGender);
        victimModel.setVictim_father_name(VictimFatherNameBox.getText());
        victimModel.setVictim_mother_name(VictimMotherNameBox.getText());
        victimModel.setVictim_phone(victimPhoneBox.getText());
        victimModel.setVictim_age(victimAgeBox.getText());

        String isVictimFiledGD = null;
        if(victimGDInfoBox.getSelectedItem().equals("Yes")){
            isVictimFiledGD = "Yes";
        }else{
            isVictimFiledGD = "No";
        }
        victimModel.setVictim_gd_yes_or_no(isVictimFiledGD);
        
        victimModel.setVictim_gd_id(victimGDIDBox.getText());
        victimModel.setVictim_permanentAd(victimPresentAdBox.getText());
        victimModel.setVictim_permanentAd(victimPermanentAdBox.getText());
        
        
       int victimCreateStatus =  victimDao.create(victimModel);
        if (victimCreateStatus > 0) {
            JOptionPane.showMessageDialog(null, "Victim  Added!");
            
        } else {
            JOptionPane.showMessageDialog(null, "Victim is not Added!");
        }
        
        
        
        
        
        
        
        
    }
    
    
    
    
   
    //===========================Rank Interface============================
    
     
    
    @Override
    public void rankCreate() {
        rankDao = new rankDAO();
        Rankmodel = new rankModel();

        String rankIDBox = rankID.getText();
        String rNameBox = rankNameBox.getText();
//         System.out.println(rankIDBox);
//         System.out.println(rNameBox);

        Rankmodel.setRank_id(rankIDBox);
        System.out.println("inperr1");

        Rankmodel.setRank_name(rNameBox);

        int status = rankDao.create(Rankmodel);

        if (status > 0) {
            JOptionPane.showMessageDialog(null, "Rank Added!");
        } else {
            JOptionPane.showMessageDialog(null, "Rank is not Added!");
        }

    }
    List<rankModel> getRank() {
        rankDao = new rankDAO();
        return rankDao.getAll();

    }
    

        //===========================department Interface============================
    List<departmentModel> getDept() {
        departmentDao = new DepartmentDao();
        return departmentDao.getAll();
    }
     @Override
    public void deptCreate() {
        departmentDao = new DepartmentDao();
        DepartmentModel = new departmentModel();

        String deptIDInp = deptIDBox.getText();
        String deptNameInp = deptNameBox.getText();

//         System.out.println(rankIDBox);
//         System.out.println(rNameBox);
        DepartmentModel.setDepartment_id(deptIDInp);
        DepartmentModel.setDepartment_name(deptNameInp);

        int status = departmentDao.create(DepartmentModel);

        if (status > 0) {
            JOptionPane.showMessageDialog(null, "Department Added!");
                    departmentDao = new DepartmentDao();
                    departmentDao.showDeptTable(deptTable);
        } else {
            JOptionPane.showMessageDialog(null, "Department is not Added!");
        }

    }

    @Override
    public void editDept() {
        departmentDao = new DepartmentDao();
        DepartmentModel = new departmentModel();
        String editDeptSearch = editDeptSearchBar.getText();
        DepartmentModel.setDepartment_id(editDeptSearch);

        departmentModel dm = departmentDao.getByID(DepartmentModel);
        System.err.println(dm.getDepartment_name());
    }
    
     @Override
    public void showTable() {

        departmentDao = new DepartmentDao();
        departmentDao.showDeptTable(deptTable);

    }
    
    //===========================Criminal Interface============================
    @Override
    public void createCriminal() {
        crimDAO = new CriminalDAO();
        CriminalModel = new criminalModel();
        

        
        String accusedID = accusedIDBox.getText();
        victimCriminalIDBox.setText(accusedID);
        caseAccusedIDBox.setText(accusedID);
        String accusedcaseID = accusedCaseIDBox.getText();
        victimCaseIDBox.setText(accusedcaseID);
        caseIDBox.setText(accusedcaseID);
        String accusedFirstName = accusedFirstNameBox.getText();
        String accusedLastName = accusedLastNameBox.getText();
        String accusedFather = accusedFatherNameBox.getText();
        String accusedMother = accusedMotherNameBox.getText();
        String accusedGender = null;
        if (accusedGenderBox.getSelectedItem().equals("Male")) {
            accusedGender = "Male";
        } else if (accusedGenderBox.getSelectedItem().equals("Female")) {
            accusedGender = "Female";
        }

        String accusedAge = accusedAgeBox.getText();
        String accusedArrestingSituation = null;
        if (accusedArrestSituationBox.getSelectedItem().equals("Arrested")) {
            accusedArrestingSituation = "Arrested";

        } else if (accusedArrestSituationBox.getSelectedItem().equals("Not Arrested")) {
            accusedArrestingSituation = "Not Arrested";
        } else {
            accusedArrestingSituation = "Flew Away";
        }
        String accusedArrestingTime = accusedArrestingTimeBox.getText();
        String accusedPrisonSituation = null;
        if (accusedIsPrisonedBox.getSelectedItem().equals("Yes")) {
            accusedPrisonSituation = "Yes";
        } else {
            accusedPrisonSituation = "No";
        }

        String accusedPreviousCase = accusedPreviousCaseBox.getText();
        String accusedPresentAd = accusedPresentAddressBox.getText();
        String accusedParmanentAd = accusedPermanentAddressBox.getText();
        String accusedMobileNumber = accusedMobileNoBOx.getText();
        String accusedRemarks = accusedRemarksBox.getText();

        CriminalModel.setCriminal_id(accusedID);
        CriminalModel.setCase_ID(accusedcaseID);
        CriminalModel.setCriminal_first_name(accusedFirstName);
        CriminalModel.setCriminal_last_name(accusedLastName);
        CriminalModel.setCriminal_father_name(accusedFather);
        CriminalModel.setCriminal_mother_name(accusedMother);
        CriminalModel.setCriminal_gender(accusedGender);
        CriminalModel.setCriminal_age(accusedAge);
        CriminalModel.setArrested_or_not(accusedArrestingSituation);
        CriminalModel.setArresting_time(accusedArrestingTime);
        //Need To add prison information from database
        CriminalModel.setPrevious_casees(accusedPreviousCase);
        CriminalModel.setCriminal_location(accusedPresentAd);
        CriminalModel.setCriminalp_location(accusedParmanentAd);
        CriminalModel.setCriminal_mobile(accusedMobileNumber);
        CriminalModel.setCriminal_remarks(accusedRemarks);

        int accusedAddStatus = crimDAO.create(CriminalModel);

        if (accusedAddStatus > 0) {
            JOptionPane.showMessageDialog(null, "Criminal Added!");
        } else {
            JOptionPane.showMessageDialog(null, "Criminal is not Added!");
        }

    }
    
    
    
    //===========================Case Interface============================
    @Override
    public void createCase() {
        
        
        caseDao = new CaseDao();
        caseModel = new CaseModel();

        String caseIDbox = caseIDBox.getText();
        String caseAccusedIDbox = caseAccusedIDBox.getText();
        String caseVictimIDbox = caseVictimIDBox.getText();
        String casePoliceIDbox = casePoliceIDBox.getText();
        String caseCrimeTypebox = caseCrimeTypeBox.getText();
  
//        String caseOpenDatebox = caseOpeningDateBox.getText();
        
        String caseOffenceTimebox = caseOffenceTimeBox.getText();
        String caseOffenceLocationbox = caseOffenceLocationBox.getText();
        String caseWitnessNamebox = caseWitnnessNameBox.getText();
        String caseWitnessLocationbox = caseWitnessLocationBox.getText();
        String caseWitnessMobile = caseWitnessMobileBox.getText();
        String caseEvidencebox = caseEvidneceBox.getText();
        String caseDetailbox = caseDetailsBox.getText();
        String caseStatus = null;
        if (caseConditionBox.getSelectedItem().equals("Pending")) {
            
            caseStatus = "Pending";
        }else if(caseConditionBox.getSelectedItem().equals("Investigating")){
            caseStatus = "Investigating";
        }else if(caseConditionBox.getSelectedItem().equals("Solved")){
            caseStatus = "Solved";
        }else if(caseConditionBox.getSelectedItem().equals("On Trial")){
            caseStatus = "On Trial";
        }else{
            
             caseStatus = "Closed";
        }
        

        caseModel.setCase_id(caseIDbox);
        caseModel.setAccused_id(caseAccusedIDbox);
        caseModel.setVictim_id(caseVictimIDbox);
        caseModel.setPolice_id(casePoliceIDbox);
        caseModel.setCrime_type(caseCrimeTypebox);
        caseModel.setCase_status(caseStatus);
        
        
        //Case Creation Date
//        java.util.Date dat = new java.util.Date();
//        java.sql.Date caseCreationDate = new java.sql.Date(dat.getDate());
//        java.sql.Timestamp caseCreationTime=new java.sql.Timestamp(dat.getTime());
        
//        System.out.println(sqlDate);
//        System.out.println(sqlTime);
       
        java.util.Date cd = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm aa");
        String caseCreationDate= df.format(cd);
        String caseCreationTime= tf.format(cd);
        caseModel.setCase_open_date(caseCreationDate.toString());
        caseModel.setCase_open_time(caseCreationTime.toString());
        
        caseModel.setOffence_time(caseOffenceTimebox);
        caseModel.setOffence_location(caseOffenceLocationbox);
        caseModel.setWitness_name(caseWitnessNamebox);
        caseModel.setWitness_location(caseWitnessLocationbox);
        caseModel.setWitness_mobile(caseWitnessMobile);
        caseModel.setCase_evidence(caseEvidencebox);
        caseModel.setCase_details(caseDetailbox);
        

        int caseAddingStatus = caseDao.create(caseModel);

        if (caseAddingStatus > 0) {
            JOptionPane.showMessageDialog(null, "Case Added!");
            caseReset();
        } else {
            JOptionPane.showMessageDialog(null, "Case is not Added!");
        }
        
        
    }
    
     @Override
    public void caseReset() {
        caseModel.setCase_id("");
        caseModel.setAccused_id("");
        caseModel.setVictim_id("");
        caseModel.setPolice_id("");
        caseModel.setCrime_type("");
        
        
        //Case Creation Date
        java.util.Date dat = new java.util.Date();
        java.sql.Date caseCreationDate = new java.sql.Date(dat.getDate());
        java.sql.Timestamp caseCreationTime=new java.sql.Timestamp(dat.getTime());
        
//        System.out.println(sqlDate);
//        System.out.println(sqlTime);
       
//        java.util.Date cd = new java.util.Date();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat tf = new SimpleDateFormat("hh:mm aa");
//        String caseCreationDate= df.format(cd);
//        String caseCreationTime= tf.format(cd);
        caseModel.setCase_open_date("");
        caseModel.setCase_open_time("");
        
        caseModel.setOffence_time("");
        caseModel.setOffence_location("");
        caseModel.setWitness_name("");
        caseModel.setWitness_location("");
        caseModel.setWitness_mobile("");
        caseModel.setCase_evidence("");
        caseModel.setCase_details("");
        

    }
    
    
    List<CaseModel> getCaseIDList() {
        caseDao = new CaseDao();
        return caseDao.getAll();

    }
    
    
    
    
    //===========================Police Interface============================
    @Override
    public void createPolice() {
        policeDao = new policeDao();
        Policemodel = new policeModel();

        String policeID = polDOBBox.getText();
        String polBatch = polBatchNoBox.getText();
        String polFirst = polFirstNameBox.getText();
        String polLast = polLastNameBox.getText();
        String polEmail = polMailBox.getText();
        String polFather = polFatherNameBox.getText();
        String polGender = null;
        if (polGenderBox.getSelectedItem().equals("Male")) {
            polGender = "Male";
        } else if (polGenderBox.getSelectedItem().equals("Female")) {
            polGender = "Female";
        }
        String polDOB = polDOBBox.getText();
        String polPersonalPhone = polPersonalPhoneBox.getText();
        String polOfficePhone = PolOfficePhoneBox.getText();
        String polRankName = polGenderBox.getSelectedItem().toString();
        String polWeaponID = polWeaponIdBox.getText();
        String polWeaponSerial = polWeaponSerialBox.getText();
        String polDeptName = polRankItem.getSelectedItem().toString();
        String polPresent = polPresentBox.getText();
        String polPermanent = polPermanentBox.getText();
        String polJoinDate = polJoinBox.getText();

        Policemodel.setPolice_id(policeID);
        Policemodel.setBatch_number(polBatch);
        Policemodel.setFirst_name(polFirst);
        Policemodel.setLast_name(polLast);
        Policemodel.setEmail(polEmail);
        Policemodel.setFather_name(polFather);
        Policemodel.setGender(polGender);
        Policemodel.setBirth_date(polDOB);
        Policemodel.setPersonal_phone(polPersonalPhone);
        Policemodel.setOffice_phone(polOfficePhone);
        Policemodel.setRank_name(polRankName);
        Policemodel.setWeapon_id(polWeaponID);
        Policemodel.setWeapon_serial(polWeaponSerial);
        Policemodel.setDepartment_name(polDeptName);
        Policemodel.setPresent_adress(polPresent);
        Policemodel.setPermanent_address(polPermanent);
        Policemodel.setJoin_date(polJoinDate);
        Policemodel.setCreation_date("no date entry");

        int status = policeDao.create(Policemodel);

        if (status > 0) {
            JOptionPane.showMessageDialog(null, "Police Added!");
        } else {
            JOptionPane.showMessageDialog(null, "Police is not Added!");
        }

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menubox = new javax.swing.JPanel();
        policeInfo1 = new javax.swing.JLabel();
        userName19 = new javax.swing.JLabel();
        activity1 = new javax.swing.JLabel();
        logOut1 = new javax.swing.JLabel();
        addNewAdmin1 = new javax.swing.JLabel();
        editProfile1 = new javax.swing.JLabel();
        personalInfo1 = new javax.swing.JLabel();
        comInfo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dash2 = new javax.swing.JLabel();
        policeInfo2 = new javax.swing.JLabel();
        dash3 = new javax.swing.JLabel();
        dash4 = new javax.swing.JLabel();
        userName20 = new javax.swing.JLabel();
        userName21 = new javax.swing.JLabel();
        titlebar = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        menuitem = new javax.swing.JPanel();
        dashboard = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        policeboard = new javax.swing.JPanel();
        buttonPane = new javax.swing.JPanel();
        adpolb = new javax.swing.JButton();
        createrankb = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        createdeptb = new javax.swing.JButton();
        solvedcaseb = new javax.swing.JButton();
        editpolb = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        polDynamicPane = new javax.swing.JPanel();
        pbhome = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        policeTable = new javax.swing.JTable();
        createpol = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        polPermanentBox = new javax.swing.JTextField();
        polDOBBox = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        polJoinBox = new javax.swing.JTextField();
        PolOfficePhoneBox = new javax.swing.JTextField();
        polWeaponIdBox = new javax.swing.JTextField();
        polFirstNameBox = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        polPersonalPhoneBox = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        polBatchNoBox = new javax.swing.JTextField();
        polLastNameBox = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        polWeaponSerialBox = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        polWeaponAssingedBox = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        polPresentBox = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        polIDBox = new javax.swing.JTextField();
        polFatherNameBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        polMailBox = new javax.swing.JTextField();
        polRankItem = new javax.swing.JComboBox();
        createpol2 = new javax.swing.JPanel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        polPermanentBox3 = new javax.swing.JTextField();
        polDOBBox3 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        polJoinBox3 = new javax.swing.JTextField();
        PolOfficePhoneBox3 = new javax.swing.JTextField();
        polWeaponIdBox3 = new javax.swing.JTextField();
        polFirstNameBox3 = new javax.swing.JTextField();
        jLabel222 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jLabel309 = new javax.swing.JLabel();
        polPersonalPhoneBox3 = new javax.swing.JTextField();
        jLabel310 = new javax.swing.JLabel();
        jLabel311 = new javax.swing.JLabel();
        jLabel312 = new javax.swing.JLabel();
        jLabel313 = new javax.swing.JLabel();
        polBatchNoBox3 = new javax.swing.JTextField();
        polLastNameBox3 = new javax.swing.JTextField();
        jLabel314 = new javax.swing.JLabel();
        jLabel315 = new javax.swing.JLabel();
        polWeaponSerialBox3 = new javax.swing.JTextField();
        jLabel316 = new javax.swing.JLabel();
        polWeaponAssingedBox3 = new javax.swing.JTextField();
        jLabel317 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();
        jLabel319 = new javax.swing.JLabel();
        polPresentBox3 = new javax.swing.JTextField();
        jLabel320 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jLabel321 = new javax.swing.JLabel();
        jLabel322 = new javax.swing.JLabel();
        polIDBox3 = new javax.swing.JTextField();
        polFatherNameBox3 = new javax.swing.JTextField();
        jLabel323 = new javax.swing.JLabel();
        polMailBox3 = new javax.swing.JTextField();
        polDeptSel = new javax.swing.JComboBox();
        polRankItem1 = new javax.swing.JComboBox();
        polGenderBox = new javax.swing.JComboBox();
        createrank = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jLabel229 = new javax.swing.JLabel();
        rankID = new javax.swing.JTextField();
        jLabel230 = new javax.swing.JLabel();
        rankNameBox = new javax.swing.JTextField();
        jLabel231 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        rankTable = new javax.swing.JTable();
        jLabel235 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jLabel236 = new javax.swing.JLabel();
        deptIDBox4 = new javax.swing.JTextField();
        jLabel237 = new javax.swing.JLabel();
        deptNameBox4 = new javax.swing.JTextField();
        editDeptSearchBar2 = new javax.swing.JTextField();
        jButton38 = new javax.swing.JButton();
        jLabel238 = new javax.swing.JLabel();
        jLabel239 = new javax.swing.JLabel();
        createdept = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel224 = new javax.swing.JLabel();
        deptIDBox = new javax.swing.JTextField();
        jLabel225 = new javax.swing.JLabel();
        deptNameBox = new javax.swing.JTextField();
        jLabel227 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        deptTable = new javax.swing.JTable();
        jLabel226 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jLabel232 = new javax.swing.JLabel();
        deptIDBox3 = new javax.swing.JTextField();
        jLabel233 = new javax.swing.JLabel();
        deptNameBox3 = new javax.swing.JTextField();
        editDeptSearchBar = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jLabel234 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        editpolice = new javax.swing.JPanel();
        jTextField43 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jTextField58 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jTextField59 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jTextField60 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel78 = new javax.swing.JLabel();
        jTextField63 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        officerlist = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        officerTable = new javax.swing.JTable();
        closedcasecount = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        countPolice = new javax.swing.JTable();
        caseboard = new javax.swing.JPanel();
        buttonPane1 = new javax.swing.JPanel();
        addcaseb = new javax.swing.JButton();
        caseassingb = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        pendingcaseb = new javax.swing.JButton();
        solvedcaseb1 = new javax.swing.JButton();
        casewithdrawnb = new javax.swing.JButton();
        caseevidenceb = new javax.swing.JButton();
        caseEvidenceb = new javax.swing.JButton();
        caseDynamicPane = new javax.swing.JPanel();
        casehome = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        caseTableList = new javax.swing.JTable();
        createCriminal = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        accusedAgeBox = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        accusedArrestingTimeBox = new javax.swing.JTextField();
        accusedPresentAddressBox = new javax.swing.JTextField();
        accusedFirstNameBox = new javax.swing.JTextField();
        accusedFatherNameBox = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        accusedCaseIDBox = new javax.swing.JTextField();
        accusedLastNameBox = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        accusedPermanentAddressBox = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        accusedPreviousCaseBox = new javax.swing.JTextField();
        accusedIsPrisonedBox = new javax.swing.JComboBox();
        jLabel98 = new javax.swing.JLabel();
        accusedIDBox = new javax.swing.JTextField();
        accusedMotherNameBox = new javax.swing.JTextField();
        accusedGenderBox = new javax.swing.JComboBox();
        accusedArrestSituationBox = new javax.swing.JComboBox();
        jLabel99 = new javax.swing.JLabel();
        accusedMobileNoBOx = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        accusedRemarksBox = new javax.swing.JTextField();
        createvictim = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        victimAgeBox = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        victimPhoneBox = new javax.swing.JTextField();
        victimPresentAdBox = new javax.swing.JTextField();
        victimCaseIDBox = new javax.swing.JTextField();
        VictimFatherNameBox = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        victimCriminalIDBox = new javax.swing.JTextField();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jLabel167 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        victimIDBox = new javax.swing.JTextField();
        VictimMotherNameBox = new javax.swing.JTextField();
        victimGenderBox = new javax.swing.JComboBox();
        victimGDInfoBox = new javax.swing.JComboBox();
        jLabel324 = new javax.swing.JLabel();
        victimFirstNameBox = new javax.swing.JTextField();
        jLabel325 = new javax.swing.JLabel();
        victimLastNameBox = new javax.swing.JTextField();
        victimPermanentAdBox = new javax.swing.JTextField();
        victimGDIDBox = new javax.swing.JTextField();
        jLabel170 = new javax.swing.JLabel();
        createCase = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        caseOffenceLocationBox = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        caseWitnnessNameBox = new javax.swing.JTextField();
        caseEvidneceBox = new javax.swing.JTextField();
        caseVictimIDBox = new javax.swing.JTextField();
        caseWitnessLocationBox = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        caseAccusedIDBox = new javax.swing.JTextField();
        casePoliceIDBox = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel144 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        caseWitnessMobileBox = new javax.swing.JTextField();
        caseIDBox = new javax.swing.JTextField();
        jLabel151 = new javax.swing.JLabel();
        caseOffenceTimeBox = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        caseDetailsBox = new javax.swing.JTextArea();
        caseCrimeTypeBox = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        caseConditionBox = new javax.swing.JComboBox();
        caseAssign = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        caseAssignedPolice = new javax.swing.JTable();
        casePending = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        pendingCaseTable = new javax.swing.JTable();
        jLabel105 = new javax.swing.JLabel();
        caseStatusComboBox = new javax.swing.JComboBox();
        caseClosed = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        caseWithdrawn = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        caseEvidence = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        caseTable = new javax.swing.JTable();
        caseEdit = new javax.swing.JPanel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jTextField94 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jTextField95 = new javax.swing.JTextField();
        jTextField97 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField98 = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jTextField99 = new javax.swing.JTextField();
        jTextField100 = new javax.swing.JTextField();
        jLabel159 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jTextField101 = new javax.swing.JTextField();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jTextField102 = new javax.swing.JTextField();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel177 = new javax.swing.JLabel();
        jTextField104 = new javax.swing.JTextField();
        jTextField108 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox();
        jLabel178 = new javax.swing.JLabel();
        jTextField109 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField110 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        courtBoard = new javax.swing.JPanel();
        buttonPane2 = new javax.swing.JPanel();
        adpolb1 = new javax.swing.JButton();
        createrankb1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        createdeptb2 = new javax.swing.JButton();
        solvedcaseb2 = new javax.swing.JButton();
        editpolb1 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        courtDynamicPane = new javax.swing.JPanel();
        courtPane = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jLabel293 = new javax.swing.JLabel();
        courtIDBox = new javax.swing.JTextField();
        jLabel294 = new javax.swing.JLabel();
        courtNameBox = new javax.swing.JTextField();
        jLabel295 = new javax.swing.JLabel();
        jLabel326 = new javax.swing.JLabel();
        courtLocationBox = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        courtTable = new javax.swing.JTable();
        jLabel296 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jLabel297 = new javax.swing.JLabel();
        deptIDBox7 = new javax.swing.JTextField();
        jLabel298 = new javax.swing.JLabel();
        deptNameBox7 = new javax.swing.JTextField();
        editDeptSearchBar4 = new javax.swing.JTextField();
        jButton61 = new javax.swing.JButton();
        jLabel299 = new javax.swing.JLabel();
        jLabel300 = new javax.swing.JLabel();
        courtTrialPane = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jLabel301 = new javax.swing.JLabel();
        jLabel302 = new javax.swing.JLabel();
        jLabel303 = new javax.swing.JLabel();
        jLabel327 = new javax.swing.JLabel();
        getCaseIDBox = new javax.swing.JComboBox();
        getCourListbox = new javax.swing.JComboBox();
        courtFacingDateChoosed = new com.toedter.calendar.JDateChooser();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        caseTrialTable = new javax.swing.JTable();
        jLabel304 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jButton65 = new javax.swing.JButton();
        jLabel305 = new javax.swing.JLabel();
        trialCaseIDFindBox = new javax.swing.JTextField();
        jLabel308 = new javax.swing.JLabel();
        courtHomeBoard = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        policeTable2 = new javax.swing.JTable();
        criminalboard = new javax.swing.JPanel();
        buttonPane4 = new javax.swing.JPanel();
        adpolb3 = new javax.swing.JButton();
        createrankb3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        createdeptb3 = new javax.swing.JButton();
        solvedcaseb4 = new javax.swing.JButton();
        editpolb3 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        polDynamicPane1 = new javax.swing.JPanel();
        pbhome1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        policeTable3 = new javax.swing.JTable();
        createpol1 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        polPermanentBox2 = new javax.swing.JTextField();
        polDOBBox2 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        polJoinBox2 = new javax.swing.JTextField();
        PolOfficePhoneBox2 = new javax.swing.JTextField();
        polWeaponIdBox2 = new javax.swing.JTextField();
        polFirstNameBox2 = new javax.swing.JTextField();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        polPersonalPhoneBox2 = new javax.swing.JTextField();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        polBatchNoBox2 = new javax.swing.JTextField();
        polLastNameBox2 = new javax.swing.JTextField();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        polWeaponSerialBox2 = new javax.swing.JTextField();
        jLabel188 = new javax.swing.JLabel();
        polWeaponAssingedBox2 = new javax.swing.JTextField();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        polPresentBox2 = new javax.swing.JTextField();
        jLabel192 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        polIDBox2 = new javax.swing.JTextField();
        polFatherNameBox2 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        polMailBox2 = new javax.swing.JTextField();
        polRankItem3 = new javax.swing.JComboBox();
        createpol3 = new javax.swing.JPanel();
        jLabel306 = new javax.swing.JLabel();
        jLabel307 = new javax.swing.JLabel();
        jLabel328 = new javax.swing.JLabel();
        polPermanentBox4 = new javax.swing.JTextField();
        polDOBBox4 = new javax.swing.JTextField();
        jButton66 = new javax.swing.JButton();
        polJoinBox4 = new javax.swing.JTextField();
        PolOfficePhoneBox4 = new javax.swing.JTextField();
        polWeaponIdBox4 = new javax.swing.JTextField();
        polFirstNameBox4 = new javax.swing.JTextField();
        jLabel329 = new javax.swing.JLabel();
        jLabel330 = new javax.swing.JLabel();
        jLabel331 = new javax.swing.JLabel();
        polPersonalPhoneBox4 = new javax.swing.JTextField();
        jLabel332 = new javax.swing.JLabel();
        jLabel333 = new javax.swing.JLabel();
        jLabel334 = new javax.swing.JLabel();
        jLabel335 = new javax.swing.JLabel();
        polBatchNoBox4 = new javax.swing.JTextField();
        polLastNameBox4 = new javax.swing.JTextField();
        jLabel336 = new javax.swing.JLabel();
        jLabel337 = new javax.swing.JLabel();
        polWeaponSerialBox4 = new javax.swing.JTextField();
        jLabel338 = new javax.swing.JLabel();
        polWeaponAssingedBox4 = new javax.swing.JTextField();
        jLabel339 = new javax.swing.JLabel();
        jLabel340 = new javax.swing.JLabel();
        jLabel341 = new javax.swing.JLabel();
        polPresentBox4 = new javax.swing.JTextField();
        jLabel342 = new javax.swing.JLabel();
        jButton67 = new javax.swing.JButton();
        jLabel343 = new javax.swing.JLabel();
        jLabel344 = new javax.swing.JLabel();
        polIDBox4 = new javax.swing.JTextField();
        polFatherNameBox4 = new javax.swing.JTextField();
        jLabel345 = new javax.swing.JLabel();
        polMailBox4 = new javax.swing.JTextField();
        polDeptSel2 = new javax.swing.JComboBox();
        polRankItem4 = new javax.swing.JComboBox();
        polGenderBox2 = new javax.swing.JComboBox();
        createrank1 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jLabel346 = new javax.swing.JLabel();
        rankID2 = new javax.swing.JTextField();
        jLabel347 = new javax.swing.JLabel();
        rankNameBox2 = new javax.swing.JTextField();
        jLabel348 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        rankTable2 = new javax.swing.JTable();
        jLabel349 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jLabel350 = new javax.swing.JLabel();
        deptIDBox8 = new javax.swing.JTextField();
        jLabel351 = new javax.swing.JLabel();
        deptNameBox8 = new javax.swing.JTextField();
        editDeptSearchBar5 = new javax.swing.JTextField();
        jButton72 = new javax.swing.JButton();
        jLabel352 = new javax.swing.JLabel();
        jLabel353 = new javax.swing.JLabel();
        createdept2 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jButton73 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jLabel354 = new javax.swing.JLabel();
        deptIDBox2 = new javax.swing.JTextField();
        jLabel355 = new javax.swing.JLabel();
        deptNameBox2 = new javax.swing.JTextField();
        jLabel356 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        deptTable2 = new javax.swing.JTable();
        jLabel357 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jButton75 = new javax.swing.JButton();
        jButton76 = new javax.swing.JButton();
        jLabel358 = new javax.swing.JLabel();
        deptIDBox9 = new javax.swing.JTextField();
        jLabel359 = new javax.swing.JLabel();
        deptNameBox9 = new javax.swing.JTextField();
        editDeptSearchBar6 = new javax.swing.JTextField();
        jButton77 = new javax.swing.JButton();
        jLabel360 = new javax.swing.JLabel();
        jLabel361 = new javax.swing.JLabel();
        editpolice1 = new javax.swing.JPanel();
        jTextField64 = new javax.swing.JTextField();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jTextField66 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jTextField75 = new javax.swing.JTextField();
        jTextField76 = new javax.swing.JTextField();
        jLabel197 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField77 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jTextField82 = new javax.swing.JTextField();
        jTextField85 = new javax.swing.JTextField();
        jLabel201 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jTextField86 = new javax.swing.JTextField();
        jTextField87 = new javax.swing.JTextField();
        jTextField88 = new javax.swing.JTextField();
        jLabel202 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jTextField89 = new javax.swing.JTextField();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jTextField90 = new javax.swing.JTextField();
        jLabel206 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jTextField91 = new javax.swing.JTextField();
        jLabel211 = new javax.swing.JLabel();
        jTextField92 = new javax.swing.JTextField();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jTextField93 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel215 = new javax.swing.JLabel();
        jTextField96 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        officerlist1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        officerTable2 = new javax.swing.JTable();
        closedcasecount1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        countPolice2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menubox.setBackground(new java.awt.Color(51, 51, 51));
        menubox.setPreferredSize(new java.awt.Dimension(250, 650));
        menubox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        policeInfo1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        policeInfo1.setForeground(new java.awt.Color(255, 255, 255));
        policeInfo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        policeInfo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-police-24.png"))); // NOI18N
        policeInfo1.setText(" Police Information");
        policeInfo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                policeInfo1MouseClicked(evt);
            }
        });
        menubox.add(policeInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 250, 50));

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

        dash2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        dash2.setForeground(new java.awt.Color(255, 255, 255));
        dash2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard_1.png"))); // NOI18N
        dash2.setText(" Dashboard");
        dash2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash2MouseExited(evt);
            }
        });
        menubox.add(dash2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 250, 50));

        policeInfo2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        policeInfo2.setForeground(new java.awt.Color(255, 255, 255));
        policeInfo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        policeInfo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-court-32.png"))); // NOI18N
        policeInfo2.setText(" Court Information");
        policeInfo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                policeInfo2MouseClicked(evt);
            }
        });
        menubox.add(policeInfo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 250, 70));

        dash3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        dash3.setForeground(new java.awt.Color(255, 255, 255));
        dash3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Book of record.png"))); // NOI18N
        dash3.setText(" Case Information");
        dash3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash3MouseExited(evt);
            }
        });
        menubox.add(dash3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 250, 50));

        dash4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        dash4.setForeground(new java.awt.Color(255, 255, 255));
        dash4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Vampire.png"))); // NOI18N
        dash4.setText(" Criminal Information");
        dash4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash4MouseExited(evt);
            }
        });
        menubox.add(dash4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 250, 50));

        userName20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userName20.setForeground(new java.awt.Color(255, 255, 255));
        userName20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/police.png"))); // NOI18N
        menubox.add(userName20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 70, 70));

        userName21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userName21.setForeground(new java.awt.Color(255, 255, 255));
        userName21.setText("MinhajulIslam");
        menubox.add(userName21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

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

        dashboard.setBackground(new java.awt.Color(252, 250, 246));
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel141.setText("Dashboard");
        dashboard.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 21, -1, -1));

        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel135.setBackground(new java.awt.Color(255, 0, 255));
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel135.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gun.png"))); // NOI18N
        jLabel135.setOpaque(true);
        jPanel43.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(102, 102, 102));
        jLabel136.setText("GUNS");
        jPanel43.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel137.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel137.setText("250");
        jPanel43.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel138.setBackground(new java.awt.Color(102, 204, 255));
        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel138.setOpaque(true);
        jPanel44.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(102, 102, 102));
        jLabel139.setText("TOTAL CASE");
        jPanel44.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel140.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("250");
        jPanel44.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel43.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 109, 230, 100));

        dashboard.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 230, 100));

        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel110.setBackground(new java.awt.Color(153, 51, 255));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/police-car-64.png"))); // NOI18N
        jLabel110.setOpaque(true);
        jPanel34.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(102, 102, 102));
        jLabel111.setText("VEHICLE");
        jPanel34.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel112.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("250");
        jPanel34.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setBackground(new java.awt.Color(102, 204, 255));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setOpaque(true);
        jPanel35.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(102, 102, 102));
        jLabel114.setText("TOTAL CASE");
        jPanel35.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel115.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("250");
        jPanel35.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel34.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 109, 230, 100));

        dashboard.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 230, 100));

        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel129.setBackground(new java.awt.Color(0, 174, 245));
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/case.png"))); // NOI18N
        jLabel129.setOpaque(true);
        jPanel41.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(102, 102, 102));
        jLabel130.setText("TOTAL CASE");
        jPanel41.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel131.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("250");
        jPanel41.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel132.setBackground(new java.awt.Color(102, 204, 255));
        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel132.setOpaque(true);
        jPanel42.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(102, 102, 102));
        jLabel133.setText("TOTAL CASE");
        jPanel42.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel134.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("250");
        jPanel42.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel41.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 109, 230, 100));

        dashboard.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 230, 100));

        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel123.setBackground(new java.awt.Color(0, 147, 87));
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Solved Case.png"))); // NOI18N
        jLabel123.setOpaque(true);
        jPanel39.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(102, 102, 102));
        jLabel124.setText("SOLVED");
        jPanel39.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel125.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("250");
        jPanel39.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel126.setBackground(new java.awt.Color(102, 204, 255));
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel126.setOpaque(true);
        jPanel40.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(102, 102, 102));
        jLabel127.setText("TOTAL CASE");
        jPanel40.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel128.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel128.setText("250");
        jPanel40.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel39.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 109, 230, 100));

        dashboard.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 230, 100));

        jPanel36.setBackground(new java.awt.Color(204, 204, 204));
        jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Dashboard  >");
        jPanel36.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 100, 30));

        jLabel116.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel116.setText("Home  >");
        jPanel36.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 60, 30));

        dashboard.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 920, 30));

        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel117.setBackground(new java.awt.Color(234, 87, 55));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pendingCase.png"))); // NOI18N
        jLabel117.setOpaque(true);
        jPanel37.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(102, 102, 102));
        jLabel118.setText("PENDING");
        jPanel37.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel119.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("250");
        jPanel37.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel120.setBackground(new java.awt.Color(102, 204, 255));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setOpaque(true);
        jPanel38.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(102, 102, 102));
        jLabel121.setText("TOTAL CASE");
        jPanel38.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel122.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("250");
        jPanel38.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        jPanel37.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 109, 230, 100));

        dashboard.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 230, 100));

        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel145.setBackground(new java.awt.Color(102, 204, 255));
        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel145.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-police-80.png"))); // NOI18N
        jLabel145.setOpaque(true);
        jPanel46.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(102, 102, 102));
        jLabel146.setText("TOTAL CASE");
        jPanel46.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel147.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel147.setText("250");
        jPanel46.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 80, 30));

        dashboard.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 360, 230, 100));

        jLabel148.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(153, 153, 153));
        jLabel148.setText("Version 1.0");
        dashboard.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 234, 16));

        menuitem.add(dashboard, "card3");

        policeboard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane.setBackground(new java.awt.Color(234, 238, 243));
        buttonPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adpolb.setText("Add New Police");
        adpolb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpolbActionPerformed(evt);
            }
        });
        buttonPane.add(adpolb, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, 50));

        createrankb.setText("Police Rank");
        createrankb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createrankbActionPerformed(evt);
            }
        });
        buttonPane.add(createrankb, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 130, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Police Information Update / Delete Or Modify");
        buttonPane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        createdeptb.setText("Police Department");
        createdeptb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createdeptbActionPerformed(evt);
            }
        });
        buttonPane.add(createdeptb, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 130, 50));

        solvedcaseb.setText("Demo");
        solvedcaseb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcasebActionPerformed(evt);
            }
        });
        buttonPane.add(solvedcaseb, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 130, 50));

        editpolb.setText("Edit Police");
        editpolb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpolbActionPerformed(evt);
            }
        });
        buttonPane.add(editpolb, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 130, 50));

        jButton44.setText("Demo");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        buttonPane.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 130, 50));

        jButton45.setText("Demo ");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        buttonPane.add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 130, 50));

        polDynamicPane.setBackground(new java.awt.Color(252, 250, 246));
        polDynamicPane.setMinimumSize(new java.awt.Dimension(1000, 524));
        polDynamicPane.setPreferredSize(new java.awt.Dimension(860, 492));
        polDynamicPane.setLayout(new java.awt.CardLayout());

        pbhome.setBackground(new java.awt.Color(234, 238, 243));
        pbhome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("All The Police Details Are:");
        pbhome.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane2.setBackground(new java.awt.Color(252, 250, 246));

        policeTable.setBackground(new java.awt.Color(234, 238, 243));
        policeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(policeTable);

        pbhome.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        polDynamicPane.add(pbhome, "card5");

        createpol.setBackground(new java.awt.Color(252, 250, 246));
        createpol.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Please Input Police Details:");
        createpol.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Present Adress:");
        createpol.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 110, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Official Phone:");
        createpol.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));
        createpol.add(polPermanentBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 200, 30));
        createpol.add(polDOBBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Submit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        createpol.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createpol.add(polJoinBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));
        createpol.add(PolOfficePhoneBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));
        createpol.add(polWeaponIdBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));
        createpol.add(polFirstNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Placement Date:");
        createpol.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 110, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Dept Name:");
        createpol.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 110, 30));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Permanent Address:");
        createpol.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 130, 30));
        createpol.add(polPersonalPhoneBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("First Name:");
        createpol.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Email:");
        createpol.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Police ID:");
        createpol.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Personal Phone:");
        createpol.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        createpol.add(polBatchNoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));
        createpol.add(polLastNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Gender:");
        createpol.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Weapon ID:");
        createpol.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 110, 30));
        createpol.add(polWeaponSerialBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("Rank Name:");
        createpol.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));
        createpol.add(polWeaponAssingedBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("Last Name");
        createpol.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("Weapon Type:");
        createpol.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 130, 30));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Date Of Birth:");
        createpol.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));
        createpol.add(polPresentBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 200, 30));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("Weapon Serial:");
        createpol.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Back");
        createpol.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Batch Number:");
        createpol.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("Father's Name:");
        createpol.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));
        createpol.add(polIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));
        createpol.add(polFatherNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        jLabel4.setText("jLabel4");
        createpol.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, -1, -1));
        createpol.add(polMailBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));

        createpol.add(polRankItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 200, 30));

        createpol2.setBackground(new java.awt.Color(252, 250, 246));
        createpol2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel219.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel219.setText("Please Input Police Details:");
        createpol2.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel220.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel220.setText("Present Adress:");
        createpol2.add(jLabel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 110, 30));

        jLabel221.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel221.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel221.setText("Official Phone:");
        createpol2.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));
        createpol2.add(polPermanentBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 200, 30));
        createpol2.add(polDOBBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jButton26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton26.setText("Submit");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        createpol2.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createpol2.add(polJoinBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));
        createpol2.add(PolOfficePhoneBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));
        createpol2.add(polWeaponIdBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));
        createpol2.add(polFirstNameBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));

        jLabel222.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel222.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel222.setText("Placement Date:");
        createpol2.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 110, 30));

        jLabel223.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel223.setText("Dept Name:");
        createpol2.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 110, 30));

        jLabel309.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel309.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel309.setText("Permanent Address:");
        createpol2.add(jLabel309, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 130, 30));
        createpol2.add(polPersonalPhoneBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));

        jLabel310.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel310.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel310.setText("First Name:");
        createpol2.add(jLabel310, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));

        jLabel311.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel311.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel311.setText("Email:");
        createpol2.add(jLabel311, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel312.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel312.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel312.setText("Police ID:");
        createpol2.add(jLabel312, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel313.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel313.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel313.setText("Personal Phone:");
        createpol2.add(jLabel313, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        createpol2.add(polBatchNoBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));
        createpol2.add(polLastNameBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));

        jLabel314.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel314.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel314.setText("Gender:");
        createpol2.add(jLabel314, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel315.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel315.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel315.setText("Weapon ID:");
        createpol2.add(jLabel315, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 110, 30));
        createpol2.add(polWeaponSerialBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));

        jLabel316.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel316.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel316.setText("Rank Name:");
        createpol2.add(jLabel316, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));
        createpol2.add(polWeaponAssingedBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        jLabel317.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel317.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel317.setText("Last Name");
        createpol2.add(jLabel317, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jLabel318.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel318.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel318.setText("Weapon Type:");
        createpol2.add(jLabel318, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 130, 30));

        jLabel319.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel319.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel319.setText("Date Of Birth:");
        createpol2.add(jLabel319, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));
        createpol2.add(polPresentBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 200, 30));

        jLabel320.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel320.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel320.setText("Weapon Serial:");
        createpol2.add(jLabel320, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));

        jButton27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton27.setText("Back");
        createpol2.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel321.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel321.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel321.setText("Batch Number:");
        createpol2.add(jLabel321, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel322.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel322.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel322.setText("Father's Name:");
        createpol2.add(jLabel322, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));
        createpol2.add(polIDBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));
        createpol2.add(polFatherNameBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        jLabel323.setText("jLabel4");
        createpol2.add(jLabel323, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, -1, -1));
        createpol2.add(polMailBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));

        createpol2.add(polDeptSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 200, 30));

        createpol2.add(polRankItem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 200, 30));

        polGenderBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        createpol2.add(polGenderBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 200, 30));

        createpol.add(createpol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        polDynamicPane.add(createpol, "card2");

        createrank.setBackground(new java.awt.Color(252, 250, 246));
        createrank.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(252, 250, 246));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton30.setText("Edit Departments");
        jPanel6.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton31.setText("Create");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel229.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel229.setText("Rank ID:");
        jPanel6.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel6.add(rankID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel230.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel230.setText("Rank Name:");
        jPanel6.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel6.add(rankNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel231.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel231.setText("Create a New Rank");
        jPanel6.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        createrank.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rankTable.setBackground(new java.awt.Color(252, 250, 246));
        rankTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane16.setViewportView(rankTable);

        jPanel7.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel235.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel235.setText("List of All ranks");
        jPanel7.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        createrank.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel8.setBackground(new java.awt.Color(252, 250, 246));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton36.setText("Edit Departments");
        jPanel8.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton37.setText("Find");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel236.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel236.setText("Rank ID:");
        jPanel8.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel8.add(deptIDBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel237.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel237.setText("Rank Name:");
        jPanel8.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel8.add(deptNameBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel8.add(editDeptSearchBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton38.setText("Edit");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel238.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel238.setText("Rank ID:");
        jPanel8.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel239.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel239.setText("Edit Your Rank");
        jPanel8.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        createrank.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        polDynamicPane.add(createrank, "card3");

        createdept.setBackground(new java.awt.Color(252, 250, 246));
        createdept.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(252, 250, 246));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton29.setText("Edit Departments");
        jPanel3.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton28.setText("Create");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel224.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel224.setText("Department ID:");
        jPanel3.add(jLabel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel3.add(deptIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel225.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel225.setText("Department Name:");
        jPanel3.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel3.add(deptNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel227.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel227.setText("Create a New Department");
        jPanel3.add(jLabel227, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        createdept.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deptTable.setBackground(new java.awt.Color(252, 250, 246));
        deptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane15.setViewportView(deptTable);

        jPanel4.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel226.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel226.setText("Edit Your Department");
        jPanel4.add(jLabel226, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createdept.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel5.setBackground(new java.awt.Color(252, 250, 246));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton33.setText("Edit Departments");
        jPanel5.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton34.setText("Find");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel232.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel232.setText("Dept ID:");
        jPanel5.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel5.add(deptIDBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel233.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel233.setText("Department Name:");
        jPanel5.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel5.add(deptNameBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel5.add(editDeptSearchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton35.setText("Edit");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel234.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel234.setText("Department ID:");
        jPanel5.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel228.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel228.setText("Edit Your Department");
        jPanel5.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createdept.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        polDynamicPane.add(createdept, "card3");

        editpolice.setBackground(new java.awt.Color(252, 250, 246));
        editpolice.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        editpolice.add(jTextField43, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("Police ID:");
        editpolice.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel56.setText("Placement Date:");
        editpolice.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 110, 30));
        editpolice.add(jTextField44, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 200, 30));
        editpolice.add(jTextField47, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));
        editpolice.add(jTextField48, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));
        editpolice.add(jTextField49, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 200, 30));
        editpolice.add(jTextField50, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText("Gender:");
        editpolice.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel5.setText("jLabel4");
        editpolice.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, -1, -1));
        editpolice.add(jTextField51, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));
        editpolice.add(jTextField52, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel61.setText("Role:");
        editpolice.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 110, 30));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setText("Staion ID:");
        editpolice.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setText("Join Date:");
        editpolice.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 110, 30));
        editpolice.add(jTextField53, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 200, 30));
        editpolice.add(jTextField54, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 200, 30));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel64.setText("Last Name");
        editpolice.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Update");
        editpolice.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        editpolice.add(jTextField55, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));
        editpolice.add(jTextField56, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));
        editpolice.add(jTextField57, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("Father's Name:");
        editpolice.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel66.setText("Official Phone:");
        editpolice.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));
        editpolice.add(jTextField58, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 200, 30));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel67.setText("Email:");
        editpolice.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("Dept Name:");
        editpolice.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 110, 30));
        editpolice.add(jTextField59, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel69.setText("Present Adress:");
        editpolice.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 110, 30));

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("Find");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        editpolice.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 70, 30));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("Batch ID:");
        editpolice.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel71.setText("Rank ID:");
        editpolice.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 110, 30));
        editpolice.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText("Weapon Assigned:");
        editpolice.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 130, 30));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText("Personal Phone:");
        editpolice.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        editpolice.add(jTextField60, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel74.setText("First Name:");
        editpolice.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));
        editpolice.add(jTextField61, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 200, 30));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel75.setText("Permanent Address:");
        editpolice.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 130, 30));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel76.setText("Dept ID:");
        editpolice.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel77.setText("Date Of Birth:");
        editpolice.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));
        editpolice.add(jTextField62, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        editpolice.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 200, 30));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel78.setText("Find Police By Police ID:");
        editpolice.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 180, 30));
        editpolice.add(jTextField63, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 200, 30));

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setText("Back");
        editpolice.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        polDynamicPane.add(editpolice, "card5");

        officerlist.setBackground(new java.awt.Color(252, 250, 246));
        officerlist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("All The Police Officer Details Are:");
        officerlist.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        officerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(officerTable);

        officerlist.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 460));

        polDynamicPane.add(officerlist, "card5");

        closedcasecount.setBackground(new java.awt.Color(252, 250, 246));
        closedcasecount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Closed Case Count by Police Officer");
        closedcasecount.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        countPolice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(countPolice);

        closedcasecount.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 480));

        polDynamicPane.add(closedcasecount, "card5");

        javax.swing.GroupLayout policeboardLayout = new javax.swing.GroupLayout(policeboard);
        policeboard.setLayout(policeboardLayout);
        policeboardLayout.setHorizontalGroup(
            policeboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(polDynamicPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        policeboardLayout.setVerticalGroup(
            policeboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(policeboardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(polDynamicPane, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(policeboard, "card2");

        caseboard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane1.setBackground(new java.awt.Color(252, 250, 246));
        buttonPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addcaseb.setText("Add New Case");
        addcaseb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcasebActionPerformed(evt);
            }
        });
        buttonPane1.add(addcaseb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, 50));

        caseassingb.setText("Case Assignment");
        caseassingb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseassingbActionPerformed(evt);
            }
        });
        buttonPane1.add(caseassingb, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 130, 50));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Case Information Update / Delete Or Modify");
        buttonPane1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        pendingcaseb.setText("Pending Case");
        pendingcaseb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingcasebActionPerformed(evt);
            }
        });
        buttonPane1.add(pendingcaseb, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 130, 50));

        solvedcaseb1.setText("Closed Case");
        solvedcaseb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcaseb1ActionPerformed(evt);
            }
        });
        buttonPane1.add(solvedcaseb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 130, 50));

        casewithdrawnb.setText("Case Withdrawn");
        casewithdrawnb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casewithdrawnbActionPerformed(evt);
            }
        });
        buttonPane1.add(casewithdrawnb, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 130, 50));

        caseevidenceb.setText("Case Update");
        caseevidenceb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseevidencebActionPerformed(evt);
            }
        });
        buttonPane1.add(caseevidenceb, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, 130, 50));

        caseEvidenceb.setText("Case Evidence");
        caseEvidenceb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseEvidencebActionPerformed(evt);
            }
        });
        buttonPane1.add(caseEvidenceb, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 130, 50));

        caseDynamicPane.setBackground(new java.awt.Color(252, 250, 246));
        caseDynamicPane.setPreferredSize(new java.awt.Dimension(860, 492));
        caseDynamicPane.setLayout(new java.awt.CardLayout());

        casehome.setBackground(new java.awt.Color(252, 250, 246));
        casehome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("All The Case List:");
        casehome.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane5.setBackground(new java.awt.Color(252, 250, 246));

        caseTableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(caseTableList);

        casehome.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 460));

        caseDynamicPane.add(casehome, "card5");

        createCriminal.setBackground(new java.awt.Color(252, 250, 246));
        createCriminal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setText("Please Put Accused Information");
        createCriminal.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel80.setText("Arresting Time:");
        createCriminal.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));
        createCriminal.add(accusedAgeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setText("Back");
        createCriminal.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createCriminal.add(accusedArrestingTimeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));
        createCriminal.add(accusedPresentAddressBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));
        createCriminal.add(accusedFirstNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));
        createCriminal.add(accusedFatherNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel81.setText("Is Prisoned: ");
        createCriminal.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 120, 30));

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel85.setText("First Name:");
        createCriminal.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel86.setText("Fathe's Name:");
        createCriminal.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel87.setText("Accused ID:");
        createCriminal.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel88.setText("Is Arrested:");
        createCriminal.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 150, 30));

        accusedCaseIDBox.setText("CaseNo:");
        createCriminal.add(accusedCaseIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));
        createCriminal.add(accusedLastNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel89.setText("Gender:");
        createCriminal.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel90.setText("Present Adress:");
        createCriminal.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));
        createCriminal.add(accusedPermanentAddressBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel92.setText("Last Name");
        createCriminal.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel94.setText("Age:");
        createCriminal.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel95.setText("Permanent Adress:");
        createCriminal.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 120, 30));

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton13.setText("Next");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        createCriminal.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel96.setText("Case ID:");
        createCriminal.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel97.setText("Previous Cases:");
        createCriminal.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 130, 30));
        createCriminal.add(accusedPreviousCaseBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        accusedIsPrisonedBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        createCriminal.add(accusedIsPrisonedBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel98.setText("Mother's Name:");
        createCriminal.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));

        accusedIDBox.setText("AccusedNo:");
        createCriminal.add(accusedIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));
        createCriminal.add(accusedMotherNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        accusedGenderBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        createCriminal.add(accusedGenderBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 200, 30));

        accusedArrestSituationBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Arrested", "Not Arrested", "Flew Away" }));
        createCriminal.add(accusedArrestSituationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel99.setText("Mobile Number:");
        createCriminal.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));
        createCriminal.add(accusedMobileNoBOx, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 200, 30));

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel100.setText("Remarks:");
        createCriminal.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 120, 30));
        createCriminal.add(accusedRemarksBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 200, 30));

        caseDynamicPane.add(createCriminal, "card2");

        createvictim.setBackground(new java.awt.Color(252, 250, 246));
        createvictim.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel79.setText("Please Put Victim Information");
        createvictim.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel82.setText("Phone:");
        createvictim.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        createvictim.add(victimAgeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton19.setText("Back");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        createvictim.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createvictim.add(victimPhoneBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));
        createvictim.add(victimPresentAdBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));
        createvictim.add(victimCaseIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));
        createvictim.add(VictimFatherNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 200, 30));

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel91.setText("Case ID:");
        createvictim.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel93.setText("Fathe's Name:");
        createvictim.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel160.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel160.setText("Victim ID:");
        createvictim.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel161.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel161.setText("Does Victim Filed GD?:");
        createvictim.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 150, 30));

        victimCriminalIDBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                victimCriminalIDBoxActionPerformed(evt);
            }
        });
        createvictim.add(victimCriminalIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));

        jLabel162.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel162.setText("Gender:");
        createvictim.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));

        jLabel163.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel163.setText("Present Adress:");
        createvictim.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 110, 30));

        jLabel165.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel165.setText("Age:");
        createvictim.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));

        jLabel166.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel166.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel166.setText("Permanent Adress:");
        createvictim.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 120, 30));

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton20.setText("Next");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        createvictim.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel167.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel167.setText("Accused ID:");
        createvictim.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel169.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel169.setText("Mother's Name:");
        createvictim.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));

        victimIDBox.setText("VictimNo:");
        createvictim.add(victimIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));
        createvictim.add(VictimMotherNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        victimGenderBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        createvictim.add(victimGenderBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        victimGDInfoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        createvictim.add(victimGDInfoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));

        jLabel324.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel324.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel324.setText("Victim's First Name:");
        createvictim.add(jLabel324, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 120, 30));
        createvictim.add(victimFirstNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));

        jLabel325.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel325.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel325.setText("Victim's Last Name:");
        createvictim.add(jLabel325, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 130, 30));
        createvictim.add(victimLastNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));
        createvictim.add(victimPermanentAdBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));
        createvictim.add(victimGDIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        jLabel170.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel170.setText("Victim GD ID:");
        createvictim.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 120, 30));

        caseDynamicPane.add(createvictim, "card2");

        createCase.setBackground(new java.awt.Color(252, 250, 246));
        createCase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel83.setText("Please Input Case Information");
        createCase.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel84.setText("Witness Name:");
        createCase.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        createCase.add(caseOffenceLocationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton14.setText("Back");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        createCase.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createCase.add(caseWitnnessNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));
        createCase.add(caseEvidneceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));
        createCase.add(caseVictimIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));
        createCase.add(caseWitnessLocationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel101.setText("Witness Location:");
        createCase.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 120, 30));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel102.setText("Victim's ID:");
        createCase.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel103.setText("Crime Type:");
        createCase.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel104.setText("Case ID:");
        createCase.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));
        createCase.add(caseAccusedIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));
        createCase.add(casePoliceIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel106.setText("Offence Time:");
        createCase.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel107.setText("Evidence List:");
        createCase.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 110, 30));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel108.setText("Police ID:");
        createCase.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel109.setText("Offence Location");
        createCase.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton15.setText("Submit");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        createCase.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel144.setText("Accused ID:");
        createCase.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel149.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel149.setText("Withness Mobile:");
        createCase.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 130, 30));
        createCase.add(caseWitnessMobileBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));
        createCase.add(caseIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));

        jLabel151.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel151.setText("Case Details:");
        createCase.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));
        createCase.add(caseOffenceTimeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 200, 30));

        caseDetailsBox.setColumns(20);
        caseDetailsBox.setRows(5);
        jScrollPane1.setViewportView(caseDetailsBox);

        createCase.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 550, 70));
        createCase.add(caseCrimeTypeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));

        jLabel164.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel164.setText("Case Status:");
        createCase.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 130, 30));

        caseConditionBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Investigating", "Solved", "On Trial", "Closed" }));
        createCase.add(caseConditionBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        caseDynamicPane.add(createCase, "card2");

        caseAssign.setBackground(new java.awt.Color(252, 250, 246));
        caseAssign.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Case Assigned Officers:");
        caseAssign.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane7.setBackground(new java.awt.Color(252, 250, 246));

        caseAssignedPolice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(caseAssignedPolice);

        caseAssign.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        caseDynamicPane.add(caseAssign, "card5");

        casePending.setBackground(new java.awt.Color(252, 250, 246));
        casePending.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Change Status:");
        casePending.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, -1));

        jScrollPane8.setBackground(new java.awt.Color(252, 250, 246));

        pendingCaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(pendingCaseTable);

        casePending.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel105.setText("Pending Cases Are:");
        casePending.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        caseStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Investigating", "Solved", "On Trial", "Closed" }));
        casePending.add(caseStatusComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 130, 30));

        caseDynamicPane.add(casePending, "card5");

        caseClosed.setBackground(new java.awt.Color(252, 250, 246));
        caseClosed.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Close and Solved Cases:");
        caseClosed.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane9.setBackground(new java.awt.Color(252, 250, 246));

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTable9);

        caseClosed.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        caseDynamicPane.add(caseClosed, "card5");

        caseWithdrawn.setBackground(new java.awt.Color(252, 250, 246));
        caseWithdrawn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Case Withdrawn:");
        caseWithdrawn.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane10.setBackground(new java.awt.Color(252, 250, 246));

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jTable10);

        caseWithdrawn.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        caseDynamicPane.add(caseWithdrawn, "card5");

        caseEvidence.setBackground(new java.awt.Color(252, 250, 246));
        caseEvidence.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Case Evidence List:");
        caseEvidence.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane11.setBackground(new java.awt.Color(252, 250, 246));

        caseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane11.setViewportView(caseTable);

        caseEvidence.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        caseDynamicPane.add(caseEvidence, "card5");

        caseEdit.setBackground(new java.awt.Color(252, 250, 246));
        caseEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel152.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel152.setText("Find Your Case By ID:");
        caseEdit.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, 30));

        jLabel153.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel153.setText("Witness Name:");
        caseEdit.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 110, 30));
        caseEdit.add(jTextField94, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 200, 30));

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton16.setText("Find");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        caseEdit.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 90, -1));
        caseEdit.add(jTextField95, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 200, 30));
        caseEdit.add(jTextField97, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 200, 30));
        caseEdit.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 200, 30));
        caseEdit.add(jTextField98, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 200, 30));

        jLabel154.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel154.setText("Witness Location:");
        caseEdit.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 120, 30));

        jLabel155.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel155.setText("Victim's ID:");
        caseEdit.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 110, 30));

        jLabel156.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel156.setText("Crime Type:");
        caseEdit.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 110, 30));

        jLabel157.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel157.setText("Case ID:");
        caseEdit.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 110, 30));

        jLabel158.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel158.setText("Witness ID:");
        caseEdit.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 150, 30));
        caseEdit.add(jTextField99, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 200, 30));
        caseEdit.add(jTextField100, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 200, 30));

        jLabel159.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel159.setText("Offence Time:");
        caseEdit.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 110, 30));

        jLabel168.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel168.setText("Evidence List:");
        caseEdit.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 110, 30));
        caseEdit.add(jTextField101, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 200, 30));

        jLabel172.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel172.setText("Police ID:");
        caseEdit.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 110, 30));

        jLabel173.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel173.setText("Offence Location");
        caseEdit.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 110, 30));

        jLabel174.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel174.setText("Remarks:");
        caseEdit.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 120, 30));

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setText("Update");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        caseEdit.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel175.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel175.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel175.setText("Accused ID:");
        caseEdit.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 110, 30));

        jLabel176.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel176.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel176.setText("Withness Mobile:");
        caseEdit.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 130, 30));
        caseEdit.add(jTextField102, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, 200, 30));

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        caseEdit.add(jComboBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 200, 30));

        jLabel177.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel177.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel177.setText("Case Opening Date:");
        caseEdit.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 130, 30));
        caseEdit.add(jTextField104, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 200, 30));
        caseEdit.add(jTextField108, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 200, 30));

        jLabel13.setText("jLabel4");
        caseEdit.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 530, -1, -1));

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        caseEdit.add(jComboBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 200, 30));

        jLabel178.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel178.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel178.setText("Case Details:");
        caseEdit.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 110, 30));
        caseEdit.add(jTextField109, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 200, 30));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        caseEdit.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 550, 70));
        caseEdit.add(jTextField110, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 200, 30));

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton21.setText("Back");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        caseEdit.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));

        caseDynamicPane.add(caseEdit, "card2");

        javax.swing.GroupLayout caseboardLayout = new javax.swing.GroupLayout(caseboard);
        caseboard.setLayout(caseboardLayout);
        caseboardLayout.setHorizontalGroup(
            caseboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(caseDynamicPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        caseboardLayout.setVerticalGroup(
            caseboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(caseboardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(caseDynamicPane, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(caseboard, "card2");

        courtBoard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane2.setBackground(new java.awt.Color(234, 238, 243));
        buttonPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adpolb1.setText("Add New Court");
        adpolb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpolb1ActionPerformed(evt);
            }
        });
        buttonPane2.add(adpolb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, 50));

        createrankb1.setText("Court Trial List");
        createrankb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createrankb1ActionPerformed(evt);
            }
        });
        buttonPane2.add(createrankb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 130, 50));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Court Information Update/Delete");
        buttonPane2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        createdeptb2.setText("Bell Out Criminals");
        createdeptb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createdeptb2ActionPerformed(evt);
            }
        });
        buttonPane2.add(createdeptb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 130, 50));

        solvedcaseb2.setText("Demo");
        solvedcaseb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcaseb2ActionPerformed(evt);
            }
        });
        buttonPane2.add(solvedcaseb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 130, 50));

        editpolb1.setText("Court Facing Date");
        editpolb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpolb1ActionPerformed(evt);
            }
        });
        buttonPane2.add(editpolb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 130, 50));

        jButton47.setText("Demo ");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        buttonPane2.add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 130, 50));

        courtDynamicPane.setBackground(new java.awt.Color(252, 250, 246));
        courtDynamicPane.setPreferredSize(new java.awt.Dimension(860, 492));
        courtDynamicPane.setLayout(new java.awt.CardLayout());

        courtPane.setBackground(new java.awt.Color(252, 250, 246));
        courtPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(252, 250, 246));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton57.setText("Edit Departments");
        jPanel15.add(jButton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton58.setText("Create");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel293.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel293.setText("Court ID:");
        jPanel15.add(jLabel293, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 120, 40));
        jPanel15.add(courtIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel294.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel294.setText("Court Name:");
        jPanel15.add(jLabel294, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 120, 40));
        jPanel15.add(courtNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 200, 40));

        jLabel295.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel295.setText("Create New Court");
        jPanel15.add(jLabel295, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel326.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel326.setText("Court Location:");
        jPanel15.add(jLabel326, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 120, 40));
        jPanel15.add(courtLocationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 200, 40));

        courtPane.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel16.setBackground(new java.awt.Color(204, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        courtTable.setBackground(new java.awt.Color(252, 250, 246));
        courtTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane22.setViewportView(courtTable);

        jPanel16.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel296.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel296.setText("List of All Court");
        jPanel16.add(jLabel296, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        courtPane.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel17.setBackground(new java.awt.Color(252, 250, 246));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton59.setText("Edit Departments");
        jPanel17.add(jButton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton60.setText("Find");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel297.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel297.setText("Rank ID:");
        jPanel17.add(jLabel297, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel17.add(deptIDBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel298.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel298.setText("Rank Name:");
        jPanel17.add(jLabel298, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel17.add(deptNameBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel17.add(editDeptSearchBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton61.setText("Edit");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton61, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel299.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel299.setText("Rank ID:");
        jPanel17.add(jLabel299, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel300.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel300.setText("Edit Court");
        jPanel17.add(jLabel300, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        courtPane.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        courtDynamicPane.add(courtPane, "card3");

        courtTrialPane.setBackground(new java.awt.Color(252, 250, 246));
        courtTrialPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(252, 250, 246));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton62.setText("Edit Departments");
        jPanel18.add(jButton62, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton63.setText("Create");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel301.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel301.setText("Case ID:");
        jPanel18.add(jLabel301, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 120, 40));

        jLabel302.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel302.setText("Court Name:");
        jPanel18.add(jLabel302, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 120, 40));

        jLabel303.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel303.setText("Set A Date for Court Trial");
        jPanel18.add(jLabel303, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel327.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel327.setText("Court Facing Date:");
        jPanel18.add(jLabel327, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 120, 40));

        jPanel18.add(getCaseIDBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 200, 40));

        jPanel18.add(getCourListbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 200, 40));
        jPanel18.add(courtFacingDateChoosed, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 200, 40));

        courtTrialPane.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel19.setBackground(new java.awt.Color(204, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        caseTrialTable.setBackground(new java.awt.Color(252, 250, 246));
        caseTrialTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane23.setViewportView(caseTrialTable);

        jPanel19.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel304.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel304.setText("All The Casse Hearings in Court");
        jPanel19.add(jLabel304, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        courtTrialPane.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel20.setBackground(new java.awt.Color(252, 250, 246));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton65.setText("Find");
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 70, 30));

        jLabel305.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel305.setText("Case ID:");
        jPanel20.add(jLabel305, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 30));
        jPanel20.add(trialCaseIDFindBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 160, 30));

        jLabel308.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel308.setText("Find All Hearings By Case ID:");
        jPanel20.add(jLabel308, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        courtTrialPane.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        courtDynamicPane.add(courtTrialPane, "card3");

        courtHomeBoard.setBackground(new java.awt.Color(234, 238, 243));
        courtHomeBoard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("All the court Details:");
        courtHomeBoard.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane12.setBackground(new java.awt.Color(252, 250, 246));

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
        jScrollPane12.setViewportView(policeTable2);

        courtHomeBoard.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        courtDynamicPane.add(courtHomeBoard, "card5");

        javax.swing.GroupLayout courtBoardLayout = new javax.swing.GroupLayout(courtBoard);
        courtBoard.setLayout(courtBoardLayout);
        courtBoardLayout.setHorizontalGroup(
            courtBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(courtDynamicPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        courtBoardLayout.setVerticalGroup(
            courtBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(courtBoardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(courtDynamicPane, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(courtBoard, "card2");

        criminalboard.setBackground(new java.awt.Color(0, 0, 153));

        buttonPane4.setBackground(new java.awt.Color(234, 238, 243));
        buttonPane4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adpolb3.setText("Add New Police");
        adpolb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpolb3ActionPerformed(evt);
            }
        });
        buttonPane4.add(adpolb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, 50));

        createrankb3.setText("Police Rank");
        createrankb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createrankb3ActionPerformed(evt);
            }
        });
        buttonPane4.add(createrankb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 130, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Police Information Update / Delete Or Modify");
        buttonPane4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        createdeptb3.setText("Police Department");
        createdeptb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createdeptb3ActionPerformed(evt);
            }
        });
        buttonPane4.add(createdeptb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 130, 50));

        solvedcaseb4.setText("Demo");
        solvedcaseb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvedcaseb4ActionPerformed(evt);
            }
        });
        buttonPane4.add(solvedcaseb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 130, 50));

        editpolb3.setText("Edit Police");
        editpolb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpolb3ActionPerformed(evt);
            }
        });
        buttonPane4.add(editpolb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 130, 50));

        jButton46.setText("Demo");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        buttonPane4.add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 130, 50));

        jButton64.setText("Demo ");
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });
        buttonPane4.add(jButton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 130, 50));

        polDynamicPane1.setBackground(new java.awt.Color(252, 250, 246));
        polDynamicPane1.setPreferredSize(new java.awt.Dimension(860, 492));
        polDynamicPane1.setLayout(new java.awt.CardLayout());

        pbhome1.setBackground(new java.awt.Color(234, 238, 243));
        pbhome1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("All The Police Details Are:");
        pbhome1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane13.setBackground(new java.awt.Color(252, 250, 246));

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
        jScrollPane13.setViewportView(policeTable3);

        pbhome1.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 470));

        polDynamicPane1.add(pbhome1, "card5");

        createpol1.setBackground(new java.awt.Color(252, 250, 246));
        createpol1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel143.setText("Please Input Police Details:");
        createpol1.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel150.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel150.setText("Present Adress:");
        createpol1.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 110, 30));

        jLabel171.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel171.setText("Official Phone:");
        createpol1.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));
        createpol1.add(polPermanentBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 200, 30));
        createpol1.add(polDOBBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton18.setText("Submit");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        createpol1.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createpol1.add(polJoinBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));
        createpol1.add(PolOfficePhoneBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));
        createpol1.add(polWeaponIdBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));
        createpol1.add(polFirstNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));

        jLabel179.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel179.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel179.setText("Placement Date:");
        createpol1.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 110, 30));

        jLabel180.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel180.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel180.setText("Dept Name:");
        createpol1.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 110, 30));

        jLabel181.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel181.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel181.setText("Permanent Address:");
        createpol1.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 130, 30));
        createpol1.add(polPersonalPhoneBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));

        jLabel182.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel182.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel182.setText("First Name:");
        createpol1.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));

        jLabel183.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel183.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel183.setText("Email:");
        createpol1.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel184.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel184.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel184.setText("Police ID:");
        createpol1.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel185.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel185.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel185.setText("Personal Phone:");
        createpol1.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        createpol1.add(polBatchNoBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));
        createpol1.add(polLastNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));

        jLabel186.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel186.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel186.setText("Gender:");
        createpol1.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel187.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel187.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel187.setText("Weapon ID:");
        createpol1.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 110, 30));
        createpol1.add(polWeaponSerialBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));

        jLabel188.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel188.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel188.setText("Rank Name:");
        createpol1.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));
        createpol1.add(polWeaponAssingedBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        jLabel189.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel189.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel189.setText("Last Name");
        createpol1.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jLabel190.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel190.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel190.setText("Weapon Type:");
        createpol1.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 130, 30));

        jLabel191.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel191.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel191.setText("Date Of Birth:");
        createpol1.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));
        createpol1.add(polPresentBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 200, 30));

        jLabel192.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel192.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel192.setText("Weapon Serial:");
        createpol1.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));

        jButton22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton22.setText("Back");
        createpol1.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel193.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel193.setText("Batch Number:");
        createpol1.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel194.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel194.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel194.setText("Father's Name:");
        createpol1.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));
        createpol1.add(polIDBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));
        createpol1.add(polFatherNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        jLabel26.setText("jLabel4");
        createpol1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, -1, -1));
        createpol1.add(polMailBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));

        createpol1.add(polRankItem3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 200, 30));

        createpol3.setBackground(new java.awt.Color(252, 250, 246));
        createpol3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel306.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel306.setText("Please Input Police Details:");
        createpol3.add(jLabel306, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel307.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel307.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel307.setText("Present Adress:");
        createpol3.add(jLabel307, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 110, 30));

        jLabel328.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel328.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel328.setText("Official Phone:");
        createpol3.add(jLabel328, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));
        createpol3.add(polPermanentBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 200, 30));
        createpol3.add(polDOBBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jButton66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton66.setText("Submit");
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });
        createpol3.add(jButton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        createpol3.add(polJoinBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));
        createpol3.add(PolOfficePhoneBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));
        createpol3.add(polWeaponIdBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));
        createpol3.add(polFirstNameBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));

        jLabel329.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel329.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel329.setText("Placement Date:");
        createpol3.add(jLabel329, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 110, 30));

        jLabel330.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel330.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel330.setText("Dept Name:");
        createpol3.add(jLabel330, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 110, 30));

        jLabel331.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel331.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel331.setText("Permanent Address:");
        createpol3.add(jLabel331, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 130, 30));
        createpol3.add(polPersonalPhoneBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));

        jLabel332.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel332.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel332.setText("First Name:");
        createpol3.add(jLabel332, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));

        jLabel333.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel333.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel333.setText("Email:");
        createpol3.add(jLabel333, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel334.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel334.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel334.setText("Police ID:");
        createpol3.add(jLabel334, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel335.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel335.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel335.setText("Personal Phone:");
        createpol3.add(jLabel335, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        createpol3.add(polBatchNoBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));
        createpol3.add(polLastNameBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));

        jLabel336.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel336.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel336.setText("Gender:");
        createpol3.add(jLabel336, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel337.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel337.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel337.setText("Weapon ID:");
        createpol3.add(jLabel337, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 110, 30));
        createpol3.add(polWeaponSerialBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));

        jLabel338.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel338.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel338.setText("Rank Name:");
        createpol3.add(jLabel338, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));
        createpol3.add(polWeaponAssingedBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        jLabel339.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel339.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel339.setText("Last Name");
        createpol3.add(jLabel339, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jLabel340.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel340.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel340.setText("Weapon Type:");
        createpol3.add(jLabel340, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 130, 30));

        jLabel341.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel341.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel341.setText("Date Of Birth:");
        createpol3.add(jLabel341, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));
        createpol3.add(polPresentBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 200, 30));

        jLabel342.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel342.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel342.setText("Weapon Serial:");
        createpol3.add(jLabel342, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));

        jButton67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton67.setText("Back");
        createpol3.add(jButton67, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        jLabel343.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel343.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel343.setText("Batch Number:");
        createpol3.add(jLabel343, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel344.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel344.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel344.setText("Father's Name:");
        createpol3.add(jLabel344, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));
        createpol3.add(polIDBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));
        createpol3.add(polFatherNameBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        jLabel345.setText("jLabel4");
        createpol3.add(jLabel345, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, -1, -1));
        createpol3.add(polMailBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));

        createpol3.add(polDeptSel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 200, 30));

        createpol3.add(polRankItem4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 200, 30));

        polGenderBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        createpol3.add(polGenderBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 200, 30));

        createpol1.add(createpol3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        polDynamicPane1.add(createpol1, "card2");

        createrank1.setBackground(new java.awt.Color(252, 250, 246));
        createrank1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(252, 250, 246));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton68.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton68.setText("Edit Departments");
        jPanel21.add(jButton68, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton69.setText("Create");
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel346.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel346.setText("Rank ID:");
        jPanel21.add(jLabel346, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel21.add(rankID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel347.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel347.setText("Rank Name:");
        jPanel21.add(jLabel347, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel21.add(rankNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel348.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel348.setText("Create a New Rank");
        jPanel21.add(jLabel348, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        createrank1.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel22.setBackground(new java.awt.Color(204, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rankTable2.setBackground(new java.awt.Color(252, 250, 246));
        rankTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane24.setViewportView(rankTable2);

        jPanel22.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel349.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel349.setText("List of All ranks");
        jPanel22.add(jLabel349, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        createrank1.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel23.setBackground(new java.awt.Color(252, 250, 246));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton70.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton70.setText("Edit Departments");
        jPanel23.add(jButton70, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton71.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton71.setText("Find");
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton71, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel350.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel350.setText("Rank ID:");
        jPanel23.add(jLabel350, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel23.add(deptIDBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel351.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel351.setText("Rank Name:");
        jPanel23.add(jLabel351, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel23.add(deptNameBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel23.add(editDeptSearchBar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton72.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton72.setText("Edit");
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton72, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel352.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel352.setText("Rank ID:");
        jPanel23.add(jLabel352, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel353.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel353.setText("Edit Your Rank");
        jPanel23.add(jLabel353, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        createrank1.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        polDynamicPane1.add(createrank1, "card3");

        createdept2.setBackground(new java.awt.Color(252, 250, 246));
        createdept2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setBackground(new java.awt.Color(252, 250, 246));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton73.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton73.setText("Edit Departments");
        jPanel24.add(jButton73, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 40));

        jButton74.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton74.setText("Create");
        jButton74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton74ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 40));

        jLabel354.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel354.setText("Department ID:");
        jPanel24.add(jLabel354, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 40));
        jPanel24.add(deptIDBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 40));

        jLabel355.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel355.setText("Department Name:");
        jPanel24.add(jLabel355, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 40));
        jPanel24.add(deptNameBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 200, 40));

        jLabel356.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel356.setText("Create a New Department");
        jPanel24.add(jLabel356, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        createdept2.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 520));

        jPanel25.setBackground(new java.awt.Color(204, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane25.setViewportView(deptTable2);

        jPanel25.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, 470));

        jLabel357.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel357.setText("Edit Your Department");
        jPanel25.add(jLabel357, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createdept2.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 360, 510));

        jPanel26.setBackground(new java.awt.Color(252, 250, 246));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton75.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton75.setText("Edit Departments");
        jPanel26.add(jButton75, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 40));

        jButton76.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton76.setText("Find");
        jButton76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton76ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton76, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 70, 30));

        jLabel358.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel358.setText("Dept ID:");
        jPanel26.add(jLabel358, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        jPanel26.add(deptIDBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jLabel359.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel359.setText("Department Name:");
        jPanel26.add(jLabel359, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));
        jPanel26.add(deptNameBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 200, 40));
        jPanel26.add(editDeptSearchBar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, 30));

        jButton77.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton77.setText("Edit");
        jButton77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton77ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton77, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        jLabel360.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel360.setText("Department ID:");
        jPanel26.add(jLabel360, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel361.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel361.setText("Edit Your Department");
        jPanel26.add(jLabel361, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        createdept2.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 520));

        polDynamicPane1.add(createdept2, "card3");

        editpolice1.setBackground(new java.awt.Color(252, 250, 246));
        editpolice1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        editpolice1.add(jTextField64, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 200, 30));

        jLabel195.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel195.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel195.setText("Police ID:");
        editpolice1.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 110, 30));

        jLabel196.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel196.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel196.setText("Placement Date:");
        editpolice1.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 110, 30));
        editpolice1.add(jTextField66, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 200, 30));
        editpolice1.add(jTextField67, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 200, 30));
        editpolice1.add(jTextField71, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 200, 30));
        editpolice1.add(jTextField75, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 200, 30));
        editpolice1.add(jTextField76, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 30));

        jLabel197.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel197.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel197.setText("Gender:");
        editpolice1.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 110, 30));

        jLabel27.setText("jLabel4");
        editpolice1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, -1, -1));
        editpolice1.add(jTextField77, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 200, 30));
        editpolice1.add(jTextField79, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 200, 30));

        jLabel198.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel198.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel198.setText("Role:");
        editpolice1.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 110, 30));

        jLabel199.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel199.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel199.setText("Staion ID:");
        editpolice1.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 110, 30));

        jLabel200.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel200.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel200.setText("Join Date:");
        editpolice1.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 110, 30));
        editpolice1.add(jTextField82, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 200, 30));
        editpolice1.add(jTextField85, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 200, 30));

        jLabel201.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel201.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel201.setText("Last Name");
        editpolice1.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 110, 30));

        jButton23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton23.setText("Update");
        editpolice1.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 170, 40));
        editpolice1.add(jTextField86, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));
        editpolice1.add(jTextField87, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));
        editpolice1.add(jTextField88, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 30));

        jLabel202.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel202.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel202.setText("Father's Name:");
        editpolice1.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 110, 30));

        jLabel203.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel203.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel203.setText("Official Phone:");
        editpolice1.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 110, 30));
        editpolice1.add(jTextField89, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 200, 30));

        jLabel204.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel204.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel204.setText("Email:");
        editpolice1.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 110, 30));

        jLabel205.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel205.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel205.setText("Dept Name:");
        editpolice1.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 110, 30));
        editpolice1.add(jTextField90, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 200, 30));

        jLabel206.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel206.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel206.setText("Present Adress:");
        editpolice1.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 110, 30));

        jButton24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton24.setText("Find");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        editpolice1.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 70, 30));

        jLabel207.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel207.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel207.setText("Batch ID:");
        editpolice1.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 30));

        jLabel208.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel208.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel208.setText("Rank ID:");
        editpolice1.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 110, 30));
        editpolice1.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 30));

        jLabel209.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel209.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel209.setText("Weapon Assigned:");
        editpolice1.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 130, 30));

        jLabel210.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel210.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel210.setText("Personal Phone:");
        editpolice1.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 110, 30));
        editpolice1.add(jTextField91, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 30));

        jLabel211.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel211.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel211.setText("First Name:");
        editpolice1.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 110, 30));
        editpolice1.add(jTextField92, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 200, 30));

        jLabel212.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel212.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel212.setText("Permanent Address:");
        editpolice1.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 130, 30));

        jLabel213.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel213.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel213.setText("Dept ID:");
        editpolice1.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));

        jLabel214.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel214.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel214.setText("Date Of Birth:");
        editpolice1.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 110, 30));
        editpolice1.add(jTextField93, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 200, 30));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        editpolice1.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 200, 30));

        jLabel215.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel215.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel215.setText("Find Police By Police ID:");
        editpolice1.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 180, 30));
        editpolice1.add(jTextField96, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 200, 30));

        jButton25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton25.setText("Back");
        editpolice1.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 150, 40));

        polDynamicPane1.add(editpolice1, "card5");

        officerlist1.setBackground(new java.awt.Color(252, 250, 246));
        officerlist1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("All The Police Officer Details Are:");
        officerlist1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        officerTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane14.setViewportView(officerTable2);

        officerlist1.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 460));

        polDynamicPane1.add(officerlist1, "card5");

        closedcasecount1.setBackground(new java.awt.Color(252, 250, 246));
        closedcasecount1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Closed Case Count by Police Officer");
        closedcasecount1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        countPolice2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane26.setViewportView(countPolice2);

        closedcasecount1.add(jScrollPane26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 480));

        polDynamicPane1.add(closedcasecount1, "card5");

        javax.swing.GroupLayout criminalboardLayout = new javax.swing.GroupLayout(criminalboard);
        criminalboard.setLayout(criminalboardLayout);
        criminalboardLayout.setHorizontalGroup(
            criminalboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(polDynamicPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        criminalboardLayout.setVerticalGroup(
            criminalboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(criminalboardLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(polDynamicPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem.add(criminalboard, "card2");

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

    private void adpolbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpolbActionPerformed
        // TODO add your handling code here:

        pbhome.setVisible(false);
        createrank.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        editpolice.setVisible(false);
        createrank.setVisible(false);
        createdept.setVisible(false);
        editpolice.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        createpol.setVisible(true);


    }//GEN-LAST:event_adpolbActionPerformed

    private void createrankbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createrankbActionPerformed
        // TODO add your handling code here:

        pbhome.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        editpolice.setVisible(false);
        createrank.setVisible(false);
        createdept.setVisible(false);
        editpolice.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        createpol.setVisible(false);
        createrank.setVisible(true);


    }//GEN-LAST:event_createrankbActionPerformed

    private void createdeptbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createdeptbActionPerformed
        pbhome.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        editpolice.setVisible(false);
        createrank.setVisible(false);
        editpolice.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        createpol.setVisible(false);
        createrank.setVisible(false);
        createdept.setVisible(true);
    }//GEN-LAST:event_createdeptbActionPerformed

    private void solvedcasebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcasebActionPerformed
        // TODO add your handling code here:
        pbhome.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(true);
        editpolice.setVisible(false);
        createpol.setVisible(false);
        createrank.setVisible(false);
    }//GEN-LAST:event_solvedcasebActionPerformed

    private void editpolbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpolbActionPerformed
        // TODO add your handling code here:

        pbhome.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        editpolice.setVisible(false);
        createrank.setVisible(false);
        officerlist.setVisible(false);
        closedcasecount.setVisible(false);
        createpol.setVisible(false);
        createrank.setVisible(false);
        createdept.setVisible(false);
        editpolice.setVisible(true);
    }//GEN-LAST:event_editpolbActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void addcasebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcasebActionPerformed
        // TODO add your handling code here:
        createvictim.setVisible(false);
        casehome.setVisible(false);

        caseAssign.setVisible(false);
        caseWithdrawn.setVisible(false);
        caseEdit.setVisible(false);
        caseEvidence.setVisible(false);
        casePending.setVisible(false);
        caseClosed.setVisible(false);

        createCriminal.setVisible(true);

    }//GEN-LAST:event_addcasebActionPerformed

    private void caseassingbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseassingbActionPerformed

        


// TODO add your handling code here:

        createCriminal.setVisible(false);
        createvictim.setVisible(false);
        casehome.setVisible(false);

        caseWithdrawn.setVisible(false);
        caseEdit.setVisible(false);
        caseEvidence.setVisible(false);
        casePending.setVisible(false);
        caseClosed.setVisible(false);
        caseAssign.setVisible(true);


    }//GEN-LAST:event_caseassingbActionPerformed

    private void pendingcasebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingcasebActionPerformed
        // TODO add your handling code here:
        createCriminal.setVisible(false);

        casehome.setVisible(false);
        caseWithdrawn.setVisible(false);
        caseEdit.setVisible(false);
        caseEvidence.setVisible(false);
        caseClosed.setVisible(false);
        createCriminal.setVisible(false);
        caseAssign.setVisible(false);
        casePending.setVisible(true);

    }//GEN-LAST:event_pendingcasebActionPerformed

    private void solvedcaseb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcaseb1ActionPerformed
        // TODO add your handling code here:
        createvictim.setVisible(false);

        casehome.setVisible(false);
        caseWithdrawn.setVisible(false);
        caseEdit.setVisible(false);
        caseEvidence.setVisible(false);
        createCriminal.setVisible(false);
        caseAssign.setVisible(false);
        casePending.setVisible(false);
        caseClosed.setVisible(true);

    }//GEN-LAST:event_solvedcaseb1ActionPerformed

    private void casewithdrawnbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casewithdrawnbActionPerformed
        // TODO add your handling code here:
        createvictim.setVisible(false);

        casehome.setVisible(false);
        caseEdit.setVisible(false);
        caseEvidence.setVisible(false);
        createCriminal.setVisible(false);
        caseAssign.setVisible(false);
        casePending.setVisible(false);
        caseClosed.setVisible(false);
        caseWithdrawn.setVisible(true);
    }//GEN-LAST:event_casewithdrawnbActionPerformed

    private void caseevidencebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseevidencebActionPerformed
        // TODO add your handling code here:
        createvictim.setVisible(false);

        casehome.setVisible(false);
        createCriminal.setVisible(false);
        caseAssign.setVisible(false);
        casePending.setVisible(false);
        caseClosed.setVisible(false);
        caseWithdrawn.setVisible(false);
        caseEvidence.setVisible(false);

        caseEdit.setVisible(true);

    }//GEN-LAST:event_caseevidencebActionPerformed

    private void caseEvidencebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseEvidencebActionPerformed
        // TODO add your handling code here:
        createvictim.setVisible(false);

        casehome.setVisible(false);
        caseEdit.setVisible(false);
        createCriminal.setVisible(false);
        caseAssign.setVisible(false);
        casePending.setVisible(false);
        caseClosed.setVisible(false);
        caseWithdrawn.setVisible(false);
        caseEvidence.setVisible(true);
    }//GEN-LAST:event_caseEvidencebActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        createVictim();

// TODO add your handling code here:
        createCriminal.setVisible(false);
        createvictim.setVisible(false);
        createCase.setVisible(true);

    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        createCriminal();

// TODO add your handling code here:
        createCriminal.setVisible(false);
        createCase.setVisible(false);
        createvictim.setVisible(true);

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:

        createCase.setVisible(false);
        createvictim.setVisible(false);
        createCriminal.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void victimCriminalIDBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_victimCriminalIDBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_victimCriminalIDBoxActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        createCase();


    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        dashboard.setVisible(false);
        policeboard.setVisible(false);
        caseboard.setVisible(true);
        casehome.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        setState(ICONIFIED);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void policeInfo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_policeInfo2MouseClicked
        // TODO add your handling code here:
        
        dashboard.setVisible(false);
        policeboard.setVisible(false);
        caseboard.setVisible(false);
        pbhome.setVisible(false);
        courtBoard.setVisible(true);
    }//GEN-LAST:event_policeInfo2MouseClicked

    private void dash3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dash3MouseExited

    private void dash3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dash3MouseEntered

    private void dash3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash3MouseClicked
        // TODO add your handling code here:
        
                
        caseDao = new CaseDao();
        caseDao.showCaseTable(caseTableList);

        dashboard.setVisible(false);
        policeboard.setVisible(false);
        caseboard.setVisible(true);
        casehome.setVisible(true);
    }//GEN-LAST:event_dash3MouseClicked

    private void policeInfo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_policeInfo1MouseClicked
        // TODO add your handling code here:

       
        

        dashboard.setVisible(false);
        policeboard.setVisible(true);
        caseboard.setVisible(false);
        pbhome.setVisible(true);

    }//GEN-LAST:event_policeInfo1MouseClicked

    private void dash2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dash2MouseExited

    private void dash2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dash2MouseEntered

    private void dash2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash2MouseClicked
        // TODO add your handling code here:
         
        policeboard.setVisible(false);
        caseboard.setVisible(false);
        pbhome.setVisible(false);
        dashboard.setVisible(true);
        
    }//GEN-LAST:event_dash2MouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        deptCreate();


    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        createPolice();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:

        editDept();
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:

        rankCreate();
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void adpolb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpolb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adpolb1ActionPerformed

    private void createrankb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createrankb1ActionPerformed
        // TODO add your handling code here:
        
        courtHomeBoard.setVisible(false);
        courtPane.setVisible(false);
        courtTrialPane.setVisible(true);
        
    }//GEN-LAST:event_createrankb1ActionPerformed

    private void createdeptb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createdeptb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createdeptb2ActionPerformed

    private void solvedcaseb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcaseb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solvedcaseb2ActionPerformed

    private void editpolb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpolb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editpolb1ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
        createCourt();
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        // TODO add your handling code here:
        
        createCaseTrialDate();
        casetmDao = new CaseTrialDAO();
        casetmDao.showCaseTrialTable(caseTrialTable);
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        // TODO add your handling code here:
        
        getCourseInformation();
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void adpolb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpolb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adpolb3ActionPerformed

    private void createrankb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createrankb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createrankb3ActionPerformed

    private void createdeptb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createdeptb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createdeptb3ActionPerformed

    private void solvedcaseb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvedcaseb4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solvedcaseb4ActionPerformed

    private void editpolb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpolb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editpolb3ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton71ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton74ActionPerformed

    private void jButton76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton76ActionPerformed

    private void jButton77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton77ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void dash4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dash4MouseExited

    private void dash4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dash4MouseEntered

    private void dash4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dash4MouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PolOfficePhoneBox;
    private javax.swing.JTextField PolOfficePhoneBox2;
    private javax.swing.JTextField PolOfficePhoneBox3;
    private javax.swing.JTextField PolOfficePhoneBox4;
    private javax.swing.JTextField VictimFatherNameBox;
    private javax.swing.JTextField VictimMotherNameBox;
    private javax.swing.JTextField accusedAgeBox;
    private javax.swing.JComboBox accusedArrestSituationBox;
    private javax.swing.JTextField accusedArrestingTimeBox;
    private javax.swing.JTextField accusedCaseIDBox;
    private javax.swing.JTextField accusedFatherNameBox;
    private javax.swing.JTextField accusedFirstNameBox;
    private javax.swing.JComboBox accusedGenderBox;
    private javax.swing.JTextField accusedIDBox;
    private javax.swing.JComboBox accusedIsPrisonedBox;
    private javax.swing.JTextField accusedLastNameBox;
    private javax.swing.JTextField accusedMobileNoBOx;
    private javax.swing.JTextField accusedMotherNameBox;
    private javax.swing.JTextField accusedPermanentAddressBox;
    private javax.swing.JTextField accusedPresentAddressBox;
    private javax.swing.JTextField accusedPreviousCaseBox;
    private javax.swing.JTextField accusedRemarksBox;
    private javax.swing.JLabel activity1;
    private javax.swing.JLabel addNewAdmin1;
    private javax.swing.JButton addcaseb;
    private javax.swing.JButton adpolb;
    private javax.swing.JButton adpolb1;
    private javax.swing.JButton adpolb3;
    private javax.swing.JPanel buttonPane;
    private javax.swing.JPanel buttonPane1;
    private javax.swing.JPanel buttonPane2;
    private javax.swing.JPanel buttonPane4;
    private javax.swing.JTextField caseAccusedIDBox;
    private javax.swing.JPanel caseAssign;
    private javax.swing.JTable caseAssignedPolice;
    private javax.swing.JPanel caseClosed;
    private javax.swing.JComboBox caseConditionBox;
    private javax.swing.JTextField caseCrimeTypeBox;
    private javax.swing.JTextArea caseDetailsBox;
    private javax.swing.JPanel caseDynamicPane;
    private javax.swing.JPanel caseEdit;
    private javax.swing.JPanel caseEvidence;
    private javax.swing.JButton caseEvidenceb;
    private javax.swing.JTextField caseEvidneceBox;
    private javax.swing.JTextField caseIDBox;
    private javax.swing.JTextField caseOffenceLocationBox;
    private javax.swing.JTextField caseOffenceTimeBox;
    private javax.swing.JPanel casePending;
    private javax.swing.JTextField casePoliceIDBox;
    private javax.swing.JComboBox caseStatusComboBox;
    private javax.swing.JTable caseTable;
    private javax.swing.JTable caseTableList;
    private javax.swing.JTable caseTrialTable;
    private javax.swing.JTextField caseVictimIDBox;
    private javax.swing.JPanel caseWithdrawn;
    private javax.swing.JTextField caseWitnessLocationBox;
    private javax.swing.JTextField caseWitnessMobileBox;
    private javax.swing.JTextField caseWitnnessNameBox;
    private javax.swing.JButton caseassingb;
    private javax.swing.JPanel caseboard;
    private javax.swing.JButton caseevidenceb;
    private javax.swing.JPanel casehome;
    private javax.swing.JButton casewithdrawnb;
    private javax.swing.JPanel closedcasecount;
    private javax.swing.JPanel closedcasecount1;
    private javax.swing.JLabel comInfo;
    private javax.swing.JTable countPolice;
    private javax.swing.JTable countPolice2;
    private javax.swing.JPanel courtBoard;
    private javax.swing.JPanel courtDynamicPane;
    private com.toedter.calendar.JDateChooser courtFacingDateChoosed;
    private javax.swing.JPanel courtHomeBoard;
    private javax.swing.JTextField courtIDBox;
    private javax.swing.JTextField courtLocationBox;
    private javax.swing.JTextField courtNameBox;
    private javax.swing.JPanel courtPane;
    private javax.swing.JTable courtTable;
    private javax.swing.JPanel courtTrialPane;
    private javax.swing.JPanel createCase;
    private javax.swing.JPanel createCriminal;
    private javax.swing.JPanel createdept;
    private javax.swing.JPanel createdept2;
    private javax.swing.JButton createdeptb;
    private javax.swing.JButton createdeptb2;
    private javax.swing.JButton createdeptb3;
    private javax.swing.JPanel createpol;
    private javax.swing.JPanel createpol1;
    private javax.swing.JPanel createpol2;
    private javax.swing.JPanel createpol3;
    private javax.swing.JPanel createrank;
    private javax.swing.JPanel createrank1;
    private javax.swing.JButton createrankb;
    private javax.swing.JButton createrankb1;
    private javax.swing.JButton createrankb3;
    private javax.swing.JPanel createvictim;
    private javax.swing.JPanel criminalboard;
    private javax.swing.JLabel dash2;
    private javax.swing.JLabel dash3;
    private javax.swing.JLabel dash4;
    private javax.swing.JPanel dashboard;
    private javax.swing.JTextField deptIDBox;
    private javax.swing.JTextField deptIDBox2;
    private javax.swing.JTextField deptIDBox3;
    private javax.swing.JTextField deptIDBox4;
    private javax.swing.JTextField deptIDBox7;
    private javax.swing.JTextField deptIDBox8;
    private javax.swing.JTextField deptIDBox9;
    private javax.swing.JTextField deptNameBox;
    private javax.swing.JTextField deptNameBox2;
    private javax.swing.JTextField deptNameBox3;
    private javax.swing.JTextField deptNameBox4;
    private javax.swing.JTextField deptNameBox7;
    private javax.swing.JTextField deptNameBox8;
    private javax.swing.JTextField deptNameBox9;
    private javax.swing.JTable deptTable;
    private javax.swing.JTable deptTable2;
    private javax.swing.JTextField editDeptSearchBar;
    private javax.swing.JTextField editDeptSearchBar2;
    private javax.swing.JTextField editDeptSearchBar4;
    private javax.swing.JTextField editDeptSearchBar5;
    private javax.swing.JTextField editDeptSearchBar6;
    private javax.swing.JLabel editProfile1;
    private javax.swing.JButton editpolb;
    private javax.swing.JButton editpolb1;
    private javax.swing.JButton editpolb3;
    private javax.swing.JPanel editpolice;
    private javax.swing.JPanel editpolice1;
    private javax.swing.JComboBox getCaseIDBox;
    private javax.swing.JComboBox getCourListbox;
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
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel295;
    private javax.swing.JLabel jLabel296;
    private javax.swing.JLabel jLabel297;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel299;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel339;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel340;
    private javax.swing.JLabel jLabel341;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel344;
    private javax.swing.JLabel jLabel345;
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
    private javax.swing.JLabel jLabel360;
    private javax.swing.JLabel jLabel361;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField100;
    private javax.swing.JTextField jTextField101;
    private javax.swing.JTextField jTextField102;
    private javax.swing.JTextField jTextField104;
    private javax.swing.JTextField jTextField108;
    private javax.swing.JTextField jTextField109;
    private javax.swing.JTextField jTextField110;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField85;
    private javax.swing.JTextField jTextField86;
    private javax.swing.JTextField jTextField87;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField89;
    private javax.swing.JTextField jTextField90;
    private javax.swing.JTextField jTextField91;
    private javax.swing.JTextField jTextField92;
    private javax.swing.JTextField jTextField93;
    private javax.swing.JTextField jTextField94;
    private javax.swing.JTextField jTextField95;
    private javax.swing.JTextField jTextField96;
    private javax.swing.JTextField jTextField97;
    private javax.swing.JTextField jTextField98;
    private javax.swing.JTextField jTextField99;
    private javax.swing.JLabel logOut1;
    private javax.swing.JPanel menubox;
    private javax.swing.JPanel menuitem;
    private javax.swing.JTable officerTable;
    private javax.swing.JTable officerTable2;
    private javax.swing.JPanel officerlist;
    private javax.swing.JPanel officerlist1;
    private javax.swing.JPanel pbhome;
    private javax.swing.JPanel pbhome1;
    private javax.swing.JTable pendingCaseTable;
    private javax.swing.JButton pendingcaseb;
    private javax.swing.JLabel personalInfo1;
    private javax.swing.JTextField polBatchNoBox;
    private javax.swing.JTextField polBatchNoBox2;
    private javax.swing.JTextField polBatchNoBox3;
    private javax.swing.JTextField polBatchNoBox4;
    private javax.swing.JTextField polDOBBox;
    private javax.swing.JTextField polDOBBox2;
    private javax.swing.JTextField polDOBBox3;
    private javax.swing.JTextField polDOBBox4;
    private javax.swing.JComboBox polDeptSel;
    private javax.swing.JComboBox polDeptSel2;
    private javax.swing.JPanel polDynamicPane;
    private javax.swing.JPanel polDynamicPane1;
    private javax.swing.JTextField polFatherNameBox;
    private javax.swing.JTextField polFatherNameBox2;
    private javax.swing.JTextField polFatherNameBox3;
    private javax.swing.JTextField polFatherNameBox4;
    private javax.swing.JTextField polFirstNameBox;
    private javax.swing.JTextField polFirstNameBox2;
    private javax.swing.JTextField polFirstNameBox3;
    private javax.swing.JTextField polFirstNameBox4;
    private javax.swing.JComboBox polGenderBox;
    private javax.swing.JComboBox polGenderBox2;
    private javax.swing.JTextField polIDBox;
    private javax.swing.JTextField polIDBox2;
    private javax.swing.JTextField polIDBox3;
    private javax.swing.JTextField polIDBox4;
    private javax.swing.JTextField polJoinBox;
    private javax.swing.JTextField polJoinBox2;
    private javax.swing.JTextField polJoinBox3;
    private javax.swing.JTextField polJoinBox4;
    private javax.swing.JTextField polLastNameBox;
    private javax.swing.JTextField polLastNameBox2;
    private javax.swing.JTextField polLastNameBox3;
    private javax.swing.JTextField polLastNameBox4;
    private javax.swing.JTextField polMailBox;
    private javax.swing.JTextField polMailBox2;
    private javax.swing.JTextField polMailBox3;
    private javax.swing.JTextField polMailBox4;
    private javax.swing.JTextField polPermanentBox;
    private javax.swing.JTextField polPermanentBox2;
    private javax.swing.JTextField polPermanentBox3;
    private javax.swing.JTextField polPermanentBox4;
    private javax.swing.JTextField polPersonalPhoneBox;
    private javax.swing.JTextField polPersonalPhoneBox2;
    private javax.swing.JTextField polPersonalPhoneBox3;
    private javax.swing.JTextField polPersonalPhoneBox4;
    private javax.swing.JTextField polPresentBox;
    private javax.swing.JTextField polPresentBox2;
    private javax.swing.JTextField polPresentBox3;
    private javax.swing.JTextField polPresentBox4;
    private javax.swing.JComboBox polRankItem;
    private javax.swing.JComboBox polRankItem1;
    private javax.swing.JComboBox polRankItem3;
    private javax.swing.JComboBox polRankItem4;
    private javax.swing.JTextField polWeaponAssingedBox;
    private javax.swing.JTextField polWeaponAssingedBox2;
    private javax.swing.JTextField polWeaponAssingedBox3;
    private javax.swing.JTextField polWeaponAssingedBox4;
    private javax.swing.JTextField polWeaponIdBox;
    private javax.swing.JTextField polWeaponIdBox2;
    private javax.swing.JTextField polWeaponIdBox3;
    private javax.swing.JTextField polWeaponIdBox4;
    private javax.swing.JTextField polWeaponSerialBox;
    private javax.swing.JTextField polWeaponSerialBox2;
    private javax.swing.JTextField polWeaponSerialBox3;
    private javax.swing.JTextField polWeaponSerialBox4;
    private javax.swing.JLabel policeInfo1;
    private javax.swing.JLabel policeInfo2;
    private javax.swing.JTable policeTable;
    private javax.swing.JTable policeTable2;
    private javax.swing.JTable policeTable3;
    private javax.swing.JPanel policeboard;
    private javax.swing.JTextField rankID;
    private javax.swing.JTextField rankID2;
    private javax.swing.JTextField rankNameBox;
    private javax.swing.JTextField rankNameBox2;
    private javax.swing.JTable rankTable;
    private javax.swing.JTable rankTable2;
    private javax.swing.JButton solvedcaseb;
    private javax.swing.JButton solvedcaseb1;
    private javax.swing.JButton solvedcaseb2;
    private javax.swing.JButton solvedcaseb4;
    private javax.swing.JPanel titlebar;
    private javax.swing.JTextField trialCaseIDFindBox;
    private javax.swing.JLabel userName19;
    private javax.swing.JLabel userName20;
    private javax.swing.JLabel userName21;
    private javax.swing.JTextField victimAgeBox;
    private javax.swing.JTextField victimCaseIDBox;
    private javax.swing.JTextField victimCriminalIDBox;
    private javax.swing.JTextField victimFirstNameBox;
    private javax.swing.JTextField victimGDIDBox;
    private javax.swing.JComboBox victimGDInfoBox;
    private javax.swing.JComboBox victimGenderBox;
    private javax.swing.JTextField victimIDBox;
    private javax.swing.JTextField victimLastNameBox;
    private javax.swing.JTextField victimPermanentAdBox;
    private javax.swing.JTextField victimPhoneBox;
    private javax.swing.JTextField victimPresentAdBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void createGD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createGDCat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void GDEdit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   

   

   
   

    

}

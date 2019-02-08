/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearning;

import BLL.*;
import DAL.*;
import gui.models.*;
import java.awt.*;
import static java.awt.PageAttributes.MediaType.C;
import java.util.List;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Doruntina
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    InputStream input = null;
    FileOutputStream output = null;
    KursiTableModel ktm;
    StudentiKursiTableModel sktm;
    ProfesoriComboBoxModel pcb;
    MaterialiKursiInterface mkir = new MaterialiKursiRepository();
    KursiInterface kir = new KursiRepository();
    ProfesoriInterface pir = new ProfesoriRepository();
    KursiProfesoriInterface kpir = new KursiProfesoriRepository();
    StudentiKursiInterface skir = new StudentiKursiRepository();
    MaterialiInterface mir = new MaterialiRepository();
    LoginForm l = new LoginForm();
    Studenti st = new Studenti();
    Kursi k = new Kursi();

    public Main(Studenti s) {
        this.setExtendedState(Main.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        initComponents();
        st = s;
        tableLoadC();
        tableLoadRC();
        tableLoadYC();
        populateProfesoriComboBox(k);
        tabelaSelectedIndexChange();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        logoutBttn.setBackground(new Color(0, 0, 0, 0));
        dashboardBttn.setBackground(new Color(0, 0, 0, 0));
        coursesBttn.setBackground(new Color(0, 0, 0, 0));
        messagesBttn.setBackground(new Color(0, 0, 0, 0));
        calendarBttn.setBackground(new Color(0, 0, 0, 0));
        announcmentBttn.setBackground(new Color(0, 0, 0, 0));
        minimizeButton.setBackground(new Color(0, 0, 0, 0));
        enrollButton.setBackground(new Color(0, 0, 0, 0));
        startCourseButton.setBackground(new Color(0, 0, 0, 0));
        //tipiCB.setBackground(new Color(0, 0, 0, 0));
        emriMbiemri.setText(s.getEmri() + " " + s.getMbiemri());
        coursePanel.setVisible(false);
        courseLecturePanel.setVisible(false);
    }

    public void tableLoadRC() {
        try {
            List<Kursi> lista = kir.findAll();
            List<Kursi> listA = new java.util.ArrayList<>();
            Iterator<Kursi> it = lista.iterator();
            while (it.hasNext()) {
                Kursi k = it.next();
                if (k.getKategoria().equals(st.getInteresat())) {
                    listA.add(k);
                }
            }
            ktm = new KursiTableModel(listA);
            recommendedCourseTable.setModel(ktm);
            ktm.fireTableDataChanged();

        } catch (eLearningException ve) {
            JOptionPane.showMessageDialog(this, ve.getMessage());
        }
    }

    public void tableLoadC() {
        try {
            List<StudentiKursi> lista = skir.findAll();
            List<Kursi> listaA = new java.util.ArrayList<>();
            List<Kursi> listaB = kir.findAll();
            Iterator<StudentiKursi> it = lista.iterator();
            StudentiKursi sk = new StudentiKursi();
            while (it.hasNext()) {
                sk = it.next();
                if (sk.getStudentiID().equals(st)) {
                    listaA.add(sk.getKursiID());
                }
            }
            listaB.removeAll(listaA);
            ktm = new KursiTableModel(listaB);
            courseTable.setModel(ktm);
            ktm.fireTableDataChanged();

        } catch (eLearningException ve) {
            JOptionPane.showMessageDialog(this, ve.getMessage());
        }
    }

    public void tableLoadYC() {
        try {
            List<StudentiKursi> lista = skir.findAll();
            List<StudentiKursi> listA = new java.util.ArrayList<>();
            Iterator<StudentiKursi> it = lista.iterator();
            while (it.hasNext()) {
                StudentiKursi sk = it.next();
                if (sk.getStudentiID().equals(st)) {
                    listA.add(sk);
                }
            }
            sktm = new StudentiKursiTableModel(listA);
            myCourseTable.setModel(sktm);
            sktm.fireTableDataChanged();

        } catch (eLearningException ve) {
            JOptionPane.showMessageDialog(this, ve.getMessage());
        }
    }

    private void tabelaSelectedIndexChange() {
        tableLoadC();
        final ListSelectionModel rowSM = courseTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
                int selectedIndex = rowSM.getAnchorSelectionIndex();
                if (selectedIndex > -1) {
                    Kursi k = ktm.getKursi(selectedIndex);
                    populateProfesoriComboBox(k);
                }
            }
        });
    }

    public void populateProfesoriComboBox(Kursi k) {
        try {
            List<KursiProfesori> listakp = kpir.findAll();
            List<Profesori> listA = new java.util.ArrayList<>();
            Iterator<KursiProfesori> it = listakp.iterator();
            while (it.hasNext()) {
                KursiProfesori kp = it.next();
                if (kp.getKursiID().equals(k)) {
                    listA.add(kp.getProfesoriID());
                }
            }
            pcb = new ProfesoriComboBoxModel(listA);
            profesoriCB.setModel(pcb);
            profesoriCB.repaint();
        } catch (eLearningException se) {
            JOptionPane.showMessageDialog(this, se.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        minimizeButton = new javax.swing.JButton();
        logoutBttn = new javax.swing.JButton();
        sidePanel = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        emriMbiemri = new javax.swing.JLabel();
        dashboardBttn = new javax.swing.JButton();
        coursesBttn = new javax.swing.JButton();
        announcmentBttn = new javax.swing.JButton();
        calendarBttn = new javax.swing.JButton();
        messagesBttn = new javax.swing.JButton();
        dashboardPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recommendedCourseTable = new javax.swing.JTable();
        jCalendar = new com.toedter.calendar.JCalendar();
        jLabel2 = new javax.swing.JLabel();
        coursePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        myCourseTable = new javax.swing.JTable();
        enrollButton = new javax.swing.JButton();
        tipiCB = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        professor = new javax.swing.JLabel();
        startCourseButton = new javax.swing.JButton();
        profesoriCB = new javax.swing.JComboBox();
        courseLecturePanel = new javax.swing.JPanel();
        lesson1Button = new javax.swing.JButton();
        lesson2Button = new javax.swing.JButton();
        lesson3Button = new javax.swing.JButton();
        lesson4Button = new javax.swing.JButton();
        lesson5Button = new javax.swing.JButton();
        lesson6Button = new javax.swing.JButton();
        lesson7Button = new javax.swing.JButton();
        lesson8Button = new javax.swing.JButton();
        lesson9Button = new javax.swing.JButton();
        lesson10Button = new javax.swing.JButton();
        lesson11Button = new javax.swing.JButton();
        lesson12Button = new javax.swing.JButton();
        courseName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(39, 56, 86));
        headerPanel.setForeground(new java.awt.Color(39, 56, 86));

        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negative.png"))); // NOI18N
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });

        logoutBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout (1).png"))); // NOI18N
        logoutBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(1082, Short.MAX_VALUE)
                .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimizeButton)
                    .addComponent(logoutBttn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1180, 40));

        sidePanel.setBackground(new java.awt.Color(105, 202, 211));

        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        emriMbiemri.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        emriMbiemri.setForeground(new java.awt.Color(255, 255, 255));
        emriMbiemri.setText("Emri Mbiemri");

        dashboardBttn.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        dashboardBttn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        dashboardBttn.setText("Dashboard         ");
        dashboardBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBttnActionPerformed(evt);
            }
        });

        coursesBttn.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        coursesBttn.setForeground(new java.awt.Color(255, 255, 255));
        coursesBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/online-course.png"))); // NOI18N
        coursesBttn.setText("Courses             ");
        coursesBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesBttnActionPerformed(evt);
            }
        });

        announcmentBttn.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        announcmentBttn.setForeground(new java.awt.Color(255, 255, 255));
        announcmentBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/megaphone.png"))); // NOI18N
        announcmentBttn.setText("Announcement");
        announcmentBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                announcmentBttnActionPerformed(evt);
            }
        });

        calendarBttn.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        calendarBttn.setForeground(new java.awt.Color(255, 255, 255));
        calendarBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendar.png"))); // NOI18N
        calendarBttn.setText("Calendar            ");
        calendarBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarBttnActionPerformed(evt);
            }
        });

        messagesBttn.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        messagesBttn.setForeground(new java.awt.Color(255, 255, 255));
        messagesBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/envelope.png"))); // NOI18N
        messagesBttn.setText("Messages          ");
        messagesBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messagesBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coursesBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(announcmentBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendarBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messagesBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addComponent(emriMbiemri)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(69, 69, 69))))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(user)
                .addGap(18, 18, 18)
                .addComponent(emriMbiemri)
                .addGap(53, 53, 53)
                .addComponent(dashboardBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(coursesBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(announcmentBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(calendarBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(messagesBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(408, Short.MAX_VALUE))
        );

        mainPanel.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 770));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        recommendedCourseTable.setBackground(new java.awt.Color(240, 240, 240));
        recommendedCourseTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        recommendedCourseTable.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        recommendedCourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Course"
            }
        ));
        recommendedCourseTable.setRowHeight(25);
        jScrollPane1.setViewportView(recommendedCourseTable);

        jCalendar.setBackground(new java.awt.Color(255, 255, 255));
        jCalendar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCalendar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel2.setText("Recommended Courses");

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addContainerGap(251, Short.MAX_VALUE))
        );

        mainPanel.add(dashboardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1180, 720));

        myCourseTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        myCourseTable.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        myCourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Course"
            }
        ));
        myCourseTable.setRowHeight(25);
        jScrollPane2.setViewportView(myCourseTable);

        enrollButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_enroll.png"))); // NOI18N
        enrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollButtonActionPerformed(evt);
            }
        });

        tipiCB.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        tipiCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "‏‏‎", "Personal Interests", "Credit", "Diploma", "Certification" }));
        tipiCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipiCBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel1.setText("Why do you want to attend this course:");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel3.setText("My Courses");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel4.setText("Courses");

        courseTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        courseTable.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Course"
            }
        ));
        courseTable.setRowHeight(25);
        jScrollPane3.setViewportView(courseTable);

        professor.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        professor.setText("Professor:");

        startCourseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_start-course.png"))); // NOI18N
        startCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startCourseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout coursePanelLayout = new javax.swing.GroupLayout(coursePanel);
        coursePanel.setLayout(coursePanelLayout);
        coursePanelLayout.setHorizontalGroup(
            coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coursePanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(coursePanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(498, 498, 498)
                        .addComponent(jLabel4)
                        .addContainerGap(500, Short.MAX_VALUE))
                    .addGroup(coursePanelLayout.createSequentialGroup()
                        .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                        .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(professor)
                                .addComponent(tipiCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(profesoriCB, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
            .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, coursePanelLayout.createSequentialGroup()
                    .addContainerGap(631, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)))
        );
        coursePanelLayout.setVerticalGroup(
            coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coursePanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(coursePanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(startCourseButton))
                    .addGroup(coursePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipiCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(professor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profesoriCB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(248, Short.MAX_VALUE))
            .addGroup(coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(coursePanelLayout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(411, Short.MAX_VALUE)))
        );

        mainPanel.add(coursePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1180, 720));

        courseLecturePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lesson1Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson1Button.setText("Lesson 1");
        lesson1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson1ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson1Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 146, 620, 41));

        lesson2Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson2Button.setText("Lesson 2");
        lesson2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson2ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson2Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 193, 620, 41));

        lesson3Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson3Button.setText("Lesson 3");
        lesson3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson3ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson3Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 620, 41));

        lesson4Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson4Button.setText("Lesson 4");
        lesson4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson4ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson4Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 287, 620, 41));

        lesson5Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson5Button.setText("Lesson 5");
        lesson5Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson5ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson5Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 334, 620, 41));

        lesson6Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson6Button.setText("Lesson 6");
        lesson6Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson6ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson6Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 381, 620, 41));

        lesson7Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson7Button.setText("Lesson 7");
        lesson7Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson7ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson7Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 428, 620, 41));

        lesson8Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson8Button.setText("Lesson 8");
        lesson8Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson8ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson8Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 475, 620, 41));

        lesson9Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson9Button.setText("Lesson 9");
        lesson9Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson9ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson9Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 522, 620, 41));

        lesson10Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson10Button.setText("Lesson 10");
        lesson10Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson10ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson10Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 569, 620, 41));

        lesson11Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson11Button.setText("Lesson 11");
        lesson11Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson11ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson11Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 616, 620, 41));

        lesson12Button.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        lesson12Button.setText("Lesson 12");
        lesson12Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lesson12ButtonActionPerformed(evt);
            }
        });
        courseLecturePanel.add(lesson12Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 663, 620, 41));

        courseName.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        courseName.setText("jLabel13");
        courseLecturePanel.add(courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 61, -1, -1));

        mainPanel.add(courseLecturePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1180, 720));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBttnActionPerformed
        dashboardPanel.setVisible(true);
        coursePanel.setVisible(false);
        courseLecturePanel.setVisible(false);
    }//GEN-LAST:event_dashboardBttnActionPerformed

    private void coursesBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesBttnActionPerformed
        coursePanel.setVisible(true);
        dashboardPanel.setVisible(false);
        courseLecturePanel.setVisible(false);
    }//GEN-LAST:event_coursesBttnActionPerformed

    private void announcmentBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_announcmentBttnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_announcmentBttnActionPerformed

    private void calendarBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarBttnActionPerformed

    }//GEN-LAST:event_calendarBttnActionPerformed

    private void messagesBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messagesBttnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_messagesBttnActionPerformed

    private void logoutBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBttnActionPerformed
        Studenti s = new Studenti();
        Main m = new Main(s);
        m.setVisible(false);
        LoginForm lf = new LoginForm();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutBttnActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void enrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollButtonActionPerformed
        int row = courseTable.getSelectedRow();
        if (row != -1 && tipiCB.getSelectedIndex() != 0) {
            StudentiKursi sk = new StudentiKursi();
            Object[] ob = {"Yes", "No"};
            int result = JOptionPane.showOptionDialog(this, "Are you sure ?", "Enroll", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
            if (result == 0) {
                Kursi k = ktm.getKursi(row);
                sk.setStudentiID(st);
                sk.setKursiID(k);
                sk.setTipi(String.valueOf(tipiCB.getSelectedItem()));
                try {
                    skir.create(sk);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "You didn't select any course!");
            }
            tableLoadYC();
            tableLoadC();
        }
    }//GEN-LAST:event_enrollButtonActionPerformed

    private void tipiCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipiCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipiCBActionPerformed

    private void startCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startCourseButtonActionPerformed
        courseLecturePanel.setVisible(true);
        coursePanel.setVisible(false);
        dashboardPanel.setVisible(false);
        StudentiKursi sk = new StudentiKursi();
        int row = myCourseTable.getSelectedRow();
        if (row != -1) {
            sk = sktm.getStudentiKursi(row);
            k = sk.getKursiID();
            courseName.setText(k.getEmriKursit());
        }
    }//GEN-LAST:event_startCourseButtonActionPerformed

    private void lesson1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson1ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 1.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 1.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson1ButtonActionPerformed

    private void lesson2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson2ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 2.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 2.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson2ButtonActionPerformed

    private void lesson3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson3ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 3.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 3.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson3ButtonActionPerformed

    private void lesson4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson4ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 4.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 4.pdf")) {
                            File file = new File("/Users/User/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson4ButtonActionPerformed

    private void lesson5ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson5ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 5.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 5.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson5ButtonActionPerformed

    private void lesson6ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson6ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 6.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 6.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson6ButtonActionPerformed

    private void lesson7ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson7ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 7.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 7.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson7ButtonActionPerformed

    private void lesson8ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson8ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 8.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 8.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson8ButtonActionPerformed

    private void lesson9ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson9ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 9.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 9.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson9ButtonActionPerformed

    private void lesson10ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson10ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 10.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 10.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson10ButtonActionPerformed

    private void lesson11ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson11ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 11.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 11.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson11ButtonActionPerformed

    private void lesson12ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lesson12ButtonActionPerformed
        MaterialiKursi mk = new MaterialiKursi();
        Materiali m1 = new Materiali();
        try {
            List<MaterialiKursi> listamk = mkir.findAll();
            Iterator<MaterialiKursi> it = listamk.iterator();
            while (it.hasNext()) {
                mk = it.next();
                m1 = mk.getMaterialiID();
                if (k.equals(mk.getKursiID())) {
                    if (m1.getEmri().equals("Ligjerata 12.pdf")) {
                        File theFile = new File(m1.getEmri());
                        output = new FileOutputStream(theFile);
                        input = new ByteArrayInputStream(m1.getMaterialet());
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        if (m1.getEmri().equals("Ligjerata 12.pdf")) {
                            File file = new File("/Users/Gentiana/Documents/NetBeansProjects/eLearning/" + m1.getEmri());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }
                    }
                }
            }
        } catch (eLearningException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lesson12ButtonActionPerformed
    public void mouseEntered(MouseEvent event) {
        dashboardBttn.setBackground(new Color(1, 1, 1, 1));
    }

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Studenti s = new Studenti();
                new Main(s).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton announcmentBttn;
    private javax.swing.JButton calendarBttn;
    private javax.swing.JPanel courseLecturePanel;
    private javax.swing.JLabel courseName;
    private javax.swing.JPanel coursePanel;
    private javax.swing.JTable courseTable;
    private javax.swing.JButton coursesBttn;
    private javax.swing.JButton dashboardBttn;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JLabel emriMbiemri;
    private javax.swing.JButton enrollButton;
    private javax.swing.JPanel headerPanel;
    private com.toedter.calendar.JCalendar jCalendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton lesson10Button;
    private javax.swing.JButton lesson11Button;
    private javax.swing.JButton lesson12Button;
    private javax.swing.JButton lesson1Button;
    private javax.swing.JButton lesson2Button;
    private javax.swing.JButton lesson3Button;
    private javax.swing.JButton lesson4Button;
    private javax.swing.JButton lesson5Button;
    private javax.swing.JButton lesson6Button;
    private javax.swing.JButton lesson7Button;
    private javax.swing.JButton lesson8Button;
    private javax.swing.JButton lesson9Button;
    private javax.swing.JButton logoutBttn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton messagesBttn;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JTable myCourseTable;
    private javax.swing.JComboBox profesoriCB;
    private javax.swing.JLabel professor;
    private javax.swing.JTable recommendedCourseTable;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JButton startCourseButton;
    private javax.swing.JComboBox<String> tipiCB;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}

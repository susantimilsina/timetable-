/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import algorithm.ExistingClass;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import static timetable.Dashboard.existingClasses;
import static timetable.Dashboard.findIndex;
import static timetable.Dashboard.maximized;

/**
 *
 * @author acer
 */
public class TeacherDashboard extends javax.swing.JFrame {

    /**
     * Creates new form TeacherDashboard
     */
    int xMouse;
    int yMouse;
    public static ArrayList<ExistingClass> existingClasses = null;

    public TeacherDashboard(String username) {

        initComponents();
        welcomelbl.setText("Welcome "+username);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JTable tbl = new JTable();
        try {
            ExistingClass ec = null;
            DB_Connect dc = new DB_Connect();
            dc.connectDatabase();
            Statement s = dc.conn.createStatement();
            ResultSet rs = null;
            String query = "SELECT s.classNumber,ct.startTime,ct.endTime,s.professorUsername,sub.faculty,sub.semester,sub.name FROM schedule s join classtime ct on s.classTimeID = ct.timeID join subject sub on s.subjectCode = sub.code where s.professorUsername='" + username + "';";
            rs = s.executeQuery(query);
            if (rs.isBeforeFirst()) {
                existingClasses = new ArrayList<>();
                System.out.println("hello");
                while (rs.next()) {
                    ec = new ExistingClass(Integer.parseInt(rs.getString("classNumber")),
                            rs.getString("startTime") + "-" + rs.getString("endTime"),
                            rs.getString("faculty") + "-" + rs.getString("semester"),
                            rs.getString("professorUsername"), rs.getString("name"));
                    System.out.println(ec.toString());
                    existingClasses.add(ec);
                }

            } else {
                existingClasses = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (existingClasses == null) {
            schedulePnl.removeAll();
            schedulePnl.repaint();
            schedulePnl.revalidate();
            schedulePnl.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 80));
            JLabel lbl = new JLabel("No classes yet");

            lbl.setFont(new Font(Font.SERIF,Font.BOLD, 40));
            lbl.setForeground(Color.red);
            schedulePnl.add(lbl);
            schedulePnl.repaint();
            schedulePnl.revalidate();
        } else {
                        schedulePnl.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 200));
            ArrayList<String> timeslots = new ArrayList<String>();
            for (ExistingClass ec : existingClasses) {
                if (timeslots.indexOf(ec.getTimeslot()) == -1) {
                    timeslots.add(ec.getTimeslot());
                }
            }
            Collections.sort(timeslots);
//            ArrayList<String> groups = new ArrayList<String>();
//            for (ExistingClass ec : existingClasses) {
//                if (groups.indexOf(ec.getGroup()) == -1) {
//                    groups.add(ec.getGroup());
//                }
//            }
//            Collections.sort(groups);
            String[] columnNames = new String[timeslots.size()];
            columnNames = timeslots.toArray(columnNames);

            String[] columnNamesFinal = new String[columnNames.length + 1];
            columnNamesFinal[0] = "Week Day";
            String tabletitle = "";
            for (int i = 0; i < columnNames.length; i++) {
                columnNamesFinal[i + 1] = columnNames[i];
            }

            String[][] data = new String[6][columnNamesFinal.length];

            for (ExistingClass ec : existingClasses) {
                // System.out.println(findIndex(columnNames, timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot()));
                data[0][findIndex(columnNamesFinal, ec.getTimeslot())] = ec.getSubjectName()
                        + "(" + ec.getGroup() + "," + ec.getClassNumber() + ")";
                data[1][findIndex(columnNamesFinal, ec.getTimeslot())] = ec.getSubjectName()
                        + "(" + ec.getGroup() + "," + ec.getClassNumber() + ")";
                data[2][findIndex(columnNamesFinal, ec.getTimeslot())] = ec.getSubjectName()
                        + "(" + ec.getGroup() + "," + ec.getClassNumber() + ")";
                data[3][findIndex(columnNamesFinal, ec.getTimeslot())] = ec.getSubjectName()
                        + "(" + ec.getGroup() + "," + ec.getClassNumber() + ")";
                data[4][findIndex(columnNamesFinal, ec.getTimeslot())] = ec.getSubjectName()
                        + "(" + ec.getGroup() + "," + ec.getClassNumber() + ")";
                data[5][findIndex(columnNamesFinal, ec.getTimeslot())] = ec.getSubjectName()
                        + "(" + ec.getGroup() + "," + ec.getClassNumber() + ")";

                tabletitle = ec.getProfessor();

            }
            data[0][0] = "Sunday";
            data[1][0] = "Monday";
            data[2][0] = "Tuesday";
            data[3][0] = "Wednesday";
            data[4][0] = "Thrusday";
            data[5][0] = "Friday";

            tbl = new JTable(data, columnNamesFinal);
            // tbl.setPreferredSize(new Dimension(1000, 500));
            tbl.setSize(1600, 170);
            tbl.setPreferredSize(new Dimension(1600, 170));
            JScrollPane scrollPane = new JScrollPane(tbl);
            scrollPane.setSize(1600, 170);
            scrollPane.setPreferredSize(new Dimension(1600, 170));
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(BorderFactory.createTitledBorder(tabletitle));
            schedulePnl.add(scrollPane);
            schedulePnl.repaint();
            schedulePnl.revalidate();

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

        pnlHeader = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnMaximize = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        logoutlbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        welcomelbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        schedulePnl = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1644, 1200));

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setForeground(new java.awt.Color(255, 255, 255));
        pnlHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeaderMouseDragged(evt);
            }
        });
        pnlHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeaderMousePressed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Exit.png"))); // NOI18N
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusable(false);
        btnExit.setOpaque(true);
        btnExit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Exit (2).png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnMaximize.setBackground(new java.awt.Color(255, 255, 255));
        btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Maximize.png"))); // NOI18N
        btnMaximize.setContentAreaFilled(false);
        btnMaximize.setFocusable(false);
        btnMaximize.setOpaque(true);
        btnMaximize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Maximize (2).png"))); // NOI18N
        btnMaximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMaximizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMaximizeMouseExited(evt);
            }
        });
        btnMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizeActionPerformed(evt);
            }
        });

        btnMinimize.setBackground(new java.awt.Color(255, 255, 255));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Minimize.png"))); // NOI18N
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setFocusable(false);
        btnMinimize.setOpaque(true);
        btnMinimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Minimize (2).png"))); // NOI18N
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseExited(evt);
            }
        });
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/smallLogo.png"))); // NOI18N
        jLabel19.setText("Time-Table");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnMaximize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnExit))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMaximize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMinimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/logo.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("You are login as Professor ");

        logoutlbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logoutlbl.setForeground(new java.awt.Color(51, 51, 51));
        logoutlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        logoutlbl.setText("Logout ?");
        logoutlbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutlbl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                logoutlblFocusGained(evt);
            }
        });
        logoutlbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutlblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutlblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutlblMouseExited(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Time-Table Generation System");

        welcomelbl.setForeground(new java.awt.Color(255, 255, 255));
        welcomelbl.setText("Welcome Username");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcomelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(logoutlbl)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(logoutlbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(welcomelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setMaximumSize(new java.awt.Dimension(1919, 1210));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1919, 1210));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1919, 1210));

        schedulePnl.setMaximumSize(new java.awt.Dimension(1644, 1200));
        schedulePnl.setMinimumSize(new java.awt.Dimension(1644, 1200));

        javax.swing.GroupLayout schedulePnlLayout = new javax.swing.GroupLayout(schedulePnl);
        schedulePnl.setLayout(schedulePnlLayout);
        schedulePnlLayout.setHorizontalGroup(
            schedulePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1917, Short.MAX_VALUE)
        );
        schedulePnlLayout.setVerticalGroup(
            schedulePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1208, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(schedulePnl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(232, 17, 35));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnMaximizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseEntered
        btnMaximize.setBackground(new Color(229, 229, 229));
    }//GEN-LAST:event_btnMaximizeMouseEntered

    private void btnMaximizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseExited
        btnMaximize.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMaximizeMouseExited

    private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
        if (maximized) {
            //handle fullscreen - taskbar
            TeacherDashboard.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            TeacherDashboard.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }//GEN-LAST:event_btnMaximizeActionPerformed

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setBackground(new Color(229, 229, 229));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void pnlHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMouseDragged
        if (maximized) {
            int x = evt.getXOnScreen();
            int y = evt.getYOnScreen();

            this.setLocation(x - xMouse, y - yMouse);
        }
    }//GEN-LAST:event_pnlHeaderMouseDragged

    private void pnlHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnlHeaderMousePressed

    private void logoutlblFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_logoutlblFocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_logoutlblFocusGained

    private void logoutlblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutlblMouseClicked
        login l = new login();
        l.setVisible(true);
        this.hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutlblMouseClicked

    private void logoutlblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutlblMouseEntered
        logoutlbl.setForeground(Color.white);        // TODO add your handling code here:
    }//GEN-LAST:event_logoutlblMouseEntered

    private void logoutlblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutlblMouseExited
        logoutlbl.setForeground(new Color(51, 51, 51));        // TODO add your handling code here:
    }//GEN-LAST:event_logoutlblMouseExited

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logoutlbl;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel schedulePnl;
    private javax.swing.JLabel welcomelbl;
    // End of variables declaration//GEN-END:variables
}

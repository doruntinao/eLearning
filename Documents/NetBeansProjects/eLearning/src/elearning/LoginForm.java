/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearning;

import BLL.Login;
import BLL.Studenti;
import DAL.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Doruntina
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LogIn
     */
    LoginRepository li;
    Login l = new Login();
    Studenti s = new Studenti();

    public LoginForm() {
        this.setUndecorated(true);
        initComponents();
        li = new LoginRepository();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        emailTxt.setBackground(new Color(0, 0, 0, 0));
        passwordTxt.setBackground(new Color(0, 0, 0, 0));
        closeButton.setBackground(new Color(0, 0, 0, 0));
        minimizeButton.setBackground(new Color(0, 0, 0, 0));
        LoginBttn.setBackground(new Color(0, 0, 0, 0));
        signupButton.setBackground(new Color(0, 0, 0, 0));

        passwordTxt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                passwordTxt.setText("");
            }
        });
        emailTxt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (emailTxt.getText().equals("Enter Email")) {
                    emailTxt.setText("");
                }
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();
        signupButton = new javax.swing.JButton();
        LoginBttn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error (1).png"))); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jPanel1.add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 30, -1));

        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negative.png"))); // NOI18N
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });
        jPanel1.add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 30, -1));

        signupButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        signupButton.setForeground(new java.awt.Color(255, 255, 255));
        signupButton.setText("or Sign Up");
        signupButton.setOpaque(false);
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });
        jPanel1.add(signupButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 120, 50));

        LoginBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_login (3).png"))); // NOI18N
        LoginBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBttnActionPerformed(evt);
            }
        });
        jPanel1.add(LoginBttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 150, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Email");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 320, 30));

        emailTxt.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        emailTxt.setForeground(new java.awt.Color(102, 102, 102));
        emailTxt.setText("Enter Email");
        emailTxt.setBorder(null);
        emailTxt.setOpaque(false);
        emailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxtActionPerformed(evt);
            }
        });
        jPanel1.add(emailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 370, 30));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Password");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 320, 30));

        passwordTxt.setForeground(new java.awt.Color(102, 102, 102));
        passwordTxt.setText("jPasswordField1");
        passwordTxt.setBorder(null);
        passwordTxt.setOpaque(false);
        passwordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxtActionPerformed(evt);
            }
        });
        jPanel1.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 380, 30));

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 510, 450));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/e-learning-vector (1).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 510, 450));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed
//Verifikimi per email dhe password
    private void LoginBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBttnActionPerformed

        if (emailTxt.getText().equals("") && passwordTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Username ose Password i zbrazet!!");
        } else if (!emailTxt.getText().equals("") && !passwordTxt.getText().equals("")) {
            try {
                l = li.findByEmail(emailTxt.getText());
                s = l.getStudentID();
                if (l != null && l.getPassword().equals(passwordTxt.getText())) {
                    LoginForm lf = new LoginForm();
                    lf.setVisible(false);
                    Main j = new Main(s);
                    j.setVisible(true);
                    this.dispose();
                }
                if (!((l.getUsername().equals(emailTxt.getText())) || l.getPassword().equals(passwordTxt.getText()))) {
                    JOptionPane.showMessageDialog(this, "Email apo Password gabim!");
                }
            } catch (eLearningException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_LoginBttnActionPerformed

    private void emailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxtActionPerformed

    }//GEN-LAST:event_emailTxtActionPerformed

    private void passwordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtActionPerformed

    }//GEN-LAST:event_passwordTxtActionPerformed
//Hapja e formes SignUp
    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        LoginForm lf = new LoginForm();
        lf.setVisible(false);
        SignUpForm s = new SignUpForm();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_signupButtonActionPerformed
//Minimizimi i formes
    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginBttn;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JButton signupButton;
    // End of variables declaration//GEN-END:variables
}

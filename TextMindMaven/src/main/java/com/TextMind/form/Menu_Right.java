/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.form;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.event.PublicEvent;
import com.TextMind.component.Change_Password;
import com.TextMind.event.EventMenuRight;
import com.TextMind.main.Login;
import com.TextMind.main.main;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author KHOA
 */
public class Menu_Right extends javax.swing.JPanel {

    /**
     * Creates new form Menu_Left
     */
    public Menu_Right() {
        initComponents();
        init();
        
        menuRight.setLayout(new MigLayout("wrap", "[center]", "60[]35[]10[]45[]45[]10[]push"));
        
        btnChangePass.setText("Change Password");
        btnChangePass.setBackground(new Color(179, 233, 255));
        btnChangePass.setForeground(new Color(0,0,0));
        menuRight.add(btnChangePass);
        btnSignOut.setText("Sign Out");
        btnSignOut.setBackground(new Color(179, 233, 255));
        btnSignOut.setForeground(new Color(0,0,0));
        menuRight.add(btnSignOut);
    }

    private void init() {
        txtName.setText(Auth.user.getName());
        initEvent();
    }

    private void signOut() {
        getSocket().emit("signOutStatus", Auth.user.getuID());

        PublicEvent.getInstance().getEventMain().signOut();
        PublicEvent.getInstance().getEventLogin().reLogin();
    }
    
    private void initEvent() {
        PublicEvent.getInstance().addEventMenuRight(new EventMenuRight() {
            @Override
            public void logOut() {
                signOut();
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

        menuRight = new com.TextMind.Helper.GradientPanelB();
        btnSignOut = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();
        txtName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));

        btnSignOut.setText("Đăng xuất");
        btnSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignOutActionPerformed(evt);
            }
        });

        btnChangePass.setText("Đổi mật khẩu");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        txtName.setBackground(new java.awt.Color(51, 51, 51));
        txtName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtName.setForeground(new java.awt.Color(51, 51, 51));
        txtName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtName.setText("Menu_Right_Note");

        javax.swing.GroupLayout menuRightLayout = new javax.swing.GroupLayout(menuRight);
        menuRight.setLayout(menuRightLayout);
        menuRightLayout.setHorizontalGroup(
            menuRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuRightLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(menuRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        menuRightLayout.setVerticalGroup(
            menuRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuRightLayout.createSequentialGroup()
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 445, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignOutActionPerformed
        signOut();
    }//GEN-LAST:event_btnSignOutActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        Change_Password cp = new Change_Password(main.getFrames()[0], true);
        cp.setVisible(true);
    }//GEN-LAST:event_btnChangePassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnSignOut;
    private com.TextMind.Helper.GradientPanelB menuRight;
    private javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}

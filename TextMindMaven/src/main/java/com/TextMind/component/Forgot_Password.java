/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.TextMind.component;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.swing.MyPasswordField;
import com.TextMind.swing.MyTextField;
import io.socket.emitter.Emitter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author khang
 */
public class Forgot_Password extends javax.swing.JDialog {

    MyTextField txtEmail = new MyTextField();
    MyTextField txtConfirm = new MyTextField();
    private String code;
    private String validateMail;
    JLabel lblError = new JLabel("JoptionPane Error", SwingConstants.CENTER);
    JButton btnSubmit = new JButton("SUBMIT") ;
    JButton btnResend = new JButton("RESEND") ;

    private int pX;
    private int pY;

    private int countdown = 30; 

    /**
     * Creates new form ReportUser
     */
    public Forgot_Password(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initForm();
        init();
    }

    private void init() {
        setLocationRelativeTo(null);
        forgotPass.setVisible(true);
        tick.setVisible(true);
    }

    public void initForm() {
        title.setText("FORGOT PASSWORD");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(204, 255, 255));
        title.setBackground(new Color(51, 153, 255));        
        forgotPass.setLayout(new MigLayout("wrap", "push[center]push", "0[]15[]10[]10[]10[]10[]50[]30[]push"));
//        forgotPass.setLayout(new MigLayout());
        forgotPass.add(title2);
        
        lblError.setFont(new Font("sansserif", 1, 12));
        lblError.setForeground(new Color(255,0,0));
        lblError.setBackground(new Color(51, 153, 255));
        lblError.setVisible(false);
        
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        txtEmail.setHint("Email");
        

        txtConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        txtConfirm.setHint("Confirm code");
        
        btnSubmit.setBackground(new Color(0, 102, 204));
        btnSubmit.setForeground(new Color(250, 250, 250));
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                try {
                    checkCode();
                } catch (JSONException ex) {
                    Logger.getLogger(Forgot_Password.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        btnResend.setBackground(new Color(0, 102, 204));
        btnResend.setForeground(new Color(250, 250, 250));
        btnResend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnResend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                


                try {
                    // Your code to send the verify code
                    reSendCode(validateMail);
                } catch (JSONException ex) {
                    Logger.getLogger(Forgot_Password.class.getName()).log(Level.SEVERE, null, ex);
                }
                        btnResend.setEnabled(false);

                        // Start the countdown
                        startCountdown();

            }
        });

        btnSend.setBackground(new Color(0, 102, 204));
        btnSend.setForeground(new Color(250, 250, 250));
        btnSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnClose.setBackground(new Color(0, 102, 204));
        btnClose.setForeground(new Color(250, 250, 250));
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              
        forgotPass.add(txtEmail, "w 90%");
//        forgotPass.add(txtConfirm, "w 90%");

        forgotPass.add(lblError, "w 90%");
        forgotPass.add(btnSend, "w 40%, h 20");
//        forgotPass.add(btnSubmit, "w 40%, h 20");

        forgotPass.add(btnClose, "w 40%, h 20");     
        
    }
    
    
    private void sendVerifyCode() throws JSONException {
        String email = txtEmail.getText().trim();
        if(!EmailValidator.getInstance().isValid(email) || email.isBlank()) {
            lblError.setText("Mail is wrong format or being blank");
            txtEmail.grabFocus();
            return;
        }
        else{
            JSONObject data = new JSONObject();
            String randomString = RandomStringUtils.randomAlphanumeric(6);
            data.put("email", email);
            data.put("random", randomString);
            data.put("type", "forget");
            getSocket().emit("getValicateEmail", data);
            getSocket().once("verificationCodeSent"+randomString, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String jsonString = args[0].toString();
                try {                  

                        JSONObject jsonObject = new JSONObject(jsonString);
                        
                        String mailCode = jsonObject.optString("code");
                        String mailOfCode = jsonObject.optString("mailOfThis");
                        validateMail = mailOfCode;
                        code = mailCode;
                    }
                catch (Exception e) {
                    System.out.println(e);
                }
                }
            });
        
        }
    }
    
    private void reSendCode(String email) throws JSONException{
            JSONObject data = new JSONObject();
            String randomString = RandomStringUtils.randomAlphanumeric(6);
            data.put("email", email);
            data.put("random", randomString);
            data.put("type", "forget");
            getSocket().emit("getValicateEmail", data);
            getSocket().once("verificationCodeSent"+randomString, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String jsonString = args[0].toString();
                try {                  

                        JSONObject jsonObject = new JSONObject(jsonString);
                        
                        String mailCode = jsonObject.optString("code");
                        String mailOfCode = jsonObject.optString("mailOfThis");
                        validateMail = mailOfCode;
                        code = mailCode;
                    }
                catch (Exception e) {
                    System.out.println(e);
                }
                }
            });
    }
    
    private void startCountdown() {
        SwingWorker<Void, Void> countdownWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (countdown > 0) {
                    // Update the button text with the countdown message
                    SwingUtilities.invokeLater(() -> {
                        btnResend.setText("Wait for " + countdown + "s to resend");
                        btnResend.setForeground(Color.BLACK);
                    });

                    Thread.sleep(1000); // Sleep for 1 second
                    countdown--;
                }
                return null;
            }

            @Override
            protected void done() {
                // Re-enable the button and set the default text
                btnResend.setEnabled(true);
                btnResend.setText("Send Verify Code");
                countdown = 30; // Reset countdown
            }
        };

        countdownWorker.execute();
    }
    
    private void checkCode() throws JSONException{
        String codeTyped = txtConfirm.getText().trim();
        if(!codeTyped.equals(code)){
            lblError.setText("Verify Code wrong");
            lblError.setVisible(true);
            return ;
        }
        
        JSONObject data = new JSONObject();
        String randomString = RandomStringUtils.randomAlphanumeric(6);
        data.put("email", validateMail);
        data.put("random", randomString);
            
        getSocket().emit("sendUserAndPass", data);
        
        getSocket().once("successGetUserPass"+randomString, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                String jsonString = args[0].toString();
                Boolean isSuccess = Boolean.parseBoolean(jsonString);

                if (isSuccess) {
                    lblError.setText("Success, please check your email");
                    lblError.setVisible(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            tick.setVisible(true);
                            try {
                                Thread.sleep(2000); 
                            } catch (InterruptedException e) {
                            } 
                            btnCloseActionPerformed(null);

                        }
                    }).start();
                } else {
                    lblError.setText("No user found with the provided email");
                    lblError.setVisible(true);
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        forgotPass = new com.TextMind.Helper.GradientPanel();
        title2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        tick = new com.TextMind.form.Tick();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        title2.setBackground(new java.awt.Color(51, 153, 255));
        title2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                title2MouseDragged(evt);
            }
        });
        title2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                title2MousePressed(evt);
            }
        });

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout title2Layout = new javax.swing.GroupLayout(title2);
        title2.setLayout(title2Layout);
        title2Layout.setHorizontalGroup(
            title2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title2Layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );
        title2Layout.setVerticalGroup(
            title2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnSend.setText("SEND CODE");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout forgotPassLayout = new javax.swing.GroupLayout(forgotPass);
        forgotPass.setLayout(forgotPassLayout);
        forgotPassLayout.setHorizontalGroup(
            forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(forgotPassLayout.createSequentialGroup()
                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, forgotPassLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSend)
                .addGap(108, 108, 108)
                .addComponent(btnClose)
                .addGap(30, 30, 30))
        );
        forgotPassLayout.setVerticalGroup(
            forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(forgotPassLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addGroup(forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(btnClose))
                .addGap(21, 21, 21))
        );

        jLayeredPane1.add(forgotPass, "card2");
        jLayeredPane1.add(tick, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void title2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_title2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - pX, this.getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_title2MouseDragged

    private void title2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_title2MousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_title2MousePressed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String email = txtEmail.getText().trim();
                if(EmailValidator.getInstance().isValid(email) && !email.isBlank()) {
                    try {
                        // Your code to send the verify code
                        sendVerifyCode();
                        lblError.setVisible(false);
                        // Start the countdown
                    } catch (Exception ex) {
                        System.out.println(ex);
                        return;
                    }
                }
                else{
                    lblError.setText("Mail is wrong format or being blank");
                    lblError.setVisible(true);
                    txtEmail.grabFocus();
                    return;
                }
        forgotPass.remove(txtEmail);
        forgotPass.remove(btnSend);
        forgotPass.remove(lblError);
        forgotPass.remove(btnClose);
        forgotPass.revalidate();
        forgotPass.repaint();
        forgotPass.add(txtConfirm, "w 90%");
        forgotPass.add(lblError, "w 90%");
        
        forgotPass.add(btnSubmit, "w 40%, h 20");   
        forgotPass.add(btnResend, "w 40%, h 20"); 
        forgotPass.add(btnClose, "w 40%, h 20");
        
    }//GEN-LAST:event_btnSendActionPerformed

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
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Forgot_Password dialog = new Forgot_Password(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSend;
    private com.TextMind.Helper.GradientPanel forgotPass;
    private javax.swing.JLayeredPane jLayeredPane1;
    private com.TextMind.form.Tick tick;
    private javax.swing.JLabel title;
    private javax.swing.JPanel title2;
    // End of variables declaration//GEN-END:variables
}

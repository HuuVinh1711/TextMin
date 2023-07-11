/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.form;

import com.TextMind.component.Item_People;
import com.TextMind.swing.ScrollBar;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author KHOA
 */
public class Menu_Left extends javax.swing.JPanel {

    /**
     * Creates new form Menu_Left
     */
    public Menu_Left() {
        initComponents();
        init() ;
    }
    
    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        showMess();
    }

    private void showMess() {
        menuList.removeAll();
        for (int i = 0; i < 20; i++) {
            menuList.add(new Item_People("People " + i), "wrap");
        }
        refreshMenuList();
    }

    private void showGroup() {
        menuList.removeAll();
        for (int i = 0; i < 15; i++) {
            menuList.add(new Item_People("Group " + i), "wrap");
        }
        refreshMenuList();
    }

    private void showBox() {
        menuList.removeAll();
        for (int i = 0; i < 10; i++) {
            menuList.add(new Item_People("Box " + i), "wrap");
        }
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMess = new com.TextMind.component.MenuButton();
        menuGroup = new com.TextMind.component.MenuButton();
        menuBox = new com.TextMind.component.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMess.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/mess.png"))); // NOI18N
        menuMess.setSelected(true);
        menuMess.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/messSelected.png"))); // NOI18N
        menuMess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessActionPerformed(evt);
            }
        });
        menu.add(menuMess);

        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group.png"))); // NOI18N
        menuGroup.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/group.png"))); // NOI18N
        menuGroup.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/groupSelected.png"))); // NOI18N
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuGroup);

        menuBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/letter.png"))); // NOI18N
        menuBox.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/letter.png"))); // NOI18N
        menuBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/letterSelected.png"))); // NOI18N
        menuBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoxActionPerformed(evt);
            }
        });
        menu.add(menuBox);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 159, Short.MAX_VALUE)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessActionPerformed
        if (!menuMess.isSelected()) {
            menuMess.setSelected(true);
            menuGroup.setSelected(false);
            menuBox.setSelected(false);
            showMess();
        }
    }//GEN-LAST:event_menuMessActionPerformed

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
        if (!menuGroup.isSelected()) {
            menuMess.setSelected(false);
            menuGroup.setSelected(true);
            menuBox.setSelected(false);
            showGroup();
        }
    }//GEN-LAST:event_menuGroupActionPerformed

    private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoxActionPerformed
        if (!menuBox.isSelected()) {
            menuMess.setSelected(false);
            menuGroup.setSelected(false);
            menuBox.setSelected(true);
            showBox();
        }
    }//GEN-LAST:event_menuBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private com.TextMind.component.MenuButton menuBox;
    private com.TextMind.component.MenuButton menuGroup;
    private javax.swing.JLayeredPane menuList;
    private com.TextMind.component.MenuButton menuMess;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.irods.jargon.idrop.desktop.systraygui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.irods.jargon.conveyor.core.ConveyorBusyException;
import org.irods.jargon.conveyor.core.ConveyorExecutionException;
import org.irods.jargon.transfer.exception.PassPhraseInvalidException;

/**
 *
 * @author lisa
 */
public class ChangePassPhraseDialog extends javax.swing.JDialog {

    private final IDROPCore idropCore;
    /**
     * Creates new form ChangePassPhraseDialog
     */
    public ChangePassPhraseDialog(java.awt.Frame parent, boolean modal, final IDROPCore idropCore) {
        super(parent, modal);
        initComponents();
        this.idropCore = idropCore;
        
        this.getRootPane().setDefaultButton(btnOkay);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPassPhrase = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtNewPassPhrase = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtVerifyNewPassPhrase = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnOkay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(450, 220));

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel2.add(jLabel1, gridBagConstraints);

        txtPassPhrase.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.txtPassPhrase.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(txtPassPhrase, gridBagConstraints);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel2.add(jLabel2, gridBagConstraints);
        jLabel2.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.jLabel2.AccessibleContext.accessibleName")); // NOI18N

        txtNewPassPhrase.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.txtNewPassPhrase.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(txtNewPassPhrase, gridBagConstraints);

        jLabel3.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel2.add(jLabel3, gridBagConstraints);

        txtVerifyNewPassPhrase.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.txtVerifyNewPassPhrase.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(txtVerifyNewPassPhrase, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 14, 8, 14));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanel4, java.awt.BorderLayout.WEST);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.setLayout(new java.awt.BorderLayout());

        btnCancel.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel5.add(btnCancel, java.awt.BorderLayout.WEST);

        btnOkay.setText(org.openide.util.NbBundle.getMessage(ChangePassPhraseDialog.class, "ChangePassPhraseDialog.btnOkay.text")); // NOI18N
        btnOkay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkayActionPerformed(evt);
            }
        });
        jPanel5.add(btnOkay, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel5, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOkayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkayActionPerformed

        String currentPassPhrase = txtPassPhrase.getText();
        String newPassPhrase = txtNewPassPhrase.getText();
        String confirmNewPassPhrase = txtVerifyNewPassPhrase.getText();

        // make sure pass phrase is entered

        if ((currentPassPhrase == null) || (currentPassPhrase.length() <= 0)) {
            JOptionPane.showMessageDialog(
                this, "Please enter the current pass phrase.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if ((newPassPhrase == null) || (newPassPhrase.length() <= 0)) {
            JOptionPane.showMessageDialog(
                this, "Please enter a new pass phrase.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if ((confirmNewPassPhrase == null) || (confirmNewPassPhrase.length() <= 0)) {
            JOptionPane.showMessageDialog(
                this, "Please confirm the new pass phrase.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // check to make sure current pass phrase is valid
        try {
            idropCore.getConveyorService().validatePassPhrase(currentPassPhrase);
        } catch (PassPhraseInvalidException ex) {
            Logger.getLogger(InitialPassPhraseDialog.class.getName()).log(
                Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(
                this, "Current pass phrase is invalid. Please try again.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (ConveyorExecutionException ex) {
            Logger.getLogger(InitialPassPhraseDialog.class.getName()).log(
                Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(
                this, "Update of pass phrase failed.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // check to make sure pass phrases match
        if (! newPassPhrase.equals(confirmNewPassPhrase)) {
            JOptionPane.showMessageDialog(
                this, "Pass phrases do not match. Please try again.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // now update with new pass phrase
            idropCore.getConveyorService().getGridAccountService().changePassPhraseWhenAlreadyValidated(newPassPhrase);
        } catch (ConveyorBusyException ex) {
            Logger.getLogger(InitialPassPhraseDialog.class.getName()).log(
                Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(
                this, "Update of pass phrase failed.\nTransfer for this grid account is currently in progess.\nPlease try again later.",
                "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
        } catch (PassPhraseInvalidException ex) {
            Logger.getLogger(InitialPassPhraseDialog.class.getName()).log(
                Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(
                this, "Update of pass phrase failed. Pass phrase is invalid.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
        } catch (ConveyorExecutionException ex) {
            Logger.getLogger(InitialPassPhraseDialog.class.getName()).log(
                Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(
                this, "Update of pass phrase failed.", "Change Pass Phrase", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(
            this, "Pass phrase updated successfully.", "Change Pass Phrase", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();
    }//GEN-LAST:event_btnOkayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOkay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField txtNewPassPhrase;
    private javax.swing.JPasswordField txtPassPhrase;
    private javax.swing.JPasswordField txtVerifyNewPassPhrase;
    // End of variables declaration//GEN-END:variables
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SetupWizard.java
 *
 * Created on Jul 12, 2011, 5:54:17 PM
 */
package org.irods.jargon.idrop.desktop.systraygui.viscomponents;

import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.irods.jargon.idrop.desktop.systraygui.IDROPCore;
import org.irods.jargon.idrop.desktop.systraygui.MessageManager;
import org.irods.jargon.idrop.desktop.systraygui.iDrop;
import org.irods.jargon.idrop.desktop.systraygui.services.IdropConfigurationService;
import org.irods.jargon.idrop.exceptions.IdropException;
import org.irods.jargon.idrop.exceptions.IdropRuntimeException;
import org.openide.util.Exceptions;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mikeconway
 */
public class SetupWizard extends javax.swing.JDialog {

    private final IDROPCore idropCore;
    private final IdropConfigurationService idropConfigurationService;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SetupWizard.class);
    public final String SETUP_ERROR_TITLE = "iDrop - Setup Error";
    private int tabStep = 0;
    private boolean tabAdvancing = false;

    /** Creates new form SetupWizard */
    public SetupWizard(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        super.setLocationRelativeTo(parent);
        initComponents();
        iDrop idrop = (iDrop) parent;
        idropCore = idrop.getiDropCore();
        idropConfigurationService = idropCore.getIdropConfigurationService();
        tabWizardTabs.addChangeListener(new ChangeListener() {

            /*
             * Quash a manual tab move, can only be done via the 'wizard' 
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!tabAdvancing) {
                    tabWizardTabs.setSelectedIndex(tabStep);
                }
                tabAdvancing = false;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelTop = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        tabWizardTabs = new javax.swing.JTabbedPane();
        panelTabSeeSysTray = new javax.swing.JPanel();
        panelTabSeeSysTrayQuestion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSeeIcon = new javax.swing.JTextArea();
        panelTabSeeSysTrayAnswer = new javax.swing.JPanel();
        btnSeeSystemTrayYes = new javax.swing.JButton();
        btnSeeSystemTrayNo = new javax.swing.JButton();
        panelTabNameDevice = new javax.swing.JPanel();
        panelTabNameDeviceQuestion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSeeIcon1 = new javax.swing.JTextArea();
        panelTabNameDeviceAnswer = new javax.swing.JPanel();
        lblDeviceName = new javax.swing.JLabel();
        txtDeviceName = new javax.swing.JTextField();
        btnSetDeviceName = new javax.swing.JButton();
        pnlInitialSynchSetup = new javax.swing.JPanel();
        pnlInitialSynchSetupQuestion = new javax.swing.JPanel();
        scrollInitialSynchSetupQuestion = new javax.swing.JScrollPane();
        txtInitialSynchSetupQuestion = new javax.swing.JTextArea();
        pnlInitialSynchSetupAnswer = new javax.swing.JPanel();
        pnlLocalSynch = new javax.swing.JPanel();
        btnChooseLocalSynch = new javax.swing.JButton();
        pnlSynchMode = new javax.swing.JPanel();
        pnlIrodsSynch = new javax.swing.JPanel();
        btnChooseIrodsSynch = new javax.swing.JButton();
        pnlWizardToolbar = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnLater = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelTop.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N

        lblWelcome.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        lblWelcome.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.lblWelcome.text")); // NOI18N
        lblWelcome.setMaximumSize(null);
        lblWelcome.setMinimumSize(null);
        lblWelcome.setPreferredSize(null);
        panelTop.add(lblWelcome);

        getContentPane().add(panelTop, java.awt.BorderLayout.NORTH);

        tabWizardTabs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabWizardTabsKeyPressed(evt);
            }
        });

        panelTabSeeSysTray.setLayout(new java.awt.BorderLayout());

        txtSeeIcon.setColumns(60);
        txtSeeIcon.setEditable(false);
        txtSeeIcon.setLineWrap(true);
        txtSeeIcon.setRows(8);
        txtSeeIcon.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.txtSeeIcon.text")); // NOI18N
        txtSeeIcon.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtSeeIcon);

        panelTabSeeSysTrayQuestion.add(jScrollPane1);

        panelTabSeeSysTray.add(panelTabSeeSysTrayQuestion, java.awt.BorderLayout.CENTER);

        btnSeeSystemTrayYes.setMnemonic('y');
        btnSeeSystemTrayYes.setToolTipText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnSeeSystemTrayYes.toolTipText")); // NOI18N
        btnSeeSystemTrayYes.setLabel(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnSeeSystemTrayYes.label")); // NOI18N
        btnSeeSystemTrayYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeSystemTrayYesActionPerformed(evt);
            }
        });
        panelTabSeeSysTrayAnswer.add(btnSeeSystemTrayYes);

        btnSeeSystemTrayNo.setMnemonic('n');
        btnSeeSystemTrayNo.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnSeeSystemTrayNo.text")); // NOI18N
        btnSeeSystemTrayNo.setToolTipText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnSeeSystemTrayNo.toolTipText")); // NOI18N
        btnSeeSystemTrayNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeSystemTrayNoActionPerformed(evt);
            }
        });
        panelTabSeeSysTrayAnswer.add(btnSeeSystemTrayNo);

        panelTabSeeSysTray.add(panelTabSeeSysTrayAnswer, java.awt.BorderLayout.SOUTH);

        tabWizardTabs.addTab(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.panelTabSeeSysTray.TabConstraints.tabTitle"), panelTabSeeSysTray); // NOI18N

        panelTabNameDevice.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setPreferredSize(null);

        txtSeeIcon1.setColumns(60);
        txtSeeIcon1.setEditable(false);
        txtSeeIcon1.setLineWrap(true);
        txtSeeIcon1.setRows(8);
        txtSeeIcon1.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.txtSeeIcon1.text")); // NOI18N
        txtSeeIcon1.setWrapStyleWord(true);
        txtSeeIcon1.setMinimumSize(null);
        txtSeeIcon1.setPreferredSize(null);
        jScrollPane2.setViewportView(txtSeeIcon1);

        panelTabNameDeviceQuestion.add(jScrollPane2);

        panelTabNameDevice.add(panelTabNameDeviceQuestion, java.awt.BorderLayout.CENTER);

        lblDeviceName.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.lblDeviceName.text")); // NOI18N
        panelTabNameDeviceAnswer.add(lblDeviceName);

        txtDeviceName.setColumns(20);
        txtDeviceName.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.txtDeviceName.text")); // NOI18N
        panelTabNameDeviceAnswer.add(txtDeviceName);

        btnSetDeviceName.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnSetDeviceName.text")); // NOI18N
        btnSetDeviceName.setToolTipText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnSetDeviceName.toolTipText")); // NOI18N
        btnSetDeviceName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDeviceNameActionPerformed(evt);
            }
        });
        panelTabNameDeviceAnswer.add(btnSetDeviceName);

        panelTabNameDevice.add(panelTabNameDeviceAnswer, java.awt.BorderLayout.SOUTH);

        tabWizardTabs.addTab(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.panelTabNameDevice.TabConstraints.tabTitle"), panelTabNameDevice); // NOI18N

        pnlInitialSynchSetup.setLayout(new java.awt.BorderLayout());

        txtInitialSynchSetupQuestion.setColumns(60);
        txtInitialSynchSetupQuestion.setEditable(false);
        txtInitialSynchSetupQuestion.setLineWrap(true);
        txtInitialSynchSetupQuestion.setRows(8);
        txtInitialSynchSetupQuestion.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.txtInitialSynchSetupQuestion.text")); // NOI18N
        txtInitialSynchSetupQuestion.setWrapStyleWord(true);
        scrollInitialSynchSetupQuestion.setViewportView(txtInitialSynchSetupQuestion);

        pnlInitialSynchSetupQuestion.add(scrollInitialSynchSetupQuestion);

        pnlInitialSynchSetup.add(pnlInitialSynchSetupQuestion, java.awt.BorderLayout.CENTER);

        pnlInitialSynchSetupAnswer.setLayout(new java.awt.GridBagLayout());

        btnChooseLocalSynch.setMnemonic('c');
        btnChooseLocalSynch.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnChooseLocalSynch.text")); // NOI18N
        btnChooseLocalSynch.setToolTipText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnChooseLocalSynch.toolTipText")); // NOI18N
        btnChooseLocalSynch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseLocalSynchActionPerformed(evt);
            }
        });
        pnlLocalSynch.add(btnChooseLocalSynch);

        pnlInitialSynchSetupAnswer.add(pnlLocalSynch, new java.awt.GridBagConstraints());

        org.jdesktop.layout.GroupLayout pnlSynchModeLayout = new org.jdesktop.layout.GroupLayout(pnlSynchMode);
        pnlSynchMode.setLayout(pnlSynchModeLayout);
        pnlSynchModeLayout.setHorizontalGroup(
            pnlSynchModeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        pnlSynchModeLayout.setVerticalGroup(
            pnlSynchModeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlInitialSynchSetupAnswer.add(pnlSynchMode, gridBagConstraints);

        btnChooseIrodsSynch.setMnemonic('i');
        btnChooseIrodsSynch.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnChooseIrodsSynch.text")); // NOI18N
        btnChooseIrodsSynch.setToolTipText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnChooseIrodsSynch.toolTipText")); // NOI18N
        btnChooseIrodsSynch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseIrodsSynchActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlIrodsSynchLayout = new org.jdesktop.layout.GroupLayout(pnlIrodsSynch);
        pnlIrodsSynch.setLayout(pnlIrodsSynchLayout);
        pnlIrodsSynchLayout.setHorizontalGroup(
            pnlIrodsSynchLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 180, Short.MAX_VALUE)
            .add(pnlIrodsSynchLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlIrodsSynchLayout.createSequentialGroup()
                    .add(0, 1, Short.MAX_VALUE)
                    .add(btnChooseIrodsSynch)
                    .add(0, 2, Short.MAX_VALUE)))
        );
        pnlIrodsSynchLayout.setVerticalGroup(
            pnlIrodsSynchLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
            .add(pnlIrodsSynchLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlIrodsSynchLayout.createSequentialGroup()
                    .add(0, 35, Short.MAX_VALUE)
                    .add(btnChooseIrodsSynch)
                    .add(0, 36, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlInitialSynchSetupAnswer.add(pnlIrodsSynch, gridBagConstraints);

        pnlInitialSynchSetup.add(pnlInitialSynchSetupAnswer, java.awt.BorderLayout.SOUTH);

        tabWizardTabs.addTab(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.pnlInitialSynchSetup.TabConstraints.tabTitle"), pnlInitialSynchSetup); // NOI18N

        getContentPane().add(tabWizardTabs, java.awt.BorderLayout.CENTER);

        pnlWizardToolbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBack.setMnemonic('b');
        btnBack.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnBack.text")); // NOI18N
        btnBack.setToolTipText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnBack.toolTipText")); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        pnlWizardToolbar.add(btnBack);

        btnLater.setMnemonic('l');
        btnLater.setText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnLater.text")); // NOI18N
        btnLater.setToolTipText(org.openide.util.NbBundle.getMessage(SetupWizard.class, "SetupWizard.btnLater.toolTipText")); // NOI18N
        btnLater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaterActionPerformed(evt);
            }
        });
        pnlWizardToolbar.add(btnLater);

        getContentPane().add(pnlWizardToolbar, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** 
     * User indicates that he can see the system tray icon, right now should not show GUI at startup
     * @param evt 
     */
    private void btnSeeSystemTrayYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeSystemTrayYesActionPerformed

        log.info("indicates system try shown, set to not load gui");
        try {
            idropConfigurationService.updateConfig(IdropConfigurationService.SHOW_GUI, "false");
            log.info("config is updated");
        } catch (IdropException ex) {
            log.error("error updating configuration", ex);
            throw new IdropRuntimeException("error updating configuration", ex);
        }
        advanceTab();
    }//GEN-LAST:event_btnSeeSystemTrayYesActionPerformed

    private void btnSetDeviceNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDeviceNameActionPerformed
        if (txtDeviceName.getText().length() == 0) {
            txtDeviceName.setBackground(Color.red);
            MessageManager.showError(this, SETUP_ERROR_TITLE, "Device name is not entered");
            return;
        }
        try {
            idropConfigurationService.updateConfig(IdropConfigurationService.DEVICE_NAME, txtDeviceName.getText());
            log.info("device name is set to:{}", txtDeviceName.getText());
            // FIXME: check name in iRODS
        } catch (IdropException ex) {
            throw new IdropRuntimeException("error setting device name", ex);
        }
        advanceTab();

    }//GEN-LAST:event_btnSetDeviceNameActionPerformed

    private void tabWizardTabsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabWizardTabsKeyPressed
    }//GEN-LAST:event_tabWizardTabsKeyPressed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        reverseTab();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaterActionPerformed
        log.info("indicates system try not shown, set to always load gui");
        try {
            idropConfigurationService.updateConfig(IdropConfigurationService.SHOW_GUI, "true");
            log.info("clearing device name to force wizard next time");
            idropConfigurationService.removeConfigProperty(IdropConfigurationService.DEVICE_NAME);
            log.info("config is updated");
        } catch (IdropException ex) {
            log.error("error updating configuration", ex);
            throw new IdropRuntimeException("error updating configuration", ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnLaterActionPerformed

    private void btnChooseLocalSynchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseLocalSynchActionPerformed
        // TODO add your handling code here:
        JFileChooser localFileChooser = new JFileChooser();
           localFileChooser.setMultiSelectionEnabled(false);
        localFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = localFileChooser.showOpenDialog(this);
    }//GEN-LAST:event_btnChooseLocalSynchActionPerformed

    private void btnChooseIrodsSynchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseIrodsSynchActionPerformed
       IRODSFileSystemChooserView irodsFileSystemChooserView =  new IRODSFileSystemChooserView(idropCore);
        IRODSFileChooser irodsFileChooser = new IRODSFileChooser(idropCore, idropCore.getDefaultDirectory().getAbsolutePath(), irodsFileSystemChooserView);
        int returnVal = irodsFileChooser.showOpenDialog(this);
    }//GEN-LAST:event_btnChooseIrodsSynchActionPerformed

    private void btnSeeSystemTrayNoActionPerformed(
            final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSeeSystemTrayNoActionPerformed
        log.info("indicates system try not shown, set to always load gui");
        try {
            idropConfigurationService.updateConfig(IdropConfigurationService.SHOW_GUI, "true");
            log.info("config is updated");
        } catch (IdropException ex) {
            log.error("error updating configuration", ex);
            throw new IdropRuntimeException("error updating configuration", ex);
        }
        advanceTab();
    }// GEN-LAST:event_btnSeeSystemTrayNoActionPerformed

    private void reverseTab() {
        int tabLength = tabWizardTabs.getTabCount();
        int currentTab = tabWizardTabs.getSelectedIndex();

        if (--currentTab < 0) {
            tabStep = 0;
        } else {
            tabAdvancing = true;
            tabWizardTabs.setSelectedIndex(currentTab);
            tabStep = currentTab;
        }
    }

    private void advanceTab() {
        int tabLength = tabWizardTabs.getTabCount();
        int currentTab = tabWizardTabs.getSelectedIndex();

        if (++currentTab >= tabLength) {
            log.info("done with tabs");
            finishWizard();
        }

        tabAdvancing = true;
        tabWizardTabs.setSelectedIndex(currentTab);
        tabStep = currentTab;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChooseIrodsSynch;
    private javax.swing.JButton btnChooseLocalSynch;
    private javax.swing.JButton btnLater;
    private javax.swing.JButton btnSeeSystemTrayNo;
    private javax.swing.JButton btnSeeSystemTrayYes;
    private javax.swing.JButton btnSetDeviceName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDeviceName;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel panelTabNameDevice;
    private javax.swing.JPanel panelTabNameDeviceAnswer;
    private javax.swing.JPanel panelTabNameDeviceQuestion;
    private javax.swing.JPanel panelTabSeeSysTray;
    private javax.swing.JPanel panelTabSeeSysTrayAnswer;
    private javax.swing.JPanel panelTabSeeSysTrayQuestion;
    private javax.swing.JPanel panelTop;
    private javax.swing.JPanel pnlInitialSynchSetup;
    private javax.swing.JPanel pnlInitialSynchSetupAnswer;
    private javax.swing.JPanel pnlInitialSynchSetupQuestion;
    private javax.swing.JPanel pnlIrodsSynch;
    private javax.swing.JPanel pnlLocalSynch;
    private javax.swing.JPanel pnlSynchMode;
    private javax.swing.JPanel pnlWizardToolbar;
    private javax.swing.JScrollPane scrollInitialSynchSetupQuestion;
    private javax.swing.JTabbedPane tabWizardTabs;
    private javax.swing.JTextField txtDeviceName;
    private javax.swing.JTextArea txtInitialSynchSetupQuestion;
    private javax.swing.JTextArea txtSeeIcon;
    private javax.swing.JTextArea txtSeeIcon1;
    // End of variables declaration//GEN-END:variables

    private void finishWizard() {
        log.info("finishing wizard");
        this.dispose();
    }
}

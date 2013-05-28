package org.irods.jargon.idrop.desktop.systraygui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import org.irods.jargon.idrop.desktop.systraygui.viscomponents.LocalFileNode;
import org.irods.jargon.idrop.desktop.systraygui.viscomponents.LocalFileSystemModel;
import org.irods.jargon.idrop.desktop.systraygui.viscomponents.LocalFileTree;
import org.slf4j.LoggerFactory;

/**
 * Dialog to create a new local directory in the local file tree
 * 
 * @author mikeconway
 */
public class NewLocalDirectoryDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5136632505840351034L;
	private final iDrop idrop;
	private String parentDirectory = "";
	private final LocalFileTree localFileTree;
	private final LocalFileNode parentNode;
	public static org.slf4j.Logger log = LoggerFactory
			.getLogger(NewLocalDirectoryDialog.class);

	public String getParentDirectory() {
		return parentDirectory;
	}

	public void setParentDirectory(final String parentDirectory) {
		this.parentDirectory = parentDirectory;
	}

	/** Creates new form NewIRODSDirectoryDialog */
	public NewLocalDirectoryDialog(final iDrop parent, final boolean modal,
			final String parentDirectory, final LocalFileTree localFileTree,
			final LocalFileNode parentNode) {
		super(parent, modal);
		idrop = parent;
		this.parentDirectory = parentDirectory;
		this.localFileTree = localFileTree;
		this.parentNode = parentNode;
		initComponents();
		txtAreaCurrentParent.setText(this.parentDirectory);
		registerKeystrokeListener();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		lblTitle = new javax.swing.JLabel();
		pnlFolderData = new javax.swing.JPanel();
		pnlCurrentParent = new javax.swing.JPanel();
		lblCurrentParent = new java.awt.Label();
		scrollCurrentParent = new javax.swing.JScrollPane();
		txtAreaCurrentParent = new javax.swing.JTextArea();
		lblNewDiretoryName = new java.awt.Label();
		txtNewFolder = new javax.swing.JTextField();
		pnlBottom = new javax.swing.JPanel();
		btnCancel = new javax.swing.JButton();
		btnOK = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Create New Folder Dialog");
		setName("NewParentDialog"); // NOI18N

		lblTitle.setText("Please enter a name for the new folder");
		getContentPane().add(lblTitle, java.awt.BorderLayout.NORTH);

		pnlFolderData.setLayout(new java.awt.GridLayout(0, 1));

		lblCurrentParent.setText("Current parent directory:");

		txtAreaCurrentParent.setColumns(20);
		txtAreaCurrentParent.setEditable(false);
		txtAreaCurrentParent.setLineWrap(true);
		txtAreaCurrentParent.setRows(5);
		txtAreaCurrentParent.setFocusable(false);
		scrollCurrentParent.setViewportView(txtAreaCurrentParent);

		lblNewDiretoryName.setText("New folder name:");

		txtNewFolder
				.setToolTipText("A name for the new folder underneath the displayed parent");

		org.jdesktop.layout.GroupLayout pnlCurrentParentLayout = new org.jdesktop.layout.GroupLayout(
				pnlCurrentParent);
		pnlCurrentParent.setLayout(pnlCurrentParentLayout);
		pnlCurrentParentLayout
				.setHorizontalGroup(pnlCurrentParentLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(org.jdesktop.layout.GroupLayout.TRAILING,
								pnlCurrentParentLayout
										.createSequentialGroup()
										.addContainerGap(20, Short.MAX_VALUE)
										.add(pnlCurrentParentLayout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.TRAILING)
												.add(lblCurrentParent,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
												.add(lblNewDiretoryName,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(pnlCurrentParentLayout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.LEADING,
														false)
												.add(txtNewFolder)
												.add(scrollCurrentParent,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														413, Short.MAX_VALUE))
										.addContainerGap()));
		pnlCurrentParentLayout
				.setVerticalGroup(pnlCurrentParentLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(pnlCurrentParentLayout
								.createSequentialGroup()
								.add(58, 58, 58)
								.add(pnlCurrentParentLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(scrollCurrentParent,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(lblCurrentParent,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED)
								.add(pnlCurrentParentLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(txtNewFolder,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(lblNewDiretoryName,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(68, Short.MAX_VALUE)));

		lblNewDiretoryName.getAccessibleContext().setAccessibleName(
				"New directory name:");

		pnlFolderData.add(pnlCurrentParent);

		getContentPane().add(pnlFolderData, java.awt.BorderLayout.CENTER);

		pnlBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});
		pnlBottom.add(btnCancel);

		btnOK.setText("OK");
		btnOK.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				btnOKActionPerformed(evt);
			}
		});
		pnlBottom.add(btnOK);

		getContentPane().add(pnlBottom, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnCancelActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
		dispose();
	}// GEN-LAST:event_btnCancelActionPerformed

	private void btnOKActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnOKActionPerformed
		processNew();
	}// GEN-LAST:event_btnOKActionPerformed
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JButton btnCancel;

	private javax.swing.JButton btnOK;

	private java.awt.Label lblCurrentParent;

	private java.awt.Label lblNewDiretoryName;

	private javax.swing.JLabel lblTitle;

	private javax.swing.JPanel pnlBottom;

	private javax.swing.JPanel pnlCurrentParent;

	private javax.swing.JPanel pnlFolderData;

	private javax.swing.JScrollPane scrollCurrentParent;

	private javax.swing.JTextArea txtAreaCurrentParent;

	private javax.swing.JTextField txtNewFolder;

	// End of variables declaration//GEN-END:variables
	private void processNew() {
		// add the new folder to irods, add to the tree, and scroll the tree
		// into view

		if (txtNewFolder.getText().isEmpty()) {
			txtNewFolder.setBackground(Color.red);
			idrop.showMessageFromOperation("please enter a new folder name");
			return;
		}

		final NewLocalDirectoryDialog thisDialog = this;

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				log.info("adding new folder named:{}", txtNewFolder.getText());
				thisDialog.setCursor(Cursor
						.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {

					StringBuilder sb = new StringBuilder();
					sb.append(parentDirectory);
					sb.append('/');
					sb.append(txtNewFolder.getText());
					String newDirPath = sb.toString();

					File newDir = new File(newDirPath);

					boolean created = newDir.mkdirs();

					if (!created) {
						log.info("could not create new folder in:{}",
								newDirPath);
						idrop.showMessageFromOperation("directory could not be created");
						thisDialog.setCursor(Cursor
								.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						return;
					}

					log.info("new file was created, adding as a node in the local tree");
					LocalFileNode newLocalNode = new LocalFileNode(newDir);
					LocalFileSystemModel localFileSystemModel = (LocalFileSystemModel) localFileTree
							.getModel();
					if (parentNode.isCached()) {
						localFileSystemModel.insertNodeInto(newLocalNode,
								parentNode, parentNode.getChildCount());
					} else {
						parentNode.lazyLoadOfChildrenOfThisNode();
					}

					TreePath scrollToPath = new TreePath(newLocalNode.getPath());
					localFileTree.expandPath(scrollToPath);

					localFileTree.scrollPathToVisible(scrollToPath);
					thisDialog.setCursor(Cursor
							.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					idrop.showMessageFromOperation("new local folder created");

				} catch (Exception ex) {
					Logger.getLogger(NewLocalDirectoryDialog.class.getName())
							.log(Level.SEVERE, null, ex);
					idrop.showIdropException(ex);
				}

				thisDialog.dispose();
			}
		});
	}

	/**
	 * Register a listener for the enter event, so login can occur.
	 */
	private void registerKeystrokeListener() {

		KeyStroke enter = KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_ENTER, 0);
		Action enterAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 426777698063028685L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				processNew();
			}
		};
		btnOK.registerKeyboardAction(enterAction, enter,
				JComponent.WHEN_IN_FOCUSED_WINDOW);

	}
}

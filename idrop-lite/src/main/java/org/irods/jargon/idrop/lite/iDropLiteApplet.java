/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * iDropLiteApplet.java
 *
 * Created on Jun 7, 2011, 4:10:11 PM
 */

package org.irods.jargon.idrop.lite;

import java.awt.Rectangle;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import javax.swing.JToggleButton;
import javax.swing.tree.TreePath;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.query.CollectionAndDataObjectListingEntry;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.UserAO;
import org.irods.jargon.datautils.datacache.DataCacheServiceImpl;
import org.netbeans.swing.outline.Outline;

import org.slf4j.LoggerFactory;

/**
 *
 * @author lisa
 */
public class iDropLiteApplet extends javax.swing.JApplet {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(iDropLiteApplet.class);
    private final Integer defaultLoginMode = -1;
    private iDropLiteCore iDropCore = null;
    private IRODSAccount irodsAccount = null;
    private LocalFileTree fileTree = null;
    private IRODSTree irodsTree = null;
    private Integer mode;
    private String host;
    private Integer port;
    private String zone;
    private String user;
    private String defaultStorageResource;
    private String tempPswd;
    private String absPath;
    private String permPswd;
    IRODSFileSystem irodsFileSystem = null;

    /** Initializes the applet iDropLiteApplet */
    public void init() {
        try {
   
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
               	
                    getAppletParams();         
                    if(doStartup()) {
                    	initComponents();
                    	bntRefreshIrodsTree.hide();
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    protected void getAppletParams() {

    	try {
    		this.mode = Integer.parseInt(getParameter("mode"));
    	} catch (Exception ex) {
            this.mode = defaultLoginMode;
        }
    		
    	try {
    		this.host = getParameter("host");
    		this.port = Integer.parseInt(getParameter("port"));
    		this.user = getParameter("user");
    		this.zone = getParameter("zone");
    		this.defaultStorageResource = getParameter("defaultStorageResource");
    		this.tempPswd = getParameter("password");
    		this.absPath = getParameter("absPath");

            log.debug("creating account with applet params");
            log.info("mode:{}", mode);
            log.info("host:{}", host);
            log.info("port:{}", port);
            log.info("user:{}", user);
            log.info("zone:{}", zone);
            log.info("resource:{}", defaultStorageResource);
            log.info("absPath:{}", absPath);
        } catch (Exception ex) {
            Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
            showIdropException(ex);
        }
                
    }
    
    private boolean retrievePermAccount()
    {
    	String pswd = null;
    	
    	DataCacheServiceImpl dataCache = new DataCacheServiceImpl();
    	try {
			dataCache.setIrodsAccessObjectFactory(irodsFileSystem.getIRODSAccessObjectFactory());
		} catch (JargonException e1) {
			Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, e1);
		}
    	dataCache.setIrodsAccount(irodsAccount);
    	
    	try {
			pswd = dataCache.retrieveStringValueFromCache(tempPswd, user);
			irodsFileSystem.closeAndEatExceptions();
			this.irodsAccount = new IRODSAccount(host, port, user, pswd, absPath, zone, defaultStorageResource);
		} catch (JargonException e2) {
			Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, e2);
			return false;
		}
		
		return true;
    }
    
    private boolean createPermAccount()
    {
    	this.irodsAccount = new IRODSAccount(host, port, user, tempPswd, absPath, zone, defaultStorageResource);
    	
    	return true;
    }


    private boolean processLogin()  {

    	// do different logins depending on which mode is used
    	// 0 - Hard-coded permanent password - just use this password to create and IRODS Account
    	// 1 - Temporary password supplied - use this password to retrieve permanent password from cache file in cacheServiceTempDir
    	
    	switch(this.mode) {
    	
    	case 1:
    		log.info("processLogin: retrieving permanent password...");
    		if(!retrievePermAccount()) {
    			showMessageFromOperation("Temporary Password Mode: login error - unable to log in, or invalid user id");
    			return false;
    		}
    		break;
    	case 0:
    		log.info("processLogin: creating account with provided permanent password...");
    		if(!createPermAccount()) {
    			showMessageFromOperation("Permanent Password Mode: login error - unable to log in, or invalid user id");
    			return false;
    		}
    		break;
    	default:
    		showMessageFromOperation("Unsupported Login Mode");
    		return false;
    	
    	}
    	
        
        try {

            final UserAO userAO = irodsFileSystem.getIRODSAccessObjectFactory().getUserAO(irodsAccount);
            iDropCore.setIrodsAccount(irodsAccount);
            iDropCore.setIrodsFileSystem(irodsFileSystem);
        } catch (JargonException ex) {
            if (ex.getMessage().indexOf("Connection refused") > -1) {
                Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
                showMessageFromOperation("Cannot connect to the server, is it down?");
                return false;
            } else if (ex.getMessage().indexOf("Connection reset") > -1) {
                Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
                showMessageFromOperation("Cannot connect to the server, is it down?");
                return false;
            } else if (ex.getMessage().indexOf("io exception opening socket") > -1) {
                Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
                showMessageFromOperation("Cannot connect to the server, is it down?");
                return false;
            } else {
                Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
                showMessageFromOperation("login error - unable to log in, or invalid user id");
                return false;
            }
        } finally {
            if (irodsFileSystem != null) {
                try {
                    irodsFileSystem.close();
                } catch (JargonException ex) {
                    Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }


    protected boolean doStartup() {

        log.info("initiating startup sequence...");
        
        log.info("creating irods file system instance...");
        try {
			irodsFileSystem = IRODSFileSystem.instance();
		} catch (JargonException ex) {
			Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        log.info("creating temporary irods account...");
        this.irodsAccount = new IRODSAccount(host, port, user, tempPswd, absPath, zone, defaultStorageResource);
        
        log.info("creating idropCore...");
        iDropCore = new iDropLiteCore();

        if(!processLogin()) {
        	return false;
        }

        buildTargetTree();
        
        return true;
    }

    public void buildTargetTree() {
        log.info("building tree to look at staging resource");
        final iDropLiteApplet gui = this;

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                CollectionAndDataObjectListingEntry root = new CollectionAndDataObjectListingEntry();

                /*if (iDropCore.getIdropConfig().isLoginPreset()) {
                    log.info("using policy preset home directory");
                    StringBuilder sb = new StringBuilder();
                    sb.append("/");
                    sb.append(getIrodsAccount().getZone());
                    sb.append("/");
                    sb.append("home");
                    root.setParentPath(sb.toString());
                    root.setPathOrName(getIrodsAccount().getHomeDirectory());
                } else {*/
                    log.info("using root path, no login preset");
                    root.setPathOrName("/");
                //}

                log.info("building new iRODS tree");
                try {
                    if (irodsTree == null) {
                        irodsTree = new IRODSTree(gui);
                        IRODSNode rootNode = new IRODSNode(root, getIrodsAccount(),
                                getiDropCore().getIrodsFileSystem(), irodsTree);
                        irodsTree.setRefreshingTree(true);
                        // irodsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
                    }
                    IRODSNode rootNode = new IRODSNode(root, getIrodsAccount(), getiDropCore().getIrodsFileSystem(),
                            irodsTree);

                    IRODSFileSystemModel irodsFileSystemModel = new IRODSFileSystemModel(rootNode, getIrodsAccount());
                    IRODSOutlineModel mdl = new IRODSOutlineModel(gui, irodsFileSystemModel, new IRODSRowModel(), true,
                            "File System");
                    irodsTree.setModel(mdl);

                    /*
                     * IrodsTreeListenerForBuildingInfoPanel treeListener = new
                     * IrodsTreeListenerForBuildingInfoPanel(gui); irodsTree.addTreeExpansionListener(treeListener);
                     * irodsTree.addTreeSelectionListener(treeListener); // preset to display root tree node
                     * irodsTree.setSelectionRow(0);
                     */
                } catch (Exception ex) {
                    Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
                    throw new IdropRuntimeException(ex);
                }

                scrollIrodsTree.setViewportView(getTreeStagingResource());
                try {
                    TreePath selectedPath = TreeUtils.buildTreePathForIrodsAbsolutePath(irodsTree, absPath);
                    irodsTree.expandPath(selectedPath);
                    //irodsTree.getSelectionModel().setSelectionInterval(10, 12);
                    Rectangle rect = irodsTree.getPathBounds(selectedPath);
                    scrollIrodsTree.getViewport().scrollRectToVisible(rect);
                }
                catch(IdropException ex) {
                    Logger.getLogger(iDropLiteApplet.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*
                 * TreePath currentPath;
                 *
                 * if (currentPaths != null) { while (currentPaths.hasMoreElements()) { currentPath = (TreePath)
                 * currentPaths.nextElement(); log.debug("expanding tree path:{}", currentPath);
                 * irodsTree.expandPath(currentPath); } }
                 */
                irodsTree.setRefreshingTree(false);

                getiDropCore().getIrodsFileSystem().closeAndEatExceptions(iDropCore.getIrodsAccount());
            }
        });
    }

    public IRODSAccount getIrodsAccount() {
        synchronized (this) {
            return this.iDropCore.getIrodsAccount();
        }
    }

    public JToggleButton getToggleIrodsDetails() {
        // FIX ME: NEED TO IMPLEMENT
        return new JToggleButton();
    }

    public void showIdropException(Exception idropException) {
        JOptionPane.showMessageDialog(this, idropException.getMessage(), "iDROP Exception", JOptionPane.WARNING_MESSAGE);
    }

    public void showMessageFromOperation(final String messageFromOperation) {

        final iDropLiteApplet thisIdropGui = this;
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JOptionPane.showMessageDialog(thisIdropGui, messageFromOperation, "iDROP Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Initialize the info panel with data from iRODS. In this case, the data is an iRODS data object (file)
     *
     * @param dataObject
     *            <code>DataObject</code> iRODS domain object for a file.
     * @throws IdropException
     */
    public void initializeInfoPanel(final DataObject dataObject) throws IdropException {
/*
        if (!toggleIrodsDetails.isSelected()) {
            log.info("info display not selected, don't bother");
            return;
        }

        if (dataObject == null) {
            throw new IdropException("Null dataObject");
        }

        this.lastCachedInfoItem = dataObject;
        final iDrop idropGui = this;

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                idropGui.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                lblFileOrCollectionName.setText(dataObject.getDataName());
                txtParentPath.setText(dataObject.getCollectionName());
                txtComment.setText(dataObject.getComments());

                log.debug("getting available tags for data object");

                try {
                    FreeTaggingService freeTaggingService = FreeTaggingServiceImpl.instance(getiDropCore()
                            .getIrodsFileSystem().getIRODSAccessObjectFactory(), getiDropCore().getIrodsAccount());
                    IRODSTagGrouping irodsTagGrouping = freeTaggingService.getTagsForDataObjectInFreeTagForm(dataObject
                            .getCollectionName() + "/" + dataObject.getDataName());
                    txtTags.setText(irodsTagGrouping.getSpaceDelimitedTagsForDomain());
                    pnlInfoIcon.removeAll();
                    pnlInfoIcon.add(IconHelper.getFileIcon());
                    pnlInfoIcon.validate();
                    lblInfoCreatedAtValue.setText(df.format(dataObject.getCreatedAt()));
                    lblInfoUpdatedAtValue.setText(df.format(dataObject.getUpdatedAt()));
                    lblInfoLengthValue.setText(String.valueOf(dataObject.getDataSize()));
                    lblInfoLengthValue.setVisible(true);
                    lblInfoLength.setVisible(true);
                } catch (JargonException ex) {
                    Logger.getLogger(iDrop.class.getName()).log(Level.SEVERE, null, ex);
                    throw new IdropRuntimeException(ex);
                } finally {
                    getiDropCore().getIrodsFileSystem().closeAndEatExceptions(getIrodsAccount());
                    idropGui.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
 */

    }

     /**
     * Initialize the info panel with data from iRODS. In this case, the data is an iRODS collection (directory).
     *
     * @param collection
     * @throws IdropException
     */
    public void initializeInfoPanel(final Collection collection) throws IdropException {
        if (collection == null) {
            throw new IdropException("Null collection");
        }

        log.info("initialize info panel with collection:{}", collection);
        /*

        if (!toggleIrodsDetails.isSelected()) {
            log.info("info display not selected, don't bother");
            return;
        }

        this.lastCachedInfoItem = collection;
        final iDrop idropGui = this;

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                idropGui.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                lblFileOrCollectionName.setText(collection.getCollectionLastPathComponent());
                txtParentPath.setText(collection.getCollectionParentName());
                txtComment.setText(collection.getComments());

                log.debug("getting available tags for data object");

                try {
                    FreeTaggingService freeTaggingService = FreeTaggingServiceImpl.instance(getiDropCore()
                            .getIrodsFileSystem().getIRODSAccessObjectFactory(), getIrodsAccount());
                    IRODSTagGrouping irodsTagGrouping = freeTaggingService.getTagsForCollectionInFreeTagForm(collection
                            .getCollectionName());
                    txtTags.setText(irodsTagGrouping.getSpaceDelimitedTagsForDomain());
                    pnlInfoIcon.removeAll();
                    pnlInfoIcon.add(IconHelper.getFolderIcon());
                    pnlInfoIcon.validate();
                    lblInfoCreatedAtValue.setText(df.format(collection.getCreatedAt()));
                    lblInfoUpdatedAtValue.setText(df.format(collection.getModifiedAt()));
                    lblInfoLengthValue.setVisible(false);
                    lblInfoLength.setVisible(false);
                } catch (JargonException ex) {
                    Logger.getLogger(iDrop.class.getName()).log(Level.SEVERE, null, ex);
                    throw new IdropRuntimeException(ex);
                } finally {
                    getiDropCore().getIrodsFileSystem().closeAndEatExceptions(getIrodsAccount());
                    idropGui.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        */
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        tabIrodsViews = new javax.swing.JTabbedPane();
        pnlIrodsTreeView = new javax.swing.JPanel();
        pnlIrodsTreeToolbar = new javax.swing.JPanel();
        bntRefreshIrodsTree = new javax.swing.JButton();
        pnlTreeMaster = new javax.swing.JPanel();
        scrollIrodsTree = new javax.swing.JScrollPane();

        pnlMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlIrodsTreeView.setLayout(new java.awt.BorderLayout());

        bntRefreshIrodsTree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntRefreshIrodsTree.setLabel("Refresh");
        pnlIrodsTreeToolbar.add(bntRefreshIrodsTree);

        pnlIrodsTreeView.add(pnlIrodsTreeToolbar, java.awt.BorderLayout.NORTH);

        pnlTreeMaster.setLayout(new java.awt.BorderLayout());
        pnlIrodsTreeView.add(pnlTreeMaster, java.awt.BorderLayout.SOUTH);
        pnlIrodsTreeView.add(scrollIrodsTree, java.awt.BorderLayout.CENTER);

        tabIrodsViews.addTab("iRODS Tree View", pnlIrodsTreeView);

        org.jdesktop.layout.GroupLayout pnlMainLayout = new org.jdesktop.layout.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tabIrodsViews, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .add(tabIrodsViews, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntRefreshIrodsTree;
    private javax.swing.JPanel pnlIrodsTreeToolbar;
    private javax.swing.JPanel pnlIrodsTreeView;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTreeMaster;
    private javax.swing.JScrollPane scrollIrodsTree;
    private javax.swing.JTabbedPane tabIrodsViews;
    // End of variables declaration//GEN-END:variables

    public IRODSTree getIrodsTree() {
        return irodsTree;
    }

    public iDropLiteCore getiDropCore() {
        return iDropCore;
    }

    public LocalFileTree getFileTree() {
        return fileTree;
    }

    public Outline getTreeStagingResource() {
        return irodsTree;
    }

}

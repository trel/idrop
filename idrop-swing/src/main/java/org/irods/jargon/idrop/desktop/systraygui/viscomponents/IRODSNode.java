package org.irods.jargon.idrop.desktop.systraygui.viscomponents;

import java.awt.Cursor;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.tree.DefaultMutableTreeNode;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.CollectionAndDataObjectListAndSearchAO;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.query.CollectionAndDataObjectListingEntry;
import org.irods.jargon.idrop.desktop.systraygui.IRODSTreeContainingComponent;
import org.irods.jargon.idrop.desktop.systraygui.MessageManager;
import org.irods.jargon.idrop.exceptions.IdropException;
import org.irods.jargon.idrop.exceptions.IdropRuntimeException;
import org.slf4j.LoggerFactory;

/**
 * tree node for iRODS file tree
 *
 * @author Mike Conway - DICE (www.irods.org)
 */
public class IRODSNode extends DefaultMutableTreeNode {

    /**
     *
     */
    private static final long serialVersionUID = 7660837627188146455L;
    private boolean cached = false;
    public static org.slf4j.Logger log = LoggerFactory
            .getLogger(IRODSNode.class);
    public final IRODSAccount irodsAccount;
    public final IRODSFileSystem irodsFileSystem;
    public boolean hasMore = true;
    public boolean continuation = false;
    private final IRODSTreeContainingComponent irodsTree;

    public IRODSNode(final CollectionAndDataObjectListingEntry entry,
            final IRODSAccount irodsAccount,
            final IRODSFileSystem irodsFileSystem,
            final IRODSTreeContainingComponent irodsTree) {
        super(entry);
        if (irodsAccount == null) {
            throw new IdropRuntimeException("null irodsAccount");
        }
        this.irodsAccount = irodsAccount;
        this.irodsFileSystem = irodsFileSystem;
        this.irodsTree = irodsTree;
    }

    @Override
    public boolean isLeaf() {
        CollectionAndDataObjectListingEntry nodeToCheck = (CollectionAndDataObjectListingEntry) getUserObject();
        return (nodeToCheck.getObjectType() == CollectionAndDataObjectListingEntry.ObjectType.DATA_OBJECT);
    }

    /**
     * Load children of this node, and then close the connection (appropriate
     * for the user expanding a node).
     *
     * @throws IdropException
     */
    public void lazyLoadOfChildrenOfThisNode() throws IdropException {
        boolean refreshing = false;

        if (irodsTree != null) {
            refreshing = irodsTree.isRefreshingTree();
        }
        lazyLoadOfChildrenOfThisNode(!refreshing);
    }

    /**
     * Load children of this node by accessing iRODS. Note that in a refresh
     * situation, you do not want to continuously open and close the connection,
     * so there is an option to defer closing the connection to the caller.
     *
     * @param closeTheConnectionAfterLoad <code>boolean</code> that indicates
     * that the connection will be closed by this method. If <code>true</code>
     * is passed, then this method will close the connection. If
     * <code>false</code> is passed, then the caller must close the connection.
     * @throws IdropException
     */
    public void lazyLoadOfChildrenOfThisNode(
            final boolean closeTheConnectionAfterLoad) throws IdropException {

        if (cached) {
            return;
        }

        log.debug("lazily loading children of:{}", this);
        log.debug("will I close this connection:{}",
                closeTheConnectionAfterLoad);

        CollectionAndDataObjectListingEntry parentObject = (CollectionAndDataObjectListingEntry) getUserObject();
        try {
            irodsTree.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            CollectionAndDataObjectListAndSearchAO collectionAO = irodsFileSystem
                    .getIRODSAccessObjectFactory()
                    .getCollectionAndDataObjectListAndSearchAO(irodsAccount);

            long timeStart = System.currentTimeMillis();

            List<CollectionAndDataObjectListingEntry> childCache = collectionAO
                    .listDataObjectsAndCollectionsUnderPath(parentObject
                            .getFormattedAbsolutePath());

            long timeEnd = System.currentTimeMillis();

            log.info(">>> total query time = {}", timeEnd = timeStart);

            for (CollectionAndDataObjectListingEntry childEntry : childCache) {
                insert(new IRODSNode(childEntry, irodsAccount, irodsFileSystem,
                        irodsTree), getChildCount());
            }

            if (children == null) {
                children = new Vector();
            }

            cached = true;

        } catch (Exception ex) {
            Logger.getLogger(IRODSNode.class.getName()).log(Level.SEVERE, null,
                    ex);
            throw new IdropException(
                    "error occurred accessing collection data", ex);
        } finally {
            if (closeTheConnectionAfterLoad) {
                irodsFileSystem.closeAndEatExceptions(irodsAccount);
            }
            irodsTree.setCursor(Cursor
                    .getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void forceReloadOfChildrenOfThisNode() {
        cached = false;
        removeAllChildren();
    }

    @Override
    public String toString() {
        CollectionAndDataObjectListingEntry entry = (CollectionAndDataObjectListingEntry) getUserObject();
        String returnedString = "";

        if (entry.getPathOrName().equals("")) {
            returnedString = "/";
        } else {
            returnedString = entry.getNodeLabelDisplayValue();
        }
        return returnedString;
    }

    @Override
    public int hashCode() {
        return getUserObject().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (!(obj instanceof IRODSNode)) {
            return false;
        }

        IRODSNode comparableAsNode = (IRODSNode) obj;

        CollectionAndDataObjectListingEntry thisFile = (CollectionAndDataObjectListingEntry) getUserObject();
        CollectionAndDataObjectListingEntry thatFile = (CollectionAndDataObjectListingEntry) comparableAsNode
                .getUserObject();
        return thisFile.equals(thatFile);
    }

    @SuppressWarnings("unchecked")
    List getChildren() throws IdropException {
        lazyLoadOfChildrenOfThisNode(!irodsTree.isRefreshingTree());
        return children;
    }

    /**
     * Have the children nodes been cached?
     *
     * @return <code>boolean</code> if children nodes have been cached.
     */
    public boolean isCached() {
        return cached;
    }

    /**
     * Is this node a continuation of a previous query
     *
     * @return <code>boolean<code>
     */
    public boolean isContinuation() {
        return continuation;
    }

    /**
     * Are there more records (children) to be retrieved from iRODS?
     *
     * @return <code>boolean</code> if more records are available.
     */
    public boolean isHasMore() {
        return hasMore;
    }

    /**
     * Return full path of this node
     *
     * @return <code>String</code> of nodes full path
     *
     */
    public String getFullPath() {
        String fullPath = "";
        CollectionAndDataObjectListingEntry entry = (CollectionAndDataObjectListingEntry) getUserObject();
        if (entry.isCollection()) {
            fullPath = entry.getPathOrName();
        } else {
            fullPath = entry.getParentPath() + "/" + entry.getPathOrName();
        }
        return fullPath;
    }

}

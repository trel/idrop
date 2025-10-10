/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.irods.jargon.conveyor.core.callables;

import org.irods.jargon.conveyor.core.ConveyorExecutionException;
import org.irods.jargon.conveyor.core.ConveyorService;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.transfer.TransferControlBlock;
import org.irods.jargon.transfer.dao.domain.TransferAttempt;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * 
 * @author lisa
 */
public class GetConveyorCallable extends AbstractConveyorCallable {

	private static final Logger log = LogManager
			.getLogger(PutConveyorCallable.class);

	public GetConveyorCallable(final TransferAttempt transferAttempt,
			final ConveyorService conveyorService) {
		super(transferAttempt, conveyorService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.irods.jargon.conveyor.core.callables.AbstractConveyorCallable#
	 * processCallForThisTransfer
	 * (org.irods.jargon.core.transfer.TransferControlBlock,
	 * org.irods.jargon.core.connection.IRODSAccount)
	 */
	@Override
	void processCallForThisTransfer(final TransferControlBlock tcb,
			final IRODSAccount irodsAccount) throws ConveyorExecutionException,
			JargonException {
		log.info("processCallForThisTransfer()");

		DataTransferOperations dataTransferOperationsAO = getIrodsAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		dataTransferOperationsAO
				.getOperation(getTransferAttempt().getTransfer()
						.getIrodsAbsolutePath(), getTransferAttempt()
						.getTransfer().getLocalAbsolutePath(),
						getTransferAttempt().getTransfer().getGridAccount()
								.getDefaultResource(), this, tcb);

	}

}

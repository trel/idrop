package org.irods.jargon.idrop.web.services

import org.irods.jargon.core.connection.IRODSAccount
import org.irods.jargon.core.pub.IRODSAccessObjectFactory
import org.irods.jargon.vircoll.VirtualCollectionDiscoveryService
import org.irods.jargon.vircoll.impl.VirtualCollectionDiscoveryServiceImpl



class VirtualCollectionService {

	static transactional = false
	IRODSAccessObjectFactory irodsAccessObjectFactory
	enum ListingType {
		ALL, COLLETIONS, DATA_OBJECTS
	}


	/**
	 * Get the default list of virtual collections associated with a user
	 * @param irodsAccount {@link IRODSAccount} for the target user
	 * @return <code>List</code> of {@link AbstractVirtualCollection} 
	 */
	def virtualCollectionHomeListingForUser(IRODSAccount irodsAccount) {

		log.info("virtualCollectionHomeListingForUser()")

		if (!irodsAccount) {
			throw new IllegalArgumentException("null irodsAccount")
		}

		log.info("irodsAccount: ${irodsAccount}")

		VirtualCollectionDiscoveryService virtualCollectionDiscoveryService = new VirtualCollectionDiscoveryServiceImpl(irodsAccessObjectFactory, irodsAccount)
		
		def colls = virtualCollectionDiscoveryService.listDefaultUserCollections()
		
		def session = RequestContextHolder.currentRequestAttributes().getSession()
		session.virtualCollections = colls
		
		return colls
	}


	def virtualCollectionListing(String vcName, ListingType listingType, int offset) {
		
		log.info("virtualCollectionListing")
		
		if (!vcName) {
			throw new IllegalArgumentException("null or empty vcName")
		}
		
		if (!listingType) {
			throw new IllegalArgumentException("null or empty listingType")
		}
		
		log.info("listing for vc: ${vcName} listing type:${listingType} offset:{$offset}")
		def session = RequestContextHolder.currentRequestAttributes().getSession()
		
		
		def virColls = session.virtualCollections
		
		def virColl
		for (virCollEntry in virColls) {
			if (virCollEntry.uniqueName == vcName) {
				log.info("found it")
				session.virtualCollection = virCollEntry
				break
			}
		}
		
		if (!virColl) {
			throw new Exception "no virtual collections found for name:${vcName}"
		}
		
		
		
		
		
		
		
		
		
	}
}

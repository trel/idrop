
### Project: iDrop-swing- Desktop iRODS transfer manager and supporting libraries
#### Date:
#### Version: 4.3.7.0-RELEASE
#### Git Tag: 

iDrop is a graphical multi-platform file transfer client suitable for moving large files, doing basic synchronization, and other
tasks too 'heavy' for a standard web interface

This interface is being revived and will become part of the regular Jargon release train starting with 4.3.0.1

GitHub:  [https://github.com/irods-contrib/idrop](https://github.com/irods-contrib/idrop)

## Requirements

- iDrop depends on Java 1.8+
- iDrop is built using Maven

iDrop-swing uses Maven for dependency management.  See the pom.xml file for references to various dependencies.

## Changes

#### Add negotiation drop down to grid config #130

Now support SSL negotiation settings per grid account

#### Update dependencies and upgrade Spring and Hibernate #136 and #137

Update various dependencies, especially Hibernate. Update various bean configurations to reflect the shift to Hibernate5 semantics

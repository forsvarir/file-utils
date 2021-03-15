# File Utils

## Goal
The goal is to allow a client application to run 
a scan of a folder structure and store details
about the files that it finds.  It should then be 
possible to query about those specific files.

Services required:

### Batch Run Services
#### Create Run
/file-utils/batch (Post)

Return a unique run id, which will be used to
identify all information relevant to this particular
run.

#### Discard Run 
/file-utils/batch/id (Delete)
If it exists, then the given run is deleted

#### Run Details
/file-utils/batch/id (Get)

Returns information about the run, including:
* Run Id
* Run Start Date/Time
* Last Run Update Date/Time
* Id of last file processed

### File Services
###Operations file-utils/files
/file-utils/files/{id} (Get)

Returns FileDetails with given id

/file-utils/files (Post)

Adds a file object if it doesn't already exist

### Data

FileDetails

* Id
* BatchId / ClientId
* Name
* Path
* Size

## Requirements

### Postgresql database. This can be setup

using the following instructions at the command line:

* sudo -u postgres psql
* create user fileutil;
* alter user fileutil with encrypted password 'fileutil';
* create database fileutildb;
* grant all privileges on database fileutildb to fileutil;
* quit;
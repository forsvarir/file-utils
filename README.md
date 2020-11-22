# File Utils

File services, implemented with spring to support indexing and analysis of files.

## Rest Services

###Operations file-utils/files
####Get
/file-utils/files/{id}

Returns FileDetails with given id

####Post
/file-utils/files

Adds a file object if it doesn't already exist

### Operations file-utils/directory
####Get
/file-utils/directory/{path}

Returns list of file details with matching path

###Data
FileDetails
* Id
* Name
* Path
* Size


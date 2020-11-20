# File Utils

File services, implemented with spring to support indexing and analysis of files.

## Rest Services

###Operations
####Get
/file-utils/files/{path}

Returns list of files at given path
####Post
/file-utils/files

Adds a file object if it doesn't already exist

###Data

File
* Id
* Name
* Path
* Size


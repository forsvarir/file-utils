package com.forsvarir.file.utils.common.api.data;

public class CreateFileRequest {
    private long batchId;
    private FileDetail fileDetail;

    public CreateFileRequest() {
        this(0L, null);
    }

    public CreateFileRequest(long batchId, FileDetail fileDetail) {
        this.fileDetail = fileDetail;
        this.batchId = batchId;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public FileDetail getFileDetail() {
        return fileDetail;
    }

    public void setFileDetail(FileDetail fileDetail) {
        this.fileDetail = fileDetail;
    }
}

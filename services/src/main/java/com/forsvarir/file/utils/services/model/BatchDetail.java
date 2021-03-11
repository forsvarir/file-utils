package com.forsvarir.file.utils.services.model;

public class BatchDetail {
    private long id;

    public BatchDetail(long batchId) {

        id = batchId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

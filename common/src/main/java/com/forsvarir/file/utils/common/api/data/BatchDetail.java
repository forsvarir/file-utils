package com.forsvarir.file.utils.common.api.data;

public class BatchDetail {
    private long id;

    public BatchDetail() {
        this(0);
    }

    public BatchDetail(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

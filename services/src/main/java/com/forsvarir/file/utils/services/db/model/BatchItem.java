package com.forsvarir.file.utils.services.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BatchItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long batchId;
    private long itemId;
    private String itemType;

    public BatchItem() {
        this(0, "", 0);
    }

    public BatchItem(long batchId, String itemType, long itemIdentifier) {
        id = 0;
        this.batchId = batchId;
        this.itemId = itemIdentifier;
        this.itemType = itemType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}

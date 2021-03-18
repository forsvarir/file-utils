package com.forsvarir.file.utils.services.db.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("FILE")
public class BatchFileItem extends BatchItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    FileInformation file;

    private long item_id;

    public BatchFileItem(Batch batch, long file_id) {
        super(batch);
        item_id = file_id;
    }

    public BatchFileItem() {
    }

    public FileInformation getFile() {
        return file;
    }

    public void setFile(FileInformation file) {
        this.file = file;
    }
}

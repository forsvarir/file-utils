package com.forsvarir.file.utils.services.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Batch() {
        this(0);
    }

    public Batch(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

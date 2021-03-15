package com.forsvarir.file.utils.services.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "files")
public class FileInformation {
    private String name;
    private String path;
    private long size;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @SuppressWarnings("unused")
    public FileInformation() {
        this("", "", 0, 0);
    }

    public FileInformation(String name, String path, long size) {
        this(name, path, size, 0);
    }

    public FileInformation(String name, String path, long size, long id) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setId(long id) {
        this.id = id;
    }

}

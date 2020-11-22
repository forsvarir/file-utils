package com.forsvarir.fileutils.model;

public class FileDetail {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public FileDetail(String path, String name) {
        this.name = name;
        this.path = path;
        this.id = 1;
        this.size = 400L;
    }

    public FileDetail() {
        name = "unknown";
        path = "/unk/";
        this.id = 1;
        this.size = 400L;
    }

    private Integer id;
    private String name;
    private String path;
    private Long size;
}

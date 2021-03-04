package com.forsvarir.file.utils.services.model;

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

    public FileDetail() {
        this("", "", 0L);
    }

    public FileDetail(String path, String name, Long size) {
        this(path, name, size, 0);
    }

    public FileDetail(String path, String name, Long size, Integer id) {
        this.name = name;
        this.path = path;
        this.id = id;
        this.size = size;
    }

    private Integer id;
    private String name;
    private String path;
    private Long size;
}

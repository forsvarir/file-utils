package com.forsvarir.file.utils.fileclient.services.data;

public class FileDetails {
    private final String name;
    private final String path;
    private final long size;

    public FileDetails(String name, String path, long size) {
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
}

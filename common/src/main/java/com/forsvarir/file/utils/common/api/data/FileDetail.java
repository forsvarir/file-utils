package com.forsvarir.file.utils.common.api.data;

public class FileDetail {
    private final String name;
    private final String path;
    private final long size;
    private final long id;
    private final long clientId;

    @SuppressWarnings("unused")
    public FileDetail() {
        this("", "", 0, 0, 0);
    }

    public FileDetail(String name, String path, long size) {
        this(name, path, size, 0);
    }

    public FileDetail(String name, String path, long size, long id) {
        this(name, path, size, 0, id);
    }

    public FileDetail(String name, String path, long size, long clientId, long id) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.size = size;
        this.clientId = clientId;
    }

    public FileDetail(FileDetail fileDetail, long batchId) {
        this(fileDetail.getName(), fileDetail.getPath(), fileDetail.getSize(), batchId, fileDetail.getId());
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

    public long getClientId() {
        return clientId;
    }
}

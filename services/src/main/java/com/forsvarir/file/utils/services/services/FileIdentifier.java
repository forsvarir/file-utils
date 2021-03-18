package com.forsvarir.file.utils.services.services;

public class FileIdentifier {
    public long getId() {
        return id;
    }

    private final long id;

    public FileIdentifier(long id) {
        this.id = id;
    }
}

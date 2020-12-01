package com.forsvarir.fileutils.services;

import com.forsvarir.fileutils.model.FileDetail;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    // TODO: Stubbed
    public FileDetail findById(Integer id) {
        return new FileDetail("/stubbedPath/", "aFile", 9999L, id);
    }

    // TODO: Stubbed
    public FileDetail save(FileDetail newFile) {
        return newFile;
    }
}

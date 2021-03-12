package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    // TODO: Stubbed
    public FileDetail findById(Integer id) {
        return new FileDetail("aFile", "/stubbedPath/", 9999L, id);
    }

    // TODO: Stubbed
    public FileDetail save(FileDetail newFile) {
        System.out.println(newFile.getPath() + '/' + newFile.getName());
        return newFile;
    }
}

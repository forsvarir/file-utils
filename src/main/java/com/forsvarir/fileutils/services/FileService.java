package com.forsvarir.fileutils.services;

import com.forsvarir.fileutils.model.FileDetail;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    public FileDetail findById(Integer id) {
        return new FileDetail("/stubbedPath/", "aFile", id);
    }
}

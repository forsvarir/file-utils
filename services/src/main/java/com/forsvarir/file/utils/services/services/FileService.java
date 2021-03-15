package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import com.forsvarir.file.utils.services.db.FileRepository;
import com.forsvarir.file.utils.services.db.model.FileInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    // TODO: Stubbed
    public FileDetail findById(Integer id) {
        return new FileDetail("aFile", "/stubbedPath/", 9999L, id);
    }

    public FileDetail addFile(FileDetail newFile) {
        System.out.println(newFile.getPath() + '/' + newFile.getName());
        var saved = fileRepository.save(new FileInformation(newFile.getName(), newFile.getPath(), newFile.getSize()));
        return new FileDetail(saved.getName(), saved.getPath(), saved.getSize(), saved.getId());
    }
}

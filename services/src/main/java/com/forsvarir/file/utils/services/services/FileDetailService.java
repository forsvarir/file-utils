package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import com.forsvarir.file.utils.services.db.FileInformationRepository;
import com.forsvarir.file.utils.services.db.model.FileInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDetailService {
    @Autowired
    FileInformationRepository fileInformationRepository;

    // TODO: Stubbed
    public FileDetail findById(Integer id) {
        return new FileDetail("aFile", "/stubbedPath/", 9999L, id);
    }

    public FileDetail addFile(FileDetail newFile) {
        System.out.println(newFile.getPath() + '/' + newFile.getName());
        var saved = fileInformationRepository.save(new FileInformation(newFile.getName(), newFile.getPath(), newFile.getSize(), newFile.getClientId(), 0));
        return new FileDetail(saved.getName(), saved.getPath(), saved.getSize(), saved.getId(), saved.getClientId());
    }
}

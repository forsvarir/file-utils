package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchFileProcessing {
    @Autowired
    FileDetailService fileDetailService;

    @Autowired
    BatchService batchService;

    public FileDetail addFileToBatch(FileDetail fileToAdd, long batchId) {
        var addedFile = fileDetailService.addFile(fileToAdd);
        batchService.addItem(batchId, new FileIdentifier(addedFile.getId()));

        return addedFile;
    }

    public List<FileDetail> allFiles(Integer batchid) {
        return batchService.allFiles(batchid).
                stream().map(f -> new FileDetail(f.getName(), f.getPath(), f.getSize(), f.getId()))
                .collect(Collectors.toList());
    }
}

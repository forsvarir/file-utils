package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchFileProcessing {
    @Autowired
    FileDetailService fileDetailService;

    @Autowired
    BatchService batchService;

    public FileDetail addFileToBatch(FileDetail fileToAdd, long batchId) {
        var addedFile = fileDetailService.addFile(fileToAdd);
        batchService.addItem(batchId, "File", addedFile.getId());

        return addedFile;
    }
}

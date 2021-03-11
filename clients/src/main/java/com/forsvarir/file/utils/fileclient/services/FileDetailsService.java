package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import org.springframework.stereotype.Service;

@Service
public class FileDetailsService {
    public void createFile(long batchId, FileDetail fileDetail) {
        System.out.println(fileDetail.getPath() + '/' + fileDetail.getName());
    }
}

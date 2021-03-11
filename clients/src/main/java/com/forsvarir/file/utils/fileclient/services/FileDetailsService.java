package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.FileDetails;
import org.springframework.stereotype.Service;

@Service
public class FileDetailsService {
    public void createFile(int batchId, FileDetails fileDetails) {
        System.out.println(fileDetails.getPath() + '/' + fileDetails.getName());
    }
}

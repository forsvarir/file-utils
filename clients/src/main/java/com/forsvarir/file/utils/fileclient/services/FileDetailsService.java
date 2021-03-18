package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.common.api.data.CreateFileRequest;
import com.forsvarir.file.utils.common.api.data.FileDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class FileDetailsService {
    public static final String SERVICE_URL = "http://localhost:8080/file-utils/files";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public FileDetail createFile(FileDetail fileDetail) {
        CreateFileRequest createFileRequest = new CreateFileRequest(fileDetail.getClientId(), fileDetail);
        return restTemplateBuilder
                .build()
                .postForObject(SERVICE_URL, createFileRequest, FileDetail.class);
    }
}

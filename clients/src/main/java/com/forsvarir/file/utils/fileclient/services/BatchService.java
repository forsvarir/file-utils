package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.BatchInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    public static final String SERVICE_URL = "http://localhost:8080/file-utils/batch";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public BatchInformation createNewRun() {
        return restTemplateBuilder
                .build()
                .postForObject(SERVICE_URL, null, BatchInformation.class);

    }
}

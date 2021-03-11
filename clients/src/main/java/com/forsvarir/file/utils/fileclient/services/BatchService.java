package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.common.api.data.BatchDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    public static final String SERVICE_URL = "http://localhost:8080/file-utils/batch";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public BatchDetail createNewRun() {
        return restTemplateBuilder
                .build()
                .postForObject(SERVICE_URL, null, BatchDetail.class);

    }
}

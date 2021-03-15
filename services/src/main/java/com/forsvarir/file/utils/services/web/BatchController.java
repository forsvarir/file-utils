package com.forsvarir.file.utils.services.web;

import com.forsvarir.file.utils.services.services.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class BatchController {
    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/file-utils/batch")
    public ResponseEntity<?> createBatch() {
        var batchDetail = batchService.create();
        return ResponseEntity.created(URI.create("/file-utils/batch/" + batchDetail.getId()))
                .body(batchDetail);
    }
}

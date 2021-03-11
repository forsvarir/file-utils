package com.forsvarir.file.utils.services.web;

import com.forsvarir.file.utils.services.services.BatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BatchController {
    private BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/file-utils/batch")
    public ResponseEntity<?> createBatch() {
        try {
            var batchDetail = batchService.create();
            return ResponseEntity.created(new URI("/file-utils/batch/" + batchDetail.getId()))
                    .body(batchDetail);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

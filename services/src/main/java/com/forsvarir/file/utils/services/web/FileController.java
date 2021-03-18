package com.forsvarir.file.utils.services.web;

import com.forsvarir.file.utils.common.api.data.CreateFileRequest;
import com.forsvarir.file.utils.services.services.BatchFileProcessing;
import com.forsvarir.file.utils.services.services.FileDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class FileController {

    private final FileDetailService fileDetailService;
    private final BatchFileProcessing batchFileProcessingService;

    public FileController(FileDetailService fileDetailService,
                          BatchFileProcessing batchFileProcessing) {
        this.fileDetailService = fileDetailService;
        batchFileProcessingService = batchFileProcessing;
    }

    @GetMapping("/file-utils/files/{id}")
    public ResponseEntity<?> getFiles(@PathVariable Integer id) {
        var stubbedFile = fileDetailService.findById(id);
        return ResponseEntity.ok()
                .body(stubbedFile);
    }

    @PostMapping("/file-utils/files")
    public ResponseEntity<?> addFile(@RequestBody CreateFileRequest newFile) {
        var savedFile = batchFileProcessingService.addFileToBatch(newFile.getFileDetail(), newFile.getBatchId());

        return ResponseEntity.created(URI.create("/file-utils/files/" + savedFile.getId()))
                .body(savedFile);
    }
}

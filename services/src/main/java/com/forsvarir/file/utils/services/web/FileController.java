package com.forsvarir.file.utils.services.web;

import com.forsvarir.file.utils.common.api.data.CreateFileRequest;
import com.forsvarir.file.utils.services.services.FileDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class FileController {

    private final FileDetailService fileDetailService;

    public FileController(FileDetailService fileDetailService) {
        this.fileDetailService = fileDetailService;
    }

    @GetMapping("/file-utils/files/{id}")
    public ResponseEntity<?> getFiles(@PathVariable Integer id) {
        var stubbedFile = fileDetailService.findById(id);
        return ResponseEntity.ok()
                .body(stubbedFile);
    }

    @PostMapping("/file-utils/files")
    public ResponseEntity<?> addFile(@RequestBody CreateFileRequest newFile) {
        var savedFile = fileDetailService.addFile(newFile.getFileDetail());

        return ResponseEntity.created(URI.create("/file-utils/files/" + savedFile.getId()))
                .body(savedFile);
    }
}

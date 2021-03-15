package com.forsvarir.file.utils.services.web;

import com.forsvarir.file.utils.common.api.data.CreateFileRequest;
import com.forsvarir.file.utils.services.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/file-utils/files/{id}")
    public ResponseEntity<?> getFiles(@PathVariable Integer id) {
        var stubbedFile = fileService.findById(id);
        return ResponseEntity.ok()
                .body(stubbedFile);
    }

    @PostMapping("/file-utils/files")
    public ResponseEntity<?> addFile(@RequestBody CreateFileRequest newFile) {
        var savedFile = fileService.addFile(newFile.getFileDetail());

        return ResponseEntity.created(URI.create("/file-utils/files/" + savedFile.getId()))
                .body(savedFile);
    }
}

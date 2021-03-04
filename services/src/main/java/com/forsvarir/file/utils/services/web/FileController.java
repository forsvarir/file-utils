package com.forsvarir.file.utils.services.web;

import com.forsvarir.file.utils.services.model.FileDetail;
import com.forsvarir.file.utils.services.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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
    public ResponseEntity<?> addFile(@RequestBody FileDetail newFile)     {
        var savedFile = fileService.save(newFile);
        try {
            return ResponseEntity.created(new URI("/file-utils/files/"+ savedFile.getId()))
                    .body(savedFile);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

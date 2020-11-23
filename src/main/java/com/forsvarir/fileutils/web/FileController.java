package com.forsvarir.fileutils.web;

import com.forsvarir.fileutils.model.FileDetail;
import com.forsvarir.fileutils.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
}

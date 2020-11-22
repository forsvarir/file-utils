package com.forsvarir.fileutils.web;

import com.forsvarir.fileutils.model.FileDetail;
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
    @GetMapping("/file-utils/files/{id}")
    public ResponseEntity<?> getFiles(@PathVariable Integer id) {
        var stubbedFile = new FileDetail("/oh/", "SomeFile");

        try {
            return ResponseEntity.ok()
                    .eTag(Integer.toString(stubbedFile.getId()))
                    .location(new URI("/file-utils/files" + stubbedFile.getPath() + stubbedFile.getName()))
                    .body(stubbedFile);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

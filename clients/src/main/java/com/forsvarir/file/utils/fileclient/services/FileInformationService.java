package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.FileDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileInformationService {
    public FileDetails toFileDetails(Path filePath) {
        return new FileDetails(filePath.getFileName().toString(),
                filePath.getParent().toString(),
                getSize(filePath));
    }

    private long getSize(Path filePath) {
        try {
            return Files.size(filePath);
        } catch (IOException e) {
            // TODO: Need to rework exception as
            // currently it will fail any streams
            throw new RuntimeException(e);
        }
    }
}

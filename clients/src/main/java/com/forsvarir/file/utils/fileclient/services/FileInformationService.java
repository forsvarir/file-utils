package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.FileDetails;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class FileInformationService {
    public FileDetails toFileDetails(Path filePath) {
        return new FileDetails(filePath.toString());
    }
}

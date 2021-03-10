package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.common.utils.file.FileWalker;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class FileDetailsUploader {
    public void processFolder(String pathToProcess) {
        FileWalker.walk(Path.of("/tmp"))
                .forEach(f -> System.out.println(f.toString()));
    }
}

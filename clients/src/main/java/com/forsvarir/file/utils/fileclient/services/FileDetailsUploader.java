package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.common.utils.file.FileWalker;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class FileDetailsUploader {
    Function<Path, Stream<Path>> fileWalker;

    @SuppressWarnings("unused")
    FileDetailsUploader() {
        this( FileWalker::walk);
    }

    FileDetailsUploader(Function<Path, Stream<Path>> fileWalker) {
        this.fileWalker = fileWalker;
    }

    public void processFolder(String pathToProcess) {
        fileWalker.apply(Path.of(pathToProcess))
                .forEach(f -> System.out.println(f.toString()));
    }
}

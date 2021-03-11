package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.common.utils.file.FileWalker;
import com.forsvarir.file.utils.fileclient.services.data.FileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class FileDetailsUploader {
    final private Function<Path, Stream<Path>> fileWalker;

    @Autowired
    private BatchService batchService;

    @Autowired
    private FileDetailsService fileService;

    @SuppressWarnings("unused")
    FileDetailsUploader() {
        this.fileWalker = FileWalker::walk;
    }

    @SuppressWarnings("unused")
    FileDetailsUploader(Function<Path, Stream<Path>> fileWalker,
                        FileDetailsService fileService,
                        BatchService batchService) {
        this.fileWalker = fileWalker;
        this.batchService = batchService;
        this.fileService = fileService;
    }

    public void processFolder(String pathToProcess) {
        var batchInformation = batchService.createNewRun();
        fileWalker.apply(Path.of(pathToProcess))
                .forEach(f -> fileService.createFile(batchInformation.getBatchId(), new FileDetails(f.toString())));
    }
}

package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.BatchInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileDetailsUploaderTest {

    @Mock
    private Function<Path, Stream<Path>> fileWalker;

    @Mock
    private BatchService batchService;

    @Mock
    private FileDetailsService fileService;

    @InjectMocks
    private FileDetailsUploader uploader;

    @BeforeEach
    void beforeEach() {
        when(fileWalker.apply(any())).thenReturn(Stream.empty());
    }

    @Test
    void processFolder_walksSuppliedPath() {
        uploader.processFolder("someFolder");

        verify(fileWalker).apply(Path.of("someFolder"));
    }

    @Test
    void processFolder_createsFilesWithRunId() {
        BatchInformation newBatchInformation = new BatchInformation();
        newBatchInformation.setBatchId(55);
        when(batchService.createNewRun()).thenReturn(newBatchInformation);

        when(fileWalker.apply(any())).thenReturn(Stream.of(
                Path.of("File1"),
                Path.of("File2"),
                Path.of("File3")
        ));
        uploader.processFolder("someFolder");

        verify(fileService, times(3)).createFile(eq(55), any());
    }
}
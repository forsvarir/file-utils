package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.BatchInformation;
import com.forsvarir.file.utils.fileclient.services.data.FileDetails;
import org.jetbrains.annotations.NotNull;
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

    @Mock
    private FileInformationService fileInformationService;

    @InjectMocks
    private FileDetailsUploader uploader;

    @BeforeEach
    void beforeEach() {
        when(fileWalker.apply(any())).thenReturn(Stream.empty());

        BatchInformation newBatchInformation = createBatchInformation(1);
        when(batchService.createNewRun()).thenReturn(newBatchInformation);
    }

    @Test
    void processFolder_walksSuppliedPath() {
        uploader.processFolder("someFolder");

        verify(fileWalker).apply(Path.of("someFolder"));
    }

    @Test
    void processFolder_newRun_createsFilesWithRunId() {
        BatchInformation newBatchInformation = createBatchInformation(55);
        when(batchService.createNewRun()).thenReturn(newBatchInformation);

        when(fileWalker.apply(any())).thenReturn(Stream.of(
                Path.of("File1"),
                Path.of("File2"),
                Path.of("File3")
        ));
        uploader.processFolder("someFolder");

        verify(fileService, times(3)).createFile(eq(55), any());
    }

    @Test
    void processFolder_newRun_getsFileDetailsFromEachPath() {
        when(fileWalker.apply(any())).thenReturn(Stream.of(
                Path.of("File1"),
                Path.of("File2"),
                Path.of("File3")
        ));
        uploader.processFolder("someFolder");

        verify(fileInformationService).toFileDetails(Path.of("File1"));
    }

    @Test
    void processFolder_newRun_storesFileDetails() {
        FileDetails fileDetail1 = createDetails("File1");
        FileDetails fileDetail2 = createDetails("File2");
        FileDetails fileDetail3 = createDetails("File3");
        when(fileWalker.apply(any())).thenReturn(Stream.of(
                Path.of("aFile"),
                Path.of("aFile"),
                Path.of("aFile")
        ));
        when(fileInformationService.toFileDetails(any()))
                .thenReturn(fileDetail1)
                .thenReturn(fileDetail2)
                .thenReturn(fileDetail3);
        uploader.processFolder("someFolder");

        verify(fileService).createFile(anyInt(), eq(fileDetail1));
        verify(fileService).createFile(anyInt(), eq(fileDetail2));
        verify(fileService).createFile(anyInt(), eq(fileDetail3));
    }

    @NotNull
    private FileDetails createDetails(String fileName) {
        return new FileDetails(fileName, "", 0);
    }


    @NotNull
    private BatchInformation createBatchInformation(int batchId) {
        BatchInformation newBatchInformation = new BatchInformation();
        newBatchInformation.setBatchId(batchId);
        return newBatchInformation;
    }
}
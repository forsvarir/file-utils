package com.forsvarir.file.utils.fileclient;

import com.forsvarir.file.utils.fileclient.services.FileDetailsUploader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileWalkerApplicationTest {
    @Mock
    FileDetailsUploader mockUploader;

    @InjectMocks
    FileWalkerApplication application;

    @Test
    void run_pathSupplied_walksTargetFolder() {
        application.run("/targetFolderToProcess");

        verify(mockUploader).processFolder("/targetFolderToProcess");
    }

    @Test
    void run_noPathSupplied_walksDefaultFolder() {
        application.run();

        verify(mockUploader).processFolder(FileWalkerApplication.DEFAULT_SEARCH_FOLDER);
    }
}
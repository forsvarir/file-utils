package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import com.forsvarir.file.utils.services.db.FileRepository;
import com.forsvarir.file.utils.services.db.model.FileInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    FileRepository repository;

    @InjectMocks
    FileService service;

    @Test
    void addFile_returnsSavedInstance() {
        final long expectedId = 55L;
        final long expectedSize = 99L;
        FileInformation savedDatabaseInstance = new FileInformation("Name", "Path", expectedSize, expectedId);
        when(repository.save(any())).thenReturn(savedDatabaseInstance);

        var returnedFileInformation = service.addFile(new FileDetail());

        assertThat(returnedFileInformation.getId()).isEqualTo(expectedId);
        assertThat(returnedFileInformation.getPath()).isEqualTo("Path");
        assertThat(returnedFileInformation.getName()).isEqualTo("Name");
        assertThat(returnedFileInformation.getSize()).isEqualTo(expectedSize);
    }

    @Test
    void addFile_savesNewInformation() {
        final long expectedSize = 99L;
        FileDetail fileDetail = new FileDetail("Name", "Path", expectedSize);
        ArgumentCaptor<FileInformation> fileInformationCaptor = ArgumentCaptor.forClass(FileInformation.class);

        when(repository.save(any())).thenReturn(new FileInformation());

        service.addFile(fileDetail);

        verify(repository).save(fileInformationCaptor.capture());
        assertThat(fileInformationCaptor.getAllValues()).hasSize(1);
        var capturedInformation = fileInformationCaptor.getValue();

        assertThat(capturedInformation.getId()).isEqualTo(0);
        assertThat(capturedInformation.getPath()).isEqualTo("Path");
        assertThat(capturedInformation.getName()).isEqualTo("Name");
        assertThat(capturedInformation.getSize()).isEqualTo(expectedSize);
    }
}
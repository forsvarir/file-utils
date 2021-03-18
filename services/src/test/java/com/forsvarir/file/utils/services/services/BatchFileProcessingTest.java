package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import org.junit.jupiter.api.BeforeEach;
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
class BatchFileProcessingTest {
    @Mock
    BatchService batchService;

    @Mock
    FileDetailService fileDetailService;

    @InjectMocks
    BatchFileProcessing service;

    @BeforeEach
    void beforeEach() {
        when(fileDetailService.addFile(any())).thenReturn(new FileDetail());
    }

    @Test
    void addFileToBatch_savesFile() {
        FileDetail fileToAdd = new FileDetail("aFile", "aPath", 55L, 77L);

        service.addFileToBatch(fileToAdd, 999L);

        ArgumentCaptor<FileDetail> fileDetailCaptor = ArgumentCaptor.forClass(FileDetail.class);
        verify(fileDetailService).addFile(fileDetailCaptor.capture());

        assertThat(fileDetailCaptor.getAllValues()).hasSize(1);
        var capturedInformation = fileDetailCaptor.getValue();

        assertThat(capturedInformation.getId()).isEqualTo(77L);
        assertThat(capturedInformation.getPath()).isEqualTo("aPath");
        assertThat(capturedInformation.getName()).isEqualTo("aFile");
        assertThat(capturedInformation.getSize()).isEqualTo(55L);
        assertThat(capturedInformation.getClientId()).isEqualTo(999L);
    }

    @Test
    void addFileToBatch_returnsSavedFileDetails() {
        FileDetail expectedFileDetail = new FileDetail("aFile", "aPath", 55L, 77L, 99L);

        when(fileDetailService.addFile(any())).thenReturn(expectedFileDetail);

        var savedBatch = service.addFileToBatch(new FileDetail(), 0L);

        assertThat(savedBatch.getId()).isEqualTo(77L);
        assertThat(savedBatch.getPath()).isEqualTo("aPath");
        assertThat(savedBatch.getName()).isEqualTo("aFile");
        assertThat(savedBatch.getSize()).isEqualTo(55L);
        assertThat(savedBatch.getClientId()).isEqualTo(99L);
    }
}
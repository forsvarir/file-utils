package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.common.api.data.FileDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileDetailsServiceTest {
    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    FileDetailsService fileDetailsService;

    @BeforeEach
    void beforeEach() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    void createNewFile_callsRemoteAPI() {
        FileDetail fileDetail = new FileDetail("a file", "a path", 55L, 53L, 0L);

        fileDetailsService.createFile(fileDetail);

        ArgumentCaptor<FileDetail> createFileCaptor = ArgumentCaptor.forClass(FileDetail.class);
        verify(restTemplate).postForObject(eq(FileDetailsService.SERVICE_URL), createFileCaptor.capture(), eq(FileDetail.class));

        var capturedRequest = createFileCaptor.getValue();
        assertThat(capturedRequest.getClientId()).isEqualTo(53);
        assertThat(capturedRequest.getName()).isEqualTo("a file");
        assertThat(capturedRequest.getPath()).isEqualTo("a path");
        assertThat(capturedRequest.getSize()).isEqualTo(55L);
    }

    @Test
    void createNewFile_returnsAPIResult() {
        FileDetail fileDetail = new FileDetail("a file", "a path", 55L, 53L);

        FileDetail expectedFileDetail = new FileDetail();
        when(restTemplate.postForObject(any(String.class), any(), any())).thenReturn(expectedFileDetail);

        var returnedBatchInformation = fileDetailsService.createFile(fileDetail);

        assertThat(returnedBatchInformation).isSameAs(expectedFileDetail);
    }
}
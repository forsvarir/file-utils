package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.BatchInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    BatchService batchService;

    @BeforeEach
    void beforeEach() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    void createNewRun_callsRemoteAPI() {
        batchService.createNewRun();

        verify(restTemplate).postForObject(BatchService.SERVICE_URL, null, BatchInformation.class);
    }

    @Test
    void createNewRun_returnsAPIResult() {
        BatchInformation expectedBatchInformation = new BatchInformation();
        when(restTemplate.postForObject(any(String.class), any(), any(Class.class))).thenReturn(expectedBatchInformation);

        var returnedBatchInformation = batchService.createNewRun();

        assertThat(returnedBatchInformation).isSameAs(expectedBatchInformation);
    }
}
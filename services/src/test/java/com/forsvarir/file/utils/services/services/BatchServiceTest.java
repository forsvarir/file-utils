package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.services.db.BatchRepository;
import com.forsvarir.file.utils.services.db.model.Batch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

    @Mock
    BatchRepository batchRepository;

    @InjectMocks
    BatchService service;

    @Test
    void create_returnsNewBatch() {
        Batch databaseBatch = new Batch(88);
        when(batchRepository.save(any())).thenReturn(databaseBatch);

        var createdBatch = service.create();

        assertThat(createdBatch.getId()).isEqualTo(databaseBatch.getId());
    }
}
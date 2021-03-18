package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.services.db.BatchItemRepository;
import com.forsvarir.file.utils.services.db.BatchRepository;
import com.forsvarir.file.utils.services.db.model.Batch;
import com.forsvarir.file.utils.services.db.model.BatchItem;
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
class BatchServiceTest {

    @Mock
    BatchRepository batchRepository;

    @Mock
    BatchItemRepository itemRepository;

    @InjectMocks
    BatchService service;

    @Test
    void create_returnsNewBatch() {
        Batch databaseBatch = new Batch(88);
        when(batchRepository.save(any())).thenReturn(databaseBatch);

        var createdBatch = service.create();

        assertThat(createdBatch.getId()).isEqualTo(databaseBatch.getId());
    }

    @Test
    void addItem_savesItemDetails() {
        ArgumentCaptor<BatchItem> batchItemCaptor = ArgumentCaptor.forClass(BatchItem.class);

        service.addItem(55L, "File", 99L);

        verify(itemRepository).save(batchItemCaptor.capture());

        assertThat(batchItemCaptor.getAllValues()).hasSize(1);
        var capturedInformation = batchItemCaptor.getValue();

        assertThat(capturedInformation.getBatchId()).isEqualTo(55L);
        assertThat(capturedInformation.getItemType()).isEqualTo("File");
        assertThat(capturedInformation.getItemId()).isEqualTo(99L);
    }
}
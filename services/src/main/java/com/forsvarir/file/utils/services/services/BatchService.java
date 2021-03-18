package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.BatchDetail;
import com.forsvarir.file.utils.services.db.BatchItemRepository;
import com.forsvarir.file.utils.services.db.BatchRepository;
import com.forsvarir.file.utils.services.db.model.Batch;
import com.forsvarir.file.utils.services.db.model.BatchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;

    @Autowired
    BatchItemRepository batchItemRepository;

    public BatchDetail create() {
        return new BatchDetail(batchRepository.save(new Batch()).getId());
    }

    public void addItem(long batchId, String itemType, long itemIdentifier) {
        batchItemRepository.save(new BatchItem(batchId, itemType, itemIdentifier));
    }
}

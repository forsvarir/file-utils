package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.BatchDetail;
import com.forsvarir.file.utils.services.db.BatchRepository;
import com.forsvarir.file.utils.services.db.model.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;

    public BatchDetail create() {
        return new BatchDetail(batchRepository.save(new Batch()).getId());
    }
}

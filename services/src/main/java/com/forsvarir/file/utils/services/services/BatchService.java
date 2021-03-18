package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.BatchDetail;
import com.forsvarir.file.utils.services.db.BatchItemRepository;
import com.forsvarir.file.utils.services.db.BatchRepository;
import com.forsvarir.file.utils.services.db.model.Batch;
import com.forsvarir.file.utils.services.db.model.BatchFileItem;
import com.forsvarir.file.utils.services.db.model.FileInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;

    @Autowired
    BatchItemRepository batchItemRepository;

    public BatchDetail create() {
        return new BatchDetail(batchRepository.save(new Batch()).getId());
    }

    public void addItem(long batchId, FileIdentifier item) {
        var batch = batchRepository.findById(batchId);
        batchItemRepository.save(new BatchFileItem(batch.get(), item.getId()));
    }

    public List<FileInformation> allFiles(Integer batchid) {
        return batchItemRepository.findAllFileInformationByBatchId(batchid)
                .stream().map(b -> b.getFile())
                .collect(Collectors.toList());
    }
}

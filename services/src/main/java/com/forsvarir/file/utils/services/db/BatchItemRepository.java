package com.forsvarir.file.utils.services.db;

import com.forsvarir.file.utils.services.db.model.BatchFileItem;
import com.forsvarir.file.utils.services.db.model.BatchItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchItemRepository extends CrudRepository<BatchItem, Long> {

    List<BatchFileItem> findAllFileInformationByBatchId(long batchId);
}

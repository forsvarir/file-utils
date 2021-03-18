package com.forsvarir.file.utils.services.db;

import com.forsvarir.file.utils.services.db.model.BatchItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchItemRepository extends CrudRepository<BatchItem, Long> {
}

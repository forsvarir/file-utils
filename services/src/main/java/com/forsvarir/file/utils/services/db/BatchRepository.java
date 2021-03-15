package com.forsvarir.file.utils.services.db;

import com.forsvarir.file.utils.services.db.model.Batch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends CrudRepository<Batch, Long> {
}

package com.forsvarir.file.utils.services.db;

import com.forsvarir.file.utils.services.db.model.FileInformation;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileInformation, Long> {
}

package com.forsvarir.file.utils.services.db;

import com.forsvarir.file.utils.services.db.model.FileInformation;
import org.springframework.data.repository.CrudRepository;

public interface FileInformationRepository extends CrudRepository<FileInformation, Long> {
}

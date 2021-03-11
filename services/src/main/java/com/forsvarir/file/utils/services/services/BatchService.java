package com.forsvarir.file.utils.services.services;

import com.forsvarir.file.utils.common.api.data.BatchDetail;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    public BatchDetail create() {
        return new BatchDetail(1);
    }
}

package com.forsvarir.file.utils.fileclient.services;

import com.forsvarir.file.utils.fileclient.services.data.BatchInformation;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    public BatchInformation createNewRun() {
        return new BatchInformation();
    }
}

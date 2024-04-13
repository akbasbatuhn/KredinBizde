package com.patika.errorlogservice.service;

import com.patika.errorlogservice.entity.ErrorLog;
import com.patika.errorlogservice.repository.ErrorLogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final ErrorLogRepository repository;

    public LogService(ErrorLogRepository repository) {
        this.repository = repository;
    }

    public ErrorLog save(ErrorLog errorLog) {
        return repository.save(errorLog);
    }
}

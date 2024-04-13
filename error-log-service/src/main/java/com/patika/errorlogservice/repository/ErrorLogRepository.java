package com.patika.errorlogservice.repository;

import com.patika.errorlogservice.entity.ErrorLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorLogRepository extends MongoRepository<ErrorLog, Integer> {
}

package com.patika.akbankservice.repository;

import com.patika.akbankservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}

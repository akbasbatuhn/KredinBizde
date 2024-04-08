package com.patika.garantiservice.repository;

import com.patika.garantiservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUserId(Long userId);
    List<Application> findAllByUserId(Long userId);
}

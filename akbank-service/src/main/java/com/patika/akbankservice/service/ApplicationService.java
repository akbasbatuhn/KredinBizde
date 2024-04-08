package com.patika.akbankservice.service;

import com.patika.akbankservice.converter.ApplicationConverter;
import com.patika.akbankservice.dto.request.ApplicationRequest;
import com.patika.akbankservice.dto.response.ApplicationResponse;
import com.patika.akbankservice.entity.Application;
import com.patika.akbankservice.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ApplicationService {

    private final ApplicationRepository repository;

    private final ApplicationConverter applicationConverter;

    public ApplicationService(ApplicationRepository repository, ApplicationConverter applicationConverter) {
        this.repository = repository;
        this.applicationConverter = applicationConverter;
    }

    @Transactional
    public ApplicationResponse createApplication(ApplicationRequest request) {
        Application application = repository.save(applicationConverter.toApplication(request));
        return applicationConverter.toResponse(application);
    }

    public List<ApplicationResponse> getAll() {
        return repository.findAll().stream()
                .map(application -> applicationConverter.toResponse(application))
                .toList();
    }

    public List<ApplicationResponse> getApplicationsByIdUserId(Long userId) {
        if(checkUserRecordExists(userId)) throw new RuntimeException("User record not found with id: " + userId);

        return repository.findAllByUserId(userId).stream()
                .map(applicationConverter::toResponse)
                .toList();
    }

    public ApplicationResponse getApplicationById(Long id) {
        Application application = findApplicationById(id);

        log.info("Application found with id: {}", id);
        return applicationConverter.toResponse(application);
    }

    private Application findApplicationById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id " + id)
        );
    }

    private boolean checkUserRecordExists(Long userId) {
        return repository.findAllByUserId(userId).isEmpty();
    }

    @Transactional
    public ApplicationResponse updateApplication(Application application) {
        Application found = findApplicationById(application.getId());

        found.setApplicationStatus(application.getApplicationStatus());
        Application newApplication = repository.save(found);

        return applicationConverter.toResponse(newApplication);
    }

    @Transactional
    public void deleteApplicationById(Long id) {
        Application application = findApplicationById(id);

        repository.delete(application);
        log.info("Application deleted with id: {}", id);
    }
}

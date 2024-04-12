package com.patika.applicationservice.service;

import com.patika.applicationservice.dto.converter.ApplicationDTOConverter;
import com.patika.applicationservice.dto.request.ApplicationRequest;
import com.patika.applicationservice.dto.response.ApplicationResponseDTO;
import com.patika.applicationservice.entity.Application;
import com.patika.applicationservice.exception.ExceptionMessages;
import com.patika.applicationservice.exception.ResourceNotFoundException;
import com.patika.applicationservice.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ApplicationService implements IApplicationService {

    private final ApplicationRepository repository;
    private final ExternalAPIService externalAPIService;


    public ApplicationService(ApplicationRepository repository, ExternalAPIService externalAPIService) {
        this.repository = repository;
        this.externalAPIService = externalAPIService;
    }

    @Override
    public ApplicationResponseDTO createApplication(ApplicationRequest request) {
        externalAPIService.createApplication(request);

        Application newApplication = ApplicationDTOConverter.toApplication(request);
        Application saved = repository.save(newApplication);

        log.info("Application saved with id: {}", saved.getId());

        return ApplicationDTOConverter.toResponseDTO(saved);
    }

    @Override
    public ApplicationResponseDTO getApplicationById(Long id) {
        Application found = findApplicationById(id);
        return ApplicationDTOConverter.toResponseDTO(found);
    }

    // Add pagination to this method
    @Override
    public List<ApplicationResponseDTO> getAllApplications() {
        return repository.findAll().stream()
                .map(ApplicationDTOConverter::toResponseDTO)
                .toList();
    }

    @Override
    public ApplicationResponseDTO updateApplication(Long id, ApplicationRequest requestDTO) {
        Application found = findApplicationById(id);

        Application newApplication = repository.save(found);

        return ApplicationDTOConverter.toResponseDTO(newApplication);
    }

    @Override
    public void deleteById(Long id) {
        Application application = findApplicationById(id);
        repository.delete(application);
        log.info("Application deleted with id: {}", id);
    }

    public Application findApplicationById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionMessages.APPLICATION_NOT_FOUND)
        );
    }
}

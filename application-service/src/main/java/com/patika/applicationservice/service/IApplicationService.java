package com.patika.applicationservice.service;

import com.patika.applicationservice.dto.request.ApplicationRequest;
import com.patika.applicationservice.dto.response.ApplicationResponseDTO;

import java.util.List;

public interface IApplicationService {

    ApplicationResponseDTO createApplication(ApplicationRequest requestDTO);
    ApplicationResponseDTO getApplicationById(Long id);
    List<ApplicationResponseDTO> getAllApplications();
    ApplicationResponseDTO updateApplication(Long id, ApplicationRequest requestDTO);
    void deleteById(Long id);
}

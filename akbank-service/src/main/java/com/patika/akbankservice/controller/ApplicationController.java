package com.patika.akbankservice.controller;

import com.patika.akbankservice.dto.ControllerResponseDTO;
import com.patika.akbankservice.dto.request.ApplicationRequest;
import com.patika.akbankservice.dto.response.ApplicationResponse;
import com.patika.akbankservice.entity.Application;
import com.patika.akbankservice.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/akbank/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponse createApplication(@Valid @RequestBody ApplicationRequest request) {
        return applicationService.createApplication(request);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAll() {
        return ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(applicationService.getApplicationById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(applicationService.getApplicationsByIdUserId(userId));
    }

    @PutMapping
    public ResponseEntity<ApplicationResponse> updateApplication(@RequestBody Application application) {
        return ResponseEntity.ok().body(applicationService.updateApplication(application));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.ok().body(new ControllerResponseDTO(HttpStatus.OK, "Application deleted"));
    }

}

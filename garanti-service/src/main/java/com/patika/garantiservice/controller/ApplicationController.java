package com.patika.garantiservice.controller;

import com.patika.garantiservice.dto.ControllerResponseDTO;
import com.patika.garantiservice.dto.request.ApplicationRequest;
import com.patika.garantiservice.dto.response.ApplicationResponse;
import com.patika.garantiservice.entity.Application;
import com.patika.garantiservice.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/garanti/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest request) {
        return applicationService.createApplication(request);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAll() {
        return ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApplicationResponse> getApplicationByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(applicationService.getApplicationByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<ApplicationResponse> updateApplication(@RequestBody Application application) {
        return ResponseEntity.ok().body(applicationService.updateApplication(application));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<ControllerResponseDTO> deleteApplicationByUserId(@PathVariable Long userId) {
        applicationService.deleteApplicationById(userId);
        return ResponseEntity.ok().body(new ControllerResponseDTO(HttpStatus.OK, "Application deleted"));
    }

}

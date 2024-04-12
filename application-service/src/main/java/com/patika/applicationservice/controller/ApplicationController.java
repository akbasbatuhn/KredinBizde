package com.patika.applicationservice.controller;

import com.patika.applicationservice.dto.request.ApplicationRequest;
import com.patika.applicationservice.dto.response.ApplicationResponseDTO;
import com.patika.applicationservice.dto.response.ControllerResponseDTO;
import com.patika.applicationservice.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ApplicationResponseDTO> garantiApplication(@RequestBody ApplicationRequest request) {
        return ResponseEntity.ok().body(service.createApplication(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getApplicationById(id));
    }

    // TODO Add pageable
    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
        return ResponseEntity.ok().body(service.getAllApplications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> updateApplication(@PathVariable Long id, @RequestBody ApplicationRequest request) {
        return ResponseEntity.ok().body(service.updateApplication(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> deleteApplication(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().body(new ControllerResponseDTO(HttpStatus.OK, "Successfully deleted"));
    }
}

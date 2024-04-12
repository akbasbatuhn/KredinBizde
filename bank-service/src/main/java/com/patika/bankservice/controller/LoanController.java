package com.patika.bankservice.controller;

import com.patika.bankservice.dto.response.ControllerResponseDTO;
import com.patika.bankservice.dto.response.LoanResponseDTO;
import com.patika.bankservice.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {


    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getLoanById(id));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAllApplications() {
        return ResponseEntity.ok().body(service.getAllLoans());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> deleteApplication(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().body(new ControllerResponseDTO(HttpStatus.OK, "Successfully deleted"));
    }
}

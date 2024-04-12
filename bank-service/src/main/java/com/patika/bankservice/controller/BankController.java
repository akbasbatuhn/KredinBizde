package com.patika.bankservice.controller;

import com.patika.bankservice.dto.response.BankResponseDTO;
import com.patika.bankservice.dto.response.ControllerResponseDTO;
import com.patika.bankservice.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banks")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankResponseDTO> getBankById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getBankById(id));
    }

    //TODO pagination
    @GetMapping
    public ResponseEntity<List<BankResponseDTO>> getAllBanks() {
        return ResponseEntity.ok().body(service.getAllBanks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> deleteBank(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().body(new ControllerResponseDTO(HttpStatus.OK, "Successfully deleted"));
    }
}

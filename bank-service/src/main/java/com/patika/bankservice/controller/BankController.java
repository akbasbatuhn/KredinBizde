package com.patika.bankservice.controller;

import com.patika.bankservice.dto.ErrorResponseDTO;
import com.patika.bankservice.dto.response.BankResponseDTO;
import com.patika.bankservice.dto.response.ControllerResponseDTO;
import com.patika.bankservice.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Bank in Kredinbizde",
        description = "CRUD REST APIs in Kredinbizde to GET and DELETE bank details"
)
@RestController
@RequestMapping("/api/v1/banks")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @Operation(
            summary = "Get Bank details",
            description = "REST API to get Bank by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<BankResponseDTO> getBankById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getBankById(id));
    }

    @Operation(
            summary = "Get Bank details",
            description = "REST API to get Banks"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    //TODO pagination
    @GetMapping
    public ResponseEntity<List<BankResponseDTO>> getAllBanks() {
        return ResponseEntity.ok().body(service.getAllBanks());
    }

    @Operation(
            summary = "Delete Bank details",
            description = "REST API to delete Bank by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> deleteBank(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().body(new ControllerResponseDTO(HttpStatus.OK, "Successfully deleted"));
    }
}

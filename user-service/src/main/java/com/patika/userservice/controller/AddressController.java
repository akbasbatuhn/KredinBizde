package com.patika.userservice.controller;

import com.patika.userservice.dto.ControllerResponseDTO;
import com.patika.userservice.entity.Address;
import com.patika.userservice.service.AddressService;
import org.bson.types.ObjectId;
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
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        return ResponseEntity.ok().body(service.save(address));
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok().body(service.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        return ResponseEntity.ok().body(service.getAddressById(objectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddressDetails(@PathVariable String id, @RequestBody Address address) {
        ObjectId objectId = new ObjectId(id);
        return ResponseEntity.ok().body(service.updateAddressDetails(objectId, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> saveAddress(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        service.deleteAddress(objectId);
        return ResponseEntity.ok().body(new ControllerResponseDTO(HttpStatus.OK, "Address successfully deleted"));
    }
}

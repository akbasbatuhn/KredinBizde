package com.patika.userservice.service;

import com.patika.userservice.entity.Address;
import com.patika.userservice.exception.ExceptionMessages;
import com.patika.userservice.exception.ResourceNotFoundException;
import com.patika.userservice.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        log.info("Address saved with id: {}", address.getId());
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(ObjectId id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionMessages.ADDRESS_NOT_FOUND)
        );
    }

    @Override
    public Address updateAddressDetails(ObjectId id, Address address) {
        Address found = getAddressById(id);

        found.setAddressDescription(address.getAddressDescription());
        found.setAddressTitle(address.getAddressTitle());
        found.setProvince(address.getProvince());

        Address newAddress = addressRepository.save(found);
        log.info("Address saved with id: {}", address.getId());
        return newAddress;
    }

    @Override
    public void deleteAddress(ObjectId id) {
        Address found = getAddressById(id);

        addressRepository.delete(found);
        log.info("Address deleted with id: {}", id);
    }
}

package com.patika.userservice.service;

import com.patika.userservice.entity.Address;
import org.bson.types.ObjectId;

import java.util.List;

public interface IAddressService {

    Address save(Address address);
    List<Address> getAllAddresses();
    Address getAddressById(ObjectId id);
    Address updateAddressDetails(ObjectId id, Address address);
    void deleteAddress(ObjectId id);
}

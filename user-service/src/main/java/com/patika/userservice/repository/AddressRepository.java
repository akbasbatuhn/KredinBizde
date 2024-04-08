package com.patika.userservice.repository;

import com.patika.userservice.entity.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, ObjectId> {
}

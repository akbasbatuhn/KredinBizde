package com.patika.userservice;

import com.patika.userservice.entity.Address;
import org.bson.types.ObjectId;

public class TestSupport {

    public final ObjectId objectId = new ObjectId("66145e8bfed0f514eefdd0cc");

    public Address generateAddress() {
        Address address = new Address();

        address.setAddressDescription("Home");
        address.setProvince("Izmir");
        address.setAddressTitle("Home");
        return address;
    }

    public Address generateUpdatedAddress() {
        Address address = new Address();

        address.setAddressDescription("Home");
        address.setProvince("Istanbul");
        address.setAddressTitle("Home");
        return address;
    }
}

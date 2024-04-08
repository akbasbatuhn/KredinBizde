package com.patika.userservice;

import com.patika.userservice.entity.Address;
import com.patika.userservice.entity.User;
import org.bson.types.ObjectId;

import java.time.LocalDate;

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

    public User generateUser() {
        return User.builder()
                .id(new ObjectId("66145e8bfed0f514eefdd0cc"))
                .name("Batuhan")
                .surname("Akbaş")
                .email("batuhanakbas@gmail.com")
                .addressId("6614563c7a2c9f397df46e77")
                .birthDate(LocalDate.of(1997, 11, 11))
                .isActive(true)
                .password("12345")
                .phoneNumber("+905055055050")
                .build();
    }

    public User generateUpdatedUser() {
        return User.builder()
                .id(new ObjectId("66145e8bfed0f514eefdd0cc"))
                .name("Batuhan")
                .surname("Akbaş")
                .email("batuhanakbas@gmail.com")
                .addressId("6614563c7a2c9f397df46e77")
                .birthDate(LocalDate.of(1997, 11, 11))
                .isActive(true)
                .password("1234567890")
                .phoneNumber("+905555555555")
                .build();
    }
}

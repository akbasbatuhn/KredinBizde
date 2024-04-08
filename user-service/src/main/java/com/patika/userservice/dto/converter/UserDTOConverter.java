package com.patika.userservice.dto.converter;

import com.patika.userservice.dto.UserDTO;
import com.patika.userservice.entity.User;

public class UserDTOConverter {

    private UserDTOConverter(){}

    public static UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .id(user.getId())
                .email(user.getEmail())
                .addressId(user.getAddressId())
                .birthDate(user.getBirthDate())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}

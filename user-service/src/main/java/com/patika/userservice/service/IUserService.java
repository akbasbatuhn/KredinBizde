package com.patika.userservice.service;

import com.patika.userservice.dto.UserDTO;
import com.patika.userservice.entity.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface IUserService {

    UserDTO save(User user);
    List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);
    UserDTO updateUserDetails(User user);

    void deleteUserById(ObjectId id);
}

package com.patika.userservice.service;

import com.patika.userservice.TestSupport;
import com.patika.userservice.dto.UserDTO;
import com.patika.userservice.entity.User;
import com.patika.userservice.exception.ExceptionMessages;
import com.patika.userservice.exception.ResourceAlreadyExistException;
import com.patika.userservice.exception.ResourceNotFoundException;
import com.patika.userservice.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest extends TestSupport {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User userObj;
    private User updatedUserObj;

    @BeforeEach
    void setup() {
        userObj = generateUser();
        updatedUserObj = generateUpdatedUser();
    }

    @Test
    void testCreateUser_whenEverythingValid_shouldReturnUser() {
        when(userRepository.save(any(User.class))).thenReturn(userObj);

        UserDTO userResponse = userService.save(userObj);

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getAddressId()).isEqualTo(userObj.getAddressId());
        assertThat(userResponse.getName()).isEqualTo(userObj.getName());
        assertThat(userResponse.getSurname()).isEqualTo(userObj.getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(userObj.getEmail());
        assertThat(userResponse.getBirthDate()).isEqualTo(userObj.getBirthDate());
        assertThat(userResponse.getPhoneNumber()).isEqualTo(userObj.getPhoneNumber());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_whenEmailAlreadyExist_shouldThrowException() {
        when(userRepository.save(any(User.class)))
                .thenThrow(new ResourceAlreadyExistException(ExceptionMessages.ALREADY_EXIST + " with email " + userObj.getEmail()));

        ResourceAlreadyExistException exception = assertThrows(ResourceAlreadyExistException.class, () -> {
            userService.save(userObj);
        });

        String expectedMessage = ExceptionMessages.ALREADY_EXIST + " with email " + userObj.getEmail();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetAllUsers_whenUsersExist_shouldReturnListOfUserDTOs() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(userObj, updatedUserObj));

        List<UserDTO> userList = userService.getAllUsers();

        assertNotEquals(0, userList.size());
        assertEquals(2, userList.size());
        assertEquals("+905055055050", userList.get(0).getPhoneNumber());
        assertEquals("+905555555555", userList.get(1).getPhoneNumber());
    }

    @Test
    void testGetUserById_whenUserExistsWithId_shouldReturnUser() {

        //given
        when(userRepository.findById(any(ObjectId.class))).thenReturn(Optional.of(userObj));

        //when
        User user = userService.getById(objectId);

        //then
        assertThat(user).isNotNull();
        assertThat(user.getAddressId()).isEqualTo(userObj.getAddressId());
        assertThat(user.getName()).isEqualTo(userObj.getName());
        assertThat(user.getSurname()).isEqualTo(userObj.getSurname());
        assertThat(user.getEmail()).isEqualTo(userObj.getEmail());
        assertThat(user.getBirthDate()).isEqualTo(userObj.getBirthDate());
        assertThat(user.getPhoneNumber()).isEqualTo(userObj.getPhoneNumber());

        verify(userRepository, times(1)).findById(objectId);
    }

    @Test
    void testGetUserByEmail_whenUserExistsWithEmail_shouldReturnUserDTO() {
        //given
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userObj));

        //when
        UserDTO user = userService.getUserByEmail(userObj.getEmail());

        //then
        assertThat(user).isNotNull();
        assertThat(user.getAddressId()).isEqualTo(userObj.getAddressId());
        assertThat(user.getName()).isEqualTo(userObj.getName());
        assertThat(user.getSurname()).isEqualTo(userObj.getSurname());
        assertThat(user.getEmail()).isEqualTo(userObj.getEmail());
        assertThat(user.getBirthDate()).isEqualTo(userObj.getBirthDate());
        assertThat(user.getPhoneNumber()).isEqualTo(userObj.getPhoneNumber());

        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void testGetUserByEmail_whenUserNotExistsWithEmail_shouldThrowException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserByEmail(userObj.getEmail());
        });

        String expectedMessage = ExceptionMessages.USER_NOT_FOUND + " with email : " + userObj.getEmail();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetUserById_whenUserNotExist_shouldThrowException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.getById(objectId);
        });

        String expectedMessage = "User not found with id : 66145e8bfed0f514eefdd0cc";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testUpdateUserDetails_whenUserExist_shouldReturnUser() {
        //given
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userObj));
        when(userRepository.save(any(User.class))).thenReturn(updatedUserObj);

        //when
        UserDTO updatedUser = userService.updateUserDetails(userObj);

        //then
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getAddressId()).isEqualTo(updatedUserObj.getAddressId());
        assertThat(updatedUser.getName()).isEqualTo(updatedUserObj.getName());
        assertThat(updatedUser.getSurname()).isEqualTo(updatedUserObj.getSurname());
        assertThat(updatedUser.getEmail()).isEqualTo(updatedUserObj.getEmail());
        assertThat(updatedUser.getBirthDate()).isEqualTo(updatedUserObj.getBirthDate());
        assertThat(updatedUser.getPhoneNumber()).isEqualTo(updatedUserObj.getPhoneNumber());

        verify(userRepository, times(1)).findByEmail(updatedUserObj.getEmail());
    }

    @Test
    void testDeleteAddress_whenAddressExist_shouldDeleteAddress() {
        //when
        when(userRepository.findById(any(ObjectId.class))).thenReturn(Optional.ofNullable(userObj));

        //then
        assertAll(() -> userService.deleteUserById(objectId));
    }


}
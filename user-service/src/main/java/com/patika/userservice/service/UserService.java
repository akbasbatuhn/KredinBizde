package com.patika.userservice.service;

//import com.patika.userservice.dto.NotificationDTO;
import com.patika.userservice.dto.UserDTO;
import com.patika.userservice.dto.converter.UserDTOConverter;
import com.patika.userservice.entity.Address;
import com.patika.userservice.entity.User;
//import com.patika.userservice.enums.NotificationType;
import com.patika.userservice.exception.ExceptionMessages;
import com.patika.userservice.exception.ResourceAlreadyExistException;
import com.patika.userservice.exception.ResourceNotFoundException;
import com.patika.userservice.repository.UserRepository;;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@Scope(value = "singleton")
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //private final NotificationProducer notificationProducer;

    @Transactional
    @Override
    public UserDTO save(User user) {
        checkUserExistWithEmail(user.getEmail());

        userRepository.save(user);

        //notificationProducer.sendNotification(prepareNotificationDTO(NotificationType.EMAIL, user.getEmail()));
        return UserDTOConverter.userToUserDTO(user);
    }

    private void checkUserExistWithEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent())
            throw new ResourceAlreadyExistException(ExceptionMessages.ALREADY_EXIST + " with email " + email);
    }

    private Address getAddress() {
        Address address = new Address();
        address.setAddressTitle("Home");
        address.setAddressDescription("izmir");
        address.setProvince("izmir");
        return address;
    }

    /*
    private NotificationDTO prepareNotificationDTO(NotificationType notificationType, String email) {
        return NotificationDTO.builder()
                .message("user kaydedildi.")
                .notificationType(notificationType)
                .email(email)
                .build();
    }
    */

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserDTOConverter.userToUserDTO(user))
                .toList();
    }

    @Cacheable(value = "users", key = "#email")
    @Override
    public UserDTO getUserByEmail(String email) {
        return UserDTOConverter.userToUserDTO(findUserByEmail(email));
    }

    public User findUserByEmail(String email) {
        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(ExceptionMessages.USER_NOT_FOUND + " with email : " + email)
                );

        log.info("User found with email : {}", email);
        return foundUser;
    }

    @Transactional
    @Override
    public UserDTO updateUserDetails(User user) {
        User foundUser = findUserByEmail(user.getEmail());

        foundUser.setPassword(user.getPassword());
        foundUser.setName(user.getName());
        foundUser.setSurname(user.getSurname());
        foundUser.setEmail(user.getEmail());
        foundUser.setAddressId(user.getAddressId());
        foundUser.setBirthDate(user.getBirthDate());
        foundUser.setPhoneNumber(user.getPhoneNumber());

        User newUser = userRepository.save(foundUser);

        log.info("User details updated with id: {}", newUser.getId());
        return UserDTOConverter.userToUserDTO(newUser);
    }

    @Transactional
    @Override
    public void deleteUserById(ObjectId id) {
        User user = getById(id);

        userRepository.delete(user);
        log.info("User deleted with id: {}", id);
    }

    public User getById(ObjectId userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionMessages.USER_NOT_FOUND + " with id : " + userId)
        );
    }
}

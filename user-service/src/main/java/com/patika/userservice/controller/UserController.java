package com.patika.userservice.controller;

import com.patika.userservice.dto.ControllerResponseDTO;
import com.patika.userservice.dto.UserDTO;
import com.patika.userservice.entity.User;
import com.patika.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getUserByEmail(email));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody User user) {
        UserDTO user1 = userService.updateUserDetails(user);
        return ResponseEntity.ok().body(user1);
    }


   @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> delete(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        userService.deleteUserById(objectId);
        return ResponseEntity.ok()
                .body(new ControllerResponseDTO(HttpStatus.OK, "User successfully deleted"));
   }
}

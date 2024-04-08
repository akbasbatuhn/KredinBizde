package com.patika.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class UserDTO {

    private ObjectId id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String addressId;
}

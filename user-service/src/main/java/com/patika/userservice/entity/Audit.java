package com.patika.userservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document
public abstract class Audit {

    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedDate
    private LocalDate updatedDate;
}

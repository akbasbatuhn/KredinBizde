package com.patika.bankservice.dto.client;

import com.patika.bankservice.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationResponse {

    private Long id;
    private Long userId;
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;
}

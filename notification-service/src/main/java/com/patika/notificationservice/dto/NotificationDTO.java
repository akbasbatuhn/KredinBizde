package com.patika.notificationservice.dto;

import com.patika.notificationservice.dto.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationDTO {

    private NotificationType notificationType;
    private String message;
    private String email;
}
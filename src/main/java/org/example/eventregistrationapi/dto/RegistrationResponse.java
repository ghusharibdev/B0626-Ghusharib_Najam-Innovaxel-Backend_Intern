package org.example.eventregistrationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.eventregistrationapi.model.RegistrationStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationResponse {
    private Long registrationId;
    private Long eventId;
    private String eventName;
    private String username;
    private RegistrationStatus status;
    private LocalDateTime registeredAt;
    private LocalDateTime cancelledAt;
}

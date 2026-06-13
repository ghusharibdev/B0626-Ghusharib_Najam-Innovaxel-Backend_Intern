package org.example.eventregistrationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class EventResponse {
    private Long eventId;
    private String eventName;
    private int totalSeats;
    private LocalDateTime eventDate;
    private long totalRegistrations;
    private long availableSeats;
}

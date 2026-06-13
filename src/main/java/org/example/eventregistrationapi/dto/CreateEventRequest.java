package org.example.eventregistrationapi.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateEventRequest {
    @NotBlank(message = "Event name is required")
    String eventName;

    @Positive(message = "Total seats must be greater than 0")
            @NotNull
    int totalSeats;

    @NotNull(message = "Date is required")
    @Future(message = "Event date must be in the future")
    LocalDateTime eventDate;
}

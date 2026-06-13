package org.example.eventregistrationapi.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Size(min = 3, max = 50, message = "Name must be 3 to 50 characters")
    @Column(nullable = false, unique = true)
    private String eventName;

    @Column(nullable = false)
    private int totalSeats;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

}

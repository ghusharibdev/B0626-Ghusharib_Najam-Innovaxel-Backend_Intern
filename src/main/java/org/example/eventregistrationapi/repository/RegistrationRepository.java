package org.example.eventregistrationapi.repository;

import org.example.eventregistrationapi.model.Event;
import org.example.eventregistrationapi.model.Registration;
import org.example.eventregistrationapi.model.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByEventAndUserNameIgnoreCaseAndStatus(
            Event event,
            String userName,
            RegistrationStatus status
    );

    long countByEventAndStatus(Event event, RegistrationStatus status);

    Optional<Registration> findByEventAndUserNameIgnoreCaseAndStatus(
            Event event,
            String userName,
            RegistrationStatus status
    );
}

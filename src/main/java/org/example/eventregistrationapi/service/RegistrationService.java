package org.example.eventregistrationapi.service;

import lombok.RequiredArgsConstructor;
import org.example.eventregistrationapi.dto.CancelRegistrationRequest;
import org.example.eventregistrationapi.dto.RegisterUserRequest;
import org.example.eventregistrationapi.dto.RegistrationResponse;
import org.example.eventregistrationapi.model.Event;
import org.example.eventregistrationapi.model.Registration;
import org.example.eventregistrationapi.model.RegistrationStatus;
import org.example.eventregistrationapi.repository.EventRepository;
import org.example.eventregistrationapi.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    @Transactional
    public RegistrationResponse registerUser(Long eventId, RegisterUserRequest request){

        Event event = eventRepository.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getEventDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot register for past event");
        }

        boolean alreadyRegistered = registrationRepository.existsByEventAndUserNameIgnoreCaseAndStatus(
                event,
                request.getUsername(),
                RegistrationStatus.ACTIVE
        );

        if (alreadyRegistered){
            throw new RuntimeException("User is already registered for this event");
        }

        long activeRegistrations = registrationRepository.countByEventAndStatus(
                event,
                RegistrationStatus.ACTIVE
        );

        if (activeRegistrations >= event.getTotalSeats()){
            throw new RuntimeException("Event is full");
        }

        Registration registration = Registration.builder().
                userName(request.getUsername()).
                event(event).
                status(RegistrationStatus.ACTIVE).
                build();

        Registration savedRegistration = registrationRepository.save(registration);

        return mapToRegistrationResponse(savedRegistration);
    }

    public RegistrationResponse cancelRegistration(Long eventId, CancelRegistrationRequest request){
        Event event = eventRepository.findByEventId(eventId).orElseThrow(()->new RuntimeException("Event not found"));

        Registration registration = registrationRepository.findByEventAndUserNameIgnoreCaseAndStatus(
                event,
                request.getUserName(),
                RegistrationStatus.ACTIVE
        ).orElseThrow(() -> new RuntimeException("Active registration not found"));

        registration.setStatus(RegistrationStatus.CANCELLED);
        registration.setCancelledAt(LocalDateTime.now());

        Registration savedRegistration = registrationRepository.save(registration);

        return mapToRegistrationResponse(savedRegistration);
    }

    private RegistrationResponse mapToRegistrationResponse(Registration registration) {
        return new RegistrationResponse(
                registration.getRegistrationId(),
                registration.getEvent().getEventId(),
                registration.getEvent().getEventName(),
                registration.getUserName(),
                registration.getStatus(),
                registration.getRegisteredAt(),
                registration.getCancelledAt()
        );
    }
}

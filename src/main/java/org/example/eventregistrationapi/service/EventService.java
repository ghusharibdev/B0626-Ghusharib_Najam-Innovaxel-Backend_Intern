package org.example.eventregistrationapi.service;

import lombok.RequiredArgsConstructor;
import org.example.eventregistrationapi.dto.CreateEventRequest;
import org.example.eventregistrationapi.dto.EventResponse;
import org.example.eventregistrationapi.model.Event;
import org.example.eventregistrationapi.model.RegistrationStatus;
import org.example.eventregistrationapi.repository.EventRepository;
import org.example.eventregistrationapi.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    final EventRepository eventRepository;
    final RegistrationRepository registrationRepository;

    public EventResponse createEvent (CreateEventRequest request){
        if (eventRepository.existsByEventNameIgnoreCase(request.getEventName())) {
            throw new RuntimeException("Event name already exists");
        }

        Event event = Event.builder().eventName(request.getEventName()).totalSeats(request.getTotalSeats()).eventDate(request.getEventDate()).build();

        Event savedEvent = eventRepository.save(event);

        return mapToEventResponse(savedEvent);
    }

    public List<EventResponse> getEvents(boolean upcomingOnly){
        List<Event> events = upcomingOnly ? eventRepository.findByEventDateAfterOrderByEventDateAsc(LocalDateTime.now()) : eventRepository.findAllByOrderByEventDateAsc();

        return events.stream().map(this::mapToEventResponse).toList();
    }

    private EventResponse mapToEventResponse(Event event) {

        long totalRegistrations = registrationRepository.countByEventAndStatus(
                event,
                RegistrationStatus.ACTIVE
        );

        long availableSeats = event.getTotalSeats() - totalRegistrations;

        return new EventResponse(
                event.getEventId(),
                event.getEventName(),
                event.getTotalSeats(),
                event.getEventDate(),
                totalRegistrations,
                availableSeats
        );
    }
}

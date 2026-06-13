package org.example.eventregistrationapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.example.eventregistrationapi.dto.CreateEventRequest;
import org.example.eventregistrationapi.dto.EventResponse;
import org.example.eventregistrationapi.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public EventResponse createEvent (@Valid @RequestBody CreateEventRequest request){
        return eventService.createEvent(request);
    }

    @GetMapping
    public List<EventResponse> getEvents(@RequestParam(defaultValue = "false") boolean upcomingOnly){
        return eventService.getEvents(upcomingOnly);
    }
}

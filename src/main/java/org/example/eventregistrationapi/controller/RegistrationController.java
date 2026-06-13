package org.example.eventregistrationapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eventregistrationapi.dto.CancelRegistrationRequest;
import org.example.eventregistrationapi.dto.RegisterUserRequest;
import org.example.eventregistrationapi.dto.RegistrationResponse;
import org.example.eventregistrationapi.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events/{eventId}/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public RegistrationResponse registerUser(@PathVariable Long eventId, @Valid @RequestBody RegisterUserRequest request){
        return registrationService.registerUser(eventId, request);
    }

    @PatchMapping("/cancel")
    public RegistrationResponse cancelRegistration(@PathVariable Long eventId, @Valid @RequestBody CancelRegistrationRequest request){
        return registrationService.cancelRegistration(eventId,request);
    }
}

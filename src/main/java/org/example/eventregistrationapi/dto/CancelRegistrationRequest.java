package org.example.eventregistrationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelRegistrationRequest {
    @NotBlank(message = "User name is required")
    @Size(min = 2, max = 50, message = "User name must be 2 to 50 characters")
    private String username;
}

package co.com.coordinadora.events.object;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRegistrationDto {

    private String registryId;
    private LocalDate registrationDate;
    private String userId;
    private String eventId;
}

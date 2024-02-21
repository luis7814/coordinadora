package co.com.coordinadora.events.object;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDto {

    private String eventId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userId;
}

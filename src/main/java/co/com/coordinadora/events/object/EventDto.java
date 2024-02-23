package co.com.coordinadora.events.object;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventDto {

    private String eventId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userId;
    private Double latitude;
    private Double longitude;

    private String address;
    private List<String> searhNearbySites;
}

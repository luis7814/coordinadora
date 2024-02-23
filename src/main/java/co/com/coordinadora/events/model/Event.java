package co.com.coordinadora.events.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table (name = "EVENTS")
public class Event {

    @Id
    @Column (name = "event_id")
    private String eventId;

    @Column (name = "title")
    private String title;

    @Column (name = "description")
    private String description;

    @Column (name = "start_date")
    private LocalDate startDate;

    @Column (name = "end_date")
    private LocalDate endDate;

    @Column (name = "user_id")
    private String userId;

    @Column (name = "latitude")
    private Double latitude;

    @Column (name = "length")
    private Double longitude;


}

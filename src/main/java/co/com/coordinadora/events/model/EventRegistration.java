package co.com.coordinadora.events.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table (name = "EVENT_REGISTRATION")
public class EventRegistration {

    @Id
    @Column (name = "registry_id")
    private String registryId;

    @Column (name = "registration_date")
    private LocalDate registrationDate;

    @Column (name = "user_id")
    private String userId;

    @Column (name = "event_id")
    private String eventId;
}

package co.com.coordinadora.events.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table (name = "NOTIFICATIONS")
public class Notification {

    @Id
    @Column (name = "notification_id")
    private String notificationId;

    @Column (name = "message")
    private String message;

    @Column (name = "notification_date")
    private LocalDate notificationDate;

    @Column (name = "user_id")
    private String userId;

    @Column (name = "event_id")
    private String eventId;
}

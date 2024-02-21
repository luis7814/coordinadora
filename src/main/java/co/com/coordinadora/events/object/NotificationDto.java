package co.com.coordinadora.events.object;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificationDto {

    private String notificationId;
    private String message;
    private LocalDate notificationDate;
    private String userId;
    private String eventId;
}

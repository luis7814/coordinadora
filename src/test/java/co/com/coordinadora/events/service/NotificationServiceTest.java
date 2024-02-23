package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.Notification;
import co.com.coordinadora.events.object.NotificationDto;
import co.com.coordinadora.events.repository.NotificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class NotificationServiceTest {
    @Mock
    NotificationRepository notificationRepository;
    @InjectMocks
    NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        NotificationDto result = notificationService.save(notificationDtoData());
        Assertions.assertNotNull(result.getNotificationId());
    }

    @Test
    void testUpdate() {
        NotificationDto result = notificationService.update(notificationDtoData());
        Assertions.assertEquals(notificationData().getNotificationId(), result.getNotificationId());
    }

    @Test
    void testFindById() {
        when(notificationRepository.findById("id")).thenReturn(Optional.of(notificationData()));
        NotificationDto result = notificationService.findById("id");
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<NotificationDto> result = notificationService.findAll();
        Assertions.assertNotNull(List.of(result));
    }

    private NotificationDto notificationDtoData() {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setNotificationId("1");
        notificationDto.setMessage("Message");
        notificationDto.setNotificationDate(LocalDate.now());
        notificationDto.setEventId("1");

        return notificationDto;
    }

    private Notification notificationData() {
        Notification notification = new Notification();
        notification.setNotificationId("1");
        notification.setMessage("Message");
        notification.setNotificationDate(LocalDate.now());
        notification.setEventId("1");

        return notification;
    }
}

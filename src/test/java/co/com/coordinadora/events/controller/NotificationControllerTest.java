package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.NotificationDto;
import co.com.coordinadora.events.service.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class NotificationControllerTest {
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    private NotificationDto notificationDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationDto = new NotificationDto();
        notificationDto.setNotificationId("1");
        notificationDto.setMessage("Message");
        notificationDto.setNotificationDate(LocalDate.now());
        notificationDto.setEventId("1");
    }

    @Test
    void saveNotificationTest() {
        when(notificationService.save(any(NotificationDto.class))).thenReturn(notificationDto);
        ResponseEntity<NotificationDto> response = notificationController.save(notificationDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(notificationDto, response.getBody());
    }

    @Test
    void updateNotificationTest() {
        when(notificationService.update(any(NotificationDto.class))).thenReturn(notificationDto);
        ResponseEntity<NotificationDto> response = notificationController.update(notificationDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(notificationDto, response.getBody());
    }

    @Test
    void findNotificationByIdTest() {
        when(notificationService.findById(any(String.class))).thenReturn(notificationDto);
        ResponseEntity<NotificationDto> response = notificationController.findById("1");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(notificationDto, response.getBody());
    }


}


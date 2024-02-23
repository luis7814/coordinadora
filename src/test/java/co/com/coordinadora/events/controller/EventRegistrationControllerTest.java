package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.service.EventRegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class EventRegistrationControllerTest {
    @Mock
    private EventRegistrationService eventRegistrationService;

    @InjectMocks
    private EventRegistrationController eventRegistrationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        EventRegistrationDto mockEventRegistrationDto = new EventRegistrationDto();
        when(eventRegistrationService.save(any(EventRegistrationDto.class))).thenReturn(mockEventRegistrationDto);

        ResponseEntity<EventRegistrationDto> response = eventRegistrationController.save(new EventRegistrationDto());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        verify(eventRegistrationService).save(any(EventRegistrationDto.class));
    }

    @Test
    void testUpdate() {
        EventRegistrationDto mockEventRegistrationDto = new EventRegistrationDto();
        when(eventRegistrationService.update(any(EventRegistrationDto.class))).thenReturn(mockEventRegistrationDto);

        ResponseEntity<EventRegistrationDto> response = eventRegistrationController.update(new EventRegistrationDto());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        verify(eventRegistrationService).update(any(EventRegistrationDto.class));
    }

    @Test
    void testFindById() {
        String id = "testId";
        EventRegistrationDto mockEventRegistrationDto = new EventRegistrationDto();
        when(eventRegistrationService.findById(id)).thenReturn(mockEventRegistrationDto);

        ResponseEntity<EventRegistrationDto> response = eventRegistrationController.findById(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        verify(eventRegistrationService).findById(id);
    }

    @Test
    void testFindAll() {
        List<EventRegistrationDto> mockEventRegistrationDtoList = Arrays.asList(new EventRegistrationDto(), new EventRegistrationDto());
        when(eventRegistrationService.findAll()).thenReturn(mockEventRegistrationDtoList);

        ResponseEntity<List<EventRegistrationDto>> response = eventRegistrationController.findAll();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        verify(eventRegistrationService).findAll();
    }
}


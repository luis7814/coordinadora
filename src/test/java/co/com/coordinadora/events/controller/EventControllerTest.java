package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.service.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {
    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    private EventDto eventDto;
    private String eventId = "1";

    @BeforeEach
    void setUp() {
        eventDto = new EventDto();
        eventDto.setDescription("Description");
        eventDto.setEventId("1");
        eventDto.setTitle("Title");
        eventDto.setEndDate(LocalDate.now());
        eventDto.setUserId("2");
        eventDto.setStartDate(LocalDate.now());
        eventDto.setLatitude(1.0);
        eventDto.setLongitude(2.0);
    }

    @Test
    void saveEventTest() {
        when(eventService.save(eventDto)).thenReturn(eventDto);
        ResponseEntity<EventDto> response = eventController.save(eventDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(eventDto, response.getBody());
    }

    @Test
    void updateEventTest() {
        when(eventService.update(eventDto)).thenReturn(eventDto);
        ResponseEntity<EventDto> response = eventController.update(eventDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(eventDto, response.getBody());
    }

    @Test
    void findEventByIdTest() {
        when(eventService.findById(eventId)).thenReturn(eventDto);
        ResponseEntity<EventDto> response = eventController.findById(eventId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(eventDto, response.getBody());
    }

    @Test
    void findAllEventsTest() {
        List<EventDto> eventList = Arrays.asList(eventDto);
        when(eventService.findAll()).thenReturn(eventList);
        ResponseEntity<List<EventDto>> response = eventController.findAll();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(eventList, response.getBody());
    }

    @Test
    void findByGeocodingTest() {
        when(eventService.findByGeocoding(eventId)).thenReturn(eventDto);
        ResponseEntity<EventDto> response = eventController.findByGeocoding(eventId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(eventDto, response.getBody());
    }
}


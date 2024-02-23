package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.Event;
import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.object.NominatimResponse;
import co.com.coordinadora.events.object.OsmElement;
import co.com.coordinadora.events.object.OsmResponse;
import co.com.coordinadora.events.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

class EventServiceTest {
    @Mock
    EventRepository eventRepository;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        EventDto result = eventService.save(eventDtoData());
        Assertions.assertNotNull(result.getEventId());
    }

    @Test
    void testUpdate() {
        EventDto result = eventService.update(eventDtoData());
        Assertions.assertEquals(eventDtoData().getEventId(), result.getEventId());
    }

    @Test
    void testFindById() {
        when(eventRepository.findById("id")).thenReturn(Optional.of(eventData()));
        EventDto result = eventService.findById("id");
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<EventDto> result = eventService.findAll();
        Assertions.assertNotNull(List.of(result));
    }

    @Test
    void findByGeocodingTest() {
        // Datos de prueba
        String eventId = "1";
        Event event = new Event();
        event.setEventId(eventId);
        event.setLatitude(1.0);
        event.setLongitude(2.0);

        EventDto eventDto = new EventDto();
        eventDto.setEventId(eventId);
        eventDto.setLatitude(1.0);
        eventDto.setLongitude(2.0);

        NominatimResponse nominatimResponse = new NominatimResponse();
        nominatimResponse.setDisplay_name("Test Address");

        OsmElement element1 = new OsmElement();
        element1.setType("node");
        element1.setId(1L);
        Map<String, String> tags1 = new HashMap<>();
        tags1.put("name", "Café Test 1");
        element1.setTags(tags1);

        OsmElement element2 = new OsmElement();
        element2.setType("node");
        element2.setId(2L);
        Map<String, String> tags2 = new HashMap<>();
        tags2.put("name", "Librería Test 2");
        element2.setTags(tags2);

        OsmResponse osmResponse = new OsmResponse();
        osmResponse.setElements(Arrays.asList(element1, element2));



        // Simulación
        when(eventRepository.findById(anyString())).thenReturn(Optional.of(event));
        when(restTemplate.getForEntity(anyString(), eq(NominatimResponse.class))).thenReturn(ResponseEntity.ok(nominatimResponse));
        when(restTemplate.getForEntity(anyString(), eq(OsmResponse.class))).thenReturn(ResponseEntity.ok(osmResponse));

        // Ejecución
        EventDto result = eventService.findByGeocoding(eventId);

        // Verificación
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Test Address", result.getAddress());
        // Verifica también que se hayan llamado los métodos esperados en las dependencias
        verify(eventRepository).findById(eventId);
        verify(restTemplate).getForEntity(anyString(), eq(NominatimResponse.class));
        // Añade más verificaciones según sea necesario
    }

    private EventDto eventDtoData() {
        EventDto eventDto = new EventDto();
        eventDto.setDescription("Description");
        eventDto.setEventId("1");
        eventDto.setTitle("Title");
        eventDto.setEndDate(LocalDate.now());
        eventDto.setUserId("2");
        eventDto.setStartDate(LocalDate.now());
        eventDto.setLatitude(1.0);
        eventDto.setLongitude(2.0);

        return eventDto;
    }

    private Event eventData() {
        Event event = new Event();
        event.setDescription("Description");
        event.setEventId("1");
        event.setTitle("Title");
        event.setEndDate(LocalDate.now());
        event.setUserId("2");
        event.setStartDate(LocalDate.now());
        event.setLatitude(1.0);
        event.setLongitude(2.0);

        return event;
    }
}

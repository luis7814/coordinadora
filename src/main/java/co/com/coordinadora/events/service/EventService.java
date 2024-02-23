package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.Event;
import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.object.NominatimResponse;
import co.com.coordinadora.events.object.OsmResponse;
import co.com.coordinadora.events.repository.EventRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestTemplate restTemplate;

    public EventDto save(EventDto eventDto) {
        eventDto.setEventId(Utilities.id());

        eventRepository.save(mapperToEvent(eventDto));
        return eventDto;
    }

    public EventDto update(EventDto eventDto) {
        eventRepository.save(mapperToEvent(eventDto));
        return eventDto;
    }

    public EventDto findById(String id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        return eventOptional.isPresent() ?
                mapperToEventDto(eventOptional.get()) : null;
    }

    public Page<EventDto> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable)
            .map(this::mapperToEventDto);
    }

    public EventDto findByGeocoding(String id) {

        EventDto eventDto = findById(id);
        if (eventDto != null) {
            eventDto.setAddress(getAddress(eventDto.getLatitude(), eventDto.getLongitude()));
            eventDto.setSearhNearbySites(searhNearbySites(eventDto.getLatitude(), eventDto.getLongitude(), 19));
        }
        return eventDto;
    }

    private String getAddress(double latitude, double longitude) {

        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude;
        ResponseEntity<NominatimResponse> response = restTemplate.getForEntity(url, NominatimResponse.class);
        return response.getBody().getDisplay_name();
    }

    private List<String> searhNearbySites(double latitude, double longitude, int range) {

        //String url = "http://overpass-api.de/api/interpreter?data=[out:json];node[amenity=cafe](around:500," + latitude + "," + longitude + ");out;";
        String url = "http://overpass-api.de/api/interpreter?data=[out:json];node(around:200," + latitude + "," + longitude + ");out;";

        ResponseEntity<OsmResponse> response = restTemplate.getForEntity(url, OsmResponse.class);
        List<String> places = new ArrayList<>();

        response.getBody().getElements().forEach((a) -> {
            Map<String, String> tags = a.getTags();
            if (tags != null) {
                if (tags.containsKey("name")) {
                    places.add(tags.get("name"));
                }
            }
        });

        return places;
    }

    private EventDto mapperToEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setDescription(event.getDescription());
        eventDto.setEventId(event.getEventId());
        eventDto.setTitle(event.getTitle());
        eventDto.setEndDate(event.getEndDate());
        eventDto.setUserId(event.getUserId());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setLatitude(event.getLatitude());
        eventDto.setLongitude(event.getLongitude());

        return eventDto;
    }

    private Event mapperToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setDescription(eventDto.getDescription());
        event.setEventId(eventDto.getEventId());
        event.setTitle(eventDto.getTitle());
        event.setEndDate(eventDto.getEndDate());
        event.setUserId(eventDto.getUserId());
        event.setStartDate(eventDto.getStartDate());
        event.setLongitude(eventDto.getLongitude());
        event.setLatitude(eventDto.getLatitude());

        return event;
    }

}

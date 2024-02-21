package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.Event;
import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.repository.EventRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

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

    public List<EventDto> findAll() {
        return eventRepository.findAll().stream()
                .map(this::mapperToEventDto)
                .collect(Collectors.toList());
    }

    private EventDto mapperToEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setDescription(event.getDescription());
        eventDto.setEventId(event.getEventId());
        eventDto.setTitle(event.getTitle());
        eventDto.setEndDate(event.getEndDate());
        eventDto.setUserId(event.getUserId());
        eventDto.setStartDate(event.getStartDate());

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

        return event;
    }

}

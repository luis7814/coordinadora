package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.EventRegistration;
import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.repository.EventRegistrationRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    public EventRegistrationDto save(EventRegistrationDto eventRegistrationDto) {
        eventRegistrationDto.setRegistryId(Utilities.id());

        eventRegistrationRepository.save(mapperToEventRegistration(eventRegistrationDto));
        return eventRegistrationDto;
    }

    public EventRegistrationDto update(EventRegistrationDto eventRegistrationDto) {
        eventRegistrationRepository.save(mapperToEventRegistration(eventRegistrationDto));
        return eventRegistrationDto;
    }

    public EventRegistrationDto findById(String id) {
        Optional<EventRegistration> eventRegistrationOptional = eventRegistrationRepository.findById(id);
        return eventRegistrationOptional.isPresent() ?
                mapperToEventRegistrationDto(eventRegistrationOptional.get()) : null;
    }

    public List<EventRegistrationDto> findAll() {
        return eventRegistrationRepository.findAll().stream()
                .map(this::mapperToEventRegistrationDto)
                .collect(Collectors.toList());
    }

    private EventRegistrationDto mapperToEventRegistrationDto(EventRegistration eventRegistration) {
        EventRegistrationDto eventRegistrationDto = new EventRegistrationDto();
        eventRegistrationDto.setRegistryId(eventRegistration.getRegistryId());
        eventRegistrationDto.setRegistrationDate(eventRegistration.getRegistrationDate());
        eventRegistrationDto.setUserId(eventRegistration.getUserId());
        eventRegistrationDto.setEventId(eventRegistration.getEventId());

        return eventRegistrationDto;
    }

    private EventRegistration mapperToEventRegistration(EventRegistrationDto eventRegistrationDto) {
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setRegistryId(eventRegistration.getRegistryId());
        eventRegistration.setRegistrationDate(eventRegistration.getRegistrationDate());
        eventRegistration.setUserId(eventRegistration.getUserId());
        eventRegistration.setEventId(eventRegistrationDto.getEventId());

        return eventRegistration;
    }

}

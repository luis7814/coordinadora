package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.EventRegistration;
import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.repository.EventRegistrationRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<EventRegistrationDto> findAll(Pageable pageable) {
        return eventRegistrationRepository.findAll(pageable)
                .map(this::mapperToEventRegistrationDto);
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
        eventRegistration.setRegistryId(eventRegistrationDto.getRegistryId());
        eventRegistration.setRegistrationDate(eventRegistrationDto.getRegistrationDate());
        eventRegistration.setUserId(eventRegistrationDto.getUserId());
        eventRegistration.setEventId(eventRegistrationDto.getEventId());

        return eventRegistration;
    }

}

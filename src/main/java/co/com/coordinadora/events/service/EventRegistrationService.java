package co.com.coordinadora.events.service;

import co.com.coordinadora.events.controller.UserController;
import co.com.coordinadora.events.exceptions.NotFoundException;
import co.com.coordinadora.events.model.EventRegistration;
import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.repository.EventRegistrationRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventRegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(EventRegistrationService.class);

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    public EventRegistrationDto save(EventRegistrationDto eventRegistrationDto) {
        eventRegistrationDto.setRegistryId(Utilities.id());

        logger.info("save " + eventRegistrationDto.getRegistryId());

        eventRegistrationRepository.save(mapperToEventRegistration(eventRegistrationDto));
        return eventRegistrationDto;
    }

    public EventRegistrationDto update(EventRegistrationDto eventRegistrationDto) {

        logger.info("update " + eventRegistrationDto.getRegistryId());

        eventRegistrationRepository.save(mapperToEventRegistration(eventRegistrationDto));
        return eventRegistrationDto;
    }

    public EventRegistrationDto findById(String id) {

        logger.info("findById " + id);

        Optional<EventRegistration> eventRegistrationOptional = eventRegistrationRepository.findById(id);
        return eventRegistrationOptional
                .map(this::mapperToEventRegistrationDto)
                .orElseThrow(() -> new NotFoundException("Información no encontrada"));
    }

    public Page<EventRegistrationDto> findAll(Pageable pageable) {

        logger.info("findAll");

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

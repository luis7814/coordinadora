package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.EventRegistration;
import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.repository.EventRegistrationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class EventRegistrationServiceTest {
    @Mock
    EventRegistrationRepository eventRegistrationRepository;
    @InjectMocks
    EventRegistrationService eventRegistrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        EventRegistrationDto result = eventRegistrationService.save(registrationDtoData());
        Assertions.assertNotNull(result.getRegistryId());
    }

    @Test
    void testUpdate() {
        EventRegistrationDto result = eventRegistrationService.update(registrationDtoData());
        Assertions.assertEquals(registrationDtoData().getRegistryId(), result.getRegistryId());
    }

    @Test
    void testFindById() {
        when(eventRegistrationRepository.findById("id")).thenReturn(Optional.of(registrationData()));
        EventRegistrationDto result = eventRegistrationService.findById("id");
        Assertions.assertNotNull(result);
    }

    private EventRegistrationDto registrationDtoData() {
        EventRegistrationDto eventRegistrationDto = new EventRegistrationDto();
        eventRegistrationDto.setRegistryId("1");
        eventRegistrationDto.setRegistrationDate(LocalDate.now());
        eventRegistrationDto.setUserId("2");
        eventRegistrationDto.setEventId("1");

        return eventRegistrationDto;
    }

    private EventRegistration registrationData() {
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setRegistryId("1");
        eventRegistration.setRegistrationDate(LocalDate.now());
        eventRegistration.setUserId("2");
        eventRegistration.setEventId("1");

        return eventRegistration;
    }
}

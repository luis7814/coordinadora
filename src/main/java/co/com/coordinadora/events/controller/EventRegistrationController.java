package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.service.EventRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Eventos Registrados", description = "API para gestionar eventos registrados")
public class EventRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(EventRegistrationController.class);

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @PostMapping("/eventRegistration")
    @Operation(summary = "Registro de Eventos por usuario")
    public ResponseEntity<EventRegistrationDto> save(@RequestBody EventRegistrationDto eventRegistrationDto) {
        logger.info("save");
        return new ResponseEntity<>(eventRegistrationService.save(eventRegistrationDto), HttpStatus.OK);
    }

    @PutMapping("/eventRegistration")
    @Operation(summary = "Actualización de Eventos por usuario")
    public ResponseEntity<EventRegistrationDto> update(@RequestBody EventRegistrationDto eventRegistrationDto) {
        logger.info("update");
        return new ResponseEntity<>(eventRegistrationService.update(eventRegistrationDto), HttpStatus.OK);
    }

    @GetMapping("/eventRegistration/{id}")
    @Operation(summary = "Busqueda de un Eventos por usuario")
    public ResponseEntity<EventRegistrationDto> findById(@PathVariable String id) {
        logger.info("findById " + id);
        return new ResponseEntity<>(eventRegistrationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/eventRegistration")
    @Operation(summary = "Listado de todos los eventos por usuario")
    public ResponseEntity<Page<EventRegistrationDto>> findAll(Pageable pageable) {
        logger.info("findAll");
        return new ResponseEntity<>(eventRegistrationService.findAll(pageable), HttpStatus.OK);
    }
}


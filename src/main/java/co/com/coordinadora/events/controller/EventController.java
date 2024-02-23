package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@RestController
@Tag(name = "Eventos", description = "API para gestionar eventos")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    @Operation(summary = "Registro de Eventos")
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto) {
        logger.info("save");
        return new ResponseEntity<>(eventService.save(eventDto), HttpStatus.OK);
    }

    @PutMapping("/event")
    @Operation(summary = "Actualización de Eventos")
    public ResponseEntity<EventDto> update(@RequestBody EventDto eventDto) {
        logger.info("update");
        return new ResponseEntity<>(eventService.update(eventDto), HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    @Operation(summary = "Buscar un Evento")
    public ResponseEntity<EventDto> findById(@PathVariable String id) {
        logger.info("findById " + id);
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/event")
    @Operation(summary = "Listar todos los Eventos")
    public ResponseEntity<Page<EventDto>> findAll(Pageable pageable) {
        logger.info("findAll");
        return new ResponseEntity<>(eventService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/event/geocoding/{id}")
    @Operation(summary = "Listado de informacion por geocodificacion")
    public ResponseEntity<EventDto> findByGeocoding(@PathVariable String id) {
        logger.info("findByGeocoding " + id);
        return new ResponseEntity<>(eventService.findByGeocoding(id), HttpStatus.OK);
    }

}


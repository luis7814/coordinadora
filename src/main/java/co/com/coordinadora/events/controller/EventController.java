package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Eventos", description = "API para gestionar eventos")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    @Operation(summary = "Registro de Eventos")
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.save(eventDto), HttpStatus.OK);
    }

    @PutMapping("/event")
    @Operation(summary = "Actualización de Eventos")
    public ResponseEntity<EventDto> update(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.update(eventDto), HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    @Operation(summary = "Buscar un Evento")
    public ResponseEntity<EventDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/event")
    @Operation(summary = "Listar todos los Eventos")
    public ResponseEntity<List<EventDto>> findAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
}


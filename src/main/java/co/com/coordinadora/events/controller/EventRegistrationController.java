package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.service.EventRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Eventos Registrados", description = "API para gestionar eventos registrados")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @PostMapping("/eventRegistration")
    @Operation(summary = "Registro de Eventos por usuario")
    public ResponseEntity<EventRegistrationDto> save(@RequestBody EventRegistrationDto eventRegistrationDto) {
        return new ResponseEntity<>(eventRegistrationService.save(eventRegistrationDto), HttpStatus.OK);
    }

    @PutMapping("/eventRegistration")
    @Operation(summary = "Actualización de Eventos por usuario")
    public ResponseEntity<EventRegistrationDto> update(@RequestBody EventRegistrationDto eventRegistrationDto) {
        return new ResponseEntity<>(eventRegistrationService.update(eventRegistrationDto), HttpStatus.OK);
    }

    @GetMapping("/eventRegistration/{id}")
    @Operation(summary = "Busqueda de un Eventos por usuario")
    public ResponseEntity<EventRegistrationDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(eventRegistrationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/eventRegistration")
    @Operation(summary = "Listado de todos los eventos por usuario")
    public ResponseEntity<List<EventRegistrationDto>> findAll() {
        return new ResponseEntity<>(eventRegistrationService.findAll(), HttpStatus.OK);
    }
}


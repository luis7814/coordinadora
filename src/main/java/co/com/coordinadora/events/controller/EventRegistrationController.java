package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventRegistrationDto;
import co.com.coordinadora.events.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventRegistrationController {

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @PostMapping("/eventRegistration")
    public ResponseEntity<EventRegistrationDto> save(@RequestBody EventRegistrationDto eventRegistrationDto) {
        return new ResponseEntity<>(eventRegistrationService.save(eventRegistrationDto), HttpStatus.OK);
    }

    @PutMapping("/eventRegistration")
    public ResponseEntity<EventRegistrationDto> update(@RequestBody EventRegistrationDto eventRegistrationDto) {
        return new ResponseEntity<>(eventRegistrationService.update(eventRegistrationDto), HttpStatus.OK);
    }

    @GetMapping("/eventRegistration/{id}")
    public ResponseEntity<EventRegistrationDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(eventRegistrationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/eventRegistration")
    public ResponseEntity<List<EventRegistrationDto>> findAll() {
        return new ResponseEntity<>(eventRegistrationService.findAll(), HttpStatus.OK);
    }
}


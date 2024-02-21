package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.save(eventDto), HttpStatus.OK);
    }

    @PutMapping("/event")
    public ResponseEntity<EventDto> update(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.update(eventDto), HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<EventDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventDto>> findAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
}


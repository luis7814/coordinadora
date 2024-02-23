package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.NotificationDto;
import co.com.coordinadora.events.service.NotificationService;
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
@Tag(name = "Notificaciones", description = "API para gestionar notificaciones a usuarios")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    @Operation(summary = "Registro de Notificaciones")
    public ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto) {
        logger.info("save");
        return new ResponseEntity<>(notificationService.save(notificationDto), HttpStatus.OK);
    }

    @PutMapping("/notification")
    @Operation(summary = "Actualizacion de Notificaciones")
    public ResponseEntity<NotificationDto> update(@RequestBody NotificationDto notificationDto) {
        logger.info("update");
        return new ResponseEntity<>(notificationService.update(notificationDto), HttpStatus.OK);
    }

    @GetMapping("/notification/{id}")
    @Operation(summary = "Busqueda de un Notificacion")
    public ResponseEntity<NotificationDto> findById(@PathVariable String id) {
        logger.info("findById " + id);
        return new ResponseEntity<>(notificationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/notification")
    @Operation(summary = "Listar todas las Notificaciones")
    public ResponseEntity<Page<NotificationDto>> findAll(Pageable pageable) {
        logger.info("findAll");
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }
}


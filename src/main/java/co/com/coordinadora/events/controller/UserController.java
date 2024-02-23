package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.UserDto;
import co.com.coordinadora.events.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Usuarios", description = "API para gestionar usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @Operation(summary = "Registro de Usuarios")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
    }

    @PutMapping("/user")
    @Operation(summary = "Actualizacion de Usuarios")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Busqueda de un Usuario")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user")
    @Operation(summary = "Listado de Usuarios")
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }
}


package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.model.Domicilio;
import com.dh.ClinicaOdontologica.service.impl.DomicilioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioServiceImpl domicilioService;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<Domicilio> registrarDomicilio(@RequestBody Domicilio domicilio) {
        log.debug("Registrando Domicilio al Sistema.");
        return ResponseEntity.ok(domicilioService.guardar(domicilio));
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Domicilio> buscar(@PathVariable Long id) {
        Domicilio domicilio = domicilioService.buscarPorId(id).orElse(null);
        log.debug("Buscando Domicilio por Id.");
        return ResponseEntity.ok(domicilio);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Domicilio> actualizar(@RequestBody Domicilio domicilio) {
        ResponseEntity<Domicilio> response = null;

        if (domicilio.getId() != null && domicilioService.buscarPorId(domicilio.getId()).isPresent()) {
            response = ResponseEntity.ok(domicilioService.guardar(domicilio));
            log.debug("Actualizando datos.");
        } else
            response = new ResponseEntity(domicilioService.guardar(domicilio), HttpStatus.OK);
        log.debug("Creando un nuevo Domicilio.");
        return response;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (domicilioService.buscarPorId(id).isPresent()) {
            domicilioService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");
            log.debug("Eliminando Domicilio");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
            log.debug("Domicilio no encontrado");
        }

        return response;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Domicilio>> buscarTodos() {
        log.debug("Listando todos los Domicilios.");
        return ResponseEntity.ok(domicilioService.listar());

    }


}

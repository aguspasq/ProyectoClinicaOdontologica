package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.model.Odontologo;
import com.dh.ClinicaOdontologica.service.impl.OdontologoServiceImpl;
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
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoServiceImpl odontologoService;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        log.debug("Registrando Odontologo al Sistema.");
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Odontologo> buscar(@PathVariable Long id) {
        Odontologo odontologo = odontologoService.buscarPorId(id).orElse(null);
        log.debug("Buscando Odontologo por Id.");
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> response = null;

        if (odontologo.getId() != null && odontologoService.buscarPorId(odontologo.getId()).isPresent()) {
            response = ResponseEntity.ok(odontologoService.guardar(odontologo));
            log.debug("Actualizando datos.");
        } else
            response = new ResponseEntity(odontologoService.guardar(odontologo), HttpStatus.OK);
        log.debug("Creando un nuevo Odontologo.");
        return response;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (odontologoService.buscarPorId(id).isPresent()) {
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Registro eliminado");
            log.debug("Eliminando Odontologo");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
            log.debug("Odontologo no encontrado");
        }

        return response;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        log.debug("Listando todos los Odontologos.");
        return ResponseEntity.ok(odontologoService.listar());

    }
}
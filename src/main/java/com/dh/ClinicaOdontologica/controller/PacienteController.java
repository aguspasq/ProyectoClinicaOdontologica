package com.dh.ClinicaOdontologica.controller;


import com.dh.ClinicaOdontologica.model.Paciente;
import com.dh.ClinicaOdontologica.service.impl.PacienteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteServiceImpl pacienteService;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        log.debug("Registrando Paciente al Sistema.");
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id).orElse(null);
        log.debug("Buscando Paciente por Id.");
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response = null;

        if (paciente.getId() != null && pacienteService.buscarPorId(paciente.getId()).isPresent()) {
            response = ResponseEntity.ok(pacienteService.guardar(paciente));
            log.debug("Actualizando datos.");
        } else
            response = new ResponseEntity(pacienteService.guardar(paciente), HttpStatus.OK);
        log.debug("Creando un nuevo Paciente.");
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Eliminando Odontologo");
        ResponseEntity<String> response = null;

        if (pacienteService.buscarPorId(id).isPresent()) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");

        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
            log.debug("Paciente no encontrado");
        }
        return response;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Paciente>> buscarTodos() {
        log.debug("Listando todos los Pacientes.");
        return ResponseEntity.ok(pacienteService.listar());
    }

    @GetMapping("buscar/{apellido}")
    public ResponseEntity<List<Paciente>> buscarPorApellido(@PathVariable String apellido) {
       log.debug("Listando pacientes por apellido.");
       return ResponseEntity.ok(pacienteService.buscarPacienteByApellido(apellido));
    }

}

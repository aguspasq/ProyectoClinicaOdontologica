package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.model.Paciente;
import com.dh.ClinicaOdontologica.model.Turno;
import com.dh.ClinicaOdontologica.service.impl.OdontologoServiceImpl;
import com.dh.ClinicaOdontologica.service.impl.PacienteServiceImpl;
import com.dh.ClinicaOdontologica.service.impl.TurnoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoServiceImpl turnoService;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private OdontologoServiceImpl odontologoService;

    @PostMapping("/registrar")
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        log.debug("Registrando Turno al Sistema.");
        ResponseEntity<Turno> response=null;

        if (pacienteService.buscarPorId(turno.getPaciente().getId()).isPresent() && odontologoService.buscarPorId(turno.getOdontologo().getId()).isPresent()) {
            response = ResponseEntity.ok(turnoService.guardar(turno));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        Turno turno = turnoService.buscarPorId(id).orElse(null);
        log.debug("Buscando Turno por Id.");
        return ResponseEntity.ok(turno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response = null;

        if (turno.getId() != null && turnoService.buscarPorId(turno.getId()).isPresent()) {
            response = ResponseEntity.ok(turnoService.guardar(turno));
            log.debug("Actualizando datos.");
        } else
            response = new ResponseEntity(turnoService.guardar(turno), HttpStatus.OK);
             log.debug("Creando un nuevo Turno.");
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (turnoService.buscarPorId(id).isPresent()) {
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
            log.debug("Eliminando Turno");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
            log.debug("Turno no encontrado");
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        log.debug("Listando todos los Turnos.");
        return ResponseEntity.ok(turnoService.listar());
    }
}

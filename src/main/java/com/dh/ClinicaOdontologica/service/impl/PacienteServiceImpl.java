package com.dh.ClinicaOdontologica.service.impl;

import com.dh.ClinicaOdontologica.model.Paciente;
import com.dh.ClinicaOdontologica.repository.PacienteRepository;
import com.dh.ClinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PacienteServiceImpl implements IService<Paciente> {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        paciente.setFechaIngreso(new Date());
        pacienteRepository.save(paciente);
        return paciente;
    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Paciente> buscarPacienteByApellido(String apellido){
        List<Paciente> pacientes = pacienteRepository.buscarPacienteByApellido(apellido);
        return pacientes;
    }


}

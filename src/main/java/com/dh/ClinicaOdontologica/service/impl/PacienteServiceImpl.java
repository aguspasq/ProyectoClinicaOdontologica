package com.dh.ClinicaOdontologica.service.impl;

import com.dh.ClinicaOdontologica.model.Paciente;
import com.dh.ClinicaOdontologica.repository.PacienteRepository;
import com.dh.ClinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IService<Paciente> {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
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
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

}

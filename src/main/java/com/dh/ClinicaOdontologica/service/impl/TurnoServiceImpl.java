package com.dh.ClinicaOdontologica.service.impl;

import com.dh.ClinicaOdontologica.model.Turno;
import com.dh.ClinicaOdontologica.repository.TurnoRepository;
import com.dh.ClinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements IService<Turno> {

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno guardar(Turno turno) {
        turnoRepository.save(turno);
        return turno;
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Turno> buscarPorId(Long id) {
        return turnoRepository.findById(id);
    }

}

package com.dh.ClinicaOdontologica.service.impl;

import com.dh.ClinicaOdontologica.model.Odontologo;
import com.dh.ClinicaOdontologica.repository.OdontologoRepository;
import com.dh.ClinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements IService<Odontologo> {

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Odontologo> listar() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Odontologo> buscarPorId(Long id) {
        return odontologoRepository.findById(id);
    }
}

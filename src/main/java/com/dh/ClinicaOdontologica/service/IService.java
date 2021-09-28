package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    List<T> listar();

   T guardar(T t);

    void eliminar(Long id);

    Optional<T> buscarPorId(Long id);
}

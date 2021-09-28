package com.dh.ClinicaOdontologica.repository;

import com.dh.ClinicaOdontologica.model.Odontologo;
import com.dh.ClinicaOdontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

}

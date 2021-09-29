package com.dh.ClinicaOdontologica.repository;

import com.dh.ClinicaOdontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("from Paciente p where p.apellido like %:apellido%")
    List<Paciente> buscarPacienteByApellido(@Param("apellido") String apellido);


}

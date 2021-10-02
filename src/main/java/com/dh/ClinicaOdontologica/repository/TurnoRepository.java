package com.dh.ClinicaOdontologica.repository;

import com.dh.ClinicaOdontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

}

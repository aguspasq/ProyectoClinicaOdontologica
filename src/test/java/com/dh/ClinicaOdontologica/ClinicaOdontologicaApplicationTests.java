package com.dh.ClinicaOdontologica;

import com.dh.ClinicaOdontologica.model.Domicilio;
import com.dh.ClinicaOdontologica.model.Odontologo;
import com.dh.ClinicaOdontologica.model.Paciente;
import com.dh.ClinicaOdontologica.service.impl.OdontologoServiceImpl;
import com.dh.ClinicaOdontologica.service.impl.PacienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {

    @Autowired
    OdontologoServiceImpl odontologoService;
    @Autowired
    PacienteServiceImpl pacienteService;

    @Test
    void contextLoads() {

        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Pasqualis");
        odontologo.setNombre("Agustina");
        odontologo.setMatricula(1234);

        odontologoService.guardar(odontologo);

        Collection<Odontologo> odontologos = odontologoService.listar();
        boolean resultado = odontologos.size() > 0 && odontologos.size() < 2;

        assertTrue(resultado);

    }

    @Test
    public void test() {

        Domicilio domicilio = new Domicilio();
        domicilio.setProvincia("Cordoba");
        domicilio.setLocalidad("Cordoba");
        domicilio.setCalle("San Lorenzo");
        domicilio.setNumero("47");

        Paciente paciente = new Paciente();
        paciente.setDomicilio(domicilio);
        paciente.setNombre("Agustina");
        paciente.setApellido("Pasqualis");
        paciente.setDni("32020854");

        pacienteService.guardar(paciente);

        Collection<Paciente> pacientes = pacienteService.listar();

        boolean resultado = pacientes.size() > 0;

        assertTrue(resultado);

    }

}


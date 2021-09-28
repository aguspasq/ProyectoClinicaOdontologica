package com.dh.ClinicaOdontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "odontologo")
public class Odontologo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;
    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Turno> turnos;
}

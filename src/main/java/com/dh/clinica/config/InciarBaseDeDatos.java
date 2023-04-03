package com.dh.clinica.config;

import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Component
public class InciarBaseDeDatos implements CommandLineRunner {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Override
    public void run(String... args) throws Exception {
        cargarPacientes();
        cargarOdontologos();
    }

    private void cargarOdontologos() {
        List<Odontologo> odontologos = Arrays.asList(
                new Odontologo("John", "Lennon", 1),
                new Odontologo("Paul", "McCartney", 2),
                new Odontologo("George", "Harrison", 3),
                new Odontologo("Ringo", "Starr", 4)
        );

        odontologos.forEach(odontologo -> odontologoService.registrarOdontologo(odontologo));
    }

    private void cargarPacientes() {
        LocalDate fechaActual = LocalDate.now();
        List<Paciente> pacientes = Arrays.asList(
                new Paciente("Mick", "Jagger", "1",
                        convertirADate(fechaActual),new Domicilio("Main Street","1",
                "London", "London"))
        );

        pacientes.forEach(paciente -> pacienteService.guardar(paciente));
    }

    private Date convertirADate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }
}

package com.dh.clinica.service;

import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.repository.impl.TurnoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final OdontologoRepository odontologoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {

        this.turnoRepository = turnoRepository;
        this.odontologoRepository= odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }


    public Turno registrarTurno(Turno turno) {
        if(turno.getPaciente().getId()==null || turno.getOdontologo().getId()==null) {
            throw new RuntimeException();
        }
        // Cargar el objeto "Odontologo" desde la base de datos
        Odontologo odontologo = odontologoRepository.findById(turno.getOdontologo().getId())
                .orElseThrow(() -> new RuntimeException("Odontologo no encontrado"));
        // Asignar el objeto "Odontologo" cargado al objeto "Turno"
        turno.setOdontologo(odontologo);
        // Cargar el objeto "Paciente" desde la base de datos
        Paciente paciente= pacienteRepository.findById(turno.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        // Asignar el objeto "Paciente" cargado al objeto "Turno"
        turno.setPaciente(paciente);

        log.info("****************Turno Registrado*********************");
        return turnoRepository.save(turno);
    }
    public List<Turno> listar(){
        return turnoRepository.findAll();
    }
    public void eliminar(Integer id){
        turnoRepository.deleteById(id);
    }
    public Turno actualizar(Turno turno){
        return turnoRepository.save(turno);
    }
    public Optional<Turno> buscar(Integer id){
        return turnoRepository.findById(id);
    }

}

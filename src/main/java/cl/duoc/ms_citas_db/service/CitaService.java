package cl.duoc.ms_citas_db.service;

import cl.duoc.ms_citas_db.model.dto.CitaUpdateDTO;
import cl.duoc.ms_citas_db.model.entity.Cita;
import cl.duoc.ms_citas_db.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    CitaRepository citaRepository;

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Cita findById(Long id) {
        return citaRepository.findById(id).get();
    }

    public Cita findByPacienteId(Long pacienteId) {
        return this.citaRepository.findByPacienteId(pacienteId);
    }

    public Cita registerCita(Cita cita) {
        return this.citaRepository.save(cita);
    }
    
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }

    public void actualizarCita(CitaUpdateDTO cita){
        Optional<Cita> citaEncontrada = citaRepository.findById(cita.getId());

        if (citaEncontrada.isPresent()) {
            Cita citaAActualizar = citaEncontrada.get();

            citaAActualizar.actualizarCita(cita);

        }
    }
}

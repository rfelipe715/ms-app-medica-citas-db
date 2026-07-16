package cl.duoc.ms_citas_db.service;

import cl.duoc.ms_citas_db.exception.CitaNotFoundException;
import cl.duoc.ms_citas_db.model.dto.CitaUpdateDTO;
import cl.duoc.ms_citas_db.model.entity.Cita;
import cl.duoc.ms_citas_db.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    CitaRepository citaRepository;

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Cita findById(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new CitaNotFoundException(id));
    }

    public Cita findByPacienteId(Long pacienteId) {
        return this.citaRepository.findByPacienteId(pacienteId);
    }

    public Cita registerCita(Cita cita) {
        return this.citaRepository.save(cita);
    }

    public void eliminarCita(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new CitaNotFoundException(id);
        }
        citaRepository.deleteById(id);
    }

    public Cita actualizarCita(CitaUpdateDTO cita) {
        Cita citaAActualizar = citaRepository.findById(cita.getId())
                .orElseThrow(() -> new CitaNotFoundException(cita.getId()));

        citaAActualizar.actualizarCita(cita);

        return citaRepository.save(citaAActualizar);
    }
}

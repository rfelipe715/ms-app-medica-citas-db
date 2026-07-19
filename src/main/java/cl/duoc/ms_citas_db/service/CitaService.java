package cl.duoc.ms_citas_db.service;

import cl.duoc.ms_citas_db.exception.CitaNotFoundException;
import cl.duoc.ms_citas_db.model.dto.CitaUpdateDTO;
import cl.duoc.ms_citas_db.model.entity.Cita;
import cl.duoc.ms_citas_db.repository.CitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    private static final Logger log = LoggerFactory.getLogger(CitaService.class);

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
        Cita guardada = this.citaRepository.save(cita);
        log.info("Cita registrada con id={}, pacienteId={}", guardada.getId(), guardada.getPacienteId());
        return guardada;
    }

    public void eliminarCita(Long id) {
        if (!citaRepository.existsById(id)) {
            log.warn("Intento de eliminar una cita inexistente, id={}", id);
            throw new CitaNotFoundException(id);
        }
        citaRepository.deleteById(id);
        log.info("Cita id={} eliminada correctamente", id);
    }

    public Cita actualizarCita(CitaUpdateDTO cita) {
        Cita citaAActualizar = citaRepository.findById(cita.getId())
                .orElseThrow(() -> {
                    log.warn("Intento de actualizar una cita inexistente, id={}", cita.getId());
                    return new CitaNotFoundException(cita.getId());
                });

        citaAActualizar.actualizarCita(cita);

        Cita actualizada = citaRepository.save(citaAActualizar);
        log.info("Cita id={} actualizada correctamente", actualizada.getId());
        return actualizada;
    }
}

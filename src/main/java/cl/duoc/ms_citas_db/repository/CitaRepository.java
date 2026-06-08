package cl.duoc.ms_citas_db.repository;

import cl.duoc.ms_citas_db.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    Cita findByPacienteId(Long pacienteId);
}

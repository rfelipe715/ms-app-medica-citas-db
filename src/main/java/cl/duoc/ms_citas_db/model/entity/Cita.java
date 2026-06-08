package cl.duoc.ms_citas_db.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente", nullable = false)
    private Long pacienteId;

//    @Column(name = "medico_id", nullable = false)
//    private Long medicoId;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private String hora;

    @Column(length = 20)
    private String estado = "PENDIENTE";
}
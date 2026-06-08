package cl.duoc.ms_citas_db.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CitaDTO {

    private Long id;
    private Long pacienteId;
    private String fecha;
    private String hora;
    private String estado;

}

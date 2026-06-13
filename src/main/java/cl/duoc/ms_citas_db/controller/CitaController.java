package cl.duoc.ms_citas_db.controller;

import cl.duoc.ms_citas_db.model.dto.CitaDTO;
import cl.duoc.ms_citas_db.model.dto.CitaUpdateDTO;
import cl.duoc.ms_citas_db.model.entity.Cita;
import cl.duoc.ms_citas_db.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> registrarCita(@RequestBody CitaDTO citaDTO) {
        Cita citaCreada = new Cita();
//        citaCreada.setId(citaDTO.getId());
        citaCreada.setPacienteId(citaDTO.getPacienteId());
        citaCreada.setFecha(citaDTO.getFecha());
        citaCreada.setHora(citaDTO.getHora());
        citaCreada.setEstado(citaDTO.getEstado());


        Cita nuevaCita = citaService.registerCita(citaCreada);
        return ResponseEntity.ok(nuevaCita);
    }

    @GetMapping
    public ResponseEntity<List<Cita>> findAll(){
        List<Cita> citas = citaService.findAll();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> findById(Long id){
        Cita cita = citaService.findById(id);
        return ResponseEntity.ok(cita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(Long id){
        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<CitaUpdateDTO> actualizarCita(@RequestBody CitaUpdateDTO cita) {
        return ResponseEntity.ok(cita);
    }
}

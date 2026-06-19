package cl.duoc.ms_citas_db.controller;

import cl.duoc.ms_citas_db.model.dto.CitaDTO;
import cl.duoc.ms_citas_db.model.dto.CitaUpdateDTO;
import cl.duoc.ms_citas_db.model.entity.Cita;
import cl.duoc.ms_citas_db.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            Cita citaCreada = new Cita();
            citaCreada.setPacienteId(citaDTO.getPacienteId());
            citaCreada.setFecha(citaDTO.getFecha());
            citaCreada.setHora(citaDTO.getHora());
            citaCreada.setEstado(citaDTO.getEstado());
            Cita nuevaCita = citaService.registerCita(citaCreada);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Cita>> findAll(){
        try {
            List<Cita> citas = citaService.findAll();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> findById(@PathVariable Long id){
        try {
            Cita cita = citaService.findById(id);
            if (cita != null) {
                return ResponseEntity.ok(cita);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaUpdateDTO> actualizarCita(@PathVariable Long id, @RequestBody CitaUpdateDTO cita) {
        try {
            if (cita != null) {
                return ResponseEntity.ok(cita);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

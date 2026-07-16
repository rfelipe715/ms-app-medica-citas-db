package cl.duoc.ms_citas_db.controller;

import cl.duoc.ms_citas_db.model.dto.CitaDTO;
import cl.duoc.ms_citas_db.model.dto.CitaUpdateDTO;
import cl.duoc.ms_citas_db.model.entity.Cita;
import cl.duoc.ms_citas_db.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@Tag(name = "Citas (DB)", description = "Persistencia de citas médicas en la base de datos")
public class CitaController {

    @Autowired
    CitaService citaService;

    @Operation(summary = "Registrar una nueva cita", description = "Crea y persiste una nueva cita médica en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cita registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<Cita> registrarCita(@Valid @RequestBody CitaDTO citaDTO) {
        Cita citaCreada = new Cita();
        citaCreada.setPacienteId(citaDTO.getPacienteId());
        citaCreada.setFecha(citaDTO.getFecha());
        citaCreada.setHora(citaDTO.getHora());
        if (citaDTO.getEstado() != null) {
            citaCreada.setEstado(citaDTO.getEstado());
        }
        Cita nuevaCita = citaService.registerCita(citaCreada);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas las citas", description = "Retorna todas las citas persistidas en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de citas obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Cita>> findAll(){
        List<Cita> citas = citaService.findAll();
        return ResponseEntity.ok(citas);
    }

    @Operation(summary = "Buscar cita por ID", description = "Retorna una cita específica según su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita encontrada"),
            @ApiResponse(responseCode = "404", description = "No existe una cita con el ID indicado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cita> findById(@PathVariable Long id){
        Cita cita = citaService.findById(id);
        return ResponseEntity.ok(cita);
    }

    @Operation(summary = "Eliminar una cita", description = "Elimina de forma permanente una cita identificada por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cita eliminada exitosamente, sin contenido de respuesta"),
            @ApiResponse(responseCode = "404", description = "No existe una cita con el ID indicado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar una cita existente", description = "Actualiza los datos de una cita ya registrada y persiste el cambio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No existe una cita con el ID indicado")
    })
    @PutMapping
    public ResponseEntity<Cita> actualizarCita(@Valid @RequestBody CitaUpdateDTO cita) {
        Cita citaActualizada = citaService.actualizarCita(cita);
        return ResponseEntity.ok(citaActualizada);
    }
}

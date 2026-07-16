package cl.duoc.ms_citas_db.service;

import cl.duoc.ms_citas_db.exception.CitaNotFoundException;
import cl.duoc.ms_citas_db.model.dto.CitaUpdateDTO;
import cl.duoc.ms_citas_db.model.entity.Cita;
import cl.duoc.ms_citas_db.repository.CitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaService citaService;

    private Cita cita;

    @BeforeEach
    void setUp() {
        cita = new Cita();
        cita.setId(1L);
        cita.setPacienteId(10L);
        cita.setFecha("2026-08-01");
        cita.setHora("10:00");
        cita.setEstado("PENDIENTE");
    }

    @Test
    void findAll_retornaTodasLasCitas() {
        when(citaRepository.findAll()).thenReturn(List.of(cita));

        List<Cita> resultado = citaService.findAll();

        assertThat(resultado).containsExactly(cita);
    }

    @Test
    void findById_retornaLaCita_cuandoExiste() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));

        Cita resultado = citaService.findById(1L);

        assertThat(resultado).isEqualTo(cita);
    }

    @Test
    void findById_lanzaExcepcion_cuandoNoExiste() {
        when(citaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> citaService.findById(99L))
                .isInstanceOf(CitaNotFoundException.class);
    }

    @Test
    void findByPacienteId_delegaEnElRepositorio() {
        when(citaRepository.findByPacienteId(10L)).thenReturn(cita);

        Cita resultado = citaService.findByPacienteId(10L);

        assertThat(resultado).isEqualTo(cita);
        verify(citaRepository).findByPacienteId(10L);
    }

    @Test
    void registerCita_guardaYRetornaLaCita() {
        when(citaRepository.save(cita)).thenReturn(cita);

        Cita resultado = citaService.registerCita(cita);

        assertThat(resultado).isEqualTo(cita);
        verify(citaRepository).save(cita);
    }

    @Test
    void eliminarCita_eliminaCuandoExiste() {
        when(citaRepository.existsById(1L)).thenReturn(true);

        citaService.eliminarCita(1L);

        verify(citaRepository).deleteById(1L);
    }

    @Test
    void eliminarCita_lanzaExcepcion_cuandoNoExiste() {
        when(citaRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> citaService.eliminarCita(99L))
                .isInstanceOf(CitaNotFoundException.class);

        verify(citaRepository, never()).deleteById(any());
    }

    @Test
    void actualizarCita_actualizaYPersisteLosDatos_cuandoExiste() {
        CitaUpdateDTO update = new CitaUpdateDTO(1L, 10L, "2026-08-02", "11:00", "CONFIRMADA");
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));
        when(citaRepository.save(cita)).thenReturn(cita);

        Cita resultado = citaService.actualizarCita(update);

        assertThat(resultado.getFecha()).isEqualTo("2026-08-02");
        assertThat(resultado.getHora()).isEqualTo("11:00");
        assertThat(resultado.getEstado()).isEqualTo("CONFIRMADA");
        verify(citaRepository, times(1)).save(cita);
    }

    @Test
    void actualizarCita_lanzaExcepcion_cuandoNoExiste() {
        CitaUpdateDTO update = new CitaUpdateDTO(99L, 10L, "2026-08-02", "11:00", "CONFIRMADA");
        when(citaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> citaService.actualizarCita(update))
                .isInstanceOf(CitaNotFoundException.class);

        verify(citaRepository, never()).save(any());
    }
}

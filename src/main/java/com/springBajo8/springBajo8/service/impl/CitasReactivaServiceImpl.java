package com.springBajo8.springBajo8.service.impl;

import com.springBajo8.springBajo8.model.TratamientoYPadecimiento;
import com.springBajo8.springBajo8.model.CitasReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
public class CitasReactivaServiceImpl implements IcitasReactivaService {

    @Autowired
    private IcitasReactivaRepository IcitasReactivaRepository;

    @Override
    public Mono<CitasReactiva> save(CitasReactiva citasReactiva) {
        return this.IcitasReactivaRepository.save(citasReactiva);
    }

    @Override
    public Mono<CitasReactiva> delete(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));

    }

    @Override
    public Mono<CitasReactiva> update(String id, CitasReactiva citasReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasReactiva1 -> {
                    citasReactiva.setId(id);
                    return save(citasReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<CitasReactiva> findByIdPaciente(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }

    @Override
    public Flux<CitasReactiva> findAll() {
        return this.IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<CitasReactiva> findById(String id) {
        return this.IcitasReactivaRepository.findById(id);
    }

    @Override
    public Flux<CitasReactiva> cancelDate(String id) {
        return this.IcitasReactivaRepository.findByIdPaciente(id)
                .flatMap(citasReactiva1 -> {
                    citasReactiva1.setEstadoReservaCita("Cancelada");
                    return save(citasReactiva1);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<CitasReactiva> consultDateAndHour(LocalDate fecha, String hora) {
        return this.IcitasReactivaRepository.findByFechaReservaCita(fecha)
                .filter(cita -> cita.getHoraReservaCita().equals(hora))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Flux<CitasReactiva> consultarMedicoQueLoAtendera(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente)
                .flatMap(citasReactiva1 -> {
                            CitasReactiva newCita = new CitasReactiva();
                            newCita.setNombreMedico(citasReactiva1.getNombreMedico());
                            newCita.setApellidosMedico(citasReactiva1.getApellidosMedico());
                            return Flux.just(newCita);
                        }
                )
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<List<TratamientoYPadecimiento>> consultTratamientosYPadecimientos(String id) {
        return this.IcitasReactivaRepository.findByIdPaciente(id)
                .flatMap(cita -> Mono.just(cita.getTratamientosYpadecimientos()))
                .switchIfEmpty(Mono.empty());
    }
}


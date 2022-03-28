package com.springBajo8.springBajo8.service;

import com.springBajo8.springBajo8.model.TratamientoYPadecimiento;
import com.springBajo8.springBajo8.model.CitasReactiva;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface IcitasReactivaService  {
    Mono<CitasReactiva> save(CitasReactiva citasReactiva);

    Mono<CitasReactiva> delete(String id);

    Mono<CitasReactiva> update(String id, CitasReactiva citasReactiva);

    Flux<CitasReactiva> findByIdPaciente(String idPaciente);

    Flux<CitasReactiva> findAll();

    Mono<CitasReactiva> findById(String id);

    Flux<CitasReactiva> cancelDate(String id);

    Flux<CitasReactiva> consultDateAndHour(LocalDate fecha, String hora);

    Flux<CitasReactiva> consultarMedicoQueLoAtendera(String id);

    Flux<List<TratamientoYPadecimiento>> consultTratamientosYPadecimientos(String id);
}

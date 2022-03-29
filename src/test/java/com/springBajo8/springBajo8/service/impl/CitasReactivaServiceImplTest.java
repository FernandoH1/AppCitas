package com.springBajo8.springBajo8.service.impl;

import com.springBajo8.springBajo8.model.CitasReactiva;
import com.springBajo8.springBajo8.model.TratamientoYPadecimiento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CitasReactivaServiceImplTest {

    @Autowired
    CitasReactivaServiceImpl servicio;

    @Test
    void findByIdPaciente() {
        CitasReactiva cr = new CitasReactiva("1","2","","","","",LocalDate.of(2022,3,28),"","");
        Flux<CitasReactiva> uno = servicio.findByIdPaciente("2");
        StepVerifier.create(uno).expectNext(cr);
    }

    @Test
    void findById() {
        CitasReactiva cr = new CitasReactiva("1","2","","","","",LocalDate.of(2022,3,28),"","");
        Mono<CitasReactiva> uno = servicio.findById("1");
        StepVerifier.create(uno).expectNext(cr);
    }

    @Test
    void cancelDate() {
        CitasReactiva cr = new CitasReactiva("1","2","","","","",LocalDate.of(2022,3,28),"","");
        Flux<CitasReactiva> uno = servicio.cancelDate("1");
        StepVerifier.create(uno).expectNext(cr);
    }

    @Test
    void consultDateAndHour() {
        CitasReactiva cr = new CitasReactiva("1","2","","","","",LocalDate.of(2022,3,28),"20:00","");
        Flux<CitasReactiva> uno = servicio.consultDateAndHour(cr.getFechaReservaCita(),"20:00");
        StepVerifier.create(uno).expectNext(cr);
    }

    @Test
    void consultarMedicoQueLoAtendera() {
        CitasReactiva cr = new CitasReactiva("1","2","","","","",LocalDate.of(2022,3,28),"","");
        Flux<CitasReactiva> uno = servicio.consultarMedicoQueLoAtendera("2");
        StepVerifier.create(uno).expectNext(cr);
    }

    @Test
    void consultTratamientosYPadecimientos() {
        TratamientoYPadecimiento tr = new TratamientoYPadecimiento("","");
        List<TratamientoYPadecimiento> tr2 = new ArrayList<TratamientoYPadecimiento>();
        tr2.add(tr);
        CitasReactiva cr = new CitasReactiva("1","2","","","","",LocalDate.of(2022,3,28),"","", tr2);
        Flux<List<TratamientoYPadecimiento>> uno = servicio.consultTratamientosYPadecimientos("2");
        StepVerifier.create(uno).expectNext(cr.getTratamientosYpadecimientos());
    }
}

package com.mycompany.parcking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


class Vehiculo {
    private String placa;
    private LocalTime horaEntrada;
    private LocalDate fechaEntrada;
    public Vehiculo(String placa) {
    this.placa = placa;
    this.horaEntrada = LocalTime.now();
    this.fechaEntrada =LocalDate.now();
    }

    public String getPlaca() {
        return placa;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
    
    public LocalDate getfechaEntrada() {
        return fechaEntrada;
    }
    
    
    
}





    
   







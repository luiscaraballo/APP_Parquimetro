
package com.mycompany.parcking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class Parquimetro {
    private ArrayList<Vehiculo> vehiculos;
        private final double TARIFA_POR_HORA = 1000; // Tarifa por hora en pesos chilenos

            public Parquimetro() {
                vehiculos = new ArrayList<>();
    }

    
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
    
    
    public void registrarVehiculo(String placa) throws Exception {
        if (placa == null || placa.isEmpty()) {
            throw new Exception("La placa no puede estar vacía.");
            }
                Vehiculo vehiculo = new Vehiculo(placa);
                    vehiculos.add(vehiculo);
                        System.out.println("Vehículo con placa " + placa + " registrado a las " + vehiculo.getHoraEntrada());
    }

    
    
    public double calcularMonto(String placa) throws Exception {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                long horas = ChronoUnit.HOURS.between(v.getHoraEntrada(), LocalDateTime.now());
                    return Math.max(horas, 1) * TARIFA_POR_HORA; // Cobra al menos por una hora
                    }
                        }
                            throw new Exception("Vehículo no encontrado.");
    }
}




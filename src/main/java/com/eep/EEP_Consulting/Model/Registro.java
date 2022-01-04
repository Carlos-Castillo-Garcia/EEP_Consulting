package com.eep.EEP_Consulting.Model;

import java.time.LocalDateTime;

public class Registro {
    String hora;
    String operacion;
    String log;

    public Registro() {}

    public Registro(String hora, String operacion, String log) {
        this.hora = hora;
        this.operacion = operacion;
        this.log = log;
    }

    public String getHora() {
        return hora;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getLog() {
        return log;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(hora).append("#");
        sb.append(operacion).append("#");
        sb.append(log).append("#");
        return sb.toString();
    }
}

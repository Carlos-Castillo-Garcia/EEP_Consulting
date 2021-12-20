package com.eep.EEP_Consulting.Model;

public class Camionero {
    String nombre;
    String apellidos;
    String correo_electronio;
    String numero_telefono;
    String fecha_nacimiento;
    String genero;
    String transporte;
    String comentarios;
    String contratado;

    public Camionero() {
    }

    public Camionero(String nombre, String apellidos, String correo_electronio,
                     String numero_telefono, String fecha_nacimiento, String genero,
                     String transporte, String comentarios, String contratado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo_electronio = correo_electronio;
        this.numero_telefono = numero_telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.transporte = transporte;
        this.comentarios = comentarios;
        this.contratado = contratado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo_electronio() {
        return correo_electronio;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public String getTransporte() {
        return transporte;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getContratado() {
        return contratado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo_electronio(String correo_electronio) {
        this.correo_electronio = correo_electronio;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setContratado(String contratado) {
        this.contratado = contratado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(nombre).append("#");
        sb.append(apellidos).append("#");
        sb.append(correo_electronio).append("#");
        sb.append(numero_telefono).append("#");
        sb.append(fecha_nacimiento).append("#");
        sb.append(genero).append("#");
        sb.append(transporte).append("#");
        sb.append(comentarios).append("#");
        sb.append(contratado).append("#");
        return sb.toString();
    }
}

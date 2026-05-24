package com.Sistemagestionresiduos.model;

public class Recoleccion {
    private int idRecoleccion;
    private String fecha;
    private String hora;
    private String ubicacion;
    private String tipoResiduo;
    private double cantidadKg;
    private String observaciones;
    private Usuario usuario;

    public Recoleccion() {
    }

    public Recoleccion(String fecha, String hora, String ubicacion, String tipoResiduo, double cantidadKg,
            String observaciones, Usuario usuario) {
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.tipoResiduo = tipoResiduo;
        this.cantidadKg = cantidadKg;
        this.observaciones = observaciones;
        this.usuario = usuario;
    }

    public Recoleccion(int idRecoleccion, String fecha, String hora, String ubicacion, String tipoResiduo,
            double cantidadKg, String observaciones, Usuario usuario) {
        this.idRecoleccion = idRecoleccion;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.tipoResiduo = tipoResiduo;
        this.cantidadKg = cantidadKg;
        this.observaciones = observaciones;
        this.usuario = usuario;
    }

    public int getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(int idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public double getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(double cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public String getObservations() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ID: " + idRecoleccion + " | Fecha: " + fecha + " | Hora: " + hora +
                " | Tipo: " + tipoResiduo + " | Peso: " + cantidadKg + "kg | Ubicación: " + ubicacion +
                " | Usuario ID: " + (usuario != null ? usuario.getIdUsuario() : "N/A");
    }
}
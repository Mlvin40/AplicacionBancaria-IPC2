/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.tarjetas;

import Backend.enums.TipoTarjeta;

import java.util.Random;

/**
 * @author melvin
 */
public class Tarjeta {

    private String numeroTarjeta;
    private TipoTarjeta tipoTarjeta;
    private double limite;
    private String nombreTitular;
    private String direccion;
    private String fechaSolicitud;
    private String numeroSolicitud; // para buscar una solicitud en el sistema
    private double salarioDuenio;

    private String estado;

    public Tarjeta(TipoTarjeta tipoTarjeta, double salarioDuenio, String nombreTitular, String direccion, String fechaSolicitud, String numeroSolicitud) {
        this.tipoTarjeta = tipoTarjeta;
        this.salarioDuenio = salarioDuenio;

        this.limite = establecerLimite(salarioDuenio);
        this.nombreTitular = nombreTitular;
        this.direccion = direccion;
        this.fechaSolicitud = fechaSolicitud;
        this.numeroTarjeta = generarNumeroTarjeta(tipoTarjeta);
        this.numeroSolicitud = numeroSolicitud;
    }

    /**
     * Constructor para crear una tarjeta con un número de tarjeta específico, este sirve para los reportes
     * @param numeroTarjeta
     * @param tipoTarjeta
     * @param limite
     * @param nombreTitular
     * @param direccion
     * @param fechaSolicitud
     * @param numeroSolicitud
     * @param estado
     */
    public Tarjeta(String numeroTarjeta, TipoTarjeta tipoTarjeta, double limite, String nombreTitular, String direccion, String fechaSolicitud, String numeroSolicitud, String estado) {
        this.numeroTarjeta = numeroTarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.limite = limite;
        this.nombreTitular = nombreTitular;
        this.direccion = direccion;
        this.fechaSolicitud = fechaSolicitud;
        this.numeroSolicitud = numeroSolicitud;
        this.estado = estado;
    }

    private double establecerLimite(double salarioDuenio) {
        // El límite de la tarjeta es el 60% del salario del dueño
        double limite = salarioDuenio * 0.60;
        return limite;
    }

    private String generarNumeroTarjeta(TipoTarjeta tipoTarjeta) {
        StringBuilder numero = new StringBuilder();
        //El primer segmento común
        numero.append("4256 3102 ");
        switch (tipoTarjeta) {
            case NACIONAL:
                numero.append("654");
                break;
            case REGIONAL:
                numero.append("656");
                break;
            case INTERNACIONAL:
                numero.append("658");
                break;
        }
        numero.append(generarNumeroAleatorio()).append(" ");
        for (int i = 0; i < 4; i++) {
            numero.append(generarNumeroAleatorio());
        }
        return numero.toString();
    }

    private int generarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(10);
    }

    //Getters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public double getLimite() {
        return limite;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

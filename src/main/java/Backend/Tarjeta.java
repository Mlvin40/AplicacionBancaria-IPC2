/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.enums.TipoTarjeta;

import java.util.Random;

/**
 * @author melvin
 */
public class Tarjeta {

    private String numeroTarjeta;
    private TipoTarjeta tipoTarjeta;
    private double Limite;
    private String nombreTitular;
    private String direccion;
    private String fechaSolicitud;
    private String numeroSolicitud; // para buscar una solicitud en el sistema
    //estado de la tarjeta

    public Tarjeta(TipoTarjeta tipoTarjeta, String nombreTitular, String direccion, String fechaSolicitud, String numeroSolicitud) {

        this.tipoTarjeta = tipoTarjeta;
        this.Limite = establecerLimite(tipoTarjeta);
        this.nombreTitular = nombreTitular;
        this.direccion = direccion;
        this.fechaSolicitud = fechaSolicitud;
        this.numeroTarjeta = generarNumeroTarjeta(tipoTarjeta);
        this.numeroSolicitud = numeroSolicitud;
    }

    private double establecerLimite(TipoTarjeta tipoTarjeta) {
        int limite = 0;
        switch (tipoTarjeta) {
            case NACIONAL:
                limite = 5000;
                break;
            case REGIONAL:
                limite = 10000;
                break;
            case INTERNACIONAL:
                limite = 20000;
                break;
        }
        return limite;
    }

    private String generarNumeroTarjeta(TipoTarjeta tipoTarjeta){
        StringBuilder numero = new StringBuilder();
        //El primer segmento común
        numero.append("4256 3102 ");
        switch (tipoTarjeta){
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

    private int generarNumeroAleatorio(){
        Random random = new Random();
        return random.nextInt(10);
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public double getLimite() {
        return Limite;
    }

    public void setLimite(double limite) {
        Limite = limite;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }
}

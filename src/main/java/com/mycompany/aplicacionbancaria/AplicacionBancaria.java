/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.aplicacionbancaria;

import Backend.database.ConexionMySQL;
import Backend.database.SolicitudDB;
import Backend.database.TarjetaDB;
import Backend.enums.TipoTarjeta;
import Backend.solicitudes.Solicitud;
import Backend.tarjetas.Tarjeta;

/**
 * @author melvin
 */
public class AplicacionBancaria {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        ConexionMySQL conexionMySQL = new ConexionMySQL();

        String numeroSolicitud = "01";
        //Pruebas de la base de datos
        Solicitud solicitud = new Solicitud(numeroSolicitud,
                "15/07/2024",
                TipoTarjeta.REGIONAL,
                "José Moisés Granados",
                20000,
                "5ta calle 25-7 zona 1");

        SolicitudDB solicitudDB = new SolicitudDB();
        solicitudDB.crearSolicitud(solicitud);


        if (solicitudDB.aprobarSolicitud(numeroSolicitud)) {
            TarjetaDB tarjetaDB = new TarjetaDB();
            Tarjeta tarjeta = tarjetaDB.obtenerDatosDesdeSolicitud(numeroSolicitud);
            tarjetaDB.crearTarjeta(tarjeta);
        }
    }
}

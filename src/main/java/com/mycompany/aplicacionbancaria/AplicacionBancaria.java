/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.aplicacionbancaria;

import Backend.database.ConexionMySQL;
import Backend.database.SolicitudDB;
import Backend.enums.Estado;
import Backend.enums.TipoTarjeta;
import Backend.solicitudes.Solicitud;

/**
 *
 * @author melvin
 */
public class AplicacionBancaria {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        ConexionMySQL conexionMySQL = new ConexionMySQL();


//        String fecha = "15/07/2024";
//        TipoTarjeta tipo = TipoTarjeta.NACIONAL;
//        String nombreSolicitante = "José Moisés Granados";
//        double salario = 10500.00;
//        String direccion = "5ta calle 25-7 zona 1";
//        Estado estado = Estado.PENDIENTE;

        Solicitud solicitud = new Solicitud(
                "1221233", // numeroSolicitud
                "15/07/2024", // fecha
                TipoTarjeta.NACIONAL, // tipo
                "José Moisés Granados", // nombreSolicitante
                10500.00, // salario
                "5ta calle 25-7 zona 1", // direccion
                Estado.PENDIENTE // estado
        );

        SolicitudDB solicitudDB = new SolicitudDB();
        solicitudDB.crearSolicitud(solicitud);

    }
}

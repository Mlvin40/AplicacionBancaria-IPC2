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
 * @author melvin
 */
public class AplicacionBancaria {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        ConexionMySQL conexionMySQL = new ConexionMySQL();

        //Pruebas de la base de datos
        Solicitud solicitud = new Solicitud("2",
                "15/07/2024",
                TipoTarjeta.NACIONAL,
                "José Moisés Granados",
                10500.00,
                "5ta calle 25-7 zona 1");

        SolicitudDB solicitudDB = new SolicitudDB();
        solicitudDB.crearSolicitud(solicitud);

    }
}

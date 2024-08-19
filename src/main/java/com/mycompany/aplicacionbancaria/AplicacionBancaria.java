/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.aplicacionbancaria;

import Backend.database.*;
import Backend.enums.TipoMovimiento;
import Backend.enums.TipoTarjeta;
import Backend.movimientos.MovimientoTarjeta;
import Backend.solicitudes.Solicitud;
import Backend.tarjetas.Tarjeta;
import com.sun.security.jgss.GSSUtil;

import java.time.LocalDate;

/**
 * @author melvin
 */
public class AplicacionBancaria {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        ConexionMySQL conexionMySQL = new ConexionMySQL();

        String numeroSolicitud = "15";
        //Pruebas de la base de datos
        Solicitud solicitud = new Solicitud(numeroSolicitud,
                "15/07/2024",
                TipoTarjeta.REGIONAL,
                "José Moisés Granados",
                2000,
                "5ta calle 25-7 zona 1");

        SolicitudDB solicitudDB = new SolicitudDB();
        solicitudDB.crearSolicitud(solicitud);

        TarjetaDB tarjetaDB = new TarjetaDB();

        if (solicitudDB.aprobarSolicitud(numeroSolicitud)) {

            Tarjeta tarjeta = tarjetaDB.obtenerDatosDesdeSolicitud(numeroSolicitud);
            tarjetaDB.crearTarjeta(tarjeta);
        }

        tarjetaDB.cancelarTarjeta("4256 3102 6563 3525");
        MovimientoTarjeta movimientoTarjeta = new MovimientoTarjeta(
                "4256 3102 6562 2722",
                "15/07/2024",
                TipoMovimiento.valueOf("CARGO"),
                "Compra de zapatos",
                "Zapatería el Zapato Feliz",
                1500.25);

        MovimientoTarjetaDB movimientoTarjetaDB = new MovimientoTarjetaDB();
        movimientoTarjetaDB.registrarMovimiento(movimientoTarjeta);
        System.out.println();

        System.out.println("----------------------------------------");


        MovimientoTarjeta movimientoTarjeta2 = new MovimientoTarjeta(
                "4256 3102 6562 2722",
                "15/07/2024",
                TipoMovimiento.valueOf("CARGO"),
                "Compra de zapatos",
                "Zapatería el Zapato Feliz",
                1500.25);

        movimientoTarjetaDB.registrarMovimiento(movimientoTarjeta2);
    }
}

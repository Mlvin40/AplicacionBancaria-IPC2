/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.aplicacionbancaria;

import Backend.database.*;
import Backend.enums.TipoMovimiento;
import Backend.movimientos.MovimientoTarjeta;
import Backend.reportes.EstadoCuenta;
import Frontend.VentanaPrincipal;
import Frontend.reportes.PanelEstadoCuenta;
import java.awt.GridLayout;

import javax.swing.*;

/**
 * @author melvin
 */
public class AplicacionBancaria {

    public static void main(String[] args) {

        SolicitudDB solicitudDB = new SolicitudDB();
        TarjetaDB tarjetaDB = new TarjetaDB();
        ValidarMovimientoDB validarMovimientoDB = new ValidarMovimientoDB();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);

    }
}

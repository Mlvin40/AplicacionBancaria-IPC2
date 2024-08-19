package Backend.reportes;

import Backend.database.ConexionMySQL;
import Backend.database.ValidarMovimientoDB;
import Backend.enums.TipoMovimiento;
import Backend.movimientos.MovimientoTarjeta;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoCuenta {

    private String numeroTarjeta;
    private String tipoTarjeta;
    private String nombreTitular;
    private String direccion;
    private List<MovimientoTarjeta> movimientosRealizados;

    //Datos del estado de cuenta
    private double montoTotal;
    private double intereses;
    private double saldoTotal;

    private Connection connection;

    public EstadoCuenta(String numeroTarjeta){
        this.numeroTarjeta = numeroTarjeta;
        this.movimientosRealizados = new ArrayList<>();
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Llenar los datos del estado de cuenta
        realizarAcciones();
    }

    //Metodo para llenar los datos del estado de cuenta en base a la tarjeta
    private void obtenerDatosCliente(){
        //Obtener los datos del cliente
        String consulta = "SELECT tipo, nombre, direccion FROM tarjetas WHERE numero_tarjeta = ?";
        try {
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, numeroTarjeta);
            ResultSet resultSet = statementConsulta.executeQuery();

            if (resultSet.next()) {
                this.tipoTarjeta = resultSet.getString("tipo");
                this.nombreTitular = resultSet.getString("nombre");
                this.direccion = resultSet.getString("direccion");
            } else {
                System.out.println("Tarjeta no encontrada");
                JOptionPane.showMessageDialog(null, "Tarjeta no encontrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la tarjeta: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar la tarjeta");
        }
    }


    /**
     * Con este metodo se obtienen todos los movimientos de la tarjeta
     */
    private void obtenerMovimientos() {
        // Obtener los movimientos de la tarjeta
        String consulta = "SELECT numero_tarjeta, fecha, tipo_movimiento, descripcion, establecimiento, monto FROM movimientos WHERE numero_tarjeta = ?";

        try {
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, numeroTarjeta);
            ResultSet resultSet = statementConsulta.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores desde el ResultSet
                String numeroTarjeta = resultSet.getString("numero_tarjeta");
                String fecha = resultSet.getString("fecha");
                String tipoMovimientoStr = resultSet.getString("tipo_movimiento");
                TipoMovimiento tipoMovimiento = TipoMovimiento.valueOf(tipoMovimientoStr.toUpperCase()); //parsear el string a enum
                String descripcion = resultSet.getString("descripcion");
                String establecimiento = resultSet.getString("establecimiento");
                double monto = resultSet.getDouble("monto");

                // Crear un nuevo objeto MovimientoTarjeta
                MovimientoTarjeta movimiento = new MovimientoTarjeta(numeroTarjeta, fecha, tipoMovimiento, descripcion, establecimiento, monto);
                movimientosRealizados.add(movimiento);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los movimientos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar los movimientos");
        }
    }

    private void calcularTotales(){
        ValidarMovimientoDB validarMovimientoDB = new ValidarMovimientoDB();
        this.montoTotal = validarMovimientoDB.montoTotal(numeroTarjeta);
        this.intereses = validarMovimientoDB.generarInteres(numeroTarjeta);
        this.saldoTotal = validarMovimientoDB.establecerSaldoTotal(numeroTarjeta);
    }

    private void realizarAcciones(){
        obtenerDatosCliente();
        obtenerMovimientos();
        calcularTotales();
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<MovimientoTarjeta> getMovimientosRealizados() {
        return movimientosRealizados;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public double getIntereses() {
        return intereses;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }
}

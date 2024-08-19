package Backend.database;

import Backend.movimientos.MovimientoTarjeta;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidarMovimientoDB {

    private Connection connection;

    public ValidarMovimientoDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean estaActivo(MovimientoTarjeta movimientoTarjeta) {
        //Validar si la tarjeta está activa
        String consulta = "SELECT estado FROM tarjetas WHERE numero_tarjeta = ?";
        try {
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, movimientoTarjeta.getNumeroTarjeta());
            ResultSet resultSet = statementConsulta.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("estado").equals("ACTIVA")) {
                    return true;
                } else {
                    System.out.println("La tarjeta esta inactiva");
                    JOptionPane.showMessageDialog(null, "La tarjeta esta inactiva");
                    return false;
                }
            } else {
                System.out.println("Tarjeta no encontrada");
                JOptionPane.showMessageDialog(null, "Tarjeta no encontrada");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la tarjeta: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar la tarjeta");
            return false;
        }
    }

    // Con esto checamos que la tarjeta tenga suficiente limite para realizar el movimiento
    public boolean tieneCreditoSuficiente(MovimientoTarjeta movimientoTarjeta) {
        String numeroTarjeta = movimientoTarjeta.getNumeroTarjeta();
        double limite = TarjetaDB.consultarLimite(numeroTarjeta);
        double saldoactual = limite - montoTotal(numeroTarjeta);

        //si es un abono debe devolver true
        if (movimientoTarjeta.getTipoMovimiento().name().equals("ABONO")) {
            return true;
        }

        if (saldoactual >= movimientoTarjeta.getMonto()) {
            System.out.println("Tiene suficiente credito");
            return true;
        } else {

            System.out.println("No tiene suficiente credito");
            JOptionPane.showMessageDialog(null, "No tiene suficiente credito");
            return false;
        }
    }

    private double sumarAbonos(String numeroTarjeta) {
        //Sumar los abonos de la tarjeta
        String consultaAbonos = "SELECT SUM(monto) AS total FROM movimientos WHERE numero_tarjeta = ? AND tipo_movimiento = 'ABONO'";

        try {
            PreparedStatement statementConsultaAbonos = connection.prepareStatement(consultaAbonos);
            statementConsultaAbonos.setString(1, numeroTarjeta);
            ResultSet resultSetAbonos = statementConsultaAbonos.executeQuery();

            if (resultSetAbonos.next()) {
                return resultSetAbonos.getDouble("total");
            } else {
                return 0.0;
            }
        } catch (SQLException e) {
            System.out.println("Error al sumar los abonos: " + e.getMessage());
            return 0.0;
        }
    }

    private double sumarCargos(String numeroTarjeta) {
        //Sumar los cargos de la tarjeta
        String consultaCargos = "SELECT SUM(monto) AS total FROM movimientos WHERE numero_tarjeta = ? AND tipo_movimiento = 'CARGO'";

        try {
            PreparedStatement statementConsultaCargos = connection.prepareStatement(consultaCargos);
            statementConsultaCargos.setString(1, numeroTarjeta);
            ResultSet resultSetCargos = statementConsultaCargos.executeQuery();

            if (resultSetCargos.next()) {
                return resultSetCargos.getDouble("total");
            } else {
                return 0.0;
            }
        } catch (SQLException e) {
            System.out.println("Error al sumar los cargos: " + e.getMessage());
            return 0.0;
        }
    }

    public double montoTotal(String numeroTarjeta) {
        double abonos = sumarAbonos(numeroTarjeta);
        double cargos = sumarCargos(numeroTarjeta);
        double montoTotal = cargos - abonos;
        //Significa que la deuda esta cancelada
        if (montoTotal < 0) {
            return 0.0;
        }
        return montoTotal;
    }

    //En este metodo se calcula el interes que se le va a cobrar a la persona
    public double generarInteres(String numeroTarjeta){
        double interes = InteresesTipo.establecerInteres(numeroTarjeta);
        double interesGenerado = montoTotal(numeroTarjeta) * interes;
        return interesGenerado;
    }

    //En este metodo se calcula lo que el usuario debe pagar por intereses
    //Para quedar al corriente con su tarjeta este restultado debe de ser 0
    //Para poder cancelar una tarjeta debe devolver 0, que esta al corriente con su tarjeta
    public double establecerSaldoTotal(String numeroTarjeta) {
        return montoTotal(numeroTarjeta) + generarInteres(numeroTarjeta);
    }
}

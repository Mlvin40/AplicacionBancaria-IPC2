package Backend.database;

import Backend.enums.TipoTarjeta;
import Backend.tarjetas.Tarjeta;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TarjetaDB {

    private static Connection connection;

    public TarjetaDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Para la funcionalidad de consultar la tarjeta
    public String consultarTarjeta(String tarjeta) {

        StringBuilder sb = new StringBuilder();
        String consulta = "SELECT * FROM tarjetas WHERE numero_tarjeta = ?";
        try {
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, tarjeta);
            ResultSet resultSet = statementConsulta.executeQuery();
            if (resultSet.next()) {

                sb.append("Datos de la tarjeta: \n");
                sb.append("Número de tarjeta: ").append(resultSet.getString("numero_tarjeta")).append("\n");
                sb.append("Tipo de tarjeta: ").append(resultSet.getString("tipo")).append("\n");
                sb.append("Límite: ").append(resultSet.getDouble("limite")).append("\n");
                sb.append("Nombre del titular: ").append(resultSet.getString("nombre")).append("\n");
                sb.append("Dirección: ").append(resultSet.getString("direccion")).append("\n");
                sb.append("Estado de tarjeta: ").append(resultSet.getString("estado")).append("\n");

            } else {
                return "Tarjeta no encontrada";
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la tarjeta: " + e.getMessage());
        }

        return sb.toString();
    }

    // Agregar tarjeta a la base de datos
    public void crearTarjeta(Tarjeta tarjeta) {

        //TipoTarjeta tipoTarjeta, String nombreTitular, String direccion, String fechaSolicitud)
        String insert = "INSERT INTO tarjetas (numero_tarjeta, tipo, limite, nombre, direccion, fecha, numero_solicitud) VALUES "
                + "('" + tarjeta.getNumeroTarjeta() + "', '" + tarjeta.getTipoTarjeta() + "', " + tarjeta.getLimite() + ", '" + tarjeta.getNombreTitular() + "', '"
                + tarjeta.getDireccion() + "', '" + tarjeta.getFechaSolicitud() + "', '" + tarjeta.getNumeroSolicitud() + "')";

        try {
            Statement statementInsert = connection.createStatement();
            int rowsAffected = statementInsert.executeUpdate(insert);
            System.out.println("Tarjeta agregada");
            JOptionPane.showMessageDialog(null, "Tarjeta agregada");

        } catch (SQLException e) {
            System.out.println("Error al insertar en la tabla tarjetas");
            JOptionPane.showMessageDialog(null, "Error al insertar en la tabla tarjetas");
        }
    }


    // Con este método se obtienen los datos de la solicitud para crear la tarjeta
    public Tarjeta obtenerDatosDesdeSolicitud(String numeroSolicitud) {
        String consulta = "SELECT tipo_tarjeta, nombre_solicitante, direccion, fecha, salario FROM solicitudes WHERE numero_solicitud = ?";
        try {

            // Preparar la consulta
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, numeroSolicitud);
            ResultSet resultSet = statementConsulta.executeQuery();

            // Verificar si se encontró la solicitud
            if (resultSet.next()) {
                // Obtener los datos de la solicitud
                String tipoTarjetaStr = resultSet.getString("tipo_tarjeta");
                String nombreSolicitante = resultSet.getString("nombre_solicitante");
                String direccion = resultSet.getString("direccion");
                String fechaSolicitud = resultSet.getString("fecha");
                double salarioDuenio = resultSet.getDouble("salario");

                // Convertir el tipo de tarjeta al enum TipoTarjeta
                TipoTarjeta tipoTarjeta = TipoTarjeta.valueOf(tipoTarjetaStr);

                // Crear y retornar la instancia de Tarjeta
                return new Tarjeta(tipoTarjeta, salarioDuenio, nombreSolicitante, direccion, fechaSolicitud, numeroSolicitud);
            } else {
                // Si no se encontró la solicitud, retorna null o lanza una excepción
                System.out.println("Solicitud no encontrada");
                JOptionPane.showMessageDialog(null, "Solicitud no encontrada");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la solicitud: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la solicitud");
            return null;
        }
    }

    public void cancelarTarjeta(String numeroTarjeta) {
        // Consulta para verificar si la tarjeta existe y su estado actual
        String consulta = "SELECT estado FROM tarjetas WHERE numero_tarjeta = ?";
        String update = "UPDATE tarjetas SET estado = 'CANCELADO' WHERE numero_tarjeta = ?";

        try {
            // Preparar la consulta para verificar el estado de la tarjeta
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, numeroTarjeta);
            ResultSet resultSet = statementConsulta.executeQuery();

            if (resultSet.next()) {
                String estadoActual = resultSet.getString("estado");

                if ("CANCELADO".equals(estadoActual)) {
                    System.out.println("La tarjeta ya está cancelada.");
                    JOptionPane.showMessageDialog(null, "La tarjeta ya está cancelada.");
                    return;
                }


                //Si tiene un saldo pendiente no se puede cancelar
                ValidarMovimientoDB validarMovimientoDB = new ValidarMovimientoDB();
                double saldoTotal = validarMovimientoDB.establecerSaldoTotal(numeroTarjeta);
                if (saldoTotal > 0) {
                    System.out.println("No se puede cancelar la tarjeta, tiene un saldo pendiente de : " + saldoTotal);
                    JOptionPane.showMessageDialog(null, "No se puede cancelar la tarjeta, tiene un saldo pendiente de : " + saldoTotal);
                    return;
                }

                // Preparar la actualización si el estado no es 'CANCELADO'
                PreparedStatement statementUpdate = connection.prepareStatement(update);
                statementUpdate.setString(1, numeroTarjeta);
                int rowsAffected = statementUpdate.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Tarjeta cancelada exitosamente.");
                    JOptionPane.showMessageDialog(null, "Tarjeta cancelada exitosamente.");
                }
            } else {
                // Si no se encuentra la tarjeta
                System.out.println("Tarjeta no encontrada.");
                JOptionPane.showMessageDialog(null, "Tarjeta no encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cancelar la tarjeta: ");
            JOptionPane.showMessageDialog(null, "Error al cancelar la tarjeta");
        }
    }

    public static double consultarLimite(String numeroTarjeta) {
        String consulta = "SELECT limite FROM tarjetas WHERE numero_tarjeta = ?";
        try {
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, numeroTarjeta);
            ResultSet resultSet = statementConsulta.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("limite");
            } else {
                System.out.println("Tarjeta no encontrada");
                return 0.0;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el límite de la tarjeta");
            return 0.0;
        }
    }
}
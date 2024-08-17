package Backend.database;

import Backend.tarjetas.Tarjeta;
import Backend.solicitudes.Solicitud;

import java.sql.*;

public class SolicitudDB {

    Connection connection;

    public SolicitudDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearSolicitud(Solicitud solicitud) {
        String insert = "INSERT INTO solicitudes (numero_solicitud, fecha, tipo_tarjeta, nombre_solicitante, salario, direccion) VALUES " +
                "('" + solicitud.getNumeroSolicitud() + "', '" + formatoFecha(solicitud.getFecha()) + "', '" + solicitud.getTipo() + "', '" + solicitud.getNombreSolicitante() + "', " +
                solicitud.getSalario() + ", '" + solicitud.getDireccion() + "')";

        try {
            Statement statementInsert = connection.createStatement();
            int rowsAffected = statementInsert.executeUpdate(insert);
            System.out.println("Rows affected> " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos");
        }
    }

    private void rechazarSolictud(String numeroSolicitud) {
        String update = "UPDATE solicitudes SET estado = 'RECHAZADA' WHERE numero_solicitud = ?";
        try {
            PreparedStatement statementUpdate = connection.prepareStatement(update);
            statementUpdate.setString(1, numeroSolicitud);
            int rowsAffected = statementUpdate.executeUpdate();
            System.out.println("Rows affected> " + rowsAffected);
            System.out.println("Solicitud rechazada");
        } catch (SQLException e) {
            System.out.println("Error al rechazar la solicitud: " + e.getMessage());
        }
    }

    public boolean aprobarSolicitud(String numeroSolicitud) {

        if (!autorizarSolicitud(numeroSolicitud)) {
            rechazarSolictud(numeroSolicitud);
            return false;
        }

        // Consulta para verificar si la solicitud existe y está pendiente
        String consulta = "SELECT COUNT(*) FROM solicitudes WHERE numero_solicitud = ? AND estado = 'PENDIENTE'";
        try {

            // Preparar la consulta
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, numeroSolicitud);
            ResultSet resultSet = statementConsulta.executeQuery();

            // Verificar si la solicitud existe
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Si existe, actualizar el estado a AUTORIZADA
                String update = "UPDATE solicitudes SET estado = 'AUTORIZADA' WHERE numero_solicitud = ?";
                PreparedStatement statementUpdate = connection.prepareStatement(update);
                statementUpdate.setString(1, numeroSolicitud);
                int rowsAffected = statementUpdate.executeUpdate();

                System.out.println("Rows affected> " + rowsAffected);
                System.out.println("Solicitud aprobada");
                return true;
            } else {
                // Si no existe, retornar false
                System.out.println("La solicitud no existe o no está pendiente");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la solicitud: " + e.getMessage());
            return false;
        }
    }

    // Metodo para ver si se cumple con el minimo de salario y autorizar la solicitud
    // Metodo de la clase SolicitudDB
    private boolean autorizarSolicitud(String numeroSolicitud) {
        String query = "SELECT salario, tipo_tarjeta FROM solicitudes WHERE numero_solicitud = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, numeroSolicitud);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double salario = resultSet.getDouble("salario");
                String tipoTarjeta = resultSet.getString("tipo_tarjeta");

                double porcentajeSalario = salario * 0.60;
                double limiteCredito;
                switch (tipoTarjeta) {
                    case "NACIONAL":
                        limiteCredito = 5000;
                        break;
                    case "REGIONAL":
                        limiteCredito = 10000;
                        break;
                    case "INTERNACIONAL":
                        limiteCredito = 20000;
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de tarjeta no válido");
                }
                if (porcentajeSalario >= limiteCredito) {
                    //Como cumple con el minimo de salario, se autoriza la solicitud
                    return true;
                } else {
                    System.out.println("El 60% del salario no supera el límite de crédito del tipo de tarjeta.");
                    return false;
                }
            } else {
                System.out.println("Solicitud no encontrada.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al autorizar la solicitud: " + e.getMessage());
            return false;
        }
    }

    //Metodo para formatear la fecha a formato yyyy-MM-dd compatible con MySQL
    private String formatoFecha(String fecha) {
        fecha = fecha.trim();
        String[] fechaArray = fecha.split("/");
        String fechaFormateada = fechaArray[2] + "-" + fechaArray[1] + "-" + fechaArray[0];
        return fechaFormateada;
    }
}

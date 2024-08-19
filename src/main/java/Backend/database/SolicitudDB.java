package Backend.database;

import Backend.solicitudes.Solicitud;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class SolicitudDB {

    private Connection connection;

    public SolicitudDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearSolicitud(Solicitud solicitud) {
        String insert = "INSERT INTO solicitudes (numero_solicitud, fecha, tipo_tarjeta, nombre_solicitante, salario, direccion) VALUES " +
                "('" + solicitud.getNumeroSolicitud() + "', '" + Herramientas.formatoFecha(solicitud.getFecha()) + "', '" + solicitud.getTipo() + "', '" + solicitud.getNombreSolicitante() + "', " +
                solicitud.getSalario() + ", '" + solicitud.getDireccion() + "')";

        try {
            Statement statementInsert = connection.createStatement();
            int rowsAffected = statementInsert.executeUpdate(insert);
            System.out.println("Rows affected> " + rowsAffected);
            JOptionPane.showMessageDialog(null, "Solicitud Registrada");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar en la base de datos");
            System.out.println("Error al insertar en la base de datos");
        }
    }

    private void rechazarSolicitud(String numeroSolicitud) {

        LocalDate fechaActual = LocalDate.now();
        //Actualizar el estado y la fecha en la base de datos
        String update = "UPDATE solicitudes SET estado = 'RECHAZADA', fecha = ? WHERE numero_solicitud = ?";
        try {
            PreparedStatement statementUpdate = connection.prepareStatement(update);
            statementUpdate.setString(1, fechaActual.toString());
            statementUpdate.setString(2, numeroSolicitud);
            int rowsAffected = statementUpdate.executeUpdate();

            System.out.println("Solicitud rechazada");
            //JOptionPane.showMessageDialog(null, "Solicitud rechazada");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al rechazar la solicitud");
            System.out.println("Error al rechazar la solicitud: " + e.getMessage());
        }
    }

    public boolean aprobarSolicitud(String numeroSolicitud) {
        if (!autorizarSolicitud(numeroSolicitud)) {
            rechazarSolicitud(numeroSolicitud);
            return false;
        }

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Consulta para verificar si la solicitud existe y está pendiente
        String consulta = "SELECT COUNT(*) FROM solicitudes WHERE numero_solicitud = ? AND estado = 'PENDIENTE'";
        try {
            // Preparar la consulta
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            statementConsulta.setString(1, numeroSolicitud);
            ResultSet resultSet = statementConsulta.executeQuery();

            // Verificar si la solicitud existe
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Si existe, actualizar el estado a AUTORIZADA y la fecha a la fecha actual
                String update = "UPDATE solicitudes SET estado = 'AUTORIZADA', fecha = ? WHERE numero_solicitud = ?";
                PreparedStatement statementUpdate = connection.prepareStatement(update);
                statementUpdate.setString(1, fechaActual.toString());  // Utilizar la fecha actual
                statementUpdate.setString(2, numeroSolicitud);
                int rowsAffected = statementUpdate.executeUpdate();

                System.out.println("Rows affected> " + rowsAffected);
                System.out.println("Solicitud aprobada");
                JOptionPane.showMessageDialog(null, "Solicitud aprobada");

                return true;
            } else {
                // Si no existe, retornar false
                System.out.println("La solicitud no existe o no está pendiente");
                JOptionPane.showMessageDialog(null, "La solicitud no existe o no está pendiente");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la solicitud: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar la solicitud");
            return false;
        }
    }
        // Metodo para ver si se cumple con el minimo de salario y autorizar la solicitud
        // Metodo de la clase SolicitudDB
        private boolean autorizarSolicitud(String numeroSolicitud){
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
                        JOptionPane.showMessageDialog(null, "El 60% del salario no supera el límite de crédito del tipo de tarjeta.");
                        return false;
                    }
                } else {
                    System.out.println("Solicitud no encontrada.");
                    JOptionPane.showMessageDialog(null, "Solicitud no encontrada.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al autorizar la solicitud: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Error al autorizar la solicitud.");
                return false;
            }
        }

    }

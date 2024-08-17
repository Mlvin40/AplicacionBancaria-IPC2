package Backend.database;

import Backend.enums.TipoTarjeta;
import Backend.tarjetas.Tarjeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TarjetaDB {

    private Connection connection;

    public TarjetaDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

        } catch (SQLException e) {
            System.out.println("Error al insertar en la tabla tarjetas");
        }
    }

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
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la solicitud: " + e.getMessage());
            return null;
        }
    }


//    private String formatoFecha(String fecha) {
//        fecha = fecha.trim();
//        String[] fechaArray = fecha.split("/");
//        String fechaFormateada = fechaArray[2] + "-" + fechaArray[1] + "-" + fechaArray[0];
//        return fechaFormateada;
//    }

}

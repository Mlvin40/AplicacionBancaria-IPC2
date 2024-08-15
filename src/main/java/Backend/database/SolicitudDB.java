package Backend.database;

import Backend.Tarjeta;
import Backend.solicitudes.Solicitud;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SolicitudDB {

    Connection connection;

    public SolicitudDB( ) {
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

            //agregar la tarjeta a la tabla tarjetas
            Tarjeta tarjeta = new Tarjeta(
                    solicitud.getTipo(),
                    solicitud.getNombreSolicitante(),
                    solicitud.getDireccion(),
                    solicitud.getFecha(),
                    solicitud.getNumeroSolicitud()
            );
            TarjetaDB tarjetaDB = new TarjetaDB();
            tarjetaDB.agregarTarjeta(tarjeta);

        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos");
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

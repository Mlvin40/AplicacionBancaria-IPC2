package Backend.database;

import Backend.Tarjeta;
import Backend.solicitudes.Solicitud;

import java.sql.Connection;
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

    public void agregarTarjeta(Tarjeta tarjeta) {

        //TipoTarjeta tipoTarjeta, String nombreTitular, String direccion, String fechaSolicitud)
        String insert = "INSERT INTO tarjetas (numero_tarjeta, tipo, limite, nombre, direccion, fecha, numero_solicitud) VALUES "
                + "('" + tarjeta.getNumeroTarjeta() + "', '" + tarjeta.getTipoTarjeta() + "', " + tarjeta.getLimite() + ", '" + tarjeta.getNombreTitular() + "', '"
                + tarjeta.getDireccion() + "', '" + formatoFecha(tarjeta.getFechaSolicitud()) + "', '" + tarjeta.getNumeroSolicitud() + "')";

        try {
            Statement statementInsert = connection.createStatement();
            int rowsAffected = statementInsert.executeUpdate(insert);
            System.out.println("Tarjeta agregada");

        } catch (SQLException e) {
            System.out.println("Error al insertar en la tabla tarjetas");
        }
    }

    private String formatoFecha(String fecha) {
        fecha = fecha.trim();
        String[] fechaArray = fecha.split("/");
        String fechaFormateada = fechaArray[2] + "-" + fechaArray[1] + "-" + fechaArray[0];
        return fechaFormateada;
    }

}

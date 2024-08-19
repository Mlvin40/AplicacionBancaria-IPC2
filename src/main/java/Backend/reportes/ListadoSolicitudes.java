package Backend.reportes;

import Backend.database.ConexionMySQL;
import Backend.enums.TipoTarjeta;
import Backend.solicitudes.Solicitud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListadoSolicitudes {
    //En esta clase debo de Crear una lista de objetos de tipo Solicitud
    //Estos deben de ser llenados con la informacion de la base de datos

    private Connection connection;

    public ListadoSolicitudes() {
        try {
            connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Solicitud> obtenerSolicitudes() {
        List<Solicitud> solicitudes = new ArrayList<>();
        String insert = "SELECT numero_solicitud, fecha, tipo_tarjeta, nombre_solicitante, salario, direccion, estado FROM solicitudes";

        try (PreparedStatement pst = connection.prepareStatement(insert);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                // Obtener los valores desde el ResultSet
                String numeroSolicitud = rs.getString("numero_solicitud");
                String fecha = rs.getString("fecha");
                String tipo = rs.getString("tipo_tarjeta");
                String nombreSolicitante = rs.getString("nombre_solicitante");
                double salario = rs.getDouble("salario");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");
                // Convertir el tipo de tarjeta a un enum
                TipoTarjeta tipoTarjeta = TipoTarjeta.valueOf(tipo.toUpperCase());

                Solicitud solicitud = new Solicitud(numeroSolicitud, fecha, tipoTarjeta, nombreSolicitante, salario, direccion, estado);
                // Agregar la solicitud a la lista
                solicitudes.add(solicitud);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitudes;
    }
}

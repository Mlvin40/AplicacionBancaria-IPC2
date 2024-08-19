package Backend.reportes;

import Backend.database.ConexionMySQL;
import Backend.enums.TipoTarjeta;
import Backend.tarjetas.Tarjeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListadoTarjetas {
    //En esta clase debo de Crear una lista de objetos de tipo Tarjeta
    //Estos deben de ser llenados con la informacion de la base de datos

    private Connection connection;

    public ListadoTarjetas() {
        try {
            connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tarjeta> obtenerTarjetas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        String insert = "SELECT numero_tarjeta, tipo, limite, nombre, direccion, fecha, estado, numero_solicitud FROM tarjetas";

        try (PreparedStatement pst = connection.prepareStatement(insert);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                // Obtener los valores desde el ResultSet
                String numeroTarjeta = rs.getString("numero_tarjeta");
                String tipo = rs.getString("tipo");
                double limite = rs.getDouble("limite");
                String nombreTitular = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String fechaSolicitud = rs.getString("fecha");
                String estado = rs.getString("estado");
                String numeroSolicitud = rs.getString("numero_solicitud");

                // Convertir el tipo de tarjeta a un enum
                TipoTarjeta tipoTarjeta = TipoTarjeta.valueOf(tipo.toUpperCase());
                Tarjeta tarjeta = new Tarjeta(numeroTarjeta, tipoTarjeta, limite, nombreTitular, direccion, fechaSolicitud, numeroSolicitud, estado);

                // Agregar la tarjeta a la lista
                tarjetas.add(tarjeta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tarjetas;
    }
}

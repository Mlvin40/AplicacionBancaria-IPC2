package Backend.reportes;

import Backend.database.ConexionMySQL;
import Backend.tarjetas.Tarjeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListadoEstadosCuenta {

    private List<String> tarjetas; // esta variable almacena la lista de tarjetas activas
    private Connection connection;

    public ListadoEstadosCuenta() {
        try {
            connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        obtenerTarjetasActivas();
    }

    private void obtenerTarjetasActivas() {
        //Obtener las tarjetas activas
        this.tarjetas = new ArrayList<>();
        String consulta = "SELECT numero_tarjeta FROM tarjetas WHERE estado = 'ACTIVA'";
        try {
            PreparedStatement statementConsulta = connection.prepareStatement(consulta);
            ResultSet resultSet = statementConsulta.executeQuery();

            while (resultSet.next()) {
                String numeroTarjeta = resultSet.getString("numero_tarjeta");
                tarjetas.add(numeroTarjeta);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las tarjetas: " + e.getMessage());
        }
    }

    public List<EstadoCuenta> obtenerEstadosCuenta() {
        List<EstadoCuenta> estadosCuenta = new ArrayList<>();
        for (String numeroTarjeta : tarjetas) {
            EstadoCuenta estadoCuenta = new EstadoCuenta(numeroTarjeta);
            estadosCuenta.add(estadoCuenta);
        }
        return estadosCuenta;
    }
}


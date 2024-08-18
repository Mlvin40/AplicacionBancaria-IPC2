package Backend.database;

import Backend.movimientos.MovimientoTarjeta;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MovimientoTarjetaDB {

    private Connection connection;

    public MovimientoTarjetaDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarMovimiento(MovimientoTarjeta movimientoTarjeta) {
        //Validar si la tarjeta está activa
        ValidarMovimientoDB validarMovimiento = new ValidarMovimientoDB();
        if (!validarMovimiento.estaActivo(movimientoTarjeta)) {
            return;
        }

        //Validar le alcanza el credito
        if (!validarMovimiento.tieneCreditoSuficiente(movimientoTarjeta)) {
            return;
        }

        String insert = "INSERT INTO movimientos (numero_tarjeta, fecha, tipo_movimiento, descripcion, establecimiento, monto) VALUES " +
                "('" + movimientoTarjeta.getNumeroTarjeta() + "', '" + Herramientas.formatoFecha(movimientoTarjeta.getFecha()) + "', '" + movimientoTarjeta.getTipoMovimiento() + "', '" + movimientoTarjeta.getDescripcion() + "', '" + movimientoTarjeta.getEstablecimiento() + "', " +
                movimientoTarjeta.getMonto() + ")";

        try {
            Statement statementInsert = connection.createStatement();
            int rowsAffected = statementInsert.executeUpdate(insert);
            System.out.println("Rows affected> " + rowsAffected);
            System.out.println("Movimiento registrado");

        } catch (SQLException e) {
            System.out.println("Error al agregar movimiento: ");
        }
    }
}
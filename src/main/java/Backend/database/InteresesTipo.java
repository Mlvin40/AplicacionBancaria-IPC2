package Backend.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class InteresesTipo {


    private static Connection connection;

    /**
     * Método para establecer el interés de una tarjeta
     * El interés se establece basado en el tipo de tarjeta
     *
     * @param numeroTarjeta
     * @return
     */
    public static double establecerInteres(String numeroTarjeta)  {
        {
            try{
                 connection = ConexionMySQL.conectar();
            }
            catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }

            String tipoTarjeta = null;
            double interes = 0.0;

            // Consulta para obtener el tipo de tarjeta
            String consulta = "SELECT tipo FROM tarjetas WHERE numero_tarjeta = ?";

            try {
                // Preparar la consulta
                PreparedStatement statementConsulta = connection.prepareStatement(consulta);
                statementConsulta.setString(1, numeroTarjeta);
                ResultSet resultSet = statementConsulta.executeQuery();

                // Verificar si se encontró la tarjeta
                if (resultSet.next()) {
                    tipoTarjeta = resultSet.getString("tipo");

                    // Calcular el interés basado en el tipo de tarjeta
                    switch (tipoTarjeta) {
                        case "NACIONAL":
                            interes = 0.012; // 1.2%
                            break;
                        case "REGIONAL":
                            interes = 0.023; // 2.3%
                            break;
                        case "INTERNACIONAL":
                            interes = 0.0375; // 3.75%
                            break;
                        default:
                            System.out.println("Tipo de tarjeta desconocido");
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tarjeta no encontrada");
                    System.out.println("Tarjeta no encontrada");
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el tipo de tarjeta: ");
            }
            return interes;
        }
    }

}


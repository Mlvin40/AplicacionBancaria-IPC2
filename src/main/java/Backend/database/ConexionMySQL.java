/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author melvin
 */
public class ConexionMySQL {

    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/banco";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    private static Connection connection;

//    public ConexionMySQL() {
//        try {
//            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
//            System.out.println("Conexión exitosa");
//            System.out.println("Esquema: " + connection.getSchema());
//        } catch (SQLException ex) {
//            System.out.println("Error al conectar con la base de datos");
//            ex.printStackTrace();
//        }
//    }

    public static Connection conectar()  throws SQLException {
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos exitosa");
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos", ex);
        }
    }
}

package com.Sistemagestionresiduos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD{

    private static final String URL = "jdbc:mysql://localhost:3306/ecovidadb";
    private static final String USUARIO = "root";
    private static final String CLAVE = "Marly2026";

    public static Connection obtenerConexion() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

            System.out.print("Conexion exitosa a la base de datos");
            return conexion;

        } catch (ClassNotFoundException e) {

            System.out.print("Driver de MySQL no encontrado");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.print("Erros al conectar con la base de datos : " + e.getMessage());
            e.printStackTrace();

        }

        throw new RuntimeException("No se pudo establecer la conexión con la base de  datos.");
    }

}

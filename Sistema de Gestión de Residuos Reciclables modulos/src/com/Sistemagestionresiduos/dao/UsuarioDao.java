package com.Sistemagestionresiduos.dao;

import com.Sistemagestionresiduos.config.ConexionBD;
import com.Sistemagestionresiduos.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public void insertarUsuario(Usuario usuario) {

        String sql = "INSERT INTO usuario (Nombre, Apellido, Correo, Telefono, Contraseña) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCorreoElectronico());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getPassword());

            statement.executeUpdate();
            System.out.println("Usuario registrado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setApellido(resultSet.getString("Apellido"));
                usuario.setCorreoElectronico(resultSet.getString("Correo"));
                usuario.setTelefono(resultSet.getString("Telefono"));
                usuario.setPassword(resultSet.getString("Contraseña"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET Nombre = ?, Apellido = ?, Correo = ?, Telefono = ?, Contraseña = ? WHERE id_usuario = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCorreoElectronico());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getPassword());
            // El ID es el parámetro 6, no el 8
            statement.setInt(6, usuario.getIdUsuario());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se encontró el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
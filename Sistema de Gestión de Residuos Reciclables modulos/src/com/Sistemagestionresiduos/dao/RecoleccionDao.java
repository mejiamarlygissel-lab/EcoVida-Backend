package com.Sistemagestionresiduos.dao;

import com.Sistemagestionresiduos.config.ConexionBD;
import com.Sistemagestionresiduos.model.Recoleccion;
import com.Sistemagestionresiduos.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecoleccionDao {

    private Recoleccion mapResultSetToRecoleccion(ResultSet rs) throws SQLException {

        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));

        return new Recoleccion(
                rs.getInt("idRecoleccion"),
                rs.getString("fecha"),
                rs.getString("hora"),
                rs.getString("ubicacion"),
                rs.getString("tipo_residuo"),
                rs.getDouble("cantidad_kg"),
                rs.getString("observaciones"),
                u);
    }

    public void insertarRecoleccion(Recoleccion r) {
   
    String sql = "INSERT INTO recoleccion (fecha, hora, ubicacion, tipo_residuo, cantidad_kg, observaciones, id_usuario) VALUES (?, CURTIME(), ?, ?, ?, ?, ?)";

    
    ejecutarActualizacion(sql, r.getFecha(), r.getUbicacion(), r.getTipoResiduo(),
            r.getCantidadKg(), r.getObservations(), r.getUsuario().getIdUsuario());
}
    public List<Recoleccion> listarRecolecciones() {
        List<Recoleccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM recoleccion";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSetToRecoleccion(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar recolecciones: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarRecoleccion(Recoleccion r) {

        String sql = "UPDATE recoleccion SET fecha=?, hora=?, ubicacion=?, tipo_residuo=?, cantidad_kg=?, observaciones=?, id_usuario=? WHERE idRecoleccion=?";

        ejecutarActualizacion(sql, r.getFecha(), r.getHora(), r.getUbicacion(), r.getTipoResiduo(),
                r.getCantidadKg(), r.getObservations(), r.getUsuario().getIdUsuario(), r.getIdRecoleccion());
    }

    public void eliminarRecoleccion(int id) {
        String sql = "DELETE FROM recoleccion WHERE idRecoleccion = ?";
        ejecutarActualizacion(sql, id);
    }

    private void ejecutarActualizacion(String sql, Object... params) {
        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Operación exitosa en Eco Vida." : "No se encontró el registro.");

        } catch (SQLException e) {
            System.err.println("Error en base de datos: " + e.getMessage());
        }
    }
}
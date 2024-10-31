package com.mycompany.workflow.Incidencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.mycompany.workflow.BaseDeDatos.Conexion;

public class IncidenciasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrarIncidencias (Incidencias inc) {
        String SQL = "INSERT INTO incidencias (titulo,descripcion,status_i) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, inc.getTitulo());
            ps.setString(2, inc.getDescripcion());
            ps.setBoolean(3, false);
            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public void ConsultarIncidencias(JComboBox<String> incidencia) {
        String sql = "SELECT titulo FROM incidencias";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            // Obtener la conexión a la base de datos
            con = cn.getConnection();
            // Preparar la consulta
            ps = con.prepareStatement(sql);
            // Ejecutar la consulta
            rs = ps.executeQuery();
            
            // Limpiar el JComboBox antes de llenarlo
            incidencia.removeAllItems();
            
            // Iterar sobre los resultados y agregarlos al JComboBox
            while (rs.next()) {
                incidencia.addItem(rs.getString("titulo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar ResultSet, PreparedStatement y conexión
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    public void ConsultarDescripcionIncidencias (JComboBox<String> titulo, JTextArea textArea) {
        String sql = "SELECT descripcion FROM incidencias WHERE titulo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            String tituloString = (String) titulo.getSelectedItem();
            ps.setString(1, tituloString);
            rs = ps.executeQuery();
            while (rs.next()) {
                textArea.setText(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void ConsultarUsuarios (JComboBox<String> usuario) {
        String sql = "SELECT name_c FROM users";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            usuario.removeAllItems();
            while(rs.next()) {
                usuario.addItem(rs.getString("name_c"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void asignarIncidencia(JComboBox cbxIncidencia, JComboBox cbxUsuario, JTextArea comentarios, Incidencias incidencia) {
        String seleccionIncidencia = (String) cbxIncidencia.getSelectedItem();
        String seleccionUsuario = (String) cbxUsuario.getSelectedItem();
        String comentario = comentarios.getText();
        String sqlIncidencia = "INSERT INTO comentarios (id_incidencia, id_user, comentario) VALUES (?, ?, ?)";
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Conexion cn = new Conexion();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sqlIncidencia);
            ps.setInt(1, obtenerIdIncidencia(seleccionIncidencia));
            ps.setInt(2, obtenerIdUsuario(seleccionUsuario));
            ps.setString(3, comentario);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Incidencia Asignada Correctamente");
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
    
    public void actualizarIncidencia(JComboBox cbxIncidencia, JComboBox cbxUsuario) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Conexion cn = new Conexion();
        String sql = "UPDATE incidencias SET asignado_a = (SELECT id_user FROM users WHERE name_c = ?), fecha_atendido = NOW(), status_i = 1 WHERE id_incidencia = ?";
                
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, (String) cbxUsuario.getSelectedItem());
            ps.setInt(2, obtenerIdIncidencia((String) cbxIncidencia.getSelectedItem()));
            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public int obtenerIdIncidencia (String titulo) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Conexion cn = new Conexion();

        String SQL = "SELECT * FROM incidencias WHERE titulo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, titulo);
            rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("id_incidencia");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        }
    }

    public int obtenerIdUsuario (String nombre)  {
        String SQL = "SELECT id_user FROM users WHERE name_c = ?";
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Conexion cn = new Conexion();
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_user");
            } else {
                return -1;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        }

    }

    public static void main(String[] args) {
        IncidenciasDAO inc = new IncidenciasDAO();
        System.out.println(inc.obtenerIdUsuario("asd"));
    }
}

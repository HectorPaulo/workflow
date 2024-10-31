package com.mycompany.workflow.Encriptacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.workflow.BaseDeDatos.Conexion;
import com.mycompany.workflow.EstructuraUsuarios.Usuario;

public class VerificacionLogueo {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Usuario user (String correo, String pass) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM users WHERE mail = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,correo);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId_user(rs.getInt("id_user"));
                usuario.setName_c(rs.getString("name_c"));
                usuario.setTipo_user(rs.getInt("tipo_user"));
                usuario.setMail(rs.getString("mail"));
                usuario.setPasswordS(rs.getString("passwordS"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }
        return usuario;
    }


}

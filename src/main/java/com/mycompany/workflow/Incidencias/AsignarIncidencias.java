package com.mycompany.workflow.Incidencias;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class AsignarIncidencias extends JFrame {

    private JComboBox<String> cbIncidencias;
    private JTextArea taDescripcion;
    private JTextArea taComentarios;
    private JComboBox<String> cbAsignarA;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection connection;

    public AsignarIncidencias() {
        initComponents();
        connectToDatabase();
        cargarIncidencias();
        cargarUsuarios();
    }

    private void initComponents() {
        setTitle("Asignar Incidencias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel lblTitulo = new JLabel("TÍTULO");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(lblTitulo, gbc);
        
        cbIncidencias = new JComboBox<>();
        cbIncidencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDescripcion();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(cbIncidencias, gbc);

        JLabel lblDescripcion = new JLabel("DESCRIPCIÓN");
        lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(lblDescripcion, gbc);
        
        taDescripcion = new JTextArea(5, 20);
        taDescripcion.setLineWrap(true);
        taDescripcion.setWrapStyleWord(true);
        taDescripcion.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(taDescripcion), gbc);

        JLabel lblComentarios = new JLabel("COMENTARIOS");
        lblComentarios.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(lblComentarios, gbc);
        
        taComentarios = new JTextArea(5, 20);
        taComentarios.setLineWrap(true);
        taComentarios.setWrapStyleWord(true);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(taComentarios), gbc);

        JLabel lblAsignarA = new JLabel("ASIGNAR A:");
        lblAsignarA.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(lblAsignarA, gbc);
        
        cbAsignarA = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(cbAsignarA, gbc);

        btnAceptar = new JButton("ACEPTAR");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarIncidencia();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(btnAceptar, gbc);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(btnCancelar, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private void connectToDatabase() {
        try {
            // Cambia esto según tu configuración de base de datos
            String url = "jdbc:mysql://localhost:3306/workflowdb";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarIncidencias() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_incidencia, titulo FROM incidencias");
            cbIncidencias.removeAllItems();
            while (rs.next()) {
                int id = rs.getInt("id_incidencia");
                String titulo = rs.getString("titulo");
                cbIncidencias.addItem(id + " - " + titulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar incidencias", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDescripcion() {
        String seleccion = (String) cbIncidencias.getSelectedItem();
        if (seleccion != null) {
            String[] partes = seleccion.split(" - ");
            int id = Integer.parseInt(partes[0]);
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT descripcion FROM incidencias WHERE id_incidencia = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String descripcion = rs.getString("descripcion");
                    taDescripcion.setText(descripcion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar la descripción", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarUsuarios() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name_c FROM users WHERE tipo_user = 1");
            cbAsignarA.removeAllItems();
            while (rs.next()) {
                String nombre = rs.getString("name_c");
                cbAsignarA.addItem(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void asignarIncidencia() {
        String seleccionIncidencia = (String) cbIncidencias.getSelectedItem();
        String seleccionUsuario = (String) cbAsignarA.getSelectedItem();
        String comentario = taComentarios.getText();

        if (seleccionIncidencia != null && seleccionUsuario != null && !comentario.isEmpty()) {
            String[] partes = seleccionIncidencia.split(" - ");
            int idIncidencia = Integer.parseInt(partes[0]);
            try {
                connection.setAutoCommit(false);

                // Insertar comentario
                PreparedStatement psComentario = connection.prepareStatement(
                    "INSERT INTO comentarios (id_incidencia, id_user, comentario) VALUES (?, ?, ?)");
                psComentario.setInt(1, idIncidencia);
                psComentario.setInt(2, obtenerIdUsuario(seleccionUsuario));
                psComentario.setString(3, comentario);
                psComentario.executeUpdate();

                // Actualizar asignado_a
                PreparedStatement psAsignacion = connection.prepareStatement(
                    "UPDATE incidencias SET asignado_a = (SELECT id_user FROM users WHERE name_c = ?) WHERE id_incidencia = ?");
                psAsignacion.setString(1, seleccionUsuario);
                psAsignacion.setInt(2, idIncidencia);
                psAsignacion.executeUpdate();

                connection.commit();
                JOptionPane.showMessageDialog(this, "Incidencia asignada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al asignar incidencia", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private int obtenerIdUsuario(String nombre) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT id_user FROM users WHERE name_c = ?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_user");
        }
        throw new SQLException("Usuario no encontrado");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AsignarIncidencias().setVisible(true);
            }
        });
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.workflow.InterfacesGraficas;

/**
 *
 * @author danielpaput
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class DinamicRowsExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dynamic Rows Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
           
        for (int i = 0; i < 10; i++) {
            String fecha = "fecha";
                String nombre = "nombre";
                String mensaje = "mensaje";

                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));

                JLabel fechaLabel = new JLabel(fecha);
                JLabel nombreLabel = new JLabel(nombre);
                JTextArea mensajeArea = new JTextArea(mensaje);
                mensajeArea.setLineWrap(true);
                mensajeArea.setWrapStyleWord(true);
                mensajeArea.setEditable(false);

                rowPanel.add(fechaLabel);
                rowPanel.add(nombreLabel);
                rowPanel.add(mensajeArea);

                panel.add(rowPanel);
                panel.add(Box.createVerticalStrut(10)); // Adds space between rows
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}


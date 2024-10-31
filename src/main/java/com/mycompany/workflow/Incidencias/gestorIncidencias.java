package com.mycompany.workflow.Incidencias;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import com.mycompany.workflow.EstructuraUsuarios.Usuario;

public class gestorIncidencias extends Incidecias {
    
    private String comentario;

    public gestorIncidencias(Long id, Usuario asignado, String titulo, String descripcion, Boolean status,
            LocalDateTime fechaInicio, LocalDateTime fechaAtencion, LocalDateTime fechaTermino, String comentario) {
                super(id, asignado, titulo, descripcion, status, fechaInicio, fechaAtencion, fechaTermino);
                this.comentario = comentario;
            }

    private void registrarIncidencia(){
        String titulo=tituloField.getText().trim();
        String descripcion = descripcionField.getText().trim();


        if (titulo.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, asegurese de completar todos los campos. ", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si los campos están vacíos
        }

        Incidencias incidencias= new Incidencias(titulo, descripcion);
    }

}
 
package clinica.ui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import clinica.dominio.EnumPanel;

/*
==========================================================
                PANEL PRINCIPAL
==========================================================

Es la pantalla de inicio del sistema.

Desde aquí el usuario puede elegir a qué módulo
quiere ingresar.

No registra datos.
No modifica archivos.
Solo cambia entre paneles.

==========================================================
*/

public class PanelPrincipal extends JPanel {

    private PanelManager manager;

    public PanelPrincipal(PanelManager manager) {

        this.manager = manager;

        // Componentes acomodados verticalmente
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton botonRegistrarPaciente =
                crearBoton("Registrar Paciente");

        JButton botonMostrarPacientes =
                crearBoton("Mostrar Pacientes");

        JButton botonRegistrarMedico =
                crearBoton("Registrar Médico");

        JButton botonMostrarMedicos =
                crearBoton("Mostrar Médicos");

        JButton botonRegistrarTurno =
                crearBoton("Registrar Turno");

        JButton botonMostrarTurnos =
                crearBoton("Mostrar Turnos");

        add(Box.createVerticalGlue());

        add(botonRegistrarPaciente);
        add(Box.createVerticalStrut(15));

        add(botonMostrarPacientes);
        add(Box.createVerticalStrut(15));

        add(botonRegistrarMedico);
        add(Box.createVerticalStrut(15));

        add(botonMostrarMedicos);
        add(Box.createVerticalStrut(15));

        add(botonRegistrarTurno);
        add(Box.createVerticalStrut(15));

        add(botonMostrarTurnos);

        add(Box.createVerticalGlue());

        botonRegistrarPaciente.addActionListener(e ->
            this.manager.mostrarPanel(EnumPanel.FORMULARIO_PACIENTE));
    
        botonMostrarPacientes.addActionListener(e ->
            this.manager.mostrarPanel(EnumPanel.TABLA_PACIENTE));
    
        botonRegistrarMedico.addActionListener(e ->
            this.manager.mostrarPanel(EnumPanel.FORMULARIO_MEDICO));
    
        botonMostrarMedicos.addActionListener(e ->
            this.manager.mostrarPanel(EnumPanel.TABLA_MEDICO));
    
        botonRegistrarTurno.addActionListener(e ->
            this.manager.mostrarPanel(EnumPanel.FORMULARIO_TURNO));
    
        botonMostrarTurnos.addActionListener(e ->
            this.manager.mostrarPanel(EnumPanel.TABLA_TURNO));
    }

    /*
    ========================================
            CREA TODOS LOS BOTONES
    ========================================
    */

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setAlignmentX(CENTER_ALIGNMENT);

        boton.setMaximumSize(
                new java.awt.Dimension(250, 40));

        return boton;
    }
}
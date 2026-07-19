package clinica.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import clinica.dominio.EnumPanel;

/*
==========================================================
                VENTANA PRINCIPAL
==========================================================

Esta clase crea la ventana principal del sistema.

Se encarga de:

- Crear el JFrame.
- Crear el menú.
- Agregar el PanelManager.
- Cambiar entre paneles mediante EnumPanel.

==========================================================
*/

public class Ventana extends JFrame {

    private PanelManager panelManager;

    public Ventana() {

        super("Sistema Clínica");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);

        panelManager = new PanelManager();

        getContentPane().add(panelManager, BorderLayout.CENTER);

        // ==========================
        // BARRA DE MENÚ
        // ==========================

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Opciones");

        JMenuItem itemInicio =
                new JMenuItem("Inicio");

        JMenuItem itemRegistrarPaciente =
                new JMenuItem("Registrar Paciente");

        JMenuItem itemMostrarPacientes =
                new JMenuItem("Mostrar Pacientes");

        JMenuItem itemRegistrarMedico =
                new JMenuItem("Registrar Médico");

        JMenuItem itemMostrarMedicos =
                new JMenuItem("Mostrar Médicos");

        JMenuItem itemRegistrarTurno =
                new JMenuItem("Registrar Turno");

        JMenuItem itemMostrarTurnos =
                new JMenuItem("Mostrar Turnos");

        // ==========================
        // ACCIONES
        // ==========================

        itemInicio.addActionListener(e ->
                panelManager.mostrarPanel(EnumPanel.PRINCIPAL));

        itemRegistrarPaciente.addActionListener(e ->
                panelManager.mostrarPanel(EnumPanel.FORMULARIO_PACIENTE));

        itemMostrarPacientes.addActionListener(e ->
                panelManager.mostrarPanel(EnumPanel.TABLA_PACIENTE));

        itemRegistrarMedico.addActionListener(e ->
                panelManager.mostrarPanel(EnumPanel.FORMULARIO_MEDICO));

        itemMostrarMedicos.addActionListener(e ->
                panelManager.mostrarPanel(EnumPanel.TABLA_MEDICO));

        itemRegistrarTurno.addActionListener(e ->
                panelManager.mostrarPanel(EnumPanel.FORMULARIO_TURNO));

        itemMostrarTurnos.addActionListener(e ->
                panelManager.mostrarPanel(EnumPanel.TABLA_TURNO));

        // ==========================
        // AGREGAR OPCIONES AL MENÚ
        // ==========================

        menu.add(itemInicio);

        menu.addSeparator();

        menu.add(itemRegistrarPaciente);
        menu.add(itemMostrarPacientes);

        menu.addSeparator();

        menu.add(itemRegistrarMedico);
        menu.add(itemMostrarMedicos);

        menu.addSeparator();

        menu.add(itemRegistrarTurno);
        menu.add(itemMostrarTurnos);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args) {

        new Ventana();

    }

}
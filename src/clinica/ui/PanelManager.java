package clinica.ui;

import java.awt.CardLayout;

import javax.swing.JPanel;

import clinica.dominio.Medico;
import clinica.dominio.Paciente;
import clinica.dominio.Turno;
import clinica.repositorio.RepositorioArchivo;
import clinica.servicio.Servicio;
import clinica.dominio.EnumPanel;
/*
==========================================================
                    PANEL MANAGER
==========================================================

Es el administrador de toda la interfaz.

Se encarga de:

- Crear los servicios.
- Crear los modelos.
- Crear los paneles.
- Compartir los servicios.
- Cambiar entre formulario y tabla usando CardLayout.

==========================================================
*/

public class PanelManager extends JPanel {

    // Permite cambiar entre paneles
    private CardLayout cardLayout;
    private PanelPrincipal panelPrincipal;
    // ==========================
    // PACIENTES
    // ==========================

    private PanelPacientes panelPacientes;
    private PanelPacienteTabla panelPacienteTabla;
    private TablePacienteModel modeloPaciente;
    private Servicio<Paciente> servicio;

    // ==========================
    // MEDICOS
    // ==========================

    private PanelMedicos panelMedicos;
    private PanelMedicoTabla panelMedicoTabla;
    private TableMedicoModel modeloMedico;
    private Servicio<Medico> servicioMedicos;

    // ==========================
    // TURNOS
    // ==========================

    private PanelTurnos panelTurnos;
    private PanelTurnoTabla panelTurnoTabla;
    private TableTurnoModel modeloTurno;
    private Servicio<Turno> servicioTurnos;

    public PanelManager() {

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        panelPrincipal = new PanelPrincipal(this);

        // ===================================
        // PACIENTES
        // ===================================

        servicio = new Servicio<>(
                new RepositorioArchivo<>("pacientes.dat"));

        modeloPaciente =
                new TablePacienteModel(servicio.listar());

        panelPacientes =
                new PanelPacientes(modeloPaciente, this);

        panelPacienteTabla =
                new PanelPacienteTabla(modeloPaciente, this);

        // ===================================
        // MEDICOS
        // ===================================

        servicioMedicos = new Servicio<>(
                new RepositorioArchivo<>("medicos.dat"));

        modeloMedico =
                new TableMedicoModel(servicioMedicos.listar());

        panelMedicos =
                new PanelMedicos(modeloMedico, this);

        panelMedicoTabla =
                new PanelMedicoTabla(modeloMedico, this);

        // ===================================
        // TURNOS
        // ===================================

        servicioTurnos = new Servicio<>(
                new RepositorioArchivo<>("turnos.dat"));

        modeloTurno =
                new TableTurnoModel(servicioTurnos.listar());

        panelTurnos =
                new PanelTurnos(modeloTurno, this);

        panelTurnoTabla =
                new PanelTurnoTabla(modeloTurno, this);

        // ===================================
        // AGREGAMOS TODOS LOS PANELES
        // ===================================

        add(panelPrincipal, EnumPanel.PRINCIPAL.name());

        add(panelPacientes, EnumPanel.FORMULARIO_PACIENTE.name());
        add(panelPacienteTabla, EnumPanel.TABLA_PACIENTE.name());

        add(panelMedicos, EnumPanel.FORMULARIO_MEDICO.name());
        add(panelMedicoTabla, EnumPanel.TABLA_MEDICO.name());

        add(panelTurnos, EnumPanel.FORMULARIO_TURNO.name());
        add(panelTurnoTabla, EnumPanel.TABLA_TURNO.name());

    }

    public void mostrarPanel(EnumPanel panel) {

        cardLayout.show(this, panel.name());
    
    }
        // ==========================
    // PACIENTES
    // ==========================

    public void mostrarFormulario() {
        mostrarPanel(EnumPanel.FORMULARIO_PACIENTE);
    }

    public void mostrarTabla() {
        mostrarPanel(EnumPanel.TABLA_PACIENTE);
    }

    public Servicio<Paciente> getServicio() {
        return servicio;
    }

    public PanelPacientes getPanelPacientes() {
        return panelPacientes;
    }

    // ==========================
    // MEDICOS
    // ==========================

    public void mostrarFormularioMedico() {
        mostrarPanel(EnumPanel.FORMULARIO_MEDICO);
    }

    public void mostrarTablaMedicos() {
        mostrarPanel(EnumPanel.TABLA_MEDICO);
    }

    public Servicio<Medico> getServicioMedicos() {
        return servicioMedicos;
    }

    public PanelMedicos getPanelMedicos() {
        return panelMedicos;
    }

    // ==========================
    // TURNOS
    // ==========================

    public void mostrarFormularioTurno() {
        mostrarPanel(EnumPanel.FORMULARIO_TURNO);
    }

    public void mostrarTablaTurnos() {
        mostrarPanel(EnumPanel.TABLA_TURNO);
    }

    public Servicio<Turno> getServicioTurnos() {
        return servicioTurnos;
    }

    public PanelTurnos getPanelTurnos() {
        return panelTurnos;
    }

}
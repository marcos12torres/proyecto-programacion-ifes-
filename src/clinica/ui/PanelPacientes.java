package clinica.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinica.dominio.Paciente;

public class PanelPacientes extends JPanel implements ActionListener {

    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoDni;
    private JTextField campoObraSocial;

    private JButton botonGuardar;
    private JButton botonSalir;

    private TablePacienteModel modelo;
    private PanelManager manager;

    private int filaEditando = -1;

    public PanelPacientes(TablePacienteModel modelo,
                          PanelManager manager) {

        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.modelo = modelo;
        this.manager = manager;

        add(new JLabel("Nombre"));
        campoNombre = new JTextField();
        add(campoNombre);

        add(new JLabel("Apellido"));
        campoApellido = new JTextField();
        add(campoApellido);

        add(new JLabel("DNI"));
        campoDni = new JTextField();
        add(campoDni);

        add(new JLabel("Obra Social"));
        campoObraSocial = new JTextField();
        add(campoObraSocial);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(this);
        add(botonGuardar);

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        add(botonSalir);
    }

    public void cargarPaciente(Paciente paciente, int fila) {

        campoNombre.setText(paciente.getNombre());
        campoApellido.setText(paciente.getApellido());
        campoDni.setText(String.valueOf(paciente.getDni()));
        campoObraSocial.setText(paciente.getObraSocial());

        filaEditando = fila;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botonGuardar) {

            if (campoNombre.getText().trim().isEmpty()
                    || campoApellido.getText().trim().isEmpty()
                    || campoDni.getText().trim().isEmpty()
                    || campoObraSocial.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "Debe completar todos los campos");

                return;
            }

            try {

                Paciente paciente = new Paciente(
                        campoNombre.getText().trim(),
                        campoApellido.getText().trim(),
                        Integer.parseInt(campoDni.getText().trim()),
                        campoObraSocial.getText().trim());

                if (filaEditando == -1) {

                    manager.getServicio().agregar(paciente);
                    modelo.fireTableDataChanged();

                } else {

                    modelo.actualizarPaciente(filaEditando, paciente);

                    manager.getServicio().listar().set(filaEditando, paciente);

                    manager.getServicio()
                            .getRepositorio()
                            .guardar(manager.getServicio().listar());

                    filaEditando = -1;
                }

                JOptionPane.showMessageDialog(
                        null,
                        "Paciente guardado correctamente");

                limpiarCampos();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "El DNI debe ser numérico");
            }

        } else if (e.getSource() == botonSalir) {

            System.exit(0);
        }

    }

    private void limpiarCampos() {

        campoNombre.setText("");
        campoApellido.setText("");
        campoDni.setText("");
        campoObraSocial.setText("");
    }

}
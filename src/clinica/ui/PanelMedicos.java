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

import clinica.dominio.Medico;

public class PanelMedicos extends JPanel implements ActionListener {

    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoDni;
    private JTextField campoMatricula;
    private JTextField campoEspecialidad;

    private JButton botonGuardar;
    private JButton botonSalir;

    private TableMedicoModel modelo;
    private PanelManager manager;

    private int filaEditando = -1;

    public PanelMedicos(TableMedicoModel modelo, PanelManager manager) {

        this.modelo = modelo;
        this.manager = manager;

        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Nombre"));
        campoNombre = new JTextField();
        add(campoNombre);

        add(new JLabel("Apellido"));
        campoApellido = new JTextField();
        add(campoApellido);

        add(new JLabel("DNI"));
        campoDni = new JTextField();
        add(campoDni);

        add(new JLabel("Matricula"));
        campoMatricula = new JTextField();
        add(campoMatricula);

        add(new JLabel("Especialidad"));
        campoEspecialidad = new JTextField();
        add(campoEspecialidad);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(this);
        add(botonGuardar);

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        add(botonSalir);
    }

    public void cargarMedico(Medico medico, int fila) {

        campoNombre.setText(medico.getNombre());
        campoApellido.setText(medico.getApellido());
        campoDni.setText(String.valueOf(medico.getDni()));
        campoMatricula.setText(String.valueOf(medico.getMatricula()));
        campoEspecialidad.setText(medico.getEspecialidad());

        filaEditando = fila;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botonGuardar) {

            if (campoNombre.getText().trim().isEmpty()
                    || campoApellido.getText().trim().isEmpty()
                    || campoDni.getText().trim().isEmpty()
                    || campoMatricula.getText().trim().isEmpty()
                    || campoEspecialidad.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "Debe completar todos los campos");
                return;
            }

            try {

                Medico medico = new Medico(
                        campoNombre.getText().trim(),
                        campoApellido.getText().trim(),
                        Integer.parseInt(campoDni.getText().trim()),
                        Integer.parseInt(campoMatricula.getText().trim()),
                        campoEspecialidad.getText().trim());

                if (filaEditando == -1) {

                    manager.getServicioMedicos().agregar(medico);
                    modelo.fireTableDataChanged();

                } else {

                    modelo.actualizarMedico(filaEditando, medico);

                    manager.getServicioMedicos().listar().set(filaEditando, medico);

                    manager.getServicioMedicos()
                            .getRepositorio()
                            .guardar(manager.getServicioMedicos().listar());

                    filaEditando = -1;
                }

                JOptionPane.showMessageDialog(null,
                        "Medico guardado correctamente");

                limpiarCampos();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(null,
                        "DNI y Matricula deben ser numericos");
            }

        } else if (e.getSource() == botonSalir) {

            System.exit(0);
        }
    }

    private void limpiarCampos() {

        campoNombre.setText("");
        campoApellido.setText("");
        campoDni.setText("");
        campoMatricula.setText("");
        campoEspecialidad.setText("");
    }
}
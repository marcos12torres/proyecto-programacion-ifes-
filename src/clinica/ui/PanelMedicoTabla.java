package clinica.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import clinica.dominio.EnumPanel;
import clinica.dominio.Medico;

public class PanelMedicoTabla extends JPanel {

    public PanelMedicoTabla(TableMedicoModel modelo,
                            PanelManager manager) {

        setLayout(new BorderLayout());

        JTable tabla = new JTable(modelo);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton botonEditar = new JButton("Editar");
        JButton botonEliminar = new JButton("Eliminar");

        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);

        botonEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                manager.getServicioMedicos().listar().remove(fila);

                manager.getServicioMedicos()
                        .getRepositorio()
                        .guardar(manager.getServicioMedicos().listar());

                modelo.fireTableDataChanged();

                JOptionPane.showMessageDialog(null,
                        "Medico eliminado correctamente.");
            }
        });

        botonEditar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                Medico medico = modelo.getMedicos().get(fila);

                manager.mostrarPanel(EnumPanel.FORMULARIO_MEDICO);

                manager.getPanelMedicos()
                       .cargarMedico(medico, fila);
            }
        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
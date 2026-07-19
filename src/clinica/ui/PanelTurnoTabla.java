package clinica.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import clinica.dominio.EnumPanel;

import clinica.dominio.Turno;

/*
==========================================================
                TABLA DE TURNOS
==========================================================

Esta clase muestra todos los turnos registrados
en una JTable.

Permite:
- Editar un turno.
- Eliminar un turno.

No crea turnos.
No guarda archivos.
Solo trabaja con la tabla.

==========================================================
*/

public class PanelTurnoTabla extends JPanel {

    public PanelTurnoTabla(TableTurnoModel modelo,
                           PanelManager manager) {

        setLayout(new BorderLayout());

        JTable tabla = new JTable(modelo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton botonEditar = new JButton("Editar");
        JButton botonEliminar = new JButton("Eliminar");

        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);

        botonEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                manager.getServicioTurnos().listar().remove(fila);

                manager.getServicioTurnos()
                        .getRepositorio()
                        .guardar(manager.getServicioTurnos().listar());

                modelo.fireTableDataChanged();

                JOptionPane.showMessageDialog(
                        null,
                        "Turno eliminado correctamente.");
            }

        });

        botonEditar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                Turno turno = modelo.getTurnos().get(fila);

                manager.mostrarPanel(EnumPanel.FORMULARIO_TURNO);

                manager.getPanelTurnos()
                    .cargarTurno(turno, fila);

            }

        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

}
package clinica.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clinica.dominio.Paciente;

public class TablePacienteModel extends AbstractTableModel {

    private String[] columnas = {
            "Nombre",
            "Apellido",
            "DNI",
            "Obra Social"
    };

    private List<Paciente> pacientes;

    public TablePacienteModel(List<Paciente> pacientes) {

        this.pacientes = pacientes;
    }

    @Override
    public int getRowCount() {

        return pacientes.size();
    }

    @Override
    public int getColumnCount() {

        return columnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Paciente p = pacientes.get(fila);

        switch (columna) {

            case 0:
                return p.getNombre();

            case 1:
                return p.getApellido();

            case 2:
                return p.getDni();

            case 3:
                return p.getObraSocial();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columna) {

        return columnas[columna];
    }

    public void agregarPaciente(Paciente paciente) {

        pacientes.add(paciente);

        fireTableRowsInserted(
                pacientes.size() - 1,
                pacientes.size() - 1);
    }

    public void actualizarPaciente(int fila,
                                   Paciente paciente) {

        pacientes.set(fila, paciente);

        fireTableRowsUpdated(fila, fila);
    }

    public void borrarPaciente(int fila) {

        pacientes.remove(fila);

        fireTableRowsDeleted(fila, fila);
    }

    public List<Paciente> getPacientes() {

        return pacientes;
    }

}
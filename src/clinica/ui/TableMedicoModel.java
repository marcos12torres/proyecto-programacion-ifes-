package clinica.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clinica.dominio.Medico;

public class TableMedicoModel extends AbstractTableModel {

    private String[] columnas = {
            "Nombre",
            "Apellido",
            "DNI",
            "Matricula",
            "Especialidad"
    };

    private List<Medico> medicos;

    public TableMedicoModel(List<Medico> medicos) {
        this.medicos = medicos;
    }

    @Override
    public int getRowCount() {
        return medicos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Medico medico = medicos.get(fila);

        switch (columna) {

            case 0:
                return medico.getNombre();

            case 1:
                return medico.getApellido();

            case 2:
                return medico.getDni();

            case 3:
                return medico.getMatricula();

            case 4:
                return medico.getEspecialidad();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    public void agregarMedico(Medico medico) {

        medicos.add(medico);

        fireTableRowsInserted(
                medicos.size() - 1,
                medicos.size() - 1);
    }

    public void actualizarMedico(int fila, Medico medico) {

        medicos.set(fila, medico);

        fireTableRowsUpdated(fila, fila);
    }

    public void borrarMedico(int fila) {

        medicos.remove(fila);

        fireTableRowsDeleted(fila, fila);
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

}
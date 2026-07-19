package clinica.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clinica.dominio.Turno;

import clinica.dominio.Cirugia;
import clinica.dominio.Consulta;
import clinica.dominio.Estudio;

public class TableTurnoModel extends AbstractTableModel {

    private String[] columnas = {
        "ID",
        "Fecha",
        "Hora",
        "Estado",
        "Tipo",
        "Detalle"
    };

    private List<Turno> turnos;

    public TableTurnoModel(List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public int getRowCount() {
        return turnos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {

    Turno turno = turnos.get(fila);

    switch (columna) {

        case 0:
            return turno.getId();

        case 1:
            return turno.getFecha();

        case 2:
            return turno.getHora();

        case 3:
            return turno.getEstado();

        case 4:

            if (turno instanceof Consulta)
                return "Consulta";

            if (turno instanceof Estudio)
                return "Estudio";

            if (turno instanceof Cirugia)
                return "Cirugia";

            return "";

        case 5:

          if (turno instanceof Consulta) {

          Consulta c = (Consulta) turno;

            return "Síntomas: " + c.getSintomas()
                + " | Diagnóstico: " + c.getDiagnostico();

        }

        if (turno instanceof Estudio) {

        Estudio e = (Estudio) turno;

        return "Tipo: " + e.getTipoEstudio()
                + " | Laboratorio: " + e.getLaboratorio();

       }

       if (turno instanceof Cirugia) {

        Cirugia c = (Cirugia) turno;

        return "Tipo: " + c.getTipoCirugia()
                + " | Quirófano: " + c.getQuirofano();

        }

        return "";

        default:
            return null;
    }
   }


    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    public void agregarTurno(Turno turno) {

        turnos.add(turno);

        fireTableRowsInserted(
                turnos.size()-1,
                turnos.size()-1);
    }

    public void actualizarTurno(int fila,
                                Turno turno){

        turnos.set(fila,turno);

        fireTableRowsUpdated(fila,fila);

    }

    public List<Turno> getTurnos(){

        return turnos;

    }

}
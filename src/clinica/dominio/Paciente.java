package clinica.dominio;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {

    private String obraSocial;
    private List<Turno> historialTurnos;

    public Paciente(String nombre, String apellido, int dni, String obraSocial) {
        super(nombre, apellido, dni);

        this.obraSocial = obraSocial;
        this.historialTurnos = new ArrayList<>();
    }

    public void solicitarTurno(Turno turno) {
        historialTurnos.add(turno);
    }

    public void cancelarTurno(Turno turno) {
        historialTurnos.remove(turno);
    }

    public void mostrarHistorial() {

        System.out.println("Historial de turnos:");

        for (Turno turno : historialTurnos) {
            turno.mostrarInfo();
        }
    }

    @Override
    public void mostrarInfo() {

        System.out.println("--------------------------------------------");

        System.out.println("Paciente " + nombre +
                           " Apellido " + apellido +
                           " DNI " + dni);

        System.out.println("Obra Social: " + obraSocial);

        mostrarHistorial();

        System.out.println("--------------------------------------------");
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public List<Turno> getHistorialTurnos() {
        return historialTurnos;
    }
}
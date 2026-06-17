package clinica.dominio;

import clinica.excepcion.TurnoNoDisponibleException;

public class Consulta extends Turno {

    private String sintomas;
    private String diagnostico;

    public Consulta(int id, String fecha, String hora,
                    String descripcion,
                    String sintomas,
                    String diagnostico) {

        super(id, fecha, hora, descripcion);

        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
    }

    @Override
    public void confirmar() throws TurnoNoDisponibleException {
        super.confirmar();
    }

    @Override
    public void cancelar() {
        super.cancelar();
    }

    @Override
    public String getEspecialidad() {
        return "Consulta";
    }

    @Override
    public void mostrarInfo() {

        super.mostrarInfo();

        System.out.println("Sintomas: " + sintomas);
        System.out.println("Diagnostico: " + diagnostico);

        System.out.println("--------------------------------------------");
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
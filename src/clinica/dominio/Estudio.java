package clinica.dominio;

import clinica.excepcion.TurnoNoDisponibleException;

public class Estudio extends Turno {

    private String tipoEstudio;
    private String laboratorio;

    public Estudio(int id, String fecha, String hora,
                   String descripcion,
                   String tipoEstudio,
                   String laboratorio) {

        super(id, fecha, hora, descripcion);

        this.tipoEstudio = tipoEstudio;
        this.laboratorio = laboratorio;
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
        return tipoEstudio;
    }

    @Override
    public void mostrarInfo() {

        super.mostrarInfo();

        System.out.println("Tipo de estudio: " + tipoEstudio);
        System.out.println("Laboratorio: " + laboratorio);
    }

    public String getTipoEstudio() {
        return tipoEstudio;
    }

    public void setTipoEstudio(String tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }
}
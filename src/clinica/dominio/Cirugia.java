package clinica.dominio;

import clinica.excepcion.TurnoNoDisponibleException;

public class Cirugia extends Turno {

    private String tipoCirugia;
    private int quirofano;

    public Cirugia(int id, String fecha, String hora,
                   String descripcion,
                   String tipoCirugia,
                   int quirofano) {

        super(id, fecha, hora, descripcion);

        this.tipoCirugia = tipoCirugia;
        this.quirofano = quirofano;
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
        return tipoCirugia;
    }

    @Override
    public void mostrarInfo() {

        super.mostrarInfo();

        System.out.println("Tipo de cirugia: " + tipoCirugia);
        System.out.println("Quirofano: " + quirofano);
    }

    public String getTipoCirugia() {
        return tipoCirugia;
    }

    public void setTipoCirugia(String tipoCirugia) {
        this.tipoCirugia = tipoCirugia;
    }

    public int getQuirofano() {
        return quirofano;
    }

    public void setQuirofano(int quirofano) {
        this.quirofano = quirofano;
    }
}

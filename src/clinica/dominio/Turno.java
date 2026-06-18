package clinica.dominio;

import java.io.Serializable;

import clinica.excepcion.TurnoNoDisponibleException;

/**
 * Representa un turno dentro de la clínica.
 * Puede ser una Consulta, Estudio o Cirugía.
 */
public abstract class Turno implements IAgendable, Serializable {

    protected int id;
    protected String fecha;
    protected String hora;
    protected String descripcion;
    private EstadoTurno estado;

    public Turno(int id, String fecha, String hora, String descripcion) {

        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.estado = EstadoTurno.PENDIENTE;
    }

    public Turno() {

    }

    public abstract String getEspecialidad();

    public void mostrarInfo() {
        System.out.println("Turno ID: " + id);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Estado: " + estado);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Especialidad: " + getEspecialidad());
    }

    @Override
    public void confirmar() throws TurnoNoDisponibleException {

        if (estado != EstadoTurno.PENDIENTE) {

            throw new TurnoNoDisponibleException(
                    "El turno no se puede confirmar");
        }

        estado = EstadoTurno.CONFIRMADO;
    }

    @Override
    public void cancelar() {
        estado = EstadoTurno.CANCELADO;
    }

    @Override
    public String toString() {
        return "Turno ID: " + id + "\n" +
               " Fecha: " + fecha + "\n" +
               " Hora: " + hora + "\n" +
               " Estado: " + estado + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }
}
package clinica.excepcion;

public class TurnoNoDisponibleException extends Exception {

    public TurnoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}

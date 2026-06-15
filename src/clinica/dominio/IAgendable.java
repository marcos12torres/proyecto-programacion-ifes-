package clinica.dominio;

import clinica.excepcion.TurnoNoDisponibleException;

public interface IAgendable {

    void confirmar() throws TurnoNoDisponibleException;

    void cancelar();
}
package clinica.servicio;

import java.util.List;

public interface IServicio<T> {

    void agregar(T elemento);

    List<T> listar();

}
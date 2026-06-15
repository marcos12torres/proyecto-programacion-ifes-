package repositorio;

import java.util.List;

public interface IRepositorio<T> {

    void guardar(List<T> elementos);

    List<T> consultar();
}


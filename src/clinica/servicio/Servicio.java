package clinica.servicio;

import java.util.List;

import clinica.repositorio.IRepositorio;

public class Servicio<T> implements IServicio<T> {

    private IRepositorio<T> repositorio;
    private List<T> elementos;

    public Servicio(IRepositorio<T> repositorio) {

        this.repositorio = repositorio;
        this.elementos = repositorio.consultar();
    }

    @Override
    public void agregar(T elemento) {

        elementos.add(elemento);
        repositorio.guardar(elementos);
    }

    @Override
    public List<T> listar() {

        return elementos;
    }

    public IRepositorio<T> getRepositorio() {

        return repositorio;
    }
}
package repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo<T> implements IRepositorio<T> {

    private String nombreArchivo;

    public RepositorioArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void guardar(List<T> elementos) {

        try (ObjectOutputStream salida =
                     new ObjectOutputStream(
                             new FileOutputStream(nombreArchivo))) {

            salida.writeObject(elementos);

        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> consultar() {

        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                     new ObjectInputStream(
                             new FileInputStream(nombreArchivo))) {

            return (List<T>) entrada.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error al leer archivo: " + e.getMessage());

            return new ArrayList<>();
        }
    }
}
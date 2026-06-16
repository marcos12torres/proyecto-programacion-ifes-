package clinica.repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo<T> implements IRepositorio<T> {

    private File nombreArchivo;

    public RepositorioArchivo(String nombreArchivo) {

        this.nombreArchivo = new File(nombreArchivo);
    }

    @Override
    public void guardar(List<T> elementos) {

        try (ObjectOutputStream salida =
                     new ObjectOutputStream(
                             new FileOutputStream(nombreArchivo))) {

            salida.writeObject(elementos);

        } catch (Exception e) {

            e.printStackTrace();
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

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
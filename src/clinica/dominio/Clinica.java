package clinica.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Clinica {

    private ArrayList<Turno> turnos;
    private HashMap<Integer, Persona> personas;
    private HashSet<Persona> personasActivas;

    public Clinica() {

        this.turnos = new ArrayList<>();
        this.personas = new HashMap<>();
        this.personasActivas = new HashSet<>();
    }

    public void agregarPersona(Persona persona) {

        personas.put(persona.getDni(), persona);
    }

    public void agregarTurno(Turno turno) {

        turnos.add(turno);
    }

    public void agregarPersonaActiva(Persona persona) {

        personasActivas.add(persona);
    }

    public Paciente buscarPaciente(int dni) {

        Persona persona = personas.get(dni);

        if (persona instanceof Paciente) {

            return (Paciente) persona;
        }

        return null;
    }
    public Medico buscarMedico(int dni) {

        Persona persona = personas.get(dni);
    
        if (persona instanceof Medico) {
    
            return (Medico) persona;
        }
    
        return null;
    }

    public Turno buscarTurno(String fecha) {

        return turnos.stream()
                .filter(t -> t.getFecha().equalsIgnoreCase(fecha))
                .findFirst()
                .orElse(null);
    }

    public List<Turno> mostrarPendientes() {

        return turnos.stream()
                .filter(t -> t.getEstado() == EstadoTurno.PENDIENTE)
                .collect(Collectors.toList());
    }

    public void mostrarOrdenados() {

        turnos.stream()
                .sorted((t1, t2) ->
                        t1.getFecha().compareTo(t2.getFecha()))
                .forEach(Turno::mostrarInfo);
    }

    public void ordenarConComparator() {

        Collections.sort(turnos,
                Comparator.comparing(Turno::getFecha));
    }

    public long contarCancelados() {

        return turnos.stream()
                .filter(t -> t.getEstado() == EstadoTurno.CANCELADO)
                .count();
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public HashMap<Integer, Persona> getPersonas() {
        return personas;
    }

    public HashSet<Persona> getPersonasActivas() {
        return personasActivas;
    }
}
package clinica.dominio;

public class Medico extends Persona {

    private int matricula;
    private String especialidad;

    public Medico(String nombre, String apellido, int dni,
                  int matricula, String especialidad) {

        super(nombre, apellido, dni);

        this.matricula = matricula;
        this.especialidad = especialidad;
    }

    public void registrarTurno(Turno turno) {

        System.out.println("El medico " + nombre +
                           " registro el turno:");

        turno.mostrarInfo();
    }
    @Override
    public void mostrarInfo() {

        System.out.println("--------------------------------------------");

        System.out.println("Medico: " + nombre + " Apellido " + apellido);
        System.out.println("DNI: " + dni);
        System.out.println("Matricula: " + matricula);
        System.out.println("Especialidad: " + especialidad);

        System.out.println("--------------------------------------------");
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
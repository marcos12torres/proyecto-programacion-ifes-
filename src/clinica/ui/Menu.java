package clinica.ui;
import java.util.Scanner;
import clinica.dominio.Cirugia;
import clinica.dominio.Clinica;
import clinica.dominio.Consulta;
import clinica.dominio.Estudio;
import clinica.dominio.Paciente;
import clinica.dominio.Medico;
import clinica.dominio.Turno;
import clinica.excepcion.TurnoNoDisponibleException;
import clinica.repositorio.IRepositorio;
import clinica.repositorio.RepositorioArchivo;
import clinica.dominio.Persona;
public class Menu {

    private Scanner scanner;
    private Clinica clinica;
    private IRepositorio<Turno> repositorioTurnos;
    private IRepositorio<Paciente> repositorioPacientes;
    private IRepositorio<Medico> repositorioMedicos;
    public void iniciar() {

        scanner = new Scanner(System.in);

        clinica = new Clinica();

        
        repositorioTurnos = new RepositorioArchivo<>("turnos.dat");
        repositorioPacientes = new RepositorioArchivo<>("pacientes.dat");
        repositorioMedicos = new RepositorioArchivo<>("medicos.dat");   

        cargarDatos();

        int opcion = -1;

        do {

            System.out.println("\n===== CLINICA =====");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar medico");
            System.out.println("3. Agregar turno");
            System.out.println("4. Confirmar turno");
            System.out.println("5. Cancelar turno");
            System.out.println("6. Mostrar pendientes");
            System.out.println("7. Mostrar agenda");
            System.out.println("8. Mostrar cancelados");
            System.out.println("9. Mostrar historial paciente");
            System.out.println("10. Mostrar medicos");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opcion: ");

            if (scanner.hasNextInt()) {

                opcion = scanner.nextInt();

            } else {

                System.out.println("Debe ingresar una opcion valida");
                scanner.next();
                continue;
            }

            scanner.nextLine();

            switch (opcion) {

                case 1:
                    registrarPaciente();
                    break;
                case 2:
                    registrarMedico();
                    break;
                case 3:
                    agregarTurno();
                    break;

                case 4:
                    confirmarTurno();
                    break;

                case 5:
                    cancelarTurno();
                    break;

                case 6:
                    mostrarPendientes();
                    break;

                case 7:
                    mostrarAgenda();
                    break;

                case 8:
                    mostrarCancelados();
                    break;

                case 9:
                    mostrarHistorialPaciente();
                    break;
                case 10:
                    mostrarMedicos();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private void cargarDatos() {

        for (Paciente paciente :
                repositorioPacientes.consultar()) {
    
            clinica.agregarPersona(paciente);
            clinica.agregarPersonaActiva(paciente);
        }
    
        for (Turno turno :
                repositorioTurnos.consultar()) {
    
            clinica.agregarTurno(turno);
        }
        for (Medico medico :
            repositorioMedicos.consultar()) {
    
        clinica.agregarPersona(medico);
    }
    }
   

    private void guardarTurnos() {
        repositorioTurnos.guardar(clinica.getTurnos());
    }

    private void guardarPacientes() {

        java.util.List<Paciente> pacientes =
                new java.util.ArrayList<>();
    
        for (Object persona :
                clinica.getPersonas().values()) {
    
            if (persona instanceof Paciente) {
    
                pacientes.add((Paciente) persona);
            }
        }
    
        repositorioPacientes.guardar(pacientes);
    }


    private void guardarMedicos() {

        java.util.List<Medico> medicos =
                new java.util.ArrayList<>();
    
        for (Object persona :
                clinica.getPersonas().values()) {
    
            if (persona instanceof Medico) {
    
                medicos.add((Medico) persona);
            }
        }
    
        repositorioMedicos.guardar(medicos);
    }

    private void registrarPaciente() {

        System.out.println("\n--- REGISTRAR PACIENTE ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Obra social: ");
        String obraSocial = scanner.nextLine();

        Paciente paciente =
                new Paciente(nombre, apellido, dni, obraSocial);

        clinica.agregarPersona(paciente);
        clinica.agregarPersonaActiva(paciente);

        guardarPacientes();

        System.out.println("Paciente registrado.");
    }
    private void registrarMedico() {

        System.out.println("\n--- REGISTRAR MEDICO ---");
    
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
    
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
    
        System.out.print("DNI: ");
        int dni = scanner.nextInt();
    
        System.out.print("Matricula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();
    
        Medico medico =
                new Medico(
                        nombre,
                        apellido,
                        dni,
                        matricula,
                        especialidad);
    
        clinica.agregarPersona(medico);
    
        guardarMedicos();
    
        System.out.println("Medico registrado.");
    }

    private void agregarTurno() {

        System.out.println("\n--- AGREGAR TURNO ---");

        System.out.println("1. Consulta");
        System.out.println("2. Estudio");
        System.out.println("3. Cirugia");


        

        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("DNI del paciente: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        Paciente paciente = clinica.buscarPaciente(dni);

        if (paciente == null) {

            System.out.println("Paciente no encontrado");
            return;
        }

        System.out.print("DNI del medico: ");
        int dniMedico = scanner.nextInt();
        scanner.nextLine();

        Medico medico = clinica.buscarMedico(dniMedico);

        if (medico == null) {

            System.out.println("Medico no encontrado");
            return;
        }

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Fecha: ");
        String fecha = scanner.nextLine();

        System.out.print("Hora: ");
        String hora = scanner.nextLine();

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        Turno turno = null;

        switch (tipo) {

            case 1:

                System.out.print("Sintomas: ");
                String sintomas = scanner.nextLine();

                System.out.print("Diagnostico: ");
                String diagnostico = scanner.nextLine();

                turno = new Consulta(
                        id,
                        fecha,
                        hora,
                        descripcion,
                        sintomas,
                        diagnostico);

                break;

            case 2:

                System.out.print("Tipo estudio: ");
                String tipoEstudio = scanner.nextLine();

                System.out.print("Laboratorio: ");
                String laboratorio = scanner.nextLine();

                turno = new Estudio(
                        id,
                        fecha,
                        hora,
                        descripcion,
                        tipoEstudio,
                        laboratorio);

                break;

            case 3:

                System.out.print("Tipo cirugia: ");
                String tipoCirugia = scanner.nextLine();

                System.out.print("Quirofano: ");
                int quirofano = scanner.nextInt();
                scanner.nextLine();

                turno = new Cirugia(
                        id,
                        fecha,
                        hora,
                        descripcion,
                        tipoCirugia,
                        quirofano);

                break;
        }

        if (turno != null) {
            clinica.agregarTurno(turno);

            paciente.solicitarTurno(turno);
        
            medico.registrarTurno(turno);
        
            guardarTurnos();
            guardarPacientes();
        
            System.out.println("Turno agregado.");
        }
    }

    private void confirmarTurno() {

        System.out.println("\n--- CONFIRMAR TURNO ---");

        System.out.print("Fecha: ");
        String fecha = scanner.nextLine();

        Turno turno = clinica.buscarTurno(fecha);

        if (turno == null) {

            System.out.println("Turno no encontrado");
            return;
        }

        try {

            turno.confirmar();
            guardarTurnos();
            guardarPacientes();
            System.out.println("Turno confirmado");

        } catch (TurnoNoDisponibleException e) {

            System.out.println(e.getMessage());
        } finally {

            System.out.println("Operacion finalizada");
        }
    }

    private void cancelarTurno() {

        System.out.println("\n--- CANCELAR TURNO ---");

        System.out.print("Fecha: ");
        String fecha = scanner.nextLine();

        Turno turno = clinica.buscarTurno(fecha);

        if (turno == null) {

            System.out.println("Turno no encontrado");
            return;
        }

        turno.cancelar();
        guardarTurnos();
        guardarPacientes();
        System.out.println("Turno cancelado");
    }

    private void mostrarPendientes() {

        System.out.println("\n--- TURNOS PENDIENTES ---");

        clinica.mostrarPendientes()
                .forEach(System.out::println);
    }

    private void mostrarAgenda() {

        System.out.println("\n--- AGENDA ---");
    
        clinica.ordenarConComparator();
    
        clinica.getTurnos()
                .forEach(Turno::mostrarInfo);
    }

    private void mostrarCancelados() {

        System.out.println("\n--- TURNOS CANCELADOS ---");

        System.out.println(
                "Cantidad: " + clinica.contarCancelados());
    }

    private void mostrarHistorialPaciente() {

        System.out.println("\n--- HISTORIAL PACIENTE ---");
    
        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine();
    
        Paciente paciente = clinica.buscarPaciente(dni);
    
        if (paciente == null) {
    
            System.out.println("Paciente no encontrado");
            return;
        }
    
        paciente.mostrarHistorial();
    }
   
    private void mostrarMedicos() {

        System.out.println("\n--- MEDICOS ---");
    
        for (Persona persona :
                clinica.getPersonas().values()) {
    
            if (persona instanceof Medico) {
    
                persona.mostrarInfo();
            }
        }
    }}
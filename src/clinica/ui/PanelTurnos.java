package clinica.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; // Importa todos los componentes gráficos

import clinica.dominio.Cirugia;
import clinica.dominio.Consulta;
import clinica.dominio.Estudio;
import clinica.dominio.Turno;

/*
==========================================================
                FORMULARIO DE TURNOS
==========================================================

Permite registrar y editar turnos.

Según el tipo seleccionado crea:
- Consulta
- Estudio
- Cirugia

==========================================================
*/

public class PanelTurnos extends JPanel implements ActionListener {

    // Campos de texto para ingresar datos
    private JTextField campoId;
    private JTextField campoFecha;
    private JTextField campoHora;
    private JTextField campoDescripcion;
    private JTextField campoDato1;
    private JTextField campoDato2;

    // Etiquetas dinámicas (cambian según tipo de turno)
    private JLabel labelDato1;
    private JLabel labelDato2;

    // Combo para elegir tipo de turno
    private JComboBox<String> comboTipo;

    // Botones
    private JButton botonGuardar;
    private JButton botonSalir;

    // Modelo de tabla y manager para manejar datos
    private TableTurnoModel modelo;
    private PanelManager manager;

    // Para saber si estamos editando un turno existente
    private int filaEditando = -1;

    // Constructor: arma el formulario
    public PanelTurnos(TableTurnoModel modelo, PanelManager manager) {

        this.modelo = modelo;
        this.manager = manager;

        // Layout en grilla de 8 filas x 2 columnas
        setLayout(new GridLayout(8,2,10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Combo tipo de turno
        add(new JLabel("Tipo"));
        comboTipo = new JComboBox<>(new String[]{"Consulta","Estudio","Cirugia"});
        add(comboTipo);

        // Cuando cambia el tipo, actualiza etiquetas
        comboTipo.addActionListener(e -> actualizarEtiquetas());

        // Campos básicos
        add(new JLabel("ID"));
        campoId = new JTextField();
        add(campoId);

        add(new JLabel("Fecha"));
        campoFecha = new JTextField();
        add(campoFecha);

        add(new JLabel("Hora"));
        campoHora = new JTextField();
        add(campoHora);

        add(new JLabel("Descripcion"));
        campoDescripcion = new JTextField();
        add(campoDescripcion);

        // Campos variables según tipo
        labelDato1 = new JLabel("Síntomas");
        add(labelDato1);
        campoDato1 = new JTextField();
        add(campoDato1);

        labelDato2 = new JLabel("Diagnóstico");
        add(labelDato2);
        campoDato2 = new JTextField();
        add(campoDato2);

        // Botones
        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(this);
        add(botonGuardar);

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        add(botonSalir);

        // Inicializa etiquetas según tipo seleccionado
        actualizarEtiquetas();
    }

    // Cambia etiquetas según el tipo de turno
    private void actualizarEtiquetas() {
        switch (comboTipo.getSelectedIndex()) {
            case 0: // Consulta
                labelDato1.setText("Síntomas");
                labelDato2.setText("Diagnostico");
                break;
            case 1: // Estudio
                labelDato1.setText("Tipo estudio");
                labelDato2.setText("Laboratorio");
                break;
            default: // Cirugía
                labelDato1.setText("Tipo cirugia");
                labelDato2.setText("Quirofano");
                break;
        }
    }

    // Carga un turno existente para editarlo
    public void cargarTurno(Turno turno,int fila){
        campoId.setText(String.valueOf(turno.getId()));
        campoFecha.setText(turno.getFecha());
        campoHora.setText(turno.getHora());
        campoDescripcion.setText(turno.getDescripcion());

        // Según el tipo de objeto, carga los datos específicos
        if (turno instanceof Consulta) {
            comboTipo.setSelectedIndex(0);
            campoDato1.setText(((Consulta) turno).getSintomas());
            campoDato2.setText(((Consulta) turno).getDiagnostico());
        } else if (turno instanceof Estudio) {
            comboTipo.setSelectedIndex(1);
            campoDato1.setText(((Estudio) turno).getTipoEstudio());
            campoDato2.setText(((Estudio) turno).getLaboratorio());
        } else if (turno instanceof Cirugia) {
            comboTipo.setSelectedIndex(2);
            campoDato1.setText(((Cirugia) turno).getTipoCirugia());
            campoDato2.setText(String.valueOf(((Cirugia) turno).getQuirofano()));
        }

        actualizarEtiquetas();
        filaEditando = fila; // Marca que estamos editando
    }

    // Manejo de eventos de botones
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==botonGuardar){
            try{
                int id = Integer.parseInt(campoId.getText());
                Turno turno;

                // Según tipo crea objeto correspondiente
                switch(comboTipo.getSelectedIndex()){
                    case 0: // Consulta
                        turno = new Consulta(
                                id,
                                campoFecha.getText(),
                                campoHora.getText(),
                                campoDescripcion.getText(),
                                campoDato1.getText(),
                                campoDato2.getText());
                        break;
                    case 1: // Estudio
                        turno = new Estudio(
                                id,
                                campoFecha.getText(),
                                campoHora.getText(),
                                campoDescripcion.getText(),
                                campoDato1.getText(),
                                campoDato2.getText());
                        break;
                    default: // Cirugía
                        turno = new Cirugia(
                                id,
                                campoFecha.getText(),
                                campoHora.getText(),
                                campoDescripcion.getText(),
                                campoDato1.getText(),
                                Integer.parseInt(campoDato2.getText()));
                }

                // Si es nuevo turno
                if(filaEditando==-1){
                    manager.getServicioTurnos().agregar(turno);
                    modelo.fireTableDataChanged();
                }else{ // Si estamos editando
                    modelo.actualizarTurno(filaEditando, turno);
                    manager.getServicioTurnos().listar().set(filaEditando, turno);
                    manager.getServicioTurnos()
                            .getRepositorio()
                            .guardar(manager.getServicioTurnos().listar());
                    filaEditando = -1;
                }

                JOptionPane.showMessageDialog(null,"Turno guardado correctamente");
                limpiarCampos();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Datos incorrectos");
            }

        }else if(e.getSource()==botonSalir){
            System.exit(0); // Cierra programa
        }
    }

    // Limpia todos los campos del formulario
    private void limpiarCampos(){
        campoId.setText("");
        campoFecha.setText("");
        campoHora.setText("");
        campoDescripcion.setText("");
        campoDato1.setText("");
        campoDato2.setText("");
        comboTipo.setSelectedIndex(0);
        actualizarEtiquetas();
    }
}

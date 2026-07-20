 🏥 Sistema de Clínica / Consultorio

## Trabajo Práctico Integrador – Programación I

Aplicación desarrollada en **Java** para la gestión de una clínica o consultorio médico utilizando **Programación Orientada a Objetos** e **Interfaz Gráfica con Swing**.

---

👨‍💻 Integrantes

- Marcos Torres
- Federico Matus

---

📚 Materia

Programación I

---

🏫 Institución

IFES – Analista de Sistemas

---

📖 Descripción

El sistema permite administrar pacientes, médicos y turnos médicos mediante una interfaz gráfica desarrollada con Java Swing.

Los turnos pueden registrarse como distintos tipos (**Consulta, Estudio o Cirugía**) aplicando herencia y polimorfismo. Además, la aplicación permite administrar pacientes y médicos, visualizar la información en tablas y almacenar los datos en archivos para conservar la información entre ejecuciones.

---

✅ Funcionalidades

Pacientes

- Registrar pacientes.
- Modificar pacientes.
- Eliminar pacientes.
- Listar pacientes.

Médicos

- Registrar médicos.
- Modificar médicos.
- Eliminar médicos.
- Listar médicos.

Turnos

- Registrar turnos.
- Modificar turnos.
- Eliminar turnos.
- Listar turnos.
- Manejo de distintos tipos de turnos:
  - Consulta
  - Estudio
  - Cirugía

---

🖥️ Interfaz Gráfica

La aplicación cuenta con:

- Pantalla principal.
- Menú de navegación.
- Formularios para cada entidad.
- Tablas (`JTable`) para visualizar los registros.
- Actualización automática de los listados luego de realizar altas, modificaciones o bajas.

---

💾 Persistencia

Los datos se almacenan utilizando archivos `.dat` mediante serialización, permitiendo conservar la información entre distintas ejecuciones del programa.

---

🛠️ Tecnologías utilizadas

- Java
- Java Swing
- Programación Orientada a Objetos
- Git
- GitHub
- Visual Studio Code

---

📌 Conceptos aplicados

- Herencia
- Polimorfismo
- Encapsulamiento
- Abstracción
- Interfaces gráficas con Swing
- JTable y AbstractTableModel
- CardLayout
- Enum
- Serializable
- Comparable
- equals() y hashCode()
- Collections Framework
- Streams y Lambdas
- Persistencia de datos en archivos
- Arquitectura por capas (UI, Servicios, Repositorios y Dominio)

---

▶️ Ejecución

1. Clonar el repositorio.

```bash
git clone https://github.com/marcos12torres/proyecto-programacion-ifes-
```

2. Abrir el proyecto en Visual Studio Code.

3. Compilar el proyecto Java.

4. Ejecutar la clase:

```
clinica.ui.Ventana
```
📂 Repositorio

Proyecto desarrollado utilizando **Git** y **GitHub** para el control de versiones y el trabajo colaborativo entre los integrantes.

Repositorio:

https://github.com/marcos12torres/proyecto-programacion-ifes-
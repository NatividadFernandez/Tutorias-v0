package org.iesalandalus.programacion.tutorias.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Vista {

	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		int opcion;
		Opcion opciones;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			opciones = Opcion.getOpcionSegunOrdinal(opcion);
			opciones.ejecutar();
		} while (opcion != Opcion.SALIR.ordinal());
	}

	public void terminar() {
		controlador.terminar();
	}

	// Alumno
	public void insertarAlumno() {
		Consola.mostrarCabecera("Insertar Alumno");
		try {
			controlador.insertarAlumno(Consola.leerAlumno());
			System.out.println("Alumno insertado correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarAlumno() {
		Consola.mostrarCabecera("Buscar Alumno");
		Alumno alumno;
		try {
			alumno = controlador.buscarAlumno(Consola.leerAlumnoFicticio());
			String mensaje = (alumno != null) ? alumno.toString() : "No existe dicho alumno.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlumno() {
		Consola.mostrarCabecera("Borrar Alumno");
		try {
			controlador.borrarAlumno(Consola.leerAlumnoFicticio());
			System.out.println("Alumno borrado correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void listarAlumnos() {
		Consola.mostrarCabecera("Listar Alumnos");
		Alumno[] alumnos = controlador.getAlumnos();
		if (alumnos[0] != null) {
			for (Alumno alumno : alumnos) {
				if (alumno != null) {
					System.out.println(alumno);
				}
			}
		} else {
			System.out.println("No hay alumnos que mostrar.");
		}
	}

	// Profesor
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException| NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesorFicticio());
			String mensaje = (profesor != null) ? profesor.toString() : "No existe dicho profesor.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesorFicticio());
			System.out.println("Profesor borrado correctamente.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	// DUDAS
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar Profesores");
		Profesor[] profesores = controlador.getProfesores();
		if (profesores[0] != null) {
			for (Profesor profesor : profesores) {
				if (profesor != null) {
					System.out.println(profesor);
				}
			}
		} else {
			System.out.println("No hay profesores que mostrar.");
		}
	}

	// Tutorias
	public void insertarTutoria() {
		Consola.mostrarCabecera("Insertar Tutoría");
		try {
			controlador.insertarTutoria(Consola.leerTutoria());
			System.out.println("Tutoría insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarTutoria() {
		Consola.mostrarCabecera("Buscar Tutoría");
		Tutoria tutoria;
		try {
			tutoria = controlador.buscarTutoria(Consola.leerTutoria());
			String mensaje = (tutoria != null) ? tutoria.toString() : "No existe dicha tutoría.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarTutoria() {
		Consola.mostrarCabecera("Borrar Tutoría");
		try {
			controlador.borrarTutoria(Consola.leerTutoria());
			System.out.println("Tutoría borrada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarTutorias() {
		Consola.mostrarCabecera("Listar Tutorías");
		Tutoria[] tutorias = controlador.getTutorias();
		if (tutorias[0] != null) {
			for (Tutoria tutoria : tutorias) {
				if (tutoria != null) {
					System.out.println(tutoria);
				}
			}
		} else {
			System.out.println("No hay tutorías que mostrar.");
		}
	}

	public void listarTutoriasProfesor() {
		Consola.mostrarCabecera("Listar Tutorías Profesor");
		Tutoria[] tutorias = controlador.getTutorias(Consola.leerProfesorFicticio());
		if (tutorias[0] != null) {
			for (Tutoria tutoria : tutorias) {
				if (tutoria != null) {
					System.out.println(tutoria);
				}
			}
		} else {
			System.out.println("No hay tutorías, para dicho profesor, que mostrar.");
		}
	}

	// Sesion
	public void insertarSesion() {
		Consola.mostrarCabecera("Insertar Sesión");
		try {
			controlador.insertarSesion(Consola.leerSesion());
			System.out.println("Sesión insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarSesion() {
		Consola.mostrarCabecera("Buscar Sesión");
		Sesion sesion;
		try {
			sesion = controlador.buscarSesion(Consola.leerSesionFicticia());
			String mensaje = (sesion != null) ? sesion.toString() : "No existe dicha sesión.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarSesion() {
		Consola.mostrarCabecera("Borrar Sesión");
		try {
			controlador.borrarSesion(Consola.leerSesionFicticia());
			System.out.println("Sesión borrada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarSesiones() {
		Consola.mostrarCabecera("Listar Sesiones");
		Sesion[] sesiones = controlador.getSesiones();
		if (sesiones[0] != null) {
			for (Sesion sesion : sesiones) {
				if (sesion != null) {
					System.out.println(sesion);
				}
			}
		} else {
			System.out.println("No hay sesiones que mostrar.");
		}
	}

	public void listarSesionesTutoria() {
		Consola.mostrarCabecera("Listar Sesiones Tutoría");
		Sesion[] sesiones = controlador.getSesiones(Consola.leerTutoria());
		if (sesiones[0] != null) {
			for (Sesion sesion : sesiones) {
				if (sesion != null) {
					System.out.println(sesion);
				}
			}
		} else {
			System.out.println("No hay sesiones, para dicha tutoría, que mostrar.");
		}
	}

	// Cita
	public void insertarCita() {
		Consola.mostrarCabecera("Insertar Cita");
		try {
			controlador.insertarCita(Consola.leerCita());
			System.out.println("Cita insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarCita() {
		Consola.mostrarCabecera("Buscar Cita");
		Cita cita;
		try {
			cita = controlador.buscarCita(Consola.leerCita());
			String mensaje = (cita != null) ? cita.toString() : "No existe dicha cita.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCita() {
		Consola.mostrarCabecera("Borrar Cita");
		try {
			controlador.borrarCita(Consola.leerCita());
			System.out.println("Cita borrada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarCitas() {
		Consola.mostrarCabecera("Listar Citas");
		Cita[] citas = controlador.getCitas();
		if (citas[0] != null) {
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
		} else {
			System.out.println("No hay citas que mostrar.");
		}
	}

	public void listarCitasSesion() {
		Consola.mostrarCabecera("Listas Citas Sesiones");
		Cita[] citas = controlador.getCitas(Consola.leerSesionFicticia());
		if (citas[0] != null) {
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
		} else {
			System.out.println("No hay citas, para dicha sesión, que mostrar.");
		}
	}

	public void listarCitasAlumno() {
		Consola.mostrarCabecera("Listas Citas Alumnos");
		Cita[] citas = controlador.getCitas(Consola.leerAlumnoFicticio());
		if (citas[0] != null) {
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
		} else {
			System.out.println("No hay citas, para dicho alumno, que mostrar.");
		}
	}
}

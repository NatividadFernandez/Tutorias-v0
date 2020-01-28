package org.iesalandalus.programacion.tutorias.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.*;

public class Modelo {

	private static final int CAPACIDAD = 5;
	private Alumnos alumnos;
	private Citas citas;
	private Profesores profesores;
	private Sesiones sesiones;
	private Tutorias tutorias;

	public Modelo() {
		alumnos = new Alumnos(CAPACIDAD);
		citas = new Citas(CAPACIDAD);
		profesores = new Profesores(CAPACIDAD);
		sesiones = new Sesiones(CAPACIDAD);
		tutorias = new Tutorias(CAPACIDAD);
	}

	// Alumno
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.insertar(alumno);
	}

	public Alumno buscar(Alumno alumno) {
		return alumnos.buscar(alumno);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.borrar(alumno);
	}

	public Alumno[] getAlumnos() {
		return alumnos.get();
	}

	// Profesor
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}

	public Profesor buscar(Profesor profesor) {
		return profesores.buscar(profesor);
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	public Profesor[] getProfesores() {
		return profesores.get();
	}

	// Tutoría
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		//No pasa este test debido a las comprobaciones de resctricion que no se piden en la tarea.
		if (tutoria == null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}

		Profesor profesor = profesores.buscar(tutoria.getProfesor());
		if (profesor == null) {
			throw new OperationNotSupportedException("ERROR: No existe profesor con ese DNI.");
		}
		tutorias.insertar(new Tutoria(profesor, tutoria.getNombre()));
	}

	public Tutoria buscar(Tutoria tutoria) {
		return tutorias.buscar(tutoria);
	}

	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		tutorias.borrar(tutoria);
	}

	public Tutoria[] getTutorias() {
		return tutorias.get();
	}

	public Tutoria[] getTutorias(Profesor profesor) {
		return tutorias.get(profesor);
	}

	// Sesion
	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		//No pasa este test debido a las comprobaciones de resctricion que no se piden en la tarea.
		if (sesion == null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}

		Tutoria tutoria = tutorias.buscar(sesion.getTutoria());
		if (tutoria == null) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese nombre o profesor.");
		}

		sesiones.insertar(new Sesion(tutoria, sesion.getFecha(), sesion.getHoraInicio(), sesion.getHoraFin(),
				sesion.getMinutosDuracion()));
	}

	public Sesion buscar(Sesion sesion) {
		return sesiones.buscar(sesion);
	}

	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		sesiones.borrar(sesion);
	}

	public Sesion[] getSesiones() {
		return sesiones.get();
	}

	public Sesion[] getSesiones(Tutoria tutoria) {
		return sesiones.get(tutoria);
	}

	// Cita
	public void insertar(Cita cita) throws OperationNotSupportedException {
		//No pasa este test debido a las comprobaciones de resctricion que no se piden en la tarea.
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		Sesion sesion = sesiones.buscar(cita.getSesion());
		if (sesion == null) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha y tutoría.");
		}

		Alumno alumno = alumnos.buscar(cita.getAlumno());
		if (alumno == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");
		}
		citas.insertar(new Cita(alumno, sesion, cita.getHora()));
	}

	public Cita buscar(Cita cita) {
		return citas.buscar(cita);
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		citas.borrar(cita);
	}

	public Cita[] getCitas() {
		return citas.get();
	}

	public Cita[] getCitas(Sesion sesion) {
		return citas.get(sesion);
	}

	public Cita[] getCitas(Alumno alumno) {
		return citas.get(alumno);
	}

}
